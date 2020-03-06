package com.knoldus.json.model

/**
 * This is a Post case class which is used to capture the details of a Post
 * @param userId it is the id of the user who has created the post
 * @param id it is the unique id of the post
 * @param title it is the title of the post
 * @param body it is the body of the post
 */
case class Post(userId: String, id: String, title: String, body: String)
