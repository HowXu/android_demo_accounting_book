package cn.howxu.accounting_book.adapter

import android.content.Context
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import androidx.recyclerview.widget.RecyclerView
import cn.howxu.accounting_book.domain.ExpenseDomain
import cn.howxu.android_demo_accounting_book.databinding.ViewHolderItemsBinding
import com.bumptech.glide.Glide

/**
 * @description: TODO
 * @author: HowXu
 * @date:  23:20
 */

/**
 * RecyclerView 是用于高效展示大量数据列表的核心组件
 * Adapter（适配器）
 * 数据与视图的桥梁：将数据源（如 List）中的每一项数据绑定到 RecyclerView 的单个 item 视图上。
 * 告诉 RecyclerView 有多少数据项（getItemCount）。
 * 创建和管理 ViewHolder（onCreateViewHolder）。
 * 绑定数据到 ViewHolder（onBindViewHolder）。
 *
 * ViewHolder（视图容器）
 * 复用 item 视图：避免频繁调用 findViewById，提升滚动性能。
 * 持有视图引用：保存 item 布局中的子视图（如 TextView、ImageView）。
 * 复用机制：RecyclerView 的缓存池（如 RecycledViewPool）会复用已滑出屏幕的 ViewHolder。
 * 与 Adapter 关系：ViewHolder 由 Adapter 创建和管理，但生命周期由 RecyclerView 控制。
 *
 * Adapter 和 ViewHolder 的协作流程
 * 首次加载：
 * RecyclerView 调用 Adapter 的 onCreateViewHolder() 创建新的 ViewHolder。
 * 接着调用 onBindViewHolder() 绑定数据到 ViewHolder。
 * 滚动复用：
 * 当 item 滑出屏幕时，ViewHolder 会被放入缓存池。
 * 新 item 进入屏幕时，直接从缓存池获取 ViewHolder，仅触发 onBindViewHolder()（无需重新创建视图）。
 */
class ExpenseListAdapter(private val items: MutableList<ExpenseDomain>): RecyclerView.Adapter<ExpenseListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewHolderItemsBinding):RecyclerView.ViewHolder(binding.root)

    private lateinit var context:Context
    private var formater:DecimalFormat? = DecimalFormat("###,###,###.##")

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseListAdapter.ViewHolder {
        context = parent.context
        /**
         * LayoutInflater.from(context)
         * 作用：获取一个 LayoutInflater 实例，用于将 XML 布局文件转换为实际的 View 对象
         *
         * ViewHolderItemsBinding.inflate()
         * 来源：
         * 这是由 View Binding 自动生成的类（对应布局文件 view_holder_items.xml）。命名规则为：
         * XML文件名转换为驼峰命名 + Binding（如 item_user.xml → ItemUserBinding）。
         * 作用：
         * 将 XML 布局文件实例化为一个绑定类对象，并直接访问布局中的所有带 ID 的视图（无需 findViewById）inflate() 方法将 XML 转为 View 对象
         */
        val binding = ViewHolderItemsBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    // 上面创建之后传到这里 就可以直接修改内容了
    override fun onBindViewHolder(holder: ExpenseListAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleText.text = item.title
        holder.binding.timeText.text = item.time
        // 为什么不建议用+呢
        holder.binding.priceText.text = buildString {
            append("$ ")
            append(formater?.format(item.price))
        }
        holder.binding.titleText.text = item.title

        val resId = holder.itemView.resources.getIdentifier(item.pic,"drawable",context.packageName)
        // 这里直接加载进去
        Glide.with(context).load(resId).into(holder.binding.pic)

    }

    override fun getItemCount(): Int = items.size
}