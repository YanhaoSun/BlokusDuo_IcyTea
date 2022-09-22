/**
 * Group name: IcyTea
 * Members:
 *  Cathal Poon (20343243)
 *  YanHao Sun (19205434)
 *  Rubing Wang (20206497)
 */
import gameControl.Board;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

//test board class
class BoardTest {
    String baseTestBoard =
            """
                    13  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                    12  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                    11  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                    10  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                     9  ~ ~ ~ ~ * ~ ~ ~ ~ ~ ~ ~ ~ ~
                     8  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                     7  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                     6  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                     5  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                     4  ~ ~ ~ ~ ~ ~ ~ ~ ~ * ~ ~ ~ ~
                     3  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                     2  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                     1  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                     0  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                        0 1 2 3 4 5 6 7 8 910111213
                    """;



    //test if the constructor of the Main.Board class works correctly
    //test initial board with two starting points
    @Test
    void printBoard()
    {
        /*
        String test =
                "    A B C D E F G H I J K L M N\n" +
                " 0  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                " 1  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                " 2  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                " 3  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                " 4  ~ ~ ~ ~ ~ ~ ~ ~ ~ * ~ ~ ~ ~\n" +
                " 5  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                " 6  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                " 7  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                " 8  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                " 9  ~ ~ ~ ~ * ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "10  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "11  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "12  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "13  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";
        */
        Board testPrint = new Board();
        assertEquals(baseTestBoard, testPrint.toString());
        // System.out.println(testPrint);
    }


    @Test//test again if we can update another symbol in a different coordinate A0 and print it on the board
    void testUpdate() {
        /*
        String testString =
                "    A B C D E F G H I J K L M N\n" +
                " 0  & ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                " 1  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                " 2  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                " 3  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                " 4  & ~ ~ ~ ~ ~ ~ ~ ~ * ~ ~ ~ ~\n" +
                " 5  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                " 6  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                " 7  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                " 8  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                " 9  ~ ~ ~ ~ * ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "10  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "11  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "12  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n" +
                "13  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";
*/
        String testString =
                """
                        13  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                        12  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                        11  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                        10  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                         9  ~ ~ ~ ~ * ~ ~ ~ ~ ~ ~ ~ ~ ~
                         8  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                         7  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                         6  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                         5  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                         4  & ~ ~ ~ ~ ~ ~ ~ ~ * ~ ~ ~ ~
                         3  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                         2  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                         1  ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                         0  & ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
                            0 1 2 3 4 5 6 7 8 910111213
                        """;
        char ch = '&';
        Board myBoard =  new Board();

        myBoard.update(0, 4, ch);
        myBoard.update(0 , 0, ch);
        assertEquals(testString, myBoard.toString());

        assertThrows(IllegalArgumentException.class, () -> myBoard.update(-1, 0, ch));
        assertThrows(IllegalArgumentException.class, () -> myBoard.update(-1, -1, ch));
        assertThrows(IllegalArgumentException.class, () -> myBoard.update(14, 14, ch));
        assertThrows(IllegalArgumentException.class, () -> myBoard.update(14, 14, ch));
        assertThrows(IllegalArgumentException.class, () -> myBoard.update(14, 14, ch));

    }

    @Test
    void isEmptyTest() {
        Board testBoard = new Board();

        int[][] pairsA = {
                {0,0},
                {0,6},
                {3,5},
                {7,9},
                {13,13},
                {0,13},
                {11,3},
                {12,10},
                {13,0}
        };

        int[][] pairsB = {
                {7,8},
                {12,8},
                {10,8},
                {10,4},
                {1,3},
                {2,9},
                {4,3},
                {5,8}
        };


        for (int[] pair: pairsA) {
            testBoard.update(pair[0], pair[1], 'a');
        }

        for (int[] pair : pairsB){
            testBoard.update(pair[0], pair[1], 'b');
        }

        boolean flag;

        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 14; j++) {
                // stuff
                flag = true;
                int[] temp = new int[] {i, j};
                //System.out.println("temp = "+temp[j]);
                for (int[] pair : pairsA) {
                    if (Arrays.equals(pair, temp)) {
                        flag = false;
                        break;
                    }
                }

                if (flag){
                    for (int[] pair : pairsB) {
                        if (Arrays.equals(pair, temp)) {
                            flag = false;
                            break;
                        }
                    }
                }

                assertEquals(flag, testBoard.isEmpty(i, j));
            }
        }

    }


}

