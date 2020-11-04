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
}
