package cn.howxu.accounting_book.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.core.widget.NestedScrollView
import kotlin.math.abs


/**
 * @description: 重写ScrollView渲染
 * @author: HowXu
 * @date:  10:11
 */
class FullScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : NestedScrollView(context, attrs) {
    private var slop = 0
    private var touch = 0

    init {
        setSlop(context)
    }

    /**
     * 是否intercept当前的触摸事件
     * @param ev 触摸事件
     * @return true：调用onMotionEvent()方法，并完成滑动操作
     */
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN ->                 //  保存当前touch的纵坐标值
                touch = ev.rawY.toInt()

            MotionEvent.ACTION_MOVE ->                 //  滑动距离大于slop值时，返回true
                if (abs((ev.rawY.toInt() - touch).toDouble()) > slop) return true
        }

        return super.onInterceptTouchEvent(ev)
    }

    /**
     * 获取相应context的touch slop值（即在用户滑动之前，能够滑动的以像素为单位的距离）
     * @param context ScrollView对应的context
     */
    private fun setSlop(context: Context) {
        slop = ViewConfiguration.get(context).scaledTouchSlop
    }
}