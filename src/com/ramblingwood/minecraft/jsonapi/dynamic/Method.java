package com.ramblingwood.minecraft.jsonapi.dynamic;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Method {
	private String name = "Method name";
	private String desc = "Method description";
	private Class<?> returnValue = void.class;
	private String returnDesc = "Method return desc";
	private ArgumentList args;
	private Call call;
	
	public Method (JSONObject o) {
		setName((String)o.get("name"));
		setDesc((String)o.get("desc"));
		
		if(o.get("returns") != null && o.get("returns") instanceof JSONArray) {
			Class<?> c = Argument.getClassFromName((String)((JSONArray)o.get("returns")).get(0));
			if(c != null) {
				setReturnValue(c);
				setReturnDesc((String)((JSONArray)o.get("returns")).get(1));
			}
		}
		
		if(o.get("args") != null && o.get("args") instanceof JSONArray) {
			JSONArray thisargs = (JSONArray)o.get("args");
			
			for(Object obj : thisargs) {
				if(obj instanceof JSONArray) {
					args.add(new Argument((JSONArray) obj));
				}
			}
		}
		
		setCall(new Call((String)o.get("call"), args));
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setReturnValue(Class<?> returnValue) {
		this.returnValue = returnValue;
	}

	public Class<?> getReturnValue() {
		return returnValue;
	}

	public void setReturnDesc(String returnDesc) {
		this.returnDesc = returnDesc;
	}

	public String getReturnDesc() {
		return returnDesc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public void setCall(Call call) {
		this.call = call;
	}

	public Call getCall() {
		return call;
	}
}
