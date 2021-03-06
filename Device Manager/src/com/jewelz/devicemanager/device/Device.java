package com.jewelz.devicemanager.device;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

public class Device {

	String ID;
	Hashtable<String, Object> props;
	Vector<Service> services = new Vector<Service>();
	
	@SuppressWarnings("unchecked")
	public Device(Hashtable<String, Object> device_info) {
		props = device_info;
		ID = (String) device_info.get("UPnP.device.UDN");
		Hashtable<String, ArrayList<Object>> Services =
				(Hashtable<String, ArrayList<Object>>) device_info.get("Device Services");
		for (String key : Services.keySet()) {
			services.add(new Service(ID, key, Services.get(key)));
		}
		props.remove("Device Services");
		props.remove("objectClass");
		props.remove("UPnP");
	}
	
	public Vector<Service> getServices() {
		return services;
	}
	
	public Hashtable<String, Object> getDeviceInfo() {
		return props;
	}
	
}
