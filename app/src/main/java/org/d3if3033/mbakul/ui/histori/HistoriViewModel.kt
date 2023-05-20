package org.d3if3033.mbakul.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3033.mbakul.db.UntungDao

class HistoriViewModel(private val db: UntungDao) : ViewModel() {
    val data = db.getLastUntung()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}