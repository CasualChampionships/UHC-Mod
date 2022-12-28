package net.casualuhc.uhcmod.mixin;

import com.mojang.authlib.GameProfile;
import net.casualuhc.uhcmod.managers.GameManager;
import net.casualuhc.uhcmod.utils.uhc.Config;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;
import net.minecraft.world.WorldProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.net.SocketAddress;
import java.util.Optional;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {
    @Shadow
    public abstract boolean isOperator(GameProfile profile);

    @Redirect(method = "onPlayerConnect", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/WorldProperties;isHardcore()Z"))
    private boolean isHardcore(WorldProperties instance) {
        return true;
    }

    @Inject(method = "checkCanJoin", at = @At("HEAD"), cancellable = true)
    private void onCheckJoin(SocketAddress address, GameProfile profile, CallbackInfoReturnable<Text> cir) {
        if (!GameManager.isReadyForPlayers() && !this.isOperator(profile)) {
            cir.setReturnValue(Text.literal("CTF isn't quite ready yet..."));
        }
    }

    @Redirect(method = "onPlayerConnect", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;getResourcePackProperties()Ljava/util/Optional;"))
    private Optional<MinecraftServer.ServerResourcePackProperties> onSendResourcePack(MinecraftServer instance, ClientConnection connection, ServerPlayerEntity player) {
        MinecraftServer.ServerResourcePackProperties resource = Config.CURRENT_EVENT.getResourcePack();
        if (resource != null) {
            net.casualuhc.uhcmod.managers.PlayerManager.sendResourcePack(player, resource);
        }
        return Optional.empty();
    }
}
