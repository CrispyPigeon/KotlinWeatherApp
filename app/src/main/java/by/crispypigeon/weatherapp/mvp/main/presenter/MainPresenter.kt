package by.crispypigeon.weatherapp.mvp.main.presenter

import by.crispypigeon.weatherapp.mvp.datamodels.apimodels.WeatherResponse
import android.app.Activity
import by.crispypigeon.weatherapp.mvp.main.model.MainModel
import by.crispypigeon.weatherapp.mvp.main.view.IMainView
import com.android.volley.Response

//TODO *1 showing 24h weather *2 if forecasts exists in db show from db
class MainPresenter {
    private val appId = "0160dc60ff74dfca79aa310ee187d61e"
    private val unit = "metric"
    private val lang = "ru"

    var model: MainModel
    var view: IMainView

    constructor(mainview: IMainView) {
        view = mainview
        model = MainModel(view as Activity)

        getWeather()
    }

    private fun getWeather() {
        model.clearDbItems()
        model.getWeather(
            Response.Listener<WeatherResponse> { response -> weatherListener(response) },
            appId,
            unit,
            lang
        )
    }

    fun weatherListener(response: WeatherResponse) {
        model.saveWeatherItemsToDb(response)
        val firstItem = model.getFirstWeatherDbItem()
        view.ShowCurrentWeather(
            firstItem.city!!.index,
            firstItem.temperature,
            firstItem.city!!.lontitude,
            firstItem.city!!.latitude,
            firstItem.condition,
            firstItem.time.toString()
        )

        val forecasts = model.getAllWeatherDbItems()
        view.ShowForecasts(forecasts)
    }
}
