package rhett.pezzuti.kotlinbutton.home

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar
import java.util.*

private const val TAG = "HomeViewModel"

class HomeViewModel : ViewModel() {

    /** Encapsulated Variables **/
    private val _number = MutableLiveData<Int>()
    val number : LiveData<Int>
        get() = _number

    private val _eventChangeText = MutableLiveData<Boolean>()
    val eventChangeText : LiveData<Boolean>
        get() = _eventChangeText

    private val _eventChangeSound = MutableLiveData<Boolean>()
    val eventChangeSound : LiveData<Boolean>
        get() = _eventChangeSound

    private val _eventChangePicture = MutableLiveData<Boolean>()
    val eventChangePicture : LiveData<Boolean>
        get() = _eventChangePicture


    /** Companion Object **/
    companion object {
        private const val TAG = "HomeViewModel"
    }


    /** Init Block **/
    init {
        Log.i(TAG, "Init block started")
        _number.value = 0
        _eventChangeText.value = false
        _eventChangeSound.value = false
        _eventChangePicture.value = false
    }



    /** Navigation Methods **/
    fun onChangeText(){
        _eventChangeText.value = true
    }

    fun onChangeSound(){
        _eventChangeSound.value = true
    }

    fun onChangePicture(){
        _eventChangePicture.value = true
    }



}
