package gr.helvia.rasaapi

class RasaClientJWT internal constructor(val url: String, internal val secret: String, val userName: String): RasaClient {
    override fun isAlive(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}