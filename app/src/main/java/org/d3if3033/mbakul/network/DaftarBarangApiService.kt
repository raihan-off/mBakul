package org.d3if3033.mbakul.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3033.mbakul.model.DaftarBarang
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://raw.githubusercontent.com/" + "raihan-off/barang-jualan/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DaftarBarangApiService {
    @GET("barang-jualan.json")
    suspend fun getDaftarBarang() : List<DaftarBarang>
}

object DaftarBarangApi {
    val service: DaftarBarangApiService by lazy {
        retrofit.create(DaftarBarangApiService::class.java)
    }

    fun getDaftarBarangUrl(imageId: String): String {
        return "$BASE_URL$imageId.jpg"
    }
    enum class ApiStatus { LOADING, SUCCESS, FAILED }
}