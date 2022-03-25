package org.wit.couries.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CouriesModel(var id: Long = 0,
                        var clientName: String= "",
                        var clientNumber: Int =0,
                        var clientAddress: String= "") : Parcelable
