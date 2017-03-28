# Haboob Demos
Examples of how to use [haboob](https://www.haboob.co/) with different programming languages and tools

- [cURL](/curl)
- [Java](/java)
- [Node.js](/nodejs)
- [PHP](/php)
- [Ruby](/ruby)
- [Scala](/scala)
- [Swift](/iOS)

## Configuration

- [Configuration File](/config/default.yaml)
- [View Templates](https://app.haboob.co/r1i-hzShe/edit/Hyej-nMSnx) (Read only)
- [View Logs](https://app.haboob.co/r1i-hzShe/edit/Hyej-nMSnx/history)
- Send Url:
  - Development: `https://send.haboob.co/v1/hooks/r1i-hzShe/send/development`
  - Production: `https://send.haboob.co/v1/hooks/r1i-hzShe/send/production`
- Example payload:
```json

{
  "confirmUrl": "http://mydomain.com/confirm",
  "user" : {
    "firstName": "Jhon",
    "email": "jhon@domain.com"
  },
  "lang": "nodejs",
  "list": [
    { "title": "item 1", "value": "my  value" },
    { "title": "item 2", "value": "" },
    { "title": "item 3", "value": "my  value" }
  ]
}
```
