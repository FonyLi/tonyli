
package com.fonyli.tonyliweb.server.ds;

import com.fonyli.tonyliweb.server.handler.FormHttpHandler;
import com.fonyli.tonyliweb.server.handler.SelectTeacherHandler;
import com.fonyli.tonyliweb.server.handler.StaticHtmlHandler;
import com.fonyli.tonyliweb.server.handler.UnkownUrlHandler;
import com.fonyli.tonyliweb.server.handler.UploaderHttpHandler;
import com.fonyli.tonyliweb.server.handler.WritePoemHandler;

/**
 * @author TonyLi 
 */
public interface HttpHandlerConstants {
	
	//static
	public final Class  HANDLER_API_STATIC = StaticHtmlHandler.class;
	public final String			API_STATIC ="/ideas";
	
	public final Class  HANDLER_API_UNKOWN = UnkownUrlHandler.class;
	public final String			API_UNKOWN ="/ideas/poemWriter/markov-chain/api/v1/unkown";
	
	
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
