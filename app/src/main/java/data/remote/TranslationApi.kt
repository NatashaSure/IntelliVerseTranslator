package data.remote

import retrofit2.http.Body
import retrofit2.http.POST
import com.google.gson.annotations.SerializedName
import retrofit2.http.Headers


interface TranslationApi {
    @POST("translate")
    @Headers("Content-Type: application/json")
    suspend fun translateText(@Body request: TranslationRequest): TranslationResponse
}

/*
{
  "q": "Hello",
  "source": "en",
  "target": "es",
  "format": "text"
}
 */

data class TranslationRequest(
    val q: String,
    val source: String,
    val target: String,
    val format: String = "text"
)

data class TranslationResponse(
    @SerializedName("translatedText")
    val translatedText: String? = null,

    @SerializedName("translation")
    val translation: String? = null
) {
    fun getText(): String = translatedText ?: translation ?: "No translation found"
}
