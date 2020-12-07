package rhett.pezzuti.kotlinbutton.result

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
import rhett.pezzuti.kotlinbutton.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private lateinit var resultViewModel: ResultViewModel
    private lateinit var resultViewModelFactory: ResultViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_result,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val arguments = ResultFragmentArgs.fromBundle(requireArguments())
        val database = PresetDatabase.getInstance(application).presetDatabaseDao
        resultViewModelFactory = ResultViewModelFactory(arguments.presetKey, database)
        resultViewModel = ViewModelProvider(this, resultViewModelFactory).get(ResultViewModel::class.java)
        binding.resultViewModelXML = resultViewModel
        binding.lifecycleOwner = this


        resultViewModel.navigateToHome.observe(viewLifecycleOwner, { event ->
            if (event == true){
                findNavController().navigate(ResultFragmentDirections.actionResultFragmentToHomeFragment())
                resultViewModel.doneNavigatingHome()
            }
        })


        return binding.root
    }
}