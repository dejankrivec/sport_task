package test.sportradar.com.sportradartest

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import test.sportradar.com.sportradartest.Fgrament.A
import test.sportradar.com.sportradartest.Fgrament.B

class MainActivity : FragmentActivity(), A.FragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onButtonClicked(text: String) {
        val fragmentB = getSupportFragmentManager().findFragmentById(R.id.fragment_B) as B
        fragmentB.setText(text)
    }
}
