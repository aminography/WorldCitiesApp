package com.aminography.domain.user

import com.aminography.domain.base.BaseFlowUseCase
import com.aminography.domain.base.Result
import com.aminography.domain.di.IoDispatcher
import com.aminography.model.user.GithubUser
import com.aminography.scope.UserListScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject

/**
 * @author aminography
 */
@UserListScope
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