package com.martin.weatherestonia.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

object Words {
    private val tensNames = arrayOf(
        "",
        " ten",
        " twenty",
        " thirty",
        " forty",
        " fifty",
        " sixty",
        " seventy",
        " eighty",
        " ninety"
    )
    private val numNames = arrayOf(
        "",
        " one",
        " two",
        " three",
        " four",
        " five",
        " six",
        " seven",
        " eight",
        " nine",
        " ten",
        " eleven",
        " twelve",
        " thirteen",
        " fourteen",
        " fifteen",
        " sixteen",
        " seventeen",
        " eighteen",
        " nineteen"
    )

    fun convertLessThanOneThousand(number: Int): String? {
        try {


            var number = number
            var soFar: String
            if (number % 100 < 20|| number % 100< -20) {
                soFar = numNames[number % 100]
                number /= 100
            } else {
                soFar = numNames[number % 10]
                number /= 10
                soFar = tensNames[number % 10] + soFar
                number /= 10
            }
            return if (number == 0) soFar else numNames[number] + " hundred" + soFar

        } catch (e: Exception) {
            print(e.printStackTrace())
        }

        return null
    }


}


@BindingAdapter("android:showWord")
fun showDayOfWeek(view: TextView, minMax: Double) {
    val value = minMax.toInt()
    view.text = Words.convertLessThanOneThousand(value)
}
