package test.sportradar.com.sportradartest.Fgrament

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_a.*
import test.sportradar.com.sportradartest.R

/**
 * Created by Dejan Krivec
 */
class A : Fragment(), View.OnClickListener {

    var listener: FragmentListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_click_me.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        listener?.onButtonClicked(ed_text.text.toString())
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as FragmentListener
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface FragmentListener {
        fun onButtonClicked(text: String)
    }
}