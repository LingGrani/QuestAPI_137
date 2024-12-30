package com.example.p10internetdatabase.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p10internetdatabase.model.Mahasiswa
import com.example.p10internetdatabase.repository.MahasiswaRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class HomeUiState {
    data class Success(val mahasiswaList: List<Mahasiswa>) : HomeUiState()
    object Error : HomeUiState()
    object Loading : HomeUiState()
}

class HomeViewModel(private val mhs: MahasiswaRepository): ViewModel() {
    var mhsUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getMhs()
    }

    fun getMhs() {
        viewModelScope.launch {
            mhsUiState = HomeUiState.Loading
            mhsUiState = try {
                HomeUiState.Success(mhs.getMahasiswa())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

    fun deleteMhs(nim:String){
        viewModelScope.launch {
            try {
                mhs.deleteMahasiswa(nim)
                getMhs()
            } catch (e:IOException) {
                HomeUiState.Error
            } catch (e:HttpException) {
                HomeUiState.Error
            }
        }
    }
}