package org.wit.couries.ui.client

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.wit.couries.models.CouriesManager
import org.wit.couries.models.CouriesModel

class ClientViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is client Fragment"
    }
    val text: LiveData<String> = _text

    private val status = MutableLiveData<Boolean>()

    val observableStatus: LiveData<Boolean>
        get() = status

    fun addCouries(couriesInd: CouriesModel) {
        status.value = try {
            CouriesManager.create(couriesInd)
            true
        } catch (e: IllegalArgumentException) {
            false
        }

    }

    fun getCouries(index:Int):CouriesModel?{
        return CouriesManager.getOne(index)
    }

    private val couriesList = MutableLiveData<List<CouriesModel>>()

    val observableCouriesList: LiveData<List<CouriesModel>>

    get() = couriesList

    init{
        load()
    }

    fun load(){
        couriesList.value= CouriesManager.findAll()
    }

//    try {
//        CouriesManager.create(couries)
//        true
//    } catch (e: IllegalArgumentException) {
//        print("ERROR")
//        print (e)
//        false
//    }
}