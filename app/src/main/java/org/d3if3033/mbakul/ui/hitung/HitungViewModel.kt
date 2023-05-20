package org.d3if3033.mbakul.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3033.mbakul.db.UntungDao
import org.d3if3033.mbakul.db.UntungEntity
import org.d3if3033.mbakul.model.HasilUntung
import org.d3if3033.mbakul.model.hitungUntung

class HitungViewModel(private val db: UntungDao) : ViewModel() {

    private val HasilUntung = MutableLiveData<HasilUntung?>()

    fun hitungUntung(angkaPenjualan : Float, modalJual : Float, hargaJual : Float) {
        val dataUntung = UntungEntity(
            angkaPenjualan = angkaPenjualan,
            modalJualan = modalJual,
            hargaJual = hargaJual
        )
        HasilUntung.value = dataUntung.hitungUntung()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataUntung)
            }
        }
    }

    fun getHasilUntung(): LiveData<HasilUntung?> = HasilUntung

}