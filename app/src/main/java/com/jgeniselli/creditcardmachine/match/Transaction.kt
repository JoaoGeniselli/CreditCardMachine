package com.jgeniselli.creditcardmachine.match

import android.support.annotation.StringRes
import com.jgeniselli.creditcardmachine.R

abstract class Transaction {

    abstract fun execute()

    companion object {
        const val PRO_LABORE_DEFAULT_VALUE: Double = 2000.00

        fun newDebit(player: Player, value: Double)
                = Debit.create(player, value)

        fun newCredit(player: Player, value: Double)
                = Credit.create(player, value)

        fun newTransfer(sourcePlayer: Player, destinyPlayer: Player, value: Double)
                = Transfer.create(sourcePlayer, destinyPlayer, value)

        fun newProLabore(player: Player)
                = ProLabore.create(player)
    }

    enum class Type(val id: Int, @StringRes val typeName: Int) {
        DEBIT(1, R.string.debit),
        CREDIT(2, R.string.credit),
        TRANSFER(3, R.string.transfer),
        PRO_LABORE(4, R.string.pro_labore);

        companion object {
            fun typeNames(): List<String> = values().map { it.name }

            fun byId(id: Int): Type? {
                val result = values().filter { it.id == id }
                if (result.isEmpty())
                    return null
                return result.first()
            }
        }
    }

    class Debit private constructor(
            private val player: Player,
            private val value: Double
    ): Transaction() {

        override fun execute() {
            player.debit(value)
        }

        override fun toString(): String {
            return "Débito: ${player.name} - ${formatInBRRealsCurrency(value)}"
        }

        companion object {
            fun create(player: Player, value: Double): Debit {
                if (value < 0.0) throw IllegalArgumentException("Value must be unsigned")
                return Debit(player, value)
            }
        }
    }

    open class Credit protected constructor(
            protected val player: Player,
            private val value: Double
    ): Transaction() {

        override fun execute() {
            player.credit(value)
        }

        override fun toString(): String {
            return "Crédito: ${player.name} - ${formatInBRRealsCurrency(value)}"
        }

        companion object {
            fun create(player: Player, value: Double): Credit {
                if (value < 0.0) throw IllegalArgumentException("Value must be unsigned")
                return Credit(player, value)
            }
        }
    }

    class Transfer private constructor(
            private val sourcePlayer: Player,
            private val destinyPlayer: Player,
            private val value: Double
    ): Transaction() {

        override fun execute() {
            sourcePlayer.debit(value)
            destinyPlayer.credit(value)
        }

        override fun toString(): String {
            return "Transferência: ${sourcePlayer.name} para ${destinyPlayer.name} - " +
                    "${formatInBRRealsCurrency(value)}"
        }

        companion object {
            fun create(sourcePlayer: Player, destinyPlayer: Player, value: Double): Transfer {
                if (value < 0.0) throw IllegalArgumentException("Value must be unsigned")
                return Transfer(sourcePlayer, destinyPlayer, value)
            }
        }
    }

    class ProLabore(player: Player) : Credit(player, PRO_LABORE_DEFAULT_VALUE) {

        override fun toString(): String {
            return "Pró-Labore: ${player.name} - ${formatInBRRealsCurrency(PRO_LABORE_DEFAULT_VALUE)}"
        }

        companion object {
            fun create(player: Player): ProLabore = ProLabore(player)
        }
    }
}