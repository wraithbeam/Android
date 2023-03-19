package com.example.droidquest

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.droidquest.models.Question
import com.example.droidquest.ui.theme.DroidQuestTheme
import com.example.droidquest.ui.theme.ImageButton
import com.example.droidquest.ui.theme.TextButton
import com.example.droidquest.ui.theme.makeToast

class MainActivity : ComponentActivity() {
    var currentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DroidQuestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    startQuiz()
                }
            }
        }
    }

    @Composable
    private fun startQuiz() {
        val questions = listOf(
            Question(R.string.question_1, true),
            Question(R.string.question_2, false),
            Question(R.string.question_3, false),
            Question(R.string.question_4, true),
            Question(R.string.question_5, true)
        )
        var question by remember { mutableStateOf(questions[currentQuestionIndex]) }
        drawQuestion(question = question) {
            question = questions[currentQuestionIndex]
        }
    }

    @Composable
    private fun drawQuestion(question: Question, onQuestionChane: () -> Unit) {
        val context = LocalContext.current
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(question.stringId),
                modifier = Modifier
                    .padding(10.dp)
                    .clickable {
                        currentQuestionIndex++
                        onQuestionChane.invoke()
                    },
                textAlign = TextAlign.Center
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                TextButton(R.string.answer_yes) {
                    checkAnswer(
                        context = context,
                        correctAnswer = question.answer,
                        userAnswer = true
                    )
                }
                TextButton(R.string.answer_no) {
                    checkAnswer(
                        context = context,
                        correctAnswer = question.answer,
                        userAnswer = false
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ImageButton(
                    stringId = R.string.control_back,
                    imageId = R.drawable.baseline_keyboard_arrow_left_24
                ) {
                    currentQuestionIndex--
                    onQuestionChane.invoke()
                }
                ImageButton(
                    stringId = R.string.control_next,
                    imageId = R.drawable.baseline_keyboard_arrow_right_24,
                    reversed = true
                ) {
                    currentQuestionIndex++
                    onQuestionChane.invoke()
                }
            }
        }
    }

    private fun checkAnswer(context: Context, correctAnswer: Boolean, userAnswer: Boolean) {
        if (correctAnswer == userAnswer)
            makeToast(context, R.string.result_correct)
        else
            makeToast(context, R.string.result_incorrect)
    }
}
