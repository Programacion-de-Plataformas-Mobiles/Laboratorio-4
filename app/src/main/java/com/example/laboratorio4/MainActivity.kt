package com.example.laboratorio4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laboratorio4.ui.theme.Laboratorio4Theme

object DataSource {
    val mascotas = listOf(
        Mascota(1, "Buddy", "French Maltés", R.drawable.buddy1),
        Mascota(2, "Joao", "Chihuahua", R.drawable.chihuahua),
        Mascota(3, "Monte", "Bulldog", R.drawable.bulldog),
        Mascota(4, "Jose", "Husky", R.drawable.husky),
        Mascota(5, "Juan", "Labrador", R.drawable.labrador),
    )
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListaMascotasScreen(
                        mascotas = DataSource.mascotas,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ListaMascotasScreen(modifier: Modifier = Modifier, mascotas: List<Mascota>) {
    LazyColumn(modifier = modifier.padding(16.dp)) {
        items(mascotas) { mascota ->
            MascotaCard(mascota = mascota)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun MascotaCard(mascota: Mascota, modifier: Modifier = Modifier) {
    var adoptadoState by remember { mutableStateOf(mascota.adoptado) }

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = mascota.foto),
                contentDescription = "Foto de ${mascota.nombre}",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = mascota.nombre,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = mascota.raza,
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    if (!adoptadoState) {
                        adoptadoState = true
                    }
                },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(if (adoptadoState) "¡Adoptado! ❤️" else "Adoptar")
            }
        }
    }
}