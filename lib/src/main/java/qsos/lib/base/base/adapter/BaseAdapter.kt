package qsos.lib.base.base.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import qsos.lib.base.base.holder.BaseHolder
import qsos.lib.base.callback.OnListItemClickListener

/**
 * @author : 华清松
 * BaseAdapter
 */
abstract class BaseAdapter<T>(
        var data: ArrayList<T>
) : RecyclerView.Adapter<BaseHolder<T>>(), OnListItemClickListener {

    lateinit var mHolder: BaseHolder<T>
    lateinit var mContext: Context

    /**让子类实现用以提供 BaseHolder */
    abstract fun getHolder(view: View, viewType: Int): BaseHolder<T>

    /**提供用于 item 布局的 layoutId */
    @LayoutRes
    abstract fun getLayoutId(viewType: Int): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<T> {
        mContext = parent.context
        val viewId = getLayoutId(viewType)
        val view: View
        view = LayoutInflater.from(mContext).inflate(viewId, parent, false)
        mHolder = getHolder(view, viewType)
        return mHolder
    }

    /**绑定数据*/
    override fun onBindViewHolder(holder: BaseHolder<T>, position: Int) {
        holder.setData(data[position], position)
    }

    /**返回数据个数*/
    override fun getItemCount(): Int {
        return data.size
    }

    /**获得某个 position 上的 item 的数据*/
    fun getItem(position: Int): T {
        return data[position]
    }

    /**遍历所有 RecyclerView 中的 Holder,释放他们需要释放的资源*/
    fun release(recyclerView: RecyclerView?) {
        if (recyclerView == null) {
            return
        }
        (recyclerView.childCount - 1 downTo 0).forEach { i ->
            val view = recyclerView.getChildAt(i)
            val viewHolder = recyclerView.getChildViewHolder(view)
            if (viewHolder != null && viewHolder is BaseHolder<*>) {
                viewHolder.release()
            }
        }
    }
}
