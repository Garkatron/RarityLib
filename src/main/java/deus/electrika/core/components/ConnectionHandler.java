package deus.electrika.core.components;

import deus.electrika.core.interfaces.IStandarComponent;
import org.lwjgl.Sys;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ConnectionHandler {
	private HashMap<String, Connection> connections = new HashMap<String, Connection>();

	public ConnectionHandler(){}

	public ConnectionHandler(HashMap<String, Connection> connections){
		this.connections = connections;
	}

	public void setConnections(HashMap<String, Connection> connections) {
		this.connections = connections;
	}

	public HashMap<String, Connection> getConnections() {
		return connections;
	}

	public void deleteConnection(String key){
		System.out.println("DELETED CONNETION: "+key);
		connections.remove(key);
	}
	public void addConnection(String key, int x, int y, int z, IStandarComponent stdc) {
		connections.put(key,new Connection(x,y,z,stdc));
	}
	public void addConnection(String key, Connection connection) {
		connections.put(key, connection);
	}

	public static <K> Connection getFirstConnection(HashMap<K, Connection> map) {
		if (map.isEmpty()) {
			return null;
		}
		Iterator<Map.Entry<K, Connection>> iterator = map.entrySet().iterator();
		return iterator.next().getValue();
	}
}
