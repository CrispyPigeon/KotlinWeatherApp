package by.crispypigeon.weatherapp.controls

import android.content.Context
import android.util.AttributeSet
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SyncImageButton : androidx.appcompat.widget.AppCompatImageButton {

    constructor(context: Context) : super(context)

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle)

    constructor(context: Context?, attrs: AttributeSet?)
            : super(context, attrs)

    fun setAnimatingOnClickListener(func: suspend () -> Unit) {
        setOnClickListener {
            startRotateAnimation()
            GlobalScope.launch(Dispatchers.Main) {
                func()
                stopRotateAnimation()
            }
        }
    }

    private fun startRotateAnimation() {
        val rotate = RotateAnimation(
            360F, 0F,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        rotate.duration = 900;
        rotate.repeatCount = Animation.INFINITE;
        startAnimation(rotate)
    }

    private fun stopRotateAnimation() {
        clearAnimation()
    }
}
