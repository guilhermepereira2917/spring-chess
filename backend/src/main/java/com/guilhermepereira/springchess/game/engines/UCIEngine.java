package com.guilhermepereira.springchess.game.engines;

import com.guilhermepereira.springchess.game.Board;
import com.guilhermepereira.springchess.game.moves.Move;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public abstract class UCIEngine {

	private final String executablePath;
	private BufferedWriter bufferedWriter;
	private BufferedReader bufferedReader;

	private final Logger logger = Logger.getAnonymousLogger();

	public UCIEngine(String executablePath) {
		this.executablePath = executablePath;
	}

	public void initialize() {
		try {
			Process process = new ProcessBuilder(executablePath).start();
			bufferedWriter = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		} catch (IOException ioException) {
			logger.log(Level.SEVERE, "an exception was thrown when trying to initialize the engine process", ioException);
		}
	}

	public void finish() {
		sendCommand("quit");

		try {
			bufferedWriter.close();
			bufferedReader.close();
		} catch (IOException ioException) {
			logger.log(Level.WARNING, "an exception was thrown when trying to finish the engine process", ioException);
		}
	}

	public String getBestMove(Board board) {
		try {
			sendCommand("isready");

			if (board.getPlayedMoves().isEmpty()) {
				sendCommand("position startpos");
			} else {
				String boardPlayedMoves = board.getPlayedMoves().stream().map(Move::getLongAlgebraicNotationRepresentation).collect(Collectors.joining(" "));
				sendCommand(String.format("position startpos moves %s", boardPlayedMoves));
			}

			sendCommand("go movetime 500");

			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.startsWith("bestmove")) {
					return line.split(" ")[1];
				}
			}
		} catch (IOException ioException) {
			logger.log(Level.SEVERE, "an exception was thrown when trying to get bestmove", ioException);
		}

		return null;
	}

	private void sendCommand(String command) {
		try {
			bufferedWriter.write(command + "\r\n");
			bufferedWriter.flush();
		} catch (IOException ioException) {
			logger.log(Level.SEVERE, "an exception was thrown when trying to send command to engine process", ioException);
		}
	}
}
