package com.example.imagify

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyRepository(private val profileDao: profileDao) {
    fun loadAll():List<Profile>{
        lateinit var res:List<Profile>
        GlobalScope.launch(Dispatchers.IO) {
             res=profileDao.showAll()
        }
        return res
    }
    suspend fun insertNew(profile:Profile){
        GlobalScope.launch(Dispatchers.IO) {
            profileDao.insert(profile)
        }
    }
    suspend fun delete(){
        GlobalScope.launch(Dispatchers.IO) {
            profileDao.deleteAll()
        }
    }
}