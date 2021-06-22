package com.shalan.base.states

/**
 * Created by Mohamed Shalan on 6/1/20.
 */

interface IResult<T> {

	fun fetchData(): T?

	fun fetchError(): String?

	fun whichStatus(): ICommonStatus
}
