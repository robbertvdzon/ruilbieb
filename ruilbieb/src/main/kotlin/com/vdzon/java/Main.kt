package com.vdzon.java

import com.pi4j.io.gpio.GpioController
import com.pi4j.io.gpio.GpioFactory
import com.pi4j.io.gpio.GpioPinDigitalInput
import com.pi4j.io.gpio.Pin
import com.pi4j.io.gpio.PinPullResistance
import com.pi4j.io.gpio.RaspiPin


object Main {

    val gpio: GpioController = GpioFactory.getInstance();

    @JvmStatic
    fun main(args: Array<String>) {
        MainWeb().start()

        val b1 = button(RaspiPin.GPIO_02)
        val b2 = button(RaspiPin.GPIO_08)
        val b3 = button(RaspiPin.GPIO_28)
        val b4 = button(RaspiPin.GPIO_20)
        val b5 = button(RaspiPin.GPIO_03)
        while (true){
            println("${b1.state} ${b2.state} ${b3.state} ${b4.state} ${b5.state}")
            Thread.sleep(1000)
        }





    }

    fun button(pin: Pin) = gpio.provisionDigitalInputPin(
            pin,  // PIN NUMBER
            "MyButton_"+pin.name  // PIN FRIENDLY NAME (optional)
//            PinPullResistance.PULL_DOWN
        )



}
