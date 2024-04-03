package academiaMulticastTeste;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;

import java.util.Scanner;

public class TesteCliente {

	public static void main(String[] args) throws IOException {
				
		Scanner sc = new Scanner(System.in);
		
		MulticastSocketManager msm = new MulticastSocketManager();
		msm.start();

		MulticastSocket socket1 = msm.getSocket1();
		MulticastSocket socket2 = msm.getSocket2();
		InetSocketAddress grupo1 = msm.getGrupo1();
		
		System.out.println("***CANAL DO CLIENTE***\n");
		System.out.println("Bem-vindo ao canal de avisos da Academia das Maravilhas!");
		System.out.println("Por favor, selecione uma opção:");
		System.out.println("1. Canal de informações sobre as aulas.");
		System.out.println("2. Canal de Promoções.");
		System.out.println("3. Os dois canais.");

		int codigoTopico = sc.nextInt();
		
		sc.nextLine(); // Limpar o buffer

		System.out.println("Por favor, informe o seu nome: ");
		String nome = sc.nextLine();
		
		Sender sender1 = new Sender(socket1, grupo1, codigoTopico, nome);

		if (codigoTopico == 1) {
			Thread thread1 = new Thread(new Receiver(socket1, nome));
			thread1.start();
			
			while(true) {
				String msg = sc.nextLine();
				sender1.sendMessage(msg);		            	
			}    

		} else if (codigoTopico == 2) {
			Thread thread2 = new Thread(new Receiver(socket2, nome));
			thread2.start();

		} else if (codigoTopico == 3) {
			Thread thread2 = new Thread(new Receiver(socket2, nome));
			thread2.start();
			
			Thread thread1 = new Thread(new Receiver(socket1, nome));
			thread1.start();
			
			while(true) {
				String msg = sc.nextLine();
				sender1.sendMessage(msg);		            	
			} 
		}
	}
}



