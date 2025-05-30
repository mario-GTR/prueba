package prueba_practica5;

import java.util.Scanner;

import prueba_practica5.Personaje.Jugador;

public class prueba {
	
	static	final String ANSI_RED = "\u001B[31m";

	static	final String ANSI_BLUE = "\u001B[34m";

	static final String ANSI_RESET = "\u001B[0m";

		public static void main(String[] args) {
			Scanner sc = new Scanner (System.in);
			
			//JUGADOR A
			Personaje posicion[][]=new Personaje[8][8];
			System.out.println("Creación de Ejército - JUGADOR A");
			System.out.println("================================");
			System.out.println("Indica tus personajes - Coste máximo: 50");
			System.out.println(" ");
			System.out.println("Caballero[Coste: 25] (C)");
			System.out.println("Soldado[Coste: 10] (S)");
			System.out.println("Lancero[Coste: 5] (L)");
			System.out.println("Arquero[Coste: 15] (A)");
			System.out.println("Salir (X)");
			System.out.println(" ");

			char personaje=0;
			int coste=0;
			do {
				System.out.println("Coste total: "+coste);
				System.out.print("Indica tipo de personaje: ");
				personaje=sc.next().charAt(0);
				System.out.println("Indica posicion del personaje: ");
				System.out.print("Columna: ");
				int columna=sc.nextInt();
				System.out.print("Fila: ");
				int fila=sc.nextInt();
				
				if(rangoA(fila, columna)==false) {
				System.out.println("Posicion de la columna(0 o 1) o fila no valida(del 0 al 7)");
				}else {
					if(estaVacia(posicion,fila,columna)==false) {
					System.out.println("Ya pusiste tu personaje en esta posicion");
					}else {
						switch(personaje) {
						case 'C':
							if(coste+25>50) {
								System.out.println("No tienes suficiente coste!");
								break;
							}
							coste+=25;
							Caballero c1=new Caballero(Jugador.A);
							posicion[fila][columna]=c1;
							break;
						case 'S':
							if(coste+10>50) {
								System.out.println("No tienes suficiente coste!");
								break;
							}
							coste+=10;
							Soldado s1=new Soldado(Jugador.A);
							posicion[fila][columna]=s1;
							break;
						case 'L':
							if(coste+5>50) {
								System.out.println("No tienes suficiente coste!");
								break;
							}
							coste+=5;
							Lancero l1=new Lancero(Jugador.A);
							posicion[fila][columna]=l1;
							break;
						case 'A':
							if(coste+15>50) {
								System.out.println("No tienes suficiente coste!");
								break;
							}
							coste+=15;
							Arquero a1=new Arquero(Jugador.A);
							posicion[fila][columna]=a1;
							break;
						case 'X':
							coste=50;
							break;
						default:
							System.out.println("Personaje no valido");
						}
					}
				}
				System.out.println(" ");
			}while(coste<50 || personaje=='X');
			System.out.println(" ");
				
			//JUGADOR B
			System.out.println("Creación de Ejército - JUGADOR B");
			System.out.println("================================");
			System.out.println("Indica tus personajes - Coste máximo: 50");
			System.out.println(" ");
			System.out.println("Caballero[Coste: 25] (C)");
			System.out.println("Soldado[Coste: 10] (S)");
			System.out.println("Lancero[Coste: 5] (L)");
			System.out.println("Arquero[Coste: 15] (A)");
			System.out.println("Salir (X)");
			System.out.println(" ");

			personaje=0;
			coste=0;
			do {
				System.out.println("Coste total: "+coste);
				System.out.print("Indica tipo de personaje: ");
				personaje=sc.next().charAt(0);
				System.out.println("Indica posicion del personaje: ");
				System.out.print("Columna: ");
				int columna=sc.nextInt();
				System.out.print("Fila: ");
				int fila=sc.nextInt();
				
				if(rangoB(fila, columna)==false) {
				System.out.println("Posicion de la columna(6 o 7) o fila no valida(del 0 al 7)");
				}else {
					if(estaVacia(posicion,fila,columna)==false) {
					System.out.println("Ya pusiste tu personaje en esta posicion");
					}else {
						switch(personaje) {
						case 'C':
							if(coste+25>50) {
								System.out.println("No tienes suficiente coste!");
								break;
							}
							coste+=25;
							Caballero c1=new Caballero(Jugador.B);
							posicion[fila][columna]=c1;
							break;
						case 'S':
							if(coste+10>50) {
								System.out.println("No tienes suficiente coste!");
								break;
							}
							coste+=10;
							Soldado s1=new Soldado(Jugador.B);
							posicion[fila][columna]=s1;
							break;
						case 'L':
							if(coste+5>50) {
								System.out.println("No tienes suficiente coste!");
								break;
							}
							coste+=5;
							Lancero l1=new Lancero(Jugador.B);
							posicion[fila][columna]=l1;
							break;
						case 'A':
							if(coste+15>50) {
								System.out.println("No tienes suficiente coste!");
								break;
							}
							coste+=15;
							Arquero a1=new Arquero(Jugador.B);
							posicion[fila][columna]=a1;
							break;
						case 'X':
							coste=50;
							break;
						default:
							System.out.println("Personaje no valido");
						}
					}
				}
				System.out.println(" ");
			}while(coste<50 || personaje=='X');
			System.out.println(" ");
			
			//sacamos tablero
			tablero(posicion);
			
			
			int lista[]=new int[2];
			lista[0]=0;
			lista[1]=0;
			
			do {
				turnoA(posicion);
				//sacamos tablero
				tablero(posicion);
				totalpersonajes(posicion,lista);
				turnoB(posicion);
				//sacamos tablero
				tablero(posicion);
				totalpersonajes(posicion,lista);
			}while(lista[0]!=0 || lista[1]!=0);
	
		}
		public static void mover(Personaje posicion[][],int fila1,int columna1,int fila2,int columna2) {
			if (fila1<0 && fila1>7) {
				System.out.println("error en la fila");
				
			}
			if (columna1<0 && columna1>7) {
				System.out.println("error en la columna");
				
			}
			if (fila1+1==fila2 && columna1==columna2) {
				
			}
			
			
		}
		
		
		public static void turnoA(Personaje posicion[][]) {
			Scanner sc = new Scanner (System.in);
			//TURNO JUGADOR A
			System.out.println("Turno de JUGADOR_A");
			System.out.println("================================");
			System.out.println("Mover tropa: (M)");
			System.out.println("Atacar: (A)");
			System.out.println("Curar: (S)");
			System.out.println("Elige opcion: ");
			char opcion=sc.next().charAt(0);
			
			switch(opcion){
			case 'M':
				System.out.println("Indica posicion del personaje");
				System.out.println("Fila: ");
				int fila1=sc.nextInt();
				System.out.println("Columna: ");
				int columna1=sc.nextInt();
				System.out.println("Indica posicion destino del personaje");
				System.out.println("Fila: ");
				int fila2=sc.nextInt();
				System.out.println("Columna: ");
				int columna2=sc.nextInt();
				System.out.println(" ");
				if(estaVacia(posicion,fila1,columna1)==false && estaVacia(posicion,fila2,columna2)) {
					posicion[fila1][columna1].mover(posicion,fila1,columna1,fila2,columna2);
				}else {
					System.out.println("no elegiste un personaje correcto o el destino ya esta ocupado");
				}
				break;
			case 'A':
				break;
			case 'S':
				break;
			}
			
			
		}
		
