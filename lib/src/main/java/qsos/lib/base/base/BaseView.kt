package qsos.lib.base.base

/**
 * @author : 华清松
 * View 接口
 */
interface BaseView {
    /**视图ID*/
    val defLayoutId: Int

    /**是否处于前台*/
    var viewActive: Boolean
}