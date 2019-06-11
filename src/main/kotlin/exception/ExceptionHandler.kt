package gr.helvia.rasaapi.exception

import okhttp3.Response

object ExceptionHandler {

    fun buildRequestException(response: Response, caller: String): RasaRequestException {
        val stringBody = response.body().toString()
        if (response.code() == 400) {
            return RasaRequestException("$caller: Bad Request.\n$stringBody")
        } else if (response.code() == 401) {
            return RasaRequestException("$caller: User is not authenticated.\n$stringBody")
        } else if (response.code() == 403) {
            return RasaRequestException("$caller: User has insufficient permission.\n$stringBody")
        } else {
            return RasaRequestException("Unknown error on $caller()\n$stringBody")
        }
    }

}