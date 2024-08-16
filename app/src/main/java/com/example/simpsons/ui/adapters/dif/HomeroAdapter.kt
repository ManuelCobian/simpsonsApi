package com.example.simpsons.ui.adapters.dif

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.network.network.models.ApiModel
import com.example.simpsons.R
import com.example.simpsons.databinding.CardHomeroBinding
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class HomeroAdapter  @Inject constructor(
    @ActivityContext private val context: Context,
    private val diff: HomeroDiff,
    val callbackClick: HomeroAdapter.CallbackClick
): ListAdapter<ApiModel, RecyclerView.ViewHolder>(diff)  {

    interface CallbackClick{
        fun onItemClicked(module: ApiModel)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener  {
        val binding = CardHomeroBinding.bind(view)

        init {

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            // Obtiene la posición del elemento clicado y llama al método onItemClick del listener
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val item = getItem(position)
                callbackClick.onItemClicked(item)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.card_homero, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val module = getItem(position)
        with((holder as HomeroAdapter.ViewHolder).binding) {
            Glide.with(context)
                .load(module.image)
                .apply(RequestOptions().placeholder(R.drawable.placeholder_image)) // Opcional: imagen de marcador de posición
                .into(holder.binding.cardImage)

            holder.binding.cardName.text = module.character
            holder.binding.cardDescription.text = module.quote
        }
    }
}