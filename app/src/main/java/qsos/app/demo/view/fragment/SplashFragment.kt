package qsos.app.demo.view.fragment

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.app_frag_splash.*
import qsos.app.demo.R
import qsos.lib.base.base.fragment.BaseFragment

class SplashFragment : BaseFragment(R.layout.app_frag_splash, false) {

    private var mTitle: String? = ""

    override fun initData(savedInstanceState: Bundle?) {
        mTitle = arguments?.getString("title")
    }

    override fun initView(view: View) {
        frag_splash_title?.text = mTitle
    }

    override fun getData(loadMore: Boolean) {

    }

}