package com.shineshiao.endeavour.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * Created by thach.nguyen on 13,10,2022
 */
@Parcelize
@Entity(tableName = "products")
data class ProductModel(
    @PrimaryKey
    val id: String,
    val imageURL: String = "",
    val title: String = "",
    val brand: String = "",
    // val price: List<PriceModel>,
    val ratingCount: Double,
    var isFavourite: Boolean = false
) : Parcelable
