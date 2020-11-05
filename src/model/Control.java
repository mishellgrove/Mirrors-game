package model;

public class Control {

	
	private MatrixGame game;
	private Player root;
	private String participants;

	public Control() {
		root = null;
		participants = "";
	}
	
	public void initialize(String name, int a, int b, int mirror ) {
		game = new MatrixGame(name, a, b, mirror);
	}
	
	public String getParticipants() {
		return participants;
	}

	public void setParticipants(String participants) {
		this.participants = participants;
	}

	public MatrixGame getGame() {
		return game;
	}

	public void setGame(MatrixGame game) {
		this.game = game;
	}
	
	public Player getRoot() {
		return root;
	}

	public void setRoot(Player root) {
		this.root = root;
	}
	
	public void addPlayer(Player temp) {
		addPlayer(temp, getRoot());
	}

	public void addPlayer(Player temp, Player root) {
		if(getRoot() == null) {
			setRoot(temp);
		}else {
			if(temp.getScore() <= root.getScore()) {
				if(root.getIzq() == null) {
					root.setIzq(temp);
				}else {
					addPlayer(temp, root.getIzq());
				}
			}else {
				if(root.getDer()==null) {
					root.setDer(temp);
				}else {
					addPlayer(temp, root.getDer());
				}
			}
		}
	}
	
	public void recibirdatos() {
		Player temp = game.mandarDatos();
		addPlayer(temp);
	}
	
	public void inOrden(Player r) {
		

	}
	
}
