package com.avoirom.calculator.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.avoirom.calculator.R
import com.avoirom.calculator.databinding.FragmentDisplayBinding
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

        val nameObserver = Observer<String> { number ->
            bind.numberView.text = number
        }

        // viewModel의 number변수를 바인딩
        viewModel.number.observe(viewLifecycleOwner, nameObserver)
    }

}
