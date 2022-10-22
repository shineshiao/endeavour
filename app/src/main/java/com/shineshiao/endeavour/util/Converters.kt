package com.shineshiao.endeavour.util

import androidx.room.TypeConverter
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.shineshiao.endeavour.model.PriceModel
import java.lang.reflect.Type
import java.util.Collections

/**
 * Created by thach.nguyen on 16,10,2022
 */
class Converters() {
    private var mGson: Gson

    init {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        mGson = gsonBuilder.create()
    }
    @TypeConverter
    fun stringToPriceList(data: String?): List<PriceModel>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<PriceModel>?>() {}.type
        return mGson.fromJson(data, listType)
    }

    @TypeConverter
    fun priceListToString(someObjects: List<PriceModel>?): String? {
        return mGson.toJson(someObjects)
    }
}
