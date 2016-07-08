package com.oksky23.acmsample.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface StringPopingServiceAsync {
	void execute(String in,/* boolean flag,*/ AsyncCallback<String> c);
}
