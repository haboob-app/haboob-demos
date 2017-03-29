using System;
using System.Collections.Generic;
using System.Net;
using System.IO;
using RestSharp;
using YamlDotNet.Serialization;
using RestSharp.Deserializers;

namespace HaboobDemo
{
    class Program
    {
        static void Main(string[] args)
        {
            try
            {
                var config = Program.LoadHaboobConfig("default.yaml");
                var env = args.Length == 1 ? args[0] : Environment.GetEnvironmentVariable("ENVIRONMENT") ?? config["defaultEnv"];

                var client = new RestClient(config["sendUrl"]);
                var request = new RestRequest(env, Method.POST);
                request.AddHeader("Content-type", "application/json");
                request.RequestFormat = DataFormat.Json;

                request.AddJsonBody(
                    new
                    {
                        confirmUrl = "http://mydomain.com/confirm",
                        user = new { firstName = "Jhon", email = "jhon@domain.com" },
                        lang = "c#",
                        list = new object[] {
                            new { title = "item 1", value = "my value" },
                            new { title = "item 2", value = "" },
                            new { title = "item 3", value = "my value" }
                        }
                    }
                );

                IRestResponse response = client.Execute(request);

                if (response.StatusCode == HttpStatusCode.OK)
                {
                    var jsonDeserializer = new JsonDeserializer();
                    var res = jsonDeserializer.Deserialize<Dictionary<string, Dictionary<string, string>>>(response);
                    Console.WriteLine("your request has been sent successfully to " + config["sendUrl"] + env);
                    Console.WriteLine("timestamp: " + res["hook"]["timestamp"]);
                    Console.WriteLine("edit url (read only): " + config["editUrl"]);
                    Console.WriteLine("logs: " + config["editUrl"] + "/history");
                }
                else
                {
                    Console.WriteLine("Error: " + response.Content);
                }
            }
            catch (WebException ex)
            {
                Console.WriteLine("Error: " + ex.Message);
            }

            Console.ReadKey();
        }

        private static Dictionary<string, string> LoadHaboobConfig(string fileName)
        {
            using (var reader = File.OpenText("default.yaml"))
            {
                string yaml = reader.ReadToEnd();
                var deserializer = new Deserializer();
                var result = deserializer.Deserialize<Dictionary<string, Dictionary<string, string>>>(yaml);
                return result["haboob"];
            }
        }
    }
}
