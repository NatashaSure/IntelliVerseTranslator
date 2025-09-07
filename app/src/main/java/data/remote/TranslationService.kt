package data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TranslationService {
    private const val BASE_URL = "https://libretranslate.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val translationApi = retrofit.create(TranslationApi::class.java)

    suspend fun translate1() {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request()
                    println("Request URL: ${request.url}")
                    println("Request body: ${request.body}")
                    val response = chain.proceed(request)
                    println("Response code: ${response.code}")
                    println("Response body: ${response.body?.string()}")
                    response
                }
                .build()
        ).build()
    }

    suspend fun translate(inputText: String, sourceLang: String, targetLang: String): String {
        return try {
            val request = TranslationRequest(q = inputText, source = sourceLang, target = targetLang)
            val response = translationApi.translateText(request)
            response.getText() // Use this instead
        } catch (e: Exception) {
            "Translation failed: ${e.message}"
        }
    }
}
