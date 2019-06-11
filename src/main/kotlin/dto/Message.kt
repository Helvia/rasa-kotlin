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

