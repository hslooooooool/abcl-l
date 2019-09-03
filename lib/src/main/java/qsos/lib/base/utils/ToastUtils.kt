package qsos.lib.base.utils

import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.widget.Toast

object ToastUtils {

    fun showToast(context: Context?, msg: String) {
        if (context == null || TextUtils.isEmpty(msg)) {
            return
        }
        val toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun showToastLong(context: Context?, msg: String) {
        if (context == null || TextUtils.isEmpty(msg)) {
            return
        }
        val toast = Toast.makeText(context, msg, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

}