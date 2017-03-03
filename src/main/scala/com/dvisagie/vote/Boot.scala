package com.dvisagie.vote

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._

object Boot extends VoteService {

  implicit val system = ActorSystem("user-system")
  implicit val timeout: Timeout = Timeout(10.seconds)
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher
  implicit val materializer = ActorMaterializer()

  def main(args: Array[String]) {
    val port = 8080
    Http().bindAndHandle(routes, "localhost", port)
    println(s"Server online at http://localhost:$port/")
  }
}
