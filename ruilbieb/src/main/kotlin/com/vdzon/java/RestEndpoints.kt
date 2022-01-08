package com.vdzon.java

import io.javalin.Javalin
import io.javalin.http.Context

class RestEndpoints {

    fun initRestEndpoints(app: Javalin) {
        app["/api/game/load", { ctx: Context? ->  ctx?.result("HALLO!")}]
    }


}
