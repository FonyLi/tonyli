
package com.al.markovchain.server.ds;

import com.al.markovchain.server.handler.FormHttpHandler;
import com.al.markovchain.server.handler.SelectTeacherHandler;
import com.al.markovchain.server.handler.UnkownUrlHandler;
import com.al.markovchain.server.handler.UploaderHttpHandler;
import com.al.markovchain.server.handler.WritePoemHandler;

/**
 * @author TonyLi 
 */
public interface HttpHandlerConstants {
	
	public final Class  HANDLER_API_UNKOWN = UnkownUrlHandler.class;
	public final String			API_UNKOWN ="/markov-chain/api/v1/unkown";
	
	
	public final Class HANDLER_API_SELECT_TEACHER = SelectTeacherHandler.class;
	public final String		   API_SELECT_TEACHER = "/markov-chain/api/v1/selectTeacher";
	
	public final Class HANDLER_API_WRITE_POEM = WritePoemHandler.class;
	public final String		   API_WRITE_POEM = "/markov-chain/api/v1/writePoem";
	
	public final Class HANDLER_API_UPLOAD_FILE = UploaderHttpHandler.class;
	public final String		   API_UPLOAD_FILE = "/markov-chain/api/v1/uploadFile";
	
	public final Class HANDLER_API_FORM = FormHttpHandler.class;
	public final String		   API_FORM = "/markov-chain/api/v1/";
	
	public static final String PARA_RETURN_TYPE ="dataType";
    public static final String VALUE_RETURN_TYPE ="jsonp";
    public static final String PARA_CALL_BACK = "callback";
    
	public final int CODE_SUCC = 0;
	
	public static final String PARA_BEGIN = "begin";
	public static final String PARA_END = "end";
	
	public static final String PARA_TEACHER = "teacher";
}
