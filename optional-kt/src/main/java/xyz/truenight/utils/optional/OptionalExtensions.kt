package xyz.truenight.utils.optional

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * SAFE
 */

inline fun Boolean?.safe(default: () -> Boolean = { false }) = this ?: default()

inline fun Byte?.safe(default: () -> Byte = { 0 }) = this ?: default()

inline fun Short?.safe(default: () -> Short = { 0 }) = this ?: default()

inline fun Int?.safe(default: () -> Int = { 0 }) = this ?: default()

inline fun Long?.safe(default: () -> Long = { 0L }) = this ?: default()

inline fun Float?.safe(default: () -> Float = { 0F }) = this ?: default()

inline fun Double?.safe(default: () -> Double = { 0.0 }) = this ?: default()

inline fun String?.safe(default: () -> String = { "" }) = this ?: default()

inline fun BooleanArray?.safe(default: () -> BooleanArray = { booleanArrayOf() }) = this
    ?: default()

inline fun ByteArray?.safe(default: () -> ByteArray = { byteArrayOf() }) = this ?: default()

inline fun ShortArray?.safe(default: () -> ShortArray = { shortArrayOf() }) = this ?: default()

inline fun IntArray?.safe(default: () -> IntArray = { intArrayOf() }) = this ?: default()

inline fun LongArray?.safe(default: () -> LongArray = { longArrayOf() }) = this ?: default()

inline fun FloatArray?.safe(default: () -> FloatArray = { floatArrayOf() }) = this ?: default()

inline fun DoubleArray?.safe(default: () -> DoubleArray = { doubleArrayOf() }) = this ?: default()

inline fun IntRange?.safe(default: () -> IntRange = { 0..0 }) = this ?: default()

inline fun LongRange?.safe(default: () -> LongRange = { 0L..0L }) = this ?: default()

inline fun <reified T> T?.safe(default: () -> T): T = this ?: default()

inline fun <reified E> Array<E>?.safe(default: () -> Array<E> = { emptyArray() }): Array<E> =
    this ?: default()

inline fun <E> List<E>?.safe(default: () -> List<E> = { emptyList() }): List<E> = this ?: default()

inline fun <K, V> Map<K, V>?.safe(default: () -> Map<K, V> = { emptyMap() }): Map<K, V> = this
    ?: default()

inline fun <E> List<E>?.safeMutable(default: () -> MutableList<E> = { mutableListOf() }): MutableList<E> =
    this as? MutableList<E> ?: this?.toMutableList() ?: default()

inline fun <K, V> Map<K, V>?.safeMutable(default: () -> MutableMap<K, V> = { mutableMapOf() }): MutableMap<K, V> =
    this as? MutableMap<K, V> ?: this?.toMutableMap() ?: default()

/**
 * CHECKERS
 */

@UseExperimental(ExperimentalContracts::class)
fun CharSequence?.isNotEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotEmpty != null)
    }

    return !isNullOrEmpty()
}

@UseExperimental(ExperimentalContracts::class)
fun CharSequence?.isNotBlank(): Boolean {
    contract {
        returns(true) implies (this@isNotBlank != null)
    }

    return !isNullOrBlank()
}

@UseExperimental(ExperimentalContracts::class)
fun <E> Collection<E>?.isNullOrEmpty(): Boolean {
    contract {
        returns(false) implies (this@isNullOrEmpty != null)
    }

    return this == null || isEmpty()
}

@UseExperimental(ExperimentalContracts::class)
fun <E> Collection<E>?.isNotEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotEmpty != null)
    }

    return !isNullOrEmpty()
}

@UseExperimental(ExperimentalContracts::class)
fun <K, V> Map<K, V>?.isNullOrEmpty(): Boolean {
    contract {
        returns(false) implies (this@isNullOrEmpty != null)
    }

    return this == null || isEmpty()
}

@UseExperimental(ExperimentalContracts::class)
fun <K, V> Map<K, V>?.isNotEmpty(): Boolean {
    contract {
        returns(true) implies (this@isNotEmpty != null)
    }

    return !isNullOrEmpty()
}

@UseExperimental(ExperimentalContracts::class)
fun <T> T?.isNull(): Boolean {
    contract {
        returns(false) implies (this@isNull != null)
    }

    return this == null
}

@UseExperimental(ExperimentalContracts::class)
fun <T> T?.isNotNull(): Boolean {
    contract {
        returns(true) implies (this@isNotNull != null)
    }

    return this != null
}

/**
 * ACTIONS
 */

fun <T> T?.ifPresent(function: (T) -> Unit) {
    if (this != null) function(this)
}