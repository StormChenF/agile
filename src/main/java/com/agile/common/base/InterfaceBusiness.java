package com.agile.common.base;

import java.util.HashMap;

public interface InterfaceBusiness {
	//请求参数
	public void setInParam(HashMap<String, Object> inParam);
	//响应参数
	public HashMap<String, Object> getOutParam();
	//系统缓存
	public void setOutParam(HashMap<String, Object> outParam);
	//调用请求方法
	public RETURN excuteMethod(String methodName) throws Exception;
}
