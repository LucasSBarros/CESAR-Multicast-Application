package academiaMulticastTeste.copy;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;


class Receiver implements Runnable {
	private MulticastSocket socket;
	String nome;
	
    public Receiver(MulticastSocket socket, String nome) {
        this.socket = socket;
        this.nome = nome;

    }

	public void run() {
		TesteAcademia academia = new TesteAcademia();
		try {
			while (true) {
				
				byte[] buffer = new byte[1024];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				String msg = new String(packet.getData(), 0, packet.getLength());
				
				if("A.D.M.".equals(nome)) {
					if(!msg.contains("A.D.M")){
						String[] sair_nome = msg.split(";");
						if("sair".equals(sair_nome[0].toLowerCase())) {
							academia.setSair(1);
							academia.setNomeSair(sair_nome[1]);
						} else {
							System.out.println("Mensagem Recebida: " + msg);
						}
					}
				} else {
					if(msg.contains("A.D.M") || !msg.contains(nome)){
						String[] sair_nome = msg.split(";");
						if("sair".equals(sair_nome[0].toLowerCase())) {
							academia.setSair(1);
							academia.setNomeSair(sair_nome[1]);
						} else {
							System.out.println("Mensagem Recebida: " + msg);
						}
					}
										
				}
							
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}