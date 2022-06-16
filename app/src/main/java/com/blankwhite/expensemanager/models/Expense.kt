package com.blankwhite.expensemanager.models

import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.RequiresApi
import java.sql.Date
import java.sql.Timestamp
import java.time.LocalDate
import java.util.*

class Expense(
    val name: String?,
    val date: java.util.Date?,
    val category: Category?,
    val value: Double
) : Parcelable{
    @RequiresApi(Build.VERSION_CODES.O)
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        java.util.Date(parcel.readLong()),
        parcel.readParcelable(Category::class.java.classLoader),
        parcel.readDouble()
    ) {
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeLong(date!!.time)
        parcel.writeParcelable(category, flags)
        parcel.writeDouble(value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Expense> {
        override fun createFromParcel(parcel: Parcel): Expense {
            return Expense(parcel)
        }

        override fun newArray(size: Int): Array<Expense?> {
            return arrayOfNulls(size)
        }
    }

}