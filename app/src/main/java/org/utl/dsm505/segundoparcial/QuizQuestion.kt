package org.utl.dsm505.segundoparcial

data class QuizQuestion(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctAnswer: Int
)
