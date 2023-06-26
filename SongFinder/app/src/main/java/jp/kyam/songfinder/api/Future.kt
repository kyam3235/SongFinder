package jp.kyam.songfinder.api

sealed class Future<out T> {
    /**
     * 実行中
     */
    object Proceeding : Future<Nothing>()

    /**
     * 成功
     */
    data class Success<out T>(val value: T) : Future<T>()

    /**
     * 失敗
     */
    data class Error(val error: Throwable) : Future<Nothing>()
}
