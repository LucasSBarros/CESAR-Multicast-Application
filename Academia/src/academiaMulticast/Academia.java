package academiaMulticast;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class Academia {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
		MulticastSocketManager msm = new MulticastSocketManager();
		msm.start();

		MulticastSocket socket1 = msm.getSocket1();
		MulticastSocket socket2 = msm.getSocket2();
		InetSocketAddress grupo1 = msm.getGrupo1();
		InetSocketAddress grupo2 = msm.getGrupo2();
	
		System.out.println("***CANAL DO SERVIDOR***\n");
		String msg = "";
		
		String nome = "A.D.M.";
		
		while (!("sair".equals(msg.toLowerCase()))) { 
			
			System.out.println("Tópicos disponíveis: 1 - para o Canal de informações sobre as aulas, 2 - para o canal de Promoções\nDigite o código do tópico escolhido:");
			
			int codigoTopico = sc.nextInt();
			
			Sender sender1 = new Sender(socket1, grupo1, codigoTopico, nome);
			Sender sender2 = new Sender(socket2, grupo2, codigoTopico, nome);
	
			System.out.println("Pronto! Você já está conectado com o tópico escolhido, pode digitar a mensagem abaixo e aperte 'Enter' para enviá-la\n");    	

			if (codigoTopico == 1) {
				Thread thread1 = new Thread(new Receiver(socket1, nome));
				thread1.start();
				
				sc.nextLine();
				msg = sc.nextLine();
				
				if("sair".equals(msg.toLowerCase())) {
					sender1.sendMessage(" O servidor está sendo fechado, até logo!");
					sender2.sendMessage(" O servidor está sendo fechado, até logo!");
				} else {
					sender1.sendMessage(msg);
				}

			} else if (codigoTopico == 2) {
				
				sc.nextLine();
				msg = sc.nextLine();
				
				if("sair".equals(msg.toLowerCase())) {
					sender1.sendMessage(" O servidor está sendo fechado, até logo!");
					sender2.sendMessage(" O servidor está sendo fechado, até logo!");
				} else {
					sender2.sendMessage(msg);
				}	
			}
		}
		
		System.out.println("Fechando o servidor!");
		System.exit(0);
		sc.close();
	}
}