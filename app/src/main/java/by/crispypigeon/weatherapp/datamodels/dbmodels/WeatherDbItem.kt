package by.crispypigeon.weatherapp.datamodels.dbmodels

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class WeatherDbItem(
    @PrimaryKey
    var id: Int = 0,

    var temperature: Double = 0.0,
    var condition: String = String(),
    var time: Date = Date(),

    var city: CityDbItem? = null
) : RealmObject()