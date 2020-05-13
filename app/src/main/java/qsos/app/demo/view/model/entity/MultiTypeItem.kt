package qsos.app.demo.view.model.entity

import androidx.annotation.DrawableRes
import qsos.app.demo.R
import qsos.lib.base.base.adapter.NormalMultipleTypesAdapter

data class MultiTypeItem(
        val title: String,
        @DrawableRes val avatar: Int,
        override val multipleTypeLayout: Int = R.layout.item_multi_type_demo1
) : NormalMultipleTypesAdapter.MultipleType