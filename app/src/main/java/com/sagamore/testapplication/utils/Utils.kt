package com.sagamore.testapplication.utils

import android.content.Context
import android.util.Log
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormatterBuilder
import java.text.SimpleDateFormat
import java.util.*


/**
 * @author a.v.davtyan
 */
fun calculateNumOfColumns(context: Context): Int {
    val displayMetrics = context.resources.displayMetrics
    val dpWidth = displayMetrics.widthPixels / displayMetrics.density
    val scalingFactor = 200
    val columnCount = (dpWidth / scalingFactor).toInt()
    return if (columnCount >= 2) columnCount else 2
}

fun String.stringFormatting() : String {

    val source = this.toLowerCase(Locale.ROOT)

    return with(StringBuilder()) {
        append(source[0].toUpperCase())
        append(source.subSequence(1, source.length))
    }.toString()
}

fun convertDate(from: String?, pattern: String) : String {

    val localDateTime =
        try {
            parseDateToMillis(from)
        }
        catch (ex: IllegalArgumentException) {
            Log.e("TestApp", "convertDate: ${ex.printStackTrace()}")
            null
        }
    val sdf = SimpleDateFormat(pattern, Locale.ROOT)
    val calendar = Calendar.getInstance()

    if(localDateTime != null) {
        calendar.timeInMillis = localDateTime
        return sdf.format(calendar.time) + " г."
    }
    return "-"
}

private fun parseDateToMillis(source: String?): Long? {

    val dateParsers = arrayOf(
        DateTimeFormat.forPattern("dd-MM-yyyy").parser,
        DateTimeFormat.forPattern("yyyy-MM-dd").parser,
        DateTimeFormat.forPattern("dd.MM.yyyy").parser
    )
    val formatter: DateTimeFormatter =
        DateTimeFormatterBuilder().append(null, dateParsers).toFormatter()

    return source?.let {
        formatter.parseMillis(it)
    }
}

fun Int.toStringWithPostfix(): String {

    val ageLastNumber: Int = this % 10
    val isExclusion = this % 100 in 11..14

    return if (ageLastNumber == 1) {
        toString() + " год"
    } else if (ageLastNumber == 0 || ageLastNumber in 5..9 || isExclusion) {
        toString() + " лет"
    } else {
        toString() + " года"
    }
}
