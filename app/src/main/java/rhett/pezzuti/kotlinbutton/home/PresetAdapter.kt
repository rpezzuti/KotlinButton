package rhett.pezzuti.kotlinbutton.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import rhett.pezzuti.kotlinbutton.R
import rhett.pezzuti.kotlinbutton.database.ButtonPreset

class PresetAdapter : RecyclerView.Adapter<PresetAdapter.ViewHolder>() {

    /** this is the data to be displayed, and the setter below is used to tell the RV when data has changed. **/
    var data = listOf<ButtonPreset>()
        set(value) {
            field = value
            notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val res = itemView.context.resources

        private val image: ImageView = itemView.findViewById(R.id.button_preset_image)
        private val message: TextView = itemView.findViewById(R.id.button_preset_text)
        private val sound: TextView = itemView.findViewById(R.id.button_preset_sound)

        fun bind(item: ButtonPreset){
            image.setImageResource(R.drawable.ic_launcher_background)

            when (item.picture){
                1 -> image.setImageResource(R.drawable.heart)
                2 -> image.setImageResource(R.drawable.awesome)
                3 -> image.setImageResource(R.drawable.i_miss_you)
                4 -> image.setImageResource(R.drawable.im_a_ball)
                5 -> image.setImageResource(R.drawable.sorry)
                6 -> image.setImageResource(R.drawable.sprout)
                7 -> image.setImageResource(R.drawable.stop)
                8 -> image.setImageResource(R.drawable.tongue)
                9 -> image.setImageResource(R.drawable.troll)
                10 -> image.setImageResource(R.drawable.yes)
            }
            message.text = item.text
            sound.text = item.sound.toString()
        }


        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                /** we're inflating a new view and returning it inside of a holder **/
                val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.button_preset_view, parent, false)
                return ViewHolder(view)
            }
        }


    }
}