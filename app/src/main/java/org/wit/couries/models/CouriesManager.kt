package org.wit.couries.models

import timber.log.Timber

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

object CouriesManager : CouriesStore {

    private val couries = ArrayList<CouriesModel>()

    override fun findAll(): List<CouriesModel> {
        return couries
    }

    override fun findById(id:Long) : CouriesModel? {
        val foundCouries: CouriesModel? = couries.find { it.id == id }
        return foundCouries
    }
    override fun getOne(index:Int) : CouriesModel? {
        val foundCouries: CouriesModel? = couries.get(index)
        return foundCouries
    }

    override fun update(couries: CouriesModel) {
        TODO("Not yet implemented")
    }

    override fun create(couriesInd: CouriesModel) {
        couriesInd.id = getId()
        couries.add(couriesInd)
        logAll()
    }

    fun logAll() {
        Timber.v("** Couries List **")
        couries.forEach { Timber.v("Couries ${it}") }
        couries.forEach { print("Couries ${it}") }
    }

}