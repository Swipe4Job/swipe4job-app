data class Skip(val value: Int) {
    init {
        if (value < 0) {
            throw Exception("Invalid skip it must be at least 0")
        }
    }
    companion object {
        fun NONE(): Skip {
            return Skip(0)
        }
    }

    fun equals(skip: Skip): Boolean {
        return this.value == skip.value
    }

    fun isNone(): Boolean {
       return this.equals(NONE())
    }
}
