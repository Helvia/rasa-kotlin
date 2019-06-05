package rest

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import gr.helvia.rasaapi.rest.RasaRequestService
import io.github.rybalkinsd.kohttp.dsl.httpGet
import okhttp3.Response

class RasaJWTRequestService internal constructor(
        override val url: String,
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

    override fun get(endpoint: String): Response {
        return httpGet {
            host = url
            path = endpoint
            header {
                "Authorization" to "Bearer $jwtToken"
            }
        }
    }
}