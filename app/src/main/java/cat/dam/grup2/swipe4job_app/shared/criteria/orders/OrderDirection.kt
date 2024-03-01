package orders

class OrderDirection(val value: String) {
    companion object {
        val NONE = OrderDirection("NONE")
        val ASC = OrderDirection("ASC")
        val DESC = OrderDirection("DESC")

        val entries: List<OrderDirection>
            get() = listOf(NONE, ASC, DESC)

        fun from(value: String): OrderDirection {
            for (orderDirection in entries) {
                if (orderDirection.value == value) {
                    return orderDirection
                }
            }

            throw Exception("'$value' is not a valid order direction")
        }
    }

    fun equals(direction: OrderDirection): Boolean {
        return this.value == direction.value
    }
}