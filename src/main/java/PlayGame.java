import Person.Dealer;
import Person.Person;
import Person.Player;
import TheDeck.Card;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.text.StyledEditorKit;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PlayGame {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.println("Welcome to BlackJack. Please enter how many decks you'd like to play with.");
    int noDecks = in.nextInt();
    System.out.println("What would you like the lower limit on the table to be?");
    double tableMinimum = in.nextDouble();
    System.out.println("And lastly what the upper limit should be?");
    double tableMaximum = in.nextDouble();

    Table table = new Table(noDecks, tableMinimum, tableMaximum);
    Dealer dealer = new Dealer("James", 500.0);
    table.addPerson(dealer);

    Boolean addingPlayers = true;
    while (addingPlayers){
      System.out.println("Lets get started with your name?");
      String name = in.next();
      System.out.println("And how much are you limiting yourself to spend with us today?");
      double wallet = in.nextDouble();
      Player player = new Player(name, wallet);
      table.addPerson(player);
      Boolean decisionMade = false;
      while (!decisionMade){
        System.out.println("Are there any more players to be added today? (y/n)");
        String decision = in.next();
        if (decision.equalsIgnoreCase("y") || decision.equalsIgnoreCase("yes")){
          decisionMade = true;
        } else if (decision.equalsIgnoreCase("n") || decision.equalsIgnoreCase("no")) {
          decisionMade = true;
          addingPlayers = false;
        } else {
          System.out.println("Please type either yes(y) or no(n)");
        }
      }
    }

    System.out.println(dealer.getName() + " will be your dealer and we have up to £" + (int) dealer.getWallet() + " for you to win today.");
    System.out.println("So playing with us today we have:");
    for (int i = 1 ; i < table.getPeople().size() ; i++){
      Person person = table.getPeople().get(i);
      System.out.println(person.getName() + " with a spending limit of £" + person.getWallet());
    }
    Boolean decisionMade = false;
    while (!decisionMade){
      System.out.println("Is this correct? (y/n)");
      String decision = in.next();
      if (decision.equalsIgnoreCase("y") || decision.equalsIgnoreCase("yes")){
        System.out.println("Great, we'll jump right in then.");
        decisionMade = true;
      } else if (decision.equalsIgnoreCase("n") || decision.equalsIgnoreCase("no")) {
        System.out.println("Sorry to hear that, please restart the program.");
        decisionMade = true;
      } else {
        System.out.println("Please type either yes(y) or no(n)");
      }
    }

    System.out.println("Lets play");

    Boolean playingGame = true;
    int counter = 0;

    while (playingGame){
      System.out.println("The table minimum is " + table.getTableMinimum() + " and the table maximum is " + table.getTableMaximum() + ". Would anyone like to change their bets? (y/n)");
      decisionMade = false;
      Boolean changingBet = false;
      while (!decisionMade){
        String decision = in.next();
        if (decision.equalsIgnoreCase("y") || decision.equalsIgnoreCase("yes")){
          System.out.println("Who would like to change their bet?");
          changingBet = true;
          decisionMade = true;
        } else if (decision.equalsIgnoreCase("n") || decision.equalsIgnoreCase("no")) {
          decisionMade = true;
        } else {
          System.out.println("Please type either yes(y) or no(n)");
        }
      }
      if (changingBet){
        for (int i = 1 ; i < table.getPeople().size() ; i++){
          Player player = (Player) table.getPeople().get(i);
          System.out.println(player.getName() + " is currently betting " + player.getCurrentBet() + ". Would they like to change? (y/n)");
          decisionMade = false;
          Boolean playerChangeBet = false;
          while (!decisionMade){
            String decision = in.next();
            if (decision.equalsIgnoreCase("y") || decision.equalsIgnoreCase("yes")){
              decisionMade = true;
              playerChangeBet = true;
            } else if (decision.equalsIgnoreCase("n") || decision.equalsIgnoreCase("no")) {
              decisionMade = true;
            } else {
              System.out.println("Please type either yes(y) or no(n)");
            }
          }
          if (playerChangeBet){
            System.out.println("How much would they like to bet? The table maximum is currently £" + table.getTableMaximum());
            double bet = in.nextDouble();
            table.setBet(player, bet);
          }
        }
      }

      table.newRound();
      for (int i = 1 ; i < table.getPeople().size() ; i++){
        Player player = (Player) table.getPeople().get(i);
        System.out.println(player.getName() + "! You're up. The dealer is showing the " + dealer.getHand().get(0).getName() + " of " + dealer.getHand().get(0).getSuit() + "s.");
        for (int j = 0 ; j < 3 ; j++){
          if (player.getHand(j).size() > 0 && table.checkIfCanSplit(player, j)){
            System.out.println("You have 2 " + player.getHand(j).get(0).getName() + "'s. Would you like to split them? (y/n)");
            decisionMade = false;
            while (!decisionMade){
                String decision = in.next();
                if (decision.equalsIgnoreCase("y") || decision.equalsIgnoreCase("yes")){
                  table.splitHand(player, j);
                  decisionMade = true;
                } else if (decision.equalsIgnoreCase("n") || decision.equalsIgnoreCase("no")) {
                  decisionMade = true;
                } else {
                  System.out.println("Please type either yes(y) or no(n)");
                }
              }
          }
        }

        for (int j = 0 ; j < 4 ; j++){
          if (player.getHand(j).size() > 0){
            Boolean playingHand = true;
            while (playingHand){
              System.out.println("The dealer is showing the " + dealer.getHand().get(0).getName() + " of " + dealer.getHand().get(0).getSuit() + "s. Your hand is currently:");
              for (Card card : player.getHand(j)){
                System.out.println("The " + card.getName() + " of " + card.getSuit() + "s.");
              }
              System.out.println("Would you like to twist? (y/n)");
              decisionMade = false;
              while (!decisionMade){
                String decision = in.next();
                if (decision.equalsIgnoreCase("y") || decision.equalsIgnoreCase("yes")){
                  table.twist(player, j);
                  Card card = player.getHand(j).get(player.getHand(j).size()-1);
                  System.out.println("You're dealt the " + card.getName() + " of " + card.getSuit() + "s.");
                  decisionMade = true;
                } else if (decision.equalsIgnoreCase("n") || decision.equalsIgnoreCase("no")) {
                  decisionMade = true;
                  playingHand = false;
                } else {
                  System.out.println("Please type either yes(y) or no(n)");
                }
              }
              if (!table.canTwist(player, j)){
                playingHand = false;
              }
            }
            int score = player.getScore(j);
            if (score == 0){
              System.out.println("Unfortunately that hand has gone bust.");
            } else {
              System.out.println("That hand has got a score of " + score);
            }
          }
        }
      }
      table.dealerTurn();
      HashMap<Player, HashMap<String, Integer>> results = table.checkWinners();
      System.out.println("The dealer got a score of " + dealer.getScore(0) +" with the following cards:");
      for (Card card : dealer.getHand()){
        System.out.println("The " + card.getName() + " of " + card.getSuit() + "s.");
      }
      for (int i = 0 ; i < results.size() ; i++){
        Player player = (Player) table.getPeople().get(i+1);
        int wins = results.get(player).get("Wins");
        int losses = results.get(player).get("Losses");
        System.out.println("Of " + player.getName() + "'s hands, " + wins + " won and " + losses + " lost.");
        double amount = (wins - losses) * player.getCurrentBet();
        player.increaseWallet(amount);
        dealer.increaseWallet(-amount);
      }


      System.out.println("The dealer has £" + dealer.getWallet() + " left to be won");
      ArrayList<Player> brokePlayers = new ArrayList<Player>();
      for (int i = 1 ; i < table.getPeople().size() ; i++){
        Player player = (Player) table.getPeople().get(i);
        System.out.println(player.getName() + " has £" + player.getWallet() + " left to spend.");
        if (player.getWallet() < table.getTableMinimum()){
          System.out.println("Unfortunately that is less than the table minimum and " + player.getName() + " can no longer play");
          brokePlayers.add(player);
        }
      }
      for (Player player : brokePlayers){
        table.getPeople().remove(player);
      }

      ArrayList<Player> playersNotWantingToPlay = new ArrayList<Player>();
      for (int i = 1 ; i < table.getPeople().size() ; i++){
        Player player = (Player) table.getPeople().get(i);
        System.out.println(player.getName() + ". Would you like to play another round? (y/n)");
        decisionMade = false;
        while (!decisionMade) {
          String decision = in.next();
          if (decision.equalsIgnoreCase("y") || decision.equalsIgnoreCase("yes")) {
            decisionMade = true;
          } else if (decision.equalsIgnoreCase("n") || decision.equalsIgnoreCase("no")) {
            playersNotWantingToPlay.add(player);
            decisionMade = true;
          } else {
            System.out.println("Please type either yes(y) or no(n)");
          }
        }
      }
      for (Player player : playersNotWantingToPlay){
        table.getPeople().remove(player);
      }

      System.out.println("Does anyone else want to play with us today? (y/n)");
      decisionMade = false;
      while (!decisionMade) {
        String decision = in.next();
        if (decision.equalsIgnoreCase("y") || decision.equalsIgnoreCase("yes")) {
          addingPlayers = true;
          decisionMade = true;
        } else if (decision.equalsIgnoreCase("n") || decision.equalsIgnoreCase("no")) {
          decisionMade = true;
        } else {
          System.out.println("Please type either yes(y) or no(n)");
        }
      }
      while (addingPlayers){
        System.out.println("Lets get started with your name?");
        String name = in.next();
        System.out.println("And how much are you limiting yourself to spend with us today?");
        double wallet = in.nextDouble();
        Player player = new Player(name, wallet);
        table.addPerson(player);
        decisionMade = false;
        while (!decisionMade){
          System.out.println("Are there any more players to be added today? (y/n)");
          String decision = in.next();
          if (decision.equalsIgnoreCase("y") || decision.equalsIgnoreCase("yes")){
            decisionMade = true;
          } else if (decision.equalsIgnoreCase("n") || decision.equalsIgnoreCase("no")) {
            decisionMade = true;
            addingPlayers = false;
          } else {
            System.out.println("Please type either yes(y) or no(n)");
          }
        }
      }


      if (table.getPeople().size() < 2){
        playingGame = false;
        System.out.println("Goodbye");
      }
    }



  }
}
