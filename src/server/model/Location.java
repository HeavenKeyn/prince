package server.model;

public class Location {
	private int k;
	private float x;
	private float y;
	private float z;
	public Location(int k, float x, float y) {
		this.k = k;
		this.x = x;
		this.y = y;
	}
	public Location(int k, float x, float y, float z) {
		this.k = k;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public Location() {
	}
	public int getK() {
		return k;
	}
	public Location setK(int k) {
		this.k = k;
		return this;
	}
	public float getX() {
		return x;
	}
	public Location setX(float x) {
		this.x = x;
		return this;
	}
	public float getY() {
		return y;
	}
	public Location setY(float y) {
		this.y = y;
		return this;
	}
	public float getZ() {
		return z;
	}
	public Location setZ(float z) {
		this.z = z;
		return this;
	}
	
	

}
