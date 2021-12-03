package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	private static final char TECLA_DERECHA = 37;
	// El objeto Entorno que controla el tiempo y otros

	private static final char TECLA_IZQUIERDA = 39;
	
	private Entorno entorno;
	private Viga [] vigas;
	private Image fondo;
	private Pared paredIzquierda;
	private Pared paredDerecha;
	private Mago mago;
	private Monstruo [] monstruo;
	private Monstruo [] monstruo2;
	private Hechizo hechizo;
	private Hechizo hechizo2;
	private Hechizo hechizo3;
	private Hechizo hechizo4;
	private HechizoEspecial hechizoEspecial;
	private HechizoEspecial hechizoEspecial1;
	private HechizoEspecial hechizoEspecial2;
	private HechizoEspecial hechizoEspecial3;
	private HechizoEspecial hechizoEspecial4;
	private HechizoEspecial hechizoEspecial5;
	private HechizoEspecial hechizoEspecial6;
	private HechizoEspecial hechizoEspecial7;
	private int tiempo;
	private int tiempoBarra;
	private int tiempoItems;
	private int tiempoTrampa;
	private int tiempoInmunidad;
	private Barra barra;
	private Items [] itemPos;
	private Items itemEspecial;
	private TrampaDeFuego [] trampas;
	private TrampaDeFuego trampa;
	private double posY;
	private boolean trampaActivada;
	private int cantMonstruos;
	private int turnoHechizoDer;
	private int turnoHechizoIzq;
	Image GameOver;
	Image YouWin;

	// Variables y métodos propios de cada grupo
	// ...
	
	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Magic Tower", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		this.vigas = new Viga[8];
		this.paredIzquierda= new Pared(25, 308, 50, 620, 0);
		this.paredDerecha= new Pared(785, 308, 50, 620, 0);
		this.mago = new Mago();
		this.monstruo = new Monstruo [4];
		this.monstruo2 = new Monstruo [4];
		this.cantMonstruos=8;
		this.trampas = new TrampaDeFuego[7];
		this.barra= new Barra (100,40,1,20,0,Color.magenta);		// Hasta 80 de ancho
		this.itemPos= new Items[8];
		this.turnoHechizoDer=1;
		this.turnoHechizoIzq=1;
		this.fondo=Herramientas.cargarImagen("wall.png");
		GameOver = Herramientas.cargarImagen("GameOver.png");
		YouWin = Herramientas.cargarImagen("YouWin.png");
		
