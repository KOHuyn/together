package com.kohuyn.together.ui.utils

import android.text.format.DateUtils
import android.util.Log
import android.widget.Toast
import com.kohuyn.together.TogetherApplication
import com.kohuyn.together.data.model.CountDate
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

object DateUtils {
    fun convertDateToTimeStamp(date: String): Long {
        val minDate = "$date 23:59:59"
        val dt = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val r: Date?
        try {
            r = dt.parse(minDate)
            return (r.time / 1000)
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
        return 0
    }

    fun getTimeCurrent(format:String):String{
        val currDate = Calendar.getInstance().time
        val dt = SimpleDateFormat(format, Locale.getDefault())
        return dt.format(currDate)
    }

    fun convertTimeStampToString(time: Long): String {
        val date = Date(time * 1000)
        val currDate = Calendar.getInstance()
        val endWeek = Calendar.getInstance()
        val startWeek = Calendar.getInstance()

        endWeek.add(Calendar.DAY_OF_MONTH, 8 - currDate.get(Calendar.DAY_OF_WEEK))

        val d = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val endWeekString = d.format(endWeek.time)

        val endWeekLong = convertDateToTimeStamp(endWeekString)
        startWeek.time = Date((endWeekLong - 86400 + 1) * 1000)
        startWeek.add(Calendar.DAY_OF_MONTH, -8)

        return when {
            DateUtils.isToday(time * 1000) -> {
                val dt = SimpleDateFormat("hh:mm a", Locale.getDefault())
                dt.format(date)
            }
            time > startWeek.time.time / 1000 -> {
                val dt = SimpleDateFormat("EEE hh:mm a", Locale.getDefault())
                dt.format(date)
            }
            else -> {
                val dt = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                dt.format(date)
            }
        }
    }

    fun countDateStartToNow(dateStart: Long, dateNow: Long): CountDate {
        val countDate = (dateNow - dateStart)/1000
        val yearToMilis = 31556926
        val monthToMilis = 2629743
        val dayToMilis = 86400
        val yearCurr: Int = (countDate / yearToMilis).toInt()
        val monthCurr: Int = ((countDate % yearToMilis) / monthToMilis).toInt()
        val dayCurr: Int = (((countDate % yearToMilis) % monthToMilis) / dayToMilis).toInt()
        val countDay:Int = (countDate/dayToMilis).toInt()
        val date= Date(dateStart)
        val dt = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return CountDate(dayCurr,monthCurr,yearCurr,countDay,dt.format(date).toString())
    }

}