package com.example.expancetracker.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expancetracker.R
import com.example.expancetracker.Utils
import com.example.expancetracker.data.ExpenseDataBase
import com.example.expancetracker.data.dao.Expancedao
import com.example.expancetracker.data.model.ExpenseEntity

class HomeviewModel(dao:Expancedao):ViewModel() {
    val expenses = dao.getAllExpense()

    fun getBalance(list: List<ExpenseEntity>): String{
        var balance = 0.0
        for (expense in list) {
            if (expense.type == "Income") {
                balance += expense.amount
            } else {
                balance -= expense.amount
            }
        }
        return "${Utils.formatToDecimalValue(balance)}"


    }


    fun getTotalExpense(list: List<ExpenseEntity>): String{
        var balance = 0.0
        for (expense in list) {
            if (expense.type == "Expense") {
                balance += expense.amount
            }
        }
        return "${Utils.formatToDecimalValue(balance)}"

    }

    fun getTotalIncome(list: List<ExpenseEntity>): String{
        var balance = 0.0
        for (expense in list) {
            if (expense.type == "Income") {
                balance += expense.amount
            }
        }
        return "${Utils.formatToDecimalValue(balance)}"



    }

    fun geItemIcon(item:ExpenseEntity):Int{

        if(item.category == "Paypal"){
            return R.drawable.ic_paypal
        }else if(item.category == "Netflix"){
            return R.drawable.ic_netflix
        }else if(item.category == "Starbucks"){
            return R.drawable.ic_starbucks
        }else if (item.category=="Miscellaneous"){
            return R.drawable.box
        }
        return R.drawable.ic_upwork
    }
}

class HomeViewModelFactory(private val context: Context):ViewModelProvider.Factory{
    override fun <T:ViewModel>create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(HomeviewModel::class.java)){
            val dao = ExpenseDataBase.getDatabase(context).expensedao()
            @Suppress("UNCHECKED_CAST")
            return HomeviewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
