package test.sportradar.com.sportradartest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.roundToInt


/**
 * Created by Dejan Krivec
 */
class MyCustomView : View {

    private var listener: VolumeCallback? = null

    private val labelHeight: Int = 60

    val paint: Paint by lazy { Paint() }

    var mvolume: String = "50" // default value in %
    var labelNumber: String = "10" // default value
    var labelColor: Int = 0 // default value

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        listener = context as VolumeCallback
        val a = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.MyCustomView, 0, 0
        )

        try {
            mvolume = a.getString(R.styleable.MyCustomView_volume)
            labelNumber = a.getString(R.styleable.MyCustomView_labelNumber)
            if (labelNumber.toInt() > 10) {
                labelNumber = "10"
            }
            labelColor = a.getInteger(R.styleable.MyCustomView_labelColor, 0)
        } finally {
            a.recycle()
        }
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val viewWidthHalf = this.measuredWidth / 4
        val viewHeightHalf = this.measuredHeight / 4

        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.color = labelColor
        paint.strokeWidth = labelHeight.toFloat()

        var startX = viewWidthHalf.toFloat()
        var startY = viewHeightHalf.toFloat() * 3
        var stopX = viewWidthHalf.toFloat() * 3
        var stopY = viewHeightHalf.toFloat() * 3

        for (i in 1..labelNumber.toInt()) { // mvolume can be grater then number of labels, in this case we show max volume

            if (i > ((labelNumber.toInt() * mvolume.toInt()) / 100)) {
                paint.color = Color.GRAY
            }

            canvas?.drawLine(startX, startY, stopX, stopY, paint)

            startY -= labelHeight * 2
            stopY -= labelHeight * 2
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val viewHeightHalf = this.measuredHeight / 4

        var positionY = event?.getY()
        var viewHeight = ((10 * 2) - 1) * labelHeight//labelHeight * 2 * 9

        var limiter = viewHeight / 10

        if (event?.getAction() == MotionEvent.ACTION_MOVE) {
            if (positionY!! < (viewHeightHalf.toFloat() * 3) && positionY!! > (viewHeightHalf.toFloat() * 3) - viewHeight) {
                mvolume =
                        ((((viewHeight - positionY) / limiter) / labelNumber.toInt()) * 100).roundToInt()
                            .toString()

                if (mvolume.toInt() > 100) {
                    mvolume = "100"
                }
                if (mvolume.toInt() < 0) {
                    mvolume = "0"
                }

                updateView()
                listener?.showVolume(mvolume)
            }
        }
        return true
    }

    fun setVolume(value: String) {
        if (value.isNotEmpty()) {
            mvolume = value
            if (mvolume.toInt() > 100) {
                mvolume = "100"
            }
            if (mvolume.toInt() < 0) {
                mvolume = "0"
            }
            updateView()
        }
    }

    fun setLabels(value: String) {
        if (value.isNotEmpty()) {
            labelNumber = value
            if (labelNumber.toInt() > 10) {
                labelNumber = "10"
            }
            if (labelNumber.toInt() < 0) {
                labelNumber = "0"
            }
            updateView()
        }
    }

    fun setColor(value: Int) {
        labelColor = value;
        updateView()
    }

    private fun updateView() {
        invalidate()
        requestLayout()
    }

    interface VolumeCallback {
        fun showVolume(value: String)
    }
}