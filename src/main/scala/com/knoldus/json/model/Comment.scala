package com.knoldus.json.model

/**
 * This is a Comment case class which is used to capture the details of a Comment
 * @param postId  it is the id of the post on which the comment is made
 * @param id  it is the unique id of the comment
 * @param name  it is the name of the user who has commented
 * @param email it is the email through which the comment is made
 * @param body it contains the content of the comment's body
 */
case class Comment(postId: String, id: String, name: String, email: String, body: String)
