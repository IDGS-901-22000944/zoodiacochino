package org.utl.dsm505.segundoparcial


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.utl.dsm505.segundoparcial.QuizQuestion
import org.utl.dsm505.segundoparcial.AppScreens

@Composable
fun QuizScreen(
    onNavigate: (AppScreens) -> Unit,
    calculateScore: (Int) -> Unit
) {
    val questions = remember {
        listOf(
            QuizQuestion(
                id = 1,
                question = "¿Cuál es el planeta más grande del sistema solar?",
                options = listOf("Tierra", "Marte", "Júpiter", "Venus"),
                correctAnswer = 2
            ),
            QuizQuestion(
                id = 2,
                question = "¿Qué autor escribió Cien años de soledad?",
                options = listOf(
                    "Mario Vargas Llosa",
                    "Gabriel García Márquez",
                    "Julio Cortázar",
                    "Isabel Allende"
                ),
                correctAnswer = 1
            ),
            QuizQuestion(
                id = 3,
                question = "¿Cuál es el resultado de 8 × 7?",
                options = listOf("54", "56", "64", "48"),
                correctAnswer = 1
            ),
            QuizQuestion(
                id = 4,
                question = "¿En qué año comenzó la Segunda Guerra Mundial?",
                options = listOf("1918", "1939", "1945", "1929"),
                correctAnswer = 1
            ),
            QuizQuestion(
                id = 5,
                question = "¿Qué órgano del cuerpo humano bombea la sangre?",
                options = listOf("Pulmones", "Estómago", "Hígado", "Corazón"),
                correctAnswer = 3
            ),
            QuizQuestion(
                id = 6,
                question = "¿Cuál es el lenguaje de programación más utilizado para desarrollo web front-end?",
                options = listOf("Python", "Java", "JavaScript", "C++"),
                correctAnswer = 2
            )
        )
    }

    val userAnswers = remember { mutableStateMapOf<Int, Int?>() }
    var showResults by remember { mutableStateOf(false) }
    var score by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Examen de Conocimientos",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        questions.forEach { question ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = "${question.id}. ${question.question}",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                question.options.forEachIndexed { index, option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = userAnswers[question.id] == index,
                            onClick = {
                                userAnswers[question.id] = index
                            }
                        )
                        Text(
                            text = option,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
            Divider(modifier = Modifier.padding(vertical = 8.dp))
        }

        if (showResults) {
            Text(
                text = "Tu puntuación: $score de ${questions.size}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        Button(
            onClick = {
                if (!showResults) {
                    // Calcular puntuación
                    score = questions.count { question ->
                        userAnswers[question.id] == question.correctAnswer
                    }
                    calculateScore(score)
                    showResults = true
                } else {
                    onNavigate(AppScreens.ResultsScreen)
                }
            },
            enabled = userAnswers.size == questions.size,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(if (showResults) "Ver Resultados" else "Terminar Examen")
        }
    }
}