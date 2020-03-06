package com.knoldus.json.controller

import org.scalatest.AsyncFlatSpec

class CommentControllerSpec extends AsyncFlatSpec {

  behavior of "getAllComments"
  it should "eventually fetch the json data  of all the comments from the specified url" in {
    val actualCommentData = CommentController.getAllComments("https://jsonplaceholder.typicode.com/comments")
    val expectedFirstCommentId = "1"
    actualCommentData.map(commentList =>  assert(commentList.head.id == expectedFirstCommentId))
  }

  it should "eventually return empty list of all the comments as the specified url is incorrect" in {
    val actualCommentData = CommentController.getAllComments("https://jsonplaceholder.typicode.com/comment")
    actualCommentData.map(commentList =>  assert(commentList.isEmpty))
  }
}
