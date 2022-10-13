package com.shineshiao.endeavour.model

/**
 * Created by thach.nguyen on 13,10,2022
 */
data class PriceModel(
    val message: String = "",
    val value: Double = 0.0,
    val isOfferPrice: Boolean = false,
)
