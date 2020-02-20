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


object JsonParsingUsers extends ReadJsonData {

  def getAllUsers : Future[List[User]] = {
    val url = "https://jsonplaceholder.typicode.com/users"

    val jsonData: Future[String] = Future {
      readData(url)
    }
    val allUsersFallback : Future[List[User]] = Future{ List.empty[User] }

    val parsedJsonData: Future[List[User]] = for {
      jsonUserData <- jsonData
      parsedJsonData <- Future(parse(jsonUserData))
    } yield parsedJsonData

    val allUsers = parsedJsonData fallbackTo allUsersFallback

    allUsers

  }

  implicit val formats: DefaultFormats.type = DefaultFormats

  def parse(jsonData: String): List[User] = {
    val parsedJsonData = net.liftweb.json.parse(jsonData)

    parsedJsonData.children map { user =>

      user.extract[User]
    }
  }
}
