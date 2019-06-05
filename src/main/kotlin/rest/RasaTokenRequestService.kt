package rest

import gr.helvia.rasaapi.rest.RasaRequestService
import io.github.rybalkinsd.kohttp.dsl.httpGet

class RasaTokenRequestService(override val url: String, val token: String) : RasaRequestService {


    override fun get(endpoint: String): okhttp3.Response {
        val response = httpGet {
            host = url
            path = "/status"
            param {
                "token" to token
            }
        }
        return  response;
    }
}