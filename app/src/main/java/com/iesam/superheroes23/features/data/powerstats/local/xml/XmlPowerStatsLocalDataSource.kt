package com.iesam.superhero.data.powerstats.local.xml

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.iesam.superhero.data.powerstats.local.PowerStatsLocalDataSource
import com.iesam.superheroes23.features.domain.PowerStats

class XmlPowerStatsLocalDataSource(
    private val context: Context)
    : PowerStatsLocalDataSource {
    private val sharedPreferences = context.getSharedPreferences("PowerStats", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()
    private val gson = Gson()

    override fun save(heroId: Int, powerStats: PowerStats) {
        editor.putString(heroId.toString(), gson.toJson(powerStats, PowerStats::class.java))
        editor.apply()
    }

    override fun getPowerStats(heroId: Int): PowerStats? {
        val jsonPowerStats = sharedPreferences.getString(heroId.toString(), null)
        return jsonPowerStats?.let {
            gson.fromJson(it, PowerStats::class.java)
        }
    }
}