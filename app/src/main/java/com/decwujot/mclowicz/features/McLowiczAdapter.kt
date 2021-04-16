package com.decwujot.mclowicz.features

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.decwujot.mclowicz.data.model.Dish
import com.decwujot.mclowicz.databinding.ItemDishBinding

class McLowiczAdapter :
        ListAdapter<Dish, McLowiczAdapter.McLowiczViewHolder>(McLowiczComparator()) {

    class McLowiczViewHolder(private val binding: ItemDishBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(dish: Dish) {
            binding.apply {
                itemDish.text = dish.dish
                itemIngridient.text = dish.ingredient
                itemMeasurement.text = dish.measurement
                itemDescription.text = dish.description
            }
        }
    }

    class McLowiczComparator : DiffUtil.ItemCallback<Dish>() {
        override fun areItemsTheSame(oldItem: Dish, newItem: Dish) =
                oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Dish, newItem: Dish) =
                oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): McLowiczViewHolder {
        val binding =
                ItemDishBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return McLowiczViewHolder((binding))
    }

    override fun onBindViewHolder(holder: McLowiczViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}