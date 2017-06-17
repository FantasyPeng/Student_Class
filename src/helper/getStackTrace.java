package helper;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class getStackTrace {
	public static String getStack(Throwable t){
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    String stackMsg ;
	    try{
	        t.printStackTrace(pw);
	        stackMsg = sw.toString();
	        int casedInt = stackMsg.lastIndexOf("Caused by:");
	        if (casedInt != -1){
	            stackMsg = stackMsg.substring(casedInt, stackMsg.length());
	            if (stackMsg.split("at").length > 15){
	                Pattern p = Pattern.compile("at");
	                Matcher m = p.matcher(stackMsg);
	                int num = 0, index = -1;
	                while(m.find()){
	                    num++;
	                    if(15 == num){
	                        index = m.start();
	                        System.out.println(m.start());
	                        stackMsg = "</br>"+stackMsg.substring(0, index);
	                        break;
	                    }
	                }
	            }
	        }
	        return stackMsg;
	    }catch (Exception e){
	        e.printStackTrace();
	    }finally{
	        pw.close();
	    }

	    return sw.toString();
	}

}
