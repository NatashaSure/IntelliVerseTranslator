package data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TranslationService {
    private const val BASE_URL = "https://libretranslate.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(TranslationApi::class.java)

    suspend fun translate(text: String, source: String, target: String): String {
        return try {
            val response = api.translateText(
                TranslationRequest(q = text, source = source, target = target)
            )
            response.translatedText
        } catch (e: Exception) {
            "Translation failed: ${e.message}"
        }
    }
}

