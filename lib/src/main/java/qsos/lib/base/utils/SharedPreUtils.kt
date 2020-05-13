package qsos.lib.base.utils

import android.content.Context

/**
 * @author : 华清松
 * SharedPreferences 工具类
 */
internal object SharedPreUtils {

    private const val SHARED_PRE = "QSOS_SHARED_PRE"

    /**第一次启动APP*/
    const val FIRST_LAUNCH = "FIRST_LAUNCH"

    /**保存 String 类型数据*/
    fun saveStr(context: Context?, key: String, value: String?) {
        context?.getSharedPreferences(SHARED_PRE, Context.MODE_PRIVATE)?.edit()?.putString(key, value)?.apply()
    }

    /**获取 String 类型数据*/
    fun getStr(context: Context?, key: String): String? {
        val sp = context?.getSharedPreferences(SHARED_PRE, Context.MODE_PRIVATE)
        return sp?.getString(key, null)
    }

    /**保存 Boolean 类型数据*/
    fun saveBoolean(context: Context?, key: String, value: Boolean) {
        context?.getSharedPreferences(SHARED_PRE, Context.MODE_PRIVATE)?.edit()?.putBoolean(key, value)?.apply()
    }

    /**获取 Boolean 类型数据*/
    fun getBoolean(context: Context?, key: String): Boolean {
        val sp = context?.getSharedPreferences(SHARED_PRE, Context.MODE_PRIVATE)
        return sp?.getBoolean(key, false) ?: false
    }

}