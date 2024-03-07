package com.georgeci.purevm.sample.data.entity

import com.georgeci.purevm.sample.data.utils.RateResponseDeserializer
import kotlinx.serialization.Serializable

@Serializable(with = RateResponseDeserializer::class)
data class RateResponse(
    val date: String,
    val items: Map<String, String>,
)