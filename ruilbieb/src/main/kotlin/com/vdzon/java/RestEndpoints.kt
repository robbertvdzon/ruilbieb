package com.vdzon.java

import io.javalin.Javalin
import io.javalin.http.Context

class RestEndpoints {

    fun initRestEndpoints(app: Javalin) {
        app["/api/game/load", { ctx: Context? ->  ctx?.json(Database.getAll())}]

        app["/api/game/summary", { ctx: Context? ->  ctx?.json(Database.getSummary())}]

    }



}
