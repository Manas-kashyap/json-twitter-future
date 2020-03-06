package com.knoldus.json.controller

import org.apache.commons.io.IOUtils
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder

/**
 *contains methods related to reading json data from url.
 */
object ReadJsonData {

  /**
   * method readData reads data from a given url.
   * @param url as string.
   * @return data in string format.
   */
  def readData(url: String): String = {
    val request = new HttpGet(url)
    val client = HttpClientBuilder.create().build()
    val response = client.execute(request)
    IOUtils.toString(response.getEntity.getContent)
  }
}
