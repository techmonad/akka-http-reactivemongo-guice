package com.techmonad.connection

import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.{AsyncDriver, DB, MongoConnection}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Contains DB related functions.
  *
  * @author Anand String (anand-singh)
  * @since September 29 2018
  */
class Database(uri: String, dbName: String) {

  // get connection to the database
  val defaultDB: Future[DB] = createConnection(uri, dbName)

  /**
    * Return a simple collection
    */
  def getCollection(collection: String): Future[BSONCollection] = {
    defaultDB.map(_.collection(collection))
  }

  /**
    * Create the connection
    */
  private def createConnection(uri: String, dbName: String): Future[DB] = {
    // Connect to the database: Must be done only once per application
    val driver = AsyncDriver()

    val connection = for {
      parsedUri <- MongoConnection.fromString(uri)
      connection <- driver.connect(parsedUri)
    } yield connection

    // Gets a reference to the database
    connection.flatMap(_.database(dbName))
  }

}
