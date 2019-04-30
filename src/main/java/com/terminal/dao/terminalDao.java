package com.terminal.dao;

import java.util.ArrayList;

import com.terminal.model.Terminal;

public interface terminalDao {
	public ArrayList<Terminal> getTerminal();
	public Terminal updateTerminalTime(Terminal terminal);

}
