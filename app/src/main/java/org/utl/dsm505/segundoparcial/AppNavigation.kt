package org.utl.dsm505.segundoparcial


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import org.utl.dsm505.segundoparcial.UserData
import org.utl.dsm505.segundoparcial.PersonalDataScreen
import org.utl.dsm505.segundoparcial.QuizScreen
import org.utl.dsm505.segundoparcial.ResultsScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun AppNavigation() {
    var currentScreen by remember { mutableStateOf<AppScreens>(AppScreens.PersonalDataScreen) }
    var userData by remember { mutableStateOf(UserData()) }
    var quizScore by remember { mutableStateOf(0) }

    when (currentScreen) {
        AppScreens.PersonalDataScreen -> PersonalDataScreen(
            onNavigate = { screen ->
                currentScreen = screen
            },
            onSaveUserData = { data ->
                userData = data
            }
        )
        AppScreens.QuizScreen -> QuizScreen(
            onNavigate = { screen ->
                currentScreen = screen
            },
            calculateScore = { score ->
                quizScore = score
            }
        )
        AppScreens.ResultsScreen -> ResultsScreen(
            userData = userData,
            score = quizScore,
            onNavigate = { screen ->
                currentScreen = screen
                // Resetear datos si es necesario
                userData = UserData()
                quizScore = 0
            }
        )
    }
}