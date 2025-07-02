package org.utl.dsm505.segundoparcial


object ZodiacCalculator {
    // Lista de signos zodiacales chinos con su nombre, imagen y años correspondientes
    fun calculateChineseZodiac(birthYear: Int): ChineseZodiacSign {
        val zodiacs = listOf(
            ChineseZodiacSign("Rata", "rata", "2008, 1996, 1984, 1972, 1960"),
            ChineseZodiacSign("Buey", "buey", "2009, 1997, 1985, 1973, 1961"),
            ChineseZodiacSign("Tigre", "tigre", "2010, 1998, 1986, 1974, 1962"),
            ChineseZodiacSign("Conejo", "conejo", "2011, 1999, 1987, 1975, 1963"),
            ChineseZodiacSign("Dragón", "dragon", "2012, 2000, 1988, 1976, 1964"),
            ChineseZodiacSign("Serpiente", "serpiente", "2013, 2001, 1989, 1977, 1965"),
            ChineseZodiacSign("Caballo", "caballo", "2014, 2002, 1990, 1978, 1966"),
            ChineseZodiacSign("Cabra", "cabra", "2015, 2003, 1991, 1979, 1967"),
            ChineseZodiacSign("Mono", "mono", "2016, 2004, 1992, 1980, 1968"),
            ChineseZodiacSign("Gallo", "gallo", "2017, 2005, 1993, 1981, 1969"),
            ChineseZodiacSign("Perro", "perro", "2018, 2006, 1994, 1982, 1970"),
            ChineseZodiacSign("Cerdo", "cerdo", "2019, 2007, 1995, 1983, 1971")
        )
        // Cálculo del índice correspondiente al signo zodiacal según el año de nacimiento
        val index = (birthYear - 1960) % 12
        return zodiacs[index]
    }
}

data class ChineseZodiacSign(
    val name: String,
    val imageRes: String,
    val years: String
)