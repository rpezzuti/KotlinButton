package rhett.pezzuti.kotlinbutton

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import rhett.pezzuti.kotlinbutton.database.ButtonVariables
import rhett.pezzuti.kotlinbutton.databinding.FragmentTextBinding

class TextFragment : Fragment() {

    private lateinit var binding: FragmentTextBinding


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_text,
                container,
                false
        )
        setText()
        return binding.root
    }

    private
    fun setText() {
        val args = TextFragmentArgs.fromBundle(requireArguments())
        var currentButton: ButtonVariables = ButtonVariables(args.text, args.picture, args.sound)
        binding.currentDataXML = currentButton
    }

}