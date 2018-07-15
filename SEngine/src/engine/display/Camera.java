package engine.display;

import engine.math.Vector3f;

/**
 * @author BpZ
 *
 * Camera that defines what is diaplyed on the window.
 */
public class Camera {
	
	private Vector3f position = new Vector3f(0, 0, 0.5f);
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
	 * Changes the position values of the {@link Camera}.
	 * @param x - Position on the x axis.
	 * @param y - Position on the y axis.
	 * @param z - Position on the z axis.
	 */
	public void setPosition(float x, float y, float z) {
		this.position.x = x;
		this.position.y = y;
		this.position.z = z;
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
	
	/**
	 * Rotates the camera around its x axis.
	 * @param angle of the rotation.
	 */
	public void tilt(float angle) {
		this.pitch += angle;
	}
	
	/**
	 * Rotates the camera around its y axis.
	 * @param angle of the rotation.
	 */
	public void pan(float angle) {
		this.yaw += angle;
	}
	
	/**
	 * Rotates the camera around its z axis.
	 * @param angle of the rotation.
	 */
	public void roll(float angle) {
		this.roll += angle;
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
