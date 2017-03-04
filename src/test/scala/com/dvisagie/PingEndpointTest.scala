package com.dvisagie

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.Timeout
import com.dvisagie.vote.PingRoutes
import org.scalatest.{FlatSpec, Matchers}

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._

class PingEndpointTest extends FlatSpec with Matchers with ScalatestRouteTest with PingRoutes {
  implicit val timeout: Timeout = Timeout(10.seconds)
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  "Ping get request" should "respond with pong message" in {
    Get("/api/ping") ~> pingRoutes ~> check {
      handled shouldEqual true
      status shouldEqual StatusCodes.OK
    }
  }
}
