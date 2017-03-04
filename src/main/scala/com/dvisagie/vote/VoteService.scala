package com.dvisagie.vote

import java.util.UUID

import akka.actor.ActorSystem
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.util.Timeout
import com.dvisagie.vote.repositories.{MyUserRepository, UserRepository}
import spray.json.DefaultJsonProtocol
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.ExecutionContext

trait Protocols extends DefaultJsonProtocol {
  implicit val system: ActorSystem
  implicit val timeout: Timeout
  implicit val executionContext: ExecutionContext
}

object DatabaseModule {
  val server: String = sys.env.getOrElse("PG_INET","localhost:54321")
  val username: String = sys.env.getOrElse("PG_USERNAME","postgres")
  val password: String = sys.env.getOrElse("PG_PASSWORD","postgres")
  val connectionUrl = s"jdbc:postgresql://$server/swissguard?user=$username&password=$password"

  def provideDatabase: Database =
    Database.forURL(connectionUrl, driver = "org.postgresql.Driver")
}


trait VoteService extends UserRoutes with StaticRoutes {
  implicit val database: Database = DatabaseModule.provideDatabase
  implicit val userRepository: UserRepository = new MyUserRepository

  val routes: Route = staticFiles ~ userRoutes
}
