package com.vdzon.java

import com.pi4j.io.i2c.I2CBus
import com.pi4j.io.i2c.I2CDevice
import com.pi4j.io.i2c.I2CFactory

object Main {


    @JvmStatic
    fun main(args: Array<String>) {
        println("Starting display")

        var _device: I2CDevice? = null
        var _lcd: I2CLCD? = null

        val bus: I2CBus = I2CFactory.getInstance(I2CBus.BUS_1)
        _device = bus.getDevice(0x38)
        _lcd = I2CLCD(_device)
        _lcd.init()
        _lcd.backlight(true)
        _lcd.display_string_pos("Hello, world!", 1, 2)

    }


}
