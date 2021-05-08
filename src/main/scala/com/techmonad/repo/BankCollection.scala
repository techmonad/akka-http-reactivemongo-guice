package com.techmonad.repo

import com.techmonad.conf.MongoConf
import com.techmonad.connection.Connection
import com.techmonad.domains.{Bank, BankRequest}
import reactivemongo.api.bson.collection.BSONCollection
import reactivemongo.api.bson.{BSONDocumentReader, BSONDocumentWriter, BSONObjectID, Macros}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * @author Anand String (anand-singh)
  * @since September 29 2018
  */
trait BankCollection extends MongoCollection {

  def findBankById(id: String): Future[Option[Bank]]

  def findAllBanks(): Future[List[Bank]]

  def createBank(bankRequest: BankRequest): Future[Option[Bank]]

  def updateBank(id: String, bankRequest: BankRequest): Future[Int]

  def deleteBank(id: String): Future[Int]

}

class BankCollectionImpl extends BankCollection {

  override val collection: Future[BSONCollection] = Connection.db.getCollection(MongoConf.BANK_COLLECTION)

  implicit def userWriter: BSONDocumentWriter[Bank] = Macros.writer[Bank]

  implicit def userReader: BSONDocumentReader[Bank] = Macros.reader[Bank]

  override def findBankById(id: String): Future[Option[Bank]] = {
    get[Bank](Map("_id" -> id))
  }

  override def findAllBanks(): Future[List[Bank]] = {
    getAll[Bank](Map.empty[String, String])
  }

  override def createBank(bankRequest: BankRequest): Future[Option[Bank]] = {
    val id = BSONObjectID.generate.stringify
    val bank = Bank(id, bankRequest.name, bankRequest.location)
    insert[Bank](bank) map {
      case true => Some(bank)
      case false => None
    }
  }

  override def updateBank(id: String, bankRequest: BankRequest): Future[Int] = {
    val bank = Bank(id, bankRequest.name, bankRequest.location)
    update[Bank](Map("_id" -> id), bank)
  }

  override def deleteBank(id: String): Future[Int] = {
    delete(Map("_id" -> id))
  }

}
