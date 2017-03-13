package com.dvisagie.vote

import akka.actor.{ActorRef, Props}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post, _}
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import com.dvisagie.vote.repositories.UserRepository
import com.dvisagie.vote.users.UserControllerActor
import com.dvisagie.vote.users.UserControllerActor.{CreateUserRequest, CreationRequestResponse, UserResponse}
import spray.json.RootJsonFormat

import scala.util.{Failure, Success}


trait UserRoutes extends Protocols {
  implicit val createUserRequestFormat: RootJsonFormat[CreateUserRequest] = jsonFormat4(CreateUserRequest)
  implicit val creationRequestResponseFormat: RootJsonFormat[CreationRequestResponse] = jsonFormat2(CreationRequestResponse)
  implicit val userResponse: RootJsonFormat[UserResponse] = jsonFormat3(UserResponse)

  implicit val userRepository: UserRepository

  def userControllerActor: ActorRef = system.actorOf(Props(new UserControllerActor))

  val userRoutes: Route =
    path ("api" / "user") {
      post {
        entity(as[CreateUserRequest]) { createUserRequest =>
          onComplete(userControllerActor ? createUserRequest) {
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
