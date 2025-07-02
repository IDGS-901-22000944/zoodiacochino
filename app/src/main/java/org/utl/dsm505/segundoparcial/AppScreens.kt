package org.utl.dsm505.segundoparcial


sealed class AppScreens {
    object PersonalDataScreen : AppScreens()
    object QuizScreen : AppScreens()
    object ResultsScreen : AppScreens()
}