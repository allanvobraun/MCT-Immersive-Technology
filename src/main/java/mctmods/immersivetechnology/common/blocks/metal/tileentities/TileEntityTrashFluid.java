package mctmods.immersivetechnology.common.blocks.metal.tileentities;

import blusunrize.immersiveengineering.common.blocks.IEBlockInterfaces.IBlockBounds;
import mctmods.immersivetechnology.common.Config;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import javax.annotation.Nullable;

public class TileEntityTrashFluid extends TileEntityGenericTrash implements IBlockBounds, IFluidTank, IFluidHandler, IFluidTankProperties {

	public String unit() {
		return ".osd.trash_fluid.unit";
	}

	public String unitPerSecond() {
		return ".osd.trash_fluid.unitlastsecond";
	}

	@Override
	public boolean hasCapability(final Capability<?> capability, final EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return true;
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(final Capability<T> capability, final EnumFacing facing) {
		if(capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return (T) this;
		return super.getCapability(capability, facing);
	}

	@Override
	public FluidStack getFluid() {
		return null;
	}

	@Override
	public int getFluidAmount() {
		return 0;
	}

	@Nullable
	@Override
	public FluidStack getContents() {
		return null;
	}

	@Override
	public int getCapacity() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean canFill() {
		return true;
	}

	@Override
	public boolean canDrain() {
		return false;
	}

	@Override
	public boolean canFillFluidType(FluidStack fluidStack) {
		return true;
	}

	@Override
	public boolean canDrainFluidType(FluidStack fluidStack) {
		return false;
	}

	FluidTankInfo info = new FluidTankInfo(null, Integer.MAX_VALUE);
	IFluidTankProperties[] tank = new IFluidTankProperties[] { this };

	@Override
	public FluidTankInfo getInfo() {
		return info;
	}

	@Override
	public IFluidTankProperties[] getTankProperties() {
		return tank;
	}

	@Override
	public int fill(FluidStack resource, boolean doFill) {
		int canTransfer = (int)Math.min(resource.amount, Config.ITConfig.Trash.fluid_max_void_rate - acceptedAmount);
		if(doFill) {
			acceptedAmount += canTransfer;
			packets++;
		}
		return canTransfer;
	}

	@Nullable
	@Override
	public FluidStack drain(FluidStack fluidStack, boolean b) {
		return null;
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		return null;
	}

}