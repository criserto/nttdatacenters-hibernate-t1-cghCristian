package org.example.presentation;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.example.constans.clientConstants;
import org.example.persistence.Client;
import org.example.service.clientDao;
import org.example.service.impl.clientDaoImpl;

public class Presentation {

	private static clientDao serviceClient = new clientDaoImpl();
	private static Client client;
	private static clientConstants constants;

	// MÉTODO PARA MOSTRAR MENU, SOLO MUESTRA EL MENSAJE DEL MENU
	public static void Menu() {
		System.out.println(constants.SC_MENU);
	}

	// MÉTODO PARA CONTROLAR LA OPCIÓN ELEGIDA
	public static boolean opcionMenu(int opcion) {

		switch (opcion) {

		case 1: // NUEVO CLIENTE
			addClient();
			break;
		case 2: // CONSULTAR TODOS LOS CIENTES
			getAllClient();
			break;

		case 3: // CONSULTAR POR ID
			getClientById();
			break;
		case 4: // CONSULTAR POR NOMBRE Y APELLIDOS
			getClientByNameAndSubnames();
			break;
		case 5: // ACTUALIZAR CLIENTE
			updateClient();
			break;
		case 6: // ELIMINAR POR ID
			deleteClientById();
			break;
		case 7: // ELIMINAR TODOS
			deleteAll();
			break;

		case 0: // SALIR
			System.out.println(constants.SC_DESPEDIDA); // SOLO SACA EL MENSAJE DE DESPEDIDA
			return false; // SI SALES EL FALSE SE ASIGNA AL BOLEANO QUE CONTROLA LA REPETICIÓN DEL MENÚ

		default:
			System.err.println(constants.SC_OPCION_INCORRECTA);
			break;
		}
		return true; // SIEMPRE QUE NO ELIJAS 0 ESTARÁ REPITIENDO EL MENÚ
	}

	// MÉTODO PARA SACAR MENSAJE
	public static void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}

	// MÉTODO PARA SACAR MENSAJE ERRONEO
	public static void mostrarMensajeErr(String mensaje) {
		System.err.println(mensaje);
	}

	// MÉTODO PARA SACAR MENSAJE Y LLAMADA AL MÉTODO DE RECOGIDA DE TEXTO
	public static String entradaTexto(String mensaje) {
		System.out.println(mensaje);
		return inLine();
	}

	// MÉTODO RECOGIDA DE TEXTO
	public static String inLine() {
		return new Scanner(System.in).nextLine();
	}

	// MÉTODO PARA SACAR MENSAJE Y LLAMADA AL MÉTODO DE RECOGIDA DE NÚMERO
	public static int entradaInt(String mensaje) {
		System.out.println(mensaje);
		return inInt();
	}

	// MÉTODO RECOGIDA DE NÚMERO
	public static int inInt() {
		try {
			return new Scanner(System.in).nextInt();
		} catch (InputMismatchException e) {
			System.out.println(constants.SC_NUM_INVALIDO);
			return inInt();
		}
	}

	/**
	 * MÉTODO AGREGAR CLIENTE RECOGIENDO PARÁMETROS SOLICITADOS
	 */
	public static void addClient() {

		String nombreCliente = entradaTexto(constants.SC_NOMBRE_CLIENTE);
		String apellido1 = entradaTexto(constants.SC_APELLIDO1_CLIENTE);
		String apellido2 = entradaTexto(constants.SC_APELLIDO2_CLIENTE);
		String dni = null;

		do {
			dni = entradaTexto(constants.SC_DNI_CLIENTE);
		} while (dni.length() != 9);

		client = new Client();

		client.setNombre(nombreCliente);
		client.setApellido1(apellido1);
		client.setApellido2(apellido2);
		client.setDni(dni);

		serviceClient.save(client);

	}

	/**
	 * MÉTODO OBTENER LISTA DE CLIENTES
	 */
	public static void getAllClient() {

		List<Client> myListClient = serviceClient.findAll();

		if (myListClient.size() > 0) {
			myListClient.stream().forEach(System.out::println);
		} else {
			mostrarMensajeErr(constants.SC_NOT_FOUND_CLIENT);
		}
	}

	/**
	 * MÉTODO OBTENER CLIENTE POR ID
	 */
	public static void getClientById() {

		Integer idClient = entradaInt(constants.SC_ID_CLIENTE);

		client = serviceClient.findById(idClient);

		if (client != null) {
			mostrarMensaje(client.toString());
		} else {
			mostrarMensajeErr(constants.SC_FAILED_FIND_CLIENT);
		}

	}

	/**
	 * MÉTODO OBTENER CLIENTE POR NOMBRE Y APELLIDOS, SOLO DEVUELVE 1
	 */
	public static void getClientByNameAndSubnames() {

		String nombreCliente = entradaTexto(constants.SC_NOMBRE_CLIENTE);
		String apellido1 = entradaTexto(constants.SC_APELLIDO1_CLIENTE);
		String apellido2 = entradaTexto(constants.SC_APELLIDO2_CLIENTE);

		client = serviceClient.findByName_Apellido1_Apellido2(nombreCliente, apellido1, apellido2);

		if (client != null) {
			mostrarMensaje(client.toString());
		} else {
			mostrarMensajeErr(constants.SC_FAILED_FIND_CLIENT);
		}

	}

	/**
	 * MÉTODO ACTUALIZAR CLIENTE POR PARÁMETROS PÁSADOS, SE COMPRUEBA SI EL CLIENTE
	 * EXISTE ANTES DE SOLICITAR.
	 */
	public static void updateClient() {

		Integer idCliente = entradaInt(constants.SC_ID_CLIENTE);
		String nombreCliente = entradaTexto(constants.SC_NOMBRE_CLIENTE);
		String apellido1 = entradaTexto(constants.SC_APELLIDO1_CLIENTE);
		String apellido2 = entradaTexto(constants.SC_APELLIDO2_CLIENTE);
		String dni = null;
		do {
			dni = entradaTexto(constants.SC_DNI_CLIENTE);
		} while (dni.length() != 9);

		if (serviceClient.findById(idCliente) == null) {
			mostrarMensajeErr(constants.SC_FAILED_FIND_CLIENT);
		} else {
			client = new Client();

			client.setId(idCliente);
			client.setNombre(nombreCliente);
			client.setApellido1(apellido1);
			client.setApellido2(apellido2);
			client.setDni(dni);

			serviceClient.updateEntity(client);
		}

	}

	/**
	 * MÉTODO ELIMINAR POR ID
	 */
	public static void deleteClientById() {

		Integer id = entradaInt(constants.SC_ID_CLIENTE);

		serviceClient.delecteById(id);

	}

	/**
	 * MÉTODO ELIMINAR TODOS
	 */
	public static void deleteAll() {

		serviceClient.delecteAll();

	}

}
