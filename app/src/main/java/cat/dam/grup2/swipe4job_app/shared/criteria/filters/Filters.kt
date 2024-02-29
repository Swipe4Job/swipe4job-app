package filters

import SerializedFilter

class Filters(val groups: List<FilterGroup>) {
    companion object {
        fun create(vararg groups: FilterGroup): Filters {
            val filters = Filters(groups.toList())
            if (filters.isEmpty()) {
                throw Exception("Empty groups not allowed")
            }
            return filters;
        }
        fun EMPTY(): Filters {
            return Filters(listOf());
        }
    }

    fun isEmpty(): Boolean {
        return groups.isEmpty()
    }

    fun equals(filters: Filters): Boolean {
        if (groups.size != filters.groups.size) {
            return false
        }

        for (i in groups.indices) {
            val filterGroup = filters.groups[i]
            val group = groups[i]

            if (!filterGroup.equals(group)) {
                return false
            }
        }

        return true;
    }

    fun serialize(): List<List<SerializedFilter>> {
        return this.groups.map { it.serialize() }
    }
}