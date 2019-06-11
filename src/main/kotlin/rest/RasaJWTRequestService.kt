package rest

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import gr.helvia.rasaapi.rest.RasaRequestService
import io.github.rybalkinsd.kohttp.dsl.httpGet
import io.github.rybalkinsd.kohttp.dsl.httpPost
import io.github.rybalkinsd.kohttp.dsl.httpPut
import okhttp3.Response

class RasaJWTRequestService internal constructor(
        override val rasaUrl: String,
        private val secret: String,
        private val userName: String) : RasaRequestService {

    private val jwtToken: String

    init {
        val algorithm = Algorithm.HMAC256(secret)
        this.jwtToken = JWT.create()
                .withIssuer("helvia")
                .withClaim("userName", userName)
                .sign(algorithm);
    }

    override fun put(endpoint: String, body: String, params: MutableMap<String, Any>?): Response {
        return httpPut {
            host = rasaUrl
            path = endpoint
            header {
                "Authorization" to "Bearer $jwtToken"
            }
            param {
                params?.forEach { (k, v) ->
                    k to v.toString()
                }
            }
            body { json(body) }
        }
    }

    override fun post(endpoint: String, body: String, params: MutableMap<String, Any>?): Response {
        return httpPost {
            host = rasaUrl
            path = endpoint
            header {
                "Authorization" to "Bearer $jwtToken"
            }
            param {
                params?.forEach { (k, v) ->
                    k to v.toString()
                }
            }
            body { json(body) }
        }
    }


    override fun get(endpoint: String, params: MutableMap<String, Any>?): Response {
        return httpGet {
            host = rasaUrl
            path = endpoint
            header {
                "Authorization" to "Bearer $jwtToken"
            }
            param {
                params?.forEach { (k, v) ->
                    k to v.toString()
                }
            }
        }
    }
}