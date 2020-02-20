package com.knoldus

import org.scalatest.{AsyncFlatSpec, BeforeAndAfterAll}

class JsonParsingSpec extends AsyncFlatSpec with BeforeAndAfterAll {

  var jsonParsing: JsonParsing = _

  override def beforeAll(): Unit = {
    jsonParsing = new JsonParsing
  }

  "getUserWithMaxPosts" should "return the name of user having max post" in {
    jsonParsing.getUserWithMaxPosts.map(user => assert(user == "Clementina DuBuque"))
  }

  "getUserWithMaxPostComments" should "return the name of user having max comments on post" in {
    jsonParsing.getUserWithMaxPostComments.map(user => assert(user == "Clementina DuBuque"))
  }
}
