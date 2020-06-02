package by.crispypigeon.weatherapp.services.database

import by.crispypigeon.weatherapp.datamodels.apimodels.WeatherResponse
import by.crispypigeon.weatherapp.datamodels.dbmodels.CityDbItem
import by.crispypigeon.weatherapp.datamodels.dbmodels.WeatherDbItem
import io.realm.Realm
import io.realm.kotlin.createObject
import java.text.SimpleDateFormat

class WeatherDatabaseService : IDatabaseService<WeatherDbItem> {
    private val realm: Realm = Realm.getDefaultInstance()
    private val datePattern = "yyyy-MM-dd HH:mm:ss"

    fun getFirstWeatherItem(): WeatherDbItem {
        return realm.where(WeatherDbItem::class.java).findFirst()!!
    }

    override fun getAllItems(): List<WeatherDbItem> {
        val result = realm.where(WeatherDbItem::class.java).findAll()
        return realm.copyFromRealm(result)
    }

    override fun saveItem(item: WeatherDbItem) {
        TODO("Not yet implemented")
    }

    override fun saveItems(items: List<WeatherDbItem>) {
        TODO("Not yet implemented")
    }

    override fun deleteItem(item: WeatherDbItem) {
        realm.executeTransaction() {
            val deletedItem =
                realm.where(WeatherDbItem::class.java).equalTo("id", item.id).findAll()
            deletedItem.deleteAllFromRealm()
        }
    }

    override fun deleteItems(items: List<WeatherDbItem>) {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        realm.executeTransaction() {
            realm.deleteAll()
        }
    }

    fun saveItemsFromAnother(response: WeatherResponse) {
        val idNumber: Number? = realm.where(WeatherDbItem::class.java).max("id")
        var currentId: Int
        currentId = if (idNumber?.toInt() == null || idNumber.toInt() == 0) {
            1
        } else {
            idNumber.toInt()
        }

        realm.executeTransaction()
        {
            val cityDbItem = realm.createObject<CityDbItem>()
            cityDbItem.index = response.city.country
            cityDbItem.name = response.city.name
            cityDbItem.lontitude = response.city.coord.lon
            cityDbItem.latitude = response.city.coord.lat

            for (item in response.list) {
                val weatherDbItem = realm.createObject<WeatherDbItem>(++currentId)
                weatherDbItem.condition = item.weather.first().description
                weatherDbItem.temperature = item.main.temp
                weatherDbItem.time = SimpleDateFormat(datePattern).parse(item.dt_txt)
                weatherDbItem.city = cityDbItem
            }
        }
    }
}