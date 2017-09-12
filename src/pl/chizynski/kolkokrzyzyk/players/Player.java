
package pl.chizynski.kolkokrzyzyk.players;

import pl.chizynski.kolkokrzyzyk.boards.Board;

public abstract class Player  {

    public Board board;

    
    public Player(Board board){
        this.board = board;
    }
    
    public abstract boolean wykonaj_ruch(int numerRuchu, char wynik);
    
    
    
    
}
