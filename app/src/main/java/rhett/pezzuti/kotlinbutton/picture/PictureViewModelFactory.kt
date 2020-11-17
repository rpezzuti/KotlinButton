package rhett.pezzuti.kotlinbutton.picture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rhett.pezzuti.kotlinbutton.home.HomeViewModel

class PictureViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PictureViewModel::class.java)) {
            return PictureViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }



}