package org.example.service.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.example.persistence.Client;
import org.example.service.clientDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class clientDaoImpl implements clientDao {

	private static SessionFactory sessionFa;
	private static Session s;

	/**
	 * Construir la SessionFactory.
	 *
	 * @return
	 */
	private static SessionFactory buildSession() {

		sessionFa = new Configuration().configure().buildSessionFactory();
		return sessionFa;
	}

	/**
	 * Devuelve un booleano si el DNI existe.
	 *
	 * @param dni
	 * @return
	 */
	private static Boolean existDNI(String dni) {

		s = buildSession().openSession();
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<Client> criteriaQuery = builder.createQuery(Client.class);
		Root<Client> root = criteriaQuery.from(Client.class);

		Client client = null;

		try {
			criteriaQuery.select(root);
			criteriaQuery.where(builder.equal(root.get("dni"), dni));

			client = s.createQuery(criteriaQuery).getSingleResult();

			s.close();

		} catch (Exception e) {
			e.getStackTrace();
		}
		return client != null;
	}

	/**
	 * Si se cumple la condición, el cliente se guarda, si no... No hace nada.
	 */
	@Override
	public void save(Client t) {

		if (!existDNI(t.getDni())) {
			s = buildSession().openSession();
			s.beginTransaction();
			s.save(t);
			s.getTransaction().commit();
			s.close();

		}

	}

	/**
	 * Devuelve una lista de los clientes encontrados.
	 */
	@Override
	public List<Client> findAll() {

		s = buildSession().openSession();

		CriteriaBuilder builder = s.getCriteriaBuilder();

		CriteriaQuery<Client> criteriaQuery = builder.createQuery(Client.class);

		criteriaQuery.from(Client.class);

		List<Client> clientList = s.createQuery(criteriaQuery).getResultList();

		s.close();

		return clientList;

	}

	/**
	 * Localiza un cliente por ID.
	 */
	@Override
	public Client findById(Integer id) {
		s = buildSession().openSession();
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<Client> criteriaQuery = builder.createQuery(Client.class);
		Root<Client> root = criteriaQuery.from(Client.class);

		try {
			criteriaQuery.select(root);
			criteriaQuery.where(builder.equal(root.get("id"), id));

			Client client = s.createQuery(criteriaQuery).getSingleResult();

			s.close();
			return client;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Localiza un cliente por nombre, apellido1, apellido2
	 */
	@Override
	public Client findByName_Apellido1_Apellido2(String nombre, String apellido1, String apellido2) {

		s = buildSession().openSession();
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<Client> criteriaQuery = builder.createQuery(Client.class);
		Root<Client> root = criteriaQuery.from(Client.class);

		try {

			// PREDICATE PARA CUMPLIR CON LAS RESTRICCIONES
			Predicate userPredicate = builder.and(builder.equal(root.get("nombre"), nombre),
					builder.equal(root.get("apellido1"), apellido1), builder.equal(root.get("apellido2"), apellido2));

			criteriaQuery.where(userPredicate);

			Client client = s.createQuery(criteriaQuery).getSingleResult();

			s.close();
			return client;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Actualiza un cliente que se recibe por parámetros, si el ID del cliente no
	 * existe no hace nada.
	 */
	@Override
	public void updateEntity(Client object) {

		s = buildSession().openSession();
		s.beginTransaction();

		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaUpdate<Client> criteriaQuery = builder.createCriteriaUpdate(Client.class);
		Root<Client> root = criteriaQuery.from(Client.class);

		criteriaQuery.set("nombre", object.getNombre());
		criteriaQuery.set("apellido1", object.getApellido1());
		criteriaQuery.set("apellido2", object.getApellido2());
		criteriaQuery.set("dni", object.getDni());
		criteriaQuery.where(builder.equal(root.get("id"), object.getId()));

		s.createQuery(criteriaQuery).executeUpdate();

		s.getTransaction().commit();

		s.close();

	}

	/**
	 * Elimina por ID
	 */
	@Override
	public void delecteById(Integer id) {

		s = buildSession().openSession();
		s.beginTransaction();

		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaDelete<Client> criteriaQuery = builder.createCriteriaDelete(Client.class);
		Root<Client> root = criteriaQuery.from(Client.class);

		criteriaQuery.where(builder.equal(root.get("id"), id));

		s.createQuery(criteriaQuery).executeUpdate();

		s.getTransaction().commit();

		s.close();

	}

	/**
	 * Elimina todos.
	 */
	@Override
	public void delecteAll() {
		s = buildSession().openSession();
		s.beginTransaction();

		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaDelete<Client> criteriaQuery = builder.createCriteriaDelete(Client.class);

		criteriaQuery.from(Client.class);
		s.createQuery(criteriaQuery).executeUpdate();

		s.getTransaction().commit();

		s.close();
	}

}
