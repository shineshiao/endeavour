package com.shineshiao.endeavour.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class AppPreferencesImpl @Inject constructor(
    context: Context
) : AppPreferences {

    @Inject
    lateinit var mGson: Gson

    private val mPrefs: SharedPreferences =
        context.getSharedPreferences("ENDEAVOUR_PREFs", Context.MODE_PRIVATE)
}
