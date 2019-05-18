package br.com.renan.resourcetest.util

import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

private val formatterResponse = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
private val formatterNormalDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

fun formatNormalDate(date: String?): String {
    date?.let {
        try {
            val mDate = formatterResponse.parse(date)
            return formatterNormalDate.format(mDate)
        } catch (e: Exception) {
            return formatterNormalDate.format(formatterNormalDate.parse(date))
        }
    }
    return ""
}

fun formatCurrency(balance: Double): String {
    val nf = NumberFormat.getCurrencyInstance()
    return nf.format(balance)
}

fun addMask(textToFormat: String, mask: String): String {
    var formatter = ""
    var i = 0
    // vamos iterar a mascara, para descobrir quais caracteres vamos adicionar e quando...
    for (m in mask.toCharArray()) {
        if (m != '#') { // se não for um #, vamos colocar o caracter informado na máscara
            formatter += m
            continue
        }
        // Senão colocamos o valor que será formatado
        try {
            formatter += textToFormat[i]
        } catch (e: Exception) {
            break
        }

        i++
    }
    return formatter
}