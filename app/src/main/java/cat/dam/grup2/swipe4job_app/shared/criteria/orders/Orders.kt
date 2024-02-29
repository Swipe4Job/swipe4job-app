package orders

import SerializedOrder

class Orders(val orders: List<Order>) {
    companion object {
        fun create(orderList: List<Order>): Orders {
            val orders = Orders(orderList)
            if (orders.isEmpty()) {
                throw Exception("Empty orders not allowed")
            }

            return orders
        }

        fun EMPTY(): Orders {
            return Orders(listOf())
        }
    }

    fun isEmpty(): Boolean {
        return this.orders.isEmpty()
    }

    @OptIn(ExperimentalStdlibApi::class)
    fun equals(orders: Orders): Boolean {
        if (this.orders.size != orders.orders.size) {
            return false
        }

        for (i in 0..<this.orders.size) {
            val order = orders.orders[i]
            val _order = this.orders[i]

            if (!order.equals(_order)) {
                return false
            }
        }

        return true
    }

    fun serialize(): List<SerializedOrder> {
        return this.orders.map { it.serialize() }
    }
}