package qsos.lib.base.base.adapter

import android.view.View
import qsos.lib.base.base.holder.BaseHolder

/**NormalHolder
 * @author : 华清松
 *
 */
class NormalHolder<T>(
        val view: View,
        private val setHolder: (holder: BaseHolder<T>, data: T, position: Int) -> Unit
) : BaseHolder<T>(view) {
    override fun bind(data: T, position: Int) {
        setHolder(this, data, position)
    }
}