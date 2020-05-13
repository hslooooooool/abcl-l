package qsos.lib.base.base.adapter

import android.view.View
import androidx.annotation.LayoutRes
import qsos.lib.base.R
import qsos.lib.base.base.holder.BaseHolder

/**
 * @author : 华清松
 * 多类型列表项容器 NormalMultipleTypesAdapter
 */
class NormalMultipleTypesAdapter<T : NormalMultipleTypesAdapter.MultipleType>(
        list: ArrayList<T>,
        private val setHolder: (view: View, viewType: Int) -> NormalMultipleTypesHolder<T>
) : BaseAdapter<T>(list) {

    override fun getHolder(view: View, viewType: Int): BaseHolder<T> {
        return setHolder(view, viewType)
    }

    override fun getLayoutId(viewType: Int): Int {
        return viewType
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].multipleTypeLayout
    }

    interface MultipleType {
        val multipleTypeLayout: Int
    }
}