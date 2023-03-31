package org.d3if3033.mbakul

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import org.d3if3033.mbakul.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener { catatPenjualan() }
    }

    private fun catatPenjualan() {
        val angkaPenjualan = binding.hasilPenjualanInp.text.toString()
        if (TextUtils.isEmpty(angkaPenjualan)){
            Toast.makeText(this, R.string.input_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val modalJualan = binding.modalInp.text.toString()
        if (TextUtils.isEmpty(modalJualan)){
            Toast.makeText(this, R.string.input_invalid, Toast.LENGTH_LONG).show()
        }

        val hargaJual = binding.hargaJualInp.text.toString()
        if (TextUtils.isEmpty(hargaJual)){
            Toast.makeText(this, R.string.input_invalid, Toast.LENGTH_LONG).show()
        }

        val keuntungan = (hargaJual.toFloat() * angkaPenjualan.toFloat()) - (modalJualan.toFloat() * angkaPenjualan.toFloat())

        binding.totalTextView.text = getString(R.string.total_x, keuntungan)
    }
}