package com.avoirom.calculator.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.avoirom.calculator.R
import com.avoirom.calculator.databinding.FragmentDisplayBinding
import kotlinx.android.synthetic.main.fragment_display.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DisplayFragment : Fragment() {

    companion object {
        fun newInstance() = DisplayFragment()
    }

    val viewModel: MainViewModel by sharedViewModel()
    private lateinit var bind: FragmentDisplayBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_display, container, false)
        return bind.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // SharedPreferences를 이용하여 값을 앱을 종료한 시점의 값을 저장하고, 받아옴
        val sharedPreferences = activity!!.getSharedPreferences("calculator", Context.MODE_PRIVATE)
        viewModel.initValue(sharedPreferences.getString("number", "0").toString())

        val nameObserver = Observer<String> { number ->
            bind.numberView.text = number
            // 변경이 일어날 때, 값을 저장함
            sharedPreferences.edit().putString("number", number).apply()
        }

        // viewModel의 number변수를 바인딩
        viewModel.number.observe(viewLifecycleOwner, nameObserver)
    }

}
