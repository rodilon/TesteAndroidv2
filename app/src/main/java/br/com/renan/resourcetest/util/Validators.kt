package br.com.renan.resourcetest.util

import android.text.TextUtils
import br.com.caelum.stella.validation.CPFValidator
import br.com.caelum.stella.validation.InvalidStateException

fun validateCPF(text: String) : Boolean {
    try {
        val cpfValidator = CPFValidator()
        cpfValidator.assertValid(text)
    } catch (e: InvalidStateException) {
        return false
    }
    return true
}

fun validateEMAIL(text: String): Boolean {
    return !TextUtils.isEmpty(text) && android.util.Patterns.EMAIL_ADDRESS.matcher(text).matches()
}