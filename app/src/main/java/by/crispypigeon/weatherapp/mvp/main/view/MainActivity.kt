package by.crispypigeon.weatherapp.mvp.main.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.crispypigeon.weatherapp.R
import by.crispypigeon.weatherapp.mvp.datamodels.resultmodels.WeatherItem
import by.crispypigeon.weatherapp.mvp.main.presenter.MainPresenter
import by.crispypigeon.weatherapp.mvp.services.geolocation.GeoLocationService
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), IMainView {
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
    }

    override fun ShowCurrentWeather(
        countryIndex: String,
        temperature: Double,
        lon: Double,
        lat: Double,
        condition: String,
        time: String
    ) {
        countryIndexTextView.setText(countryIndex)
        temperatureTextView.setText(temperature.toString())
        latitudeTextView.setText(lat.toString())
        lontitudeTextView.setText(lon.toString())
        conditionTextView.setText(condition)
        timeTextView.setText(time)
    }

    override fun ShowForecasts(forecasts: List<WeatherItem>) {
        forecastRecyclerView.adapter = WeatherAdapter(forecasts)
        forecastRecyclerView.layoutManager = LinearLayoutManager(this)
        forecastRecyclerView.setHasFixedSize(true)
    }




    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        GeoLocationService.INSTANCE?.permissionRequestUpdated(requestCode, grantResults)
    }
}
