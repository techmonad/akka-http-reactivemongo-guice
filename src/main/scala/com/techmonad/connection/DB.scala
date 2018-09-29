package com.techmonad.connection

import reactivemongo.api.collections.bson.BSONCollection
import reactivemongo.api.{DefaultDB, MongoConnection, MongoDriver}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Contains DB related functions.
  *
  * @author Anand String (anand-singh)
  * @since September 29 2018
  */
class DB(uri: String, dbName: String) {

  // get connection to the database
  val defaultDB: Future[DefaultDB] = createConnection(uri, dbName)

  /**
    * Return a simple collection
    */
  def getCollection(collection: String): Future[BSONCollection] = {
    defaultDB.map(_.collection(collection))
  }

  /**
    * Create the connection
    */
  private def createConnection(uri: String, dbName: String): Future[DefaultDB] = {
    // Connect to the database: Must be done only once per application
    val driver = MongoDriver()
    val parsedUri = MongoConnection.parseURI(uri)
    val connection = parsedUri.map(driver.connection)

    // Gets a reference to the database
    val futureConnection = Future.fromTry(connection)
    futureConnection.flatMap(_.database(dbName))
  }

}
