package by.crispypigeon.weatherapp.view.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import by.crispypigeon.weatherapp.R
import by.crispypigeon.weatherapp.datamodels.dbmodels.WeatherDbItem
import by.crispypigeon.weatherapp.presenter.MainPresenter
import by.crispypigeon.weatherapp.services.geolocation.GeoLocationService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.GlobalScope
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(),
    IMainView {

    private val dateFormatPattern = "dd.MM.yyyy HH:mm"
    private val dateFormat = SimpleDateFormat(dateFormatPattern)

    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(customToolBar)

        presenter =
            MainPresenter(this)

        refreshImageButton.setAnimatingOnClickListener {
            presenter.updateData()
            Toast.makeText(this@MainActivity, "Weather updated from API", Toast.LENGTH_SHORT).show()
        }
    }

    override fun showCurrentWeather(
        countryIndex: String,
        temperature: Double,
        lon: Double,
        lat: Double,
        condition: String,
        time: Date
    ) {
        countryIndexTextView.setText(countryIndex)
        temperatureTextView.setText(temperature.toString())
        latitudeTextView.setText(lat.toString())
        lontitudeTextView.setText(lon.toString())
        conditionTextView.setText(condition)
        timeTextView.setText(dateFormat.format(time))
    }

    override fun showForecasts(forecasts: List<WeatherDbItem>) {
        forecastRecyclerView.adapter =
            WeatherAdapter(
                forecasts,
                dateFormat
            )
        forecastRecyclerView.layoutManager = LinearLayoutManager(this)
        forecastRecyclerView.setHasFixedSize(true)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        GeoLocationService.INSTANCE?.permissionRequestUpdated(requestCode, grantResults)
    }
}
