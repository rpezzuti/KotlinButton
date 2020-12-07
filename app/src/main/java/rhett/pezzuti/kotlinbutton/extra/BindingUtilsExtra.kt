package rhett.pezzuti.kotlinbutton.text

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import rhett.pezzuti.kotlinbutton.R
import rhett.pezzuti.kotlinbutton.database.ButtonPreset

@BindingAdapter("presetMessage")
fun TextView.setPresetMessage(item: ButtonPreset?) {
    item?.let {
        text = item.text
    }
}

//@BindingAdapter("presetImage")
//fun ImageView.setPresetMessage(item: ButtonPreset?) {
//    item?.let {
//        setImageResource(when (item.picture) {
//            0 -> R.
//        })
//    }
//}