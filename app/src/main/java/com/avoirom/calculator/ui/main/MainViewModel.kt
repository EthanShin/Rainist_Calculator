package com.avoirom.calculator.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    // Display 화면에 보여지는 숫자
    private val _number = MutableLiveData<String>("0")
    val number: LiveData<String>
        get() = _number

    // 연산을 위한 첫번째, 두번째 숫자, 연산자
    var firstNumber: Int? = null
    var secondNumber: Int? = null
    var operator: Char? = null

    // 연산자의 존재여부, 직전에 '='가 눌렸는지에 대한 변수
    var hasOperator = false
    var isNextEqual = false

    fun selectOperand(num: Int) {
        if (hasOperator) {
            if (isNextEqual) {
                // '='의 결과 다음에는 결과값이 지워짐
                firstNumber = null
            } else {
                // '='를 제외한 연산자가 입력되면 다음 숫자를 받을 준비를 함
                secondNumber = null
            }
            _number.value = num.toString()
            hasOperator = false
        } else {
            _number.value = (number.value!!.toInt() * 10 + num).toString()
        }
    }

    fun selectOperator(op: Char) {
        hasOperator = true

        when (op) {
            '+', '-', 'x', '%' -> {
                firstNumber = number.value!!.toInt()
                secondNumber = null
                operator = op
                isNextEqual = false
            }
            '=' -> {
                isNextEqual = true
                runCalculate()
            }
            'c' -> {
                if (isNextEqual) {
                    // '='의 결과 다음에는 결과값이 지워짐
                    // 그 외의 경우 현재 입력 중인 값이 지워
                    firstNumber = 0
                }
                _number.value = "0"
                isNextEqual = false
            }
        }
    }

    private fun runCalculate() {
        if (firstNumber == null) firstNumber = number.value!!.toInt()
        else if (secondNumber == null) secondNumber = number.value!!.toInt()

        firstNumber = when (operator) {
            '+' -> firstNumber?.plus(secondNumber!!)
            '-' -> firstNumber?.minus(secondNumber!!)
            'x' -> firstNumber?.times(secondNumber!!)
            '%' -> firstNumber?.rem(secondNumber!!)
            else -> secondNumber!!
        }
        _number.value = firstNumber.toString()
    }
}
