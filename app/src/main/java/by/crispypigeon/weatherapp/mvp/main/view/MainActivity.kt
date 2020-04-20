package by.crispypigeon.weatherapp.mvp.main.view

import WeatherResponse
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.crispypigeon.weatherapp.R
import by.crispypigeon.weatherapp.mvp.main.model.MainModel
import by.crispypigeon.weatherapp.mvp.main.presenter.MainPresenter
import by.crispypigeon.weatherapp.mvp.services.api.GsonRequest
import com.android.volley.Response
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity(), IMainView {
    lateinit var presenter: MainPresenter

    //controls
    private lateinit var countryIndexTextView : TextView
    private lateinit var temperatureTextView : TextView
    private lateinit var latTextView : TextView
    private lateinit var lonTextView : TextView
    private lateinit var conditionTextView : TextView
    private lateinit var timeTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countryIndexTextView = findViewById<TextView>(R.id.countryIndexTextView)
        temperatureTextView = findViewById<TextView>(R.id.temperatureTextView)
        latTextView = findViewById<TextView>(R.id.latitudeTextView)
        lonTextView = findViewById<TextView>(R.id.lontitudeTextView)
        conditionTextView = findViewById<TextView>(R.id.conditionTextView)
        timeTextView = findViewById<TextView>(R.id.timeTextView)

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
        latTextView.setText(lat.toString())
        lonTextView.setText(lon.toString())
        conditionTextView.setText(condition)
        timeTextView.setText(time)
    }


}