		public static void turnoB(Personaje posicion[][]) {
			//TURNO JUGADOR B
			System.out.println("Turno de JUGADOR_B");
			System.out.println("================================");
			System.out.println("Mover tropa: (M)");
			System.out.println("Atacar: (A)");
			System.out.println("Curar: (S)");
			System.out.println("Elige opcion: ");
			System.out.println(" ");
			
		}
		public static int[] totalpersonajes(Personaje posicion[][], int lista[]) {
			int cont1=lista[0];
			int cont2=lista[1];
			
			for(int i=0;i<8;i++) {
				for(int x=0;x<8;x++) {
					if(posicion[i][x]==null){
						
					}else if(posicion[i][x].JugadorA()){
						cont1++;
					}else {
						cont2++;
					}
				}
			}
			System.out.println(" ");
			System.out.println("Jugador A: "+cont1+" personajes en batalla");
			System.out.println("Jugador B: "+cont2+" personajes en batalla");
			System.out.println(" ");
			return lista;
		}
		
		public static void tablero(Personaje posicion[][]) {
			System.out.println("    "+" 0	   1     2     3     4     5     6     7");
			for(int i=0;i<8;i++) {
				System.out.print(i+" |");
				for(int x=0;x<8;x++) {
					if(posicion[i][x]==null){
						System.out.print("     |");
					}else if(posicion[i][x].JugadorA()){
						System.out.print(ANSI_RED+posicion[i][x]+ANSI_RESET+"|");
					}else {
						System.out.print(ANSI_BLUE+posicion[i][x]+ANSI_RESET+"|");
					}
				}
				System.out.println(" ");
			}
		}
		public static boolean rangoA(int filas, int columna) {
			if(columna==1 || columna==0) {
				if(filas>=0 && filas<=8) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}
		public static boolean rangoB(int filas, int columna) {
			if(columna==6 || columna==7) {
				if(filas>=0 && filas<=8) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		}
		public static boolean estaVacia(Personaje posicion[][],int fila, int columna) {
			if(posicion[fila][columna]==null) {
				return true;
			}else {
				return false;
			}
		}
	}
