import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PlayerData {
    private ArrayList<Player> players;

    public PlayerData() {
        loadPlayers();
    }

    public void loadPlayers() {
        try {
            players = new ArrayList<Player>();
            File f = new File("src/players.data");
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String data = s.nextLine();
                String[] playerInfo = data.split("\\|");
                Player p = new Player(playerInfo[0], Integer.parseInt(playerInfo[1]));
                players.add(p);
            }
            s.close();
        } catch (FileNotFoundException fnf) {
            players = new ArrayList<Player>();
        }
    }

    public boolean playerExists(String player) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getUserName().equals(player)) return true;
        }
        return false;
    }

    public Player getPlayer(String player) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getUserName().equals(player)) return players.get(i);
        }
        return null;
    }

    public void addNewPlayer(Player p) {
        players.add(p);
    }

    public void saveData() {
        try {
            File f = new File("src/players.data");
            f.createNewFile();
            FileWriter fw = new FileWriter("src/players.data");
            String data = "";
            for (int i = 0; i < players.size(); i++) {
                data = players.get(i).getUserName() + "|" + players.get(i).getWinnings();
                fw.write(data + "\n");
            }
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Unable to create file");
            e.printStackTrace();
        }
    }
}
