package qsos.lib.base.base.adapter

import android.view.View
import androidx.annotation.LayoutRes
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import qsos.lib.base.base.holder.BaseHolder

/**
 * @author : 华清松
 * 持有Activity生命周期的Adapter，用于Activity销毁时清除缓存，比如停止图片加载
 */
class BaseLifeCycleAdapter<T>(
        mLifecycle: Lifecycle,
        @LayoutRes private val layoutId: Int,
        list: ArrayList<T>,
        private val setHolder: (holder: BaseHolder<T>, data: T, position: Int) -> Unit,
        private val doRelease: () -> Unit = {}
) : BaseAdapter<T>(list), DefaultLifecycleObserver {

    init {
        mLifecycle.addObserver(this)
    }

    override fun getHolder(view: View, viewType: Int): BaseHolder<T> {
        return BaseNormalHolder(view, setHolder, doRelease)
    }

    override fun getLayoutId(viewType: Int): Int {
        return layoutId
    }

    override fun onItemClick(view: View, position: Int, obj: Any?) {}

    override fun onItemLongClick(view: View, position: Int, obj: Any?) {}

    override fun onDestroy(owner: LifecycleOwner) {
        mHolder.release()
        super.onDestroy(owner)
    }
}