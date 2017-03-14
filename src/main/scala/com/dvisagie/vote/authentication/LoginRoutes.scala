package com.dvisagie.vote.authentication

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives.{complete, path, post, _}
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import com.dvisagie.vote.RouteSupport
import com.dvisagie.vote.injector.Provider

import scala.concurrent.ExecutionContext

trait LoginRoutes extends RouteSupport {

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
