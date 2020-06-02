package by.crispypigeon.weatherapp.presenter

import android.app.Activity
import by.crispypigeon.weatherapp.datamodels.resultmodels.WeatherItem
import by.crispypigeon.weatherapp.model.main.MainModel
import by.crispypigeon.weatherapp.view.main.IMainView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter {

    private var model: MainModel
    var view: IMainView

    constructor(mainview: IMainView) {
        view = mainview
        model =
            MainModel(view as Activity)

        showWeather()
    }

    private fun showWeather() {
        GlobalScope.launch(Dispatchers.Main) {

            val weatherResponse = model.getWeather()

            weatherListener(weatherResponse)
        }
    }

    private fun weatherListener(response: WeatherItem) {
        view.showCurrentWeather(
            response.weatherNow.city!!.index,
            response.weatherNow.temperature,
            response.weatherNow.city!!.lontitude,
            response.weatherNow.city!!.latitude,
            response.weatherNow.condition,
            response.weatherNow.time
        )

        view.showForecasts(response.forecast24H)
    }

    suspend fun updateData() {
        val result = model.updateForecastsFromApi()

        weatherListener(result)
    }
}
