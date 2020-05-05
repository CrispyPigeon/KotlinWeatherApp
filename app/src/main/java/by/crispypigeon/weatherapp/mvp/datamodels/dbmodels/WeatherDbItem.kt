package by.crispypigeon.weatherapp.mvp.datamodels.dbmodels

import io.realm.RealmObject

open class WeatherDbItem(
    var id: Int? = null,
    var countryIndex: String? = null,
    var temperature: Double? = null,
    var lon: Double? = null,
    var lat: Double? = null,
    var condition: String? = null,
    var time: String? = null
) : RealmObject()