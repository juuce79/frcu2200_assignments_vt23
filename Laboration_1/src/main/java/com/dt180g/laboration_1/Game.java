package com.dt180g.laboration_1;

/**
 * This entity represents the game rock, paper and scissors.
 * Simulates battles between players.
 * @author Erik Ström
 */
public class Game {
    private final Player player1;
    private final Player player2;
    private final int winLimit = 3;
    private int battleAmount = 0;

    /**
     * Constructor of game object.
     * @param firstPlayer the first player
     * @param secondPlayer the second player
     */
    public Game(final Player firstPlayer, final Player secondPlayer) {
        this.player1 = firstPlayer;
        this.player2 = secondPlayer;
    }

    /**
     * Determines which player will win this battle.
     * @param firstPlayerTool first player's selected tool.
     * @param secondPlayerTool second player's selected tool.
     * @return the victorious player.
     */
    private Player determineWinner(Tool firstPlayerTool, Tool secondPlayerTool) {
        return secondPlayerTool.getClass().equals(firstPlayerTool.getWeakness().getClass()) ? player2 : player1;
    }

    /**
     * Recursive battle simulation. Will only end once a winner has been declared.
     * The player who reaches a win count equal to winLimit will stand victorious.
     */
    public void runBattle() {
        System.out.printf("%s meets %s in battle %d...%n",
                player1.getName(), player2.getName(), ++this.battleAmount);

        Tool firstPlayerTool;
        Tool secondPlayerTool;

        boolean draw;
        do {
            firstPlayerTool = player1.selectTool();
            secondPlayerTool = player2.selectTool();

            System.out.printf("\t%s\n\t%s%n", player1, player2);

            draw = firstPlayerTool.toString().equals(secondPlayerTool.toString());
            if (draw) {
                System.out.println("\n\tIt's a draw. Choosing new tools!\n");
            }
        } while (draw);

        Player winner = determineWinner(firstPlayerTool, secondPlayerTool);

        System.out.println("\t" + winner.declareAsWinner());
        System.out.println();

        if (winner.getWinCount() < this.winLimit) {
            runBattle();
        } else {
            System.out.printf("%s stands victorious after %d battles!%n",
                    winner.getName(), this.battleAmount);
        }
    }
}
