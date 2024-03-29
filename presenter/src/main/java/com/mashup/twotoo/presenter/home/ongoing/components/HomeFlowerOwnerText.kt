package com.mashup.twotoo.presenter.home.ongoing.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.designsystem.theme.TwotooPink
import com.mashup.twotoo.presenter.home.model.UserType
import com.mashup.twotoo.presenter.home.model.UserType.ME
import com.mashup.twotoo.presenter.home.model.UserType.PARTNER

@Composable
fun HomeFlowerOwnerText(
    name: String,
    userType: UserType,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.background(
            color = TwoTooTheme.color.backgroundYellow,
            shape = TwoTooTheme.shape.small,
        ).wrapContentWidth().height(30.dp).padding(horizontal = 10.dp, vertical = 3.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            color = when (userType) {
                PARTNER -> {
                    TwoTooTheme.color.mainBrown
                }
                ME -> {
                    TwotooPink
                }
            },
            style = TwoTooTheme.typography.bodyNormal16,
            text = name,
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFlowerOwerPartnerText() {
    TwoTooTheme {
        HomeFlowerOwnerText(name = "공주", userType = PARTNER)
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFlowerOwnerText() {
    TwoTooTheme {
        HomeFlowerOwnerText(
            name = "공주",
            userType = ME,
        )
    }
}
