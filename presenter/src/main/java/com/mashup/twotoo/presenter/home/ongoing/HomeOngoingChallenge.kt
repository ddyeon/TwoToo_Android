package com.mashup.twotoo.presenter.home.ongoing

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.component.button.TwoTooTextButton
import com.mashup.twotoo.presenter.designsystem.theme.TwoTooTheme
import com.mashup.twotoo.presenter.home.TwoTooGoalAchievementProgressbar
import com.mashup.twotoo.presenter.home.model.ChallengeState
import com.mashup.twotoo.presenter.home.model.Flower
import com.mashup.twotoo.presenter.home.model.HomeChallengeStateUiModel
import com.mashup.twotoo.presenter.home.model.HomeCheerUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerPartnerAndMeUiModel
import com.mashup.twotoo.presenter.home.model.HomeFlowerUiModel
import com.mashup.twotoo.presenter.home.model.OngoingChallengeUiModel
import com.mashup.twotoo.presenter.home.model.UserType
import com.mashup.twotoo.presenter.home.ongoing.components.HomeBeeButton
import com.mashup.twotoo.presenter.home.ongoing.components.HomeFlowerMeAndPartner
import com.mashup.twotoo.presenter.home.ongoing.components.HomeGoalCount
import com.mashup.twotoo.presenter.home.ongoing.components.HomeGoalField
import com.mashup.twotoo.presenter.home.ongoing.components.HomeShotCountText
import com.mashup.twotoo.presenter.model.FlowerName
import com.mashup.twotoo.presenter.model.Stage
import com.mashup.twotoo.presenter.util.wiggle

/**
 * @Created by 김현국 2023/06/11
 */

