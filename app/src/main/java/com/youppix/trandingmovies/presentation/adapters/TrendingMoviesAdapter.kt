package com.youppix.trandingmovies.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.youppix.trandingmovies.R
import com.youppix.trandingmovies.databinding.RowTrendingMovieBinding
import com.youppix.trandingmovies.domain.model.Result
import com.youppix.trandingmovies.util.Constant.IMG_BASE_URL
import com.youppix.trandingmovies.util.Constant.getGenreNameById

class TrendingMoviesAdapter : RecyclerView.Adapter<TrendingMoviesAdapter.ViewHolder>() {

    inner class ViewHolder( val binding: RowTrendingMovieBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Result>() {

        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this , differCallBack)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            RowTrendingMovieBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.binding.apply {
            imgContainer.setBackgroundResource(R.drawable.trending_img_shape)

            Glide.with(root).load("$IMG_BASE_URL${data.poster_path}").into(img)
            title.text = data.title
            releaseDate.text = data.release_date
            categories.text = getAllGenres(data.genre_ids)
            voteAverage.text = data.vote_average.toString()
            description.text = data.overview
            showDetailsBtn.setOnClickListener {
                Log.d("adapter" , "btn clicked : ${data.title}")
                onShowDetailsClickListener?.let {
                    it(data)
                }
            }
        }

    }

    override fun getItemCount(): Int = differ.currentList.size

    private var onShowDetailsClickListener : ((Result) -> Unit)? = null

    fun setOnShowDetailsClickListener (listener : (Result) -> Unit){
        onShowDetailsClickListener = listener
    }

    private fun getAllGenres(genres : List<Int>) : String{
        val builder = StringBuilder()
        for (i in genres){
            builder.append(getGenreNameById(i))
            builder.append("/")
        }
        return builder.toString().dropLast(1)
    }
}