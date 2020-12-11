package rhett.pezzuti.kotlinbutton.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
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
     * - Add header to recycler view
     * - Add menu for settings & info about app and how to use it
     * - Play with permissions?
     * - Change the top of the home fragment layout to have text or something.
     *                • I.E. remove the 3 textView items and put something else there
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
        homeViewModel.showSnackBarEvent.observe(viewLifecycleOwner, { event ->
            if (event == true){
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT
                ).show()
                homeViewModel.doneShowingSnackBar()
            }
        })

        /** Define an Options Menu **/
        setHasOptionsMenu(true)
        return binding.root
    }

    /** Overflow Menu Methods **/
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId){
//            R.id.show_info_menu -> Snackbar.make(
//                requireActivity().findViewById(android.R.id.content),
//                "Info Menu Item Selected",
//                Snackbar.LENGTH_SHORT
//            ).show()
//            R.id.show_settings_menu -> Snackbar.make(
//                requireActivity().findViewById(android.R.id.content),
//                "Settings Menu Item Selected",
//                Snackbar.LENGTH_SHORT
//            ).show()
//        }
//        return super.onOptionsItemSelected(item)



        // This return statement works when the id of the menu item matches the id of the fragment in the nav graph.
        return NavigationUI.onNavDestinationSelected(item!!, requireView().findNavController())
                || super.onOptionsItemSelected(item)


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

