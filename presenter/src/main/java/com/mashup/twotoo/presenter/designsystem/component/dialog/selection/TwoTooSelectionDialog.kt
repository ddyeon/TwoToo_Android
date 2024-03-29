package com.mashup.twotoo.presenter.history

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mashup.twotoo.presenter.designsystem.component.common.TextContainer
import com.mashup.twotoo.presenter.designsystem.component.dialog.selection.SelectionDialogButtonContent
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooRound7
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

@Composable
fun TwoTooSelectionDialog(
    selectionDialogButtonContents: List<SelectionDialogButtonContent> = SelectionDialogButtonContent.default,
    onDismissRequest: () -> Unit = {},
    properties: DialogProperties = DialogProperties(),
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        val textContainerModifier =
            Modifier
                .fillMaxWidth()
                .height(55.dp)
                .background(
                    color = Color.White,
                    shape = TwoTooRound7,
                )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 19.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                selectionDialogButtonContents.forEach {
                    TextContainer(
                        modifier = textContainerModifier.clickable { it.buttonAction() },
                        text = {
                            Text(
                                text = stringResource(id = it.titleId),
                                textAlign = TextAlign.Center,
                                color = it.color,
                                style = TwoTooTheme.typography.headLineNormal18,
                            )
                        },
                    )
                    if (selectionDialogButtonContents.last() != it) {
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TwoTooSelectionDialogPreview() {
    TwoTooSelectionDialog(
        onDismissRequest = { },
        selectionDialogButtonContents = SelectionDialogButtonContent.default,
    )
}
