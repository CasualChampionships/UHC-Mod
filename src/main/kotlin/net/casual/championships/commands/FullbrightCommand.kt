package net.casual.championships.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands

object FullbrightCommand: Command {
    override fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(
            Commands.literal("fullbright").executes {
                toggle(it)
            }.then(
                Commands.literal("enabled").executes {
                    toggle(it, true)
                }
            ).then(
                Commands.literal("disabled").executes {
                    toggle(it, false)
                }
            )
        )
    }

    private fun toggle(context: CommandContext<CommandSourceStack>): Int {
        val player = context.source.playerOrException
        // TODO:
        return toggle(context, false/*!player.flags.has(FullBright)*/)
    }

    private fun toggle(context: CommandContext<CommandSourceStack>, set: Boolean): Int {
        // context.source.playerOrException.flags.set(FullBright, set)
        // context.source.sendSystemMessage(Texts.UHC_FULL_BRIGHT.generate(set))
        return 1
    }
}