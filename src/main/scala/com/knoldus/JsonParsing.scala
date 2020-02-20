package com.knoldus

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

case class PostWithAllComments(post: Post, allComments:  List[Comment])

case class UserWithPostAndComments(user: User, postWithAllComments: List[PostWithAllComments])

class JsonParsing {

  val allUsers: Future[List[User]] = JsonParsingUsers.getAllUsers
  val allPosts: Future[List[Post]] = JsonParsingPosts.getAllPosts
  val allComments : Future[List[Comment]]=JsonParsingComments.getAllComments

  val postWithAllComments: Future[List[PostWithAllComments]] = allPosts.map(posts=>
    allComments.map(comments=>
      posts.map(post=>
        PostWithAllComments(post,
          comments.filter(_.postId==post.id))))).flatten


  val userWithPostAndComments: Future[List[UserWithPostAndComments]] =allUsers.map(users=>
    postWithAllComments.map(postWithAllComment=>
      users.map(user=>
        UserWithPostAndComments(user,
          postWithAllComment.filter(_.post.userId==user.id))))).flatten

  val postWithMaxComments : Future[Post] = postWithAllComments.map(postWithAllComment =>
    postWithAllComment.sortWith((a,b) =>
      a.allComments.length >= b.allComments.length).head.post)


  val userIdWithMaxPosts : Future[String]  = postWithAllComments.map(postWithAllComment =>
    postWithAllComment.sortWith((a,b) =>
      a.allComments.length >= b.allComments.length).head.post.userId)

  def getUserWithMaxPosts : Future[String] = {
    userIdWithMaxPosts.map(userIdWithMaxPost => allUsers.map( users => users.filter(_.id==userIdWithMaxPost).head.name)).flatten
  }

  def getUserWithMaxPostComments : Future[String] = {
    postWithMaxComments.map(postWithMaxComment => allUsers.map( users => users.filter(_.id==postWithMaxComment.userId).head.name)).flatten
  }
}
