package com.techmonad.connection

import com.techmonad.conf.MongoConf

/**
  * @author Anand String (anand-singh)
  * @since September 29 2018
  */
object Connection {

  implicit lazy val db = new Database(MongoConf.uri, MongoConf.dbName)

}
