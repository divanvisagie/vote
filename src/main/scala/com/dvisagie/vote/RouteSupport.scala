package com.dvisagie.vote

import akka.actor.ActorSystem
import akka.util.Timeout
import com.dvisagie.vote.injector.Provider
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport

import scala.concurrent.ExecutionContext

trait RouteSupport extends FailFastCirceSupport {

  implicit val provider: Provider
  implicit val system: ActorSystem
  implicit val timeout: Timeout
  implicit val executionContext: ExecutionContext
}
