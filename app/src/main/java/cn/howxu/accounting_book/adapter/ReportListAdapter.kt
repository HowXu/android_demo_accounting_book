package cn.howxu.accounting_book.adapter

import android.content.Context
import android.content.res.Resources
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.howxu.accounting_book.adapter.ExpenseListAdapter.ViewHolder
import cn.howxu.accounting_book.domain.BudgetDomain
import cn.howxu.android_demo_accounting_book.R
import cn.howxu.android_demo_accounting_book.databinding.ViewHolderBudgetsBinding
import cn.howxu.android_demo_accounting_book.databinding.ViewHolderItemsBinding
import com.bumptech.glide.Glide

/**
 * @description: 同款Adapter
 * @author: HowXu
 * @date: 2025/4/20 16:23
 */
class ReportListAdapter(private val items: MutableList<BudgetDomain>) :
    RecyclerView.Adapter<ReportListAdapter.ViewHolder>() {
    class ViewHolder(val binding: ViewHolderBudgetsBinding) : RecyclerView.ViewHolder(binding.root)

    private lateinit var context: Context
    private var formater: DecimalFormat? = DecimalFormat("###,###,###,###")

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReportListAdapter.ViewHolder {
        context = parent.context
        val binding = ViewHolderBudgetsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportListAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleText.text = item.title
        holder.binding.percentText.text = buildString {
            append(item.percent)
            append(" %")
        }
        holder.binding.monthlyText.text = buildString {
            append(buildString {
                append("$ ")
            })
            append(item.price)
            append(" /月")
        }
        // 额外做一个 设置进度条属性的
        holder.binding.progressBar.apply {
            progress = item.percent.toFloat()
            if (position % 2 == 1){
                progressBarColor = context.resources.getColor(R.color.blue,null)
                holder.binding.percentText.setTextColor(context.resources.getColor(R.color.blue,null))
            } else {
                progressBarColor = context.resources.getColor(R.color.pink,null)
                holder.binding.percentText.setTextColor(context.resources.getColor(R.color.pink,null))
            }
        }

    }

    override fun getItemCount() = items.size
}