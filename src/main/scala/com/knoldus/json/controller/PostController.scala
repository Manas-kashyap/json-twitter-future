package com.knoldus.json.controller

import com.knoldus.json.model.Post

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Controls the flow of json data of posts.
 */
object PostController {

  /**
   *  method getAllPosts is used to fetch the json data  of all the posts from the specified url.
   * @return the list of model class Post wrapped in Future
   */
  def getAllPosts(url : String) : Future[List[Post]] = {
    val jsonData: Future[String] = Future {
      ReadJsonData.readData(url)
    }
    val allPostsFallback : Future[List[Post]] = Future{ List.empty[Post] }

    val parsedJsonData: Future[List[Post]] = for {
      jsonCommentData <- jsonData
      parsedJsonData <- Future(DataParser.parser[Post](jsonCommentData))
    } yield parsedJsonData


    val allPosts = parsedJsonData fallbackTo allPostsFallback

    allPosts
  }


}
