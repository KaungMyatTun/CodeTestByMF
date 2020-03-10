package com.myapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.myapplication.R
import com.myapplication.model.BankHomeInteractor
import com.myapplication.model.BankItem
import com.myapplication.presenter.BankHomePresenter
import com.myapplication.showToast
import kotlinx.android.synthetic.main.activity_bank_home.*

class BankHomeActivity : AppCompatActivity(), BankListHomeView {

    private lateinit var bankPresenter: BankHomePresenter
    private lateinit var bankList : List<BankItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_home)

        bankPresenter = BankHomePresenter(this, BankHomeInteractor())
        progressBar.visibility = View.GONE
        recyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        bankPresenter.getBankData()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setBankData(bankList: List<BankItem>) {
        recyclerView.adapter = BankListAdapter(bankList){
            bankPresenter.onItemClick(it)
        }
        this.bankList = bankList
    }

    override fun getDataFailed(strError: String) {
        showToast(this, strError)
    }

    override fun onItemClick(adapterPosition: Int) {
        showToast(this, "You clicked $adapterPosition")
        bankPresenter.navigateTo(bankList[adapterPosition])
    }

    override fun onDestroy() {
        bankPresenter.onDestroy()
        super.onDestroy()
    }

    override fun navigateTo(bankItem: BankItem) {
        val intent = Intent(applicationContext, DetailBankInfoActivity::class.java)
        intent.putExtra("bank_info", bankItem)
        startActivity(intent)
    }
}
