package com.example.runningtracker.database

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class Converters {

    @TypeConverter
    fun fromBitmapToByte(bmp: Bitmap): ByteArray{
        var outputStream = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun fromByteToBitmap(byte: ByteArray): Bitmap{
        return BitmapFactory.decodeByteArray(byte, 0, byte.size)
    }
}