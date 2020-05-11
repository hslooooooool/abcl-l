package qsos.app.demo.view.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.app_activity_splash.*
import qsos.app.demo.R
import qsos.app.demo.view.fragment.SplashFragment
import qsos.lib.base.base.activity.BaseActivity
import qsos.lib.base.base.adapter.BaseFragmentAdapter

/**
 * @author : 华清松
 * 闪屏界面
 */
class SplashActivity : BaseActivity(R.layout.app_activity_splash, true) {

    private val mFragments = arrayListOf<Fragment>()

    override fun initData(savedInstanceState: Bundle?) {
        for (i in 1..3) {
            val frag = SplashFragment()
            val mBundle = Bundle()
            mBundle.putString("title", "页$i")
            frag.arguments = mBundle
            mFragments.add(frag)
        }
    }

    override fun initView() {
        act_splash_pager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragments)
    }

    override fun getData(loadMore: Boolean) {

    }
}