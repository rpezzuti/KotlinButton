package rhett.pezzuti.kotlinbutton.sound

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
import rhett.pezzuti.kotlinbutton.databinding.FragmentSoundBinding

class SoundFragment : Fragment() {

    private lateinit var binding: FragmentSoundBinding
    private lateinit var soundViewModel: SoundViewModel
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

        val application = requireNotNull(this.activity).application
        val dataSource = PresetDatabase.getInstance(application).presetDatabaseDao
        val arguments = SoundFragmentArgs.fromBundle(requireArguments())


        viewModelFactory = SoundViewModelFactory(arguments.presetId, dataSource)
        soundViewModel = ViewModelProvider(this, viewModelFactory).get(SoundViewModel::class.java)
        binding.soundViewModelXML = soundViewModel
        binding.lifecycleOwner = this

        /** Sets TextView to show the presetID **/
        binding.textPresetKey.text = arguments.presetId.toString()

        /** Navigation Observer **/
        soundViewModel.eventGoHome.observe(viewLifecycleOwner, { event ->
            if (event == true) {
                this.findNavController().navigate(SoundFragmentDirections.actionSoundFragmentToHomeFragment())
                soundViewModel.doneGoingHome()
            }
        })

        /** MediaPlayer Observer **/
        soundViewModel.sound.observe(viewLifecycleOwner, {
            playSound(it)
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