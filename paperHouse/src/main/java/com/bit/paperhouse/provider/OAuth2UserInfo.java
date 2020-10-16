package com.bit.paperhouse.provider;

public interface OAuth2UserInfo {
	
	String getProviderId();
	String getProvider();
	String getEmail();
	String getName();

}
