package died.guia06;

import java.util.List;

import javax.swing.JOptionPane;


public class Alumno implements Comparable{

	private String nombre;
	private Integer nroLibreta;
	private List<Curso> cursando;
	private List<Curso> aprobados;

	public int creditosObtenidos() {
		int creditos = 0;
		for(Curso c:aprobados){
			creditos += c.CreditosSiAprobó();
		}
		
		return creditos;
	}

	public void aprobar(Curso c) {
		if(aprobados.contains(c) && !(cursando.contains(c))) {
			JOptionPane.showMessageDialog(null, "Usted aprobó este Curso");	
		}else {
			JOptionPane.showMessageDialog(null, "Usted no aprobó este Curso");
		}
	}

	public void inscripcionAceptada(Curso c) {
		if(cursando.contains(c)) {
			JOptionPane.showMessageDialog(null, "Usted está inscripto a este Curso");	
		}else {
			JOptionPane.showMessageDialog(null, "Usted no está inscripto en este Curso");
		}
		
		
	}
	
	public boolean equals(Integer libreta2) {
		if(libreta2.equals(this.nroLibreta)) {
			JOptionPane.showMessageDialog(null, "Son el mismo alumno");
			return true;
		}
		return false;
	}
	
	@Override
	public boolean CompararPorNombre(String nombre1) {
		if(nombre1.equals(this.nombre)) {
			JOptionPane.showMessageDialog(null, "Son el mismo alumno");
			return true;
		}
		return false;
	}
	
	

}
