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
        Database.deurOpen()
        Thread.sleep(10)
        Database.deurDicht()
        Thread.sleep(15)
        Database.deurOpen()
        Thread.sleep(17)
        Database.deurDicht()
        val res =  Database.getAll()
        res.forEach{
            val time = Date(it.timestamp)
            println("$time ${it.action}")
        }


        val gpio: GpioController = GpioFactory.getInstance();
        val b1 = gpio.provisionDigitalInputPin(
            RaspiPin.GPIO_25,  // PIN NUMBER
            "MyButton",  // PIN FRIENDLY NAME (optional)
            PinPullResistance.PULL_DOWN
        )
        var lastState = b1.state.isHigh

        while (true){
            val state = b1.state.isHigh
            println("${state}")
            Thread.sleep(1000)
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
