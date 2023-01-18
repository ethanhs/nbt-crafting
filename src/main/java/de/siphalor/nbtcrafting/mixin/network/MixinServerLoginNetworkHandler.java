package de.siphalor.nbtcrafting.mixin.network;

import de.siphalor.nbtcrafting.network.ServerLoginNetworkHandlerAccess;

import net.minecraft.network.ClientConnection;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(ServerLoginNetworkHandler.class)
public class MixinServerLoginNetworkHandler implements ServerLoginNetworkHandlerAccess {
	@Shadow
	@Final
	ClientConnection connection;

	@Override
	public ClientConnection nbtCrafting$getConnection() {
		return this.connection;
	}
}
