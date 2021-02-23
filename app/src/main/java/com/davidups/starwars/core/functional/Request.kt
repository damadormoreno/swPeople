package com.davidups.starwars.core.functional

import com.davidups.starwars.core.exception.Failure
import retrofit2.Call

fun <T, R> request(
    call: Call<T>,
    transform: (T) -> R,
    default: T
): Either<Failure, R> {
    return try {
        val response = call.execute()
        when (response.isSuccessful) {
            true -> Either.Right(transform((response.body() ?: default)))
            false -> Either.Left(Failure.CustomError(response.code(), response.message()))
        }
    } catch (exception: Throwable) {
        Either.Left(Failure.CustomError(exception.stackTrace.hashCode(), exception.localizedMessage))
    }
}