package cn.howxu.accounting_book.activity

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cn.howxu.accounting_book.adapter.ReportListAdapter
import cn.howxu.accounting_book.viewModel.MainViewModel
import cn.howxu.android_demo_accounting_book.databinding.ActivityReportBinding

class ReportActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReportBinding
    private val reportViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        initRecycleView()
        setVariable()
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener {
            finish()
        }
    }

    private fun initRecycleView() {
        binding.reportView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.reportView.adapter = ReportListAdapter(reportViewModel.loadBudgets())
        binding.reportView.isNestedScrollingEnabled = false
    }
}