package academiaMulticastTeste;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

class MulticastSocketManager {
	private static final String ADDRESS = "230.0.0.0";
	private static final int HOST_1 = 4321;
	private static final int HOST_2 = 4322;
	private MulticastSocket socket1;
	private MulticastSocket socket2;
	private InetSocketAddress grupo1;
	private InetSocketAddress grupo2;

	public void start() {
		try {
			InetAddress ia = InetAddress.getByName(ADDRESS);
			NetworkInterface ni = NetworkInterface.getByInetAddress(ia);
			socket1 = new MulticastSocket(HOST_1);
			socket2 = new MulticastSocket(HOST_2);
			grupo1 = new InetSocketAddress(ia , HOST_1);
			grupo2 = new InetSocketAddress(ia , HOST_2);
			socket1.joinGroup(grupo1, ni);
			socket2.joinGroup(grupo2, ni);
		} catch (IOException e) {
			e.printStackTrace();
		}     
	}

	public MulticastSocket getSocket1() {
		return socket1;
	}

	public MulticastSocket getSocket2() {
		return socket2;
	}

	public InetSocketAddress getGrupo1() {
		return grupo1;
	}

	public InetSocketAddress getGrupo2() {
		return grupo2;
	}

}
