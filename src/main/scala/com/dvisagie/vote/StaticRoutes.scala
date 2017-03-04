package com.dvisagie.vote

import akka.actor.{ActorRef, Props}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.server.Directives.{as, complete, entity, path, post, _}
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import com.dvisagie.vote.actors.UserControllerActor
import com.dvisagie.vote.actors.UserControllerActor.{CreateUserRequest, CreationRequestResponse, UserResponse}
import com.dvisagie.vote.repositories.UserRepository
import com.dvisagie.vote.actors.UserControllerActor
import com.dvisagie.vote.actors.UserControllerActor.{CreateUserRequest, CreationRequestResponse, UserResponse}
import spray.json.RootJsonFormat


trait StaticRoutes {
  val staticFiles: Route  = pathPrefix("") {
      getFromDirectory("website/build")
  }
}
