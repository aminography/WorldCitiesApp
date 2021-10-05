package com.aminography.domain.base

/**
 * @author aminography
 */
interface UseCase<in P, out R> where P : UseCase.Param, R : Any {

    interface Param

    object NoParam : Param
}
