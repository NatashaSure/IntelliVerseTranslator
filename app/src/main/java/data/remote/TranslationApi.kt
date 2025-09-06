package data.remote

import retrofit2.http.Body
import retrofit2.http.POST
import com.google.gson.annotations.SerializedName

interface TranslationApi {
    @POST("translate")
    suspend fun translateText(
        @Body request: TranslationRequest
    ): TranslationResponse
}

data class TranslationRequest(
    val q: String,
    val source: String,
    val target: String,
    val format: String = "text"
)

data class TranslationResponse(
    @SerializedName("translatedText")
    val translatedText: String
)
