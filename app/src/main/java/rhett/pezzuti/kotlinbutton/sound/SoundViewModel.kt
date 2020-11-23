package rhett.pezzuti.kotlinbutton.sound

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SoundViewModel : ViewModel(){

    private val _eventGoHome = MutableLiveData<Boolean>()
    val eventGoHome : LiveData<Boolean>
        get() = _eventGoHome

    init {
        _eventGoHome.value = false
    }

    fun goHome(){
        _eventGoHome.value = true
    }
    fun doneGoingHome(){
        _eventGoHome.value = false
    }

}