package com.knoldus
object AppDriver extends App {
 val ob=new JsonParsingUser
  val obj=new JsonParsingPosts
  val obje=new JsonParsingComments
  println(ob.getAllUsers)
  println(obj.getAllPosts)
  println(obje.getAllComments)
}
