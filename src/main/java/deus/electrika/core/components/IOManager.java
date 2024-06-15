package deus.electrika.core.components;

import deus.electrika.core.enums.Direction;
import deus.electrika.core.interfaces.IReceiver;
import deus.electrika.core.interfaces.IProvider;
import deus.electrika.core.interfaces.IStandarComponent;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;

/**
 * Manages connections and I/O interactions for an electrical component.
 * Allows configuring and managing input providers and output receivers,
 * as well as connecting and transferring energy between them.
 */
public class IOManager {

	// Input provider
	private IProvider inputProvider;

	// Output receiver
	private IReceiver outputReceiver;

	public IOManager() {}

	// I/O Configuration
	private String[] configuration = {
		"N", "I", " ", // 0 1
		"N", "N", "N", // 2 3 4
		" ", "O", " ", //   5
	};

	// Manages block coordinate connections
	public ConnectionHandler connectionHandler = new ConnectionHandler();

	/**
	 * Retrieves the I/O configuration directions based on the current configuration.
	 * @return Array of directions corresponding to the current configuration.
	 */
	public Direction[] getIOConfig() {
		Direction[] directions = new Direction[configuration.length];
		for (int i = 0; i < configuration.length; i++) {
			switch (configuration[i]) {
				case "I":
				case "O":
					directions[i] = getDirectionFromIndex(i);
					break;
				case "N": // No direction assigned
					break;
				default:
					break;
			}
		}
		return directions;
	}

	/**
	 * Deletes the input provider and its associated connection.
	 */
	public void deleteInputProvider() {
		if (hasInputProvider()) {
			Direction inputDirection = getInputDirection();
			connectionHandler.deleteConnection("(" + inputDirection.getX() + ", " + inputDirection.getY() + ", " + inputDirection.getZ() + ")");
			this.inputProvider = null;
		}
	}

	/**
	 * Retrieves the input direction based on the current configuration.
	 * @return Input direction or Direction.NONE if "I" is not found.
	 */
	private Direction getDirectionFromIndex(int index) {
		switch (index) {
			case 0:
				return Direction.BACK;
			case 1:
				return Direction.UP;
			case 2:
				return Direction.LEFT;
			case 3:
				return Direction.FRONT;
			case 4:
				return Direction.RIGHT;
			case 5:
				return Direction.DOWN;
			default:
				return Direction.NONE;
		}
	}

	/**
	 * Retrieves the input direction based on the current configuration.
	 * @return Input direction or Direction.NONE if "I" is not found.
	 */
	public Direction getInputDirection() {
		for (int i = 0; i < configuration.length; i++) {
			if (configuration[i].equals("I")) {
				return getDirectionFromIndex(i);
			}
		}
		return Direction.NONE;
	}

	/**
	 * Retrieves the output direction based on the current configuration.
	 * @return Output direction or Direction.NONE if "O" is not found.
	 */
	public Direction getOutputDirection() {
		for (int i = 0; i < configuration.length; i++) {
			if (configuration[i].equals("O")) {
				return getDirectionFromIndex(i);
			}
		}
		return Direction.NONE;
	}

	/**
	 * Retrieves an input provider from the world at the direction specified by the configuration.
	 * @param world World to search for the provider.
	 * @param x Base x coordinate.
	 * @param y Base y coordinate.
	 * @param z Base z coordinate.
	 * @return Standard component acting as an input provider, or null if not found.
	 */
	public IStandarComponent getInputProvider(World world, int x, int y, int z){
		Direction inputDir = getInputDirection();
		return (IStandarComponent) world.getBlockTileEntity(x + inputDir.getX(), y + inputDir.getY(), z + inputDir.getZ());
	}

	/**
	 * Retrieves an output receiver from the world at the direction specified by the configuration.
	 * @param world World to search for the receiver.
	 * @param x Base x coordinate.
	 * @param y Base y coordinate.
	 * @param z Base z coordinate.
	 * @return Standard component acting as an output receiver, or null if not found.
	 */
	public IStandarComponent getOutputReceiver(World world, int x, int y, int z){
		Direction outputDir = getOutputDirection();
		return (IStandarComponent) world.getBlockTileEntity(x + outputDir.getX(), y + outputDir.getY(), z + outputDir.getZ());
	}

