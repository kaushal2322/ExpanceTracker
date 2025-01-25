package com.example.expancetracker.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expancetracker.data.ExpenseDataBase
import com.example.expancetracker.data.dao.Expancedao
import com.example.expancetracker.data.model.ExpenseEntity

class AddExpenseViewModel(val dao:Expancedao):ViewModel() {

    suspend fun addExpense(expenseEntity: ExpenseEntity): Boolean {
        return try {
            dao.insertExpense(expenseEntity)
            true
        } catch (ex: Throwable) {
            false
        }
    }

}


class AddExpenseViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T:ViewModel>create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(AddExpenseViewModel::class.java)){
            val dao = ExpenseDataBase.getDatabase(context).expensedao()
            @Suppress("UNCHECKED_CAST")
            return AddExpenseViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}