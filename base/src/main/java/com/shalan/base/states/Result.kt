package com.shalan.base.states

/**
 * Created by Mohamed Shalan on 6/1/20.
 */

data class Result<T>(val status: ICommonStatus, val data: T?, val error: String?) : IResult<T> {

	companion object {
		fun <T> loading() = Result<T>(CommonStatusImp.LOADING, null, null)

		fun <T> success() = Result<T>(CommonStatusImp.SUCCESS, null, null)

		fun <T> success(data: T?) = Result(CommonStatusImp.SUCCESS, data, null)

		fun <T> error(error: String?) = Result<T>(CommonStatusImp.ERROR, null, error)
	}

	override fun fetchData(): T? = data

	override fun fetchError(): String? = error

	override fun whichStatus(): ICommonStatus = status
}
