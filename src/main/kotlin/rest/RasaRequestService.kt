package gr.helvia.rasaapi.rest

interface RasaRequestService {
    val rasaUrl: String

    fun get(endpoint: String, params: MutableMap<String, Any>? = null): okhttp3.Response

    fun post(endpoint: String, body: String, params: MutableMap<String, Any>? = null): okhttp3.Response
}