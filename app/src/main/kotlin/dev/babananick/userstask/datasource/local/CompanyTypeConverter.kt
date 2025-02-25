package dev.babananick.userstask.datasource.local

import androidx.room.TypeConverter
import dev.babananick.userstask.model.Company
import kotlinx.serialization.json.Json

class CompanyTypeConverter {
    @TypeConverter
    fun fromCompany(company: Company): String {
        return Json.encodeToString(company)
    }

    @TypeConverter
    fun toCompany(companyString: String): Company {
        return Json.decodeFromString(companyString)
    }
}