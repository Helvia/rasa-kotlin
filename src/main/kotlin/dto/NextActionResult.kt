package gr.helvia.rasaapi.dto

data class NextActionResult(
        val scores: List<Score>,
        val policy: String,
        val tracker: ConversationTracker
)

data class Score(
        val action: String,
        val score: Int
)