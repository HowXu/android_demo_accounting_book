package cn.howxu.accounting_book.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cn.howxu.accounting_book.adapter.ExpenseListAdapter
import cn.howxu.accounting_book.viewModel.MainViewModel
import cn.howxu.android_demo_accounting_book.databinding.ActivityMainBinding

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

        initRecycleView()
        // 在这之后 从视图到数据都有完整的控制链在 并且自动处理 自动渲染
    }

    private fun initRecycleView() {
        binding.reView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        // 传入数据由ViewModel控制 页面由Adapter控制 Adapter从view_holder_items拿布局进行渲染
        binding.reView.adapter = ExpenseListAdapter(mainViewModel.loadDta())
    }
}