package com.shineshiao.endeavour.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by thach.nguyen on 13,10,2022
 */
@Parcelize
data class PriceModel(
    val message: String = "",
    val value: Double = 0.0,
    val isOfferPrice: Boolean = false,
) : Parcelable
