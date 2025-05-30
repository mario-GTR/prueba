package prueba_practica5;

import prueba_practica5.Personaje.Jugador;

public abstract class Personaje {
	
	public enum Jugador {
		A,B
	}
	protected int coste;
	protected int ataque_max;
	protected int defensa_max;
	protected int hp;
	protected int radio;
	protected Jugador jugador;
	
	public Personaje(Jugador jugador) {
		
		this.jugador=jugador;
		
	}
	
	//metodos get y set
	public int getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}

	public int getAtaque_max() {
		return ataque_max;
	}

	public void setAtaque_max(int ataque_max) {
		this.ataque_max = ataque_max;
	}

	public int getDefensa_max() {
		return defensa_max;
	}

	public void setDefensa_max(int defensa_max) {
		this.defensa_max = defensa_max;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	//Saber que jugador es
	
	public boolean JugadorA() {
		if (jugador==Jugador.A) {
			return true;
		}
		return false;
	}
	
	//Método para mover
	public void mover(int filaDestino, int columnaDestino) {
		
	}
	
	
}

