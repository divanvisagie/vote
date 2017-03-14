package com.dvisagie

import java.util.UUID

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.util.Timeout
import com.dvisagie.vote.users.UserControllerActor.{CreateUserRequest, CreationRequestResponse, UserResponse}
import com.dvisagie.vote.repositories.UserRepository
import com.dvisagie.vote.injector.Provider
import com.dvisagie.vote.users.{RegistrationActor, UserRoutes}
import org.scalatest._
import org.scalatest.mockito.MockitoSugar
import org.mockito.Mockito._

import scala.concurrent.ExecutionContextExecutor
import scala.concurrent.duration._

class MockProvider extends Provider with MockitoSugar {
  import slick.jdbc.PostgresProfile.api.Database

  val userRepository: UserRepository = mock[UserRepository]
  val dolores = Some(UserResponse("dolores","Dolores","Abernathy"))
  when(userRepository.getUserForId(UUID.fromString("00000000-0000-0000-0000-000000000000"))) thenReturn dolores
  when(userRepository.getUserForUsername("dolores")) thenReturn dolores
  when(userRepository.getUserForUsername("jack")) thenReturn None


  override val database: Database = mock[Database]

}


class UserEndpointSpec extends FlatSpec with Matchers with ScalatestRouteTest with UserRoutes {
  implicit val timeout: Timeout = Timeout(10.seconds)
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val provider = new MockProvider()

  val expectedCreationResponse = CreationRequestResponse("message received", "dolores")
  val expectedUserResponse = UserResponse("dolores","Dolores","Abernathy")

  "Create User" should "respond with success message" in {
    val createUserRequest = RegistrationActor.UserRegistrationRequest(
      username = "dolores",
      email = "dolores@westworld.com",
      password = "Dolor35"
    )

    Post("/api/user", createUserRequest) ~> userRoutes ~> check {
      handled shouldEqual true
      status shouldEqual StatusCodes.Created
    }
  }

  "Get User with valid id" should "respond with user object" in {
    val id = UUID.fromString("00000000-0000-0000-0000-000000000000")
    Get(s"/api/user/$id") ~> userRoutes ~> check {
      handled shouldEqual true
      status shouldEqual StatusCodes.OK
      responseAs[UserResponse] shouldBe expectedUserResponse
    }
  }

  "Get User with invalid username" should "respond with user object" in {
    val username = "jack"
    Get(s"/api/user/$username") ~> userRoutes ~> check {
      handled shouldEqual true
      status shouldEqual StatusCodes.NotFound
    }
  }

  "Get User with valid username" should "respond with user object" in {
    val username = "dolores"
    Get(s"/api/user/$username") ~> userRoutes ~> check {
      handled shouldEqual true
      status shouldEqual StatusCodes.OK
    }
  }

}
