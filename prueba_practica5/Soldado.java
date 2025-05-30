package prueba_practica5;

import prueba_practica5.Personaje.Jugador;

public class Soldado extends Personaje{

	public Soldado(Jugador jugador) {
		super(jugador);
		super.coste = 10;
		super.ataque_max = 10;
		super.defensa_max = 10;
		super.hp = 10;
		super.radio = 1;
		super.jugador=jugador;
	}
	@Override
	public boolean JugadorA() {
		if (jugador==Jugador.A) {
			return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return "S(10)";
	}
	
}