package com.example.marmoshka


class ValueItem(var index: Int, val number: Int, var isUsed: Boolean)

class ValuesChooser(private val valuesFiller: ValuesFiller) {
    private var values = (1..45).toList().mapIndexed{
        index, i -> ValueItem(index, i, false)
    }

    init {
        valuesFiller.onRowFilled {
            values.forEach {
                it.isUsed = false;
            }

            onUpdateUICallback?.let { it1 -> it1(null) }
        }
    }

    private var onUpdateUICallback: ((Int?) -> Unit)? = null;

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
        onUpdateUICallback?.let { it(valueItem.index) }
    }

    fun onUpdateUI(function: (index: Int?) -> Unit) {
        onUpdateUICallback = function;
    }

    fun shuffle() {
        values = values.shuffled().mapIndexed{
            index, value -> ValueItem(index, value.number, value.isUsed)
        }
    }
}