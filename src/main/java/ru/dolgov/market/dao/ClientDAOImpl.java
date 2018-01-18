package ru.dolgov.market.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ru.dolgov.market.dao.interfaces.ClientDAO;
import ru.dolgov.market.domain.Cart;
import ru.dolgov.market.domain.Client;
import ru.dolgov.market.jdbc.DbConnection;

public class ClientDAOImpl implements ClientDAO{
	private final String GET_CLIENT_BY_ID = "select * from Clients where email = ?;";
	private final String SAVE_CLIENT = "insert into Clients (name, email, phonenumber) values (?, ?, ?)";
	private final String GET_ID_CLIENT = "select @@IDENTITY";
	
	private PreparedStatement preparedStatementGetById;
	private PreparedStatement preparedStatementSaveClient;

	public Client getClientByEmail(String email) throws SQLException {
		
		preparedStatementGetById.setString(1, email);
		ResultSet rs = preparedStatementGetById.executeQuery();
		
		Client client = new Client();
		
		if (rs.next()) {
			client.setId(rs.getInt("id"));
			client.setName(rs.getString("name"));
			client.setPhoneNumber(rs.getString("phonenumber"));
			client.setEmail(rs.getString("email"));
		}
		
		return client;

	}
	
	public void saveClient(Client client) throws SQLException {
		
		preparedStatementSaveClient.setString(1, client.getName());
		preparedStatementSaveClient.setString(2, client.getEmail());
		preparedStatementSaveClient.setString(3, client.getPhoneNumber());
		preparedStatementSaveClient.execute();
		
		Statement statement = DbConnection.getConnection().createStatement();
		ResultSet rs = statement.executeQuery(GET_ID_CLIENT);
		int id = -1;
		if (rs.next()) {
			id = rs.getInt(1);
		}
		client.setId(id);		
	}
	
	public ClientDAOImpl() throws SQLException {
		preparedStatementGetById = DbConnection.getConnection().prepareStatement(GET_CLIENT_BY_ID);
		preparedStatementSaveClient = DbConnection.getConnection().prepareStatement(SAVE_CLIENT);
	}

}
