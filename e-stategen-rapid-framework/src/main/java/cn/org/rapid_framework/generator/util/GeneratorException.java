package cn.org.rapid_framework.generator.util;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import lombok.Cleanup;

public class GeneratorException extends RuntimeException{
	
	/**  */
    private static final long serialVersionUID = 1L;
    public List<Exception> exceptions = new ArrayList<>();
	
	public GeneratorException() {
		super();
	}

	public GeneratorException(String message, Throwable cause) {
		super(message, cause);
	}

	public GeneratorException(String message) {
		super(message);
	}

	public GeneratorException(String message,List<Exception> exceptions) {
		super(message);
		this.exceptions = exceptions;
	}
	
	public GeneratorException(Throwable cause) {
		super(cause);
	}
	

	public List<Exception> getExceptions() {
		return exceptions;
	}

	public void setExceptions(List<Exception> exceptions) {
		if(exceptions == null) throw new NullPointerException("'exceptions' must be not null");
		this.exceptions = exceptions;
	}
	
	public GeneratorException add(Exception e) {
		exceptions.add(e);
		return this;
	}
	
	public GeneratorException addAll(List<Exception> excetpions) {
		exceptions.addAll(excetpions);
		return this;
	}

	@Override
	public void printStackTrace() {
		printStackTrace(System.err);
	}

	@Override
	public void printStackTrace(PrintStream s) {
		s.println("GeneratorException:"+getMessage());
		for(Exception e : exceptions) {
		    e.printStackTrace(s);
		    s.println("--------------------------------------------------------------------------------------------------------------------------------");
		}
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		s.println("GeneratorException:"+getMessage());
		for(Exception e : exceptions) {
		    e.printStackTrace(s);
		    s.println("--------------------------------------------------------------------------------------------------------------------------------");
		}
	}
	
	public String toString() {
        try {
            @Cleanup
            StringWriter out= new StringWriter();
            for(Exception e : exceptions) {
                out.append(e.toString()+"\n");
            }
            return out.toString();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(),ex);
        }

	}
	
	
	
}
