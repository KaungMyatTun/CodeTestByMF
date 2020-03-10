package com.myapplication.view

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myapplication.R
import com.myapplication.inflate
import com.myapplication.model.BankItem
import com.myapplication.networking.baseApiUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_bank_list_recycler.view.*

class BankListAdapter (private val bankList: List<BankItem>, private val listener: (Int) -> Unit): RecyclerView.Adapter<BankListAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(inflate(parent.context, R.layout.item_bank_list_recycler, parent, false))
    }


    override fun onBindViewHolder(holder: BankListAdapter.MyViewHolder, position: Int) {
        holder.bind(bankList[position], listener)
    }

    override fun getItemCount(): Int {
        Log.d("bank list > ", bankList.size.toString())
        return bankList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(bankItem: BankItem, listener: (Int) -> Unit) = with(itemView){
            Picasso.get().load(baseApiUrl() + bankItem.logoUrl).into(Image)
            name.text = bankItem.name
            itemView.setOnClickListener { listener(adapterPosition) }
        }
    }
}