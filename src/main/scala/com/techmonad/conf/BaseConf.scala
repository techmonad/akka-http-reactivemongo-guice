package com.techmonad.conf

import java.util

import com.typesafe.config.{Config, ConfigFactory}


/**
  * A class to handle config functionality
  *
  * @author Anand String (anand-singh)
  * @since September 29 2018
  */
class BaseConf(conf: String) {

  lazy val config: Config = ConfigFactory.load(conf)

  /**
    * getString : It returns value to corresponding key as a String.
    *
    * @param key The Config Key
    * @return The Config Value as String
    */
  def getString(key: String): String = config.getString(key)

  /**
    * getInt : It returns value to corresponding key as a Int.
    *
    * @param key The Config Key
    * @return The Config Value as Int
    */
  def getInt(key: String): Int = config.getInt(key)

  /**
    * getStringList : It returns list of values to corresponding to key.
    *
    * @param key
    * @return The Config Value as List[String]
    */
  def getStringList(key: String): util.List[String] = config.getStringList(key)

}
