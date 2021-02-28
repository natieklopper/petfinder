package com.example.androiddevchallenge.domain

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PetTokenResponse(
    val token_type: String,
    val expires_in: Int,
    val access_token: String
)

@Serializable
data class Welcome(
    val animals: List<Animal>? = null,
    val pagination: Pagination? = null
)

@Serializable
data class Animal(
    val id: Long? = null,

    @SerialName("organization_id")
    val organizationID: String? = null,

    val url: String? = null,
    val type: String? = null,
    val species: String? = null,
    val breeds: Breeds? = null,
    val colors: Colors? = null,
    val age: String? = null,
    val gender: String? = null,
    val size: String? = null,
    val coat: String? = null,
    val name: String? = null,
    val description: String? = null,
    val photos: List<Photo>? = null,
    val videos: List<Video>? = null,
    val status: String? = null,
    val attributes: Attributes? = null,
    val environment: Environment? = null,
    val tags: List<String>? = null,
    val contact: Contact? = null,

    @SerialName("published_at")
    val publishedAt: String? = null,

    val distance: Double? = null,

    @SerialName("_links")
    val links: AnimalLinks? = null
)

@Serializable
data class Attributes(
    @SerialName("spayed_neutered")
    val spayedNeutered: Boolean? = null,

    @SerialName("house_trained")
    val houseTrained: Boolean? = null,

    val declawed: Boolean? = null,

    @SerialName("special_needs")
    val specialNeeds: Boolean? = null,

    @SerialName("shots_current")
    val shotsCurrent: Boolean? = null
)

@Serializable
data class Breeds(
    val primary: String? = null,
    val secondary: String? = null,
    val mixed: Boolean? = null,
    val unknown: Boolean? = null
)

@Serializable
data class Colors(
    val primary: String? = null,
    val secondary: String? = null,
    val tertiary: String? = null
)

@Serializable
data class Contact(
    val email: String? = null,
    val phone: String? = null,
    val address: Address? = null
)

@Serializable
data class Address(
    val address1: String? = null,
    val address2: String? = null,
    val city: String? = null,
    val state: String? = null,
    val postcode: String? = null,
    val country: String? = null
)

@Serializable
data class Environment(
    val children: Boolean? = null,
    val dogs: Boolean? = null,
    val cats: Boolean? = null
)

@Serializable
data class AnimalLinks(
    val self: Organization? = null,
    val type: Organization? = null,
    val organization: Organization? = null
)

@Serializable
data class Organization(
    val href: String? = null
)

@Serializable
data class Photo(
    val small: String? = null,
    val medium: String? = null,
    val large: String? = null,
    val full: String? = null
)

@Serializable
data class Video(
    val embed: String? = null
)

@Serializable
data class Pagination(
    @SerialName("count_per_page")
    val countPerPage: Long? = null,

    @SerialName("total_count")
    val totalCount: Long? = null,

    @SerialName("current_page")
    val currentPage: Long? = null,

    @SerialName("total_pages")
    val totalPages: Long? = null,

    @SerialName("_links")
    val links: PaginationLinks? = null
)

@Serializable
class PaginationLinks
