package qsos.lib.base.utils

import qsos.lib.base.utils.LogUtil.LEVEL.*
import timber.log.Timber

/**
 * @author 华清松
 * 日志打印工具
 */
object LogUtil {

    /**
     * @see A 打印所有
     * @see V 打印至V级别
     * @see D 打印至D级别
     * @see I 打印至I级别
     * @see W 打印至W级别
     * @see E 打印至E级别
     * @see N 打印至E级别
     * */
    enum class LEVEL {
        A, V, I, W, D, E, N
    }

    var level = N

    private var TAG = "APP日志"

    /**
     * @param level 日志打印级别
     * */
    fun open(
            level: LEVEL,
            tree: Timber.Tree? = null
    ) {
        this.level = level
        tree?.let { Timber.plant(it) }
    }

    fun e(msg: String) {
        e(TAG, msg)
    }

    fun w(msg: String) {
        w(TAG, msg)
    }

    fun i(msg: String) {
        i(TAG, msg)
    }

    fun d(msg: String) {
        d(TAG, msg)
    }

    fun v(msg: String) {
        v(TAG, msg)
    }

    fun v(tag: String, msg: String) {
        if (level > A) {
            Timber.tag(tag).v(msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (level > V) {
            Timber.tag(tag).d(msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (level > D) {
            Timber.tag(tag).i(msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (level > I) {
            Timber.tag(tag).w(msg)
        }
    }

    fun e(tag: String, msg: String) {
        if (level > W) {
            Timber.tag(tag).e(msg)
        }
    }

}
