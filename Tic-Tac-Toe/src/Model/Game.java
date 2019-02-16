
package Model;

public class Game {
    private int id;
    private String type;
    private String player1;
    private String player2;
    private String gameResult;
    private String winner;
    private float time;
    private String boardSize;

    public Game(int id, String type, String boardSize, String player1, String player2, String gameResult, String winner, float time) {
        this.id = id;
        this.type = type;
        this.boardSize = boardSize;
        this.player1 = player1;
        this.player2 = player2;
        this.gameResult = gameResult;
        this.winner = winner;
        this.time = time;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPlayer1() {
		return player1;
	}

	public void setPlayer1(String player1) {
		this.player1 = player1;
	}

	public String getPlayer2() {
		return player2;
	}

	public void setPlayer2(String player2) {
		this.player2 = player2;
	}

	public String getGameResult() {
		return gameResult;
	}

	public void setGameResult(String gameResult) {
		this.gameResult = gameResult;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public String getBoardSize() {
		return boardSize;
	}

	public void setBoardSize(String boardSize) {
		this.boardSize = boardSize;
	}

   
}

