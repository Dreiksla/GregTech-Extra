package com.dreiksla.gtextra.integration.ae2.machine;

import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.ConfiguratorPanel;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.ButtonConfigurator;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.CircuitFancyConfigurator;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.FancyInvConfigurator;
import com.gregtechceu.gtceu.api.machine.fancyconfigurator.FancyTankConfigurator;
import com.gregtechceu.gtceu.api.machine.trait.RecipeHandlerList;
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler;
import com.gregtechceu.gtceu.common.data.machines.GTAEMachines;
import com.gregtechceu.gtceu.integration.ae2.gui.widget.AETextInputButtonWidget;
import com.gregtechceu.gtceu.integration.ae2.gui.widget.slot.AEPatternViewSlotWidget;
import com.gregtechceu.gtceu.integration.ae2.machine.MEPatternBufferPartMachine;
import com.gregtechceu.gtceu.integration.ae2.machine.trait.InternalSlotRecipeHandler;

import com.lowdragmc.lowdraglib.gui.texture.GuiTextureGroup;
import com.lowdragmc.lowdraglib.gui.util.ClickData;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;

import appeng.api.crafting.IPatternDetails;
import appeng.api.crafting.PatternDetailsHelper;
import appeng.api.implementations.blockentities.PatternContainerGroup;
import appeng.api.inventories.InternalInventory;
import appeng.api.networking.IGridNodeListener;
import appeng.api.networking.crafting.ICraftingProvider;
import appeng.api.stacks.AEItemKey;
import appeng.crafting.pattern.EncodedPatternItem;
import appeng.crafting.pattern.ProcessingPatternItem;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class MESuperPatternBufferPartMachine extends MEPatternBufferPartMachine {

    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(
            MESuperPatternBufferPartMachine.class, MEPatternBufferPartMachine.MANAGED_FIELD_HOLDER);
    
    protected static final int SUPER_MAX_PATTERN_COUNT = 54;
    
    private final InternalInventory customInternalPatternInventory = new InternalInventory() {
        @Override
        public int size() {
            return SUPER_MAX_PATTERN_COUNT;
        }

        @Override
        public ItemStack getStackInSlot(int slotIndex) {
            return customPatternInventory.getStackInSlot(slotIndex);
        }

        @Override
        public void setItemDirect(int slotIndex, ItemStack stack) {
            customPatternInventory.setStackInSlot(slotIndex, stack);
            customPatternInventory.onContentsChanged(slotIndex);
            onSuperPatternChange(slotIndex);
        }
    };

    @Getter
    @Persisted
    @DescSynced
    private final CustomItemStackHandler customPatternInventory = new CustomItemStackHandler(SUPER_MAX_PATTERN_COUNT);

    @Getter
    @Persisted
    protected final InternalSlot[] customInternalInventory = new InternalSlot[SUPER_MAX_PATTERN_COUNT];

    protected final InternalSlotRecipeHandler customRecipeHandler;

    private final BiMap<IPatternDetails, InternalSlot> customDetailsSlotMap = HashBiMap.create(SUPER_MAX_PATTERN_COUNT);
    private boolean superNeedPatternSync;

    @DescSynced
    @Persisted
    private String superCustomNameStr = "";

    public MESuperPatternBufferPartMachine(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        this.customPatternInventory.setFilter(stack -> stack.getItem() instanceof ProcessingPatternItem);
        for (int i = 0; i < this.customInternalInventory.length; i++) {
            this.customInternalInventory[i] = new InternalSlot();
        }
        this.customRecipeHandler = new InternalSlotRecipeHandler(this, customInternalInventory);
    }

    @Override
    public void setCustomName(String name) {
        this.superCustomNameStr = name;
        super.setCustomName(name);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        if (getLevel() instanceof ServerLevel serverLevel) {
            serverLevel.getServer().tell(new TickTask(1, () -> {
                for (int i = 0; i < customPatternInventory.getSlots(); i++) {
                    var pattern = customPatternInventory.getStackInSlot(i);
                    var patternDetails = PatternDetailsHelper.decodePattern(pattern, getLevel());
                    if (patternDetails != null) {
                        this.customDetailsSlotMap.put(patternDetails, this.customInternalInventory[i]);
                    }
                }
                superNeedPatternSync = true;
            }));
        }
    }

    @Override
    public List<RecipeHandlerList> getRecipeHandlers() {
        return customRecipeHandler.getSlotHandlers();
    }

    @Override
    public void onMainNodeStateChanged(IGridNodeListener.State reason) {
        super.onMainNodeStateChanged(reason);
    }

    @Override
    protected void update() {
        if (superNeedPatternSync) {
            ICraftingProvider.requestUpdate(getMainNode());
            this.superNeedPatternSync = false;
        }
    }

    private void refundAllSuper(ClickData clickData) {
        if (!clickData.isRemote) {
            for (InternalSlot internalSlot : customInternalInventory) {
                if (internalSlot != null) {
                    internalSlot.refund();
                }
            }
        }
    }

    private void onSuperPatternChange(int index) {
        if (isRemote()) return;
        var internalInv = customInternalInventory[index];
        var newPattern = customPatternInventory.getStackInSlot(index);
        var newPatternDetails = PatternDetailsHelper.decodePattern(newPattern, getLevel());
        var oldPatternDetails = customDetailsSlotMap.inverse().get(internalInv);
        customDetailsSlotMap.forcePut(newPatternDetails, internalInv);
        if (oldPatternDetails != null && !oldPatternDetails.equals(newPatternDetails)) {
            internalInv.refund();
        }
        superNeedPatternSync = true;
    }

    @Override
    public void attachConfigurators(ConfiguratorPanel configuratorPanel) {
        configuratorPanel.attachConfigurators(new ButtonConfigurator(
                new GuiTextureGroup(GuiTextures.BUTTON, GuiTextures.REFUND_OVERLAY), this::refundAllSuper)
                .setTooltips(List.of(Component.translatable("gui.gtceu.refund_all.desc"))));
        
        if (isHasCircuitSlot() && isCircuitSlotEnabled()) {
            configuratorPanel.attachConfigurators(new CircuitFancyConfigurator(circuitInventory.storage));
        }
        
        configuratorPanel.attachConfigurators(new FancyInvConfigurator(
                shareInventory.storage, Component.translatable("gui.gtceu.share_inventory.title"))
                .setTooltips(List.of(
                        Component.translatable("gui.gtceu.share_inventory.desc.0"),
                        Component.translatable("gui.gtceu.share_inventory.desc.1"))));
                        
        configuratorPanel.attachConfigurators(new FancyTankConfigurator(
                shareTank.getStorages(), Component.translatable("gui.gtceu.share_tank.title"))
                .setTooltips(List.of(
                        Component.translatable("gui.gtceu.share_tank.desc.0"),
                        Component.translatable("gui.gtceu.share_inventory.desc.1"))));
    }

    @Override
    public Widget createUIWidget() {
        int rowSize = 9;
        int colSize = 6; 
        var group = new WidgetGroup(0, 0, 18 * rowSize + 16, 18 * colSize + 16);
        int index = 0;
        
        for (int y = 0; y < colSize; ++y) {
            for (int x = 0; x < rowSize; ++x) {
                int finalI = index;
                var slot = new AEPatternViewSlotWidget(customPatternInventory, index++, 8 + x * 18, 14 + y * 18)
                        .setOccupiedTexture(GuiTextures.SLOT)
                        .setItemHook(stack -> {
                            if (!stack.isEmpty() && stack.getItem() instanceof EncodedPatternItem iep) {
                                final ItemStack out = iep.getOutput(stack);
                                if (!out.isEmpty()) {
                                    return out;
                                }
                            }
                            return stack;
                        })
                        .setChangeListener(() -> onSuperPatternChange(finalI))
                        .setBackground(GuiTextures.SLOT, GuiTextures.PATTERN_OVERLAY);
                group.addWidget(slot);
            }
        }

        group.addWidget(new LabelWidget(
                8,
                2,
                () -> this.isOnline ? "gtceu.gui.me_network.online" : "gtceu.gui.me_network.offline"));

        group.addWidget(new AETextInputButtonWidget(18 * rowSize + 8 - 70, 2, 70, 10)
                .setText(superCustomNameStr)
                .setOnConfirm(this::setCustomName)
                .setButtonTooltips(Component.translatable("gui.gtceu.rename.desc")));

        return group;
    }

    @Override
    public List<IPatternDetails> getAvailablePatterns() {
        return customDetailsSlotMap.keySet().stream().filter(Objects::nonNull).toList();
    }

    @Override
    public boolean pushPattern(IPatternDetails patternDetails, appeng.api.stacks.KeyCounter[] inputHolder) {
        if (!getMainNode().isActive() || !customDetailsSlotMap.containsKey(patternDetails)) {
            return false;
        }
        var slot = customDetailsSlotMap.get(patternDetails);
        if (slot != null) {
            slot.pushPattern(patternDetails, inputHolder);
            return true;
        }
        return false;
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public InternalInventory getTerminalPatternInventory() {
        return customInternalPatternInventory;
    }

    @Override
    public PatternContainerGroup getTerminalGroup() {
        if (superCustomNameStr != null && !superCustomNameStr.isEmpty()) {
            return new PatternContainerGroup(
                    AEItemKey.of(GTAEMachines.ME_PATTERN_BUFFER.getItem()),
                    Component.literal(superCustomNameStr),
                    Collections.emptyList());
        } else {
            return new PatternContainerGroup(
                    AEItemKey.of(GTAEMachines.ME_PATTERN_BUFFER.getItem()),
                    Component.translatable("gtextra.machine.super_pattern_buffer.name"),
                    Collections.emptyList());
        }
    }

    @Override
    public void onMachineRemoved() {
        clearInventory(customPatternInventory);
        clearInventory(shareInventory);
    }
}