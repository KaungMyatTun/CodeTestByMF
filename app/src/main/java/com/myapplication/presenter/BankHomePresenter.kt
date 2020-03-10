package com.myapplication.presenter

import com.myapplication.model.BankHomeInteractor
import com.myapplication.model.BankItem
import com.myapplication.view.BankListHomeView

class BankHomePresenter (private var bankHomeView: BankListHomeView?, private val bankIntereactor: BankHomeInteractor): BankHomeInteractor.OnFinishedListener{
    fun getBankData(){
        bankHomeView?.showProgress()
        bankIntereactor.requestBankDataAPI(this)
    }
    fun onDestroy(){
        bankHomeView = null
    }

    override fun onResultSuccess(bankUpdates: List<BankItem>) {
        bankHomeView?.hideProgress()
        bankHomeView?.setBankData(bankUpdates)
    }

    override fun onResultFail(strError: String) {
        bankHomeView?.hideProgress()
        bankHomeView?.getDataFailed(strError)
    }

    fun onItemClick(adapterPosition: Int){
        bankHomeView?.onItemClick(adapterPosition)
    }

    fun navigateTo(bankItem: BankItem){
        bankHomeView?.navigateTo(bankItem)
    }
}