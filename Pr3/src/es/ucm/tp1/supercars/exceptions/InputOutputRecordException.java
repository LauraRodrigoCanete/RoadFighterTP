package es.ucm.tp1.supercars.exceptions;

public class InputOutputRecordException extends CommandExecuteException{
	public InputOutputRecordException() { super(); }
	public InputOutputRecordException(String message){ super(message); }
	public InputOutputRecordException(Throwable cause){ super(cause); }
	public InputOutputRecordException(String message, Throwable cause){
		super(message, cause);
	}
}
