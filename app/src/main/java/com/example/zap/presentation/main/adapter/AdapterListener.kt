package com.example.zap.presentation.main.adapter

import android.view.View
import com.example.zap.presentation.model.ImmobileVO

interface AdapterListener {
    fun onSelectedItem(view: View, selectedItem: ImmobileVO)
}
