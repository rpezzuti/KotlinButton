package rhett.pezzuti.kotlinbutton.home

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import rhett.pezzuti.kotlinbutton.R
import rhett.pezzuti.kotlinbutton.database.ButtonPreset

@BindingAdapter("presetMessage")
fun TextView.setPresetMessage(item: ButtonPreset?){
    item?.let {
        text = item.text
    }
}

@BindingAdapter("presetImage")
fun ImageView.setPresetImage(item: ButtonPreset?){
    item?.let {
        setImageResource(when (item.picture){
            1 -> R.drawable.heart
            2 -> R.drawable.awesome
            3 -> R.drawable.i_miss_you
            4 -> R.drawable.im_a_ball
            5 -> R.drawable.sorry
            6 -> R.drawable.sprout
            7 -> R.drawable.stop
            8 -> R.drawable.tongue
            9 -> R.drawable.troll
            10 -> R.drawable.yes
            else -> R.drawable.ic_launcher_background
        })
    }
}

@BindingAdapter("presetSound")
fun TextView.setPresetSound(item: ButtonPreset?){
    item?.let {
        text = (when (item.sound){
            1 -> "Meep Merp"
            2 -> "Chimes"
            3 -> "Fart"
            4 -> "TeeHee"
            else -> "Sound File Not Chosen"
        })
    }
}