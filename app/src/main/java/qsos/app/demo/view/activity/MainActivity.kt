package qsos.app.demo.view.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.act_main.*
import qsos.app.demo.R
import qsos.app.demo.view.fragment.MultipleTypesFragment
import qsos.lib.base.base.activity.BaseActivity
import qsos.lib.base.base.adapter.BaseFragmentAdapter

class MainActivity : BaseActivity(R.layout.act_main, true) {

    private val mFragments = arrayListOf<Fragment>()

    override fun initData(savedInstanceState: Bundle?) {
        for (i in 1..3) {
            val frag = MultipleTypesFragment()
            val mBundle = Bundle()
            mBundle.putString("title", "页$i")
            frag.arguments = mBundle
            mFragments.add(frag)
        }
    }

    override fun initView() {
        viewpager.adapter = BaseFragmentAdapter(supportFragmentManager, mFragments)

    }

    override fun getData(loadMore: Boolean) {

    }
}