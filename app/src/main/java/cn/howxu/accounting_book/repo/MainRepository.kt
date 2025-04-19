package cn.howxu.accounting_book.repo

import cn.howxu.accounting_book.domain.ExpenseDomain

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
        ExpenseDomain("Steam商城交易", 268.00, "img4", "17 jun 2024 19:15"),
        )
}