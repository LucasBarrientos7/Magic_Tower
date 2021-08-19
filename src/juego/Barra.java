package juego;

import java.awt.Color;
import java.awt.Paint;
import entorno.Entorno;
public class Barra 
{
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;
	
	public Barra(double x, double y, double ancho, double alto, double angulo, Paint tranparent)
	{
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.angulo=angulo;
	}
	
	public void dibujarse(Entorno entorno)
	{
		entorno.dibujarRectangulo(x, y, ancho, alto, angulo, Color.MAGENTA);
	}

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
}
