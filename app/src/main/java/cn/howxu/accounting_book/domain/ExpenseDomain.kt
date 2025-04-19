package cn.howxu.accounting_book.domain

import android.os.Parcel
import android.os.Parcelable

/**
 * @author: HowXu
 *
 * 在 Android 中，Parcelable 是 android.os 包下的一个接口（interface），用于高性能的序列化机制，允许对象在进程间（如 Activity、Service 等组件之间）高效传输。
 * 实现 Parcelable 的对象可以通过 Intent 或 Bundle 传递，比 Serializable 更高效（后者基于反射，性能较低）。
 * 常用于跨进程通信（如 AIDL）或保存/恢复组件状态（如 onSaveInstanceState）。
 * writeToParcel(Parcel dest, int flags)：将对象字段写入 Parcel（二进制容器）。
 * describeContents()：返回特殊文件描述符（通常返回 0）。
 * 静态 CREATOR 字段：用于从 Parcel 重建对象。
 * 对比 Serializable：
 * Parcelable 是 Android 专用，手动实现，性能高；
 * Serializable 是 Java 标准，自动序列化，但效率低
 */
data class ExpenseDomain(
    val title:String="",
    val price:Double=0.0,
    val pic:String="",
    val time:String=""
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeDouble(price)
        parcel.writeString(pic)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ExpenseDomain> {
        override fun createFromParcel(parcel: Parcel): ExpenseDomain {
            return ExpenseDomain(parcel)
        }

        override fun newArray(size: Int): Array<ExpenseDomain?> {
            return arrayOfNulls(size)
        }
    }

}
