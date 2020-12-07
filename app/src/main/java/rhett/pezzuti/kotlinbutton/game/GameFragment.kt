package rhett.pezzuti.kotlinbutton.game

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
import rhett.pezzuti.kotlinbutton.databinding.FragmentGameBinding
import rhett.pezzuti.kotlinbutton.result.ResultFragmentArgs

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel
    private lateinit var viewModelFactory: GameViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_game,
                container,
                false
        )

        val application = requireNotNull(this.activity).application
        val database = PresetDatabase.getInstance(application).presetDatabaseDao
        val arguments = GameFragmentArgs.fromBundle(requireArguments())

        viewModelFactory = GameViewModelFactory(arguments.presetKey, database)
        viewModel = ViewModelProvider(this,viewModelFactory).get(GameViewModel::class.java)
        binding.gameViewModelXML = viewModel
        binding.lifecycleOwner = this




        viewModel.eventPressButton.observe(viewLifecycleOwner, { event ->
            if (event == true){
                this.findNavController().navigate(GameFragmentDirections.actionButtonFragmentToResultFragment(arguments.presetKey))
                playSound(arguments.presetSound)
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