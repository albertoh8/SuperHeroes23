package com.iesam.superheroes23.features.data.connections.local.xml

import android.content.Context
import com.google.gson.Gson
import com.iesam.superheroes23.app.Either
import com.iesam.superheroes23.app.ErrorApp
import com.iesam.superheroes23.app.left
import com.iesam.superheroes23.app.right
import com.iesam.superheroes23.features.data.connections.local.ConnectionsLocalDataRepository
import com.iesam.superheroes23.features.domain.Connections
import com.iesam.superheroes23.features.domain.Work

class XmlConnectionLocalDataSource (
    private val context: Context
) : ConnectionsLocalDataRepository {

    private val sharedPreferences = context.getSharedPreferences("Connections", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()
    private val gson = Gson()

    override suspend fun saveConnection(heroId: Int, connect: Connections): Either<ErrorApp, Boolean> {
        try {
            val jsonConnect = gson.toJson(connect, Connections::class.java)
            editor.putString(heroId.toString(),jsonConnect)
            editor.apply()
        }catch (ex: Exception){
            return ErrorApp.DataError.left()
        }
        return true.right()
    }

    override suspend fun getConnection(heroId: Int): Either<ErrorApp, Connections?> {
        try {
            val connect =  sharedPreferences.getString(heroId.toString(), null).let {
                gson.fromJson(it, Connections::class.java)
            }
            return connect.right()

        }catch (ex:Exception){
            return ErrorApp.DataError.left()
        }
    }
}