package com.techmonad.service

import akka.http.scaladsl.model.{HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.techmonad.domains.{Bank, BankRequest}
import com.techmonad.json.JsonHelper
import com.techmonad.repo.BankCollection
import javax.inject.{Inject, Named}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * @author Anand String (anand-singh)
  * @since September 29 2018
  */
@Named
class Routes @Inject()(bankCollection: BankCollection) {

  import JsonHelper.formats

  val bankRoutes: Route = {

    (path("bank" / Segment) & get) { id =>
      onSuccess(bankCollection.findBankById(id)) {
        case Some(bank) => complete(JsonHelper.write(bank))
        case None => complete(StatusCodes.NotFound)
      }
    } ~
      (path("banks") & get) {
        onSuccess(bankCollection.findAllBanks()) {
          case Nil => complete(StatusCodes.NotFound)
          case banks: List[Bank] => complete(JsonHelper.write(banks))
        }
      } ~
      (path("bank") & post) {
        entity(as[String]) { json =>
          complete {
            val bankRequest = JsonHelper.parse(json).extract[BankRequest]
            bankCollection.createBank(bankRequest).map { result => HttpResponse(entity = "Bank has been saved successfully") }
          }
        }
      } ~
      (path("bank" / Segment) & put) { id =>
        entity(as[String]) { json =>
          complete {
            val bankRequest = JsonHelper.parse(json).extract[BankRequest]
            bankCollection.updateBank(id, bankRequest).map { result => HttpResponse(entity = "Bank has been updated successfully") }
          }
        }
      } ~
      (path("bank" / Segment) & delete) { id =>
        complete {
          bankCollection.deleteBank(id).map { result => HttpResponse(entity = "Bank has been deleted successfully") }
        }
      }
  }

}