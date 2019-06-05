package gr.helvia.rasaapi

import rest.RasaJWTRequestService
import rest.RasaTokenRequestService

object RasaClientFactory {

    fun createRasaClientWithToken(url: String, token: String): RasaClient {
        return RasaClientImpl(RasaTokenRequestService(url, token))
    }

    fun createRasaClientWithJWT(url: String, secret: String, userName: String): RasaClient {
        return RasaClientImpl(RasaJWTRequestService(url, secret, userName))
    }
}