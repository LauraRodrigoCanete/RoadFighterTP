package es.ucm.tp1.supercars.view;

public abstract class View {
	protected abstract String getInfo();
	public abstract String toString();
	
	public static String formatTime(long t) {
		return String.format("%.2f s", t / 1000.0);
	}
}
