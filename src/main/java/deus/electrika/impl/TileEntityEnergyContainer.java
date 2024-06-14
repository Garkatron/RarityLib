package deus.electrika.impl;

import deus.electrika.core.components.Connection;
import deus.electrika.core.components.ConnectionHandler;
import deus.electrika.core.interfaces.IProvider;
import deus.electrika.core.interfaces.IStandarComponent;
import net.minecraft.core.block.entity.TileEntity;
import org.lwjgl.Sys;

public class TileEntityEnergyContainer extends TileEntity implements IProvider, IStandarComponent {

	private double chargeState = 0;
	private double voltage = 220; // Supongo que es un valor estándar para la simulación
	private double current = 10;
	private double storedEnergy = 1000;
	private double maxEnergy = 1000;
	private double minEnergy = 0;
	private boolean isOn = true;
	private double chargeSpeed = current * voltage / 1000;
	private ConnectionHandler connectionHandler = new ConnectionHandler();
	public String name;

	@Override
	public void tick() {
		super.tick();
		//double _ = discharge(chargeSpeed);
		//displayInfo();
		if (storedEnergy < maxEnergy) {

			//current * voltage / 1000; // Energía en kWh
			if (!connectionHandler.getConnections().isEmpty()) {
				Connection fc = ConnectionHandler.getFirstConnection(connectionHandler.getConnections());
				IProvider provider = (IProvider) fc.getStdc();
				if (worldObj.getBlockTileEntity(fc.getX(),fc.getY(),fc.getZ())!=null) {
					System.out.println("CARGANDO: " + storedEnergy);
					recharge(provider.discharge(chargeSpeed));
				} else {
					connectionHandler.deleteConnection("(" + (fc.getX()) + ", " + (fc.getY()) + ", " + (fc.getZ()) + ")");
				}
			} else {
				searchConnection();

			}

		}
		displayInfo();
	}

	private void searchConnection() {
		int[][] directions = {
			{0, 1, 0},  // Arriba
			{0, -1, 0}, // Abajo
			{-1, 0, 0}, // Izquierda
			{1, 0, 0},  // Derecha
			{0, 0, -1}, // Adelante
			{0, 0, 1}   // Atrás
		};

		for (int[] direction : directions) {
			int dx = direction[0];
			int dy = direction[1];
			int dz = direction[2];

			TileEntity tileEntity = worldObj.getBlockTileEntity(x + dx, y + dy, z + dz);
			if (tileEntity instanceof IProvider) {
				IStandarComponent battery = (IStandarComponent) tileEntity;
				// Establecer la conexión con la batería encontrada
				System.out.println("Battery found at: (" + (x + dx) + ", " + (y + dy) + ", " + (z + dz) + ")");
				setConnection("(" + (x + dx) + ", " + (y + dy) + ", " + (z + dz) + ")", new Connection(x + dx, y + dy, z + dz, battery));
			}
		}
	}


	@Override
	public void setChargeState(double chargeState) {
		this.chargeState = chargeState;
	}

	@Override
	public double discharge(double amount) {
		if (amount > minEnergy && storedEnergy >= amount) {
			storedEnergy -= amount;
		} else {
			storedEnergy = 0;
		}
		updateChargeState();
		return amount;
	}

	@Override
	public void recharge(double amount) {
		double result = storedEnergy += amount;
		if (result >= minEnergy && result <= maxEnergy) {
			storedEnergy += result;
		} else if (result > maxEnergy) {
			storedEnergy = maxEnergy;
		}
		isOn = true;
		updateChargeState();

	}

	@Override
	public void displayInfo() {
		System.out.println("___________________________________________________");
		System.out.println(this.name);
		System.out.println("Voltage: " + voltage + "V");
		System.out.println("Current: " + current + "A");
		System.out.println("Stored Energy: " + storedEnergy + " kWh");
		System.out.println("Charge State: " + chargeState + "%");
		System.out.println("Status: " + (isOn ? "On" : "Off"));
	}

	@Override
	public double getCapacity() {
		return maxEnergy;
	}

	@Override
	public double getChargeState() {
		return this.chargeState;
	}

	@Override
	public void start() {
		this.isOn = true;
	}

	@Override
	public void stop() {
		this.isOn = false;
	}

	@Override
	public boolean isOn() {
		return this.isOn;
	}

	@Override
	public boolean isOff() {
		return !this.isOn;
	}

	@Override
	public double getVoltage() {
		return this.voltage;
	}

	@Override
	public void setVoltage(double voltage) {
		this.voltage = voltage;
	}

	@Override
	public double getCurrent() {
		return this.current;
	}

	@Override
	public void setCurrent(double current) {
		this.current = current;
	}

	@Override
	public double getStoredEnergy() {
		return this.storedEnergy;
	}

	@Override
	public void setStoredEnergy(double storedEnergy) {
		this.storedEnergy = storedEnergy;
	}

	@Override
	public void setConnection(String key, Connection connection) {
		connectionHandler.addConnection(key, connection);
		System.out.println("Connection set:\n" + connection.toString());
	}

	public void updateChargeState() {
		this.chargeState = (storedEnergy / maxEnergy) * 100;
	}
}
