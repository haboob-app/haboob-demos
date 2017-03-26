var request = require('request'),
    config = require('config').get('haboob');

var env = process.env.NODE_ENV || config.defaultEnv;

var postData = {
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
};

var options = {
  method: 'post',
  body: postData,
  json: true,
  url: config.sendUrl + env
};

request(options, function (err, res, body) {
  if (err) {
    console.error('Error: ', err);
  } else {
    console.info("your request has been sent successfully to", options.url);
    console.info("timestamp:",  body.hook.timestamp);
    console.info('edit url (read only):', config.editUrl );
    console.info('logs:', config.editUrl + '/history');
  }
});
