package haboob

import scala.io.Source
import net.jcazevedo.moultingyaml._
import net.jcazevedo.moultingyaml.DefaultYamlProtocol._

import scala.util.{Failure, Success, Try}
import play.api.libs.json._
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import play.api.libs.ws.WSResponse
import play.api.libs.ws.ahc.AhcWSClient

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object Main extends App {

  case class HaboobConfig(editUrl: String, sendUrl: String, defaultEnv: String)
  case class User(firstName: String, email: String)
  case class KeyValue(title: String, value: String)
  case class PostData(confirmUrl: String, user: User, lang: String, list: Seq[KeyValue])

  implicit val configFormat = yamlFormat3(HaboobConfig)
  implicit val userFormat = Json.format[User]
  implicit val keyValueFormat = Json.format[KeyValue]
  implicit val postDataFormat = Json.format[PostData]

  def withYaml(filename: String): Option[HaboobConfig] = {
    Try(Source.fromFile(filename).mkString.parseYamls) match {
      case Success(yamls) => Some(yamls(0).asYamlObject.fields(YamlString("haboob")).convertTo[HaboobConfig])
      case Failure(error) => None
    }
  }

  def send(uri: String, json: JsValue): Future[WSResponse] = {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    val wsClient = AhcWSClient()
    wsClient
      .url(uri)
      .withHeaders("Content-Type" -> "application/json")
      .post(Json.stringify(json))
      .andThen { case _ => wsClient.close() }
      .andThen { case _ => system.terminate() }

  }

  val postData = PostData(
    "http://mydomain.com/confirm",
    User("Jhon", "jhon@domain.com"),
    "scala",
    Seq(
      KeyValue("item 1", "my value"),
      KeyValue("item 2", ""),
      KeyValue("item 3", "my value")
    )
  )

  val config = withYaml("../config/default.yaml").get
  val url = config.sendUrl + (sys.env.get("ENVIRONMENT").getOrElse(config.defaultEnv))

  send(url, Json.toJson(postData)).onComplete {
    case Success(r) => {
      if (r.status == 200) {
        println("your request has been sent successfully to: " + url)
        println("timestamp: " + (r.json \ "hook" \ "timestamp" ).get)
        println("edit url (read only): " + config.editUrl)
        println("logs: " + config.editUrl + "/history")
      } else {
        println("Error: " + r.body)
      }
    }
    case Failure(e) => println("Error: " + e)
  }
}
