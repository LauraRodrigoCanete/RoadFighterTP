package es.ucm.tp1.supercars.exceptions;

public class NotEnoughCoinsException extends CommandExecuteException{
	public NotEnoughCoinsException() { super(); }
	public NotEnoughCoinsException(String message){ super(message); }
	public NotEnoughCoinsException(Throwable cause){ super(cause); }
	public NotEnoughCoinsException(String message, Throwable cause){
		super(message, cause);
	}
}
