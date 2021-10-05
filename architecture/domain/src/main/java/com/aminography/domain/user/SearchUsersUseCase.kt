package com.aminography.domain.user

import androidx.paging.PagingData
import com.aminography.coroutine.di.IoDispatcher
import com.aminography.domain.base.BaseFlowUseCase
import com.aminography.domain.base.UseCase
import com.aminography.model.user.GithubUser
import com.aminography.scope.annotation.FeatureScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import java.util.Locale
import javax.inject.Inject

/**
 * The use-case of searching users.
 *
 * @param dispatcher the [CoroutineDispatcher] that the job should be executed on.
 * @param userRepository an instance of [UserRepository].
 *
 * @author aminography
 */
@FeatureScope
class SearchUsersUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val userRepository: UserRepository
) : BaseFlowUseCase<SearchUsersUseCase.Param, PagingData<GithubUser>>(dispatcher) {

    override fun execute(param: Param): Flow<PagingData<GithubUser>> =
        param.city.lowercase(Locale.getDefault()).let { query ->
            userRepository.search(query)
        }

    class Param(
        val city: String
    ) : UseCase.Param
}
