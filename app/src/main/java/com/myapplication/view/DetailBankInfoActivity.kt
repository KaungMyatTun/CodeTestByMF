package com.myapplication.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myapplication.R
import com.myapplication.model.BankItem
import com.myapplication.networking.baseApiUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_bank_detail_list.*

class DetailBankInfoActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_bank_detail_list)

        initialize()
    }

    private fun initialize(){
        var bankItem = intent.extras!!.get("bank_info") as BankItem

        Picasso.get().load(baseApiUrl() + bankItem.logoUrl).into(Image)
        bank_id.text = bankItem.id
        name.text = bankItem.name
        created_at.text = bankItem.createdDate
        updated_at.text = bankItem.updatedDate
        major_flag.text = bankItem.majorFlag.toString()
    }
}