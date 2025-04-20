package cn.howxu.accounting_book.repo

import cn.howxu.accounting_book.domain.ExpenseDomain
import cn.howxu.accounting_book.domain.BudgetDomain

/**
 * @description: TODO
 * @author: HowXu
 * @date:  22:58
 */
class MainRepository {
    val items = mutableListOf(
        ExpenseDomain("蜜雪冰城", 5.00, "img1", "17 jun 2024 19:15"),
        ExpenseDomain("麦当当", 35.00, "img2", "17 jun 2024 19:15"),
        ExpenseDomain("网易BUFF交易", 19580.00, "img3", "17 jun 2024 19:15"),
        ExpenseDomain("Steam商城交易", 268.00, "steam", "17 jun 2024 19:15"),
        ExpenseDomain("Steam商城交易", 168.00, "steam", "17 jun 2024 19:15"),
        ExpenseDomain("Steam商城交易", 38.40, "steam", "17 jun 2024 19:15"),
        ExpenseDomain("Steam商城交易", 49.50, "steam", "17 jun 2024 19:15"),
        ExpenseDomain("Steam商城交易", 19.00, "steam", "17 jun 2024 19:15"),
        ExpenseDomain("网易BUFF交易", 105.99, "img3", "17 jun 2024 19:15"),
        ExpenseDomain("网易BUFF交易", 126.99, "img3", "17 jun 2024 19:15"),
        )
    val budgets = mutableListOf(
        BudgetDomain("信用卡",1200.0,17.8),
        BudgetDomain("房贷",4000.0,80.8),
        BudgetDomain("车贷",3000.0,45.9),
        BudgetDomain("车贷",3000.0,45.9),
        BudgetDomain("车贷",3000.0,45.9),
        BudgetDomain("车贷",3000.0,45.9),
        BudgetDomain("车贷",3000.0,45.9),
        BudgetDomain("车贷",3000.0,45.9)
    )
}