package by.crispypigeon.weatherapp.mvp.main.presenter

import WeatherResponse
import android.content.Context
import by.crispypigeon.weatherapp.mvp.main.model.MainModel
import by.crispypigeon.weatherapp.mvp.main.view.IMainView
import com.android.volley.Response

class MainPresenter {

    lateinit var model: MainModel
    var view: IMainView

    constructor(mainview: IMainView) {
        view = mainview
        model = MainModel()

        GetCurrentWeather()
    }

    private fun GetCurrentWeather() {
        model.GetWeather(
            view as Context,
            Response.Listener<WeatherResponse> { response -> CurrentWeatherListener(response) },
            "0160dc60ff74dfca79aa310ee187d61e",
            53.893009,
            27.567444,
            "metric",
            "ru"
        )
    }

    fun CurrentWeatherListener(response: WeatherResponse) {
        view.ShowCurrentWeather(
            response.city.country,
            response.list[0].main.temp,
            response.city.coord.lon,
            response.city.coord.lat,
            response.list[0].weather[0].description,
            response.list[0].dt_txt
        )
    }


}