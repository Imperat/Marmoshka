package com.example.marmoshka


data class ValueItem(val number: Int, var isUsed: Boolean)

class ValuesChooser(private val valuesFiller: ValuesFiller) {
    private val values = (1..45).toList().map {
        ValueItem(it, false);
    }

    init {
        valuesFiller.onRowFilled {
            values.forEach {
                it.isUsed = false;
            }

            onUpdateUICallback?.let { it1 -> it1() }
        }
    }

    private var onUpdateUICallback: (() -> Unit)? = null;

    fun getItemCount(): Int {
        return values.size;
    }

    fun getItem(position: Int): ValueItem {
        return values[position]
    }

    fun trigger(valueItem: ValueItem) {
        if (valueItem.isUsed) {
            return;
        }

        valueItem.isUsed = true;
        valuesFiller.addValue(valueItem.number)
        onUpdateUICallback?.let { it() }
    }

    fun onUpdateUI(function: () -> Unit) {
        onUpdateUICallback = function;
    }
}