package com.example.imagify

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class viewModel(context: Context): ViewModel() {
    private val dao=profileDb.getDatabase(context).profiledao()
    private val repository=MyRepository(dao)
    fun load():List<Profile>{
      return repository.loadAll()
    }
    fun insertUser(profile: Profile){
        GlobalScope.launch (Dispatchers.IO){
            repository.insertNew(profile)
        }

    }

}