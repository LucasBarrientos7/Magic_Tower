package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import entorno.Entorno;
import entorno.Herramientas;
public class Items 
{
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;	
	private int numero;
	Image img1;
	Image img2;
	
	public Items(double x, double y, double ancho, double alto, double angulo)
	{
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.angulo=angulo;
		
		Random rand = new Random ();
		this.numero= rand.nextInt(3); 
		// Si es =0 es vida.
		// Si es =1 es una trampa de fuego.
		// Si es =2 es un disparo especial.

		img1 = Herramientas.cargarImagen("corazon.gif");
		img2 = Herramientas.cargarImagen("cofre.gif");
	}
	
	public void dibujar(Entorno entorno)
	{
		if (this.numero==0)
			entorno.dibujarRectangulo(x, y, ancho, alto, angulo, Color.green);
			
		else if (this.numero==1)
			entorno.dibujarRectangulo(x, y, ancho, alto, angulo, Color.red);
		
		else 
			entorno.dibujarRectangulo(x, y, ancho, alto, angulo, Color.white);	
	}
	
	public void dibujarse(Entorno entorno)
	{
		if (this.numero==0)
			entorno.dibujarImagen(img1, x, y, angulo, 0.20);
		else if (this.numero==4)
			entorno.dibujarImagen(img1, x, y, angulo, 0.20);
		
		else if (this.numero==1)
			entorno.dibujarImagen(img2, x, y, angulo, 0.35);
		
		else 
			entorno.dibujarImagen(img2, x, y, angulo, 0.35);
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

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}	
}
