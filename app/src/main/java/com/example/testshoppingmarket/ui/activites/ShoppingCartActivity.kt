package com.example.testshoppingmarket.ui.activites

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.testshoppingmarket.R
import com.example.testshoppingmarket.databinding.LayoutActivityShoppingCartBinding
import com.example.testshoppingmarket.databinding.LayoutMainActivityBinding
import com.example.testshoppingmarket.db.CartModel
import com.example.testshoppingmarket.ui.viewModels.MainViewModel
import com.example.testshoppingmarket.utils.ImageLoader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingCartActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    lateinit var price: String


    private val viewBinding by lazy {
        LayoutActivityShoppingCartBinding.inflate(layoutInflater)
    }

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        insetCart()
        interestCalculation()
        viewBinding.btnCashInShoppingCart.setOnClickListener {
            viewBinding.btnCashInShoppingCart.setBackgroundColor(R.color.toolbar_color)
            viewBinding.btnLoanInShoppingCart.setBackgroundColor(R.color.very_low_gray)
        }
        viewBinding.btnLoanInShoppingCart.setOnClickListener {
            viewBinding.btnCashInShoppingCart.setBackgroundColor(R.color.very_low_gray)
            viewBinding.btnLoanInShoppingCart.setBackgroundColor(R.color.toolbar_color)
        }
    }

    fun insetCart() {
        val name = intent.getStringExtra("name")
        price = intent.getStringExtra("price").toString()
        val image = intent.getStringExtra("image")
        val cartModel = CartModel(
            name.toString(),
            image.toString(),
            price.toString()
        )
        mainViewModel.insertCart(cartModel)
        viewBinding.txtTitleProductInShoppingCart.text = name
        viewBinding.txtPriceDbProductInShoppingCart.text = price
        viewBinding.txtTotalCheckProductInShoppingCart.text = price
        viewBinding.txtResultTotalAmountInShoppingCart.text = price
        ImageLoader.loadImage(viewBinding.ivImageProductInShappingCart, image.toString())
        viewBinding.btnCheckOutInShoppingCart.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("total",price)
            startActivity(intent)
        }
    }

    fun interestCalculation(){
        viewBinding.rbThreeMonthInActivityShopping.setOnClickListener {
            viewBinding.rbSixMonthInActivityShopping.isChecked = false
            viewBinding.rb12MonthInActivityShopping.isChecked = false
            val interestResult = price.toDouble() * 20
        }
        viewBinding.rbSixMonthInActivityShopping.setOnClickListener {
            viewBinding.rbThreeMonthInActivityShopping.isChecked = false
            viewBinding.rb12MonthInActivityShopping.isChecked = false
            val interestResult = price.toDouble() * 10
        }
        viewBinding.rb12MonthInActivityShopping.setOnClickListener {
            viewBinding.rbThreeMonthInActivityShopping.isChecked = false
            viewBinding.rbSixMonthInActivityShopping.isChecked = false

        }
    }



}