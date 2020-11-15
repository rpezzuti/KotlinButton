package rhett.pezzuti.kotlinbutton.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import rhett.pezzuti.kotlinbutton.R
import rhett.pezzuti.kotlinbutton.database.ButtonVariables
import rhett.pezzuti.kotlinbutton.databinding.FragmentHomeBinding
import java.util.*

private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    /** Variables **/
    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var viewModelFactory: HomeViewModelFactory



    /** onCreate **/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Log.i(TAG, "onCreateView called")

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home,
                container,
                false
        )

        /** ViewModel Pipes **/
        viewModelFactory = HomeViewModelFactory()
        viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        /** Set binding variables **/
        binding.buttonVariablesXML = ButtonVariables()
        binding.homeViewModelXML  = viewModel

        return binding.root
    }





    private
    fun showSnackbar(view: View) {
        val message = Locale.JAPAN
        Snackbar.make(view, "$message", Snackbar.LENGTH_SHORT).show()
    }
}

