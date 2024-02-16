package net.casual.championships.commands

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands

object TeamGlowCommand: Command {
    override fun register(dispatcher: CommandDispatcher<CommandSourceStack>) {
        dispatcher.register(
            Commands.literal("teamglow").executes {
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
        return toggle(context, false/*!player.flags.has(TeamGlow)*/)
    }

    private fun toggle(context: CommandContext<CommandSourceStack>, set: Boolean): Int {
        // context.source.playerOrException.flags.set(TeamGlow, set)
        // context.source.sendSystemMessage(Texts.UHC_TEAM_GLOW.generate(set))
        return 1
    }
}