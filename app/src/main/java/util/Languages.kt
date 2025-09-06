package util

data class Language(val code: String, val name: String) {
    companion object {
        fun getAll() = listOf(
            Language("en", "English"),
            Language("es", "Spanish"),
            Language("fr", "French"),
            Language("de", "German"),
            Language("zh", "Chinese"),
            Language("ar", "Arabic"),
            Language("ru", "Russian")
        )

        val defaultSource = getAll()[0]
        val defaultTarget = getAll()[1]
    }
}
