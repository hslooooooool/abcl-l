package qsos.lib.base.base.adapter

import android.view.View
import androidx.annotation.LayoutRes
import qsos.lib.base.base.holder.BaseHolder

/**
 * @author : 华清松
 * BaseNormalAdapter
 */
class BaseNormalAdapter<T>(
        @LayoutRes private val layoutId: Int,
        list: ArrayList<T>,
        private val setHolder: (holder: BaseHolder<T>, data: T, position: Int) -> Unit
) : BaseAdapter<T>(list) {

    override fun getHolder(view: View, viewType: Int): BaseHolder<T> {
        return BaseNormalHolder(view, setHolder)
    }

    override fun getLayoutId(viewType: Int): Int = layoutId

    override fun onItemClick(view: View, position: Int, obj: Any?) {}

    override fun onItemLongClick(view: View, position: Int, obj: Any?) {}

}