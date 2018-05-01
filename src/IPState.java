
public class IPState {

	private String ip;
	private Object state;
	
	public IPState(String ip) {
		this.ip = ip;
	}
	
	public void SetState(Object state) {
		this.state = state;
	}
	
	public Object GetState() {
		return this.state;
	}
	
	public String GetIP() {
		return this.ip;
	}

}
