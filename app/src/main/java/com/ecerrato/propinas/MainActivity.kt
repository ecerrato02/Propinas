package com.ecerrato.propinas

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.ecerrato.propinas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val calcButton: Button = binding.calcButton

        calcButton.setOnClickListener {



            if(binding.editTextTextCoste.text.toString().isEmpty()){
                val text = "Debes meter un valor para calcular"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(this, text, duration)
                toast.show()
            }else {

                val radioButton: RadioButton = findViewById(binding.radioGroupForm.checkedRadioButtonId)
                val precio = binding.editTextTextCoste.text.toString().toDouble()

                var propina: Double = when (radioButton.text) {
                    "Amazing (20%)" -> precio * 0.20
                    "Good (18%)" -> precio * 0.12
                    "Okay (15%)" -> precio * 0.15
                    else -> 0.0
                }

                if (binding.switchRedonear.isChecked) {
                    val output: String = "Propina a pagar: " + Math.round(propina).toDouble() + "€."
                    binding.textViewTips.text = output


                } else {
                    val output: String =
                        "Propina a pagar: " + (Math.round(propina * 100.0) / 100.0).toString() + "€."
                    binding.textViewTips.text = output

                }
            }
        }
    }
}