package prueba_practica5;

import prueba_practica5.Personaje.Jugador;

public class Caballero extends Personaje{
	
	
	public Caballero( Jugador jugador) {
		super(jugador);
		super.coste = 25;
		super.ataque_max= 15;
		super.defensa_max = 15;
		super.hp = 20;
		super.radio= 2;
	}
	@Override
	public boolean JugadorA() {
		if (jugador==Jugador.A) {
			return true;
		}
		return false;
	}
	public String toString() {
		return "C(20)";
	}
	
	
}