package by.crispypigeon.weatherapp.services.geolocation

import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationResult

open class CustomLocationCallback(val listener: LocationListener) : LocationCallback() {
    override fun onLocationResult(p0: LocationResult?) {
        super.onLocationResult(p0)
        listener.onLocationChanged(p0?.lastLocation)
    }
}