package rhett.pezzuti.kotlinbutton.picture

import android.graphics.Picture
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import rhett.pezzuti.kotlinbutton.R
import rhett.pezzuti.kotlinbutton.databinding.FragmentPictureBinding


class PictureFragment : Fragment() {

    private lateinit var binding: FragmentPictureBinding
    private lateinit var viewModelFactory: PictureViewModelFactory
    private lateinit var pictureViewModel: PictureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_picture,
                container, false
        )

        viewModelFactory = PictureViewModelFactory()
        pictureViewModel = ViewModelProvider(this, viewModelFactory).get(PictureViewModel::class.java)
        binding.pictureViewModelXML = pictureViewModel



        return binding.root
    }


}