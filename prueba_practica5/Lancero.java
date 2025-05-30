package prueba_practica5;

import prueba_practica5.Personaje.Jugador;

public class Lancero extends Personaje{
	
	
	public Lancero( Jugador jugador) {
		super(jugador);
		super.coste = 15;
		super.ataque_max = 10;
		super.defensa_max = 5;
		super.hp = 10;
		super.radio = 3;
		super.jugador=jugador;
	}
	@Override
	public boolean JugadorA() {
		if (jugador==Jugador.A) {
			return true;
		}
		return false;
	}
	public String toString() {
		return "L(10)";
	}
	
}