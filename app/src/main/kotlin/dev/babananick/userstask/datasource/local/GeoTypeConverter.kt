package dev.babananick.userstask.datasource.local

import androidx.room.TypeConverter
import dev.babananick.userstask.model.Geo
import kotlinx.serialization.json.Json

class GeoTypeConverter {
    @TypeConverter
    fun fromGeo(geo: Geo): String {
        return Json.encodeToString(geo)
    }

    @TypeConverter
    fun toGeo(geoString: String): Geo {
        return Json.decodeFromString(geoString)
    }
}