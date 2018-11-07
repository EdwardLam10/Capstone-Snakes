package online_snake;

public enum Player {
	PLAYER1(1),
	PLAYER2(2);
	
	private int _player;
	
	private Player(int num) {
		this._player = num;
	}
	
	public static Player enumFromValue(int player) {
		if (player== 1) {
			return PLAYER1;
		}
		if (player == 2) {
			return PLAYER2;
		}
		return null;
	}
	
	public static Player getRival(Player player) {
		if (player == PLAYER2) {
			return PLAYER1;
		}
		if (player == PLAYER1) {
			return PLAYER2;
		}
		return null;
	}
}
