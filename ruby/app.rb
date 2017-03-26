require "yaml"
require "net/http"
require "uri"
require "json"

config = YAML.load_file("../config/default.yaml")["haboob"]

env = ENV["ENVIRONMENT"] || config["defaultEnv"]

post_data = {
  "confirmUrl": "http://mydomain.com/confirm",
  "user" => { "firstName": "Jhon", "email": "jhon@domain.com" },
  "lang": "ruby",
  "list": [
    { "title": "item 1", "value": "my  value" },
    { "title": "item 2", "value": "" },
    { "title": "item 3", "value": "my  value" }
  ]
}

uri = URI.parse(config["sendUrl"] + env)

# Create the HTTP objects
http = Net::HTTP.new(uri.host, uri.port)
http.use_ssl = true
request = Net::HTTP::Post.new(uri.request_uri, 'Content-Type' => 'application/json')
request.body = post_data.to_json

# Send the request
begin
  response = http.request(request) {|res|
    body = JSON.parse(res.body)
    puts "your request has been sent successfully to: " + uri.to_s
    puts "timestamp: " + body["hook"]["timestamp"].to_s
    puts "edit url (read only): " + config["editUrl"]
    puts "logs: " + config["editUrl"] + "/history"
  }
rescue Exception => e
  puts "Error: " + e.message
end
