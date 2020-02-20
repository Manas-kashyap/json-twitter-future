package com.knoldus


import net.liftweb.json.DefaultFormats
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.language.implicitConversions

case class Comment(postId: String, id: String, name: String, email: String, body: String)

object JsonParsingComments extends ReadJsonData {

  def getAllComments: Future[List[Comment]] = {
    val url = "https://jsonplaceholder.typicode.com/comments"

    val jsonData: Future[String] = Future(readData(url))

    val allCommentsFallback: Future[List[Comment]] = Future {
      List.empty[Comment]
    }

    val parsedJsonData = for {
      jsonCommentData <- jsonData
      parsedJsonData <- Future(parse(jsonCommentData))
    } yield parsedJsonData

    val allComments = parsedJsonData fallbackTo allCommentsFallback

    allComments

  }

  def parse(jsonData: String): List[Comment] = {
    val parsedJsonData = net.liftweb.json.parse(jsonData)
    parsedJsonData.children map { comment =>

      comment.extract[Comment]

    }
  }

  implicit val formats: DefaultFormats.type = DefaultFormats

}
