package rhett.pezzuti.kotlinbutton.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import rhett.pezzuti.kotlinbutton.R
import rhett.pezzuti.kotlinbutton.databinding.FragmentGameBinding

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

        viewModelFactory = GameViewModelFactory()
        viewModel = ViewModelProvider(this,viewModelFactory).get(GameViewModel::class.java)
        binding.gameViewModelXML = viewModel
        binding.lifecycleOwner = this

        viewModel.eventPressButton.observe(viewLifecycleOwner, { event ->
            if (event == true){
                this.findNavController().navigate(GameFragmentDirections.actionButtonFragmentToResultFragment())
                viewModel.onDonePressButton()
            }
        })


        return binding.root
    }
}