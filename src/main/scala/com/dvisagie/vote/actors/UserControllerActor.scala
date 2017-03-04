package com.dvisagie.vote.actors

import java.util.UUID

import akka.actor.{Actor, ReceiveTimeout}
import com.dvisagie.vote.repositories.UserRepository

import scala.concurrent.duration._

class UserControllerActor(implicit userRepository: UserRepository) extends Actor {
  import UserControllerActor._
  context.setReceiveTimeout(5.seconds)

  def receive: Receive = {
    case createUserRequest: CreateUserRequest =>
      sender() ! CreationRequestResponse("message received", createUserRequest.username)
      context stop self
    case id: UUID =>
      sender() ! userRepository.getUserForId(id)
      context stop self
    case username: String =>
      sender() ! userRepository.getUserForUsername(username)
      context stop self
    case ReceiveTimeout =>
      context stop self
  }
}

object UserControllerActor {

  final case class CreateUserRequest(
    username: String,
    firstNames: String,
    lastName: String,
    email: String)

  final case class CreationRequestResponse(
    message: String,
    username: String)

  final case class UserResponse(
    username: String,
    firstNames: String,
    lastNames: String)
}