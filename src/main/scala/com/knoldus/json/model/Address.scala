package com.knoldus.json.model

/**
 * This is a case class used to model Address of a user
 * @param street contains street details
 * @param suite contains suite details
 * @param city contains city details
 * @param zipcode contains zipcode details
 * @param geo contains geographical location details
 */
case class Address(street: String, suite: String, city: String, zipcode: String, geo: Geo)
