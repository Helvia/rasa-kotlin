package gr.helvia.rasaapi

object RasaClientFactory {

    fun createRasaClientWithToken(url: String, token: String): RasaClient {
        return RasaClientToken(url, token)
    }

    fun createRasaClientWithJWT(url: String, secret: String, userName: String): RasaClient {
        return RasaClientJWT(url, secret, userName)
    }
}