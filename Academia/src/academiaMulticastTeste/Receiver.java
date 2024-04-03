package academiaMulticastTeste;

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
		try {
			while (true) {
				byte[] buffer = new byte[1024];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				String msg = new String(packet.getData(), 0, packet.getLength());

				if("A.D.M.".equals(nome)) {
					if(!msg.contains("A.D.M")){
						System.out.println("Mensagem Recebida: " + msg);
					}
				} else {
					if(msg.contains("A.D.M") || !msg.contains(nome)){
						System.out.println("Mensagem Recebida: " + msg);
					}

				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}