package com.techmonad.domains

import reactivemongo.api.bson.Macros.Annotations.Key

/**
  * @author Anand String (anand-singh)
  * @since September 29 2018
  */
case class Bank(@Key("_id") id: String, name: String, location: String)
