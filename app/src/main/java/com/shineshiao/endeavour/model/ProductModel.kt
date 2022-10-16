package com.shineshiao.endeavour.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by thach.nguyen on 13,10,2022
 */
@Parcelize
data class ProductModel(
    val id: String,
    val imageURL: String = "",
    val title: String = "",
    val brand: String = "",
    val price: List<PriceModel>,
    val isAddToCartEnable: Boolean,
    val ratingCount: Double,
    val isFavourite: Boolean = false
) : Parcelable
