package qsos.lib.base.base.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import qsos.lib.base.R
import qsos.lib.base.router.AppPath

/**
 * @author : 华清松
 * 欢迎界面
 */
@Route(group = AppPath.GROUP, path = AppPath.DEFAULT)
class DemoActivity(
        override val layoutId: Int = R.layout.welcome,
        override val reload: Boolean = false
) : BaseActivity() {

    override fun initData(savedInstanceState: Bundle?) {}

    override fun initView() {}

    override fun getData() {}
}