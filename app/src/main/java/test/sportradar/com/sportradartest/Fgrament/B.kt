package test.sportradar.com.sportradartest.Fgrament

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_b.*
import test.sportradar.com.sportradartest.R

/**
 * Created by Dejan Krivec
 */
class B : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    fun setText(text: String) {
        tv_text.text = text
    }
}