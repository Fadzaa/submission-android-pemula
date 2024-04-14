package com.example.submissiondicodingpemula.model.cast

data class Cast(
    val adult: Boolean,
    val gender: Long,
    val id: Long,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String? = null,
    val cast_id: Long? = null,
    val character: String? = null,
    val credit_id: String,
    val order: Long? = null,
)
