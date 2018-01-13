package ru.dolgov.market.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ru.dolgov.market.domain.Client;
import ru.dolgov.market.jdbc.DbConnection;

public class ClientDAO {
	private final String GET_CLIENT_BY_ID = "select * from market.clients where id = ";

	public Client getClientById(Long id) throws SQLException {
		
		Connection connection = DbConnection.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(GET_CLIENT_BY_ID + id.toString() + ";");
		
		Client client = new Client();
		
		if (rs.next()) {
			client.setId(rs.getLong("id"));
			client.setName(rs.getString("name"));
			client.setPhoneNumber(rs.getString("phonenumber"));
			client.setEmail(rs.getString("email"));
		}
		
		connection.close();
		
		return client;
		
		
		
	}
}
