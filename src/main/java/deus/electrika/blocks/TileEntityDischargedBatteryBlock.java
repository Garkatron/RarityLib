package deus.electrika.blocks;

import deus.electrika.impl.TileEntityEnergyContainer;

public class TileEntityDischargedBatteryBlock extends TileEntityEnergyContainer {
	public TileEntityDischargedBatteryBlock(){
		this.setStoredEnergy(0);
		stop();
		name="BATTERY_DISCHARGED";
	}
}
