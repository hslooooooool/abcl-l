package qsos.lib.base.callback

import android.view.View

/**RecyclerView 子项点击监听
 * @author : 华清松
 *
 */
interface OnItemListener<T> {
    /**
     * 子项点击监听
     * @param view 当前点击的视图
     * @param position 当前项位置
     * @param obj 附加值，比如可以根据此来值判断将要进行什么操作，一般为当前位置数据实体
     * @param long 是否长按，默认 false ，表示点击
     * */
    fun onClick(view: View, position: Int, obj: T? = null, long: Boolean = false)

}