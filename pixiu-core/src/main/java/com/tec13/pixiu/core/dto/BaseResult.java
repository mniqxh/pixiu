package com.tec13.pixiu.core.dto;


public class BaseResult<T>{
	private boolean success;
	private T result;
	private String msg;
	private String code;
	public BaseResult(){
		
	}
	
	public BaseResult(T result){
		this.result = result;
		setSuccess(true);
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	/**
	 * @param success
	 * @return
	 */
	public BaseResult<T> setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public T getResult() {
		return result;
	}

	public BaseResult<T> setResult(T result) {
		this.result = result;
		this.success = true;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public BaseResult<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	


	@Override
	public String toString() {
		return "BaseResult [code="+code+", msg=" + msg + ", result=" + result + ", success="
				+ success + "]";
	}

}
