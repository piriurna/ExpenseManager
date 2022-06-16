package com.blankwhite.expensemanager.utils.currency

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.blankwhite.expensemanager.utils.toBigDecimal
import java.lang.ref.WeakReference
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


class CurrencyTextWatcher(editText: EditText?, locale: Locale?, private val listener: OnCurrencyTextChangeListener) : TextWatcher {

    private var manuallyChanged = false

    private var locale: Locale = locale?:Locale.getDefault()

    private var editTextWeakReference = WeakReference<EditText>(editText)


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(editable: Editable?) {
        if(manuallyChanged) return

        val editText = editTextWeakReference.get()

        val locale = Locale.getDefault()

        val amount: BigDecimal = editable.toString().toBigDecimal(locale)
        val formatted: String = NumberFormat.getCurrencyInstance(locale).format(amount)

        manuallyChanged = true
        listener.onCurrencyTextChanged(amount)
        editText?.setText(formatted);
        editText?.setSelection(formatted.length);
        manuallyChanged = false
    }


}