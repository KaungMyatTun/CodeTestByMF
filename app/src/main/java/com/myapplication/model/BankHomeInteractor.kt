package com.myapplication.model

import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.myapplication.networking.baseApiUrl

class BankHomeInteractor {
    companion object{
        private val TAG: String = BankHomeInteractor::class.java.simpleName
    }

    interface OnFinishedListener{
        fun onResultSuccess(bankUpdates: List<BankItem>)
        fun onResultFail(strError : String)
    }

    fun requestBankDataAPI(onFinishedListener: OnFinishedListener){
        AndroidNetworking.get(baseApiUrl() + "api/banks")
            .addHeaders("app-id", "wnV24w-O8SJiHqk2DYzynz")
            .addHeaders("secret-key", "fq7emqsxGUdXvZ6ck2mcH6Tvf-GbUgZZlcB1UMKn7wb99ny")
            .setTag(TAG)
            .setPriority(Priority.LOW)
            .build()
            .getAsObject(BankResponse::class.java, object : ParsedRequestListener<BankResponse>{
                override fun onResponse(response: BankResponse?) {
                    val bankItemList : MutableList<BankItem>? = mutableListOf()

                    for(i in response!!.BankResponse!!.indices){
                        response.BankResponse!![i]?.let { bankItemList?.add(it) }
                    }

                    if(bankItemList != null && bankItemList.isNotEmpty()){
                        onFinishedListener.onResultSuccess(bankItemList)
                    }else{
                        onFinishedListener.onResultFail("Nothing to show")
                    }
                }

                override fun onError(anError: ANError?) {
                    // handle error
                    Log.i("message", anError.toString())
                    onFinishedListener.onResultFail(anError.toString())
                }
            })
    }
}