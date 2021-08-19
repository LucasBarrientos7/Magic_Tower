package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Hechizo 
{
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;
	private int direccion;
	Image img1;
	Image img2;
	
	public Hechizo(double x, double y, double ancho, double alto, double angulo)
	{
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.angulo=angulo;
		this.direccion=1;
		img1= Herramientas.cargarImagen("hechizo.png");
		img2= Herramientas.cargarImagen("hechizo2.png");
	}
	
	public void Dibujar(Entorno entorno)
	{
		entorno.dibujarRectangulo(x, y, ancho, alto, angulo, Color.WHITE);
	}
	
	public void Dibujarse(Entorno entorno)
	{
		if (this.direccion==1)
			entorno.dibujarImagen(img1, this.x+40, this.y-35, this.angulo, 0.05);
		else	
			entorno.dibujarImagen(img2, this.x-40, this.y-35, this.angulo, 0.05);
	}
	
	
	public void moverDerecha()
	{
		this.x+=4;
	}
	public void moverIzquierda()
	{
		this.x-=4;
	}
	
	public boolean toca(Pared pared) 
	{
		double distanciaX= Math.abs( pared.getX()  -  this.x );	//Da la distancia en X entre la pared y el hechizo
		
		double distanciaY= Math.abs( pared.getY()  - this.y );	//Da la distancia en Y entre la pared y el hechizo
		
		if(distanciaX < pared.getAncho()/2 + this.ancho /2 && distanciaY <= pared.getAlto()/2 + this.alto/2)
			
			return true;
		
		else
	
			return false;
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

	public int getDireccion() {
		return direccion;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

}
