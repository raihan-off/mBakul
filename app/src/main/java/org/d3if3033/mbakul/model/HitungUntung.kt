package org.d3if3033.mbakul.model

import org.d3if3033.mbakul.db.UntungEntity

fun UntungEntity.hitungUntung(): HasilUntung{
    val keuntungan = hargaJual.toFloat() * angkaPenjualan.toFloat() - modalJualan.toFloat()
    return HasilUntung(keuntungan)
}