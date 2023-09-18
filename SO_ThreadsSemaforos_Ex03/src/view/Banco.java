package view;
import java.util.concurrent.Semaphore;

import controller.BancoThread;

public class Banco {
	public static void main(String[] args) {
		Semaphore semaforoDeposito=new Semaphore(1);
		Semaphore semaforoSaque=new Semaphore(1);
		int deposito=0;
		int saque=0;
		int saldo;
		int codigo;
		int transacao;

		for(int i=0;i<19;i++) {
			saldo=(int)(Math.random()*1000)+109;
			codigo=(int)(Math.random()*100)+100;
			transacao=(int)(Math.random()*2)+1;
			if(transacao==1) {
				deposito=(int)(Math.random()*900)+100;
			}else {
				saque=(int)(Math.random()*900)+100;
			}
			Thread bancoThread=new BancoThread(deposito,saque,semaforoDeposito,semaforoSaque,saldo,codigo);
			bancoThread.start();
		}
	}
}
