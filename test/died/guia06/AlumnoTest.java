package died.guia06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AlumnoTest {
	Alumno a1 = new Alumno();
	Alumno a2 = new Alumno();
	Alumno a3 = new Alumno();
	Curso c1 = new Curso();
	Curso c2 = new Curso();
	
	
	@Test
	void testcompararPorNombre() {
		a1.setearAlumno("Diego Maradona", 1986);
		a2.setearAlumno("Gianfranco Fabbroni", 2000);
		a3.setearAlumno("Gianfranco Fabbroni", 2000);
		assertFalse(a1.compararPorNombre(a2.nombreAlumno()));
		assertTrue(a2.compararPorNombre(a3.nombreAlumno()));
		
	}
	@Test
	void testequals() {
		a1.setearAlumno("Diego Maradona", 1986);
		a2.setearAlumno("Gianfranco Fabbroni", 2000);
		a3.setearAlumno("Gianfranco Fabbroni", 2000);
		assertFalse(a1.equals(a2.libretaAlumno()));
		assertTrue(a2.equals(a3.libretaAlumno()));
		
	}
	@Test
	void testCreditosObtenidos() {
		fail("Not yet implemented");
	}

	@Test
	void testAprobar() {
		fail("Not yet implemented");
	}

	@Test
	void testInscripcionAceptada() {
		fail("Not yet implemented");
	}

}
