package by.crispypigeon.weatherapp.mvp.main.presenter

import WeatherResponse
import android.content.Context
import by.crispypigeon.weatherapp.mvp.datamodels.resultmodels.WeatherItem
import by.crispypigeon.weatherapp.mvp.main.model.MainModel
import by.crispypigeon.weatherapp.mvp.main.view.IMainView
import com.android.volley.Response

class MainPresenter {
    lateinit var model: MainModel
    var view: IMainView

    constructor(mainview: IMainView) {
        view = mainview
        model = MainModel()

        getWeather()
    }

    private fun getWeather() {
        model.GetWeather(
            view as Context,
            Response.Listener<WeatherResponse> { response -> weatherListener(response) },
            "0160dc60ff74dfca79aa310ee187d61e",
            53.893009,
            27.567444,
            "metric",
            "ru"
        )
    }

    fun weatherListener(response: WeatherResponse) {
        view.ShowCurrentWeather(
            response.city.country,
            response.list[0].main.temp,
            response.city.coord.lon,
            response.city.coord.lat,
            response.list[0].weather[0].description,
            response.list[0].dt_txt
        )

        val forecasts = ArrayList<WeatherItem>()
        for (item in response.list) {
            forecasts.add(
                WeatherItem(
                    item.main.temp.toString(), item.dt_txt,
                    item.weather.first().description
                )
            )
        }
        view.ShowForecasts(forecasts)
    }
}