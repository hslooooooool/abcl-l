package qsos.lib.base.base

/**View 接口
 * @author : 华清松
 *
 */
interface BaseView {
    /**视图ID*/
    val defLayoutId: Int

    /**是否处于前台*/
    var viewActive: Boolean
}