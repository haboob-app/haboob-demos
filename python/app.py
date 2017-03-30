import yaml
import json
import requests
import os

with open("../config/default.yaml", 'r') as stream:
    config = yaml.load(stream)["haboob"]

env =  os.getenv('ENVIRONMENT') or config["defaultEnv"]
url = config["sendUrl"] + env
payload = {
  "confirmUrl": "http://mydomain.com/confirm",
  "user" : {
    "firstName": "Jhon",
    "email": "jhon@domain.com"
  },
  "lang": "python",
  "list": [
    { "title": "item 1", "value": "my  value" },
    { "title": "item 2", "value": "" },
    { "title": "item 3", "value": "my  value" }
  ]
};

headers = {"Content-Type" : "application/json"}
try:
    response = requests.post(url, data=json.dumps(payload), headers=headers)
    response.raise_for_status()
    body = response.json()
    print "your request has been sent successfully to:", url
    print "timestamp:", body["hook"]["timestamp"]
    print "edit url (read only):", config["editUrl"]
    print "logs:", config["editUrl"] + "/history"
except Exception as e:
    print "Error:", str(e)
