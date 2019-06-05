package gr.helvia.rasaapi

import gr.helvia.rasaapi.dto.StatusDto

interface RasaClient {

    fun isAlive(): Boolean

    fun getVersion(): String?

    fun getStatus(): StatusDto?

}