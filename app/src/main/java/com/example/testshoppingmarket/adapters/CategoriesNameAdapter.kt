package com.example.testshoppingmarket.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testshoppingmarket.databinding.CategoryNameItemsBinding
import com.example.testshoppingmarket.model.CategoriesHeader


interface OnItemClickCallback {
    fun onItemClick(symbol: String)
}
@Suppress("DEPRECATION")
class CategoriesNameAdapter(private val onItemClickCallback: OnItemClickCallback) :
    RecyclerView.Adapter<CategoriesNameAdapter.CategoriesViewHolder>() {


    private val categoriesName: ArrayList<CategoriesHeader> = arrayListOf()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = CategoryNameItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoriesViewHolder(binding)
    }

    override fun getItemCount(): Int = categoriesName.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.bind(categoriesName[position],onItemClickCallback)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(list: List<CategoriesHeader>) {
        this.categoriesName.clear()
        this.categoriesName.addAll(list)
        notifyDataSetChanged()
    }

    inner class CategoriesViewHolder(private val binding: CategoryNameItemsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(model: CategoriesHeader,onItemClickCallback: OnItemClickCallback) {
                binding.txtNameCategoryInItemsCategory.text = model.get(position)



            itemView.setOnClickListener {
                onItemClickCallback.onItemClick(
                    model.get(position)
                )
            }
        }
    }

}