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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(
                            max = dimensionResource(
                                R.dimen.tinggi_maksimum_kartu
                            )
                        )
                        .verticalScroll(rememberScrollState())
                        .padding(
                            dimensionResource(R.dimen.padding_kartu)
                        ),
                    verticalArrangement = Arrangement.spacedBy(
                        dimensionResource(R.dimen.jarak_kolom)
                    )
                ) {
                    Text(
                        text = stringResource(R.string.judul_form),
                        modifier = Modifier.fillMaxWidth(),
                        color = colorResource(R.color.warna_judul),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = stringResource(R.string.subjudul_form),
                        modifier = Modifier.fillMaxWidth(),
                        color = colorResource(R.color.warna_teks_sekunder),
                        style = MaterialTheme.typography.bodySmall
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            dimensionResource(R.dimen.jarak_field)
                        )
                    ) {
                        OutlinedTextField(
                            value = namaDepan,
                            onValueChange = { namaDepan = it },
                            label = {
                                Text(stringResource(R.string.nama_depan))
                            },
                            singleLine = true,
                            modifier = Modifier.weight(1f)
                        )

                        OutlinedTextField(
                            value = namaBelakang,
                            onValueChange = { namaBelakang = it },
                            label = {
                                Text(stringResource(R.string.nama_belakang))
                            },
                            singleLine = true,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = {
                            Text(stringResource(R.string.email))
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(
                            dimensionResource(R.dimen.jarak_field)
                        )
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            OutlinedTextField(
                                value = tanggalLahir,
                                onValueChange = {},
                                readOnly = true,
                                enabled = false,
                                label = {
                                    Text(
                                        stringResource(R.string.tanggal_lahir)
                                    )
                                },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clickable {
                                        val calendar = Calendar.getInstance()

                                        DatePickerDialog(
                                            context,
                                            { _, year, month, day ->
                                                tanggalLahir = String.format("%02d/%02d/%d", day, month + 1, year)
                                            },
                                            calendar.get(Calendar.YEAR),
                                            calendar.get(Calendar.MONTH),
                                            calendar.get(Calendar.DAY_OF_MONTH)
                                        ).show()
                                    }
                            )
                        }

                        Box(modifier = Modifier.weight(1f)) {
                            OutlinedTextField(
                                value = pengalaman,
                                onValueChange = {},
                                readOnly = true,
                                enabled = false,
                                label = {
                                    Text(
                                        stringResource(R.string.pengalaman)
                                    )
                                },
                                modifier = Modifier.fillMaxWidth()
                            )

                            Box(
                                modifier = Modifier
                                    .matchParentSize()
                                    .clickable {
                                        dropdownTerbuka = true
                                    }
                            )

                            DropdownMenu(
                                expanded = dropdownTerbuka,
                                onDismissRequest = {
                                    dropdownTerbuka = false
                                }
                            ) {
                                pilihanPengalaman.forEach { pilihan ->
                                    DropdownMenuItem(
                                        text = { Text(pilihan) },
                                        onClick = {
                                            pengalaman = pilihan
                                            dropdownTerbuka = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Text(
                        text = stringResource(R.string.label_skill),
                        color = colorResource(R.color.warna_teks),
                        fontWeight = FontWeight.SemiBold
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SkillItem(
                            nama = stringResource(R.string.skill_android),
                            dipilih = androidSkill,
                            saatDiubah = { androidSkill = it }
                        )

                        SkillItem(
                            nama = stringResource(R.string.skill_uiux),
                            dipilih = uiuxSkill,
                            saatDiubah = { uiuxSkill = it }
                        )

                        SkillItem(
                            nama = stringResource(R.string.skill_web),
                            dipilih = webSkill,
                            saatDiubah = { webSkill = it }
                        )
                    }

                    Button(
                        onClick = {
                            Toast.makeText(context, "Biodata berhasil diproses!", Toast.LENGTH_SHORT).show()
                        },
                        enabled = valid,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(
                                dimensionResource(R.dimen.tinggi_tombol)
                            ),
                        shape = RoundedCornerShape(
                            dimensionResource(R.dimen.radius_tombol)
                        ),
                        colors = ButtonDefaults.buttonColors(
                            disabledContainerColor = colorResource(
                                R.color.warna_tombol_nonaktif
                            ),
                            disabledContentColor = colorResource(
                                R.color.warna_teks_tombol_nonaktif
                            )
                        )
                    ) {
                        Text(stringResource(R.string.tombol_lanjut))
                    }
                }
            }
        }
    }
}
