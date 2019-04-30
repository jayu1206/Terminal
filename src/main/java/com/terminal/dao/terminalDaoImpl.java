package com.terminal.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.terminal.model.Terminal;

@Repository
public class terminalDaoImpl implements terminalDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public ArrayList<Terminal> getTerminal() {
		// TODO Auto-generated method stub
		ArrayList<Terminal> listTerminal = null;
				 Terminal terminal = null;
				try {
					Session session = sessionFactory.getCurrentSession();
		             
		            Criteria criteria = session.createCriteria(Terminal.class);
		            listTerminal = (ArrayList<Terminal>) criteria.list();
		            
		           
		        }
		        catch (HibernateException e) {
		            e.printStackTrace();
		            
		        }
				
				
				return listTerminal;
	}

	@Override
	public Terminal updateTerminalTime(Terminal terminal) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(terminal);
		return terminal;
	}

}
