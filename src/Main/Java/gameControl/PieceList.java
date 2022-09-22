package gameControl;

import java.util.Hashtable;
import java.util.Set;

public class PieceList
{
    private Hashtable<String, Piece> piecesAvailable;
    private char symbol;

    public PieceList(char symbol)
    {
        this.symbol = symbol;
    }

    public int getPieceListSize() {
        return piecesAvailable.size();
    }

    public Set<String> getPiecesAvailable() {
        return piecesAvailable.keySet();
    }

    public Piece getPiecesAvailableElement(String key) {
        return piecesAvailable.get(key);
    }

    public void removePiecesAvailableElement(String name) {
        piecesAvailable.remove(name);
    }

    public void initPiecesHold() {
        piecesAvailable = new Hashtable<>() {{
            for (String name :
                    Piece.shapesTable.keySet()) {
                put(name, new Piece(name, symbol));
            }
        }};
    }

    //used to check if the selected piece is in the pieceList
    public boolean checkPieceList(String pieceNameChose) {
        for(String fir: piecesAvailable.keySet())
        {
            if(piecesAvailable.containsKey(fir) && fir.equals(pieceNameChose))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("gamepieces: [");
        int count=0;
        boolean first = true;
        for(String piece: piecesAvailable.keySet()) {
            if (first) {
                buffer.append(piece);
                first = false;
            } else {
                buffer.append(", ");
                buffer.append(piece);
            }

            if(count == ((piecesAvailable.keySet()).size())-1) {
                    break;
            }

            count++;
        }
        buffer.append("]");
        return buffer.toString();
    }
}
