package by.crispypigeon.weatherapp.mvp.main.view

import WeatherResponse
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.crispypigeon.weatherapp.R
import by.crispypigeon.weatherapp.mvp.main.presenter.MainPresenter
import by.crispypigeon.weatherapp.mvp.services.api.GsonRequest
import com.android.volley.Response
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity(), IMainView {

    var presenter : MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
    }
}
