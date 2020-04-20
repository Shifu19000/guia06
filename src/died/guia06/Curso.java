package died.guia06;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import died.guia06.util.Registro;

/**
 * Clase que representa un curso. Un curso se identifica por su ID y por su nombre y ciclo lectivo.
 * Un curso guarda una lista de los inscriptos actuales que tienen.
 * Un curso, al aprobarlo, otorga una cantidad de creditos definidas en el curso.
 * Un curso requiere que para inscribirnos tengamos al menos la cantidad de creditos requeridas, y que haya cupo disponible
 * @author marti
 *
 */
public class Curso {

	private Integer id;
	private String nombre;
	private Integer cicloLectivo;
	private Integer cupo; 
	private List<Alumno> inscriptos;
	private Integer creditos;
	private Integer creditosRequeridos;
	
	private Registro log;
	
	public Curso() {
		super();
		this.inscriptos = new ArrayList<Alumno>();
		this.log = new Registro();
	}
	
	public Integer NroCicloLectivo() {
		return this.cicloLectivo;
	}
	
	public void DefinirCreditosSiAprueba(Integer creditoss) {
		this.creditos = creditoss;
	}
	
	public int CreditosSiAprob�() {
		return creditos;
	}
	
	public boolean hayCupo(){
		int aux = inscriptos.size();
		if(aux < this.cupo) {
			return true;
		}
		return false;
	}
	
	public boolean cursosDelCicloLectivo(Alumno a) {
		List<Curso> cursos = a.cursosSegunCL(this.cicloLectivo);
		
		if(cursos.equals(null)) {
			return false;
		}
		return true;
	}
	/**
	 * Este método, verifica si el alumno se puede inscribir y si es así lo agrega al curso,
	 * agrega el curso a la lista de cursos en los que está inscripto el alumno y retorna verdadero.
	 * Caso contrario retorna falso y no agrega el alumno a la lista de inscriptos ni el curso a la lista
	 * de cursos en los que el alumno está inscripto.
	 * 
	 * Para poder inscribirse un alumno debe
	 * 		a) tener como minimo los creditos necesarios
	 *      b) tener cupo disponibles
	 *      c) puede estar inscripto en simultáneo a no más de 3 cursos del mismo ciclo lectivo.
	 * @param a
	 * @return
	 */
	public Boolean inscribir(Alumno a) throws IOException {
		Integer aux = a.creditosObtenidos(); 
		if(aux >= this.creditosRequeridos){
			if(this.hayCupo()) {
				if(cursosDelCicloLectivo(a)) {
					a.inscribirCurso(this);//Pone el curso en la lista del Alumno//
					this.inscriptos.add(a);//Pone al Alumno en la lista de inscriptos//
					try {
						log.registrar(this, "inscribir ",a.toString());	
					}catch(IOException e) {
						JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
					}
				}
			}
		}
		return false;
	}
	
	
	/**
	 * imprime los inscriptos en orden alfabetico
	 */
	public void imprimirInscriptos() throws IOException {
		
		int opcion;
		Scanner usuario = new Scanner(System.in);
		System.out.println("Sellecione como desea imprimir la lista \n"+
				"1. Alfab�ticamente \n" + "2. Por Libreta \n" + "3. Por Cr�ditos");
		
		opcion = usuario.nextInt();
		
		switch(opcion) {
		case 1:{
			this.imprimirAlumnosAlf();
			break;
		}
		case 2:{
			this.imprimirAlumnosLib();
			break;
		}
		case 3:{
			this.imprimirAlumnosCre();
			break;
		}
		}

		try {
			log.registrar(this, "imprimir listado",this.inscriptos.size()+ " registros ");
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
		}
		
	}
	
	public void imprimirAlumnosAlf() {
		
		List<Alumno> listadoAux = this.inscriptos;
		String nombreAImprimir;
		Alumno alumno = new Alumno();
		
		while(listadoAux.size() != 0) {
			alumno = alumnoAImprimir(listadoAux);
			nombreAImprimir = alumno.nombreAlumno();
			System.out.println(nombreAImprimir);
			listadoAux.remove(alumno);
			
		}
		
	}
	
	public Alumno alumnoAImprimir(List<Alumno> lista) {
		Alumno alumno = new Alumno();
		String nombre = "zzz";
		for(Alumno alumnoAux: lista) {
			if(alumnoAux.nombreAlumno().compareTo(nombre) < 0) {
				nombre = alumnoAux.nombreAlumno();
				alumno = alumnoAux;
			}
		}
		return alumno;
	}
	
	public void imprimirAlumnosLib() {
		List<Alumno> listadoAux = this.inscriptos;
		Integer libretaAImprimir;
		Alumno alumno = new Alumno();
		
		while(listadoAux.size() != 0) {
			alumno = LibAImprimir(listadoAux);
			libretaAImprimir = alumno.libretaAlumno();
			System.out.println(libretaAImprimir);
			listadoAux.remove(alumno);
	}
	}
	
	public Alumno LibAImprimir(List<Alumno> lista) {
		Alumno alumno = new Alumno();
		Integer libreta = 999999999;
		for(Alumno alumnoAux: lista) {
			if(alumnoAux.libretaAlumno() < libreta) {
				libreta = alumnoAux.libretaAlumno();
				alumno = alumnoAux;
			}
		}
		return alumno;
	}
	
	public void imprimirAlumnosCre() {
		List<Alumno> listadoAux = this.inscriptos;
		Integer creditoAImprimir;
		Alumno alumno = new Alumno();
		
		while(listadoAux.size() != 0) {
			alumno = creAImprimir(listadoAux);
			creditoAImprimir = alumno.libretaAlumno();
			System.out.println(creditoAImprimir);
			listadoAux.remove(alumno);
	}
	}
	
	public Alumno creAImprimir(List<Alumno> lista) {
		Alumno alumno = new Alumno();
		Integer credito = 999999999;
		for(Alumno alumnoAux: lista) {
			if(alumnoAux.creditosObtenidos() < credito) {
				credito = alumnoAux.creditosObtenidos();
				alumno = alumnoAux;
			}
		}
		return alumno;
	}
	


}
