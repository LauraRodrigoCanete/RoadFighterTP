package es.ucm.tp1.supercars.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import es.ucm.tp1.supercars.control.Level;
import es.ucm.tp1.supercars.exceptions.InputOutputRecordException;
import es.ucm.tp1.supercars.utils.StringUtils;

public class Record {
	
	private static final String FAILED_LOAD_MSG = "Failed to load record";
	
	private static final String FAILED_SAVE_MSG = "Failed to save record";
	
	private long record;
	private Level level;
	
	public Record(Game game) throws InputOutputRecordException{
		level = game.getLevel();
		cargarRecord();
	}
	
	public long getRecord() {
		return this.record;
	}
	
	public void guardarRecord() throws InputOutputRecordException{
		StringBuilder allRecords = new StringBuilder("");
		try(BufferedReader input = new BufferedReader(new FileReader("record.txt"))) {
			String l;
			while((l = input.readLine()) != null){ 
				String[] parts = l.split(":");
				if(!level.name().equalsIgnoreCase(parts[0])){
					allRecords.append(l).append(StringUtils.LINE_SEPARATOR);
				}
			}
		}
		catch(IOException e) {
			throw new InputOutputRecordException(String.format("[ERROR]: %s%n%n", FAILED_LOAD_MSG), e);
		}
		try(BufferedWriter output = new BufferedWriter(new FileWriter("record.txt"))) {
			allRecords.append(level.name()).append(":").append(record);
			output.write(allRecords.toString());
		}
		catch(IOException e) {
			throw new InputOutputRecordException(String.format("[ERROR]: %s%n%n", FAILED_SAVE_MSG), e);
		}
	}
	
	
	
	public void cargarRecord() throws InputOutputRecordException{
		try(BufferedReader input = new BufferedReader(new FileReader("record.txt"))) {
			String l;
			boolean encontrado = false;
			while((l = input.readLine()) != null && !encontrado){ 
				String[] parts = l.split(":");
				if(level.name().equalsIgnoreCase(parts[0])){
					record = Long.valueOf(parts[1]);
					encontrado = true;
				}
			}
			if(!encontrado) {
				record = Long.MAX_VALUE;
				System.out.println("Creating default record for level '" + level + "'");
			}
		}
		catch(IOException e) {
			throw new InputOutputRecordException(String.format("[ERROR]: %s%n%n", FAILED_LOAD_MSG), e);
		}
	}
	
	public boolean isNewRecord(long time) {
		if (record > time) {	
			record = time;
			return true;
		}
		return false;
	}
	
	
}