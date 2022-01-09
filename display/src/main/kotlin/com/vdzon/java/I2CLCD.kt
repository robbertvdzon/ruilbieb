package com.vdzon.java

import com.pi4j.io.i2c.I2CDevice


/**
 *
 * @author user
 */
class I2CLCD(private val _device: I2CDevice) {
    // Write a single command
    private fun write_cmd(cmd: Byte) {
        try {
            _device.write(cmd)
            Thread.sleep(0, 100000)
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    // Write a command and argument
    private fun write_cmd_arg(cmd: Byte, data: ByteArray) {
        try {
            _device.write(cmd.toInt(), data)
            Thread.sleep(0, 100000)
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    // Write a block of data
    private fun write_block_data(cmd: Byte, data: ByteArray) {
        try {
            _device.write(cmd.toInt(), data)
            Thread.sleep(0, 100000)
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    // Read a single byte def
    private fun read(): Byte {
        try {
            return _device.read().toByte()
        } catch (ex: Exception) {
            println(ex.message)
        }
        return 0.toByte()
    }

    // Read
    private fun read_data(cmd: Byte): ByteArray {
        val buffer = ByteArray(cmd.toInt())
        try {
            _device.read(buffer, 0, cmd.toInt())
        } catch (ex: Exception) {
            println(ex.message)
        }
        return buffer
    }

    // Read a block of data
    private fun read_block_data(cmd: Byte): ByteArray {
        val buffer = ByteArray(cmd.toInt())
        try {
            _device.read(buffer, 0, cmd.toInt())
        } catch (ex: Exception) {
            println(ex.message)
        }
        return buffer
    }

    private val LCD_CLEARDISPLAY = 0x01.toByte()
    private val LCD_RETURNHOME = 0x02.toByte()
    private val LCD_ENTRYMODESET = 0x04.toByte()
    private val LCD_DISPLAYCONTROL = 0x08.toByte()
    private val LCD_CURSORSHIFT = 0x10.toByte()
    private val LCD_FUNCTIONSET = 0x20.toByte()
    private val LCD_SETCGRAMADDR = 0x40.toByte()
    private val LCD_SETDDRAMADDR = 0x80.toByte()

    // flags for display entry mode
    private val LCD_ENTRYRIGHT = 0x00.toByte()
    private val LCD_ENTRYLEFT = 0x02.toByte()
    private val LCD_ENTRYSHIFTINCREMENT = 0x01.toByte()
    private val LCD_ENTRYSHIFTDECREMENT = 0x00.toByte()

    // flags for display on/off control
    private val LCD_DISPLAYON = 0x04.toByte()
    private val LCD_DISPLAYOFF = 0x00.toByte()
    private val LCD_CURSORON = 0x02.toByte()
    private val LCD_CURSOROFF = 0x00.toByte()
    private val LCD_BLINKON = 0x01.toByte()
    private val LCD_BLINKOFF = 0x00.toByte()

    // flags for display/cursor shift
    private val LCD_DISPLAYMOVE = 0x08.toByte()
    private val LCD_CURSORMOVE = 0x00.toByte()
    private val LCD_MOVERIGHT = 0x04.toByte()
    private val LCD_MOVELEFT = 0x00.toByte()

    // flags for function set
    private val LCD_8BITMODE = 0x10.toByte()
    private val LCD_4BITMODE = 0x00.toByte()
    private val LCD_2LINE = 0x08.toByte()
    private val LCD_1LINE = 0x00.toByte()
    private val LCD_5x10DOTS = 0x04.toByte()
    private val LCD_5x8DOTS = 0x00.toByte()

    // flags for backlight control
    private val LCD_BACKLIGHT = 0x08.toByte()
    private val LCD_NOBACKLIGHT = 0x00.toByte()
    private val En = 4.toByte() // Enable bit
    private val Rw = 2.toByte() // Read/Write bit
    private val Rs = 1.toByte() // Register select bit

    //initializes objects and lcd
    fun init() {
        try {
            lcd_write(0x03.toByte())
            lcd_write(0x03.toByte())
            lcd_write(0x03.toByte())
            lcd_write(0x02.toByte())
            lcd_write((LCD_FUNCTIONSET.toInt() or LCD_2LINE.toInt() or LCD_5x8DOTS.toInt() or LCD_4BITMODE.toInt()).toByte())
            lcd_write((LCD_DISPLAYCONTROL.toInt() or LCD_DISPLAYON.toInt()).toByte())
            lcd_write(LCD_CLEARDISPLAY)
            lcd_write((LCD_ENTRYMODESET.toInt() or LCD_ENTRYLEFT.toInt()).toByte())
            Thread.sleep(0, 200000)
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    // clocks EN to latch command
    private fun lcd_strobe(data: Byte) {
        try {
            _device.write((data.toInt() or En.toInt() or LCD_BACKLIGHT.toInt()).toByte())
            Thread.sleep(0, 500000)
            _device.write((data.toInt() and En.toInt().inv() or LCD_BACKLIGHT.toInt()).toByte())
            Thread.sleep(0, 100000)
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    private fun lcd_write_four_bits(data: Byte) {
        try {
            _device.write((data.toInt() or LCD_BACKLIGHT.toInt()).toByte())
            lcd_strobe(data)
        } catch (ex: Exception) {
            println(ex.message)
        }
    }

    // write a command to lcd
    private fun lcd_write(cmd: Byte, mode: Byte = 0.toByte()) {
        lcd_write_four_bits((mode.toInt() or (cmd.toInt() and 0xF0)).toByte())
        lcd_write_four_bits((mode.toInt() or (cmd.toInt() shl 4 and 0xF0)).toByte())
    }

    // write a character to lcd
    fun write_char(charvalue: Byte) {
        val mode: Byte = 1
        lcd_write_four_bits((mode.toInt() or (charvalue.toInt() and 0xF0)).toByte())
        lcd_write_four_bits((mode.toInt() or (charvalue.toInt() shl 4 and 0xF0)).toByte())
    }

    // put string function
    fun display_string(string: String, line: Int) {
        when (line) {
            1 -> lcd_write(0x80.toByte())
            2 -> lcd_write(0xC0.toByte())
            3 -> lcd_write(0x94.toByte())
            4 -> lcd_write(0xD4.toByte())
        }
        for (i in 0 until string.length) {
            lcd_write(string[i].code.toByte(), Rs)
        }
    }

    // clear lcd and set to home
    private fun clear() {
        lcd_write(LCD_CLEARDISPLAY)
        lcd_write(LCD_RETURNHOME)
    }

    // define backlight on / off(lcd.backlight(1) off = lcd.backlight(0)
    fun backlight(state: Boolean) {
        //for state, 1 = on, 0 = off
        if (state) {
            write_cmd(LCD_BACKLIGHT)
        } else {
            write_cmd(LCD_NOBACKLIGHT)
        }
    }

    // add custom characters(0 - 7)
    private fun load_custom_chars(fontdata: Array<ByteArray>) {
        lcd_write(0x40.toByte())
        for (i in fontdata.indices) {
            for (j in fontdata[i].indices) {
                write_char(fontdata[i][j])
            }
        }
    }

    // define precise positioning (addition from the forum)
    fun display_string_pos(string: String, line: Int, pos: Int) {
        var pos_new: Byte = 0
        if (line == 1) {
            pos_new = pos.toByte()
        } else if (line == 2) {
            pos_new = (0x40 + pos).toByte()
        } else if (line == 3) {
            pos_new = (0x14 + pos).toByte()
        } else if (line == 4) {
            pos_new = (0x54 + pos).toByte()
        }
        lcd_write((0x80 + pos_new).toByte())
        for (i in 0 until string.length) {
            lcd_write(string[i].code.toByte(), Rs)
        }
    }
}