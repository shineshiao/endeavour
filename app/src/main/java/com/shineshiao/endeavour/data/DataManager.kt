package com.shineshiao.endeavour.data

import com.shineshiao.endeavour.data.local.database.Database
import com.shineshiao.endeavour.data.local.prefs.AppPreferences

interface DataManager : AppPreferences, Database
