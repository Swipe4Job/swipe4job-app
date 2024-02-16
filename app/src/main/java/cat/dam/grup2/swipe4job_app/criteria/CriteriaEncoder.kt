package cat.dam.aria.retrofit.shared.criteria

import Criteria
import com.google.gson.Gson
import java.net.URLEncoder

class CriteriaEncoder {
    companion object {
        val gson = Gson()
        fun encodeCriteria(criteria: Criteria): String {
            val jsonString = gson.toJson(criteria.serialize())
            return URLEncoder.encode(jsonString, "UTF-8")
        }
    }
}