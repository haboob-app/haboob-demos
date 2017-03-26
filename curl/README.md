# Haboob cURL Demo
Example of how to use haboob with cURL

## Prerequisites

You will need the following things properly installed on your computer.

* [cURL](https://curl.haxx.se/download.html)

## Test

- Test Development:
```{r, engine='bash'}
curl 'https://send.haboob.co/v1/hooks/r1i-hzShe/send/development' \
-XPOST -H "Content-type: application/json" \
-d '{ "confirmUrl": "http://mydomain.com/confirm", "user" : { "firstName": "Jhon", "email": "jhon@domain.com" }, "lang": "cURL", "list": [ { "title": "item 1", "value": "my  value" }, { "title": "item 2", "value": "" }, { "title": "item 3", "value": "my  value" } ]}'
```
- Test Production:
```{r, engine='bash'}
curl 'https://send.haboob.co/v1/hooks/r1i-hzShe/send/development' \
-XPOST -H "Content-type: application/json" \
-d '{ "confirmUrl": "http://mydomain.com/confirm", "user" : { "firstName": "Jhon", "email": "jhon@domain.com" }, "lang": "cURL", "list": [ { "title": "item 1", "value": "my  value" }, { "title": "item 2", "value": "" }, { "title": "item 3", "value": "my  value" } ]}'
```
