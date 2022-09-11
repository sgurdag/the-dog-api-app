package com.safagurdag.domain.common

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class FailureReason(
    val msg: String? = "",
    val error: Exception = Exception(),
    var isCachedDataPresent: Boolean = false
) : Exception(msg, error) {
    class NetworkConnection : FailureReason()
    class ParsingError(error: Exception) : FailureReason()
    class Network404Error : FailureReason()
    class DatabaseError(msg: String? = "") : FailureReason(msg)

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure : FailureReason()

    fun getTitle(): String = this.javaClass.simpleName
    override fun toString(): String = this.javaClass.name

    class ServerError(msg: String?, error: Exception) : FailureReason(msg, error) {
        var serviceErrors: Array<BaseError> = emptyArray()
        var statusCode = 0

        constructor(
            serviceResponseErrors: Array<BaseError>,
            msg: String,
            statusCode: Int = 0
        ) : this(msg, Exception()) {
            this.statusCode = statusCode
            this.serviceErrors = serviceResponseErrors
        }
    }
}
