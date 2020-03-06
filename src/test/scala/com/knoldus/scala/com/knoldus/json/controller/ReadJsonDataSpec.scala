package com.knoldus.json.controller

import org.scalatest.AsyncFlatSpec

import scala.io.Source

class ReadJsonDataSpec extends AsyncFlatSpec {

  behavior of "readData"
  it should "fetch the json data from the specified url" in {
    val actualString = ReadJsonData.readData("https://jsonplaceholder.typicode.com/users")
    val expectedString = Source.fromFile("./src/test/resources/testJsonDataUsers").mkString
    assert(expectedString.trim == actualString.trim)
  }

  it should "eventually return empty list of all the Users as the specified url is incorrect" in {
    val actualString = ReadJsonData.readData("https://jsonplaceholder.typicode.com/user")
    val expectedString = "{}"
    assert(expectedString == actualString)
  }


}
