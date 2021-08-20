package juego;

import java.awt.Color;
import java.awt.Paint;
import entorno.Entorno;
public class Barra {

	// Variables de Clase: 
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;
	
	// Métodos:

	/**
	 * Constructor de la clase Barra.
	 * @param x
	 * @param y
	 * @param ancho
	 * @param alto
	 * @param angulo
	 * @param tranparent
	 */
	public Barra(double x, double y, double ancho, double alto, double angulo, Paint tranparent){
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.angulo=angulo;
	}
	
	/**
	 * Método para dibujar en pantalla el objeto Barra.
	 * @param entorno
	 */
	public void dibujarse(Entorno entorno){
		entorno.dibujarRectangulo(x, y, ancho, alto, angulo, Color.MAGENTA);
	}

	// Getters y Setters: 
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
