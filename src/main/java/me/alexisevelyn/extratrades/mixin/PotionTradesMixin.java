package me.alexisevelyn.extratrades.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers.SellPotionHoldingItemFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Mixin(SellPotionHoldingItemFactory.class)
public class PotionTradesMixin {
	// Note: Mojang, please make it possible to edit trades via datapack.
	//   I don't want to have to mixin for small things like Merchant Trades.
	@Shadow @Final private ItemStack sell;
	@Shadow @Final private int sellCount;
	@Shadow @Final private int price;
	@Shadow @Final private int maxUses;
	@Shadow @Final private int experience;
	@Shadow @Final private Item secondBuy;
	@Shadow @Final private int secondCount;
	@Shadow @Final private float priceMultiplier;

	@Inject(at = @At("TAIL"), method = "create(Lnet/minecraft/entity/Entity;Ljava/util/Random;)Lnet/minecraft/village/TradeOffer;", cancellable = true)
	private void init(Entity entity, Random random, CallbackInfoReturnable<TradeOffer> info) {

		ItemStack emeralds = new ItemStack(Items.EMERALD, this.price);
		List<Potion> potions = Registry.POTION.stream().filter((potionx) -> !potionx.getEffects().isEmpty()).collect(Collectors.toList());

		Potion potion = potions.get(random.nextInt(potions.size()));
		ItemStack itemStack2 = PotionUtil.setPotion(new ItemStack(this.sell.getItem(), this.sellCount), potion);
		TradeOffer tradeOffer = new TradeOffer(emeralds, new ItemStack(this.secondBuy, this.secondCount), itemStack2, this.maxUses, this.experience, this.priceMultiplier);

		info.setReturnValue(tradeOffer);
	}
}
