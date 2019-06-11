package gr.helvia.rasaapi.dto

data class ActionResult(
        val tracker: ConversationTracker,
        val messages: List<BotMessage>
)