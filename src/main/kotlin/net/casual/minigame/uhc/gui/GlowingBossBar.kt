package net.casual.minigame.uhc.gui

import net.casual.arcade.gui.bossbar.TimerBossBar
import net.casual.arcade.utils.TimeUtils
import net.casual.util.Texts
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.BossEvent

class GlowingBossBar: TimerBossBar() {
    override fun getTitle(player: ServerPlayer): Component {
        return Texts.BOSSBAR_GLOWING.generate(TimeUtils.formatMMSS(this.getRemainingDuration()))
    }

    override fun getColour(player: ServerPlayer): BossEvent.BossBarColor {
        return BossEvent.BossBarColor.GREEN
    }

    override fun getOverlay(player: ServerPlayer): BossEvent.BossBarOverlay {
        return BossEvent.BossBarOverlay.PROGRESS
    }
}