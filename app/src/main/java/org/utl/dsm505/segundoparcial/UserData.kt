package org.utl.dsm505.segundoparcial

data class UserData(
    val name: String = "",
    val paternalLastName: String = "",
    val maternalLastName: String = "",
    val birthDay: Int = 1,
    val birthMonth: Int = 1,
    val birthYear: Int = 2000,
    val gender: String = ""
) {
    //devuelve el nombre completo del usuario como una sola cadena.
    fun fullName(): String = "$name $paternalLastName $maternalLastName"
//Calcula la edad del usuario con base en el año actual y el año de nacimiento.
    fun calculateAge(): Int {
        val currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)
        return currentYear - birthYear
    }
}