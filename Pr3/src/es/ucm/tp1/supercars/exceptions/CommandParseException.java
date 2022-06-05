package es.ucm.tp1.supercars.exceptions;

public class CommandParseException extends GameException{
	public CommandParseException() { super(); }
	public CommandParseException(String message){ super(message); }
	public CommandParseException(Throwable cause){ super(cause); }
	public CommandParseException(String message, Throwable cause){
		super(message, cause);
	}
}
