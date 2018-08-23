package pl.chizynski.kolkokrzyzyk.rules;
import pl.chizynski.kolkokrzyzyk.boards.Board;

public class RulesFactory {
    public static Rules createRules(String boardSizeInString, Board board) {
        Rules rules = new Rules(board);
        if (boardSizeInString.equals("GUMOKU")) {
            rules = new GumokuRules(board);
        } 
        return rules;
    }
}
