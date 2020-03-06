package com.knoldus.json.model

/**
 * This is a case class used to model user with all posts done by him/her.
 * @param user contains the object of the user data model.
 * @param posts contains list of posts done by user.
 */
case class UserWithAllPosts(user: User, posts: List[Post])
