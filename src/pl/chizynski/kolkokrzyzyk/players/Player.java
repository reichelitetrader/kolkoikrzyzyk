
package pl.chizynski.kolkokrzyzyk.players;

import pl.chizynski.kolkokrzyzyk.boards.Board;

public abstract class Player  {

    public Board board;

    
    public Player(Board board){
        this.board = board;
    }
    
    public void wykonaj_ruch(){
        System.out.println("");
    }
    
    
}
