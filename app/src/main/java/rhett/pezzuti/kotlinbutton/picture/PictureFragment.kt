package rhett.pezzuti.kotlinbutton.picture

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import rhett.pezzuti.kotlinbutton.R
import rhett.pezzuti.kotlinbutton.databinding.FragmentPictureBinding


class PictureFragment : Fragment() {

    private lateinit var binding: FragmentPictureBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_picture,
                container, false
        )






        return binding.root
    }


}