package juego;

import java.awt.Color;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Monstruo
{
	private double x;
	private double y;
	private double ancho;
	private double alto;
	private double angulo;
	private int direccion;
	int tiempo;
	private double velocidad;
	private boolean congelado;
	private boolean rodando;
	private int tipoMonstruo;
	Image img1;
	Image img2;
	Image img3;
	Image img4;
	
	public Monstruo (double x, double y)
	{
		this.x=x;
		this.y=y;
		this.ancho=30;
		this.alto=70;
		this.angulo=0;
		this.direccion=-1;		// 1 es derecha, -1 es izquierda
		this.tiempo=0;
		this.velocidad=2;
		this.congelado=false;
		this.rodando=false;
		this.tipoMonstruo=1;
		img1 = Herramientas.cargarImagen("ZombieD.gif");
		img2 = Herramientas.cargarImagen("ZombieI.gif");
		img3 = Herramientas.cargarImagen("ghostI.gif");
		img4 = Herramientas.cargarImagen("ghostD.gif");
		
	}
	
	public void dibujar(Entorno entorno)
	{
		entorno.dibujarRectangulo(x, y, ancho, alto, angulo, Color.red);
	}
	
	public void dibujarse(Entorno entorno)
	{
		// Fantasmas:
		if (this.direccion==1 && this.tipoMonstruo==2)
			entorno.dibujarImagen(img4, this.x, this.y+15, this.angulo, 0.20);

		else if (this.direccion==-1 && this.tipoMonstruo==2)
			entorno.dibujarImagen(img3, this.x, this.y+15, this.angulo, 0.20);
		
		// Zombies:
		else if(this.direccion==1)
			entorno.dibujarImagen(img1, this.x, this.y-5, this.angulo, 0.15);
		
		else if (this.direccion==-1)
			entorno.dibujarImagen(img2, this.x, this.y-5, this.angulo, 0.15);
	}
	 
	public void moverse(Pared paredDer, Pared paredIzq)
	{
	//	if (this.tipoMonstruo==2)
	//	this.velocidad=2;
		
		if (( this.x + (this.ancho / 2) ) <= paredDer.getX() - (paredDer.getAncho() / 2 ) && this.direccion == 1 )
		
			this.x += this.velocidad;
		
		else if (( this.x - this.ancho / 2 ) >= paredIzq.getX() + (paredIzq.getAncho() / 2 ) && this.direccion == -1)
			
			this.x -=this.velocidad;
		
	}
	
	public void caer(Viga [] viga)
	{
		if (toca(viga[0])==false && toca(viga[1])==false && toca(viga[2])==false && toca(viga[3])==false && 
				toca(viga[4])==false && toca(viga[5])==false && toca(viga[6])==false && toca(viga[7])==false)													//if ( this.x- this.ancho/2 != viga.getX()+ viga.getAncho()/2 )
		{	
			this.y += 3;
			
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
	
	public boolean toca( Hechizo hechizo ) 
	{
		double distanciaX= Math.abs( hechizo.getX()  -  this.x );	//Da la distancia en X entre la hechizo y el mago
		
		double distanciaY= Math.abs( hechizo.getY()  - this.y );	//Da la distancia en Y entre la hechizo y el mago
		
		if(distanciaX <= (hechizo.getAncho()/2) + (this.ancho /2) && distanciaY <= (hechizo.getAlto()/2) + (this.alto/2))
			
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
	
	public void congelarse()		// Congela al monstruo
	{
		this.velocidad= 0;	
	}
	
	public void rodar()		// Hace rodar al monstruo
	{
		this.angulo+=45;
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

	public double getVelocidad() {
		return velocidad;
	}
	
	public void setVelocidad(double velocidad) {
			this.velocidad = velocidad;
	}
		
	public void setCongelado(boolean congelado) {
		this.congelado = congelado;
	}

	public boolean isCongelado() {
		return congelado;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public boolean isRodando() {
		return rodando;
	}

	public void setRodando(boolean rodando) {
		this.rodando = rodando;
	}

	public int getTipoMonstruo() {
		return tipoMonstruo;
	}

	public void setTipoMonstruo(int tipoMonstruo) {
		this.tipoMonstruo = tipoMonstruo;
	}
	
	
}
