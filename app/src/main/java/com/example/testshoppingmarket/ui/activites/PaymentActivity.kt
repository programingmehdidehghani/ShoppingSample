package com.example.testshoppingmarket.ui.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.testshoppingmarket.R
import com.example.testshoppingmarket.databinding.LayoutActivityPaymentBinding
import com.example.testshoppingmarket.ui.dialogs.DetailProductDialog
import com.example.testshoppingmarket.ui.dialogs.ResultPaymentDialog
import com.example.testshoppingmarket.utils.toast

class PaymentActivity : AppCompatActivity() {

    private val viewBinding by lazy {
        LayoutActivityPaymentBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        val allTotal = intent.getStringExtra("total")
        viewBinding.txtTotalAmountInPaymentActivity.text = getString(R.string.txt_all_total_in_payment).format(allTotal)
        viewBinding.btnCheckOutInPaymentActivity.setOnClickListener {
            if (viewBinding.etNumberCardInPaymentActivity.text.toString().isNotEmpty() && viewBinding.etCcv2InPaymentActivity.text.toString().isNotEmpty()
                && viewBinding.etMonthInPaymentActivity.text.toString().isNotEmpty() && viewBinding.etYearInPaymentActivity.text.toString().isNotEmpty()
                && viewBinding.etEpassInPaymentActivity.text.toString().isNotEmpty()){
                val fm: FragmentManager = this.supportFragmentManager
                val dialog = ResultPaymentDialog()
                val bundle = Bundle()
                bundle.putString("total",allTotal)
                bundle.putString("cardNumber",viewBinding.etNumberCardInPaymentActivity.text.toString())
                dialog.arguments = bundle
                dialog.show(fm, "start")
            } else{
                toast(this,"Filling all fields is mandatory")
            }
        }
    }
}