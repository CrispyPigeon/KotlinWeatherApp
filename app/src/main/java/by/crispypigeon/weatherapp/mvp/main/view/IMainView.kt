package by.crispypigeon.weatherapp.mvp.main.view

interface IMainView {
    fun ShowCurrentWeather(
        countryIndex: String,
        temperature: Double,
        lon: Double,
        lat: Double,
        condition: String,
        time: String
    )
}