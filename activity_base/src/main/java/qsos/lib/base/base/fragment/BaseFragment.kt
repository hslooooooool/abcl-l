package qsos.lib.base.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import qsos.lib.base.R
import qsos.lib.base.base.BaseView

/**BaseFragment
 * @author : 华清松
 *
 */
abstract class BaseFragment(
        private val layoutId: Int? = null,
        private val reload: Boolean = false
) : Fragment(), BaseView {

    open lateinit var mContext: Context

    override val defLayoutId: Int = R.layout.base_default
    override var viewActive: Boolean = false

    /*注意调用顺序*/

    /**初始化数据*/
    abstract fun initData(savedInstanceState: Bundle?)

    /**初始化视图*/
    abstract fun initView(view: View)

    /**获取数据*/
    abstract fun getData(loadMore: Boolean = true)

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        initData(bundle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, bundle: Bundle?): View? {
        mContext = this.context!!
        return inflater.inflate(layoutId ?: defLayoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    override fun onResume() {
        super.onResume()
        if (reload) {
            getData(false)
        }
    }

}