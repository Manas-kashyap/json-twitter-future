package com.knoldus.json.controller

import org.scalatest.AsyncFlatSpec

class UserControllerSpec extends AsyncFlatSpec {

  behavior of "getAllUsers"
  it should "eventually fetch the json data  of all the Users from the specified url" in {
    val actualUsersList = UserController.getAllUsers("https://jsonplaceholder.typicode.com/users")
    val expectedFirstUserId = "1"
    actualUsersList.map(usersList =>  assert(usersList.head.id == expectedFirstUserId))
  }

  it should "eventually return empty list of all the Users as the specified url is incorrect" in {
    val actualUsersList = UserController.getAllUsers("https://jsonplaceholder.typicode.com/user")
    actualUsersList.map(usersList =>  assert(usersList.isEmpty))
  }
}
