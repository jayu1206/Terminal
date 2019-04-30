package com.terminal.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.terminal.dao.terminalDao;
import com.terminal.model.Terminal;

@Service
@Transactional
public class terminalServiceImpl implements terminalService {

	@Autowired
	private terminalDao dao;
	
	
	@Override
	@Transactional
	public Terminal getTerminal() {
		// TODO Auto-generated method stub
		synchronized (dao) {
			ArrayList<Terminal> list = dao.getTerminal();
			if(list.isEmpty()){
				return null;
			}else{
				for(Terminal terminal : list){
					if(terminal.getTimeStamp()==null){
						terminal.setTimeStamp(new Date());
						dao.updateTerminalTime(terminal);
						return terminal;
					}else{
						Date dt = new Date();
						long diff = dt.getTime() - terminal.getTimeStamp().getTime();
						long diffSeconds = diff / 1000 % 60;
						long diffMinutes = diff / (60 * 1000) % 60;
						System.out.println("terminal id : "+terminal.getTerminalId() + " minuts : "+diffMinutes +  " diff : "+  diffSeconds);
						
						if( diffMinutes!=0 ||  diffSeconds > 30){
							
							if(terminal.getSequenceNo()==7){
								terminal.setSequenceNo(0);
							}else{
								terminal.setSequenceNo(terminal.getSequenceNo()+1);
							}
							
							terminal.setTimeStamp(new Date());
							dao.updateTerminalTime(terminal);
							return terminal;
						}
						
					}
					
				}
			}
			
			return null;
		}
	}


	@Override
	public boolean releaseTerminals() {
		// TODO Auto-generated method stub
		synchronized (dao) {
			ArrayList<Terminal> list = dao.getTerminal();
			if(list.isEmpty()){
				return false;
			}else{
				try {
					for (Terminal terminal : list) {
						terminal.setSequenceNo(0);
						terminal.setTimeStamp(null);
						dao.updateTerminalTime(terminal);
					}
					return true;
				} catch (Exception e) {
					// TODO: handle exception
					return false;
				}
				
				
			}
		}
	}

}
