package com.aminography.domain.user

import com.aminography.coroutine.di.IoDispatcher
import com.aminography.domain.base.BaseFlowUseCase
import com.aminography.domain.base.Result
import com.aminography.model.user.GithubUser
import com.aminography.scope.annotation.FeatureScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

/**
 * @author aminography
 */
@FeatureScope
class SearchUsersUseCase @Inject constructor(
    private val userRepository: UserRepository,
    @IoDispatcher dispatcher: CoroutineDispatcher
) : BaseFlowUseCase<String, Result<List<GithubUser>>>(dispatcher) {

    override fun execute(parameter: String): Flow<Result<List<GithubUser>>> = flow {
        emit(Result.Loading)
        parameter.toLowerCase(Locale.getDefault()).let { query ->
            emit(userRepository.search(query))
        }
    }
}