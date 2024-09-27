import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Dice {
    private Random random;

    public Dice() {
        random = new Random();
    }

    public int roll() {
        return random.nextInt(6) + 1;
    }
}

abstract class Player {
    protected int chips;
    protected List<Integer> rolls;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(int chips, String name) {
        this.name = name;
        this.chips = chips;
        this.rolls = new ArrayList<>();
    }

    public abstract void takeTurn(Dice dice);

    public int getSum() {
        return rolls.stream().mapToInt(Integer::intValue).sum();
    }

    public int getChips() {
        return chips;
    }

    public void winPot(int pot) {
        chips += pot;
    }

    public void loseChip() {
        chips--;
    }

    public boolean isEliminated() {
        return chips <= 0;
    }
}

class HumanPlayer extends Player {
    public HumanPlayer(int chips, String name) {
        super(chips, name);
    }

    @Override
    public void takeTurn(Dice dice) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your first roll: ");
        for (int i = 0; i < 3; i++) {
            int roll = dice.roll();
            rolls.add(roll);
            System.out.println("Dice " + (i + 1) + ": " + roll);
        }

        System.out.println("How many dice do you want to roll again? (0-3)");
        int numberOfRolls = scanner.nextInt();
        for (int i = 0; i < numberOfRolls; i++) {
            int roll = dice.roll();
            rolls.add(roll);
            System.out.println("Additional dice " + (i + 1) + ": " + roll);
        }
    }
}

class VirtualPlayer extends Player {
    public VirtualPlayer(int chips,String name) {
        super(chips,name);
    }

    @Override
    public void takeTurn(Dice dice) {
        for (int i = 0; i < 3; i++) {
            int roll = dice.roll();
            rolls.add(roll);
            System.out.println( getName()+" Initial Dice " + (i + 1) + ": " + roll);
        }

        int firstRollTotal = getSum();
        int numberOfRolls = 0;
        if (firstRollTotal < 9) {
            numberOfRolls = 3;
        } else if (firstRollTotal < 15) {
            numberOfRolls = 2;
        } else {
            numberOfRolls = 1;
        }

        for (int i = 0; i < numberOfRolls; i++) {
            int roll = dice.roll();
            rolls.add(roll);
            System.out.println("Virtual Player " + getName() + " Dice " + (i + 1) + ": " + roll);
        }
    }
}

class BlackJack {
    private List<Player> players;
    private Dice dice;

    public BlackJack(List<Player> players) {
        this.players = players;
        this.dice = new Dice();
    }

    public void playRound() {
        int pot = 0;
        Player winner = null;
        int closestTo21 = 0;

        for (Player player : players){
            player.rolls.clear();
        }

        for (Player player : players) {
            if (!player.isEliminated()) {
                player.takeTurn(dice);

                int sum = player.getSum();
                if (sum == 21) {
                    winner = player;
                    break;
                } else if (sum > 21) {
                    player.loseChip();
                    pot++;
                } else if (sum > closestTo21) {
                    closestTo21 = sum;
                    winner = player;
                }
            }
        }

        if (winner != null) {
            winner.winPot(pot);
            pot = 0;
            for (Player player : players) {
                if (!player.isEliminated() && player != winner) {
                    player.loseChip();
                    winner.winPot(1);
                }
            }
        }

//        List<Player> toRemove = new ArrayList<>();
//        for (Player player : players) {
//            if (player.isEliminated()) {
//                toRemove.add(player);
//            }
//        }
//
//        for (Player player : toRemove) {
//            players.remove(player);
//        }
//        for(Player player: players){
//            System.out.println("remove之后player的数量："+ player.getName()+" "+player.getChips());
//        }
    }

    public boolean isGameover() {
//        return players.size() == 1;
        int count = 0;
        for(Player player: players){
            if(!player.isEliminated()){
                count++;
            }
        }
        return count == 1;
    }

    public Player getWinner() {
//        return players.get(0);
        for(Player player: players){
            if(!player.isEliminated()){
                return player;
            }
        }
        return new VirtualPlayer(1,"返回了错误的结果，没找到赢家");
    }
}

class Statistics {
    public static void simulateGames(int numberOfGames) {
        String[] wins = new String[numberOfGames];
        for (int i = 0; i < numberOfGames; i++) {
            List<Player> players = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                players.add(new VirtualPlayer(3, "Virtual Player " + j));
            }
            BlackJack game = new BlackJack(players);
            while (!game.isGameover()) {
                game.playRound();
            }
            wins[i] = game.getWinner().getName();
        }
        int[] results = new int[4];
        for (String winner : wins) {
            for (int j = 0; j < 4; j++) {
                if (winner.equals("Virtual Player " + j)) {
                    results[j]++;
                }
            }
        }

        for (int i = 0; i < results.length; i++) {

                System.out.println("Virtual Player " + i + "赢了" + results[i] + "场游戏");

        }
    }
}

public class BlackJackGame {
    public static void main(String[] args) {
        // Simulate a single game with a human player and virtual players
//        List<Player> players = new ArrayList<>();
//        players.add(new HumanPlayer(2, "Human"));
//        players.add(new VirtualPlayer(2, "Vir 1"));
//        players.add(new VirtualPlayer(2, "Vir 2"));
//        players.add(new VirtualPlayer(2, "Vir 3"));
////        System.out.println(players.size());
//        BlackJack game = new BlackJack(players);
//
//        while (!game.isGameover()) {
//            game.playRound();
//            System.out.println("Current chips: ");
//            for (Player player : players) {
//                if (!player.isEliminated()) {
//                    System.out.println(player.getName() + ": " + player.getChips());
//                }
//            }
//        }
//        for(Player player: players){
//            System.out.println(player.getName()+" "+player.getChips());
//        }
//        System.out.println("The winner is Player " + game.getWinner().getName() + " with " + game.getWinner().getChips() + " chips.");

        // Simulate a large number of games with only virtual players
        Statistics.simulateGames(10000);
    }
}
