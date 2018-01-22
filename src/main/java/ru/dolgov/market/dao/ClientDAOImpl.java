package ru.dolgov.market.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ru.dolgov.market.dao.interfaces.ClientDAO;
import ru.dolgov.market.domain.Client;
import ru.dolgov.market.jdbc.DbConnection;

public class ClientDAOImpl implements ClientDAO{
	private final String GET_CLIENT_BY_EMAIL = "select * from Clients where email = ?;";
	private final String SAVE_CLIENT = "insert into Clients (name, email, phonenumber) values (?, ?, ?)";
	private final String GET_ID_CLIENT = "select @@IDENTITY";
	
	public Client getClientByEmail(String email) throws SQLException {
		Connection connection = null;
		try {
			connection = DbConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_BY_EMAIL);
			
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			
			Client client = new Client();
			
			if (rs.next()) {
				client.setId(rs.getInt("id"));
				client.setName(rs.getString("name"));
				client.setPhoneNumber(rs.getString("phonenumber"));
				client.setEmail(rs.getString("email"));
			}
			
			rs.close();
			preparedStatement.close();
			
			return client;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			if (connection != null) {
				try {
					connection.close();
				}catch (Exception ignore) {}
			}
		}
	}
	
	public void saveClient(Client client) throws SQLException {
		Connection connection = null;
		try {
			connection = DbConnection.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SAVE_CLIENT);
			
			preparedStatement.setString(1, client.getName());
			preparedStatement.setString(2, client.getEmail());
			preparedStatement.setString(3, client.getPhoneNumber());
			preparedStatement.execute();
			
			Statement statement = DbConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery(GET_ID_CLIENT);
			int id = -1;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			client.setId(id);	
			
			rs.close();
			statement.close();
			preparedStatement.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException(e);
		}finally {
			if (connection != null) {
				try {
					connection.close();
				}catch (Exception ignore) {}
			}
		}		
	}
	
}
