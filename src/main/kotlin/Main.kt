import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import java.net.HttpURLConnection
import java.net.URL

@Serializable
data class Country(
    val name: Name,
    val population: Int,
    val capital: List<String> = emptyList(),
    val gini: Map<String, Double> = emptyMap(),
    val borders: List<String> = emptyList()
)

@Serializable
data class Name(val common: String)

fun fetchCountryData(countryName: String): Country? {
    val url = "https://restcountries.com/v3.1/name/$countryName?fullText=true"
    val connection = URL(url).openConnection() as HttpURLConnection
    connection.requestMethod = "GET"
    return try {
        if (connection.responseCode == 200) {
            val response = connection.inputStream.bufferedReader().use { it.readText() }
            val countries: List<Country> = Json { ignoreUnknownKeys = true }.decodeFromString(response)
            countries.firstOrNull()
        } else {
            println("Error: ${connection.responseCode}")
            null
        }
    } finally {
        connection.disconnect()
    }
}

fun main() {
    println("Busca un pais:")
    val countryName = readln().trim()
    val country = fetchCountryData(countryName)

    if (country != null) {
        println("Pais: ${country.name.common}")
        println("Poblacion: ${country.population}")
        println("Capital: ${country.capital.joinToString()}")
        val gini = country.gini.entries.firstOrNull()?.value?.toString() ?: "No data"
        println("Index Gini: $gini")
        println("Tiene frontera con: ${country.borders.joinToString()}")
    } else {
        println("Pais no encontrado :(")
    }
}
