package org.utl.dsm505.segundoparcial


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.utl.dsm505.segundoparcial.R
import org.utl.dsm505.segundoparcial.UserData
import org.utl.dsm505.segundoparcial.AppScreens
import org.utl.dsm505.segundoparcial.ZodiacCalculator
import androidx.compose.runtime.remember

@Composable
fun ResultsScreen(
    userData: UserData,
    score: Int,
    onNavigate: (AppScreens) -> Unit
) {
    val zodiacSign = ZodiacCalculator.calculateChineseZodiac(userData.birthYear)
    val context = LocalContext.current
    val fileManager = remember { FileManager(context) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Resultados",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Hola ${userData.fullName()}",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Tienes ${userData.calculateAge()} años y tu signo zodiacal es:",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Mostrar imagen del signo zodiacal
        val imageRes = context.resources.getIdentifier(
            zodiacSign.imageRes,
            "drawable",
            context.packageName
        )

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = zodiacSign.name,
            modifier = Modifier
                .size(120.dp)
                .padding(16.dp)
        )

        Text(
            text = zodiacSign.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Años correspondientes: ${zodiacSign.years}",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Calificación del examen: $score de 6",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = {
                onNavigate(AppScreens.PersonalDataScreen)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Finalizar")
        }

        // Mostrar datos guardados en archivo (opcional)
        Divider(modifier = Modifier.padding(vertical = 16.dp))
        Text(
            text = "Datos guardados:",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(text = fileManager.readUserData())
    }
}