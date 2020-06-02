package by.crispypigeon.weatherapp.datamodels.dbmodels

import io.realm.RealmObject

open class CityDbItem(
    var name: String = String(),
    var index: String = String(),
    var lontitude: Double = 0.0,
    var latitude: Double = 0.0
) : RealmObject()