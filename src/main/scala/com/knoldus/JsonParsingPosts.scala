package com.knoldus

import net.liftweb.json.DefaultFormats
import net.liftweb.json.JsonAST.{JNothing, JValue}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.language.implicitConversions
import scala.util.{Failure, Success}


case class Post(userId: String, id: String, title: String, body: String)

object JsonParsingPosts extends ReadJsonData {

  def getAllPosts : Future[List[Post]] = {
    val url = "https://jsonplaceholder.typicode.com/posts"
    val jsonData: Future[String] = Future {
      readData(url)
    }
    val allPostsFallback : Future[List[Post]] = Future{ List.empty[Post] }

    val parsedJsonData: Future[List[Post]] = for {
      jsonCommentData <- jsonData
      parsedJsonData <- Future(parse(jsonCommentData))
    } yield parsedJsonData

    val allPosts = parsedJsonData fallbackTo allPostsFallback

    allPosts
  }

  implicit val formats: DefaultFormats.type = DefaultFormats

  def parse(jsonData: String): List[Post] = {
    val parsedJsonData = net.liftweb.json.parse(jsonData)

    parsedJsonData.children map { post =>
      post.extract[Post]
    }
  }

}
