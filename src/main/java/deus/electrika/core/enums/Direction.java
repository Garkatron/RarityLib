package deus.electrika.core.enums;

public enum Direction {
	UP(0, 1, 0),
	DOWN(0, -1, 0),
	LEFT(-1, 0, 0),
	RIGHT(1, 0, 0),
	BACK(0, 0, -1),
	FRONT(0, 0, 1),
	NONE(0,0,0);

	private final int x;
	private final int y;
	private final int z;

	Direction(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
}
