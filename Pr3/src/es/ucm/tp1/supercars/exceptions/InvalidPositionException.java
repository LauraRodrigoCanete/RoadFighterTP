package es.ucm.tp1.supercars.exceptions;

public class InvalidPositionException extends CommandExecuteException{
	public InvalidPositionException() { super(); }
	public InvalidPositionException(String message){ super(message); }
	public InvalidPositionException(Throwable cause){ super(cause); }
	public InvalidPositionException(String message, Throwable cause){
		super(message, cause);
	}
}
