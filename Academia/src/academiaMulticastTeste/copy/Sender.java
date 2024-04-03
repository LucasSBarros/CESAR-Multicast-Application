package academiaMulticastTeste.copy;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

class Sender {
	private MulticastSocket socket;
	private InetSocketAddress grupo;
	private SimpleDateFormat dateFormat;
	private int codigoTopico;
	private String nome;
	

	public Sender(MulticastSocket socket, InetSocketAddress grupo, int codigoTopico, String nome) {
		this.socket = socket;
		this.grupo = grupo;
		this.codigoTopico = codigoTopico;
		this.nome = nome;
		dateFormat = new SimpleDateFormat("[dd/MM/yyyy - HH:mm]");
	}

	public void sendMessage(String messageContent) throws IOException {
		String[] sair_nome = messageContent.split(";");
		if("sair".equals(sair_nome[0])) {
			byte[] envio = new byte[1024];
			String mensagem = messageContent;
			envio = mensagem.getBytes();
			DatagramPacket packet;
			
			packet = new DatagramPacket(envio, envio.length, grupo);
			socket.send(packet);
		} else {
			byte[] envio = new byte[1024];
			Date currentDate = new Date();
			String formattedDate = dateFormat.format(currentDate);		
			String nomeTopico = (codigoTopico == 1) ? "Canal de informações" : "Canal de Promoções";
			String mensagem = formattedDate + " - " + nomeTopico + " - " + nome + ": " + messageContent;
			envio = mensagem.getBytes();
			DatagramPacket packet;

			packet = new DatagramPacket(envio, envio.length, grupo);
			socket.send(packet);
		}
	}
}