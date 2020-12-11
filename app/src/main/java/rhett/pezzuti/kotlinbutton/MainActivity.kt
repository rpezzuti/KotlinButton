package rhett.pezzuti.kotlinbutton


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import rhett.pezzuti.kotlinbutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//    private lateinit var drawerLayout: DrawerLayout
//    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create binding object
//        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
//            this, R.layout.activity_main
//        )
//
//        // Create navController
//        // This is throwing an error, saying that there is no nav controller to find.
//        val navController = this.findNavController(R.id.myNavHostFragment)
//
//        val navHostFragment =
//            supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment
//        val navController: NavController = navHostFragment.navController
//
//
//        drawerLayout = binding.drawerLayout
//        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
//        NavigationUI.setupWithNavController(binding.navView, navController)
//        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)



    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = this.findNavController(R.id.myNavHostFragment)
//        return NavigationUI.navigateUp(navController, drawerLayout)
//    }

}