package com.knoldus.json.model

/**
 * This is a case class used to model post with all comments
 * @param post contains the object of post data model
 * @param allComments contains list of the comments made on the post
 */
case class PostWithAllComments(post: Post, allComments:  List[Comment])
