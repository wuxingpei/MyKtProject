package com.example.myapplication.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.net.URL

fun urlToBitmap(url: String): Bitmap {
    val url = URL(url)
    val openConnection = url.openConnection()
    openConnection.doInput = true
    openConnection.connect()
    val inputStream = openConnection.getInputStream()
    val bitmap = BitmapFactory.decodeStream(inputStream)
    inputStream.close()
    return bitmap
}