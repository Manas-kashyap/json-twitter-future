package com.knoldus

import net.liftweb.json.DefaultFormats
import net.liftweb.json.JsonAST.{JNothing, JValue}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.language.implicitConversions
case class Geo(lat: String, lng: String)

case class Address(street: String, suite: String, city: String, zipcode: String, geo: Geo)

case class Company(name: String, catchPhrase: String, bs: String)

case class User(id: String, name: String, username: String, email: String, address: Address, phone: String, website: String, company: Company)


class JsonParsingUser extends ReadJsonData {

  def getAllUsers: List[User] = {
    val url = "https://jsonplaceholder.typicode.com/users"

    val jsonData: Future[String] = Future {
      readData(url)
    }
    val parsedJsonData = for {
      jsonUserData <- jsonData
      parsedJsonData <- Future(JsonDataParsingUsers.parse(jsonUserData))
    } yield parsedJsonData
    val userData = Await.result(parsedJsonData, 10.seconds)
    userData

  }
}


object JsonDataParsingUsers  {

  implicit val formats: DefaultFormats.type = DefaultFormats

  def parse(jsonData: String): List[User] = {
    val parsedJsonData = net.liftweb.json.parse(jsonData)

    parsedJsonData.children map { user =>

      val id = (user \ "id").extract[String]
      val name = (user \ "name").extract[String]
      val username = (user \ "username").extract[String]
      val email = (user \ "email").extract[String]
      val street = (user \ "address" \ "street").extract[String]
      val suite = (user \ "address" \ "suite").extract[String]
      val city = (user \ "address" \ "city").extract[String]
      val zipcode = (user \ "address" \ "zipcode").extract[String]
      val lat = (user \ "address" \ "geo" \ "lat").extract[String]
      val lng = (user \ "address" \ "geo" \ "lng").extract[String]
      val phone = (user \ "phone").extract[String]
      val website = (user \ "website").extract[String]
      val companyName = (user \ "company" \ "name").extract[String]
      val catchPhrase = (user \ "company" \ "catchPhrase").extract[String]
      val bs = (user \ "company" \ "bs").extract[String]
      User(id, name, username, email, Address(street, suite, city, zipcode, Geo(lat, lng)), phone, website, Company(companyName, catchPhrase, bs))
    }
  }

  implicit def extract(json: JValue): String = json match {
    case JNothing => ""
    case data => data.extract[String]
  }
}

