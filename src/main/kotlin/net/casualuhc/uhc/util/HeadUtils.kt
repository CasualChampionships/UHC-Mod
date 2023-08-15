package net.casualuhc.uhc.util

import net.casualuhc.arcade.utils.ComponentUtils.gold
import net.casualuhc.arcade.utils.ComponentUtils.unItalicise
import net.casualuhc.uhc.items.UHCItems
import net.minecraft.nbt.StringTag
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.PlayerHeadItem

object HeadUtils {
    fun createGoldenHead(): ItemStack {
        return ItemStack(UHCItems.GOLDEN_HEAD).setHoverName(Texts.UHC_GOLDEN_HEAD.copy().gold().unItalicise())
    }

    fun createPlayerHead(player: ServerPlayer): ItemStack {
        val stack = ItemStack(UHCItems.PLAYER_HEAD)
        stack.addTagElement(PlayerHeadItem.TAG_SKULL_OWNER, StringTag.valueOf(player.scoreboardName))
        return stack
    }

    const val GOLDEN = "ewogICJ0aW1lc3RhbXAiIDogMTY3MDg2MDkyNTE4MywKICAicHJvZmlsZUlkIiA6ICI1N2E4NzA0ZGIzZjQ0YzhmYmVhMDY0Njc1MDExZmU3YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJQaGFudG9tVHVwYWMiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjk4Nzg4NTM2NTRjM2JiMjZmZDMzZTgwZjhlZDNjZGYwM2FiMzI0N2Y3MzY3ODQ2NGUwNmRhMTQzZGJkMGMxNyIKICAgIH0sCiAgICAiQ0FQRSIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjM0MGMwZTAzZGQyNGExMWIxNWE4YjMzYzJhN2U5ZTMyYWJiMjA1MWIyNDgxZDBiYTdkZWZkNjM1Y2E3YTkzMyIKICAgIH0KICB9Cn0"
    const val STEVE = "ewogICJ0aW1lc3RhbXAiIDogMTY4NDcwMTg1ODQ5MCwKICAicHJvZmlsZUlkIiA6ICIzY2YzYjgzNzhkYjc0ZDZlYmIxMDFjMDYzZmQ1MjVjNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJSeW9yaTE2IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzYwNDdiYjhhMGFlNzY1ZDQzMmVjYjUyOWFlNTJhZmFkMmNkNjYwYzM4ZjQxY2EyODcwYjZiOTFmN2Q0NTJmNTciCiAgICB9CiAgfQp9"

    const val RED = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2M0ZDdhM2JjM2RlODMzZDMwMzJlODVhMGJmNmYyYmVmNzY4Nzg2MmIzYzZiYzQwY2U3MzEwNjRmNjE1ZGQ5ZCJ9fX0="
    const val BLACK = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTY3YTJmMjE4YTZlNmUzOGYyYjU0NWY2YzE3NzMzZjRlZjliYmIyODhlNzU0MDI5NDljMDUyMTg5ZWUifX19"
    const val DARK_BLUE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWY4NjkwNDhmMDZkMzE4ZTUwNThiY2EwYTg3NmE1OTg2MDc5ZjQ1YTc2NGQxMmFiMzRhNDkzMWRiNmI4MGFkYyJ9fX0="
    const val DARK_GREEN = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmNhYzg1MGZiOGZiNjFlNmFmYzYzOGJhZjRkOWJiYjE4NWVlODNlZWJkMWZiODM3NTU1NzYwNGJhM2FjNzg5MCJ9fX0="
    const val DARK_AQUA = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTBmYjFmMTNlY2I3ZmJiMmZhNDljZDAzZDM1N2ZhN2UyNzg1MDJiNzg3MzA2MDJhYWExMDY1NWU0ZDk0OTBlMSJ9fX0="
    const val DARK_RED = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGY0ZGMzYzM3NTNiZjViMGI3ZjA4MWNkYjQ5YjgzZDM3NDI4YTEyZTQxODdmNjM0NmRlYzA2ZmFjNTRjZSJ9fX0="
    const val DARK_PURPLE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTQ4NzJjOGY3YTZjZWY3OTg2NDc2OWYwY2ZmNzFlZjIzZjU2NTY1NjdkZTAwMDFhZWYzNmM2YjYxNjJhYjAyZCJ9fX0="
    const val GOLD = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTMzMzBmYmVkMzc3YzI0NGY0ODdlNGJjNTY4MmQxNWFmNDBkM2NlNGMzMmVlMDNmYzI0YTdmOTUyZTdkMjlhOSJ9fX0="
    const val GRAY = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmQzY2ZjMjM5MDA2YjI1N2I4YjIwZjg1YTdiZjQyMDI2YzRhZGEwODRjMTQ0OGQwNGUwYzQwNmNlOGEyZWEzMSJ9fX0="
    const val DARK_GRAY = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjA4ZjMyMzQ2MmZiNDM0ZTkyOGJkNjcyODYzOGM5NDRlZTNkODEyZTE2MmI5YzZiYTA3MGZjYWM5YmY5In19fQ=="
    const val BLUE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmI4MTg4NmZmODliNDdlOTg3NWY5Y2E5MjM0NjhjMTY0ZGUyZjJlNTIyNDBkNTkwZGE5YWUxYTY5ODhiNTM4OSJ9fX0="
    const val GREEN = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTc2OTVmOTZkZGE2MjZmYWFhMDEwZjRhNWYyOGE1M2NkNjZmNzdkZTBjYzI4MGU3YzU4MjVhZDY1ZWVkYzcyZSJ9fX0="
    const val AQUA = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMDdjNzhmM2VlNzgzZmVlY2QyNjkyZWJhNTQ4NTFkYTVjNDMyMzA1NWViZDJmNjgzY2QzZTgzMDJmZWE3YyJ9fX0="
    const val LIGHT_PURPLE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjA1YzE3NjUwZTVkNzQ3MDEwZThiNjlhNmYyMzYzZmQxMWViOTNmODFjNmNlOTliZjAzODk1Y2VmYjkyYmFhIn19fQ=="
    const val YELLOW = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjlhMDMwY2EyYjJjNmZlNjdmZTgwOTdkM2NkMjA2OTY5ZmM1YzAwMTdjNjBiNmI0MDk5MGM3NzJhNmYwYWMwYSJ9fX0="
    const val WHITE = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjU4Y2FhYjRjOTQxNjM5NWI1OGU4Y2Y2ZjBjYjg2NDhmOWZhZGIxZDgwZDEyMTY2ZGNlNGEzZDI3MjVmYTUzIn19fQ=="
}
