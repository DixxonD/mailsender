package ch.tvlla.mailsender.utils

import kotlin.math.pow

class FileSize(private val size: Double, private val unit: Unit){
    constructor(size: Int, unit: Unit): this(size.toDouble(), unit)

    val bytes : Double
        get() =  size * 10.0.pow(unit.potency)

    fun convertTo(targetUnit: Unit) = bytes / 10.0.pow(targetUnit.potency.toDouble())
    override fun toString(): String {
        return "${convertTo(Unit.MB)} MB"
    }
}

enum class Unit(val potency: Int){
    GB(9),
    MB(6),
    KB(3),
    B(0)
}
