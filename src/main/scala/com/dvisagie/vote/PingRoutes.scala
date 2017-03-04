package com.dvisagie.vote

import akka.http.scaladsl.model.{HttpEntity, _}
import akka.http.scaladsl.server.Directives.{complete, path, _}
import akka.http.scaladsl.server.Route

trait PingRoutes {
  val pingRoutes: Route =
    path("api" / "ping") {
      get {
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "pong"))
      }
    }
}
