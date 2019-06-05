package gr.helvia.rasaapi.dto

data class StatusDto(val fingerprint: Fingerprint, val model_file: String)

data class Fingerprint(
        val config: List<String>,
        val domain: List<String>,
        val messages: List<String>,
        val stories: List<String>,
        val trained_at: Double,
        val version: String
)