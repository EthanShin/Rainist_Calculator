package com.avoirom.calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.avoirom.calculator.ui.main.CalculatorFragment
import com.avoirom.calculator.ui.main.DisplayFragment
import com.avoirom.calculator.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.display, DisplayFragment.newInstance())
                .commitNow()
            supportFragmentManager.beginTransaction()
                .add(R.id.calculator, CalculatorFragment.newInstance())
                .commitNow()
        }
    }

}