	/**
	 * Retrieves the connection key from the input direction in the world.
	 * @param world World to search for the input provider.
	 * @param x Base x coordinate.
	 * @param y Base y coordinate.
	 * @param z Base z coordinate.
	 * @return Connection key, or null if the input provider is not found.
	 */
	public String getConnectionFromInput(World world, int x, int y, int z) {
		Direction inputDir = getInputDirection();
		IStandarComponent provider = (IStandarComponent) world.getBlockTileEntity(x + inputDir.getX(), y + inputDir.getY(), z + inputDir.getZ());
		String k = "(" + (inputDir.getX()) + ", " + (inputDir.getY()) + ", " + (inputDir.getZ()) + ")";
		if (provider == null) {
			return null;
		}
		connectionHandler.addConnection(k, new Connection(inputDir.getX(), inputDir.getY(), inputDir.getZ(), provider));
		this.inputProvider = (IProvider) provider;
		return k;
	}

	/**
	 * Retrieves the connection key from a specified direction in the world.
	 * @param direction Direction from which to retrieve the component.
	 * @param world World to search for the component.
	 * @param x Base x coordinate.
	 * @param y Base y coordinate.
	 * @param z Base z coordinate.
	 * @return Connection key, or null if the component is not found in the specified direction.
	 */
	public String getConnectionFrom(Direction direction, World world, int x, int y, int z) {
		IStandarComponent component = (IStandarComponent) world.getBlockTileEntity(x + direction.getX(), y + direction.getY(), z + direction.getZ());
		String k = "(" + (direction.getX()) + ", " + (direction.getY()) + ", " + (direction.getZ()) + ")";
		if (component == null) {
			return null;
		}
		connectionHandler.addConnection(k, new Connection(direction.getX(), direction.getY(), direction.getZ(), component));
		return k;
	}

	/**
	 * Checks if there are any connections in the connection handler.
	 * @return true if there are connections, false otherwise.
	 */
	public boolean hasConnections() {
		return !connectionHandler.getConnections().isEmpty();
	}

	/**
	 * Sets a new I/O configuration.
	 * @param configuration New input/output configuration array.
	 */
	public void setConfiguration(String[] configuration) {
		this.configuration = configuration;
	}

	/**
	 * Retrieves the current I/O configuration.
	 * @return Array of input/output configuration.
	 */
	public String[] getConfiguration() {
		return configuration;
	}

	/**
	 * Checks if there is an input provider assigned.
	 * @return true if there is an input provider assigned, false otherwise.
	 */
	public boolean hasInputProvider(){
		return this.inputProvider!=null;
	}
	public boolean hasInputProvider(World world, TileEntity tileEntity){
		return this.inputProvider != null && existsInputProviderTileEntity(world, tileEntity);
	}


	private boolean existsInputProviderTileEntity(World world, TileEntity tileEntity){
		Direction inputDirection = getInputDirection();
		return world.getBlockTileEntity(tileEntity.x + inputDirection.getX(),tileEntity.y + inputDirection.getY(),tileEntity.z + inputDirection.getZ())!=null;
	}

	/**
	 * Transfers energy from a provider (input) to a receiver (output).
	 * @param receiver Receiver of the energy.
	 * @param provider Provider of the energy.
	 */
	public void transfer(IReceiver receiver, IProvider provider) {
		double energyProvided = provider.discharge(provider.getChargeSpeed());
		receiver.recharge(energyProvided);

		// Calculate current assuming 1 second transfer time (simplified example)
		double current = energyProvided / ((IStandarComponent)provider).getVoltage(); // Assuming voltage is known

		System.out.println("Current: " + current + " Amps");
		System.out.println("Receiver charged at: " + String.format("%.2f", ((IStandarComponent) receiver).getStoredEnergy()));
		System.out.println("Provider charged at: " + String.format("%.2f", ((IStandarComponent) provider).getStoredEnergy()));
	}

	/**
	 * Retrieves the current input provider assigned.
	 * @return Current input provider, or null if none is assigned.
	 */
	public IProvider getInputProvider() {
		return this.inputProvider;
	}

	/**
	 * Retrieves the current output receiver assigned.
	 * @return Current output receiver, or null if none is assigned.
	 */
	public IReceiver getOutputReceiver() {
		return this.outputReceiver;
	}

}
