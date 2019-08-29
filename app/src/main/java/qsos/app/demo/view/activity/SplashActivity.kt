package qsos.app.demo.view.activity

import android.os.Bundle
import qsos.app.demo.R
import qsos.lib.base.base.activity.BaseActivity

/**
 * @author : 华清松
 * 闪屏界面
 */
class SplashActivity(
        override val layoutId: Int = R.layout.app_activity_splash,
        override val reload: Boolean = false
) : BaseActivity() {

    override fun initData(savedInstanceState: Bundle?) {}

    override fun initView() {}

    override fun getData() {}
}