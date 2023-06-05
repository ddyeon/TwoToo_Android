package com.mashup.twotoo.presenter.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun TwoTooTextField(
    text: String,
    updateText: (String) -> Unit,
    textHint: String,
    modifier: Modifier = Modifier,
) {
    TextField(
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedTextColor = Color(0xFF443018),
            unfocusedTextColor = Color(0xFF443018),
        ),
        shape = RoundedCornerShape(10.dp),
        value = text,
        onValueChange = updateText,
        placeholder = {
            Text(
                text = textHint,
                color = Color(0xFFD9D8D7),
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTwoTooTextField() {
    TwoTooTheme {
        Box(modifier = Modifier.fillMaxSize().background(color = Color(0xFFFCF5E6))) {
            var text by remember { mutableStateOf("") }
            TwoTooTextField(
                modifier = Modifier.width(329.dp).height(297.dp),
                text = text,
                textHint = "테스트용입니다 아무거나 입력해주세요",
                updateText = { text = it },
            )
        }
    }
}