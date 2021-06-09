package snakeladdersmodels;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.lang3.RandomUtils;

import lombok.Getter;

@Getter
public class Game {
     private int numberOfLadders;
     private int numberOfSnakes;
     private Board board;
     private Queue<Players> players;
     private List<Snake> snakes;
     private List<Ladder> ladders;
     private Dice dice;
     
     public Game(int numberOfLadders, int numberOfSnakes, int size) {
    	 this.numberOfLadders=numberOfLadders;
    	 this.numberOfSnakes=numberOfSnakes;
    	 board=new Board(size);
    	 players=new ArrayDeque<>();
    	 snakes=new ArrayList<>(numberOfSnakes);
    	 ladders=new ArrayList<>(numberOfLadders);
    	 dice=new Dice(1,6,2);
    	 initBoard();
     }
     
     private void initBoard() {
    	 Set<String> pairSet =new HashSet<>();
    	 for(int i=0;i<numberOfSnakes;i++) {
    		 while(true) {
    			 int startPoint=RandomUtils.nextInt(board.getStart(),board.getEnd());
    			 int endPoint=RandomUtils.nextInt(board.getStart(),board.getEnd());
    			 if(startPoint <= endPoint) {
    				 continue;
    			 }
    			 String combinationString=String.valueOf(startPoint)+endPoint;
    			 if(!pairSet.contains(combinationString)) {
    				 Snake snake=new Snake(startPoint, endPoint);
    				 snakes.add(snake);
    				 pairSet.add(combinationString);
    				 break;
    			 }
    		 }
    	 }
    	 for(int i=0;i<numberOfLadders;i++) {
    		 while(true) {
    			 int ladderStart=RandomUtils.nextInt(board.getStart(),board.getEnd());
    			 int ladderEnd=RandomUtils.nextInt(board.getStart(),board.getEnd());
    			 if(ladderStart>=ladderEnd) {
    				 continue;
    			 }
    			 String ladderPairString=String.valueOf(ladderStart)+ladderEnd;
    			 if(!pairSet.contains(ladderPairString)) {
    				 Ladder ladder=new Ladder(ladderStart, ladderEnd);
    				 ladders.add(ladder);
    				 pairSet.add(ladderPairString);
    				 break;
    			 }
    		 }
    	 }
     }
     
     public void addPlayer(Players player) {
    	 players.add(player);
     }
     
     public void playGame() {
    	 while(true) {
    	 Players player=players.poll();
    	 int stepvalue=dice.roll();
    	 int newPosition=player.getPosition()+stepvalue;
    	 if(newPosition > board.getEnd()) {
    		 newPosition=player.getPosition();
    		 players.offer(player);
    	 }
    	 else {
    		 player.setPosition(getNewPosition(newPosition));
    		 if(player.getPosition()==board.getEnd()) {
    			 player.setWon(true);
    			 System.out.println("Player "+player.getName()+" won");
    		 }
    		 else {
    			 System.out.println("Player "+player.getName()+" moved to" + player.getPosition());
    			 players.offer(player);
    		 }
    	 }
    	 if(players.size()<2) {
    		 break;
    		 }
    	 
     }
    	 
    	 }
     
     private int getNewPosition(int value) {
    	 for(Snake sn:snakes) {
    		 if(sn.getHead()==value) {
    			 System.out.println("Snake Bit");
    			 return sn.getTail();
    		 }
    	 }
    	 
    	 for(Ladder ladd:ladders) {
    		 if(ladd.getStart()==value) {
    			 System.out.println("Took ladder");
    			 return ladd.getEnd();
    		 }
    	 }
    	 return value;
     }
}
