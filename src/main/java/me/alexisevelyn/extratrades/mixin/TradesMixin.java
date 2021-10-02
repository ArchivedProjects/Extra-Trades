package me.alexisevelyn.extratrades.mixin;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.block.Blocks;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(TradeOffers.class)
public class TradesMixin {
	@Shadow @Final public static Map<VillagerProfession, Int2ObjectMap<TradeOffers.Factory[]>> PROFESSION_TO_LEVELED_TRADE;

	static {
		PROFESSION_TO_LEVELED_TRADE.remove(VillagerProfession.CLERIC);
		PROFESSION_TO_LEVELED_TRADE.put(VillagerProfession.CLERIC,
				TradeOffers.copyToFastUtilMap(ImmutableMap.of(1,
								new TradeOffers.Factory[] {
										new TradeOffers.BuyForOneEmeraldFactory(Items.ROTTEN_FLESH, 32, 16, 2),
										new TradeOffers.SellItemFactory(Items.REDSTONE, 1, 2, 1)
								}, 2, new TradeOffers.Factory[] {
										new TradeOffers.BuyForOneEmeraldFactory(Items.GOLD_INGOT, 3, 12, 10),
										new TradeOffers.SellItemFactory(Items.LAPIS_LAZULI, 1, 1, 5)
								}, 3, new TradeOffers.Factory[] {
										new TradeOffers.BuyForOneEmeraldFactory(Items.RABBIT_FOOT, 2, 12, 20),
										new TradeOffers.SellItemFactory(Blocks.GLOWSTONE, 4, 1, 12, 10)
								}, 4, new TradeOffers.Factory[] {
										new TradeOffers.BuyForOneEmeraldFactory(Items.SCUTE, 4, 12, 30),
										new TradeOffers.BuyForOneEmeraldFactory(Items.GLASS_BOTTLE, 9, 12, 30),
										new TradeOffers.SellItemFactory(Items.ENDER_PEARL, 5, 1, 15)
								}, 5, new TradeOffers.Factory[] {
										new TradeOffers.BuyForOneEmeraldFactory(Items.NETHER_WART, 22, 12, 30),
										new TradeOffers.SellItemFactory(Items.EXPERIENCE_BOTTLE, 3, 1, 30),
										new TradeOffers.SellPotionHoldingItemFactory(Items.GLASS_BOTTLE, 1, Items.POTION, 1, 23, 12, 30)
								})
				));
	}
}
