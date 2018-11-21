package co.teruh.planets.utils;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Timer {	
	/**
	 * Almost verbatim taken from https://gafferongames.com/post/fix_your_timestep/
	 * with guidance from SilverTiger's LWJGL FixedTimestep https://goo.gl/ER7jt5
	 */
	
	private double lastLoopTime; // Used to keep track of the last time loop looped

	private float timeCount; // the amount of time since last loop

	private int fps, fpsCount; // Frames per second

	private int ups, upsCount; // Updates per second, how often program should/is updat(e)/ing

	/**
	 * Initialize timer
	 */
	public void init() {
		// Start tracking loop times, starting at current time
		lastLoopTime = getTime();
	}

	/**
	 * Get GLFW system time
	 * 
	 * @return GLFW system time
	 */
	public double getTime() {
		return glfwGetTime();
	}

	/**
	 * Get delta time, or, the time
	 * 
	 * @return delta time
	 */
	public float getDelta() {
		double time = getTime();
		float delta = (float) (time - lastLoopTime);
		lastLoopTime = time;
		timeCount += delta;
		return delta;
	}

	/**
	 * Update FPS and UPS count, or, reset them after each loop
	 */
	public void update() {
		if (timeCount > 1f) {
			fps = fpsCount;
			fpsCount = 0;

			ups = upsCount;
			upsCount = 0;

			timeCount -= 1f;
		}
	}

	/**
	 * Increment FPS count
	 */
	public void updateFPS() {
		fpsCount++;
	}

	/**
	 * Increment UPS count
	 */
	public void updateUPS() {
		upsCount++;
	}

	/**
	 * Return FPS if fps are greater than zero, otherwise, return FPS count
	 * 
	 * @return fps or fps count
	 */
	public int getFPS() {
		return fps > 0 ? fps : fpsCount;
	}

	/**
	 * Return UPS if ups are greater than zero, otherwise, return UPS count
	 * 
	 * @return ups or ups count
	 */
	public int getUPS() {
		return ups > 0 ? ups : upsCount;
	}

	/**
	 * How long has it been since the loop last looped?
	 * 
	 * @return what time the loop last looped
	 */
	public double getLastLoopTime() {
		return lastLoopTime;
	}
}
