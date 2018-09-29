package com.techmonad.json

import org.json4s.native.JsonMethods.{parse => jParser}
import org.json4s.native.Serialization.{write => jWrite}
import org.json4s.{DefaultFormats, JValue, _}

/**
  * @author Anand String (anand-singh)
  * @since September 29 2018
  */
object JsonHelper {

  implicit val formats: DefaultFormats.type = DefaultFormats

  def write[T <: AnyRef](value: T): String = jWrite(value)

  def parse(value: String): JValue = jParser(value)

}
