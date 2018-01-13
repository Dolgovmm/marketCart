package ru.dolgov.market.dao;

import ru.dolgov.market.domain.Client;

public class TestClientDAO {

	@Test
	public void testGetClientById() {
		ClientDAO dao = new ClientDAO();
		Client client = dao.getClientById(1l);
		Assert.assertNotNull(client);
	}
	
}
