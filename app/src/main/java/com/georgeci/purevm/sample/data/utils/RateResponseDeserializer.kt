package com.georgeci.purevm.sample.data.utils

import com.georgeci.purevm.sample.data.entity.RateResponse
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement

object RateResponseDeserializer : KSerializer<RateResponse> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("RateResponse") {
        element<String>("date")
        element<Map<String, String>>("")
    }

    override fun deserialize(decoder: Decoder): RateResponse = when (decoder) {
        is JsonDecoder -> {
            val raw = decoder.decodeJsonElement().toString()
            val surrogate = decoder.json.decodeFromString<Map<String, JsonElement>>(raw)

            var date: String? = null
            var map: Map<String, String>? = null
            surrogate.entries.forEach {
                when (it.key) {
                    "date" -> {
                        date = it.value.toString()
                    }

                    else -> {
                        map =
                            decoder.json.decodeFromString<Map<String, String>>(it.value.toString())
                    }
                }
            }
            if (date == null || map == null) {
                throw SerializationException("date or map is null")
            }
            RateResponse(
                date = date!!,
                items = map!!,
            )
        }

        else -> error("Unsupported decoder: $decoder")
    }

    override fun serialize(encoder: Encoder, value: RateResponse) {
        TODO("Not yet implemented")
    }
}