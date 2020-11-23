package rhett.pezzuti.kotlinbutton.picture

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rhett.pezzuti.kotlinbutton.R
import timber.log.Timber

class PictureViewModel : ViewModel(){


    private var chosenPicture: Int = 0

    /** Encapsulated Variables **/
    private val _eventNavigateForward = MutableLiveData<Boolean>()
    val eventNagivateForward : LiveData<Boolean>
        get() = _eventNavigateForward

    /** Init Block **/
    init {
        _eventNavigateForward.value = false
    }

    fun picture(view: View){
        when (view.id){
            R.id.image_1 -> chosenPicture = 1
            R.id.image_2 -> chosenPicture = 1
            R.id.image_3 -> chosenPicture = 1
            R.id.image_4 -> chosenPicture = 1
            R.id.image_5 -> chosenPicture = 1
            R.id.image_6 -> chosenPicture = 1
            R.id.image_7 -> chosenPicture = 1
            R.id.image_8 -> chosenPicture = 1
            R.id.image_9 -> chosenPicture = 1
            R.id.image_10 -> chosenPicture = 1
        }
    }


    /** Navigation Methods **/
    fun onNavigate(){
        _eventNavigateForward.value = true
    }
    fun onDoneNavigating(){
        _eventNavigateForward.value = false
    }


    override fun onCleared() {
        super.onCleared()
        Timber.i("onCleared() called")
    }
}