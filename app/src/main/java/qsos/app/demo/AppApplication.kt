package qsos.app.demo

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import qsos.lib.base.base.BaseApplication

/**
 * @author : 华清松
 * Application 类，此类被替换了怎么办？TODO
 */
open class AppApplication : BaseApplication(true), LifecycleOwner {

    override fun getLifecycle(): Lifecycle {
        return LifecycleRegistry(this)
    }

}