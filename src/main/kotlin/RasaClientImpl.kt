package gr.helvia.rasaapi;

import com.beust.klaxon.Klaxon
import gr.helvia.rasaapi.dto.StatusDto
import gr.helvia.rasaapi.rest.RasaRequestService
import io.github.rybalkinsd.kohttp.dsl.httpGet
import io.github.rybalkinsd.kohttp.ext.httpGet
import java.lang.Exception

internal class RasaClientImpl internal constructor(private val requestService: RasaRequestService): RasaClient {
    override fun getStatus(): StatusDto? {
        val response = requestService.get("/status")

        if (response.isSuccessful) {
            return Klaxon().parse<StatusDto>(response.body().toString())
        } else {
            if (response.code() == 401) {
                throw Exception("User is not authenticated.\n${response.body().toString()}")
            } else if (response.code() == 403) {
                throw Exception("User has insufficient permission.\n${response.body().toString()}")
            } else {
                throw Exception("Unknown error on getStatus()\n${response.body().toString()}")
            }
        }
    }

    override fun getVersion(): String? {
        val response = requestService.get("/version")

        data class Version(val version: String, val minimum_compatible_version: String)

        if (response.isSuccessful) {
            val parsed = Klaxon().parse<Version>(response.body().toString());
            return parsed?.version
        } else {
            throw Exception(response.message());
        }
    }

    override fun isAlive(): Boolean {
        val response = requestService.get("/")
        return response.isSuccessful
    }


}