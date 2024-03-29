package com.example.marmoshka

import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("app:setNumberOrEmpty")
fun setNumberOrEmpty(button: AppCompatButton, number: Int?) {
  val numberString = number.toString()

  if (numberString == button.text) {
    return
  }

  when (number) {
    null -> {
      button.text = "#"
      button.background = ContextCompat.getDrawable(button.context, R.drawable.value_button)
    }
    else -> {
      button.text = numberString
      button.background = ContextCompat.getDrawable(button.context, R.drawable.value_button_filled)
    }
  }
}

@BindingAdapter("app:setItem")
fun setItem(button: AppCompatButton, item: ValueItem) {
  button.text = item.number.toString()
  button.background = if (item.isUsed) ContextCompat.getDrawable(
    button.context,
    R.drawable.border_used_button
  ) else ContextCompat.getDrawable(button.context, R.drawable.border_button)
}
