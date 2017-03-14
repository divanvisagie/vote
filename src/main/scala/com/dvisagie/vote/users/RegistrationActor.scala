package com.dvisagie.vote.users

import akka.actor.{Actor, ReceiveTimeout}
import com.dvisagie.vote.injector.Provider

import scala.concurrent.duration._

class RegistrationActor(implicit provider: Provider) extends Actor {
  import RegistrationActor._
  context.setReceiveTimeout(5.seconds)


  def receive: Receive = {
    case request: UserRegistrationRequest =>
      sender() ! Unit
      context stop self
    case ReceiveTimeout =>
      context stop self
  }
}
object RegistrationActor {
  case class UserRegistrationRequest(username: String, email: String, password: String)
}