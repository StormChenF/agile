package com.agile.common.server;

import com.agile.common.base.RETURN;
import java.util.Map;

public interface ServiceInterface {
	//设置请求参数
	void setInParam(Map<String, Object> inParam);
	//设置响应参数
	void setOutParam(String key, Object value);
	//提取响应参数
	Map<String, Object> getOutParam();
	//调用请求方法
	RETURN executeMethod(String methodName,Object object) throws Throwable;
}
