package fridgepay.hfu.raysono.com.model

@kotlinx.serialization.Serializable
data class Product(
    val id: String,
    val name: String,
    val icon: String,
    val description: String,
    val price: Double,
)
