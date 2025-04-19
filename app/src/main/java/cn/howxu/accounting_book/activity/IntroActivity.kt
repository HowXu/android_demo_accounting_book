package cn.howxu.accounting_book.activity

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import cn.howxu.android_demo_accounting_book.databinding.ActivityIntroBinding

/**
 * @author: HowXu
 * @description: Intro Activity
 * @date: 2025/4/19 10:23
 * @param null
 * @return null
 */
class IntroActivity : AppCompatActivity() {
    // 这个是ViewModel 自动生成的bind lateinit可以延迟初始化
    lateinit var bind: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         *     解析 activity_intro.xml 布局文件
         *     创建所有视图对象
         *     返回一个 ActivityIntroBinding 实例，用于后续访问这些视图
         *     ActivityIntroBinding.inflate() 是 视图绑定 的初始化代码
         *     layoutInflater的主要作用是将 XML 布局文件实例化为对应的 View 对象（视图层次结构）
         */
        bind = ActivityIntroBinding.inflate(layoutInflater)
        // 视图设置为bind的根元素 也就是activity_intro
        setContentView(bind.root)
        /**
         *     取消窗口边界限制：
         *         允许应用内容延伸到系统UI区域（状态栏/导航栏）后面
         *         不同于普通全屏模式（只是隐藏系统UI）
         *     视觉效果：
         *         内容可以显示在状态栏和导航栏后方
         *         需要配合适当的padding或inset处理才能正常交互
         *
         */
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        // 这个就是getStart的监听器 监听到了就会跳转到MainActivity的页面 上下文设置为自己
        bind.getStart.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}