///**
// * Group name: IcyTea
// * Members:
// *  Cathal Poon (20343243)
// *  YanHao Sun (19205434)
// *  Rubing Wang (20206497)
// */
//
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import java.nio.charset.StandardCharsets;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class PlayersTest {
//    @Test
//    void testGetNameFromUser() {
//        String input1 = "who are u";
//        InputStream in1 = new ByteArrayInputStream(input1.getBytes(StandardCharsets.UTF_8));
//        InputStream stdin = System.in;
//        System.setIn(in1);
//        Players p1 = new Players();
//        //check if initial score for first player is equal to 0
//        assertEquals(0, p1.getScore());
//        //check if player's name is stored and can be read from getPlayerName method
//        System.out.println("player name = "+p1.getPlayerName());
//        assertEquals(input1, p1.getPlayerName());
//        System.setIn(stdin);
//    }
//    @Test
//    void testIniScores()
//    {
//        String input = "du you know who u are?";
//        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
//        InputStream stdin = System.in;
//        System.setIn(in);
//        Players p2 = new Players();
//        System.out.println("Player's initialise score = "+p2.getScore());
//        assertEquals(0, p2.getScore());
//        System.setIn(stdin);
//    }
//    @Test
//    void testInitPiecesHold()
//    {
//        String input = "I...am...IRON MAN!!!";
//        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
//        InputStream stdin = System.in;
//        System.setIn(in);
//        Players p3 = new Players('@');
//        System.out.println(p3.pieceListToString());
//        System.setIn(stdin);
//    }
//    @Test
//    void testUpdateScore()
//    {
//        String input = "Optimus Prime";
//        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
//        InputStream stdin = System.in;
//        System.setIn(in);
//        Players p4 = new Players('^');
//        System.out.println("player name = "+p4.getPlayerName());
//
//        //check if initial score for first player is equal to 0
//        System.out.println("\nPlayer's initialised score = "+p4.getScore());
//        assertEquals(0, p4.getScore());
//
//        //player selects first piece
//        p4.selectPiece("F");
//        System.out.println("Player's score after select first piece: "+p4.getScore());
//        p4.updatePieceList();
//        assertEquals("F", p4.getPieceHolding().getPieceName());
//        assertEquals(5, p4.getScore());
//
//        //player selects second piece
//        p4.selectPiece("I2");
//        System.out.println("Player's score after select second piece: "+p4.getScore());
//        assertEquals("I2", p4.getPieceHolding().getPieceName());
//        p4.updatePieceList();
//        assertEquals(7, p4.getScore());
//        System.setIn(stdin);
//    }
//    @Test
//    void testCheckPieceList()
//    {
//        String input = "Peter Park";
//        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
//        InputStream stdin = System.in;
//        System.setIn(in);
//        Players p5 = new Players('+');
//        System.out.println("player name = "+p5.getPlayerName());
//        //check if I2 is in the pieceList
//        assertEquals(true, p5.checkPieceList("I2"));
//        //check if A is in the pieceList
//        System.out.println("Player selects piece: A");
//        assertEquals(false, p5.checkPieceList("A"));
//        System.setIn(stdin);
//    }
//    @Test
//    void testSelectPiece()
//    {
//        String input = "Doctor Strange";
//        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
//        InputStream stdin = System.in;
//        System.setIn(in);
//        Players p6 = new Players('&');
//        System.out.println(p6.pieceListToString());
//        //System.out.println("\nPlayer selects piece: I2");
//        if(p6.selectPiece("I2"))
//        {
//            System.out.println("Valid Main.gameControl.Piece Name");
//        }
//        else
//        {
//            System.out.println("Invalid Main.gameControl.Piece Name");
//        }
//        //check if I2 is selected successfully
//        assertEquals("I2", p6.getPieceHolding().getPieceName());
//
//        //check if A is in the pieceList
//        p6.updatePieceList();
//        System.out.println("Select piece I2 again");
//        if(p6.selectPiece("I2"))
//        {
//            System.out.println("Valid Main.gameControl.Piece Name");
//        }
//        else
//        {
//            System.out.println("Invalid Main.gameControl.Piece Name");
//        }
//        //assertEquals("A", p.getPieceHolding().getPieceName());
//        System.setIn(stdin);
//    }
//    @Test
//    void testUpdatePieceList()
//    {
//        String input = "Batman";
//        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
//        InputStream stdin = System.in;
//        System.setIn(in);
//
//        Players p7 = new Players('&');
//        System.out.println("Main.Pieces list at first: ");
//        System.out.println(p7.pieceListToString());
//        p7.selectPiece("I2");
//        //below print out the piece list
//        System.out.println("Main.Pieces list after updated: ");
//        p7.updatePieceList();
//        System.out.println(p7.pieceListToString());
//
//        //check if A is in the pieceList
//        p7.selectPiece("W");
//        System.out.println("Main.Pieces list after updated again: ");
//        p7.updatePieceList();
//        System.out.println(p7.pieceListToString());
//        System.setIn(stdin);
//    }
//    @Test
//    void testPieceListToString()
//    {
//        String input = "Superman";
//        InputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
//        InputStream stdin = System.in;
//        System.setIn(in);
//
//        Players p8 = new Players('%');
//        System.out.println("Main.Pieces list at first: ");
//        System.out.println(p8.pieceListToString());
//        p8.selectPiece("I5");
//        //below print out the piece list
//        System.out.println("Main.Pieces list after select I5: ");
//        p8.updatePieceList();
//        System.out.println(p8.pieceListToString());
//
//        //check if A is in the pieceList
//        p8.selectPiece("Z5");
//        System.out.println("Main.Pieces list after select Z5: ");
//        p8.updatePieceList();
//        System.out.println(p8.pieceListToString());
//        System.setIn(stdin);
//    }
//}
