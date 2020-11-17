package rhett.pezzuti.kotlinbutton.sound

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import rhett.pezzuti.kotlinbutton.R
import rhett.pezzuti.kotlinbutton.databinding.FragmentSoundBinding

class SoundFragment : Fragment() {

    private lateinit var binding: FragmentSoundBinding
    private lateinit var viewModel: SoundViewModel
    private lateinit var viewModelFactory: SoundViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_sound,
                container,
                false
        )


        viewModelFactory = SoundViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(SoundViewModel::class.java)






        return binding.root
    }
}