@Composable
fun HomeOngoingChallenge(
    modifier: Modifier = Modifier,
    ongoingChallengeUiModel: OngoingChallengeUiModel = OngoingChallengeUiModel.default,
    onCommit: () -> Unit = {},
    onCompleteButtonClick: (Int) -> Unit = {},
    onBeeButtonClick: () -> Unit = {},
    navigateToHistory: (Int, String) -> Unit = { _, _ -> },
    onClickCheerButton: () -> Unit = {},
    onWiggleAnimationEnd: () -> Unit = {},
    onClickFlowerTextBubble: (FlowerName) -> Unit = {},
) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (
            homeGoalField, goalAchievement, goalCount,
            beeButton, shotCount, homeFlower,
            textButton, parentBottomSpacer,
        ) = createRefs()

        val homebackgroundGuide = createGuidelineFromBottom(0.33f)
        val homebackgroundMarginGuide = createGuidelineFromBottom(0.225f)
        HomeGoalField(
            modifier = Modifier.constrainAs(homeGoalField) {
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = 24.dp)
                end.linkTo(parent.end, margin = 24.dp)
                width = Dimension.fillToConstraints
            },
            homeGoalFieldUiModel = ongoingChallengeUiModel.homeGoalFieldUiModel,
            onClickHomeGoalField = {
                navigateToHistory(ongoingChallengeUiModel.challengeNo, "twotoo")
            },
        )
        TwoTooGoalAchievementProgressbar(
            modifier = Modifier.width(203.dp).height(62.dp).constrainAs(goalAchievement) {
                start.linkTo(homeGoalField.start)
                top.linkTo(homeGoalField.bottom, margin = 11.dp)
            }.background(color = Color.White, shape = RoundedCornerShape(15.dp)),
            homeGoalAchievePartnerAndMeUiModel = ongoingChallengeUiModel.homeGoalAchievePartnerAndMeUiModel,
        )
        HomeGoalCount(
            modifier = Modifier.constrainAs(goalCount) {
                end.linkTo(homeGoalField.end)
                top.linkTo(goalAchievement.top)
                bottom.linkTo(goalAchievement.bottom)
            },
            homeGoalCountUiModel = ongoingChallengeUiModel.homeGoalCountUiModel,
        )

        HomeFlowerMeAndPartner(
            modifier = Modifier.fillMaxWidth().constrainAs(homeFlower) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(homebackgroundMarginGuide)
            },
            onCommit = onCommit,
            homeChallengeStateUiModel = ongoingChallengeUiModel.homeChallengeStateUiModel,
            onClickCheerButton = onClickCheerButton,
            onClickFlowerTextBubble = onClickFlowerTextBubble,
        )

        if (ongoingChallengeUiModel.homeChallengeStateUiModel.challengeState == ChallengeState.Complete) {
            TwoTooTextButton(
                modifier = Modifier.testTag(stringResource(id = R.string.homeOngoingCompleteChallengeButton)).width(177.dp).height(57.dp).constrainAs(textButton) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom, margin = 51.dp)
                },
                text = stringResource(id = R.string.homeOngoingCompleteChallengeButtonText),
                onClick = {
                    onCompleteButtonClick(ongoingChallengeUiModel.challengeNo)
                },
            )
        } else {
            HomeBeeButton(
                modifier = Modifier.wiggle(
                    isWiggle = ongoingChallengeUiModel.shotInteractionState,
                    onWiggleAnimationEnded = onWiggleAnimationEnd,
                ).constrainAs(beeButton) {
                    top.linkTo(homebackgroundGuide)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }.clip(CircleShape).clickable {
                    if (ongoingChallengeUiModel.homeShotCountTextUiModel.count != 0) {
                        onBeeButtonClick()
                    }
                },
            )
            HomeShotCountText(
                modifier = Modifier.constrainAs(shotCount) {
                    top.linkTo(beeButton.bottom, margin = 10.19.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                homeShotCountTextUiModel = ongoingChallengeUiModel.homeShotCountTextUiModel,
            )
        }
        Spacer(
            modifier = Modifier.constrainAs(parentBottomSpacer) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeOngoingChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = { _, _ -> },
            onBeeButtonClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewFirstChallengeHomeOngoingChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = { _, _ -> },
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.firstChallenge,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAuthOnlyPartnerHomeOngoingChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = { _, _ -> },
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.authOnlyPartner,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAuthOnlyMeHomeOngoingChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = { _, _ -> },
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.authOnlyMe,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewAuthBothHomeOngoingChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = { _, _ -> },
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.authBoth,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDoNotAuthBothHomeOngoingChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = { _, _ -> },
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.auth.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.doNotAuthBoth,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDoNotCheerBothChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = { _, _ -> },
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.cheer.copy(
                    challengeStateUiModel = HomeCheerUiModel.doNotCheerBoth,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCheerOnlyMeChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = { _, _ -> },
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.cheer.copy(
                    challengeStateUiModel = HomeCheerUiModel.cheerOnlyMe,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCheerOnlyPartnerChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = { _, _ -> },
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.cheer.copy(
                    challengeStateUiModel = HomeCheerUiModel.cheerOnlyPartner,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCheerBothChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            navigateToHistory = { _, _ -> },
            onBeeButtonClick = {},
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.cheer.copy(
                    challengeStateUiModel = HomeCheerUiModel.cheerBoth,
                ),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewCompleteBothChallenge() {
    TwoTooTheme {
        HomeOngoingChallenge(
            ongoingChallengeUiModel = OngoingChallengeUiModel.default.copy(
                homeChallengeStateUiModel = HomeChallengeStateUiModel.complete.copy(
                    challengeStateUiModel = HomeFlowerPartnerAndMeUiModel.authBoth.copy(
                        partner = HomeFlowerUiModel.partner.copy(
                            flowerType = Flower(
                                flowerName = FlowerName.Tulip,
                                userType = UserType.PARTNER,
                                growType = Stage.Fifth,
                            ),
                        ),
                        me = HomeFlowerUiModel.me.copy(
                            flowerType = Flower(
                                flowerName = FlowerName.Tulip,
                                userType = UserType.ME,
                                growType = Stage.Fifth,
                            ),
                        ),
                    ),
                ),
            ),
        )
    }
}
