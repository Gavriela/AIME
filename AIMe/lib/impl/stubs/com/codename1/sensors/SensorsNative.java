package com.codename1.sensors;


/**
 * 
 *  @author Chen
 */
public interface SensorsNative extends com.codename1.system.NativeInterface {

	public void init(int type);

	public void registerListener(int type);

	public void deregisterListener(int type);
}
