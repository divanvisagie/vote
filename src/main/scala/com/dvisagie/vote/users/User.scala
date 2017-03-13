package com.dvisagie.vote.users

import java.time.OffsetDateTime
import java.util.UUID

case class User(
                 id: UUID,
                 username: String,
                 email: String ,
                 password: String,
                 salt: String,
                 createdOn: OffsetDateTime
               ) {
}
