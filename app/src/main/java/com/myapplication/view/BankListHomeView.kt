package com.myapplication.view

import com.myapplication.model.BankItem

interface BankListHomeView{
    fun showProgress()
    fun hideProgress()
    fun setBankData(bankList: List<BankItem>)
    fun getDataFailed(strError: String)
    fun onItemClick(adapterPosition: Int)
    fun navigateTo(bankItem : BankItem)
}