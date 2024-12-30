package com.example.p10internetdatabase.ui.view

import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.p10internetdatabase.ui.component.CustomTopAppBar
import com.example.p10internetdatabase.ui.navigation.DestinasiNavigasi
import com.example.p10internetdatabase.ui.viewmodel.InsertUiEvent
import com.example.p10internetdatabase.ui.viewmodel.InsertUiState
import com.example.p10internetdatabase.ui.viewmodel.InsertViewModel
import com.example.p10internetdatabase.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DeestinasiEntry: DestinasiNavigasi {
    override val route = "item_entry"
    override val titleRes = "Entry Mhs"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryMhsScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DeestinasiEntry.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack,
                modifier = Modifier
            )
        }
    ){ innerPadding ->
        EntryBody(
            insertUiState = viewModel.uiState,
            onMhsValueChange = viewModel::updateInsertMhsState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertMhs()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}


@Composable
fun EntryBody(
    insertUiState: InsertUiState,
    onMhsValueChange: (InsertUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInput(
            insertUiEvent = insertUiState.insertUiEvent,
            onMhsValueChange = onMhsValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button (
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput (
    insertUiEvent: InsertUiEvent,
    modifier: Modifier = Modifier,
    onMhsValueChange: (InsertUiEvent) -> Unit = {},
    enable: Boolean = true
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertUiEvent.nama,
            onValueChange = {onMhsValueChange(insertUiEvent.copy(nama = it))},
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enable,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.nim,
            onValueChange = {onMhsValueChange(insertUiEvent.copy(nim = it))},
            label = { Text("NIM") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enable,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.jenisKelamin,
            onValueChange = {onMhsValueChange(insertUiEvent.copy(jenisKelamin = it))},
            label = { Text("Jenis Kelamin") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enable,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.alamat,
            onValueChange = {onMhsValueChange(insertUiEvent.copy(alamat = it))},
            label = { Text("Alamat") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enable,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.kelas,
            onValueChange = {onMhsValueChange(insertUiEvent.copy(kelas = it))},
            label = { Text("Kelas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enable,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.angkatan,
            onValueChange = {onMhsValueChange(insertUiEvent.copy(angkatan = it))},
            label = { Text("Angkatan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enable,
            singleLine = true
        )
        if (enable) {
            Text (
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}
