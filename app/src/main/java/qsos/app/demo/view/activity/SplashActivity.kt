package qsos.app.demo.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.act_splash.*
import qsos.app.demo.R
import qsos.lib.base.base.activity.BaseActivity

/**
 * @author : 华清松
 * 闪屏界面
 */
class SplashActivity : BaseActivity(R.layout.act_splash, true) {

    private val mGuideList = arrayListOf(
            R.drawable.img_guide1,
            R.drawable.img_guide2,
            R.drawable.img_guide3,
            R.drawable.img_guide4
    )

    private var mClickable: Boolean = false

    override fun initData(savedInstanceState: Bundle?) {}

    override fun initView() {
        act_splash_pager.offscreenPageLimit = mGuideList.size
        act_splash_pager.adapter = object : PagerAdapter() {
            override fun isViewFromObject(view: View, obj: Any): Boolean {
                return view == obj
            }

            override fun getCount(): Int {
                return mGuideList.size
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val img = ImageView(container.context)
                img.setImageResource(mGuideList[position])
                img.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
                img.setOnClickListener {
                    if (mClickable) {
                        startActivity(Intent(mContext, MainActivity::class.java))
                    }
                }
                container.addView(img)
                return img
            }
        }
        act_splash_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                mClickable = position == mGuideList.size - 1
            }

        })
    }

    override fun getData(loadMore: Boolean) {}

}