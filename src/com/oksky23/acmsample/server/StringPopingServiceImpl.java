package com.oksky23.acmsample.server;

import com.oksky23.acmsample.client.StringPopingService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class StringPopingServiceImpl extends RemoteServiceServlet implements
		StringPopingService {
	
	
	private class ExecuteHelper{
		boolean breakFlag = false;
		String result="";
		public void setDefault(){
			breakFlag = false;
			result="";
		}
		public String execute(String in){
		if(breakFlag){return result;}
		String nomalizedString = this.shortenString(in);
		String[] expandedString = this.expandStringToMarix(nomalizedString);
		String[] pickedString = this.pickString(expandedString);
		
		if(pickedString.length > 0){
			for(int i = 0 ; i < pickedString.length ; i++){
				
				if(pickedString[i].length() ==1){
					if(pickedString[i].charAt(0) >= 97){
						result = "LOOP";
						continue;
					}else{
						result = "ZERO";
						breakFlag=true;
						break;
					}		
				}else
					this.execute(pickedString[i]);
				
				
			}
		}else if(pickedString.length == 0){
			result = "LOOP";
		}
		return result;
	}
	public String shortenString(String in){
		System.out.println("start shortenString");
		String returnString = new String();
		int count = in.length()-1;
		if(count==0){
			returnString = in;
			System.out.println(returnString);
			System.out.println("end shortenString\n");
			return returnString;
		}
		
		for(int i =0; i<count;i++){
			//if(i < len -1){
			if((in.charAt(i)==in.charAt(i+1)) || (Math.abs((in.charAt(i))-(in.charAt(i+1)))==32)){
				String temp = (in.substring(i, i+1)).toUpperCase();//i++;
				System.out.println(temp);

				if((returnString.length()>0) && ((returnString.substring(returnString.length()-1).equals(temp)))){
					continue;
				}else{
					returnString = returnString+(in.substring(i, i+1)).toUpperCase();
				}
				if(i==(count-1)){
					if((returnString.length()>0) && ((returnString.substring(returnString.length()-1).equals(temp)))){
						continue;}
					returnString = returnString+(in.substring(i+1, i+2));
				}
				//String s = returnString.
			}
			else{
				if(returnString.length()>0 && (returnString.substring(returnString.length()-1).equals(in.substring(i,i+1).toUpperCase()))){
					if(i==(count-1)){
						returnString = returnString+(in.substring(i+1, i+2));
					}
					continue;
				}else{
					returnString = returnString+(in.substring(i, i+1));
					if(i==(count-1)){
						returnString = returnString+(in.substring(i+1, i+2));
					}
					
				}
				
				
				
			}//}
		}
		
		System.out.println(returnString);
		System.out.println("end shortenString\n");
		return returnString;
	}
	
	
	public String[] expandStringToMarix(String in){
		int lengthString = in.length();
		String[] expandedString = new String[lengthString];
		System.out.println("start expandString");
		for(int i = 0; i < lengthString; i++){
			String temp ="";
			
			if(i==0){
				temp = in;
			}
			
			temp = in.substring(i, i+1) + in.substring(0, i) + in.substring(i+1, in.length()); 
			//pre + temp +next -> temp + pre + next
			
			expandedString[i] = new String(temp);
			System.out.println(expandedString[i]);
		}
		System.out.println("end exapandString\n");
		return expandedString;
	}
	
	public String[] pickString(String[] in){
		int lengthString = in.length;
		int newIndex=0;
		String temp = "";
		String pickedString[];
		int lengthElements = in[0].length();
		
		if(lengthElements==1){pickedString= new String[1];pickedString[0]=in[0];breakFlag = true;return pickedString;}
		
		System.out.println("Start pickString");
		for(int i = 0 ; i < lengthString ; i++){
			
			if(in[i].charAt(0) <'a'){
				temp += in[i];++newIndex;
			}else{
				in[i] = "";
			}
			
		}
		pickedString = new String[newIndex];
		System.out.println(pickedString.length);
		
		if(pickedString.length==0){
			pickedString = new String[1];
			pickedString[0] = "a";
			return pickedString;
		}
		for(int i = 0; i < newIndex ; i++){
			pickedString[i] = new String(temp.substring(i*lengthElements+1, (i+1)*lengthElements));
			System.out.println(pickedString[i]);
		}
		if(pickedString[0]==""){System.out.println("null");}
		System.out.println("end pickString\n");
		return pickedString;
	}
	}
	
	@Override
	public String execute(String in){
		ExecuteHelper e = new ExecuteHelper();
		e.execute(in);
		String result = e.result;
		e.setDefault();
		return result;
	}	

}