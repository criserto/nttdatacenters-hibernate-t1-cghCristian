package org.example.service;

import java.util.List;

import org.example.persistence.Client;

public interface clientDao {

	void save(Client t);

	public List<Client> findAll();

	public Client findById(Integer id);

	public Client findByName_Apellido1_Apellido2(String nombre, String apellido1, String apellido2);

	void updateEntity(Client object);

	void delecteById(Integer id);

	void delecteAll();

}
