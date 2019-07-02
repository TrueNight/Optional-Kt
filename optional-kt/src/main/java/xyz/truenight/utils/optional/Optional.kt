package xyz.truenight.utils.optional

class Optional<T> private constructor(val value: T?) {

    @get:JvmName("get")
    val get: T
        get() = value ?: throw NoSuchElementException("No value present")

    @get:JvmName("isPresent")
    val present
        get() = value != null

    fun ifPresent(consumer: (T) -> Unit) {
        if (value != null) consumer(value)
    }

    fun filter(predicate: (T?) -> Boolean): Optional<T> {
        return if (!present || predicate(value)) this else empty()
    }

    fun <U> map(function: (T) -> U): Optional<U> {
        return if (value == null) empty() else ofNullable(function(value))
    }

    fun <U> flatMap(function: (T) -> Optional<U>): Optional<U> {
        return if (value == null) empty() else function(value)
    }

    fun orElse(elem: T): T {
        return this.value ?: elem
    }

    fun orElseGet(supplier: () -> T): T {
        return this.value ?: supplier()
    }

    fun <X : Throwable> orElseThrow(supplier: () -> X): T {
        return this.value ?: throw supplier()
    }

    override fun equals(other: Any?): Boolean {
        return when {
            this === other -> true
            other !is Optional<*> -> false
            else -> this.value == other.value
        }
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return value?.let { "Optional[$it]" } ?: "Optional.empty"
    }


    companion object {
        private val EMPTY: Optional<*> = Optional(null)

        @JvmStatic
        fun <T> of(elem: T): Optional<T> = Optional(elem)

        @JvmStatic
        fun <T> ofNullable(elem: T?): Optional<T> = if (elem == null) empty() else of(elem)

        @JvmStatic
        @Suppress("UNCHECKED_CAST")
        fun <T> empty(): Optional<T> = EMPTY as Optional<T>
    }
}

fun <T> T?.toOptional() = Optional.ofNullable(this)