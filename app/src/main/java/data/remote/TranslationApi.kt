package data.remote

import retrofit2.http.Body
import retrofit2.http.POST
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface TranslationApi {
    @GET("get")
    suspend fun translateText(
        @Query("q") text: String,
        @Query("langpair") langPair: String
    ): TranslationResponse
}
data class TranslationResponse(
    val responseData: ResponseData
)
data class ResponseData(
    val translatedText: String
)
