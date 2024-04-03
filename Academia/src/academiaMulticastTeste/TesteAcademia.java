package academiaMulticastTeste;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class TesteAcademia {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
		MulticastSocketManager msm = new MulticastSocketManager();
		msm.start();

		MulticastSocket socket1 = msm.getSocket1();
		MulticastSocket socket2 = msm.getSocket2();
		InetSocketAddress grupo1 = msm.getGrupo1();
		InetSocketAddress grupo2 = msm.getGrupo2();
	
		System.out.println("***CANAL DO SERVIDOR***\n");
		System.out.println("Digite o código do tópico: 1 - para o Canal de informações sobre as aulas, 2 - para o canal de Promoções):");
		
		int codigoTopico = sc.nextInt();
		
		String nome = "A.D.M.";
		
		Sender sender1 = new Sender(socket1, grupo1, codigoTopico, nome);
		Sender sender2 = new Sender(socket2, grupo2, codigoTopico, nome);

		while (true) {     	

			if (codigoTopico == 1) {
				Thread thread1 = new Thread(new Receiver(socket1, nome));
				thread1.start();
				
				String msg = sc.nextLine();	            
				sender1.sendMessage(msg);

			} else if (codigoTopico == 2) {			
				String msg = sc.nextLine();
				sender2.sendMessage(msg);	
			}
		}
	}
}
