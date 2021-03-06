package qsos.lib.base.base.adapter

import android.view.View
import qsos.lib.base.base.holder.BaseHolder

/**NormalMultipleTypesHolder
 * @author : 华清松
 *
 */
class NormalMultipleTypesHolder<T>(
        val view: View,
        private val setHolder: (data: T, position: Int) -> Unit
) : BaseHolder<T>(view) {

    override fun bind(data: T, position: Int) {
        setHolder(data, position)
    }
}