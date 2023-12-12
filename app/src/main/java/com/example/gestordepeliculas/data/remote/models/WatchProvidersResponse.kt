package com.example.gestordepeliculas.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class WatchProvidersResponse(
    val id: Int,
    val results: CountryProviderResults
)

@Serializable
data class CountryProviderResults(
    val AE: WatchProviders? = null,
    val AL: WatchProviders? = null,
    val AR: WatchProviders? = null,
    val AT: WatchProviders? = null,
    val AU: WatchProviders? = null,
    val AZ: WatchProviders? = null,
    val BA: WatchProviders? = null,
    val BB: WatchProviders? = null,
    val BE: WatchProviders? = null,
    val BG: WatchProviders? = null,
    val BH: WatchProviders? = null,
    val BO: WatchProviders? = null,
    val BR: WatchProviders? = null,
    val BS: WatchProviders? = null,
    val CA: WatchProviders? = null,
    val CH: WatchProviders? = null,
    val CL: WatchProviders? = null,
    val CO: WatchProviders? = null,
    val CR: WatchProviders? = null,
    val CV: WatchProviders? = null,
    val CY: WatchProviders? = null,
    val CZ: WatchProviders? = null,
    val DE: WatchProviders? = null,
    val DK: WatchProviders? = null,
    val DO: WatchProviders? = null,
    val EC: WatchProviders? = null,
    val EE: WatchProviders? = null,
    val EG: WatchProviders? = null,
    val ES: WatchProviders? = null,
    val FI: WatchProviders? = null,
    val FJ: WatchProviders? = null,
    val FR: WatchProviders? = null,
    val GB: WatchProviders? = null,
    val GF: WatchProviders? = null,
    val GI: WatchProviders? = null,
    val GH: WatchProviders? = null,
    val GR: WatchProviders? = null,
    val GT: WatchProviders? = null,
    val HK: WatchProviders? = null,
    val HN: WatchProviders? = null,
    val HR: WatchProviders? = null,
    val HU: WatchProviders? = null,
    val ID: WatchProviders? = null,
    val IE: WatchProviders? = null,
    val IL: WatchProviders? = null,
    val IN: WatchProviders? = null,
    val IQ: WatchProviders? = null,
    val IS: WatchProviders? = null,
    val IT: WatchProviders? = null,
    val JM: WatchProviders? = null,
    val JO: WatchProviders? = null,
    val JP: WatchProviders? = null,
    val KR: WatchProviders? = null,
    val KW: WatchProviders? = null,
    val LB: WatchProviders? = null,
    val LI: WatchProviders? = null,
    val LT: WatchProviders? = null,
    val LU: WatchProviders? = null,
    val LV: WatchProviders? = null,
    val MD: WatchProviders? = null,
    val MK: WatchProviders? = null,
    val MT: WatchProviders? = null,
    val MU: WatchProviders? = null,
    val MX: WatchProviders? = null,
    val MY: WatchProviders? = null,
    val MZ: WatchProviders? = null,
    val NI: WatchProviders? = null,
    val NL: WatchProviders? = null,
    val NO: WatchProviders? = null,
    val NZ: WatchProviders? = null,
    val OM: WatchProviders? = null,
    val PA: WatchProviders? = null,
    val PE: WatchProviders? = null,
    val PH: WatchProviders? = null,
    val PK: WatchProviders? = null,
    val PL: WatchProviders? = null,
    val PS: WatchProviders? = null,
    val PT: WatchProviders? = null,
    val PY: WatchProviders? = null,
    val QA: WatchProviders? = null,
    val RO: WatchProviders? = null,
    val RS: WatchProviders? = null,
    val RU: WatchProviders? = null,
    val SA: WatchProviders? = null,
    val SE: WatchProviders? = null,
    val SG: WatchProviders? = null,
    val SI: WatchProviders? = null,
    val SK: WatchProviders? = null,
    val SM: WatchProviders? = null,
    val SV: WatchProviders? = null,
    val TH: WatchProviders? = null,
    val TR: WatchProviders? = null,
    val TT: WatchProviders? = null,
    val TW: WatchProviders? = null,
    val UA: WatchProviders? = null,
    val UG: WatchProviders? = null,
    val US: WatchProviders? = null,
    val UY: WatchProviders? = null,
    val VE: WatchProviders? = null,
    val YE: WatchProviders? = null,
    val ZA: WatchProviders? = null,
)

@Serializable
data class WatchProviders(
    val link: String? = "",
    val rent: List<Rent>? = emptyList(),
    val ads: List<Ads>? = emptyList(),
    val flatrate: List<Flatrate>? = emptyList(),
    val buy: List<Buy>? = emptyList(),
)

@Serializable
data class Rent(
    val logo_path: String? = "",
    val provider_id: Int? = 0,
    val provider_name: String? = "",
    val display_priority: Int? = 0,
)

@Serializable
data class Ads(
    val logo_path: String? = "",
    val provider_id: Int? = 0,
    val provider_name: String? = "",
    val display_priority: Int? = 0,
)

@Serializable
data class Flatrate(
    val logo_path: String? = "",
    val provider_id: Int? = 0,
    val provider_name: String? = "",
    val display_priority: Int? = 0,
)

@Serializable
data class Buy(
    val logo_path: String? = "",
    val provider_id: Int? = 0,
    val provider_name: String? = "",
    val display_priority: Int? = 0,
)