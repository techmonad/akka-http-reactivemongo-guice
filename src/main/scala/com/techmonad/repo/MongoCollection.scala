package com.techmonad.repo

import com.techmonad.utils.Logger
import reactivemongo.api.Cursor
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.bson.{BSONBoolean, BSONDocument, BSONDocumentReader, BSONDocumentWriter, BSONDouble, BSONInteger, BSONLong, BSONNull, BSONString, BSONValue}
import reactivemongo.api.commands.WriteResult

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success}

/**
  * @author Anand String (anand-singh)
  * @since September 29 2018
  */
trait MongoCollection extends Logger {

  val collection: Future[BSONCollection]

  /**
    * Converts values of Map to BSONValue
    *
    * @param p as Tuple of [String, Any]
    * @return Tuple of [String, BSONValue]
    */
  private def mapToDocument(p: (String, Any)): (String, BSONValue) = (p._1, p._2 match {
    case value: String => BSONString(value)
    case value: Double => BSONDouble(value)
    case value: Int => BSONInteger(value)
    case value: Boolean => BSONBoolean(value)
    case value: Long => BSONLong(value)
    case other => BSONNull
  })

  /**
    * Gets the specified entity type from the specified collection using the filter elements
    *
    * @param filter The Filter Criteria
    * @return Future[M] The Specified Entity
    */
  def get[M](filter: Map[String, Any])(implicit reader: BSONDocumentReader[M]): Future[Option[M]] = {
    info(s"GET ${filter.toString()}")
    collection.flatMap(_.find(BSONDocument(filter.map(mapToDocument))).one[M])
  }

  /**
    * Gets the specified entity type list from the specified collection using the filter elements
    *
    * @param filter The Filter Criteria
    * @return Future[M] The Specified Entity
    */
  def getAll[M](filter: Map[String, Any])(implicit reader: BSONDocumentReader[M]): Future[List[M]] = {
    info(s"GET ALL ${filter.toString()}")
    collection.flatMap(_.find(BSONDocument(filter.map(mapToDocument))).cursor[M]().
      collect[List](-1, Cursor.FailOnError[List[M]]()))
  }

  /**
    * Generic insert function to store document into database
    *
    * @param document The BSON Document
    * @param writer   The BSON Document Writer
    * @tparam M The BSON Document Type
    * @return The Future of status
    */
  def insert[M](document: M)(implicit writer: BSONDocumentWriter[M]): Future[Boolean] = {
    collection.flatMap(_.insert.one(document).map(_.n > 0))
  }

  /**
    * Generic update function to update document into database for the given criteria
    *
    * @param criteria The Filter Criteria
    * @param document The BSON Document
    * @param writer   The BSON Document Writer
    * @tparam M The BSON Document Type
    * @return The Future of update count
    */
  def update[M](criteria: Map[String, Any], document: M)(implicit writer: BSONDocumentWriter[M]): Future[Int] = {
    val selector = BSONDocument(criteria.map(mapToDocument))
    collection.flatMap(_.update.one(selector, document).map(_.n))
  }

  def delete(criteria: Map[String, Any]): Future[Int] = {
    val selector = BSONDocument(criteria.map(mapToDocument))
    collection.flatMap(_.delete.one(selector).map(_.n))
  }

}
