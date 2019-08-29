package qsos.lib.base.base.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import qsos.lib.base.R
import qsos.lib.base.base.BaseView
import qsos.lib.base.utils.LogUtil

/**
 * @author : 华清松
 * BaseFragment
 */
@SuppressLint("SetTextI18n")
abstract class BaseFragment : Fragment(), BaseView {

    override var isActive: Boolean = false
        protected set(value) {
            field = value
        }

    override val defLayoutId: Int = R.layout.base_default

    lateinit var mContext: Context

    private var mainView: View? = null

    /**设置视图ID*/
    abstract val layoutId: Int?

    /**视图重载是否重新加载数据*/
    abstract val reload: Boolean

    /*注意调用顺序*/

    /**初始化数据*/
    abstract fun initData(savedInstanceState: Bundle?)

    /**初始化视图*/
    abstract fun initView(view: View)

    /**获取数据*/
    abstract fun getData()

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        initData(bundle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?): View? {
        LogUtil.i("创建:${javaClass.name}")

        mContext = this.context!!

        mainView = inflater.inflate(layoutId ?: defLayoutId, container, false)

        return mainView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    override fun onResume() {
        super.onResume()
        // 页面重现，重新加载数据
        if (reload) {
            getData()
        }
    }

}
