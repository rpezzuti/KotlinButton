package rhett.pezzuti.kotlinbutton.details

import android.media.MediaPlayer
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
import rhett.pezzuti.kotlinbutton.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var detailsViewModel: DetailsViewModel
    private lateinit var detailsViewModelFactory: DetailsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = PresetDatabase.getInstance(application).presetDatabaseDao
        val arguments = DetailsFragmentArgs.fromBundle(requireArguments())
        detailsViewModelFactory = DetailsViewModelFactory(arguments.presetKey, dataSource)
        detailsViewModel = ViewModelProvider(this, detailsViewModelFactory).get(DetailsViewModel::class.java)
        binding.detailsViewModel = detailsViewModel
        binding.lifecycleOwner = this

        binding.detailsPresetId.text = "PresetID: ${arguments.presetKey}"


        /** Fix this so it uses data binding lol **/
        binding.detailsPlaySound.setOnClickListener {
            detailsViewModel.getPreset().value?.let {
                    preset -> playSound(preset.sound)
            }
        }

        detailsViewModel.navigateToButtonFragment.observe(viewLifecycleOwner, { event ->
            if (event == true){
                findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentToGameFragment(arguments.presetKey))
                detailsViewModel.doneNavigatingToButton()
            }
        })

        return binding.root
    }

    private
    fun playSound(sound: Int){
        when (sound){
            1 -> MediaPlayer.create(this.context, R.raw.meepmerp).start()
            2 -> MediaPlayer.create(this.context, R.raw.chimes).start()
            3 -> MediaPlayer.create(this.context, R.raw.fart).start()
            4 -> MediaPlayer.create(this.context, R.raw.teehee).start()
        }
    }
}