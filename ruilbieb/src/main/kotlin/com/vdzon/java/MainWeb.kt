package com.vdzon.java

import io.javalin.Javalin
import io.javalin.core.JavalinConfig
import io.javalin.plugin.rendering.vue.VueComponent
import org.slf4j.LoggerFactory

class MainWeb {
    private val log = LoggerFactory.getLogger(MainWeb::class.java)

    fun start() {
        log.info("Starting backend..")
        val app = Javalin.create { config: JavalinConfig ->
            config.enableWebjars()
            config.addStaticFiles("/html")
        }
        app.get("/", VueComponent("<play></play>"))
        app.get("/demo", VueComponent("<demo></demo>"))
        app.get("/play", VueComponent("<play></play>"))
        app.get("/manual", VueComponent("<manual></manual>"))
        app.get("/rebuild", VueComponent("<rebuild></rebuild>"))
        RestEndpoints().initRestEndpoints(app)
        log.info("Starting server")
        app.start(8080)
    }


}

