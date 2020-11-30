package rhett.pezzuti.kotlinbutton.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import rhett.pezzuti.kotlinbutton.R
import rhett.pezzuti.kotlinbutton.database.PresetDatabase
import rhett.pezzuti.kotlinbutton.databinding.FragmentHomeBinding
import timber.log.Timber


private const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    /** Variables **/
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var viewModelFactory: HomeViewModelFactory

    /**
     *                             TODO BLOCK
     * - I'd be awesome to have each variable of the preset (teXt, sound, and picture) be saved immediately
     *   after navigating away from the fragment. Could initialize the new preset when the make preset
     *   button is called, and then update the preset as the user navigates through the fragments.
     *
     * - Create the RecyclerView to hold past presets in the database, for the lols.
     *
     * - Create a View object that displays the current preset, and make it clear to the user
     *   that it is the current preset and not part of the past presets in the RecyclerView.
     *
     * - Make the fragment where the user pushes the button a little prettier
     *          • Edit the background?
     *          • Add a joke?
     *          • IDFK
     *
     */



    /** onCreate **/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        Timber.i("onCreateView called")

        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_home,
                container,
                false
        )

        /** Database Pipes **/
        val application = requireNotNull(this.activity).application
        val dataSource = PresetDatabase.getInstance(application).presetDatabaseDao

        /** ViewModel Pipes **/
        viewModelFactory = HomeViewModelFactory(dataSource, application)
        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        /** Set binding variables **/
        binding.homeViewModelXML = homeViewModel

        /** Pipes **/
        binding.lifecycleOwner = this


        /** Recycler View Pipes **/
        val adapter = PresetAdapter()
        binding.recyclerView.adapter = adapter
        homeViewModel.presets.observe(viewLifecycleOwner, {
            it?.let {
                adapter.data = it
            }
        })

        /** Navigation Observers **/
        homeViewModel.navigateToSetText.observe(viewLifecycleOwner, { event ->
            if (event == true){
                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTextFragment2())
                homeViewModel.doneChangingText()
            }
        })

        homeViewModel.navigateToButton.observe(viewLifecycleOwner, { event ->
            if (event == true) {
                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToButtonFragment())
                homeViewModel.doneLaunching()
            }
        })

        /** SnackBar Observer **/
        homeViewModel.showSnackBarEvent.observe(viewLifecycleOwner, { it ->
            if (it == true){
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT
                ).show()
                homeViewModel.doneShowingSnackBar()
            }
        })

        return binding.root
    }


    /** Lifecycle Methods **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate() called")
    }
    override fun onStart() {
        super.onStart()
        Timber.i("onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Timber.i("onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Timber.i("onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Timber.i("onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy() called")
    }
}

