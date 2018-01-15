package ru.dolgov.market.dao.interfaces;

import java.sql.SQLException;

import ru.dolgov.market.domain.Client;

public interface ClientDAO {

	public Client getClientById(Integer id) throws SQLException;
	
	public void saveClient(Client client) throws SQLException;
}
