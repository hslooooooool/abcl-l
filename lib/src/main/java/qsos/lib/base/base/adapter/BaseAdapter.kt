package qsos.lib.base.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import qsos.lib.base.base.holder.BaseHolder

/**
 * @author : 华清松
 * BaseAdapter
 */
abstract class BaseAdapter<T> constructor(
        open var data: ArrayList<T>,
        open val mRecyclerView: RecyclerView? = null
) : RecyclerView.Adapter<BaseHolder<T>>(), DefaultLifecycleObserver {

    open var mContext: Context? = null

    /**让子类实现用以提供 BaseHolder */
    abstract fun getHolder(view: View, viewType: Int): BaseHolder<T>

    /**提供用于 item 布局的 layoutId */
    @LayoutRes
    abstract fun getLayoutId(viewType: Int): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<T> {
        mContext = parent.context
        val viewId = getLayoutId(viewType)
        val view: View = LayoutInflater.from(mContext!!).inflate(viewId, parent, false)
        return getHolder(view, viewType)
    }

    /**绑定数据*/
    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) {
        holder.bind(data[position], position)
    }

    /**返回数据个数*/
    override fun getItemCount(): Int {
        return data.size
    }

    /**获得某个 position 上的 item 的数据*/
    fun getItem(position: Int): T {
        return data[position]
    }

    override fun onDestroy(owner: LifecycleOwner) {
        release()
        super.onDestroy(owner)
    }

    /**遍历所有 RecyclerView 中的 Holder,释放他们需要释放的资源*/
    open fun release() {
        mRecyclerView?.let {
            (it.childCount - 1 downTo 0).forEach { i ->
                val view = it.getChildAt(i)
                val viewHolder = it.getChildViewHolder(view)
                if (viewHolder != null && viewHolder is BaseHolder<*>) {
                    viewHolder.release()
                }
            }
        }
    }
}
