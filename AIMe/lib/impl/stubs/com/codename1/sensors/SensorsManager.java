package com.codename1.sensors;


/**
 * 
 *  @author Chen
 */
public class SensorsManager {

	public static final int TYPE_GYROSCOPE = 0;

	public static final int TYPE_ACCELEROMETER = 1;

	public static final int TYPE_MAGNETIC = 2;

	public static SensorsManager getSenorsManager(int type) {
	}

	public void registerListener(SensorListener listener) {
	}

	public void deregisterListener(SensorListener listener) {
	}

	public static void onSensorChanged(int type, float x, float y, float z) {
	}
}
