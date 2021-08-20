package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Pared {

	// Variables de clase:
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo; 
	Image img1;
	
	// Métodos de clase:

	/**
	 * Constructor de la clase pared.
	 * @param x
	 * @param y
	 * @param ancho
	 * @param alto
	 * @param angulo
	 */
	public Pared (double x, double y, double ancho, double alto, double angulo) {
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.angulo=angulo;
		img1 = Herramientas.cargarImagen("columna.png");
	}
	
	/**
	 * Método para dibujar en pantalla la paredes laterales sin imagen.
	 * @param entorno
	 */
	public void dibujar (Entorno entorno) {
		entorno.dibujarRectangulo(x, y, ancho, alto, angulo, Color.darkGray);
	}
	
	/**
	 * Método para dibujar en pantalla la paredes laterales con imagen.
	 * @param entorno
	 */
	public void dibujarse(Entorno entorno) {
		for (double i=this.y-(this.alto/2)+35;i<= this.y+(this.alto/2); i+=53) 
			entorno.dibujarImagen(img1, this.x+1, i-8, this.angulo, 0.48);
	}

	// Getters and Setters:

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAncho() {
		return ancho;
	}

	public void setAncho(double ancho) {
		this.ancho = ancho;
	}

	public double getAlto() {
		return alto;
	}

	public void setAlto(double alto) {
		this.alto = alto;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setAngulo(double angulo) {
		this.angulo = angulo;
	}
	
} // Cierre total de la clase.
