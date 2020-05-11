package qsos.lib.base.base.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import qsos.lib.base.R
import qsos.lib.base.base.BaseView
import qsos.lib.base.utils.ActivityManager
import qsos.lib.base.utils.LogUtil

/**
 * @author : 华清松
 * Base Activity
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

    override fun startActivity(intent: Intent) {
        LogUtil.i("启动:$localClassName")
        super.startActivity(intent)
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(bundle: Bundle?) {
        LogUtil.i("创建:$localClassName")
        super.onCreate(bundle)

        mContext = this

        // 竖屏显示
        if (isOrientation) requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        ARouter.getInstance().inject(this)

        ActivityManager.addActivity(this)

        initData(bundle)

        if (layoutId == null) {
            setContentView(defLayoutId)
        } else {
            setContentView(layoutId!!)
            initView()
        }

    }

    override fun onStart() {
        LogUtil.i("开启:$localClassName")
        super.onStart()
        viewActive = true
    }

    override fun onResume() {
        LogUtil.i("当前:$localClassName")
        super.onResume()
        if (reload) {
            getData(false)
        }
    }

    override fun onPause() {
        LogUtil.i("暂停:$localClassName")
        super.onPause()
    }

    override fun onStop() {
        LogUtil.i("停止:$localClassName")
        super.onStop()
        viewActive = false
    }

    override fun finish() {
        LogUtil.i("结束:$localClassName")
        super.finish()
    }

    override fun onDestroy() {
        LogUtil.i("销毁:$localClassName")
        super.onDestroy()
        ActivityManager.finishSingle(this)
    }
}
