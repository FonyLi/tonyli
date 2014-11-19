package com.al.markovchain.server;

/**
 * @author TonyLi
 */
public class MetaData {
	
	public static final int META_DATA_SUCCESS_NULL = 1;
	public static final int META_DATA_SUCCESS_CODE = 0;
	public static final int META_DATA_FAILED_CODE = -1;
	
	
    private int code ;
    private String message ;

    public MetaData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
