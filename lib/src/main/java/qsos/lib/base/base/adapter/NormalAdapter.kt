package qsos.lib.base.base.adapter

import android.view.View
import androidx.annotation.LayoutRes
import qsos.lib.base.base.holder.BaseHolder

/**单类型列表项容器 NormalAdapter
 * @author : 华清松
 *
 */
class NormalAdapter<T>(
        @LayoutRes private val layoutId: Int,
        list: ArrayList<T>,
        private val setHolder: (holder: BaseHolder<T>, data: T, position: Int) -> Unit
) : BaseAdapter<T>(list) {

    override fun getHolder(view: View, viewType: Int): BaseHolder<T> {
        return NormalHolder(view, setHolder)
    }

    override fun getLayoutId(viewType: Int): Int = layoutId

}