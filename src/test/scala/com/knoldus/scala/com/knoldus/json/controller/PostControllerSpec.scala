package com.knoldus.json.controller

import org.scalatest.AsyncFlatSpec

class PostControllerSpec extends AsyncFlatSpec {

  behavior of "getAllPosts"
  it should "eventually fetch the json data  of all the posts from the specified url" in {
    val actualPostsList = PostController.getAllPosts("https://jsonplaceholder.typicode.com/posts")
    val expectedFirstPostId = "1"
    actualPostsList.map(postsList =>  assert(postsList.head.id == expectedFirstPostId))
  }
  it should "eventually return empty list of all the posts as the specified url is incorrect" in {
    val actualPostsList = PostController.getAllPosts("https://jsonplaceholder.typicode.com/post")
    actualPostsList.map(postsList =>  assert(postsList.isEmpty))
  }
}
