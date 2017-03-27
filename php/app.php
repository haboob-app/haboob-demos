<?php
require __DIR__ . '/vendor/autoload.php';

use Symfony\Component\Yaml\Yaml;
use GuzzleHttp\Psr7;
use GuzzleHttp\Client;
use GuzzleHttp\Exception\RequestException;

$config = Yaml::parse(file_get_contents("../config/default.yaml"))["haboob"];

$env = getenv('ENVIRONMENT') ?: $config["defaultEnv"];

$postData = [
  "confirmUrl" => "http://mydomain.com/confirm",
  "user" => [ "firstName" => "Jhon", "email" => "jhon@domain.com" ],
  "lang" => "php",
  "list" => [
    [ "title" => "item 1", "value" => "my  value" ],
    [ "title" => "item 2", "value" => "" ],
    [ "title" => "item 3", "value" => "my  value" ]
  ]
];

$url = $config["sendUrl"];

$client = new Client([ "base_uri" => $url ]);

try {
  $response = $client->request('POST', $env, [ 'json' => $postData ]);
  $body = json_decode((string)$response->getBody(), true);
  echo "your request has been sent successfully to: " . $url . $env . "\n";
  echo "timestamp: " . $body["hook"]["timestamp"] . "\n";
  echo "edit url (read only): " . $config["editUrl"] . "\n";
  echo "logs: " . $config["editUrl"] . "/history";
} catch (RequestException $e) {
  if ($e->hasResponse()) {
    echo "Error: " . Psr7\str($e->getResponse());
  } else {
    echo "Error: " . $e->getMessage();
  }
}

?>
