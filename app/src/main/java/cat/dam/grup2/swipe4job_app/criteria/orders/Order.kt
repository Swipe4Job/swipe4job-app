package orders

import SerializedFilter
import SerializedOrder
import filters.filter.Field

class Order(val field: Field, val direction: OrderDirection) {
    companion object {
        fun none(): Order {
            return Order(Field("."), OrderDirection.NONE)
        }

        fun asc(field: Field): Order {
            return Order(field, OrderDirection.ASC)
        }

        fun desc(field: Field): Order {
            return Order(field, OrderDirection.DESC)
        }
    }

    fun isNone(): Boolean {
        return this.direction.equals(OrderDirection.NONE)
    }

    fun equals(order: Order): Boolean {
        return field.equals(order.field) && direction.equals(order.direction)
    }

    fun serialize(): SerializedOrder {
        return SerializedOrder(
            field = this.field.value,
            direction = this.direction.value
        )
    }
}