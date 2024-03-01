import filters.Filters
import orders.Orders

data class SerializedOrder(val field: String, val direction: String)

data class SerializedFilter(val field: String, val operator: Int, val operand: Any)

data class SerializedCriteria(
    val filters: List<List<SerializedFilter>>,
    val orders: List<SerializedOrder>,
    val limit: Int,
    val skip: Int,
)

data class Criteria(
    val filters: Filters,
    val orders: Orders,
    val limit: Limit = Limit.NONE(),
    val skip: Skip = Skip.NONE()
) {
    companion object {
        fun NONE(): Criteria {
            return Criteria(
                filters = Filters.EMPTY(),
                orders = Orders.EMPTY(),
            )
        }
    }

    fun equals(criteria: Criteria): Boolean {
        return criteria.filters.equals(this.filters) &&
                criteria.orders.equals(this.orders) &&
                criteria.skip.equals(this.skip) &&
                criteria.limit.equals(this.limit)
    }

    fun serialize(): SerializedCriteria {
        return SerializedCriteria(
            filters = this.filters.serialize(),
            orders = this.orders.serialize(),
            limit = this.limit.value,
            skip = this.skip.value,
        )
    }
}