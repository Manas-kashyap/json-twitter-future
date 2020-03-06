package com.knoldus.json.controller


import net.liftweb.json.DefaultFormats


/**
 * contains parsing methods of json data.
 */
object DataParser {

  implicit val formats: DefaultFormats.type = DefaultFormats


  /**
   *  method parser is generic in nature and used to parse the data into model class.
   * @param jsonData is passed as string.
   * @param m implicit manifest needed for inbuilt extract method generic.
   * @tparam T type of model class.
   * @return list of type T.
   */
  def parser[T](jsonData: String)(implicit m : Manifest[T]): List[T] = {
    try{
      val parsedJsonData = net.liftweb.json.parse(jsonData)
      parsedJsonData.children map { comment =>
        comment.extract[T]
      }
    }catch {
      case exception: Exception => List.empty[T]
    }
  }
}
