package com.shineshiao.endeavour.base

class DataHolderImpl<T>(override var holderType: HolderType, override var data: T) : DataHolder<T>
