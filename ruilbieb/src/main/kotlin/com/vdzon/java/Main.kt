package com.vdzon.java

import com.pi4j.io.gpio.GpioController
import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.Pin
import com.pi4j.io.gpio.PinPullResistance
import com.pi4j.io.gpio.RaspiPin
import java.sql.DriverManager
import java.sql.SQLException
import java.util.Date

object Main {


    @JvmStatic
    fun main(args: Array<String>) {
        MainWeb().start()

        Database.init()


        val gpio: GpioController = GpioFactory.getInstance();
        val b1 = gpio.provisionDigitalInputPin(
            RaspiPin.GPIO_25,
            "Deur",
            PinPullResistance.PULL_DOWN
        )
        var lastState = b1.state.isHigh

        while (true){
            val state = b1.state.isHigh
            Thread.sleep(500)
            if (state!=lastState){
                lastState = state
                if (state ){
                    Database.deurOpen()
                    println("Deur open")
                }
                else{
                    Database.deurDicht()
                    println("Deur dicht")
                }

            }

        }




    }



}
