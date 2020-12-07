package rhett.pezzuti.kotlinbutton.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
     * - Implement recyclerView onClick to use the selected preset for the button game.
     *              - When on details, use data binding to play the sound instead of onCLick listener.
     *
     * - Make the fragment where the user pushes the button a little prettier
     *          • Edit the background?
     *          • Add a joke?
     *          • IDFK
     *
     * - Add cool fade animation to when you launch the button fragment from the details fragment
     *
     * - Improve recycler view click listener to also add the preset sound ID to the arguments.
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

        /** Allows Data Binding to Observe LiveData with the lifecycle of this Fragment **/
        binding.lifecycleOwner = this


        /** Recycler View Pipes **/
        val adapter = PresetAdapter(ButtonPresetListener {
            // presetId -> Toast.makeText(context, "ID: $presetId", Toast.LENGTH_SHORT).show()
            presetId, presetSound -> findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailsFragment(presetId, presetSound))
        })
        binding.recyclerView.adapter = adapter
        homeViewModel.presets.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitList(it)
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
                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToButtonFragment(0L, 0))
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