//		this.trampa = new TrampaDeFuego(0,-100,0,0,0);
		
		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick()
	{
		this.entorno.dibujarImagen(fondo, 450, 300, 0);
		dibujarVigas();
		mago.dibujarse(entorno);
		dibujarItems();
		itemEspecial();
		barraDeEnergia();
		trampas();
		mago();
		accionesConHechizo();
		hechizoEspecial();
		accionesMonstruos();	
		dibujarPared();
		
		entorno.cambiarFont("Aerial", 20, Color.white);
		entorno.escribirTexto("SCORE : "+ mago.getPuntos(), 60, 20);	//Dibujo los puntos
		entorno.escribirTexto("LIFE : "+ mago.getVidas(), 650, 20);	//Dibujo la cantidad de vida
		
		tiempo++;
		tiempoBarra++;
		tiempoItems++;
		tiempoTrampa++;
		tiempoInmunidad++;
	}
	
	public void accionesMonstruos()
	{
		for (int i=0; i<monstruo.length ; i++)	// inicializo los monstruos 
		{
			if (monstruo[i]==null && this.cantMonstruos==8 )
				if (i==0)
				{
					this.monstruo[0]=new Monstruo(500,290);
					monstruo[0].setDireccion(monstruo[0].getDireccion()*-1);
				}
				else if (i==1)
					this.monstruo[1]=new Monstruo(300,290);
				else if (i==2)
				{
					this.monstruo[2]=new Monstruo(630,390);
					monstruo[2].setDireccion(monstruo[2].getDireccion()*-1);
				}
					
				else
					this.monstruo[3]=new Monstruo(165,390);
		}
		
		if(this.cantMonstruos==4)		// inicializo el segundo arreglo de monstruos
		{
			for (int i=0; i<monstruo2.length ; i++)	
			{
				if (monstruo2[i]==null )
					if (i==0)
					{
						this.monstruo2[0]=new Monstruo(500,290);		// hago aliasing con las ubicaciones de el primer arreglo de monstruos
						this.monstruo2[0].setVelocidad(2.4);
						this.monstruo[0]=this.monstruo2[0];
						this.monstruo[0].setTipoMonstruo(2);
						this.monstruo[0].setDireccion(monstruo[0].getDireccion()*-1);
					}
				
					else if (i==1)
					{
						this.monstruo2[1]=new Monstruo(300,290);
						this.monstruo2[1].setVelocidad(2.4);
						this.monstruo[1]=this.monstruo2[1];
						this.monstruo[1].setTipoMonstruo(2);
					}
					else if (i==2)
					{
						this.monstruo2[2]=new Monstruo(630,390);
						this.monstruo2[2].setVelocidad(2.4);
						this.monstruo[2]=this.monstruo2[2];
						this.monstruo[2].setTipoMonstruo(2);
						this.monstruo[2].setDireccion(monstruo[2].getDireccion()*-1);
					}
					else
					{
						this.monstruo2[3]=new Monstruo(165,390);
						this.monstruo2[3].setVelocidad(2.4);
						this.monstruo[3]=this.monstruo2[3];
						this.monstruo[3].setTipoMonstruo(2);
					}
			}
			
		}
		
		if (monstruo[0]!=null)	// Dibujo los monstruos en el entorno
		{
//			if (monstruo[0].getDireccion()==-1  )
//				monstruo[0].setDireccion(monstruo[0].getDireccion()*-1);
			
			monstruo[0].dibujarse(entorno);
		}
		
		if (monstruo[1]!=null )
			monstruo[1].dibujarse(entorno);
		
		if (monstruo[2]!=null )	
		{	
//			if (monstruo[2].getDireccion()==-1  )
//				monstruo[2].setDireccion(monstruo[2].getDireccion()*-1);
			monstruo[2].dibujarse(entorno);
		}
		
		if (monstruo[3]!=null )
			monstruo[3].dibujarse(entorno);
				
		for (int i=0; i <monstruo.length; i++)
		{
			if (monstruo[i]!=null)	
			{
				if (monstruo[i].toca(paredDerecha))
						
					monstruo[i].setDireccion(monstruo[i].getDireccion()*-1); 	// Si el monstruo toca la pared derecha cambia el valor de direccion
					
				if(monstruo[i].toca(paredIzquierda))
						
					monstruo[i].setDireccion(monstruo[i].getDireccion()*-1);	//Si el monstruo toca la pared izquierda cambia el valor de direccion
				
				if (monstruo[i].getY()>=610 && monstruo[i].isRodando()==false)
				{	
					monstruo[i].setX(400);
					monstruo[i].setY(20);
				}		
				// si el monstruo toca al mago y no esta congelado y no esta rodando y el mago no es inmune el mago pierde una vida y aparece en la parte superior de la pantalla
				if(monstruo[i].toca(mago.getX(), mago.getY(), mago.getAncho(), mago.getAlto()) && monstruo[i].isCongelado()==false && monstruo[i].isRodando()==false && mago.isInmunidad()==false)
				{
					mago.setX(400);
					mago.setY(0);
					mago.setVidas(mago.getVidas()-1);
					mago.setInmunidad(true);		// Al perder una vida el mago se vuelve inmune por unos segundos
					this.tiempoInmunidad=0;
				}
				
				for (int j=0;j<vigas.length;j++)		// Mientras el monstruo toca una viga se mueve
				{	
					if (monstruo[i].toca(vigas[j]))  
						
						monstruo[i].moverse(paredDerecha, paredIzquierda);		
				}
				monstruo[i].caer(vigas);	// si no toca una viga cae
			}	
		}
		
		for (int j=0; j<monstruo.length; j++)
		{
			if (monstruo[j]!=null )	
			{
				if ( hechizo!=null)
				{
					if (monstruo[j].toca(hechizo) && monstruo[j].isRodando()==false)	//Si los monstruos toca un hechizo se congela
					{
						monstruo[j].setCongelado(true);
						hechizo= null;
						monstruo[j].setVelocidad(0);
						
					}
				}
				if ( hechizo2!=null)
				{
					if (monstruo[j].toca(hechizo2) && monstruo[j].isRodando()==false)
					{
						monstruo[j].setCongelado(true);
						hechizo2= null;
						monstruo[j].setVelocidad(0);
					}
				}
				if ( hechizo3!=null)
				{
					if (monstruo[j].toca(hechizo3) && monstruo[j].isRodando()==false)
					{
						monstruo[j].setCongelado(true);
						hechizo3= null;
						monstruo[j].setVelocidad(0);
					}
				}
				if ( hechizo4!=null)
				{
					if (monstruo[j].toca(hechizo4) && monstruo[j].isRodando()==false)
					{
						monstruo[j].setCongelado(true);
						hechizo4= null;
						monstruo[j].setVelocidad(0);
					}
				}
			}
		}
		
		for (int j=0; j<monstruo.length; j++) //Si el monstruo toca algun hechizo especial se congela
		{
				if (monstruo[j]!=null )	
				{
					if ( hechizoEspecial !=null)
					{
						if (monstruo[j].toca(hechizoEspecial.getX(), hechizoEspecial.getY(), hechizoEspecial.getAncho(), hechizoEspecial.getAlto()) && monstruo[j].isRodando()==false)
						{
							monstruo[j].setCongelado(true);
							hechizoEspecial= null;
							monstruo[j].setVelocidad(0);	
						}
					}
					if ( hechizoEspecial1 !=null)
					{
						if (monstruo[j].toca(hechizoEspecial1.getX(), hechizoEspecial1.getY(), hechizoEspecial1.getAncho(), hechizoEspecial1.getAlto()) && monstruo[j].isRodando()==false)
						{
							monstruo[j].setCongelado(true);
							hechizoEspecial1= null;
							monstruo[j].setVelocidad(0);	
						}
					}
					if ( hechizoEspecial2 !=null)
					{
						if (monstruo[j].toca(hechizoEspecial2.getX(), hechizoEspecial2.getY(), hechizoEspecial2.getAncho(), hechizoEspecial2.getAlto()) && monstruo[j].isRodando()==false)
						{
							monstruo[j].setCongelado(true);
							hechizoEspecial2= null;
							monstruo[j].setVelocidad(0);	
						}
					}
					if ( hechizoEspecial3 !=null)
					{
						if (monstruo[j].toca(hechizoEspecial3.getX(), hechizoEspecial3.getY(), hechizoEspecial3.getAncho(), hechizoEspecial3.getAlto()) && monstruo[j].isRodando()==false)
						{
							monstruo[j].setCongelado(true);
							hechizoEspecial3= null;
							monstruo[j].setVelocidad(0);	
						}
					}
					if ( hechizoEspecial4 !=null)
					{
						if (monstruo[j].toca(hechizoEspecial4.getX(), hechizoEspecial4.getY(), hechizoEspecial4.getAncho(), hechizoEspecial4.getAlto()) && monstruo[j].isRodando()==false)
						{
							monstruo[j].setCongelado(true);
							hechizoEspecial4= null;
							monstruo[j].setVelocidad(0);	
						}
					}
					if ( hechizoEspecial5 !=null)
					{
						if (monstruo[j].toca(hechizoEspecial5.getX(), hechizoEspecial5.getY(), hechizoEspecial5.getAncho(), hechizoEspecial5.getAlto()) && monstruo[j].isRodando()==false)
						{
							monstruo[j].setCongelado(true);
							hechizoEspecial5= null;
							monstruo[j].setVelocidad(0);	
						}
					}
					if ( hechizoEspecial6 !=null)
					{
						if (monstruo[j].toca(hechizoEspecial6.getX(), hechizoEspecial6.getY(), hechizoEspecial6.getAncho(), hechizoEspecial6.getAlto()) && monstruo[j].isRodando()==false)
						{
							monstruo[j].setCongelado(true);
							hechizoEspecial6= null;
							monstruo[j].setVelocidad(0);	
						}
					}
					if ( hechizoEspecial7 !=null)
					{
						if (monstruo[j].toca(hechizoEspecial7.getX(), hechizoEspecial7.getY(), hechizoEspecial7.getAncho(), hechizoEspecial7.getAlto()) && monstruo[j].isRodando()==false)
						{
							monstruo[j].setCongelado(true);
							hechizoEspecial7= null;
							monstruo[j].setVelocidad(0);	
						}
					}
				}
		}

		// si el el monstruo esta congelado corre el tiempo  hasta llegar a 200 y el monstruo recupera su movimiento
		for (int i=0; i <monstruo.length; i++) 
		{
			if (monstruo[i]!=null)
			{
				if (monstruo[i].isCongelado())
					monstruo[i].tiempo++;
				else
					monstruo[i].tiempo=0;
				
				if (monstruo[i].tiempo>=200)
				{
					if(monstruo[i].getTipoMonstruo()==2)
					{
						monstruo[i].setVelocidad(2.4);
						monstruo[i].setCongelado(false);
					}
					else 
					{
						monstruo[i].setVelocidad(2);
						monstruo[i].setCongelado(false);
					}
				}
				// Si el monstruo es tocado mientras esta congelado por el mago, comienza a rodar
				if(monstruo[i].toca(mago.getX(), mago.getY(), mago.getAncho(), mago.getAlto()) && monstruo[i].isCongelado())
				{	
					monstruo[i].setDireccion(mago.getDireccion());
					monstruo[i].setRodando(true);
					monstruo[i].tiempo-=2;
					monstruo[i].setVelocidad(2);
					monstruo[i].moverse(paredDerecha, paredIzquierda);
					mago.sumarPuntos(50);
					monstruo[i].setCongelado(false);
				}
			}
		}
		
		for (int i=0; i <monstruo.length; i++)
		{
			for (int j=0; j <monstruo.length; j++)
			{
				if (monstruo[i]!=null && monstruo[j]!=null)	// si el monstruo que esta rodando toca a otro monstruo que no lo esta, el monstruo que no giraba comienza a girar en la direccion del primer monstruo
				{
					if (monstruo[i].toca(monstruo[j].getX(),monstruo[j].getY(),monstruo[j].getAncho(),monstruo[j].getAlto()) && monstruo[i].isRodando() && monstruo[j].isRodando()==false)
					{
						
						monstruo[j].setRodando(true);
						monstruo[j].tiempo-=2;
						monstruo[j].setDireccion(monstruo[i].getDireccion());
						monstruo[j].rodar();
				//		monstruo[j].setVelocidad(2);
					}
					if (monstruo[i].isRodando())
						monstruo[i].rodar();
					
					if (monstruo[i].isRodando() && monstruo[i].getY()>=610)
					{
						monstruo[i]=null;
						this.cantMonstruos-=1;
						mago.sumarPuntos(100);
					}
				}
			}
		}
		//SI el mago mata 4 monstruos se termina el juego
		if(mago.ganaste(this.cantMonstruos))
		{
			entorno.dibujarImagen(YouWin, 400, 300, 0, 0.9);	
		}
		//SI el mago pierde 3 vidas se termina el juego
		if(mago.murio())
		{
			entorno.dibujarImagen(GameOver, 400, 300, 0,0.9);
		}
	}
	
	public void hechizoEspecial()
	{
		if(entorno.sePresiono(entorno.TECLA_ESPACIO) && barra.getAncho()==80 )
		{			
				hechizoEspecial  = new HechizoEspecial (mago.getX(),mago.getY(),10,10,0 );
				hechizoEspecial1 = new HechizoEspecial (mago.getX(),mago.getY(),10,10,0 );
				hechizoEspecial2 = new HechizoEspecial (mago.getX(),mago.getY(),10,10,0 );
				hechizoEspecial3 = new HechizoEspecial (mago.getX(),mago.getY(),10,10,0 );
				hechizoEspecial4 = new HechizoEspecial (mago.getX(),mago.getY(),10,10,0 );
				hechizoEspecial5 = new HechizoEspecial (mago.getX(),mago.getY(),10,10,0 );
				hechizoEspecial6 = new HechizoEspecial (mago.getX(),mago.getY(),10,10,0 );
				hechizoEspecial7 = new HechizoEspecial (mago.getX(),mago.getY(),10,10,0 );
				barra.setAncho(1);
				tiempoBarra=0;
		}
			
		if ( hechizoEspecial!=null)		// Mientras el hechizoEspecial no toque una pared se movera a la izquierda.
		{
			if (hechizoEspecial.toca(paredIzquierda)==false)
			{	
				hechizoEspecial.moverIzquierda();	
				
				if (hechizoEspecial.toca(paredIzquierda))
					
					hechizoEspecial=null;
			}
		}
		
		if ( hechizoEspecial1!=null)		// Mientras el hechizoEspecial no toque una pared se movera a la izquierda.
		{
			if (hechizoEspecial1.toca(paredDerecha)==false)
			{	
				hechizoEspecial1.moverDerecha();	
				
				if (hechizoEspecial1.toca(paredDerecha))
					
					hechizoEspecial1=null;
			}
		}
		
		if ( hechizoEspecial2!=null)		// Mientras el hechizoEspecial no toque una pared se movera a la izquierda.
		{
			hechizoEspecial2.moverArriba();	
				
			if (hechizoEspecial2.toca(vigas[0]) || hechizoEspecial2.toca(vigas[1]) ||hechizoEspecial2.toca(vigas[2]) || 
				hechizoEspecial2.toca(vigas[3]) ||hechizoEspecial2.toca(vigas[4]) ||hechizoEspecial2.toca(vigas[5]) || 
				hechizoEspecial2.toca(vigas[6]) || hechizoEspecial2.toca(vigas[7]) || hechizoEspecial2.getY()<0 || hechizoEspecial2.getY()>605)	
					
				hechizoEspecial2=null;
		}
		
		if ( hechizoEspecial3!=null)		// Mientras el hechizoEspecial no toque una pared se movera a la izquierda.
		{
			hechizoEspecial3.moverAbajo();
			
			if ((hechizoEspecial3.toca(vigas[0]) || hechizoEspecial3.toca(vigas[1]) || hechizoEspecial3.toca(vigas[2]) || 	// Cuando el mago toca una viga despues de caer dispara en la direcion que tenga en ese momento.
				hechizoEspecial3.toca(vigas[3]) || hechizoEspecial3.toca(vigas[4]) || hechizoEspecial3.toca(vigas[5]) || 
				hechizoEspecial3.toca(vigas[6]) || hechizoEspecial3.toca(vigas[7])) || hechizoEspecial3.getY() < 0 || hechizoEspecial3.getY() > 605)
							
				hechizoEspecial3=null;
		}
		
		if ( hechizoEspecial4!=null)		// Mientras el hechizoEspecial no toque una pared se movera a la izquierda.
		{
			hechizoEspecial4.moverDiagonalDerechaArriba();
			
			if ((hechizoEspecial4.toca(vigas[0]) || hechizoEspecial4.toca(vigas[1]) || hechizoEspecial4.toca(vigas[2]) || // Cuando el mago toca una viga despues de caer dispara en la direcion que tenga en ese momento.
				hechizoEspecial4.toca(vigas[3]) || hechizoEspecial4.toca(vigas[4]) || hechizoEspecial4.toca(vigas[5]) || 
				hechizoEspecial4.toca(vigas[6]) || hechizoEspecial4.toca(vigas[7])) || hechizoEspecial4.getY()< 0 || hechizoEspecial4.getY() > 605)
										
				hechizoEspecial4=null;
		}
		
		if ( hechizoEspecial5!=null)		// Mientras el hechizoEspecial no toque una pared se movera a la izquierda.
		{
			hechizoEspecial5.moverDiagonalIzquierdaArriba();
				
			if ((hechizoEspecial5.toca(vigas[0]) || hechizoEspecial5.toca(vigas[1]) || hechizoEspecial5.toca(vigas[2]) || // Cuando el mago toca una viga despues de caer dispara en la direcion que tenga en ese momento.
				hechizoEspecial5.toca(vigas[3]) || hechizoEspecial5.toca(vigas[4]) || hechizoEspecial5.toca(vigas[5]) || 
				hechizoEspecial5.toca(vigas[6]) || hechizoEspecial5.toca(vigas[7])) || hechizoEspecial5.getY() < 0 || hechizoEspecial5.getY() > 605)
	
				hechizoEspecial5=null;
		}
			
		if ( hechizoEspecial6!=null)		// Mientras el hechizoEspecial no toque una pared se movera a la izquierda.
		{
			hechizoEspecial6.moverDiagonalDerechaAbajo();
			
			if ((hechizoEspecial6.toca(vigas[0]) || hechizoEspecial6.toca(vigas[1]) || hechizoEspecial6.toca(vigas[2]) || 
				hechizoEspecial6.toca(vigas[3]) || hechizoEspecial6.toca(vigas[4]) || hechizoEspecial6.toca(vigas[5]) || 
				hechizoEspecial6.toca(vigas[6]) || hechizoEspecial6.toca(vigas[7])) || hechizoEspecial6.getY() < 0 || hechizoEspecial6.getY() > 605)
				
				hechizoEspecial6=null;
		}
				
		if ( hechizoEspecial7!=null)		// Mientras el hechizoEspecial no toque una pared se movera a la izquierda.
		{
			hechizoEspecial7.moverDiagonalIzquierdaAbajo();
			
			if ((hechizoEspecial7.toca(vigas[0]) || hechizoEspecial7.toca(vigas[1]) || hechizoEspecial7.toca(vigas[2]) || // Cuando el mago toca una viga despues de caer dispara en la direcion que tenga en ese momento.
				hechizoEspecial7.toca(vigas[3]) || hechizoEspecial7.toca(vigas[4]) || hechizoEspecial7.toca(vigas[5]) || 
				hechizoEspecial7.toca(vigas[6]) || hechizoEspecial7.toca(vigas[7])) || hechizoEspecial7.getY() < 0 || hechizoEspecial7.getY() > 605)
				
				hechizoEspecial7=null;	
		}
			
			
			if ( hechizoEspecial!=null)	
				
				hechizoEspecial.Dibujarse(entorno);	// Dibuja el hechizoEspecial.
			
			if ( hechizoEspecial1!=null)	
				
				hechizoEspecial1.Dibujarse(entorno);	

			if ( hechizoEspecial2!=null)	
				
				hechizoEspecial2.Dibujarse(entorno);	

			if ( hechizoEspecial3!=null)	
				
				hechizoEspecial3.Dibujarse(entorno);	

			if ( hechizoEspecial4!=null)	
				
				hechizoEspecial4.Dibujarse(entorno);	

			if ( hechizoEspecial5!=null)	
				
				hechizoEspecial5.Dibujarse(entorno);	

			if ( hechizoEspecial6!=null)	
				
				hechizoEspecial6.Dibujarse(entorno);	

			if ( hechizoEspecial7!=null)	
				
				hechizoEspecial7.Dibujarse(entorno);	
		
	}
	
	public void accionesConHechizo()
	{
		if (( mago.toca(vigas[0]) || mago.toca(vigas[1]) || mago.toca(vigas[2]) || mago.toca(vigas[3]) || mago.toca(vigas[4]) ||	// Cuando el mago toca una viga despues de caer dispara en la direcion que tenga en ese momento.
				mago.toca(vigas[5]) || mago.toca(vigas[6]) || mago.toca(vigas[7])) && mago.getDisparos()== 1 && mago.getDireccion()== 1 && this.turnoHechizoDer==1)
			{	
				mago.setDisparos(0);
				hechizo=new Hechizo(mago.getX(),mago.getY(),10,10,0 );
				hechizo.setDireccion(mago.getDireccion());
				this.turnoHechizoDer=2;
			}
		
		if (( mago.toca(vigas[0]) || mago.toca(vigas[1]) || mago.toca(vigas[2]) || mago.toca(vigas[3]) || mago.toca(vigas[4]) ||	// Cuando el mago toca una viga despues de caer dispara en la direcion que tenga en ese momento.
				mago.toca(vigas[5]) || mago.toca(vigas[6]) || mago.toca(vigas[7])) && mago.getDisparos()== 1 && mago.getDireccion()== 1 && this.turnoHechizoDer==2)
			{	
				mago.setDisparos(0);
				hechizo3= new Hechizo(mago.getX(),mago.getY(),10,10,0);
				hechizo3.setDireccion(mago.getDireccion());
				this.turnoHechizoDer=1;
			}
			
		if (( mago.toca(vigas[0]) || mago.toca(vigas[1]) || mago.toca(vigas[2]) || mago.toca(vigas[3]) || mago.toca(vigas[4]) ||
				mago.toca(vigas[5]) || mago.toca(vigas[6]) || mago.toca(vigas[7])) && mago.getDisparos()==1 && mago.getDireccion()== -1 && this.turnoHechizoIzq==1)
			{	
				mago.setDisparos(0);
				hechizo2=new Hechizo(mago.getX(),mago.getY(),10,10,0 );
				hechizo2.setDireccion(mago.getDireccion());
				this.turnoHechizoIzq=2;
			}
		
		if (( mago.toca(vigas[0]) || mago.toca(vigas[1]) || mago.toca(vigas[2]) || mago.toca(vigas[3]) || mago.toca(vigas[4]) ||	// Cuando el mago toca una viga despues de caer dispara en la direcion que tenga en ese momento.
				mago.toca(vigas[5]) || mago.toca(vigas[6]) || mago.toca(vigas[7])) && mago.getDisparos()== 1 && mago.getDireccion()== -1 && this.turnoHechizoIzq==2)
			{	
				mago.setDisparos(0);
				hechizo4= new Hechizo(mago.getX(),mago.getY(),10,10,0);
				hechizo4.setDireccion(mago.getDireccion());
				this.turnoHechizoIzq=1;
			}
		
		if ( hechizo2!=null )		// Mientras el hechizo2 no toque una pared se movera a la izquierda.
		{
			if (hechizo2.toca(paredIzquierda)==false)
			{	
				hechizo2.moverIzquierda();	
				
				if (hechizo2.toca(paredIzquierda))
					
					hechizo2=null;
			}
		}
		
		if ( hechizo3!=null)		// Mientras el hechizo2 no toque una pared se movera a la izquierda.
		{
			if (hechizo3.toca(paredDerecha)==false)
			{	
				hechizo3.moverDerecha();	
				
				if (hechizo3.toca(paredDerecha))
					
					hechizo3=null;
			}
		}	
		
		if ( hechizo!=null)			// Mientras el hechizo no toque una pared se movera a la derecha.
		{
			if (hechizo.toca(paredDerecha)==false)
			{	
				hechizo.moverDerecha();
				
				if (hechizo.toca(paredDerecha))
					
					hechizo= null;
			}
		}
		
		if ( hechizo4!=null)		// Mientras el hechizo2 no toque una pared se movera a la izquierda.
		{
			if (hechizo4.toca(paredIzquierda)==false)
			{	
				hechizo4.moverIzquierda();	
				
				if (hechizo4.toca(paredIzquierda))
					
					hechizo4=null;
			}
		}
		if ( hechizo!=null )	
			
			hechizo.Dibujarse(entorno);		// Dibuja el hechizo.
		
		if ( hechizo2!=null )
			
			hechizo2.Dibujarse(entorno);	// Dibuja el hechizo2.
		
		if ( hechizo3!=null )	
			
			hechizo3.Dibujarse(entorno);		// Dibuja el hechizo3.
		
		if ( hechizo4!=null )	
			
			hechizo4.Dibujarse(entorno);		// Dibuja el hechizo4.
			
	}
	
	public void mago()
	{

		mago.caer(vigas);		// Hago que el mago caiga cuando no toca una viga.
		
		if (entorno.sePresiono(TECLA_DERECHA)) 		// Si presiono la tecla derecha el mago cambia de direccion.
		{
			if (mago.getDireccion()==1)
				mago.setDireccion(mago.getDireccion()*-1);	
		}
		
		if (entorno.sePresiono(TECLA_IZQUIERDA))	// Si presiono la tecla izquierda el mago cambia de direccion.
		{
			if(mago.getDireccion()==-1)
				mago.setDireccion(mago.getDireccion()*-1);
		}
		
		if (mago.toca(paredDerecha))
				
			mago.setDireccion(mago.getDireccion()*-1); 	// Si el mago toca la pared derecha cambia el valor de direccion
			
		if(mago.toca(paredIzquierda))
				
			mago.setDireccion(mago.getDireccion()*-1);	//Si el mago toca la pared izquierda cambia el valor de direccion
		
		if (mago.getY()>=610 )
		{	
			mago.setX(400);
			mago.setY(0);	
		}
		for (int i=0;i<vigas.length;i++)	// Si el mago toca una viga se mueve sobre ella a la derecha o izquierda dependiendo la direccion hasta ya no tocarla.
		{	
			if (mago.toca(vigas[i]))  
				
				mago.moverse(paredDerecha, paredIzquierda);		
		}
		if (this.tiempoInmunidad > 150)		// Si la variable de la clase Mago inmunidad es verdadera y tiempoInmunidad es mayor a 150 se vuelve falsa.
			mago.setInmunidad(false);
	}
	
	public void trampas()
	{
		for (int i=0; i< this.trampas.length; i++)		// Si la trampa es null se genera una trampa con en una determinada posición
		{
			if (trampas[i]==null )
				if (i==0)
					this.trampas [0]= new TrampaDeFuego(650, 190, 80, 10, 0);

			if (trampas[i]==null )
				if (i==1)
					this.trampas [1]= new TrampaDeFuego(150, 190, 80, 10, 0);

			if (trampas[i]==null )
				if (i==2)
					this.trampas [2]= new TrampaDeFuego(400, 300, 80, 10, 0);

			if (trampas[i]==null )
				if (i==3)
					this.trampas [3]= new TrampaDeFuego(250, 420, 80, 10, 0);

			if (trampas[i]==null )
				if (i==4)
					this.trampas [4]= new TrampaDeFuego(520, 420, 80, 10, 0);

			if (trampas[i]==null )
				if (i==5)
					this.trampas [5]= new TrampaDeFuego(650, 570, 80, 10, 0);

			if (trampas[i]==null )
				if (i==6)
					this.trampas [6]= new TrampaDeFuego(150, 570, 80, 10, 0);
		}
		
		if (this.tiempoTrampa == 0 && this.trampaActivada == true)
		{	
			if (trampa==null)
			{
				Random rand = new Random();
				this.trampa = this.trampas[rand.nextInt(7)];
			}
		}
		
		if (this.tiempoTrampa == 500)
			
			this.trampa = null;
		
		if (trampa!=null)
		{
			if (this.tiempoTrampa >= 0 && this.tiempoTrampa < 500)
				
				this.trampa.dibujarse(entorno, this.tiempoTrampa);
		
			if (mago.toca(trampa.getX(), trampa.getY(), trampa.getAncho(), trampa.getAlto()) && mago.isInmunidad()==false)
			{
				mago.setInmunidad(true);
				this.mago.setX(400);
				this.mago.setY(0);
				mago.setVidas(mago.getVidas()-1);
				this.tiempoInmunidad=0;
			}
		}
	}
	
	public void barraDeEnergia()
	{
		if (barra.getAncho() < 80 )		//Si el ancho de la barra es menor a 80 aumenta el ancho en 1 cada 10 instantes
		{
			if (tiempoBarra==10)
			{
				barra.setAncho(barra.getAncho()+1);
				tiempoBarra=0;
			}
		}
		barra.dibujarse(entorno);		//Dinujo la barra
	}
	
	public void itemEspecial()
	{
		if (this.tiempoItems==1000)			//si el tiempo de item es igual a 1000 se nulea el item
		{
				this.itemEspecial=null;
				this.tiempoItems=0;
		}
		
		if (this.itemEspecial!=null)	// Se dibuja el item especial si el tiempo es de 500 a 1000
		{	
			if (this.tiempoItems >= 500 && this.tiempoItems < 1000)
				this.itemEspecial.dibujarse(entorno);
			
			if (this.tiempoItems >1000)
				this.tiempoItems=0;		//el tiempo vuelve a cero cuando llega a 1000
				
			if (mago.toca(itemEspecial.getX(), itemEspecial.getY(), itemEspecial.getAncho(), // Si el mago toca el item 0, se nulea el item, el tiempo de item vuelve a 0 y el mago gana una vida.
					itemEspecial.getAlto()) && itemEspecial.getNumero()==0)
			{
				itemEspecial=null;
				this.tiempoItems=0;
				mago.setVidas(mago.getVidas()+1);
			}
			else if (mago.toca(itemEspecial.getX(),itemEspecial.getY(),itemEspecial.getAncho(), //Si el mago toca el item 1, se nulea el item, el tiempo de item vuelve a 0 y el tiempo de la trampa se pone en cero y se activa la trampa.
					itemEspecial.getAlto()) && itemEspecial.getNumero()==1)
			{
				posY=itemEspecial.getY();
				itemEspecial=null;
				this.tiempoItems=0;
				this.tiempoTrampa=0;
				this.trampaActivada=true;
			}
			
			else if (mago.toca(itemEspecial.getX(),itemEspecial.getY(),itemEspecial.getAncho(), //Si el mago toca el item 3, se nulea el item, el tiempo de item vuelve a 0 y el mago llena su barra (tiro especial).
					itemEspecial.getAlto()) && itemEspecial.getNumero()==2)
			{
				itemEspecial=null;
				this.tiempoItems=0;
				barra.setAncho(80);
			}	
		}
	}
	
	public void dibujarItems()
	{
		for (int i=0 ; i < this.itemPos.length ; i++)		//Inicializo los items en sus posiciones
		{
			if (this.itemPos[i]==null )
				if (i==0)
					this.itemPos [0]= new Items(400, 60, 30, 30, 0);
			if (this.itemPos[i]==null )
				if (i==1)
					this.itemPos [1]= new Items(650, 180, 30, 30, 0);
			if (this.itemPos[i]==null )
				if (i==2)
					this.itemPos [2]= new Items(150, 180, 30, 30, 0);
			if (this.itemPos[i]==null )
				if (i==3)
					this.itemPos [3]= new Items(400, 290, 30, 30, 0);
			if (this.itemPos[i]==null )
				if (i==4)
					this.itemPos [4]= new Items(250, 410, 30, 30, 0);
			if (this.itemPos[i]==null )
				if (i==5)
					this.itemPos [5]= new Items(520, 410, 30, 30, 0);
			if (this.itemPos[i]==null )
				if (i==6)
					this.itemPos [6]= new Items(650, 560, 30, 30, 0);
			if (this.itemPos[i]==null )
				if (i==7)
					this.itemPos [7]= new Items(150, 560, 30, 30, 0);		
		}
		
		if (this.tiempoItems==500)			// Si el tiempo de items es igual a 500 y el item especial es null,
											// se genera una posicion de entre las posiciones de los item y se le otorga
			if (itemEspecial==null)
			{
				Random rand = new Random();
				this.itemEspecial=this.itemPos[rand.nextInt(8)];
			}
	}
	
	public void dibujarVigas()
	{
		//this.entorno.dibujarImagen(fondo, 450, 300, 0);
		for (int i=0;i<vigas.length;i++)		//Dibujo las vigas.
		{
			if (vigas[i]==null)
				if (i==0)
					this.vigas[0]= new Viga( 400, 100, 400, 20, 0);
			if (vigas[i]==null)
				if (i==1)
					this.vigas[1]= new Viga( 660, 210, 310, 20, 0);
			if (vigas[i]==null)
				if (i==2)
					this.vigas[2]= new Viga( 150, 210, 300, 20, 0);
			if (vigas[i]==null)
				if (i==3)
					this.vigas[3]= new Viga( 400, 320, 300, 20, 0);
			if (vigas[i]==null)
				if (i==4)
					this.vigas[4]= new Viga( 275, 440, 240, 20, 0);
			if (vigas[i]==null)
				if (i==5)
					this.vigas[5]= new Viga( 510, 440, 235, 20, 0);
			if (vigas[i]==null)
				if (i==6)
					this.vigas[6]= new Viga( 650, 590, 300, 20, 0);
			if (vigas[i]==null)
				if (i==7)
					this.vigas[7]= new Viga( 150, 590, 300, 20, 0);
			
			vigas[i].dibujarse(entorno);
		}
	}
	
	public void dibujarPared()
	{
		if(!mago.ganaste(this.cantMonstruos) && !mago.murio())
		{
		paredIzquierda.dibujarse(entorno);		// Dibujo las paredes.
		
		paredDerecha.dibujarse(entorno);	
		}

	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}

	public Entorno getEntorno() {
		return entorno;
	}

	public void setEntorno(Entorno entorno) {
		this.entorno = entorno;
	}

	public Viga[] getVigas() {
		return vigas;
	}

	public void setVigas(Viga[] vigas) {
		this.vigas = vigas;
	}

	public Pared getParedIzquierda() {
		return paredIzquierda;
	}

	public void setParedIzquierda(Pared paredIzquierda) {
		this.paredIzquierda = paredIzquierda;
	}

	public Pared getParedDerecha() {
		return paredDerecha;
	}

	public void setParedDerecha(Pared paredDerecha) {
		this.paredDerecha = paredDerecha;
	}

	public Mago getMago() {
		return mago;
	}

	public void setMago(Mago mago) {
		this.mago = mago;
	}

	public Monstruo[] getMonstruo() {
		return monstruo;
	}

	public void setMonstruo(Monstruo[] monstruo) {
		this.monstruo = monstruo;
	}

	public Hechizo getHechizo() {
		return hechizo;
	}

	public void setHechizo(Hechizo hechizo) {
		this.hechizo = hechizo;
	}

	public Hechizo getHechizo2() {
		return hechizo2;
	}

	public void setHechizo2(Hechizo hechizo2) {
		this.hechizo2 = hechizo2;
	}

	public HechizoEspecial getHechizoEspecial() {
		return hechizoEspecial;
	}

	public void setHechizoEspecial(HechizoEspecial hechizoEspecial) {
		this.hechizoEspecial = hechizoEspecial;
	}

	public HechizoEspecial getHechizoEspecial1() {
		return hechizoEspecial1;
	}

	public void setHechizoEspecial1(HechizoEspecial hechizoEspecial1) {
		this.hechizoEspecial1 = hechizoEspecial1;
	}

	public HechizoEspecial getHechizoEspecial2() {
		return hechizoEspecial2;
	}

	public void setHechizoEspecial2(HechizoEspecial hechizoEspecial2) {
		this.hechizoEspecial2 = hechizoEspecial2;
	}

	public HechizoEspecial getHechizoEspecial3() {
		return hechizoEspecial3;
	}

	public void setHechizoEspecial3(HechizoEspecial hechizoEspecial3) {
		this.hechizoEspecial3 = hechizoEspecial3;
	}

	public HechizoEspecial getHechizoEspecial4() {
		return hechizoEspecial4;
	}

	public void setHechizoEspecial4(HechizoEspecial hechizoEspecial4) {
		this.hechizoEspecial4 = hechizoEspecial4;
	}

	public HechizoEspecial getHechizoEspecial5() {
		return hechizoEspecial5;
	}

	public void setHechizoEspecial5(HechizoEspecial hechizoEspecial5) {
		this.hechizoEspecial5 = hechizoEspecial5;
	}

	public HechizoEspecial getHechizoEspecial6() {
		return hechizoEspecial6;
	}

	public void setHechizoEspecial6(HechizoEspecial hechizoEspecial6) {
		this.hechizoEspecial6 = hechizoEspecial6;
	}

	public HechizoEspecial getHechizoEspecial7() {
		return hechizoEspecial7;
	}

	public void setHechizoEspecial7(HechizoEspecial hechizoEspecial7) {
		this.hechizoEspecial7 = hechizoEspecial7;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public int getTiempoBarra() {
		return tiempoBarra;
	}

	public void setTiempoBarra(int tiempoBarra) {
		this.tiempoBarra = tiempoBarra;
	}

	public int getTiempoItems() {
		return tiempoItems;
	}

	public void setTiempoItems(int tiempoItems) {
		this.tiempoItems = tiempoItems;
	}

	public int getTiempoTrampa() {
		return tiempoTrampa;
	}

	public void setTiempoTrampa(int tiempoTrampa) {
		this.tiempoTrampa = tiempoTrampa;
	}

	public Barra getBarra() {
		return barra;
	}

	public void setBarra(Barra barra) {
		this.barra = barra;
	}

	public Items[] getItemPos() {
		return itemPos;
	}

	public void setItemPos(Items[] itemPos) {
		this.itemPos = itemPos;
	}

	public Items getItemEspecial() {
		return itemEspecial;
	}

	public void setItemEspecial(Items itemEspecial) {
		this.itemEspecial = itemEspecial;
	}

	public TrampaDeFuego[] getTrampas() {
		return trampas;
	}

	public void setTrampas(TrampaDeFuego[] trampas) {
		this.trampas = trampas;
	}

	public TrampaDeFuego getTrampa() {
		return trampa;
	}

	public void setTrampa(TrampaDeFuego trampa) {
		this.trampa = trampa;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public boolean isTrampaActivada() {
		return trampaActivada;
	}

	public void setTrampaActivada(boolean trampaActivada) {
		this.trampaActivada = trampaActivada;
	}

	public static char getTeclaDerecha() {
		return TECLA_DERECHA;
	}

	public static char getTeclaIzquierda() {
		return TECLA_IZQUIERDA;
	}
	
}
