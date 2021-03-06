package qsos.lib.base.base.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**子项布局 Holder
 * @author : 华清松
 *
 */
abstract class BaseHolder<T>(
        itemView: View
) : RecyclerView.ViewHolder(itemView) {

    /**设置数据 */
    abstract fun bind(data: T, position: Int)

    /** 释放资源 */
    open fun release() {}

}
