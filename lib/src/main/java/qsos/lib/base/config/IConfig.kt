package qsos.lib.base.config

import qsos.lib.base.utils.LogUtil

/**
 * @author : 华清松
 * 框架配置清单
 */
interface IConfig {

    /**开启 ARouter 路由调试*/
    var debugARouter: Boolean

    /**开启 Timber 日志打印*/
    var debugTimber: LogUtil.LEVEL

}