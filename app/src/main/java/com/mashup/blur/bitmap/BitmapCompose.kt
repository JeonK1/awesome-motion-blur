package com.mashup.blur.bitmap

import android.graphics.Bitmap
import com.mashup.blur.common.compose

fun composeLeftTopBitmap(
    baseBitmap: Bitmap,
    composeBitmap: Bitmap,
    cropRemainArea: Boolean = true  // false : 중첩되어 더해지지 않는 영역 유지
): Bitmap {
    val newBitmap = if (cropRemainArea.not()) {
        baseBitmap.copy(baseBitmap.config, true)
    } else {
        Bitmap.createBitmap(composeBitmap.width, composeBitmap.height, composeBitmap.config)
    }

    for (i in 0 until composeBitmap.width) {
        for (j in 0 until composeBitmap.height) {
            newBitmap.setPixel(
                i,
                j,
                baseBitmap.getPixel(i, j) compose composeBitmap.getPixel(i, j)
            )
        }
    }
    return newBitmap
}

fun composeRightBottomBitmap(
    baseBitmap: Bitmap,
    composeBitmap: Bitmap,
    cropRemainArea: Boolean = true  // false : 중첩되어 더해지지 않는 영역 유지
): Bitmap {
    val newBitmap = if (cropRemainArea.not()) {
        baseBitmap.copy(baseBitmap.config, true)
    } else {
        Bitmap.createBitmap(composeBitmap.width, composeBitmap.height, composeBitmap.config)
    }

    val diffWidth = baseBitmap.width - composeBitmap.width
    val diffHeight = baseBitmap.height - composeBitmap.height
    for (i in 0 until composeBitmap.width) {
        for (j in 0 until composeBitmap.height) {
            newBitmap.setPixel(
                i,
                j,
                baseBitmap.getPixel(i + diffWidth, j + diffHeight) compose composeBitmap.getPixel(i, j)
            )
        }
    }
    return newBitmap
}