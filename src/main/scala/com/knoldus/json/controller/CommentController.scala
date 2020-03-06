package com.knoldus.json.controller

import com.knoldus.json.model.Comment
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Controls the flow of json data of comments.
 */
object CommentController {

  /**
   * method getAllComments is used to fetch the json data  of all the Comments from the specified url.
   *
   * @return the list of model class Comment wrapped in Future
   */
  def getAllComments(url: String): Future[List[Comment]] = {

      val jsonData: Future[String] = Future {
      ReadJsonData.readData(url)
    }
    val allCommentsFallback: Future[List[Comment]] = Future {
      List.empty[Comment]
    }

    val parsedJsonData = for {
      jsonCommentData <- jsonData
      parsedJsonData <- Future(DataParser.parser[Comment](jsonCommentData))
    } yield parsedJsonData


    val allComments = parsedJsonData fallbackTo allCommentsFallback

    allComments

  }
}
