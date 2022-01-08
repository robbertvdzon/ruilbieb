package com.vdzon.java

import com.pi4j.Pi4J
import com.pi4j.context.Context
import com.pi4j.io.gpio.digital.DigitalInput
import com.pi4j.io.gpio.digital.DigitalState
import com.pi4j.io.gpio.digital.DigitalStateChangeEvent
import com.pi4j.io.gpio.digital.DigitalStateChangeListener
import com.pi4j.io.gpio.digital.PullResistance


object Main {


    @JvmStatic
    fun main(args: Array<String>) {
        MainWeb().start()


         val PIN_BUTTON1 = 8
         val PIN_BUTTON2 = 2
         var pressCount = 0

        val pi4j: Context = Pi4J.newAutoContext()

        val buttonConfig1 = DigitalInput.newConfigBuilder(pi4j)
            .id("button1")
            .name("Press button")
            .address(PIN_BUTTON1)
            .pull(PullResistance.PULL_DOWN)
            .debounce(3000L)
            .provider("pigpio-digital-input")
        val button1 = pi4j.create(buttonConfig1)

        val buttonConfig2 = DigitalInput.newConfigBuilder(pi4j)
            .id("button2")
            .name("Press button")
            .address(PIN_BUTTON2)
            .pull(PullResistance.PULL_DOWN)
            .debounce(3000L)
            .provider("pigpio-digital-input")
        val button2 = pi4j.create(buttonConfig2)

        println("add listener")

        button1.addListener(DigitalStateChangeListener { e: DigitalStateChangeEvent<*> ->
            println("got event 1:" + e)

            if (e.state() == DigitalState.LOW) {
                pressCount++

                println("LOW:" + pressCount)
            }
            if (e.state() == DigitalState.HIGH) {
                pressCount++
                println("HIGH:" + pressCount)
            }
        })
        button2.addListener(DigitalStateChangeListener { e: DigitalStateChangeEvent<*> ->
            println("got event 2:" + e)

            if (e.state() == DigitalState.LOW) {
                pressCount++

                println("LOW:" + pressCount)
            }
            if (e.state() == DigitalState.HIGH) {
                pressCount++
                println("HIGH:" + pressCount)
            }
        })


        createButton(2, pi4j)
        createButton(8, pi4j)
        createButton(28, pi4j)
        createButton(20, pi4j)
        createButton(38, pi4j)
        createButton(3, pi4j)

        while (true) {
            println("sleep")
            Thread.sleep(5000)
        }


        pi4j.shutdown()

    }

    fun createButton(pin: Int, pi4j: Context){

        val buttonConfig2 = DigitalInput.newConfigBuilder(pi4j)
            .id("button"+pin)
            .name("Press button")
            .address(pin)
            .pull(PullResistance.PULL_DOWN)
            .debounce(3000L)
            .provider("pigpio-digital-input")
        val button2 = pi4j.create(buttonConfig2)

        button2.addListener(DigitalStateChangeListener { e: DigitalStateChangeEvent<*> ->
            println("#got event $pin:" + e)
        })

    }

}
