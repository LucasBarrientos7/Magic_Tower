package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class HechizoEspecial 
{
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;
	private int direccion;
	Image img1;
	Image img2;
	Image img3;
	Image img4;
	
	public HechizoEspecial(double x, double y, double ancho, double alto, double angulo)
	{
		this.x=x;
		this.y=y;
		this.ancho=ancho;
		this.alto=alto;
		this.angulo=angulo;
		this.direccion=0;
		img1= Herramientas.cargarImagen("hechizo.png");
		img2= Herramientas.cargarImagen("hechizo2.png");
		img3= Herramientas.cargarImagen("hechizo3.png");
		img4= Herramientas.cargarImagen("hechizo4.png");
		
	}
	
	public void Dibujar(Entorno entorno)
	{
		entorno.dibujarRectangulo(x, y, ancho, alto, angulo, Color.WHITE);
	}
	
	public void Dibujarse(Entorno entorno)
	{
		if (direccion==1)
			entorno.dibujarImagen(img1, this.x, this.y, this.angulo, 0.05);
		else if	(direccion==2)
			entorno.dibujarImagen(img2, this.x, this.y, this.angulo, 0.05);
		else if	(direccion==3)
			entorno.dibujarImagen(img3, this.x, this.y, this.angulo, 0.05);
		else if	(direccion==4)
			entorno.dibujarImagen(img4, this.x, this.y, this.angulo, 0.05);
		else if	(direccion==5)
			entorno.dibujarImagen(img1, this.x, this.y, 225, 0.05);
		else if	(direccion==6)
			entorno.dibujarImagen(img2, this.x, this.y, 45, 0.05);
		else if	(direccion==7)
			entorno.dibujarImagen(img3, this.x, this.y, 135, 0.05);
		else if	(direccion==8)
			entorno.dibujarImagen(img4, this.x, this.y, 45, 0.05);
	}
	
	public void moverDerecha()
	{
		this.direccion=1;
		this.x+=4;
		
	}
	
	public void moverIzquierda()
	{
		this.direccion=2;
		this.x-=4;
		
	}
	
	public void moverArriba()
	{
		this.direccion=3;
		this.y-=4;
	}
	
	public void moverAbajo()
	{
		this.direccion=4;
		this.y+=4;
	}
	
	public void moverDiagonalDerechaArriba()
	{
		this.direccion=5;
		this.x+=4;
		this.y-=4;
	}
	
	public void moverDiagonalIzquierdaArriba()
	{
		this.direccion=6;
		this.x-=4;
		this.y-=4;
	}
	
	public void moverDiagonalDerechaAbajo()
	{
		this.direccion=7;
		this.x+=4;
		this.y+=4;
	}
	
	public void moverDiagonalIzquierdaAbajo()
	{
		this.direccion=8;
		this.x-=4;
		this.y+=4;
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
	
	public boolean toca(Viga viga) 
	{
		double distanciaX= Math.abs( viga.getX()  -  this.x );	//Da la distancia en X entre la viga y el mago
		
		double distanciaY= Math.abs( viga.getY()  - this.y );	//Da la distancia en Y entre la viga y el mago
		
		if(distanciaX <= viga.getAncho()/2 + this.ancho /2 && distanciaY <= viga.getAlto()/2 + this.alto/2)
		
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

	public Image getImg1() {
		return img1;
	}

	public void setImg1(Image img1) {
		this.img1 = img1;
	}

	public Image getImg2() {
		return img2;
	}

	public void setImg2(Image img2) {
		this.img2 = img2;
	}
	
}