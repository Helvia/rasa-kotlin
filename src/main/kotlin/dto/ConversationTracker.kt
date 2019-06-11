package gr.helvia.rasaapi.dto

data class ConversationTracker(
        val conversation_id: String,
        val slots: List<Slot>,
        val latest_message: Message,
        val latest_event_time: Double,
        val followup_action: String,
        val paused: Boolean,
        val events: List<Event>,
        val latest_input_channel: String,
        val latest_action_name: String,
        val active_form: Form
)

data class Form(val name: String)
