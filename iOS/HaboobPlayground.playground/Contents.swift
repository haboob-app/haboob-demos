
import Foundation
import PlaygroundSupport

let body = "{ \"list\": [ { \"title\": \"iOS example\", \"value\": \"iOS emails made easy\" } ], \"user\": { \"email\": \"john@doe-main.com\", \"name\": \"John Doe\" }, \"lang\": \"iOS\", \"confirmUrl\": \"http://haboob.co\" }"

//Replace with production to test production haboob
let environment = "development"

let url = URL(string: "https://send.haboob.co/v1/hooks/r1i-hzShe/send/\(environment)")!
var request = URLRequest(url: url)

//Set the http method as POST
request.httpMethod = "POST"

//Set the Content Type header as JSON
request.setValue("application/json", forHTTPHeaderField: "Content-Type")

//Set the request body
request.httpBody = body.data(using: String.Encoding.utf8)

//Create the URLSession for the previous configured request
let urlSession = URLSession(configuration: URLSessionConfiguration.default)

let dataTask = urlSession.dataTask(with: request) { (data, response, error) in
    if let data = data,
        let contents = NSString(data:data, encoding:String.Encoding.utf8.rawValue) {
        print("data: \(contents)")
    } else {
        print("error: \(error?.localizedDescription as Any)")
    }
    
    //Finish playground execution
    PlaygroundPage.current.finishExecution()
}

//Execute the request
dataTask.resume()

//Please playground, let me call async, thanks.
PlaygroundPage.current.needsIndefiniteExecution = true
