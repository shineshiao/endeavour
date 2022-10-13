package com.shineshiao.endeavour

import androidx.lifecycle.LifecycleObserver
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : MultiDexApplication(), LifecycleObserver
