package com.mashup.twotoo.presenter.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.TwoTooImageView
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme

const val MAX_COUNT = 3

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HorizontalPagerContent(pagerState: PagerState) {
    val pageDescription = remember {
        mutableStateOf(R.string.onboarding_pager_1)
    }

    val pageImage = remember {
        mutableStateOf(R.drawable.ic_radio_check)
    }

    HorizontalPager(
        modifier = Modifier.padding(top = 100.dp),
        count = MAX_COUNT,
        state = pagerState,
    ) { page ->
        when (page) {
            0 -> {
                // TODO - image 로직도 추가 예정
                pageDescription.value = R.string.onboarding_pager_1
            }

            1 -> {
                pageDescription.value = R.string.onboarding_pager_2
            }

            2 -> {
                pageDescription.value = R.string.onboarding_pager_3
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TwoTooImageView(
                model = pageImage.value,
                modifier = Modifier.size(200.dp),
            )
            Text(
                text = stringResource(id = pageDescription.value),
                modifier = Modifier.padding(top = 30.dp, bottom = 58.dp),
                textAlign = TextAlign.Center,
                style = TwoTooTheme.typography.headLineNormal28,
                color = TwoTooTheme.color.mainBrown,
            )
        }
    }
    HorizontalPagerIndicator(
        pagerState = pagerState,
        modifier = Modifier.padding(bottom = 40.dp),
    )
}