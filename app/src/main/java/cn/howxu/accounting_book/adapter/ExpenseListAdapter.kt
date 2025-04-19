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

// TODO 解释一下你做了什么
class ExpenseListAdapter(private val items: MutableList<ExpenseDomain>): RecyclerView.Adapter<ExpenseListAdapter.ViewHolder>() {

    class ViewHolder(val binding: ViewHolderItemsBinding):RecyclerView.ViewHolder(binding.root)

    private lateinit var context:Context
    private var formater:DecimalFormat? = null
    
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExpenseListAdapter.ViewHolder {
        context = parent.context
        formater = DecimalFormat("###,###,###.##")

        val binding = ViewHolderItemsBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseListAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.titleText.text = item.title
        holder.binding.timeText.text = item.time
        holder.binding.priceText.text = "$ " + formater?.format(item.price)
        holder.binding.titleText.text = item.title

        val resId = holder.itemView.resources.getIdentifier(item.pic,"drawable",context.packageName)
        // 这里直接加载进去
        Glide.with(context).load(resId).into(holder.binding.pic)

    }

    override fun getItemCount(): Int = items.size
}