package rhett.pezzuti.kotlinbutton.text

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import rhett.pezzuti.kotlinbutton.R
import rhett.pezzuti.kotlinbutton.databinding.FragmentTextBinding

private const val TAG = "TextFragment"

class TextFragment : Fragment() {

    private lateinit var binding: FragmentTextBinding
    private lateinit var textViewModel: TextViewModel
    private lateinit var viewModelFactory: TextViewModelFactory

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView() called")
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_text,
                container,
                false
        )
        // TODO: Make the database
        binding.lifecycleOwner = this

        viewModelFactory = TextViewModelFactory()
        textViewModel = ViewModelProvider(this, viewModelFactory).get(TextViewModel::class.java)

        binding.textViewModelXML = textViewModel



        // TODO: Not make this a listener but preserve the view's message
        binding.btnSave.setOnClickListener {
            onSaveMessage()
        }

        textViewModel.eventNavForward.observe(viewLifecycleOwner, { event ->
            if (event == true){
                findNavController().navigate(
                        TextFragmentDirections.actionTextFragmentToPictureFragment(binding.etNewText.text.toString()))
            }
        })





        return binding.root
    }

    private
    fun onSaveMessage(){
        Log.i(TAG, "onSavePreset() called")
        textViewModel.onSave(binding.etNewText.text.toString())

        // TODO: Add the message to the database

        // TODO: Hide the keyboard
        // Hide the keyboard. taken from AboutMe in Lesson 2.
        // val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        // imm.hideSoftInputFromWindow(view.windowToken, 0)
        // requireActivity().findViewById(android.R.id.content)
    }
}