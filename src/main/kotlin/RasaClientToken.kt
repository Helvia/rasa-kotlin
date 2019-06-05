package gr.helvia.rasaapi;

import io.github.rybalkinsd.kohttp.ext.httpGet

internal class RasaClientToken internal constructor(val url: String, val token: String): RasaClient {

    override fun isAlive(): Boolean {
        val response = this.url.httpGet()
        return response.isSuccessful
    }


}