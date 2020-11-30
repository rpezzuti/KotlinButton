package rhett.pezzuti.kotlinbutton.picture

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import rhett.pezzuti.kotlinbutton.R
import rhett.pezzuti.kotlinbutton.database.PresetDatabase
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

        val arguments = PictureFragmentArgs.fromBundle(requireArguments())
        val application = requireNotNull(this.activity).application
        val dataSource = PresetDatabase.getInstance(application).presetDatabaseDao

        viewModelFactory = PictureViewModelFactory(arguments.presetId,dataSource)
        pictureViewModel = ViewModelProvider(this, viewModelFactory).get(PictureViewModel::class.java)
        binding.pictureViewModelXML = pictureViewModel

        binding.lifecycleOwner = this

        binding.presetID.text = arguments.presetId.toString()

        pictureViewModel.eventNagivateForward.observe(viewLifecycleOwner, {event ->
            if (event == true){
                findNavController().navigate(PictureFragmentDirections.actionPictureFragmentToSoundFragment(0L))
                pictureViewModel.onDoneNavigating()
            }
        })

        pictureViewModel.navigatePreset.observe(viewLifecycleOwner, { preset ->
           if (preset.picture != -1){
               findNavController().navigate(PictureFragmentDirections.actionPictureFragmentToSoundFragment(arguments.presetId))
               // Reset the navigation preset?
           }
        })


        return binding.root
    }


}