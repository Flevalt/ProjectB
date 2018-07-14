package engine.display;

import engine.math.Vector3f;

public class Camera {
	
	private Vector3f position = new Vector3f(0, 0, 1);
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camera() {
		
	}
	
	/**
	 * Adds the values to the position of the {@link Camera}.
	 * @param x - Position on the x axis.
	 * @param y - Position on the y axis.
	 * @param z - Position on the z axis.
	 */
	public void changePosition(float x, float y, float z) {
		this.position.x += x;
		this.position.y += y;
		this.position.z += z;
	}
	
	/**
	 * Adds the {@link Vector3f} to the position of the {@link Camera}.
	 * @param vector to be added.
	 */
	public void changePosition(Vector3f vector) {
		this.position.x += vector.x;
		this.position.y += vector.y;
		this.position.z += vector.z;
	}

	public void rotate(Vector3f vector) {
		this.pitch += vector.x;
		this.yaw += vector.y;
		this.roll += vector.z;
	}
	
	public void rotate(float x, float y, float z) {
		this.pitch += x;
		this.yaw += y;
		this.roll += z;
	}
	
	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
}
