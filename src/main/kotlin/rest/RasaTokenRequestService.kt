package rest

import gr.helvia.rasaapi.rest.RasaRequestService
import io.github.rybalkinsd.kohttp.dsl.httpGet
import io.github.rybalkinsd.kohttp.dsl.httpPost
import io.github.rybalkinsd.kohttp.dsl.httpPut
import okhttp3.Response


class RasaTokenRequestService(override val rasaUrl: String, val token: String) : RasaRequestService {
    override fun put(endpoint: String, body: String, params: MutableMap<String, Any>?): Response {
        return httpPut {
            host = rasaUrl
            path = endpoint
            param {
                params?.forEach { (k, v) ->
                    k to v.toString()
                }
                "token" to token
            }
            if (body != "") {
                body {
                    json(body)
                }
            }
        }
    }

    override fun post(endpoint: String, body: String, params: MutableMap<String, Any>?): Response {
        return httpPost {
            host = rasaUrl
            path = endpoint
            param {
                params?.forEach { (k, v) ->
                    k to v.toString()
                }
                "token" to token
            }
            if (body != "") {
                body {
                    json(body)
                }
            }
        }
    }


    override fun get(endpoint: String, params: MutableMap<String, Any>?): Response {

        return httpGet {
            host = rasaUrl
            path = endpoint
            param {
                params?.forEach { (k, v) ->
                    k to v.toString()
                }
                "token" to token
            }
        }
    }
}