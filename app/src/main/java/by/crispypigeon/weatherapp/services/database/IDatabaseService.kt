package by.crispypigeon.weatherapp.services.database

interface IDatabaseService<T> {

    fun getAllItems() : List<T>

    fun saveItem(item : T)

    fun saveItems(items : List<T>)

    fun deleteItem(item : T)

    fun deleteItems(items : List<T>)

    fun deleteAll()
}