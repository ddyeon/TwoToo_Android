package com.mashup.twotoo.presenter.garden

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.toolbar.TwoTooMainToolbar
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun GardenRoute(
    gardenViewModel: GardenViewModel,
    modifier: Modifier = Modifier,
    navigateToGarden: () -> Unit,
) {
    val state by gardenViewModel.collectAsState()
    GardenScreen(
        modifier = modifier.testTag(stringResource(id = R.string.garden)),
        navigateToGarden = navigateToGarden,
        state = state,
    )
}

@Composable
fun GardenScreen(
    state: GardenState,
    modifier: Modifier = Modifier,
    navigateToGarden: () -> Unit,
) {
    Scaffold(
        topBar = {
            TwoTooMainToolbar(text = stringResource(id = R.string.garden_title), onClickHelpIcon = {})
        },
        containerColor = TwoTooTheme.color.backgroundYellow,
    ) {
        LazyVerticalGrid(
            modifier = Modifier.padding(paddingValues = it).padding(horizontal = 24.dp, vertical = 30.dp),
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(13.dp),
            verticalArrangement = Arrangement.spacedBy(13.dp),
        ) {
            items(state.challengeCardInfos) { challengeInfo ->
                ChallengeCard(challengeInfo, navigateToGarden)
            }
        }
    }
}

@Preview(widthDp = 327, heightDp = 812)
@Composable
private fun PreviewGardenScreen() {
    GardenScreen(GardenState.default, navigateToGarden = {})
}
