package com.shineshiao.endeavour.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData

object LiveDataUtil {

    fun <T> zip(liveItems: MutableList<MutableLiveData<T>>): MutableLiveData<ArrayList<T?>> {
        return MediatorLiveData<ArrayList<T?>>().apply {
            val zippedObjects = ArrayList<T?>()
            liveItems.forEach {
                zippedObjects.add(it.value)
                addSource(it) { item ->
                    zippedObjects[liveItems.indexOf(it)] = item
                    value = zippedObjects
                }
            }
        }
    }

    fun <T> zipState(liveItems: MutableList<MutableLiveData<T>>): MutableLiveData<ArrayList<T?>> {
        return MediatorLiveData<ArrayList<T?>>().apply {
            val zippedObjects = ArrayList<T?>()
            liveItems.forEach {
                zippedObjects.add(it.value)
                addSource(it) { item ->
                    zippedObjects[liveItems.indexOf(it)] = item
                    value = zippedObjects
                }
            }
        }
    }

    fun zipAny(liveItems: MutableList<LiveData<*>>): MutableLiveData<ArrayList<Any?>> {
        return MediatorLiveData<ArrayList<Any?>>().apply {
            val zippedObjects = ArrayList<Any?>()
            liveItems.forEach {
                zippedObjects.add(it.value)
                addSource(it) { item ->
                    zippedObjects[liveItems.indexOf(it)] = item
                    value = zippedObjects
                }
            }
        }
    }
}
