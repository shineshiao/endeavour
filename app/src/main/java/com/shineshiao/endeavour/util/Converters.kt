package com.shineshiao.endeavour.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shineshiao.endeavour.model.PriceModel
import java.lang.reflect.Type
import java.util.Collections
import javax.inject.Inject

/**
 * Created by thach.nguyen on 16,10,2022
 */
class Converters {
    @Inject
    lateinit var mGson: Gson

    @TypeConverter
    fun stringToPriceList(data: String?): List<PriceModel?>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<PriceModel?>?>() {}.type
        return mGson.fromJson(data, listType)
    }

    @TypeConverter
    fun priceListToString(someObjects: List<PriceModel?>?): String? {
        return mGson.toJson(someObjects)
    }
}
