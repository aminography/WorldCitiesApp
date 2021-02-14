package com.aminography.domain.user

import androidx.paging.PagingData
import com.aminography.coroutine.di.IoDispatcher
import com.aminography.domain.base.BaseFlowUseCase
import com.aminography.model.user.GithubUser
import com.aminography.scope.annotation.FeatureScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

/**
 * @author aminography
 */
@FeatureScope
class SearchUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseFlowUseCase<String, PagingData<GithubUser>>(dispatcher) {

    override fun execute(parameter: String): Flow<PagingData<GithubUser>> =
        parameter.toLowerCase(Locale.getDefault()).let { query ->
            userRepository.search(query)
        }
}