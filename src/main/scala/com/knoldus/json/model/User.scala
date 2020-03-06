package com.knoldus.json.model

/**
 * This is a case class used to model personal details of a user
 * @param id contains unique id of the user
 * @param name contains name of the user
 * @param username contains username of the user
 * @param email contains email of  the user
 * @param address contains address of  the user
 * @param phone contains phone number of the user
 * @param website contains website of the user
 * @param company contains company details of the user
 */
case class User(id: String, name: String, username: String, email: String, address: Address, phone: String, website: String, company: Company)
