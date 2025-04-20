package cn.howxu.accounting_book.viewModel

import androidx.lifecycle.ViewModel
import cn.howxu.accounting_book.repo.MainRepository

/**
 * @description: TODO
 * @author: HowXu
 * @date:  23:02
 * 在 Android 开发中，ViewModel 是 Android Jetpack 组件 之一，属于 androidx.lifecycle 包下的一个类，专门用于 管理和存储与 UI 相关的数据。它的核心设计目的是在配置变更（如屏幕旋转、语言切换）时保持数据的一致性，同时避免内存泄漏。
 * ViewModel 的核心作用
 * 生命周期感知
 * 自动绑定到 Activity/Fragment 的生命周期，确保数据在配置更改（如旋转屏幕）时不会丢失。
 * 当 Activity 或 Fragment 被销毁并重建时（非应用退出），ViewModel 会保留数据。
 * 数据持久化
 * 存储 UI 层（如 Activity）所需的业务逻辑和数据，避免将数据直接放在 Activity/Fragment 中（减少内存泄漏风险）。
 * 与 LiveData 配合
 * 通常结合 LiveData 使用，实现数据变化的自动通知（观察者模式），确保 UI 和数据同步。
 */
class MainViewModel(val repository: MainRepository): ViewModel() {
    // 次构造函数
    // 提供一个无参构造方式，内部通过 this() 调用主构造函数，并手动创建默认的 MainRepository 实例
    constructor():this(MainRepository())

    // 直接等号等价于拿返回值
    fun loadDta() = repository.items
    fun loadBudgets() = repository.budgets

}