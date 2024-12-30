package com.example.p10internetdatabase.service

import com.example.p10internetdatabase.model.Mahasiswa
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface MahasiswaService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json",
    )
    @GET("bacamahasiswa.php")
    suspend fun getMahasiswa(): List<Mahasiswa>

    @GET("baca1mahasiswa.php/{nim}")
    suspend fun getMahasiswaById(@Query("nim")nim:String): Mahasiswa

    @POST("insertmahasiswa.php")
    suspend fun insertMahasiswa(@Body mahasiswa: Mahasiswa)

    @PUT("updatetmahasiswa.php/{nim}")
    suspend fun updateMahasiswa(@Query("nim")nim:String, @Body mahasiswa: Mahasiswa)

    @DELETE("deletemahasiswa.php/{nim}")
    suspend fun deleteMahasiswa(@Query("nim")nim:String): Response<Void>
}