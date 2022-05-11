import java.util.Arrays;

public class SlotMachine {

    public static final String SEVEN = "7️⃣";
    public static final String MONEY = "\uD83D\uDCB0";
    public static final String CHERRY = "\uD83C\uDF52";
    public static final String EIGHT_BALL = "\uD83C\uDFB1";
    public static final String HEARTS = "\uD83D\uDC95";
    public static final String X = "❌";
    public static final String DICE = "\uD83C\uDFB2";

    private String[] currentSymbols;
    private String[] allSymbols;

    private int jackpot;

    public SlotMachine() {
        currentSymbols = new String[3];
        allSymbols = new String[] {SEVEN, MONEY, CHERRY, EIGHT_BALL, HEARTS, X, DICE};
        jackpot = 0;
    }

    public void pullLever() {
        currentSymbols[0] = allSymbols[(int)(Math.random()*7)];
        currentSymbols[1] = allSymbols[(int)(Math.random()*7)];
        currentSymbols[2] = allSymbols[(int)(Math.random()*7)];
    }

    public String displayResult() {
        String space1 = " ";
        String space2 = " ";
        if (currentSymbols[0].equals(SEVEN)) space1 = space1 + " ";
        if (currentSymbols[1].equals(SEVEN)) space2 = space2 + " ";
        return currentSymbols[0] + space1 + currentSymbols[1] + space2 + currentSymbols[2];
    }

    public int getJackpot() {
        return jackpot;
    }

    public String determineScore() {
        int[] check = new int[7];
        for (int i = 0; i < currentSymbols.length; i++) {
            if (currentSymbols[i].equals(SEVEN)) check[0]++;
            if (currentSymbols[i].equals(MONEY)) check[1]++;
            if (currentSymbols[i].equals(CHERRY)) check[2]++;
            if (currentSymbols[i].equals(EIGHT_BALL)) check[3]++;
            if (currentSymbols[i].equals(HEARTS)) check[4]++;
            if (currentSymbols[i].equals(X)) check[5]++;
            if (currentSymbols[i].equals(DICE)) check[6]++;
        }

        // rules:
        // Three "MONEY" --> +5000 Das Coins to jackpot
        if (check[1] == 3) {
            jackpot = jackpot + 5000;
            return "BIG MONEY! 5000 DAS COINS!";
        }
        // Two "MONEY" --> 1000 Das Coins
        if (check[1] == 2) {
            jackpot = jackpot + 2000;
            return "MONEY BAGS! 2000 DAS COINS!";
        }
        // Three "SEVEN" --> +2000 Das Coins to jackpot
        if (check[0] == 3) {
            jackpot = jackpot + 2000;
            return "LUCKY SEVENS! 2000 DAS COINS!";
        }
        // Two "SEVEN" --> +200 Das Coins
        if (check[0] == 2) {
            jackpot = jackpot + 200;
            return "SEVENS --> 200 DAS COINS!";
        }
        // Three "X" --> jackpot goes to 0
        if (check[5] == 3) {
            jackpot = 0;
            return "YOU ARE A LOSER!";
        }
        // Two "X" --> jackpot cut in half
        if (check[5] == 2) {
            jackpot = jackpot / 2;
            return "BAD LUCK! Jackpot cut in half";
        }
        // Three dice --> +100
        if (check[6] == 3) {
            jackpot = jackpot + 100;
            return "Lucky dice! 100 DAS COINS!";
        }
        // Two dice --> +50
        if (check[6] == 2) {
            jackpot = jackpot + 50;
            return "OK OK ... Nice Dice! 50 DAS COINS!";
        }
        // One dice --> +25
        if (check[6] == 1) {
            jackpot = jackpot + 25;
            return "ONE DICE! GRATS! 25 DAS COINS!";
        }
        // Three eight ball --> jackpot cut in half
        if (check[3] == 3) {
            jackpot = jackpot / 2;
            return "BAD EIGHT BALLS! Jackpot cut in half";
        }
        // Three hearts --> +100
        if (check[4] == 3) {
            jackpot = jackpot + 100;
            return "Lucky hearts! 100 DAS COINS!";
        }
        // Two hearts --> +50
        if (check[4] == 2) {
            jackpot = jackpot + 50;
            return "OK OK ... Nice hearts! 50 DAS COINS!";
        }
        return "No Prize! Play again! You know you want to!";

    }
}
