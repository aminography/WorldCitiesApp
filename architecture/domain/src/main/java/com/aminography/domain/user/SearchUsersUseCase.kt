package com.aminography.domain.user

import androidx.paging.PagingData
import com.aminography.coroutine.di.IoDispatcher
import com.aminography.domain.base.BaseFlowUseCase
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
) : BaseFlowUseCase<String, PagingData<GithubUser>>(dispatcher) {

    override fun execute(param: String): Flow<PagingData<GithubUser>> =
        param.lowercase(Locale.getDefault()).let { query ->
            userRepository.search(query)
        }
}
