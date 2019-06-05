package gr.helvia.rasaapi.dto

data class ApiError(val version: String, val status: String, val reason: String, val message: String, val code: Int )