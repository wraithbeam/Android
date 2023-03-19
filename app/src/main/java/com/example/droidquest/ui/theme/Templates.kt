package com.example.droidquest.ui.theme

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource

@Composable
fun TextButton(stringId: Int, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = stringResource(stringId))
    }
}

@Composable
fun ImageButton(imageId: Int, stringId: Int, reversed: Boolean = false, onClick: () -> Unit) {
    if (reversed)
        Button(onClick = onClick) {
            Row {
                Text(text = stringResource(stringId))
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = stringResource(id = stringId)
                )
            }
        }
    else
        Button(onClick = onClick) {
            Row {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = stringResource(id = stringId)
                )
                Text(text = stringResource(stringId))
            }
        }
}

fun makeToast(context: Context, stringId: Int) {
    val text = context.resources.getString(stringId)
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}