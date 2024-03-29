package com.example.marmoshka

const val ROWS_NUMBERS = 6;

class RowWrapper {
    private val values: MutableList<Int?> = (1..6).map { null }.toMutableList()
    private var currentIndex: Int = 0;

    fun addValue(value: Int) {
      values[currentIndex] = value;
      currentIndex++;
    }

    fun getValues(): List<Int?> {
        return values;
    }

    fun isFilled(): Boolean {
        return currentIndex == 6
    }
}

class ValuesFiller {
    private val rows = (1..6).map { RowWrapper() }
    private var currentRowIndex: Int = 0;
    private var onUpdateUICallback: ((Int) -> Unit)? = null;
    private var onRowFilledCallback: (() -> Unit)? = null;

    fun addValue(value: Int) {
        val previousRowIndex = currentRowIndex;
        if (currentRowIndex > 5) {
            return
        }

        rows[currentRowIndex].addValue(value)

        if (rows[currentRowIndex].isFilled()) {
            currentRowIndex++
            onRowFilledCallback?.let { it() }
        }

        onUpdateUICallback?.let { it(previousRowIndex) }
    }

    fun getRow(position: Int): RowWrapper {
        return rows[position]
    }

    fun onUpdateUI(function: (Int) -> Unit) {
        onUpdateUICallback = function;
    }

    fun onRowFilled(function: () -> Unit) {
        onRowFilledCallback = function;
    }

}