package net.casualuhc.uhcmod.utils.gamesettings

import net.casualuhc.arcade.utils.ItemUtils.enableGlint
import net.casualuhc.arcade.utils.ItemUtils.removeEnchantments
import net.casualuhc.uhcmod.UHCMod
import net.minecraft.world.item.ItemStack
import java.util.function.Consumer

abstract class GameSetting<T> protected constructor(
    val display: ItemStack,
    val options: Map<ItemStack, T>,
    value: T,
    private val callback: Consumer<GameSetting<T>>?
) {
    var value: T = value
        private set

    val name: String = this.display.displayName.string

    init {
        this.resetSelected()
    }

    fun setValueFromOption(option: ItemStack) {
        val newValue = options[option]
        if (newValue != null) {
            this.setValue(newValue)
        }
    }

    fun setValue(value: T) {
        setValueQuietly(value)
        if (this.callback != null) {
            this.callback.accept(this)
        }
    }

    fun setValueQuietly(value: T) {
        if (this.value != value) {
            this.value = value
            resetSelected()
            UHCMod.logger.info("Config '${this.name}' has been set to '${this.value}'")
        }
    }

    private fun resetSelected() {
        options.forEach { (stack, value) ->
            if (this.value == value) {
                stack.enableGlint()
            } else {
                stack.removeEnchantments()
            }
        }
    }

    class Int64(
        name: ItemStack,
        options: Map<ItemStack, Long>,
        defaultValue: Long,
        callback: Consumer<GameSetting<Long>>? = null
    ): GameSetting<Long>(name, options, defaultValue, callback)

    class Float64(
        name: ItemStack,
        options: Map<ItemStack, Double>,
        defaultValue: Double,
        callback: Consumer<GameSetting<Double>>? = null
    ): GameSetting<Double>(name, options, defaultValue, callback)

    class Bool(
        name: ItemStack,
        options: Map<ItemStack, Boolean>,
        defaultValue: Boolean,
        callback: Consumer<GameSetting<Boolean>>? = null
    ): GameSetting<Boolean>(name, options, defaultValue, callback)

    class Enumerated<E: Enum<*>>(
        name: ItemStack,
        options: Map<ItemStack, E>,
        defaultValue: E,
        callback: Consumer<GameSetting<E>>? = null
    ): GameSetting<E>(name, options, defaultValue, callback)
}
