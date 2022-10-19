package org.example;

import org.example.presentation.Presentation;

/**
 * Desde el main llamo a la clase Presentacion, donde alojo métodos para
 * visualizar datos
 *
 * @author Cristian González Hens
 */
public class App {
	private static Presentation presentation;

	public static void main(String[] args) {
		boolean salida = true; // VARIABLE PARA CONTROLARÁ LA REPETICIÓN DEL MENÚ

		/*
		 * PRIMERO SE LANZÁ EL MENÚ, COMO MÍNIMO DEBE DE HACERLO UNA VEZ, LUEGO EL VALOR
		 * DEL BOLEANO QUE CONTROLARÁ LA SALIDA DEL MENÚ SE ASIGNA A LA OPCIÓN ELEGIDA
		 * EN ESTE CASO, SI PULSA 5 SE SALDRÁ DEL BUCLE.
		 */

		do {
			presentation.Menu(); // LLAMAMOS AL MÉTODO MENÚ, TAN SOLO SACA EL MENSAJE CON LAS OPCIONES DEL MENÚ
			int opcion = presentation.inInt();
			salida = presentation.opcionMenu(opcion); // AQUÍ CAMBIARÁ EL BOLEANO Y CON ELLO TERMINARÁ EL PROGRAMA SI
														// PULSAMOS 0.
		} while (salida);

	}
}
