package gr.helvia.rasaapi

import gr.helvia.rasaapi.dto.*
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

    fun replaceEventToTracker(conversationId: String,
                              event: Event,
                              includeEvent: IncludeEvent = IncludeEvent.AFTER_RESTART): ConversationTracker?

    /**
     * The story represents the whole conversation in end-to-end format.
     * This can be posted to the '/test/stories' endpoint and used as a test.
     *
     * It returns the sory in Markdown format.
     */
    fun getStory(conversationId: String, until: String = "None"): String

    /**
     * Runs the action, calling the action server if necessary.
     * Any responses sent by the executed action will be returned as part of the endpoints
     * result, they will not be sent over a connected output channel.
     */
    fun runAction(conversationId: String,
                  action: Action,
                  includeEvent: IncludeEvent = IncludeEvent.AFTER_RESTART): ActionResult?

    /**
     * Runs the conversations tracker through the model's policies to predict the scores of
     * all actions present in the model's domain. Actions are returned in the 'scores' array,
     * sorted on their 'score' values. The state of the tracker is not modified.
     */
    fun predictNextAction(conversationId: String,
                          includeEvent: IncludeEvent = IncludeEvent.AFTER_RESTART): NextActionResult?

    fun postMessageToTracker(conversationId: String,
                             message: PostMessage,
                             includeEvent: IncludeEvent = IncludeEvent.AFTER_RESTART): ConversationTracker?
}