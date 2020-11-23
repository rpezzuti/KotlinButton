package rhett.pezzuti.kotlinbutton.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _eventPressButton = MutableLiveData<Boolean>()
    val eventPressButton : LiveData<Boolean>
        get() =  _eventPressButton

    init {
        _eventPressButton.value = false
    }

    fun onPressButton(){
        _eventPressButton.value = true
    }
    fun onDonePressButton(){
        _eventPressButton.value = false
    }

















}