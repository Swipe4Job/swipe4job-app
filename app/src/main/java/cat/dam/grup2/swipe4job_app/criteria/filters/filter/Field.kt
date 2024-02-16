package filters.filter

data class Field(val value: String) {
    init {
        if (value == "") {
            throw Error("Filter field cannot be an empty string")
        }
    }
}