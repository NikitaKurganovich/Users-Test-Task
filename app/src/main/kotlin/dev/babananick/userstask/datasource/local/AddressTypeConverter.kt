package dev.babananick.userstask.datasource.local

import androidx.room.TypeConverter
import dev.babananick.userstask.model.Address
import kotlinx.serialization.json.Json

class AddressTypeConverter {
    @TypeConverter
    fun fromAddress(address: Address): String {
        return Json.encodeToString(address)
    }

    @TypeConverter
    fun toAddress(addressString: String): Address {
        return Json.decodeFromString(addressString)
    }
}