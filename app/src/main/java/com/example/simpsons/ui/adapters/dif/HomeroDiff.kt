package com.example.simpsons.ui.adapters.dif

import androidx.recyclerview.widget.DiffUtil
import com.example.network.network.models.ApiModel
import javax.inject.Inject

class HomeroDiff @Inject constructor(): DiffUtil.ItemCallback<ApiModel>() {
    override fun areItemsTheSame(oldItem: ApiModel, newItem: ApiModel): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ApiModel, newItem: ApiModel): Boolean = oldItem == newItem
}