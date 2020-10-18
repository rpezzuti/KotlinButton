package rhett.pezzuti.kotlinbutton

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import rhett.pezzuti.kotlinbutton.data.CurrentData
import rhett.pezzuti.kotlinbutton.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private var currentButton: CurrentData = CurrentData("default text", "default sound", "default picture")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home,
                container,
                false
        )

        binding.currentDataXML = currentButton

        binding.btnPicture.setOnClickListener{
            // Navigation.findNavController(this).navigate(R.id.action_homeFragment_to_pictureFragment)
        }

        return binding.root
    }
}

