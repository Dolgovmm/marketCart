package ru.dolgov.market.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Test;

import ru.dolgov.market.domain.Client;
import ru.dolgov.market.jdbc.DbConnection;

public class TestClientDAO {

	@Test
	public void testGetClientByEmail(){
		
		try {
			DbConnection.getConnection().setAutoCommit(false);
			
			Client client = new Client();
			client.setName("some name");
			client.setPhoneNumber("123456789");
			client.setEmail("someAddress@mail.ru");
			
			PreparedStatement preparedStatement = DbConnection.getConnection()
					.prepareStatement("insert into Clients (name, email, phonenumber) values (?, ?, ?)");
			preparedStatement.setString(1, client.getName());
			preparedStatement.setString(2, client.getEmail());
			preparedStatement.setString(3, client.getPhoneNumber());
			
			Statement statement = DbConnection.getConnection().createStatement();
			ResultSet rs = statement.executeQuery("select @@IDENTITY");
			int id = -1;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			client.setId(id);
			
			ClientDAOImpl dao = new ClientDAOImpl();
			
			Client clientFromDb = dao.getClientByEmail(client.getEmail());
			
			DbConnection.getConnection().rollback();
			DbConnection.getConnection().setAutoCommit(true);
			
			Assert.assertEquals(client, clientFromDb);	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testSaveClient() {
		
		try {
		
		DbConnection.getConnection().setAutoCommit(false);
		
		Client client = new Client();
		client.setName("some name");
		client.setPhoneNumber("123456789");
		client.setEmail("someAddress@mail.ru");
		
		ClientDAOImpl dao = new ClientDAOImpl();
		
		dao.saveClient(client);
		
		Statement statement = DbConnection.getConnection().createStatement();
		ResultSet rs = statement.executeQuery("select * from Clients where id = " + client.getId() + ";");
		
		Client clientFromDb = new Client();
		
		if (rs.next()) {
			clientFromDb.setId(rs.getInt("id"));
			clientFromDb.setName(rs.getString("name"));
			clientFromDb.setEmail(rs.getString("email"));
			clientFromDb.setPhoneNumber(rs.getString("phonenumber"));
		}
		
		DbConnection.getConnection().rollback();
		DbConnection.getConnection().setAutoCommit(true);
		
		Assert.assertEquals(client, clientFromDb);	
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	}
}
