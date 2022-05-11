public class Player {
    private int spent;
    private String userName;
    private int earned;

    public Player(String userName) {
        spent = 0;
        this.userName = userName;
        earned = 0;
    }

    public void play() {
        spent = spent + 20;
    }

    public void quit(int jackpot) {
        earned = jackpot - spent;
    }

    public int getWinnings() {
        return earned;
    }

    public int getSpent() {
        return spent;
    }
}
