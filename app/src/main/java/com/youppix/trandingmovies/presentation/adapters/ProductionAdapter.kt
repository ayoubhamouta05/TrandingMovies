package com.youppix.trandingmovies.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.youppix.trandingmovies.databinding.RowProductionBinding
import com.youppix.trandingmovies.domain.model.ProductionCompany
import com.youppix.trandingmovies.domain.model.Result
import com.youppix.trandingmovies.util.Constant.IMG_BASE_URL

class ProductionAdapter : RecyclerView.Adapter<ProductionAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RowProductionBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<ProductionCompany>() {

        override fun areItemsTheSame(oldItem: ProductionCompany, newItem: ProductionCompany): Boolean {
            return oldItem.id== newItem.id
        }

        override fun areContentsTheSame(oldItem: ProductionCompany, newItem: ProductionCompany): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this , differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RowProductionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val data = differ.currentList[position]
        holder.binding.apply {
            Glide.with(root).load("$IMG_BASE_URL${data.logo_path}" ).into(productionCompaniesLogo)
            name.text = data.name
            countrie.text = data.origin_country
        }
    }

    override fun getItemCount(): Int =differ.currentList.size
}