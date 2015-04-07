package com.chedb.model;


public class ModelServiceProvider implements java.io.Serializable {

	private String serviceId;
	
	private String providerId;

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

		
}
