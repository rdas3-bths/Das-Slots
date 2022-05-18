import java.util.Scanner;

public class PlaySlots {
    public static void main(String[] args) throws InterruptedException {
        SlotMachine s = new SlotMachine();
        PlayerData pd = new PlayerData();
        System.out.print("Welcome to the slot machine game! Enter your user name: ");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        Player p;
        if (pd.playerExists(userName)) {
            System.out.println("Welcome back, " + userName + "!");
            p = pd.getPlayer(userName);
        }
        else {
            p = new Player(userName, 0);
            pd.addNewPlayer(p);
        }
        System.out.print("Press \"q\" at any time to quit and take home your winnings!\nPress Enter to keep playing ");
        String o = sc.nextLine();
        while (o.equals("q") == false) {
            p.play();
            for (int i = 0; i < 50; i++) {
                s.pullLever();
                System.out.println("-----------");
                String end = "";
                if (s.lastSeven()) end = "  |";
                else end = " |";
                System.out.println("|" + s.displayResult() + end);
                System.out.println("-----------");
                if (i < 10) Thread.sleep(10);
                else if (i < 20) Thread.sleep(15);
                else if (i < 30) Thread.sleep(20);
                else if (i < 40) Thread.sleep(30);
                else if (i < 45) Thread.sleep(60);
                else if (i < 50) Thread.sleep(100);
                if (i != 49) {
                    System.out.println("\033[H\033[2J");
                }
            }
            System.out.println(s.determineScore());
            System.out.println("Current jackpot: " + s.getJackpot() + " Das Coins");
            System.out.println("Currently spent: " + p.getSpent() + " Das Coins");
            System.out.println("Lifetime winnings: " + p.getLifetimeWinnings() + " Das Coins");
            System.out.print("Press \"q\" at any time to quit and take home your winnings!\nPress Enter to keep playing ");
            o = sc.nextLine();
        }
        System.out.println("Game over!");
        System.out.println("Your current score is: " + (s.getJackpot() - p.getSpent()));
        System.out.println("Saving your data, " + p.getUserName());
        p.quit(s.getJackpot());
        pd.saveData();

    }
}
