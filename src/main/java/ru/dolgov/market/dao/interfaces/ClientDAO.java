package ru.dolgov.market.dao.interfaces;

import java.sql.SQLException;

import ru.dolgov.market.domain.Client;

public interface ClientDAO {

	public Client getClientByEmail(String email) throws SQLException;
	
	public void saveClient(Client client) throws SQLException;
}
