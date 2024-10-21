package com.ecerrato.propinas

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
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

        val resultadoTexto: TextView = binding.textViewTips

        calcButton.setOnClickListener {
            val form: RadioGroup = binding.radioGroupForm
            val idSeleccionada: Int = form.checkedRadioButtonId
            val redondear: Switch = binding.switchRedonear
            val radioButton: RadioButton = findViewById(idSeleccionada)
            val numero: TextView = binding.editTextTextCoste
            val precio = numero.text.toString().toDouble()

            var propina: Double = when (radioButton.text) {
                "Amazing (20%)" -> precio * 0.20
                "Good (18%)" -> precio * 0.12
                "Okay (15%)" -> precio * 0.15
                else -> 0.0
            }

            if(redondear.isChecked){
                propina = Math.round(propina).toDouble()
                resultadoTexto.setText(propina.toString())
            }else{
                val output: String = (Math.round(propina * 10.0) / 10.0).toString()
                resultadoTexto.text = output
            }
        }
    }
}