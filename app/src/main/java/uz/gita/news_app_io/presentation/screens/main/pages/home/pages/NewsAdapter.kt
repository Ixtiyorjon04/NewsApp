package uz.gita.news_app_io.presentation.screens.main.pages.home.pages

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.gita.news_app_io.R
import uz.gita.news_app_io.data.local.entity.NewsEntity
import uz.gita.news_app_io.databinding.ListItemNewsBinding
import uz.gita.news_app_io.utils.Constants
import uz.gita.news_app_io.utils.inflate

// Created by Jamshid Isoqov an 10/30/2022
class NewsAdapter : ListAdapter<NewsEntity, NewsAdapter.ViewHolder>(itemNewsCallback) {
    private var itemClickListener: ((NewsEntity) -> Unit)? = null

    fun setItemClickListener(block: (NewsEntity) -> Unit) {
        itemClickListener = block
    }


    inner class ViewHolder(private val binding: ListItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        @SuppressLint("SetTextI18n")
        fun onBind() {
            val data = getItem(absoluteAdapterPosition)

            binding.apply {
                Picasso.get()
                    .load(data.imageUrl)
                    .placeholder(R.drawable.logo)
                    .into(imageNews)
                tvNewsTitle.text = data.title
                tvDescription.text = data.description
                tvNewsAuthor.text = "Author : ${data.author ?: data.source ?: "Unknown source"}"
                tvNewsTime.text = "Realize news : ${Constants.getTime(data.time)}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ListItemNewsBinding.bind(parent.inflate(R.layout.list_item_news))
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind()

}

private var itemNewsCallback = object : DiffUtil.ItemCallback<NewsEntity>() {
    override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean =
        oldItem.saved == newItem.saved &&
                oldItem.title == newItem.title &&
                oldItem.time == newItem.time

}