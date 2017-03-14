package com.dvisagie.vote.authentication

import akka.actor.{Actor, ReceiveTimeout}
import com.dvisagie.vote.injector.Provider

import scala.concurrent.duration._

class LoginActor(implicit provider: Provider) extends Actor {
  import LoginActor._
  context.setReceiveTimeout(5.seconds)

  def receive: Receive = {
    case request: LoginRequest =>
      sender() ! LoginResponse
      context stop self
    case ReceiveTimeout =>
      context stop self
  }

}
object LoginActor {
    case class LoginRequest(username: String, password: String)
    case class LoginResponse(token: String)
}
