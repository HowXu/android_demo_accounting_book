package cn.howxu.accounting_book.activity

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cn.howxu.accounting_book.adapter.ExpenseListAdapter
import cn.howxu.accounting_book.viewModel.MainViewModel
//noinspection SuspiciousImport
import android.R
import android.content.Intent
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import cn.howxu.android_demo_accounting_book.databinding.ActivityMainBinding
import eightbitlab.com.blurview.RenderEffectBlur

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    /**
     * by 是一个关键字，用于 委托（Delegation）。
     * 这里的 by viewModels() 表示将 mainViewModel 的 初始化和管理工作委托给 viewModels() 这个委托工厂
     * 延迟初始化：MainViewModel 不会立即创建，只有在首次访问 mainViewModel 时才会初始化。
     * 生命周期感知：viewModels() 委托会确保 ViewModel 与当前 Activity/Fragment 的生命周期绑定，并在配置变更（如屏幕旋转）时保留实例。
     * 作用域控制：默认情况下，ViewModel 的生命周期与宿主 Activity/Fragment 一致
     * by viewModels() 本质上是一个 属性委托（Property Delegation），类似于 Kotlin 中的 lazy。它的简化实现逻辑如下：
     * 首次访问 mainViewModel 时，触发 viewModels() 的 getValue() 方法。
     * 内部通过 ViewModelProvider 获取或创建 ViewModel 实例。
     * 后续访问直接返回已创建的实例。
     */
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 直接从上下文实现绑定视图创建
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setVarable()

        initRecycleView()
        // 在这之后 从视图到数据都有完整的控制链在 并且自动处理 自动渲染
        setBlueEffect()
    }

    private fun setVarable() {
        binding.cardBtn.setOnClickListener {
            startActivity(Intent(this, ReportActivity::class.java))
        }
    }

    /**
     * @author: HowXu
     * @description: 使用BlueView渲染半透明毛玻璃效果
     * @date: 2025/4/20 10:33
     * @param null
     * @return
     */
    private fun setBlueEffect() {
        // 定义模糊的强度（值越大越模糊）
        val radius = 10f

        /**
         * decorView：当前窗口的根视图（包括状态栏、导航栏和内容区域）。
         * android.R.id.content：系统预定义的 ID，指向 FrameLayout（即内容区域的根布局，不包括状态栏/导航栏）。
         * 转型为 ViewGroup：因为模糊效果需要绑定到一个视图容器
         */
        val decorView = this.window.decorView
        // 这里这个是android.R A ViewGroup is a special view that can contain other views
        val rootView = decorView.findViewById<View>(R.id.content) as ViewGroup
        // 获取窗口的默认背景（通常是纯色或主题背景），用于在模糊时填充透明区域
        val windowBackground = decorView.background
        // RenderScriptBlur已弃用 这里用RenderEffectBlur
        /**
         * setupWith()：将模糊视图 (blurView) 绑定到目标视图 (rootView)。
         *     参数 1：需要模糊的源视图（这里选的是内容区域的根布局）。
         *     参数 2：模糊实现类（RenderEffectBlur 是 Android 12+ 的硬件加速方案）。
         * setBlurRadius()：设置模糊半径（之前定义的 10f）。
         * setFrameClearDrawable()：设置清除模糊时的备用背景（避免透明区域显示异常）
         */
        binding.blurView.setupWith(
            rootView, RenderEffectBlur()
        ).setBlurRadius(radius).setFrameClearDrawable(windowBackground)
        //outlineProvider：定义视图的轮廓（这里使用背景的轮廓，通常为矩形）。
        //clipToOutline：启用轮廓裁剪，确保模糊效果只作用于视图边界内（避免溢出）
        binding.blurView.outlineProvider = ViewOutlineProvider.BACKGROUND
        binding.blurView.clipToOutline = true

    }

    private fun initRecycleView() {
        binding.reView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // 传入数据由ViewModel控制 页面由Adapter控制 Adapter从view_holder_items拿布局进行渲染
        binding.reView.adapter = ExpenseListAdapter(mainViewModel.loadDta())
        // 加了这个之后ScrollView和RecycleView绑定 不会分开滑动 但是会限制渲染组件数量
        // 禁用嵌套滚动（Nested Scrolling）
        // 父容器是 NestedScrollView，子视图是 RecyclerView。
        // 当子视图滚动到顶部/底部时，父容器可以继续滚动（反之亦然）
        // 在MainActivity RecycleView加个底部向上70dp 不让导航栏和列表项重叠
        // 这里把ScrollView替换成NestedScrollView的重写版本FullScrollView 可以根据需求动态调整渲染高度
        // RecycleView layout_height因为wrap_content
        binding.reView.isNestedScrollingEnabled = false
    }
}