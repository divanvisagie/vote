package com.dvisagie.vote.users

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post, _}
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import akka.util.Timeout
import com.dvisagie.vote.Protocols
import com.dvisagie.vote.injector.Provider
import com.dvisagie.vote.users.UserControllerActor.{CreateUserRequest, CreationRequestResponse, UserResponse}
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import spray.json.RootJsonFormat

import scala.util.{Failure, Success}
import io.circe.generic.auto._

import scala.concurrent.ExecutionContext

trait UserRoutes extends FailFastCirceSupport {
//  implicit val createUserRequestFormat: RootJsonFormat[CreateUserRequest] = jsonFormat4(CreateUserRequest)
//  implicit val creationRequestResponseFormat: RootJsonFormat[CreationRequestResponse] = jsonFormat2(CreationRequestResponse)
//  implicit val userResponse: RootJsonFormat[UserResponse] = jsonFormat3(UserResponse)
//  implicit val userRegistrationRequest: RootJsonFormat[RegistrationActor.UserRegistrationRequest] = jsonFormat3(RegistrationActor.UserRegistrationRequest)
  implicit val provider: Provider
  implicit val system: ActorSystem
  implicit val timeout: Timeout
  implicit val executionContext: ExecutionContext
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
