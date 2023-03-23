package com.example.droidquest

import android.content.Context
import android.os.Bundle
import android.util.Log
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
        Log.d(TAG, "onCreate called")
        currentQuestionIndex = savedInstanceState?.getInt(QUESTION_NUMBER_KEY, 0) ?: 0
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
            Question(R.string.question_5, true),
            Question(R.string.question_5, true),
            Question(R.string.question_6, true),
            Question(R.string.question_7, true),
            Question(R.string.question_8, true),
            Question(R.string.question_9, true),
            Question(R.string.question_10, false)
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
                    stringId = R.string.empty,
                    imageId = R.drawable.baseline_keyboard_arrow_left_24
                ) {
                    currentQuestionIndex--
                    onQuestionChane.invoke()
                }
                ImageButton(
                    stringId = R.string.empty,
                    imageId = R.drawable.baseline_keyboard_arrow_right_24,
                    reversed = true
                ) {
                    currentQuestionIndex++
                    onQuestionChane.invoke()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
        outState.putInt(QUESTION_NUMBER_KEY, currentQuestionIndex)
    }

    private fun checkAnswer(context: Context, correctAnswer: Boolean, userAnswer: Boolean) {
        if (correctAnswer == userAnswer)
            makeToast(context, R.string.result_correct)
        else
            makeToast(context, R.string.result_incorrect)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy    called")
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val QUESTION_NUMBER_KEY = "QNK"
    }
}
