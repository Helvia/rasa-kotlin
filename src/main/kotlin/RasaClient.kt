package gr.helvia.rasaapi

import gr.helvia.rasaapi.dto.ConversationTracker
import gr.helvia.rasaapi.dto.Event
import gr.helvia.rasaapi.dto.StatusDto
import gr.helvia.rasaapi.enums.IncludeEvent

interface RasaClient {

    fun isAlive(): Boolean

    fun getVersion(): String?

    fun getStatus(): StatusDto?

    /**
     * The tracker represents the state of the conversation. The state of the tracker
     * is created by applying a sequence of events, which modify the state. These events
     * can optionally be included in the response.
     */
    fun getConversationTracker(conversationId: String,
                               includeEvent: IncludeEvent = IncludeEvent.AFTER_RESTART,
                               until: String = "None"): ConversationTracker?

    fun postEventToTracker(conversationId: String,
                           event: Event,
                           includeEvent: IncludeEvent = IncludeEvent.AFTER_RESTART): ConversationTracker?

}