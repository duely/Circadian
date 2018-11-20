package com.noobanidus.circadian.items;

import cofh.core.init.CoreEnchantments;
import cofh.core.util.CoreUtils;
import cofh.core.util.helpers.*;
import cofh.thermalexpansion.item.ItemSatchel;
import com.google.common.collect.ImmutableMap;
import com.noobanidus.circadian.Circadian;
import com.noobanidus.circadian.compat.cofh.thermalexpansion.GuiHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import thaumcraft.api.items.ItemsTC;
import vazkii.botania.common.item.ModItems;

import java.util.Arrays;

@SuppressWarnings("WeakerAccess")
public class Satchels extends ItemSatchel {

    public static boolean enabled = Circadian.CONFIG.get("Items.Satchels", "Enable", true, "Enable magical satchels.");
    public static ItemStack manasteel;
    public static ItemStack thaumium;
    public static ItemStack terrasteel;
    public static ItemStack elementium;
    public static ItemStack voidmetal;

    public Satchels() {
        super();

        this.modName = "circadian";

        setUnlocalizedName("satchel");
        setCreativeTab(Circadian.TAB);
        setRegistryName("circadian", "satchel");

        setHasSubtypes(true);
        setMaxStackSize(1);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        items.addAll(Arrays.asList(manasteel, thaumium, terrasteel, elementium, voidmetal));
    }

    public boolean init() {
        ForgeRegistries.ITEMS.register(this);

        manasteel = addItem(0, "standard0", EnumRarity.COMMON);
        thaumium = addItem(1, "standard1", EnumRarity.COMMON);
        terrasteel = addItem(2, "standard2", EnumRarity.UNCOMMON);
        elementium = addItem(3, "standard3", EnumRarity.UNCOMMON);
        voidmetal = addItem(4, "standard4", EnumRarity.RARE);

        return true;
    }

    public void registerRecipes() {
        RecipeHelper.addShapedUpgradeRecipe(thaumium,
                "IPI",
                "SCS",
                "LLL",
                'I', "ingotThaumium",
                'P', "plateThaumium",
                'S', "quicksilver",
                'C', manasteel,
                'L', ItemsTC.fabric
        );

        EnchantmentHelper.setEnchantments(ImmutableMap.of(CoreEnchantments.holding, 1), setDefaultInventoryTag(manasteel));

        RecipeHelper.addShapedRecipe(manasteel,
                "IPI",
                "LCL",
                "LLL",
                'I', "ingotManasteel",
                'P', "plateManasteel",
                'L', "itemLeather",
                'C', "chest"
        );

        RecipeHelper.addShapedUpgradeRecipe(terrasteel,
                "IPI",
                "SCS",
                "LLL",
                'I', "ingotTerrasteel",
                'P', "plateTerrasteel",
                'S', "manaDiamond",
                'C', thaumium,
                'L', "clothManaweave"
        );
        RecipeHelper.addShapedUpgradeRecipe(elementium,
                "IPI",
                "SCS",
                "LLL",
                'I', "ingotElvenElementium",
                'P', "plateElementium",
                'S', "gemResonating",
                'C', terrasteel,
                'L', ModItems.spellCloth
        );

        RecipeHelper.addShapedUpgradeRecipe(voidmetal,
                "IPI",
                "SCS",
                "LGL",
                'I', "ingotVoid",
                'P', "plateVoid",
                'S', "blockPurpur",
                'C', elementium,
                'L', "clothCrimson",
                'G', ItemsTC.primordialPearl
        );
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        if (CoreUtils.isFakePlayer(player) || hand != EnumHand.MAIN_HAND) {
            return new ActionResult<>(EnumActionResult.FAIL, stack);
        }
        if (needsTag(stack)) {
            setDefaultInventoryTag(stack);
        }
        if (ServerHelper.isServerWorld(world)) {
            if (SecurityHelper.isSecure(stack) && SecurityHelper.isDefaultUUID(SecurityHelper.getOwner(stack).getId())) {
                SecurityHelper.setOwner(stack, player.getGameProfile());
                ChatHelper.sendIndexedChatMessageToPlayer(player, new TextComponentTranslation("chat.cofh.secure.item.success"));
                return new ActionResult<>(EnumActionResult.SUCCESS, stack);
            }
            if (canPlayerAccess(stack, player)) {
                if (player.isSneaking() && ItemHelper.getItemDamage(stack) != CREATIVE) {
                    player.openGui(Circadian.instance, GuiHandler.SATCHEL_FILTER_ID, world, 0, 0, 0);
                } else {
                    player.openGui(Circadian.instance, GuiHandler.SATCHEL_ID, world, 0, 0, 0);
                }
            } else if (SecurityHelper.isSecure(stack)) {
                ChatHelper.sendIndexedChatMessageToPlayer(player, new TextComponentTranslation("chat.cofh.secure.warning", SecurityHelper.getOwnerName(stack)));
                return new ActionResult<>(EnumActionResult.FAIL, stack);
            }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
