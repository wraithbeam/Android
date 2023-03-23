package com.example.droidquest

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.droidquest.ui.theme.DroidQuestTheme
import com.example.droidquest.ui.theme.TextButton

class TakeInActivity : ComponentActivity() {
    var wasDecided = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val answerBoolean = intent.getBooleanExtra(EXTRA_ANSWER, false)
        wasDecided = savedInstanceState?.getBoolean(WAS_DECIDED, false) ?: false
        setContent {
            DroidQuestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var answerString by remember { mutableStateOf("") }
                    TakeIn(answerString) {
                        answerString =
                            if (answerBoolean) getString(R.string.result_correct)
                            else getString(R.string.result_incorrect)
                    }
                }
            }
        }
    }

    @Composable
    fun TakeIn(answerString: String, onShowAnswer: () -> Unit) {
        Column(
            modifier = Modifier
                .width(IntrinsicSize.Max),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = getString(R.string.takein_warning))
            Text(text = answerString, modifier = Modifier.padding(20.dp))
            TextButton(R.string.takein_show_answer) {
                wasDecided = true
                onShowAnswer()
                answerShown()
            }
        }
    }

    private fun answerShown() {
        val data = Intent().putExtra(EXTRA_ANSWER_SHOWN, wasDecided)
        setResult(Activity.RESULT_OK, data)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(WAS_DECIDED, wasDecided)
    }

    companion object {
        private const val EXTRA_ANSWER = "droidquest.answer"
        private const val EXTRA_ANSWER_SHOWN = "droidquest.answer_shown"
        private const val WAS_DECIDED = "WAS_DEC"

        fun newIntent(context: Context, answer: Boolean): Intent {
            return Intent(context, TakeInActivity::class.java)
                .putExtra(EXTRA_ANSWER, answer)
        }

        fun wasAnswerShown(result: Intent) =
            result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false)
    }
}