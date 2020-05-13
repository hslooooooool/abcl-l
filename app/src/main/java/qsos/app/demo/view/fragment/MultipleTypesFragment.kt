package qsos.app.demo.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.frg_multi_type.*
import qsos.app.demo.R
import qsos.app.demo.view.model.entity.MultiTypeItem
import qsos.lib.base.base.adapter.NormalMultipleTypesAdapter
import qsos.lib.base.base.adapter.NormalMultipleTypesHolder
import qsos.lib.base.base.fragment.BaseFragment

class MultipleTypesFragment : BaseFragment(R.layout.frg_multi_type, false) {

    private val mList = arrayListOf<MultiTypeItem>()

    override fun initData(savedInstanceState: Bundle?) {
        getData(false)
    }

    override fun initView(view: View) {
        recycle_multi_type_demo.layoutManager = LinearLayoutManager(mContext)
        recycle_multi_type_demo.adapter = NormalMultipleTypesAdapter(mList, setHolder = { v, viewType ->
            return@NormalMultipleTypesAdapter when (viewType) {
                R.layout.item_multi_type_demo1 -> {
                    NormalMultipleTypesHolder(v, setHolder = { data, _ ->
                        val avatar = v.findViewById<ImageView>(R.id.img_avatar)
                        avatar.setImageResource(data.avatar)
                        val title = v.findViewById<TextView>(R.id.tv_title)
                        title.text = data.title
                    })
                }
                else -> {
                    NormalMultipleTypesHolder(v, setHolder = { data, _ ->
                        val avatar = v.findViewById<ImageView>(R.id.img_avatar)
                        avatar.setImageResource(data.avatar)
                        val title = v.findViewById<TextView>(R.id.tv_title)
                        title.text = data.title
                    })
                }
            }
        })
        recycle_multi_type_demo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recycle_multi_type_demo.canScrollVertically(1)) {
                    getData(true)
                    recycle_multi_type_demo.adapter?.notifyDataSetChanged()
                }
            }
        })
    }

    override fun getData(loadMore: Boolean) {
        if (!loadMore) {
            mList.clear()
        }
        for (i in 1..30) {
            val item = MultiTypeItem(
                    "Veer图库通过运动图片专题，为大家提供高品质运动图片素材大全。" +
                            "Veer图库内容同步于全球微利图库鼻祖iStock图片资源库，" +
                            "精选超高性价比独家优质图片内容，亿级素材资源任选下载，" +
                            "100%正版与服务保障，一次授权永久商用，放心购买国际商用运动图片素材，" +
                            "让您的项目脱颖而出！",
                    when (i % 3) {
                        0 -> R.drawable.img_demo1
                        1 -> R.drawable.img_demo2
                        else -> R.drawable.img_demo3
                    },
                    if (i % 3 == 0) R.layout.item_multi_type_demo1 else R.layout.item_multi_type_demo2
            )
            mList.add(item)
        }
    }

}