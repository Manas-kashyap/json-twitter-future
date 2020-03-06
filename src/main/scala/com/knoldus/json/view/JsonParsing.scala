package com.knoldus.json.view

import com.knoldus.json.controller.{CommentController, PostController, UserController}
import com.knoldus.json.model._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * JsonParsing is used for demonstrating the functionality of json parsing.
 */
class JsonParsing {

  val allUsers: Future[List[User]] = UserController.getAllUsers("https://jsonplaceholder.typicode.com/users")
  val allPosts: Future[List[Post]] = PostController.getAllPosts("https://jsonplaceholder.typicode.com/posts")
  val allComments : Future[List[Comment]]=CommentController.getAllComments("https://jsonplaceholder.typicode.com/comments")

  val postWithAllComments: Future[List[PostWithAllComments]] = allPosts.map(posts=>
    allComments.map(comments=>
      posts.map(post=>
        PostWithAllComments(post,
          comments.filter(_.postId==post.id))))).flatten

  val userWithAllPosts: Future[List[UserWithAllPosts]] =allUsers.map(users=>
    allPosts.map(allPost =>
      users.map(user=>
        UserWithAllPosts(user,
          allPost.filter(_.userId==user.id))))).flatten


  /**
   *  method getUserWithMaxPosts can be used to find the user with maximum post
   * @return the name of the user with maximum posts
   */
  def getUserWithMaxPosts : Future[String] = {

    userWithAllPosts.map(userWithAllPost =>
      userWithAllPost.sortWith((a,b) =>
        a.posts.length >= b.posts.length).head.user.name) fallbackTo Future{"Data not found"}
  }

  /**
   *  method getUserWithMaxPostComments can be used to find the user with maximum comments on a post
   * @return the name of the user with maximum comments on a post
   */
  def getUserWithMaxPostComments : Future[String] = {

    val postWithMaxComments : Future[Post] =
      postWithAllComments.map(postWithAllComment =>
        postWithAllComment.sortWith((a,b) =>
          a.allComments.length >= b.allComments.length).head.post)

    postWithMaxComments.map(postWithMaxComment =>
      allUsers.map( users =>
        users.filter(_.id==postWithMaxComment.userId).head.name)).flatten fallbackTo Future{"Data not found"}
  }
}

object Ob extends App{
 val ob=new JsonParsing
  val r1 =ob.getUserWithMaxPosts
  val r2 =ob.getUserWithMaxPostComments
  Thread.sleep(5000)
  println(r1)
  println(r2)
}

