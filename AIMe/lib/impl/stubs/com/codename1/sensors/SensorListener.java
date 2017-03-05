package com.codename1.sensors;


/**
 * 
 *  @author Chen
 */
public interface SensorListener {

	public void onSensorChanged(long timeStamp, float x, float y, float z);
}
