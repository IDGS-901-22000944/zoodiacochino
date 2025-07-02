package org.utl.dsm505.segundoparcial


import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.utl.dsm505.segundoparcial.R
import org.utl.dsm505.segundoparcial.UserData
import org.utl.dsm505.segundoparcial.AppScreens

@Composable
fun PersonalDataScreen(
    onNavigate: (AppScreens) -> Unit,
    onSaveUserData: (UserData) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var paternalLastName by remember { mutableStateOf("") }
    var maternalLastName by remember { mutableStateOf("") }
    var birthDay by remember { mutableStateOf("") }
    var birthMonth by remember { mutableStateOf("") }
    var birthYear by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }

    val context = LocalContext.current
    val fileManager = remember { FileManager(context) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Datos Personales",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = paternalLastName,
            onValueChange = { paternalLastName = it },
            label = { Text("Apellido Paterno") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = maternalLastName,
            onValueChange = { maternalLastName = it },
            label = { Text("Apellido Materno") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        Text(
            text = "Fecha de Nacimiento",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 4.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = birthDay,
                onValueChange = { if (it.length <= 2) birthDay = it },
                label = { Text("Día") },
                modifier = Modifier.weight(0.3f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = birthMonth,
                onValueChange = { if (it.length <= 2) birthMonth = it },
                label = { Text("Mes") },
                modifier = Modifier.weight(0.3f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            OutlinedTextField(
                value = birthYear,
                onValueChange = { if (it.length <= 4) birthYear = it },
                label = { Text("Año") },
                modifier = Modifier.weight(0.4f)
            )
        }

        Text(
            text = "Sexo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = gender == "Masculino",
                onClick = { gender = "Masculino" }
            )
            Text("Masculino", modifier = Modifier.padding(start = 8.dp))

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = gender == "Femenino",
                onClick = { gender = "Femenino" }
            )
            Text("Femenino", modifier = Modifier.padding(start = 8.dp))
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    name = ""
                    paternalLastName = ""
                    maternalLastName = ""
                    birthDay = ""
                    birthMonth = ""
                    birthYear = ""
                    gender = ""
                },
                modifier = Modifier.weight(0.45f)
            ) {
                Text("Limpiar")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    val userData = UserData(
                        name = name,
                        paternalLastName = paternalLastName,
                        maternalLastName = maternalLastName,
                        birthDay = birthDay.toIntOrNull() ?: 1,
                        birthMonth = birthMonth.toIntOrNull() ?: 1,
                        birthYear = birthYear.toIntOrNull() ?: 2000,
                        gender = gender
                    )
                    onSaveUserData(userData)
                    fileManager.saveUserData(userData)
                    onNavigate(AppScreens.QuizScreen)
                },
                enabled = name.isNotBlank() && paternalLastName.isNotBlank() &&
                        birthDay.isNotBlank() && birthMonth.isNotBlank() &&
                        birthYear.isNotBlank() && gender.isNotBlank(),
                modifier = Modifier.weight(0.45f)
            ) {
                Text("Siguiente")
            }
        }
    }
}