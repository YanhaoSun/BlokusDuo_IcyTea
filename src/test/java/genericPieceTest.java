///**
// * Group name: IcyTea
// * Members:
// *  Cathal Poon (20343243)
// *  YanHao Sun (19205434)
// *  Rubing Wang (20206497)
// */
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//class genericPieceTest {
//
//    @Test
//    void testMatrixMultiplication() {
//        int[][] matrix1 = new int[][]{{3, 1}, {1, -1}, {0, 2}};
//        int[][] matrix2 = new int[][] {{2, -1, 3}, {1, 0, -1}};
//        int[][] expectResult1 = new int[][] {{5, 9}, {3, -1}};
//        int[][] expectResult2 = new int[][] {{7, -3, 8}, {1, -1, 4}, {2, 0, -2}};
//
//        Piece_I1 p1 = new Piece_I1('X');
//
//        int[][] actualResult1 = p1.matrixMultiplication(matrix2, matrix1);
//        assertArrayEquals(expectResult1, actualResult1);
//
//        System.out.println("actual result 1: ");
//        for (int i = 0; i <actualResult1.length; i++) {
//            for (int j = 0; j < actualResult1[0].length; j++) {
//                System.out.print(actualResult1[i][j] + "  ");
//            }
//            System.out.println();
//        }
//
//        int[][] actualResult2 = p1.matrixMultiplication(matrix1, matrix2);
//        assertArrayEquals(expectResult2, actualResult2);
//
//        System.out.println("actual result 2: ");
//        for (int i = 0; i <actualResult2.length; i++) {
//            for (int j = 0; j < actualResult2[0].length; j++) {
//                System.out.print(actualResult2[i][j] + "  ");
//            }
//            System.out.println();
//        }
//    }
//
//    @Test
//    void testRotate() {
//        Piece_I2 p2 = new Piece_I2('X');
//        int[][] expectResult = new int[][] {{0,0}, {1,0}};
//        p2.rotate();
//        int[][] actualResult = p2.getPieceShape();
//        assertArrayEquals(expectResult, p2.getPieceShape());
//
//        System.out.println("actual result 1: ");
//        for (int i = 0; i <actualResult.length; i++) {
//            for (int j = 0; j < actualResult[0].length; j++) {
//                System.out.print(actualResult[i][j] + "  ");
//            }
//            System.out.println();
//        }
//    }
//
//    @Test
//    void testFlip() {
//        Piece_V3 p = new Piece_V3('X');
//        int[][] expectResult = new int[][] {{0,0}, {0,-1}, {-1,0}};
//        p.flip();
//        int[][] actualResult = p.getPieceShape();
//        assertArrayEquals(expectResult, p.getPieceShape());
//
//        System.out.println("actual result 1: ");
//        for (int i = 0; i <actualResult.length; i++) {
//            for (int j = 0; j < actualResult[0].length; j++) {
//                System.out.print(actualResult[i][j] + "  ");
//            }
//            System.out.println();
//        }
//    }
//
//    @Test
//    void testToString() {
//        Piece_I1 i1 = new Piece_I1('X');
//        Piece_I2 i2 = new Piece_I2('X');
//        Piece_I3 i3 = new Piece_I3('X');
//        Piece_I4 i4 = new Piece_I4('X');
//        Piece_I5 i5 = new Piece_I5('X');
//        Piece_V3 v3 = new Piece_V3('X');
//        Piece_L4 l4 = new Piece_L4('X');
//        Piece_Z4 z4 = new Piece_Z4('X');
//        Piece_O4 o4 = new Piece_O4('X');
//        Piece_L5 l5 = new Piece_L5('X');
//        Piece_T5 t5 = new Piece_T5('X');
//        Piece_V5 v5 = new Piece_V5('X');
//        Piece_N n = new Piece_N('X');
//        Piece_Z5 z5 = new Piece_Z5('X');
//        Piece_T4 t4 = new Piece_T4('X');
//        Piece_P p = new Piece_P('X');
//        p.rotate();
//        p.rotate();
//        p.rotate();
//        Piece_W w = new Piece_W('X');
//        Piece_U u = new Piece_U('X');
//        u.flip();
//        Piece_F f = new Piece_F('X');
//        Piece_X x = new Piece_X('X');
//        Piece_Y y = new Piece_Y('X');
//
//        System.out.println("i1:");
//        System.out.println(i1);
//        System.out.println("i2:");
//        System.out.println(i2);
//        System.out.println("i3:");
//        System.out.println(i3);
//        System.out.println("i4:");
//        System.out.println(i4);
//        System.out.println("i5:");
//        System.out.println(i5);
//        System.out.println("v3:");
//        System.out.println(v3);
//        System.out.println("l4:");
//        System.out.println(l4);
//        System.out.println("z4:");
//        System.out.println(z4);
//        System.out.println("o4:");
//        System.out.println(o4);
//        System.out.println("l5:");
//        System.out.println(l5);
//        System.out.println("t5:");
//        System.out.println(t5);
//        System.out.println("v5:");
//        System.out.println(v5);
//        System.out.println("n:");
//        System.out.println(n);
//        System.out.println("z5:");
//        System.out.println(z5);
//        System.out.println("t4:");
//        System.out.println(t4);
//        System.out.println("p:");
//        System.out.println(p);
//        System.out.println("w:");
//        System.out.println(w);
//        System.out.println("u:");
//        System.out.println(u);
//        System.out.println("f:");
//        System.out.println(f);
//        System.out.println("x:");
//        System.out.println(x);
//        System.out.println("y:");
//        System.out.println(y);
//    }
//
//    @Test
//    void testPlaceOnBoard() {
//        Main.gameControl.Board expectBord = new Main.gameControl.Board();
//        expectBord.update(9,2,'X');
//        expectBord.update(9,1,'X');
//        expectBord.update(10,1,'X');
//        expectBord.update(11,1,'X');
//        expectBord.update(11,0,'X');
//
//        Main.gameControl.Board brd = new Main.gameControl.Board();
//        Piece_Z5 z5 = new Piece_Z5('X');
//        // set to (10, 1)
//        z5.setPieceShape(new int[][]{{9, 2}, {9, 1}, {10,1}, {11, 1}, {11, 0}});
//        z5.placeOnBoard(brd);
//        assertEquals(expectBord.toString(),brd.toString());
//    }
//
//    @Test
//    void testIsAdjacent() {
//        Main.gameControl.Board brd = new Main.gameControl.Board();
//        brd.update(9,4,'O');
//        Piece_I1 i1 = new Piece_I1('O');
//        System.out.println(brd);
//        //(8,5) is not adjacent to (9,4)
//        assertFalse(i1.isAdjacent(brd,8,5));
//        assertFalse(i1.isAdjacent(brd,10,5));
//        assertFalse(i1.isAdjacent(brd,8,3));
//        assertFalse(i1.isAdjacent(brd,10,3));
//
//        assertTrue(i1.isAdjacent(brd,9,5));
//        assertTrue(i1.isAdjacent(brd,9,3));
//        assertTrue(i1.isAdjacent(brd,10,4));
//        assertTrue(i1.isAdjacent(brd,8,4));
//
//        assertFalse(i1.isAdjacent(brd,5,5));
//
//        brd.update(0,12,'X');
//        Piece_Z5 z5 = new Piece_Z5('X');
//
//        assertFalse(z5.isAdjacent(brd,1,13));
//        assertFalse(z5.isAdjacent(brd,1,11));
//        assertTrue(z5.isAdjacent(brd,0,13));
//        assertTrue(z5.isAdjacent(brd,1,12));
//        assertTrue(z5.isAdjacent(brd,0,11));
//
//        assertFalse(z5.isAdjacent(brd,-1,11));
//
//        //this should be false as (-1, 12) is not on the board
//        //but for this signal test, we assume (-1, 12) is on the board
//        assertTrue(z5.isAdjacent(brd,-1,12));
//
//        assertFalse(z5.isAdjacent(brd,-1,13));
//
//    }
//
//    @Test
//    void testPlaceStartingPiece() {
//        Main.gameControl.Board target_Board = new Main.gameControl.Board();
//        target_Board.update(8,5,'X');
//        target_Board.update(8,4,'X');
//        target_Board.update(9,4,'X');
//        target_Board.update(10,4,'X');
//        target_Board.update(10,3,'X');
//
//        Main.gameControl.Board brd = new Main.gameControl.Board();
//        Piece_Z5 z5 = new Piece_Z5('X');
//        assertTrue(z5.placeStartingPiece(brd,9,4));
//        assertEquals(target_Board.toString(),brd.toString());
//
//        target_Board.update(3,10,'O');
//        target_Board.update(3,9,'O');
//        target_Board.update(4,9,'O');
//        target_Board.update(5,9,'O');
//        target_Board.update(5,8,'O');
//
//        Piece_Z5 z5_1 = new Piece_Z5('O');
//        assertTrue(z5_1.placeStartingPiece(brd,4,9));
//        assertEquals(target_Board.toString(),brd.toString());
//
//        Piece_Z5 z5_2 = new Piece_Z5('C');
//        assertFalse(z5_2.placeStartingPiece(brd,9,9));
//        assertEquals(target_Board.toString(),brd.toString());
//
//
//        Piece_V3 v3 = new Piece_V3('X');
//        assertFalse(v3.placeStartingPiece(brd,9,4));
//        assertEquals(target_Board.toString(),brd.toString());
//
//    }
//
//    @Test
//    void testPlaceNormalPiece() {
//        Main.gameControl.Board target_Board = new Main.gameControl.Board();
//        //z5 is on the starting point
//        target_Board.update(8,5,'X');
//        target_Board.update(8,4,'X');
//        target_Board.update(9,4,'X');
//        target_Board.update(10,4,'X');
//        target_Board.update(10,3,'X');
//        //add i3 at a corner
//        target_Board.update(7,6,'X');
//        target_Board.update(7,7,'X');
//        target_Board.update(7,8,'X');
//        System.out.println("***\n"+target_Board);
//
//        Main.gameControl.Board brd = new Main.gameControl.Board();
//        //first place z5 down
//        brd.update(8,5,'X');
//        brd.update(8,4,'X');
//        brd.update(9,4,'X');
//        brd.update(10,4,'X');
//        brd.update(10,3,'X');
//
//        Piece_I3 i3 = new Piece_I3('X');
//        assertTrue(i3.placeNormalPiece(brd,7,6));
//        assertEquals(target_Board.toString(),brd.toString());
//        System.out.println("-----\n"+brd);
//
//
//
//    }
//
//    @Test
//    void testplaceNormalPieceCheck() {
//        Main.gameControl.Board brd = new Main.gameControl.Board();
//        Piece_I1 p1 = new Piece_I1('X');
//        brd.update(9,4,'X');
//        assertFalse(p1.placeNormalPieceCheck(brd,0,0));
//        assertFalse(p1.placeNormalPieceCheck(brd,3,7));
//        assertFalse(p1.placeNormalPieceCheck(brd, 0,14));
//        assertFalse(p1.placeNormalPieceCheck(brd, 9,4));
//        assertFalse(p1.placeNormalPieceCheck(brd,9,5));
//        assertTrue(p1.placeNormalPieceCheck(brd, 10,5));
//    }
//
//    @Test
//    void testStartPieceCheck() {
//        Main.gameControl.Board brd = new Main.gameControl.Board();
//        Piece_I1 p1 = new Piece_I1('X');
//        assertTrue(p1.startPieceCheck(brd,9,4));
//        assertTrue(p1.startPieceCheck(brd,4,9));
//        assertFalse(p1.startPieceCheck(brd,0,0));
//        assertFalse(p1.startPieceCheck(brd,13,13));
//        assertFalse(p1.startPieceCheck(brd,-1,4));
//        assertFalse(p1.startPieceCheck(brd,1,14));
//        brd.update(9,4,'O');
//        assertFalse(p1.startPieceCheck(brd,9,4));
//
//        Piece_Z5 z5 = new Piece_Z5('X');
//        assertFalse(z5.startPieceCheck(brd,9,4));
//        assertTrue(z5.startPieceCheck(brd,4,9));
//        assertFalse(z5.startPieceCheck(brd,0,0));
//        assertFalse(z5.startPieceCheck(brd,13,13));
//        assertFalse(z5.startPieceCheck(brd,-1,4));
//        assertFalse(z5.startPieceCheck(brd,1,14));
//    }
//
//    @Test
//    void testOnStartingPoint() {
//        Piece_V3 v3 = new Piece_V3('X');
//        //(4. 9) is a starting point
//        assertTrue(v3.onStartingPoint(4,9));
//        //(9, 4) is a starting point
//        assertTrue(v3.onStartingPoint(9,4));
//        //(4, 10) is not a starting point
//        assertFalse(v3.onStartingPoint(4,10));
//        //(3, 9) is not a starting point
//        assertFalse(v3.onStartingPoint(3,9));
//        //(9, 6) is not a starting point
//        assertFalse(v3.onStartingPoint(9,6));
//        //(1, 4) is not a starting point
//        assertFalse(v3.onStartingPoint(1,4));
//        //(5, 5) is not a starting point
//        assertFalse(v3.onStartingPoint(5,5));
//        //(-1, 0) is not a starting point
//        assertFalse(v3.onStartingPoint(-1,0));
//    }
//
////    @Test
////    void testOnBoard() {
////        Piece_Z5 z5 = new Piece_Z5('X');
////        //point (0,0) is on the board
////        assertTrue(z5.onBoard(0,0));
////        //point (0,14) is not on the board
////        assertFalse(z5.onBoard(0,14));
////        //point (-1,13) is not on the board
////        assertFalse(z5.onBoard(-1,13));
////        //point (-1,14) is not on the board
////        assertFalse(z5.onBoard(-1,14));
////    }
//
//    @Test
//    void testAtCorner() {
//        Main.gameControl.Board brd = new Main.gameControl.Board();
//        brd.update(9,4,'O');
//        Piece_I1 i1 = new Piece_I1('O');
//        //(8,5) is at corner of (9,4)
//        assertTrue(i1.atCorner(brd,8,5));
//        assertTrue(i1.atCorner(brd,10,5));
//        assertTrue(i1.atCorner(brd,8,3));
//        assertTrue(i1.atCorner(brd,10,3));
//        assertFalse(i1.atCorner(brd,9,5));
//        assertFalse(i1.atCorner(brd,9,3));
//        assertFalse(i1.atCorner(brd,10,4));
//        assertFalse(i1.atCorner(brd,8,4));
//        assertFalse(i1.atCorner(brd,5,5));
//        brd.update(0,12,'X');
//        Piece_Z5 z5 = new Piece_Z5('X');
//        //(1,13) is at corner of (9,4)
//        assertTrue(z5.atCorner(brd,1,13));
//        assertTrue(z5.atCorner(brd,1,11));
//        assertFalse(z5.atCorner(brd,0,13));
//        assertFalse(z5.atCorner(brd,1,12));
//        assertFalse(z5.atCorner(brd,0,11));
//        assertFalse(z5.atCorner(brd,-1,11));
//        assertFalse(z5.atCorner(brd,-1,12));
//        assertFalse(z5.atCorner(brd,-1,13));
//    }
//
//    @Test
//    void testUpdatePieceShape() {
//        int[][] targetPosition1 = new int[][] {
//                {-1,0},
//                {-1, -1},
//                {0, 0}
//        };
//        Piece_V3 v3 = new Piece_V3('X');
//        //move 1 block left
//        v3.updatePieceShape(-1,0);
//        assertArrayEquals(targetPosition1,v3.getPieceShape());
//
//        int[][] targetPosition2 = new int[][] {
//                {-5,6},
//                {-5, 5},
//                {-4, 6}
//        };
//        Piece_V3 v3_1 = new Piece_V3('X');
//        //move 5 blocks left, 6 blocks up
//        v3_1.updatePieceShape(-5,6);
//        assertArrayEquals(targetPosition1,v3.getPieceShape());
//    }
//}