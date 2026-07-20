package gestiongala.exception;

public class FileModifyException extends RuntimeException
{
	public FileModifyException() {
		
	}
	public FileModifyException(String s) {
		super(s);
	}
	public FileModifyException(Throwable cause) {
		super(cause);
	}
	public FileModifyException(String s,Throwable cause) {
		super(s,cause);
	}
}
