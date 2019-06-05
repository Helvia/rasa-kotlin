package gr.helvia.rasaapi.rest

interface RasaRequestService {
    val url: String

    fun get(endpoint: String): okhttp3.Response
}