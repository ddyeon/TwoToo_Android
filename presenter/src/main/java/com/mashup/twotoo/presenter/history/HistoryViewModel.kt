package com.mashup.twotoo.presenter.history

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.history.datail.model.HistoryDetailInfoUiModel
import com.mashup.twotoo.presenter.history.model.ChallengeInfoUiModel
import com.mashup.twotoo.presenter.history.model.HistoryItemUiModel
import com.mashup.twotoo.presenter.history.model.OwnerNickNamesUiModel
import model.challenge.request.ChallengeNoRequestDomainModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import usecase.challenge.GetChallengeByNoUseCase
import javax.inject.Inject

class HistoryViewModel @Inject constructor(
    private val getChallengeByNoUseCase: GetChallengeByNoUseCase,
) : ContainerHost<HistoryState, Nothing>, ViewModel() {
    override val container: Container<HistoryState, Nothing> = container(
        HistoryState(),
    )

    fun getChallengeByUser(challengeNo: Int) = intent {
        getChallengeByNoUseCase(ChallengeNoRequestDomainModel(challengeNo)).onSuccess {
                challengeDetailResponseDomainModel ->

            val commitPairs = with(challengeDetailResponseDomainModel) {
                combineLists(
                    myCommitResponseDomainModel,
                    partnerCommitResponseDomainModel,
                )
            }

            val newChallengeInfoUiModel = ChallengeInfoUiModel.from(challengeDetailResponseDomainModel.challengeResponseDomainModel)
            val newHistoryItemUiModel = commitPairs.map {
                HistoryItemUiModel.from(it.first, it.second)
            }
            val newOwnerNickNamesUiModel = with(challengeDetailResponseDomainModel.challengeResponseDomainModel) {
                OwnerNickNamesUiModel.from(this.user1, this.user2)
            }

            reduce {
                state.copy(
                    challengeInfoUiModel = newChallengeInfoUiModel,
                    historyItemUiModel = newHistoryItemUiModel,
                    ownerNickNamesUiModel = newOwnerNickNamesUiModel,
                )
            }
        }.onFailure {
            Log.e("HistoryViewModel", "getChallengeByUser: ${it.message} 서버 에러!!")
        }
    }

    private fun <T, R> combineLists(list1: List<T>, list2: List<R>): List<Pair<T?, R?>> {
        val maxSize = maxOf(list1.size, list2.size)
        val combinedList = mutableListOf<Pair<T?, R?>>()

        for (i in 0 until maxSize) {
            val element1 = list1.getOrNull(i)
            val element2 = list2.getOrNull(i)
            combinedList.add(element1 to element2)
        }

        return combinedList
    }

    fun updateChallengeDetail(commitNo: Int) = intent {
        val partnerCommits = state.historyItemUiModel.map {
            it.partnerInfo
        }
        val myCommits = state.historyItemUiModel.map {
            it.myInfo
        }
        Log.i("HistoryViewModel", "updateChallengeDetail: myCommitSize${myCommits.size}, partnerCommitSize=${partnerCommits.size}")

        var isMyCommit = true
        val commit = run {
            myCommits.firstOrNull { it.commitNo == commitNo }
        } ?: run {
            isMyCommit = false
            partnerCommits.firstOrNull { it.commitNo == commitNo }
        }

        if (commit == null) {
            Log.d("HistoryViewModel", "해당 커밋이 존재하지 않습니다")
            return@intent
        }

        val ownerNickName = if (isMyCommit) {
            OwnerNickNamesUiModel(myNickName = this.state.ownerNickNamesUiModel.myNickName, partnerName = this.state.ownerNickNamesUiModel.partnerName)
        } else {
            OwnerNickNamesUiModel(myNickName = this.state.ownerNickNamesUiModel.partnerName, partnerName = this.state.ownerNickNamesUiModel.myNickName)
        }

        reduce {
            state.copy(
                historyDetailInfoUiModel = HistoryDetailInfoUiModel(
                    infoUiModel = commit,
                    ownerNickNamesUiModel = ownerNickName,
                    createdDate = "2023년 4월 20일",
                ),
            )
        }
    }
}
