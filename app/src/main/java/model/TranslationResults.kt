package model

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

class TranslationResults(context: Context) : TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = TextToSpeech(context, this)
    private var ready = false
    override fun onInit(status: Int) { ready = status == TextToSpeech.SUCCESS }
    fun speak(text: String, langTag: String) {
        if (!ready) return
        tts?.language = Locale.forLanguageTag(langTag)
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "uttId")
    }
    fun release() { tts?.shutdown() }
}
