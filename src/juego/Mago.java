package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Mago 
{
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;
	private int direccion;
	private int disparos;
	private int vidas;
	private int puntos;
	private double velocidad;
	private boolean inmunidad;
	Image img1;
	Image img2;
	Image img3;
	Image img4;
	Image img5;
	Image img6;
	
	public Mago ()
	{
		this.x=400;
		this.y=65;
		this.ancho=35;
		this.alto=60;
		this.angulo=0;
		this.direccion=1;		// 1 es derecha, -1 es izquierda
		this.disparos=1;
		this.vidas=3;
		this.puntos=0;
		this.velocidad=2;
		this.inmunidad=false;
		img1 = Herramientas.cargarImagen("drFateDerecha.gif");
		img2 = Herramientas.cargarImagen("drFateIzquierda.gif");
		img3 = Herramientas.cargarImagen("drFateDerecha.gif");
		img4 = Herramientas.cargarImagen("InmunidadDrFateIzq.gif");
		img5 = Herramientas.cargarImagen("InmunidadDrFateDer.gif");
		img6 = Herramientas.cargarImagen("InmunidadDrFateIzq.gif");
	}
	
	public void dibujar(Entorno entorno)
	{
		entorno.dibujarRectangulo(x, y, ancho, alto, angulo, Color.blue);
	}
	
	public void dibujarse(Entorno entorno)
	{
		if (this.disparos==1 && this.inmunidad)
			entorno.dibujarImagen(img6, this.x, this.y-18, this.angulo, 0.65);
		
		else if (this.direccion==1 && this.inmunidad)
			entorno.dibujarImagen(img5, this.x, this.y-18, this.angulo, 0.61);
		
		else if(this.direccion==-1 && this.inmunidad)
			entorno.dibujarImagen(img4, this.x, this.y-18, this.angulo, 0.65);
			
		else if(this.disparos==1)	
			entorno.dibujarImagen(img3, this.x, this.y-18, this.angulo, 0.65);
		
		else if (this.direccion==1)
			entorno.dibujarImagen(img1, this.x, this.y-18, this.angulo, 0.61);
		
		else if (this.direccion==-1)
			entorno.dibujarImagen(img2, this.x, this.y-18, this.angulo, 0.63);
	}
	
	public void moverse(Pared paredDer, Pared paredIzq)
	{
		
		if (( this.x + (this.ancho / 2) ) <= paredDer.getX() - (paredDer.getAncho() / 2 ) && this.direccion == 1 )
		
			this.x += this.velocidad;
		
		else if (( this.x - this.ancho / 2 ) >= paredIzq.getX() + (paredIzq.getAncho() / 2 ) && this.direccion == -1)
			
			this.x -= this.velocidad;
		
	}
	
	public void caer(Viga [] viga)
	{
		if (toca(viga[0])==false && toca(viga[1])==false && toca(viga[2])==false && toca(viga[3])==false && 
				toca(viga[4])==false && toca(viga[5])==false && toca(viga[6])==false && toca(viga[7])==false)													//if ( this.x- this.ancho/2 != viga.getX()+ viga.getAncho()/2 )
		{	
			this.y += 3;
			this.disparos=1;
		}
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
	
	public boolean toca(Pared pared) 
	{
		double distanciaX= Math.abs( pared.getX()  -  this.x );	//Da la distancia en X entre la viga y el mago
		
		double distanciaY= Math.abs( pared.getY()  - this.y );	//Da la distancia en Y entre la viga y el mago
		
		if(distanciaX <= pared.getAncho()/2 + this.ancho /2 && distanciaY <= pared.getAlto()/2 + this.alto/2)
			
			return true;
		
		else
	
			return false;
	}
	
	public boolean toca( double x, double y, double ancho, double alto ) 
	{
		double distanciaX= Math.abs( x  -  this.x );	//Da la distancia en X entre algun objeto y el monstruo
		
		double distanciaY= Math.abs( y  - this.y );	//Da la distancia en Y entre algun objeto y el monstruo
		
		if(distanciaX <= ancho/2 + this.ancho /2 && distanciaY <= alto/2 + this.alto/2)
			
			return true;
		
		else
	
			return false;
	}
	
	public boolean ganaste(int x)
	{
		if( x <= 0)
		{
			return true;
		}
		return false;
	}
	
	public boolean murio()
	{
		if(this.vidas<=0)
		{
			return true;
		}
		return false;
	}
	
	public void sumarPuntos(int p)
	{
		this.puntos+=p;
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

	public int getDisparos() {
		return disparos;
	}

	public void setDisparos(int disparos) {
		this.disparos = disparos;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public boolean isInmunidad() {
		return inmunidad;
	}

	public void setInmunidad(boolean inmunidad) {
		this.inmunidad = inmunidad;
	}
}
