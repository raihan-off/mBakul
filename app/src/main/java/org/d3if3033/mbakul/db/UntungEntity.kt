package org.d3if3033.mbakul.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "untung")
data class UntungEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal:Long = System.currentTimeMillis(),
    var modalJualan: Float,
    var angkaPenjualan: Float,
    var hargaJual: Float
)
