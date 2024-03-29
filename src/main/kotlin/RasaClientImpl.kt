package gr.helvia.rasaapi;

import com.beust.klaxon.Klaxon
import gr.helvia.rasaapi.dto.*
import gr.helvia.rasaapi.enums.IncludeEvent
import gr.helvia.rasaapi.exception.ExceptionHandler
import gr.helvia.rasaapi.rest.RasaRequestService

internal class RasaClientImpl internal constructor(private val requestService: RasaRequestService) : RasaClient {
    override fun postMessageToTracker(conversationId: String, message: PostMessage, includeEvent: IncludeEvent): ConversationTracker? {
        val response = requestService.post("/conversations/$conversationId/messages",
                Klaxon().toJsonString(message),
                mutableMapOf("include_event" to includeEvent))

        if (response.isSuccessful) {
            return Klaxon().parse<ConversationTracker>(response.body().toString())
        }

        throw ExceptionHandler.buildRequestException(response, "runAction")
    }

    override fun predictNextAction(conversationId: String, includeEvent: IncludeEvent): NextActionResult? {
        val response = requestService.post("/conversations/$conversationId/predict",
                "",
                mutableMapOf("include_event" to includeEvent))

        if (response.isSuccessful) {
            return Klaxon().parse<NextActionResult>(response.body().toString())
        }

        throw ExceptionHandler.buildRequestException(response, "runAction")
    }

    override fun runAction(conversationId: String, action: Action, includeEvent: IncludeEvent): ActionResult? {
        val response = requestService.post("/conversations/$conversationId/execute",
                Klaxon().toJsonString(action),
                mutableMapOf("include_event" to includeEvent))

        if (response.isSuccessful) {
            return Klaxon().parse<ActionResult>(response.body().toString())
        }

        throw ExceptionHandler.buildRequestException(response, "runAction")
    }

    override fun getStory(conversationId: String, until: String): String {
        val response = requestService.get("/conversations/$conversationId/story",
                mutableMapOf("until" to until))

        if (response.isSuccessful) {
            return response.body().toString()
        }

        throw ExceptionHandler.buildRequestException(response, "getStory")
    }

    override fun replaceEventToTracker(conversationId: String, event: Event, includeEvent: IncludeEvent): ConversationTracker? {
        val response = requestService.put("/conversations/$conversationId/tracker/events",
                Klaxon().toJsonString(event),
                mutableMapOf("include_event" to includeEvent)
        )

        if (response.isSuccessful) {
            return Klaxon().parse<ConversationTracker>(response.body().toString())
        }

        throw ExceptionHandler.buildRequestException(response, "replaceEventToTracker")
    }

    override fun postEventToTracker(conversationId: String, event: Event, includeEvent: IncludeEvent): ConversationTracker? {
        val response = requestService.post("/conversations/$conversationId/tracker/events",
                Klaxon().toJsonString(event),
                mutableMapOf("include_event" to includeEvent)
        )

        if (response.isSuccessful) {
            return Klaxon().parse<ConversationTracker>(response.body().toString())
        }

        throw ExceptionHandler.buildRequestException(response, "getConversationTracker")
    }

    override fun getConversationTracker(conversationId: String,
                                        includeEvent: IncludeEvent,
                                        until: String): ConversationTracker? {
        val response = requestService.get("/conversations/$conversationId/tracker",
                mutableMapOf("include_events" to includeEvent, "until" to until))

        if (response.isSuccessful) {
            return Klaxon().parse<ConversationTracker>(response.body().toString())
        }

        throw ExceptionHandler.buildRequestException(response, "getConversationTracker")
    }

    override fun getStatus(): StatusDto? {
        val response = requestService.get("/status")

        if (response.isSuccessful) {
            return Klaxon().parse<StatusDto>(response.body().toString())
        }

        throw ExceptionHandler.buildRequestException(response, "getStatus")
    }

    override fun getVersion(): String? {
        val response = requestService.get("/version")

        data class Version(val version: String, val minimum_compatible_version: String)

        if (response.isSuccessful) {
            val parsed = Klaxon().parse<Version>(response.body().toString());
            return parsed?.version
        }

        throw ExceptionHandler.buildRequestException(response, "getVersion")
    }

    override fun isAlive(): Boolean {
        val response = requestService.get("/")
        return response.isSuccessful
    }


}