package com.example.zap.data.models

import com.example.zap.presentation.model.ViewObject

interface DataObject {
    fun toViewObject(): ViewObject
}
