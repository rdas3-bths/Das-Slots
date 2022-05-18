public class Player {
    private int spent;
    private String userName;
    private int earned;
    private int savedEarnings;

    public Player(String userName, int savedEarnings) {
        spent = 0;
        this.userName = userName;
        earned = 0;
        this.savedEarnings = savedEarnings;
    }

    public void play() {
        spent = spent + 100;
    }

    public void quit(int jackpot) {
        earned = (jackpot - spent) + savedEarnings;
    }

    public int getWinnings() {
        if (spent == 0) return savedEarnings;
        return earned;
    }

    public int getSpent() {
        return spent;
    }

    public String getUserName() { return userName; }

    public int getLifetimeWinnings() { return savedEarnings; }
}
