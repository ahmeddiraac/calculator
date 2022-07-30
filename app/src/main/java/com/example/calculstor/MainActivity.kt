package com.example.calculstor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tv_input: TextView? = null

     var lastNumeric :Boolean = false
     var lastdot:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_input= findViewById(R.id.Tv_input)
    }
    fun Ondigit(view: View){
    tv_input?.append((view as Button).text)
        lastNumeric = true
        lastdot = false


    }
    fun onClear(view: View){
        tv_input?.text = ""
    }
    fun ondecimal(view: View){
        if(lastNumeric && !lastdot){
            tv_input?.append(".")
            lastNumeric = false
            lastdot = true
        }
    }

    fun onOperator(view: View){
        tv_input?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())){
                tv_input?.append((view as Button).text)
                lastNumeric = false
                lastdot = true
            }
        }
    }

    fun onEqual(view: View){
        if (lastNumeric){
            var tvvalue = tv_input?.text.toString()
            var prefix = ""

            try {
                if (tvvalue.startsWith("-"))  {
                    prefix = "-"

                    tvvalue = tvvalue.substring(1)
                }
                if (tvvalue.contains("-")){
                    val splitValue = tvvalue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix+one
                    }

                    tv_input?.text = removeZero((one.toDouble() - two.toDouble()).toString())
                }

                else if(tvvalue.contains("+")){
                    val splitValue = tvvalue.split("+")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix+one
                    }

                    tv_input?.text = removeZero((one.toDouble() + two.toDouble()).toString())
                }
                else if(tvvalue.contains("/")){
                    val splitValue = tvvalue.split("/")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix+one
                    }

                    tv_input?.text = removeZero((one.toDouble() / two.toDouble()).toString())
                }
                else if(tvvalue.contains("X")){
                    val splitValue = tvvalue.split("X")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if (prefix.isNotEmpty()){
                        one = prefix+one
                    }

                    tv_input?.text = removeZero((one.toDouble() * two.toDouble()).toString())
                }


            }catch (e: ArithmeticException){
                e.printStackTrace()
            }

        }



}

    private fun removeZero(result: String): String {
        var value = result
        if (result.contains(".0")){
            value = result.substring(0,result.length - 2)

        }
        return value
    }


    private fun isOperatorAdded(value:String) :Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    ||value.contains("*")
                    ||value.contains("+")
                    ||value.contains("-")
        }
    }

}