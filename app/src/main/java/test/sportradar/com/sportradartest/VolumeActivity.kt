package test.sportradar.com.sportradartest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_volumen.*

class VolumeActivity : AppCompatActivity(), MyCustomView.VolumeCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volumen)

        btn_volume.setOnClickListener { _ ->
            customView.setVolume(ed_volume.text.toString())
            setVolume(customView.mvolume)
        }
        btn_lines.setOnClickListener { _ -> customView.setLabels(ed_lines.text.toString()) }

        setVolume(customView.mvolume)
    }

    override fun showVolume(value: String) {
        setVolume(value);
    }

    private fun setVolume(value: String) {
        tv_volume.text = "Volume set at: " + value + " %"
    }
}
