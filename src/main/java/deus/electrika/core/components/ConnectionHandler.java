package deus.electrika.core.components;

import deus.electrika.core.interfaces.IStandarComponent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Handles connections between components in the electrical system.
 * Provides methods to manage and manipulate connections.
 */
public class ConnectionHandler {

	// Map to store connections with unique keys
	private HashMap<String, Connection> connections = new HashMap<>();

	/**
	 * Default constructor for ConnectionHandler.
	 * Initializes an empty connections map.
	 */
	public ConnectionHandler(){}

	/**
	 * Constructor that initializes ConnectionHandler with existing connections.
	 * @param connections Initial connections to populate the handler.
	 */
	public ConnectionHandler(HashMap<String, Connection> connections){
		this.connections = connections;
	}

	/**
	 * Sets the connections map to a new HashMap.
	 * @param connections New connections map to set.
	 */
	public void setConnections(HashMap<String, Connection> connections) {
		this.connections = connections;
	}

	/**
	 * Retrieves the current connections map.
	 * @return HashMap containing all connections.
	 */
	public HashMap<String, Connection> getConnections() {
		return connections;
	}

	/**
	 * Deletes a connection by its key.
	 * @param key Key of the connection to delete.
	 */
	public void deleteConnection(String key){
		System.out.println("DELETED CONNECTION: " + key);
		connections.remove(key);
	}

	/**
	 * Adds a new connection using individual parameters.
	 * @param key Key to associate with the connection.
	 * @param x X-coordinate of the connection.
	 * @param y Y-coordinate of the connection.
	 * @param z Z-coordinate of the connection.
	 * @param stdc Standard component to connect.
	 */
	public void addConnection(String key, int x, int y, int z, IStandarComponent stdc) {
		connections.put(key, new Connection(x, y, z, stdc));
	}

	/**
	 * Adds a new connection using a Connection object.
	 * @param key Key to associate with the connection.
	 * @param connection Connection object to add.
	 */
	public void addConnection(String key, Connection connection) {
		connections.put(key, connection);
	}

	/**
	 * Retrieves the first connection from a map.
	 * @param map Map of connections.
	 * @param <K> Type of the map key.
	 * @return First connection in the map, or null if the map is empty.
	 */
	public static <K> Connection getFirstConnection(HashMap<K, Connection> map) {
		if (map.isEmpty()) {
			return null;
		}
		Iterator<Map.Entry<K, Connection>> iterator = map.entrySet().iterator();
		return iterator.next().getValue();
	}
}
