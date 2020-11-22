package rhett.pezzuti.kotlinbutton.sound

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rhett.pezzuti.kotlinbutton.home.HomeViewModel

class SoundViewModelFactory : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SoundViewModel::class.java)) {
            return SoundViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}