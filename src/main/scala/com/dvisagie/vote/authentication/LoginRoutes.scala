package com.dvisagie.vote.authentication

import akka.actor.{ActorSystem, Props}
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post, _}
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import akka.util.Timeout
import com.dvisagie.vote.Protocols
import com.dvisagie.vote.injector.Provider
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport

import scala.util.{Failure, Success}
import io.fcomb.akka.http.CirceSupport._

import scala.concurrent.ExecutionContext

trait LoginRoutes extends FailFastCirceSupport {

  implicit val system: ActorSystem
  implicit val timeout: Timeout
  implicit val executionContext: ExecutionContext

  implicit val provider: Provider

  val loginRoutes: Route =
    path ("api" / "login") {
      post {

         complete("ok")
//        entity(as[LoginActor.LoginRequest]) { message =>
//          onComplete(system.actorOf(Props(new LoginActor)) ? message) {
//            case Success(_) => complete("ok")
//            case Failure(ex) => complete((InternalServerError, s"An error occurred: ${ex.getMessage}"))
//          }
//        }
      }
    }
}
