package com.techmonad.conf

/**
  * @author Anand String (anand-singh)
  * @since September 29 2018
  */
object MongoConf extends BaseConf("application") {

  val dbName: String = getString("mongo.db")

  val uri: String = getString("mongo.uri")

  val BANK_COLLECTION: String = getString("mongo.collections.bank")

}
