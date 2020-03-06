package com.knoldus.json.controller

import com.knoldus.json.model.User

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


/**
 * Controls the flow of json data of users.
 */
object UserController {
  /**
   * method getAllUsers is used to fetch the json data  of all the Users from the specified url.
   *
   * @return the list of model class Users wrapped in Future
   */
  def getAllUsers(url: String): Future[List[User]] = {

    val jsonData: Future[String] = Future {
      ReadJsonData.readData(url)
    }
    val allUsersFallback: Future[List[User]] = Future {
      List.empty[User]
    }

    val parsedJsonData: Future[List[User]] = for {
      jsonUserData <- jsonData
      parsedJsonData <- Future(DataParser.parser[User](jsonUserData))
    } yield parsedJsonData


    val allUsers = parsedJsonData fallbackTo allUsersFallback


    allUsers

  }

}
