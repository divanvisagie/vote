package com.dvisagie.vote.repositories

import java.util.UUID

import com.dvisagie.vote.actors.UserControllerActor.UserResponse
import slick.jdbc.PostgresProfile.api.Database
import slick.jdbc.PostgresProfile.api._

trait UserRepository {
  def getUserForId(id: UUID): Option[UserResponse]
  def getUserForUsername(username: String): Option[UserResponse]
}

case class User(
                 id: UUID,
                 username: String,
                 password: String,
                 email: String
               ) {
}

class MyUserRepository(implicit database: Database) extends UserRepository {

  private class UserTable(tag: Tag) extends Table[User](tag, "users"){
    def id = column[UUID]("id", O.PrimaryKey, O.Unique)
    def username = column[String]("username")
    def password = column[String]("password")
    def email = column[String]("email")
    def * = (id, username, password, email) <> ((User.apply _).tupled, User.unapply)
  }

  val fakeResponse = UserResponse("someone else","Dolores","Abernathy")

  def getUserForId(id: UUID): Option[UserResponse] =
    Some(fakeResponse)


  def getUserForUsername(username: String): Option[UserResponse] =
    Some(fakeResponse)

}