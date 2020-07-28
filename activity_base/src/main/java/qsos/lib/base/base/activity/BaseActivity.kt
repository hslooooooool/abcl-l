package qsos.lib.base.base.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import qsos.lib.base.R
import qsos.lib.base.base.BaseView
import qsos.lib.base.utils.ActivityManager

/**BaseActivity
 * @author : 华清松
 *
 */
abstract class BaseActivity(
        private val layoutId: Int? = null,
        private val reload: Boolean = false,
        private val isOrientation: Boolean = true
) : AppCompatActivity(), BaseView {

    open lateinit var mContext: Context

    override val defLayoutId: Int = R.layout.base_default
    override var viewActive: Boolean = false

    /*注意调用顺序*/

    /**初始化数据*/
    abstract fun initData(savedInstanceState: Bundle?)

    /**初始化视图*/
    abstract fun initView()

    /**获取数据*/
    abstract fun getData(loadMore: Boolean = true)

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)

        mContext = this

        // 竖屏显示
        if (isOrientation) requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        ActivityManager.addActivity(this)

        initData(bundle)

        if (layoutId == null) {
            setContentView(defLayoutId)
        } else {
            setContentView(layoutId)
            initView()
        }

    }

    override fun onStart() {
        super.onStart()
        viewActive = true
    }

    override fun onResume() {
        super.onResume()
        if (reload) {
            getData(false)
        }
    }

    override fun onStop() {
        super.onStop()
        viewActive = false
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityManager.finishSingle(this)
    }
}
