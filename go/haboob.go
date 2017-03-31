package main

import (
  "bytes"
  "io/ioutil"
  "net/http"
  "gopkg.in/yaml.v2"
  "log"
  "flag"
)

//Flag reading for environment
type stringFlag struct {
  set   bool
  value string
}

func (sf *stringFlag) Set(x string) error {
  sf.value = x
  sf.set = true
  return nil
}

func (sf *stringFlag) String() string {
  return sf.value
}

var environment stringFlag

func init() {
  flag.Var(&environment, "environment", "the environment")
}

//Settings reading
type config struct {
  Haboob struct {
    DefaultEnv string `yaml:"defaultEnv"`
    PostURL string `yaml:"sendUrl"`
  }
}


func (settings *config) getConf() *config {

  yamlFile, err := ioutil.ReadFile("default.yaml")
  if err != nil {
      log.Printf("yamlFile.Get err   #%v ", err)
  }

  err = yaml.Unmarshal(yamlFile, settings)
  if err != nil {
      log.Fatalf("Unmarshal: %v", err)
  }

  return settings
}

func main() {
  //Read the config file
  var settings config
  settings.getConf()

  //Read the environment flag
  var env string
  flag.Parse()

  if !environment.set {
    env = settings.Haboob.DefaultEnv
  } else {
    env = environment.value
  }

  log.Println(env)
  
  url := settings.Haboob.PostURL + env
  log.Println("Send URL :>", url)

  var jsonStr = []byte(`{ "list": [ { "title": "go example", "value": "go emails made easy" } ], "user": { "email": "john@doe-main.com", "name": "John Doe" }, "lang": "go", "confirmUrl": "http://haboob.co" }`)
  req, err := http.NewRequest("POST", url, bytes.NewBuffer(jsonStr))
  req.Header.Set("Content-Type", "application/json")

  client := &http.Client{}
  resp, err := client.Do(req)
  if err != nil {
      panic(err)
  }
  defer resp.Body.Close()

  log.Println("response Status:", resp.Status)
  log.Println("response Headers:", resp.Header)
  body, _ := ioutil.ReadAll(resp.Body)
  log.Println("response Body:", string(body))
}