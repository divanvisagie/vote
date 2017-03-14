package com.dvisagie.vote.users

import akka.actor.{ActorRef, Props}
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post, _}
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import com.dvisagie.vote.RouteSupport
import com.dvisagie.vote.users.UserControllerActor.UserResponse
import io.circe.generic.auto._
import scala.util.{Failure, Success}

trait UserRoutes extends RouteSupport {

  def userControllerActor: ActorRef = system.actorOf(Props(new UserControllerActor))

  val userRoutes: Route =
    path ("api" / "user") {
      post {
        entity(as[RegistrationActor.UserRegistrationRequest]) { message =>
          onComplete(system.actorOf(Props(new RegistrationActor)) ? message ) {
            case Success(_) => complete(Created)
            case Failure(ex) => complete((InternalServerError, s"An error occurred: ${ex.getMessage}"))
          }
        }
      }
    } ~ path("api" / "user" / JavaUUID) { id =>
      get {
        onComplete(userControllerActor ? id) {
          case Success(userOption) => userOption match {
            case Some(user: UserResponse) => complete(user)
            case None => complete(NotFound, "The user does not exist")
          }
          case Failure(ex) => complete((InternalServerError, s"An error occurred: ${ex.getMessage}"))
        }
      }
    } ~ path("api" / "user" / Remaining) { username =>
      get {
        onComplete(userControllerActor ? username) {
          case Success(userOption) => userOption match {
            case Some(user: UserResponse) => complete(user)
            case None => complete(NotFound, "The user does not exist")
          }
          case Failure(ex) => complete((InternalServerError, s"An error occurred: ${ex.getMessage}"))
        }
      }
    }
}
