package qsos.lib.base.utils

import android.annotation.SuppressLint
import android.nfc.FormatException
import java.text.ParseException
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author : 华清松
 * 时间格式转换 工具类
 */
@SuppressLint("SimpleDateFormat")
object DateUtils {
    const val YYYYMMDD = "yyyy-MM-dd"

    fun setTime(time: Long?): String {
        val dateFormat = SimpleDateFormat("yyyy年MM月dd日 HH时mm分", Locale.CHINA)
        return try {
            dateFormat.format(Date(time!!))
        } catch (e: Exception) {
            "未知时间"
        }
    }

    fun setTimeYYYYMMDD(time: Long): String {
        return setTimeWithFormat(time, YYYYMMDD)
    }

    fun setTimeWithFormat(time: Long, pattern: String): String {
        val format = try {
            SimpleDateFormat(pattern, Locale.CHINA)
        } catch (e: FormatException) {
            SimpleDateFormat(YYYYMMDD, Locale.CHINA)
        }

        return format.format(Date(time))
    }

    fun setTimeFormat(time: Date?): String {
        if (time == null) {
            return "今天 12:00"
        }
        val format = SimpleDateFormat(YYYYMMDD, Locale.CHINA)

        return format.format(time)
    }

    // 毫秒转化为天时
    fun formMMDDByMillisecond(time: Long): String {
        val format = SimpleDateFormat("MM-dd", Locale.CHINA)
        return format.format(time * 1000)
    }

