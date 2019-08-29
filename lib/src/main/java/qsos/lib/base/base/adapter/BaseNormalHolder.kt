package qsos.lib.base.base.adapter

import android.view.View
import qsos.lib.base.base.holder.BaseHolder

/**
 * @author : 华清松
 * BaseNormalHolder
 */
class BaseNormalHolder<T>(
        val view: View,
        private val setHolder: (holder: BaseHolder<T>, data: T, position: Int) -> Unit,
        private val doRelease: () -> Unit = {}
) : BaseHolder<T>(view) {
    override fun setData(data: T, position: Int) {
        setHolder(this, data, position)
    }

    override fun release() {
        super.release()
        doRelease()
    }
}