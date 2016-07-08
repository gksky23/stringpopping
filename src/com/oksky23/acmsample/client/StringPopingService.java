package com.oksky23.acmsample.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("stringpoping")
public interface StringPopingService extends RemoteService{

	String execute(String in/*, boolean flag*/);
}