    // 毫秒转化为天时
    fun formYYYYMMDDByMillisecond(time: Long): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
        return format.format(time * 1000)
    }

    // 毫秒转化为天时
    fun formYYYYMMDDByMillisecond(time: Date): String {
        val format = SimpleDateFormat("HH:mm:ss", Locale.CHINA)
        return format.format(time)
    }

    // 毫秒转化为天时
    fun form(time: Date, form: String): String {
        val format = SimpleDateFormat(form, Locale.CHINA)
        return format.format(time)
    }

    // 毫秒转化为时分秒
    fun formHHMMSSByMillisecond(time: Long): String {
        val format = SimpleDateFormat("HH:mm:ss", Locale.CHINA)
        return format.format(time)
    }

    fun getTimeDifference(publish_date: Date): String {
        val nowDate = Date()
        var diff = nowDate.time - publish_date.time
        diff /= 1000
        if (diff < 1) {
            return "刚刚"
        }
        diff /= 60
        if (diff < 5) {
            return "刚刚"
        }
        if (diff in 6..59) {
            return diff.toString() + "分钟前"
        }
        diff /= 60
        if (diff <= 24) {
            return diff.toString() + "小时前"
        }
        diff /= 24
        if (diff < 30) {
            return if (diff == 1L) {
                "昨天"
            } else diff.toString() + "天前"
        }
        val dfDate = SimpleDateFormat("MM-dd")
        return dfDate.format(publish_date)
    }

    fun getTimeToNow(publish_date: Date?): String {
        if (publish_date == null) {
            return ""
        }
        val nowDate = Date()
        var diff = nowDate.time - publish_date.time
        diff /= 1000
        if (diff < 1) {
            return "刚刚"
        }
        diff /= 60
        if (diff < 5) {
            return "刚刚"
        }
        if (diff in 6..59) {
            return SimpleDateFormat("HH:mm").format(publish_date)
        }
        diff /= 60
        if (diff <= 24) {
            return SimpleDateFormat("HH:mm").format(publish_date)
        }
        diff /= 24
        if (diff < 30) {
            return if (diff == 1L) {
                "昨天"
            } else diff.toString() + "天前"
        }
        val dfDate = SimpleDateFormat("yyyy-MM-dd")
        return dfDate.format(publish_date)
    }

    /**这是年-月-日 时 分 秒*/
    fun dateFormat2String(date: Date?): String {
        if (date == null) {
            return ""
        }
        @SuppressLint("SimpleDateFormat") val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return df.format(date)
    }

    fun strToDate(strDate: String): Date? {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val pos = ParsePosition(0)
        return formatter.parse(strDate, pos)
    }

    /**这是月-日*/
    fun dateFormatMD(date: Date?): String {
        if (date == null) {
            return ""
        }
        @SuppressLint("SimpleDateFormat") val df = SimpleDateFormat("MM-dd")
        return df.format(date)
    }

    /**这是年月*/
    fun dateFormatYM(date: Date?): String {
        if (date == null) {
            return ""
        }
        @SuppressLint("SimpleDateFormat") val df = SimpleDateFormat("yyyy-MM")
        return df.format(date)
    }

    /**这是年月*/
    fun dateFormatYM(date: String): String {
        @SuppressLint("SimpleDateFormat") val df = SimpleDateFormat("yyyy-MM")
        return df.format(formatYMDHMS(date))
    }

    /**将 年月日十分(秒)转为date*/
    fun formatYMDHMS(date: String?): Date? {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return try {
            df.parse(date)
        } catch (e: ParseException) {
            val df2 = SimpleDateFormat("yyyy-MM-dd HH:mm")
            try {
                df2.parse(date)
            } catch (e1: ParseException) {
                null
            }
        }
    }

    /**获取年、月*/
    fun formatYM(date: Date?): Array<String> {
        val time = arrayOf("", "")
        if (date == null) {
            return time
        }
        val year = String.format("%tY", date)
        val mon = String.format("%tm", date)
        time[0] = year
        time[1] = mon
        return time
    }

    /**
     * 验证字符串是否是一个合法的日期格式
     *
     * @param date     时间
     * @param template 验证的格式
     * @return 是否合法
     */
    fun isValidDate(date: String, template: String): Boolean {
        var convertSuccess = true
        // 指定日期格式
        val format = SimpleDateFormat(template, Locale.CHINA)
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2015/02/29会被接受，并转换成2015/03/01
            format.isLenient = false
            format.parse(date)
        } catch (e: Exception) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false
        }

        return convertSuccess
    }

    /**将毫秒数转化为X天X小时X分钟X秒*/
    fun formatTime(timeL: Long): String {
        var time = timeL
        val date: Long
        val hour: Long
        val minute: Long
        val second: Long
        //天数
        date = time / (1000 * 60 * 60 * 24)
        //减去整天后剩余
        time %= (1000 * 60 * 60 * 24)
        //小时
        hour = time / (1000 * 60 * 60)
        //减去剩余小时候时间
        time %= (1000 * 60 * 60)
        //分钟
        minute = time / (1000 * 60)
        //减去剩余分钟后时间
        time %= (1000 * 60)
        //秒
        second = time / 1000
        var result = ""
        if (date != 0L) {
            result = result + date + "天"
        }
        if (hour != 0L) {
            result = result + hour + "小时"
        }
        if (minute != 0L) {
            result = result + minute + "分"
        }
        if (second != 0L) {
            result = result + second + "秒"
        }
        return result
    }

    /**将毫秒数转化为X天X小时X分钟X秒*/
    fun formatTimeYMDHMS(timeL: Long): String {
        var time = timeL
        val date: Long
        val hour: Long
        val minute: Long
        val second: Long
        // 天数
        date = time / (1000 * 60 * 60 * 24)
        // 减去整天后剩余
        time %= (1000 * 60 * 60 * 24)
        // 小时
        hour = time / (1000 * 60 * 60)
        // 减去剩余小时候时间
        time %= (1000 * 60 * 60)
        // 分钟
        minute = time / (1000 * 60)
        // 减去剩余分钟后时间
        time %= (1000 * 60)
        // 秒
        second = time / 1000
        var result = ""
        if (date != 0L) {
            result = result + date + "天"
        }
        result = if (hour < 10) {
            result + "0" + hour + "小时"
        } else {
            result + hour + "小时"
        }
        result = if (minute < 10) {
            result + "0" + minute + "分"
        } else {
            result + minute + "分"
        }
        result = if (second < 10) {
            result + "0" + second + "秒"
        } else {
            result + second + "秒"
        }
        return result
    }

    /**将毫秒数转化为X天X小时X分钟*/
    fun formatTimeDHM(timeL: Long): String {
        var time = timeL
        val date: Long
        val hour: Long
        val minute: Long
        // 天数
        date = time / (1000 * 60 * 60 * 24)
        // 减去整天后剩余
        time %= (1000 * 60 * 60 * 24)
        // 小时
        hour = time / (1000 * 60 * 60)
        // 减去剩余小时候时间
        time %= (1000 * 60 * 60)
        // 分钟
        minute = time / (1000 * 60)
        // 减去剩余分钟后时间
        time %= (1000 * 60)
        var result = ""
        if (date != 0L) {
            result = result + date + "天"
        }
        if (hour != 0L) {
            result = result + hour + "小时"
        }
        if (minute != 0L) {
            result = result + minute + "分钟"
        }
        return result
    }

    /**将毫秒数转化为X天X小时X分钟X秒*/
    fun formatTime2Long(timeL: Long): Array<Long> {
        var time = timeL
        val longTime = arrayOf(0L, 0L, 0L, 0L)
        // 天数
        longTime[0] = time / (1000 * 60 * 60 * 24)
        // 减去整天后剩余
        time %= (1000 * 60 * 60 * 24)
        // 小时
        longTime[1] = time / (1000 * 60 * 60)
        // 减去剩余小时候时间
        time %= (1000 * 60 * 60)
        // 分钟
        longTime[2] = time / (1000 * 60)
        // 减去剩余分钟后时间
        time %= (1000 * 60)
        // 秒
        longTime[3] = time / 1000
        return longTime
    }
}