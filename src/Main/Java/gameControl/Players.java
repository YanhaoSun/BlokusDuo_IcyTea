package gameControl; /**
 * Group name: IcyTea
 * Members:
 *  Cathal Poon (20343243)
 *  YanHao Sun (19205434)
 *  Rubing Wang (20206497)
 */

public class Players {
    private String playerName;
    private char symbol;  //represent each piece in assignment 1(represent with color later in GUI) and represent player in CLI
    private PieceList pieceList; // list of pieces
    private Piece pieceHolding; // current selected piece
    private Score playerScore;
    private boolean skipped = false;



    //used for testing
    public String getPlayerName() {
        return playerName;
    }

    //used to get the symbol of player
    public char getSymbol() {
        return symbol;
    }

    //used to get the piecelist of the player
    public PieceList getPieceList() {
        return pieceList;
    }

    //used to get piece selected by player
    public Piece getPieceHolding() {
        return pieceHolding;
    }


    //used to get the score of each player
    public int getScore() {
        return playerScore.getScore();
    }


    Players(char symbol, String playerName){
        this.symbol = symbol;
        pieceList = new PieceList(symbol);

        this.playerName = playerName;
        this.playerScore = new Score(0);

        pieceList.initPiecesHold();
    }


    //this inner class is used to store score of every player
    private class Score{
        private int score;
        public Score(int score) {
            this.score = score;
        }

        public int getScore() {
            return score;
        }

        //this method will be used to update the score of player
        //only called if score of player is equal to 0
        public void addScores(int i){
            this.score += i;
        }
    }
    //every time player selects a piece, update score
    public void updateScore() {
        playerScore.addScores(pieceHolding.shape.length);
    }
    //return a string includes Player's name, the symbol and the score
    //return String.format("<%s>(%c) %d : %d <%s>(%c)\n", getPlayerName(), getSymbol(), getScore(), getScore(), getSymbol(), getPlayerName());

    public boolean setSkipped(boolean skip)
    {
        return skipped = skip;
    }
    public boolean isSkipped()
    {
        return skipped;
    }
    //if user selects piece, this method will be called to select pieces among pieceList
    public boolean selectPiece(String pieceNameChose){
        if(pieceList.checkPieceList(pieceNameChose))
        {
            pieceHolding = pieceList.getPiecesAvailableElement(pieceNameChose);
            return true;
        }
        return false;

    }

    //every time the player selects a piece, the piece selected should be removed from the PieceList
    public void updatePieceList() {
        pieceList.removePiecesAvailableElement(pieceHolding.name);
    }

    public boolean moreMovesAvailable(Board brd) {
        int rotateTimes=0; //count number of rotation
        boolean allNull = true; //indicate every element in pieceList is null

        //if there is no piece left in pieceList, return false
        if (pieceList.getPiecesAvailable().size() == 0) {
            return false;
        }

        //For all places that are not filled
        for (int i = 0; i < brd.getWidth(); i++) {
            for (int j = 0; j < brd.getHeight(); j++) {
                if (brd.isEmpty(i,j)==true) {
                    //for all pieces remaining
                    for (String eachPiece : pieceList.getPiecesAvailable()) {
                        Piece tmp = pieceList.getPiecesAvailableElement(eachPiece);
                        rotateTimes=0;
                        //check all possible situation
                        while (rotateTimes<=3) {

                            //if the move could be a valid move, return true
                            if (tmp.placeNormalPieceCheck(brd, i, j)) {
                                tmp.resetPiece();
                                return true;
                            }
                            //flip the piece
                            tmp.flip();
                            //check again
                            if (tmp.placeNormalPieceCheck(brd, i, j)) {
                                tmp.resetPiece();
                                return true;
                            }
                            //flip back
                            tmp.flip();
                            //try again after rotate
                            tmp.rotate();
                            rotateTimes++;
                        }
                        //reset the piece to its original position before moving to the next one
                        tmp.resetPiece();
                    }
                }
            }
        }
        //if no valid move find
        return false;
    }
    @Override
    public String toString() {
//        String store = ("<%s>(%c) %d : %d <%s>(%c)\n", getPlayerName(), getSymbol(), getScore(), getScore(), getSymbol(), getPlayerName(););
        return String.format("<%s>(%c) %d ", getPlayerName(), getSymbol(), getScore());
    }
}
