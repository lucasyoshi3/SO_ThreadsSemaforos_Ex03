package controller;

import java.util.concurrent.Semaphore;

public class BancoThread extends Thread{
	private int deposito;
	private int saque;
	private int saldo;
	private int codigo;
	private Semaphore semaforoDeposito;
	private Semaphore semaforoSaque;
	
	public BancoThread(int deposito,int saque,Semaphore semaforoDeposito,Semaphore semaforoSaque,int saldo,int codigo) {
		this.deposito=deposito;
		this.saque=saque;
		this.semaforoDeposito=semaforoDeposito;
		this.semaforoSaque=semaforoSaque;
		this.saldo=saldo;
		this.codigo=codigo;
	}
	
	@Override
	public void run() {
		if(deposito!=0) {
			try {
				semaforoDeposito.acquire();
				efetuarDeposito();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				semaforoDeposito.release();
			}
		}else if(saque!=0) {
			try {
				semaforoSaque.acquire();
				efetuarSaque();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				semaforoSaque.release();
			}
		}
	}

	private void efetuarSaque() {
		if(saldo<saque) {
			System.out.println("Saldo insuficiente");
		}else {
			saldo-=saque;
			System.out.println("Codigo da conta: "+codigo+"; Saque efetuado: "+saque+"; Saldo: "+saldo);
		}
		tempoSleep();
	}

	private void efetuarDeposito() {
		saldo+=deposito;
		System.out.println("Codigo da conta: "+codigo+"; Deposito efetuado: "+deposito+"; Saldo: "+saldo);
		tempoSleep();
	}
	
	private void tempoSleep() {
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
