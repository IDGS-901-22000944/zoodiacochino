package org.utl.dsm505.segundoparcial


import android.content.Context
import org.utl.dsm505.segundoparcial.UserData

class FileManager(private val context: Context) {
    private val fileName = "user_data.txt"

    fun saveUserData(userData: UserData) {
        val data = """
            Nombre: ${userData.name}
            Apellido Paterno: ${userData.paternalLastName}
            Apellido Materno: ${userData.maternalLastName}
            Fecha de Nacimiento: ${userData.birthDay}/${userData.birthMonth}/${userData.birthYear}
            Género: ${userData.gender}
        """.trimIndent()

        context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(data.toByteArray())
        }
    }

    fun readUserData(): String {
        return try {
            context.openFileInput(fileName).bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            "No hay datos guardados"
        }
    }
}