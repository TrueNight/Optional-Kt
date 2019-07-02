package xyz.truenight.utils.optional

/**
 * SAFE
 */

inline fun Boolean?.safe(default: () -> Boolean = { false }) = this ?: default()

inline fun Byte?.safe(default: () -> Byte = { 0 }) = this ?: default()

inline fun Short?.safe(default: () -> Short = { 0 }) = this ?: default()

inline fun Int?.safe(default: () -> Int = { 0 }) = this ?: default()

inline fun Long?.safe(default: () -> Long = { 0L }) = this ?: default()

inline fun Float?.safe(default: () -> Float = { 0.toFloat() }) = this ?: default()

inline fun Double?.safe(default: () -> Double = { 0.toDouble() }) = this ?: default()

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
    (if (this is MutableList<E>) this else this?.toMutableList()) ?: default()

inline fun <K, V> Map<K, V>?.safeMutable(default: () -> MutableMap<K, V> = { mutableMapOf() }): MutableMap<K, V> =
    (if (this is MutableMap<K, V>) this else this?.toMutableMap()) ?: default()

/**
 * CHECKERS
 */

fun CharSequence?.isNotNullOrEmpty() = !isNullOrEmpty()

fun CharSequence?.isNotNullOrBlank() = !isNullOrBlank()

fun <E> List<E>?.isNotNullOrEmpty() = this != null && isNotEmpty()

fun <E> List<E>?.isNullOrEmpty(): Boolean = this == null || isEmpty()

fun <K, V> Map<K, V>?.isNotNullOrEmpty() = this != null && isNotEmpty()

fun <K, V> Map<K, V>?.isNullOrEmpty() = this == null || isEmpty()

fun <T> T?.isNull() = this == null

fun <T> T?.isNotNull() = this != null

/**
 * ACTIONS
 */

fun <T> T?.ifPresent(function: (T) -> Unit) {
    if (this != null) function(this)
}