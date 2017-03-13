package com.dvisagie.vote.injector

import com.dvisagie.vote.repositories.UserRepository
import slick.jdbc.PostgresProfile.api._

trait Provider {
  def userRepository: UserRepository
  val database: Database
}
