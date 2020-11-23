package rhett.pezzuti.kotlinbutton.text

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import rhett.pezzuti.kotlinbutton.R
import rhett.pezzuti.kotlinbutton.databinding.TextFragmentBinding

class TextFragment : Fragment() {

    private lateinit var viewModel: TextViewModel
    private lateinit var viewModelFactory: TextViewModelFactory
    private lateinit var binding: TextFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.text_fragment,
            container,
            false
        )

        /** ViewModel Pipes **/
        viewModelFactory = TextViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(TextViewModel::class.java)
        binding.textViewModelXML = viewModel
        binding.lifecycleOwner = this



        viewModel.eventSaveMessage.observe(viewLifecycleOwner, {event ->
            if (event == true){
                this.findNavController().navigate(TextFragmentDirections.actionTextFragmentToPictureFragment())
                viewModel.onDoneSaveMessage()
            }
        })






        return binding.root
    }







}