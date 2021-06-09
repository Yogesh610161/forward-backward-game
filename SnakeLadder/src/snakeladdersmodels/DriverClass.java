package snakeladdersmodels;

import java.util.Scanner;

public class DriverClass {

	public static void main(String[] args) {
		Scanner scanner=new Scanner(System.in);
		System.out.println("Enter the Board Size");
		int boardSize=scanner.nextInt();
		System.out.println("Enter the number of players");
		int numberOfPlayers=scanner.nextInt();
		System.out.println("Enter the number of snakes");
		int numberOfSnakes=scanner.nextInt();
		System.out.println("Enter the number of ladders");
		int numberOfLadders=scanner.nextInt();
		
		Game game=new Game(numberOfLadders, numberOfSnakes, boardSize);
		for(int i=0;i<numberOfPlayers;i++) {
			System.out.println("Enter the player name");
			String pname= scanner.next();
			Players players=new Players(pname);
			game.addPlayer(players);
		}
           game.playGame();
	}

}
