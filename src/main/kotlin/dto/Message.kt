package gr.helvia.rasaapi.dto

import dto.Intent

data class Message(val entites: List<RasaEntity>,
                   val intent: Intent,
                   val intent_ranking: List<Intent>,
                   val text: String)

data class RasaEntity(val start: Int,
                      val end: Int,
                      val value: String,
                      val entity: String,
                      val confidence: Double)

data class BotMessage(
        val recipient_id: String,
        val text: String,
        val image: String,
        val buttons: List<Button>,
        val Attachment: List<Attachment>

)

data class Button(
        val title: String,
        val payload: String
)

data class Attachment(
        val title: String,
        val payload: String
)

data class PostMessage(
        val text: String,
        val sender: String,
        val parse_data: Message
)
