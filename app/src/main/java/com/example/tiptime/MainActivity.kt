package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateTip() }

    }

    private fun calculateTip(){
        val cost=binding.costOfService.text.toString().toDoubleOrNull()
        if(cost==null){
            displayTip(0.0)
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.twenty_percent -> 0.20
            R.id.eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip=tipPercentage*cost

        if(binding.roundUpSwitch.isChecked){
            tip=kotlin.math.ceil(tip)
        }


        displayTip(tip)
    }
    private fun displayTip(tip:Double){
        val formattedTip= NumberFormat.getCurrencyInstance(Locale("en","IN")).format(tip)
        binding.tipAmount.text= getString(R.string.tip_amount,formattedTip)
    }

}

