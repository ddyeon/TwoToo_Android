package com.mashup.twotoo.presenter.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.mashup.twotoo.presenter.createChallenge.navigation.createChallengeGraph
import com.mashup.twotoo.presenter.garden.navigation.gardenGraph
import com.mashup.twotoo.presenter.guid.navigation.guidGraph
import com.mashup.twotoo.presenter.history.navigation.historyGraph
import com.mashup.twotoo.presenter.home.navigation.homeGraph
import com.mashup.twotoo.presenter.invite.navigation.invitationGraph
import com.mashup.twotoo.presenter.mypage.navigation.userGraph
import com.mashup.twotoo.presenter.nickname.navigation.nickNameSettingGraph
import com.mashup.twotoo.presenter.onboarding.navigation.onBoardingGraph
import com.mashup.twotoo.presenter.splash.navigation.splashGraph
import com.mashup.twotoo.presenter.ui.TwoTooAppState

@Composable
fun TwoTooNavHost(
    appState: TwoTooAppState,
    modifier: Modifier = Modifier,
    startDestination: String = NavigationRoute.SplashGraph.SplashScreen.route
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        splashGraph(navController)
        guidGraph(navController)
        homeGraph(navController)
        gardenGraph(navController)
        userGraph(navController)
        historyGraph(navController)
        onBoardingGraph(navController)
        nickNameSettingGraph(navController)
        invitationGraph(navController)
        createChallengeGraph(navController)
    }
}
