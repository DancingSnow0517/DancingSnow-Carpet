package cn.dancingsnow.carpet.mixins;

import cn.dancingsnow.carpet.CarpetSettings;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.ListCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

@Mixin(ListCommand.class)
public class ListCommandMixin {

    @Inject(
            method = "execute",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void execute(ServerCommandSource source, Function<ServerPlayerEntity, Text> nameProvider, CallbackInfoReturnable<Integer> cir) {
        PlayerManager playerManager = source.getMinecraftServer().getPlayerManager();
        List<ServerPlayerEntity> list = playerManager.getPlayerList();
        if (Objects.equals(CarpetSettings.removePlayerListName, CarpetSettings.NameNoExtra)) {
            Text text = Texts.join(list, nameProvider);
            source.sendFeedback(new TranslatableText("commands.list.players", list.size(), playerManager.getMaxPlayerCount(), text), false);
            cir.setReturnValue(list.size());
        }
        else {
            List<ServerPlayerEntity> new_list = new ArrayList<>();
            for (ServerPlayerEntity player : list) {
                if (!player.getName().toString().toLowerCase().contains(CarpetSettings.removePlayerListName.toLowerCase())) {
                    new_list.add(player);
                }
            }
            Text text = Texts.join(new_list, nameProvider);
            source.sendFeedback(new TranslatableText("commands.list.players", new_list.size(), playerManager.getMaxPlayerCount(), text), false);
            cir.setReturnValue(new_list.size());
        }
    }
}
