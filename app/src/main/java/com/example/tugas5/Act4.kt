package com.example.tugas5

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import java.util.Calendar

@Composable
fun FormBiodata() {
    val context = LocalContext.current

    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tanggalLahir by remember { mutableStateOf("") }
    var pengalaman by remember { mutableStateOf("") }

    var androidSkill by remember { mutableStateOf(false) }
    var uiuxSkill by remember { mutableStateOf(false) }
    var webSkill by remember { mutableStateOf(false) }
    var dropdownTerbuka by remember { mutableStateOf(false) }

    val pilihanPengalaman =
        stringArrayResource(R.array.pilihan_pengalaman)

    val valid = namaDepan.isNotBlank() &&
            namaBelakang.isNotBlank() &&
            email.isNotBlank() &&
            tanggalLahir.isNotBlank() &&
            pengalaman.isNotBlank() &&
            (androidSkill || uiuxSkill || webSkill)

    Scaffold(
        containerColor = colorResource(R.color.warna_latar)
    ) { paddingScaffold ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingScaffold)
        ) {
            Image(
                painter = painterResource(R.drawable.header_spider),
                contentDescription = stringResource(
                    R.string.deskripsi_gambar_header
                ),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.tinggi_header))
                    .align(Alignment.TopCenter)
            )

            Card(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(
                        top = dimensionResource(R.dimen.posisi_kartu)
                    )
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(R.dimen.padding_layar)
                    ),
                shape = RoundedCornerShape(
                    dimensionResource(R.dimen.radius_kartu)
                ),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.warna_kartu)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = dimensionResource(R.dimen.elevasi_kartu)
                )
            ) {
