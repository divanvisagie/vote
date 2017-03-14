package com.dvisagie.vote.authentication

import akka.http.scaladsl.server.Directives.{complete, path, post, _}
import akka.http.scaladsl.server.Route
import com.dvisagie.vote.RouteSupport

trait LoginRoutes extends RouteSupport {

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
