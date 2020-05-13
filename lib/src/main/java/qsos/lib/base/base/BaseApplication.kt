package qsos.lib.base.base

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.alibaba.android.arouter.launcher.ARouter
import qsos.lib.base.config.IConfig
import qsos.lib.base.utils.LogUtil
import qsos.lib.base.utils.SharedPreUtils
import timber.log.Timber.DebugTree

/**
 * @author : 华清松
 * BaseApplication
 */
open class BaseApplication(
        override var debugARouter: Boolean = false,
        override var debugTimber: LogUtil.LEVEL = LogUtil.LEVEL.A
) : MultiDexApplication(), IConfig {

    companion object {
        lateinit var appContext: BaseApplication

        /**Application 初始化是否完成*/
        var buildFinish: Boolean = false
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        /**阿里路由配置*/
        if (debugARouter) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(appContext)

        /**Timber 日志*/
        LogUtil.open(debugTimber, DebugTree())
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        /**是否第一次启动APP判断*/
        if (SharedPreUtils.getBoolean(base, SharedPreUtils.FIRST_LAUNCH)) {
            // 首次启动
            SharedPreUtils.saveBoolean(base, SharedPreUtils.FIRST_LAUNCH, true)
            Thread(Runnable {
                MultiDex.install(this)
                buildFinish = true
            }).start()
        } else {
            // 非首次启动
            MultiDex.install(this)
            buildFinish = true
        }
    }

}
