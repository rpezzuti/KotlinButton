package rhett.pezzuti.kotlinbutton.extra

import android.view.View
import androidx.navigation.findNavController
import rhett.pezzuti.kotlinbutton.home.HomeFragmentDirections

/** Private view methods **/
//private
//fun setListeners(){
//    val clickableButtons: List<View> =
//            listOf(
//                    binding.btnPicture, binding.btnSound, binding.btnText, binding.btnSnackbar
//            )
//
//    for (v in clickableButtons){
//        v.setOnClickListener{
//            btnNavigate(it)
//        }
//    }
//
//}
//
//private
//fun btnNavigate(view: View) {
//    when (view.id) {
//        binding.btnPicture.id -> view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPictureFragment())
//        binding.btnSound.id -> view.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToButtonFragment())
//        binding.btnText.id -> view.findNavController().navigate(
//                HomeFragmentDirections.actionHomeFragmentToTextFragment(
//                        currentButton.text,
//                        currentButton.sound,
//                        currentButton.picture
//                )
//        )
//        binding.btnSnackbar.id -> showSnackbar(binding.btnSnackbar)
//    }
//}