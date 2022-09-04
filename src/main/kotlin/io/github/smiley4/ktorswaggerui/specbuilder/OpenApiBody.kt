package io.github.smiley4.ktorswaggerui.specbuilder

import io.github.smiley4.ktorswaggerui.dsl.OpenApiDslMarker
import io.github.smiley4.ktorswaggerui.dsl.OpenApiExample
import io.ktor.http.ContentType
import kotlin.reflect.KClass

/**
 * Describes a single request/response body.
 */
@OpenApiDslMarker
class OpenApiBody(
    /**
     * The schema defining the type used for the parameter.
     * Examples:
     * - Int::class.java
     * - UByte::class.java
     * - BooleanArray::class.java
     * - Array<String>::class.java
     * - Array<MyClass>::class.java
     */
    val schema: KClass<*>?,
) {

    /**
     * A brief description of the request body
     */
    var description: String? = null


    /**
     * Determines if the request body is required in the request
     */
    var required: Boolean? = null


    /**
     * Examples for this body
     */
    private val examples = mutableMapOf<String, OpenApiExample>()

    fun example(name: String, value: Any, block: OpenApiExample.() -> Unit) {
        examples[name] = OpenApiExample(value).apply(block)
    }

    fun example(name: String, value: Any) = example(name, value) {}

    fun getExamples(): Map<String, OpenApiExample> = examples


    /**
     * Allowed Media Types for this body. If none specified, a media type will be chosen automatically based on the provided schema
     */
    private val mediaTypes = mutableSetOf<ContentType>()

    fun mediaType(type: ContentType) {
        mediaTypes.add(type)
    }

    fun getMediaTypes(): Set<ContentType> = mediaTypes

}