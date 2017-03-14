package com.dvisagie


import java.util.UUID

import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.Timeout
import com.dvisagie.vote.authentication.{LoginActor, LoginRoutes}
import com.dvisagie.vote.users.UserControllerActor.{CreateUserRequest, CreationRequestResponse, UserResponse}
import com.dvisagie.vote.repositories.UserRepository
import com.dvisagie.vote.injector.Provider
import com.dvisagie.vote.users.{RegistrationActor, UserRoutes}
import org.scalatest._
import org.scalatest.mockito.MockitoSugar

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._
import io.circe.generic.auto._

class LoginEndpointSpec extends FlatSpec with Matchers with ScalatestRouteTest with LoginRoutes {
  class MockProvider extends Provider with MockitoSugar {
    import slick.jdbc.PostgresProfile.api.Database

    val userRepository: UserRepository = mock[UserRepository]

    val database: Database = mock[Database]
  }


  implicit val timeout: Timeout = Timeout(10.seconds)
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val provider = new MockProvider

  "Login with valid user" should "respond with token" in {
    val loginRequest = LoginActor.LoginRequest("dolores","D0lor35")

    Post("/api/login", loginRequest) ~> loginRoutes ~> check {
      handled shouldEqual true
    }
  }
}
