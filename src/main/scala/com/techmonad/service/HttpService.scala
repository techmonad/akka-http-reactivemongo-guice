package com.techmonad.service

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.google.inject.{AbstractModule, Guice}
import com.techmonad.domains.BankRequest
import com.techmonad.repo.{BankCollection, BankCollectionImpl}

import scala.concurrent.ExecutionContextExecutor

/**
  * @author Anand String (anand-singh)
  * @since September 29 2018
  */
object HttpService extends App {

  private val injector = Guice.createInjector(new AbstractModule() {
    override def configure() {
      bind(classOf[BankCollection]).to(classOf[BankCollectionImpl])
    }
  })

  private val bankService = injector.getInstance(classOf[Routes])

  implicit val system: ActorSystem = ActorSystem()

  implicit val materializer: ActorMaterializer = ActorMaterializer()

  implicit val dispatcher: ExecutionContextExecutor = system.dispatcher

  private val repositoryImpl: BankCollection = new BankCollectionImpl

  repositoryImpl.createBank(BankRequest("SBI", "New Delhi"))
  repositoryImpl.createBank(BankRequest("PNB", "New Delhi"))
  repositoryImpl.createBank(BankRequest("RBS", "New Delhi"))

  Http().bindAndHandle(bankService.bankRoutes, "localhost", 9000)

}
