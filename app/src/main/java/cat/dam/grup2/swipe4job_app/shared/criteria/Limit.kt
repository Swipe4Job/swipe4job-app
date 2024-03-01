data class Limit(val value: Int) {
    init {
        if (value < 0) {
            throw Exception("Invalid limit it must be at least 0")
        }
    }
    companion object {
        fun NONE(): Limit {
            return Limit(0)
        }
    }

    fun equals(limit: Limit): Boolean {
        return this.value == limit.value;
    }

    fun isNone(): Boolean {
        return this.equals(Limit.NONE())
    }
}
