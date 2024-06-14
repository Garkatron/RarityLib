package deus.electrika.core.components;

import deus.electrika.core.interfaces.IProvider;
import deus.electrika.core.interfaces.IStandarComponent;

public class Connection {
	private int x;
	private int y;
	private int z;
	private IStandarComponent stdc;
	public Connection(int x, int y, int z, IStandarComponent stdc) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.stdc = stdc;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	public IStandarComponent getStdc() {
		return stdc;
	}
}
