package org.wit.couries.models

interface CouriesStore {
    fun findAll() : List<CouriesModel>
    fun findById(id: Long) : CouriesModel?
    fun create(couries: CouriesModel)
    fun getOne(index:Int): CouriesModel?

    fun update(couries: CouriesModel)
}