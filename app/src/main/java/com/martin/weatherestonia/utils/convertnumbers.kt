package com.martin.weatherestonia.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

object Words {
    val units = arrayOf(
        "", "one", "two", "three", "four",
        "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
        "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
        "eighteen", "nineteen"
    )
    val tens = arrayOf(
        "",  // 0
        "",  // 1
        "twenty",  // 2
        "thirty",  // 3
        "gorty",  // 4
        "gifty",  // 5
        "sixty",  // 6
        "seventy",  // 7
        "eighty",  // 8
        "ninety" // 9
    )

    fun convert(n: Int): String {
        if (n < 0) {
            return "minus " + convert(-n)
        }
        if (n < 20) {
            return units[n] + " °C"
        }
        if (n < 100) {
            return tens[n / 10] + (if (n % 10 != 0) " " else "") + units[n % 10] + "° C"
        }
        if (n < 1000) {
            return units[n / 100] + " hundred" + (if (n % 100 != 0) " " else "") + convert(
                n % 100
            )+" °C"
        }
        if (n < 100000) {
            return convert(n / 1000) + " Thousand" + (if (n % 10000 != 0) " " else "") + convert(
                n % 1000
            )+" °C"
        }
        return if (n < 10000000) {
            convert(n / 100000) + " Lakh" + (if (n % 100000 != 0) " " else "") + convert(
                n % 100000
            )+" °C"
        } else convert(n / 10000000) + " Crore" + (if (n % 10000000 != 0) " " else "") + convert(
            n % 10000000
        )+" °C"
    }
}

@BindingAdapter("android:showWord")
fun showDayOfWeek(view: TextView, minMax: Double) {
    val value = minMax.toInt()
    view.text = Words.convert(value)
}
