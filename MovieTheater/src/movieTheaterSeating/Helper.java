package movieTheaterSeating;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Helper {
    private int numRows;
    private Status[][] availableSeats;
    private int reserved;
    private int totalSeats;
    private int buffer;
    private int[] seatsLeftInRow;

    public Helper(int rows, int cols) {
    	availableSeats = new Status[rows][cols];
        for (int i = 0; i < availableSeats.length; i++) {
            for (int j = 0; j < availableSeats[i].length; j++) {
            	availableSeats[i][j] = Status.AVAILABLE;
            }
        }
        numRows = rows;
        totalSeats = rows * cols;
        reserved = 0;
        seatsLeftInRow = new int[rows];
        for (int i = 0; i < seatsLeftInRow.length; i++)
            seatsLeftInRow[i] = 20;
    }

//	Reserve seats starting from middle row
    public String reserveSeats(int num) {
//    	No seats left    	
        if (totalSeats - reserved - buffer < num)
            return null;
        
//      Try to reserve seats in the same row for single reservation
        List<int[]> seatLocs = new ArrayList<>(num);
        StringBuilder result = new StringBuilder();
        int row = -1, distToMid = numRows;
        for (int i = 0; i < seatsLeftInRow.length; i++) {
            int dist = Math.abs(seatsLeftInRow.length / 2 - i);
            if (seatsLeftInRow[i] >= num && dist < distToMid) {
                row = i;
                distToMid = dist;
            }
        }


//		Split reservation in different rows if exceeds single row capacity and fit remaining in row with most capacity closest to middle
        if (row == -1) {
        	
//        	Initializing rolling back of reserved and buffer if ideal split reservation is not found
            List<int[]> rollbackReserved = new LinkedList<>();
            List<int[]> rollbackBuffered = new LinkedList<>();
            while (num > 0) {
                if (totalSeats - reserved - buffer < num) {
                    for (int[] locs: rollbackReserved) {
                    	availableSeats[locs[0]][locs[1]] = Status.AVAILABLE;
                        seatsLeftInRow[locs[0]]++;
                        reserved--;
                    }
                    for (int[] locs: rollbackBuffered) {
                    	availableSeats[locs[0]][locs[1]] = Status.AVAILABLE;
                        seatsLeftInRow[locs[0]]++;
                        buffer--;
                    }
                    return null;
                }
                int maxRow = -1, maxSpace = 0;
                distToMid = numRows;
                for (int i = 0; i < seatsLeftInRow.length; i++) {
                    int dist = Math.abs(seatsLeftInRow.length / 2 - i);
                    if (seatsLeftInRow[i] == num && dist < distToMid) {
                        maxSpace = Integer.MAX_VALUE;
                        maxRow = i;
                        distToMid = dist;
                    } else if (seatsLeftInRow[i] >= maxSpace && dist < distToMid) {
                        maxRow = i;
                        maxSpace = seatsLeftInRow[i];
                        distToMid = dist;
                    }
                }
                num -= seatsLeftInRow[maxRow];
                reserved += seatsLeftInRow[maxRow];
                seatsLeftInRow[maxRow] = 0;
                
                for (int col = 0; col < availableSeats[maxRow].length; col++) {
                    if (availableSeats[maxRow][col] == Status.AVAILABLE) {
                        seatLocs.add(new int[]{maxRow, col});
                        availableSeats[maxRow][col] = Status.RESERVED;
                    }
                }
                for (int[] loc: seatLocs) {
                    rollbackReserved.add(loc);
                    rollbackBuffered.addAll(bufferSpace(loc[0], loc[1]));
                }
            }
        } else {
            int col = 0;
            seatsLeftInRow[row] -= num;
            reserved += num;
            while (num > 0 && col < availableSeats[row].length) {
                if (availableSeats[row][col] == Status.AVAILABLE) {
                    seatLocs.add(new int[]{row, col});
                    availableSeats[row][col] = Status.RESERVED;
                    num--;
                }
                col++;
            }
        }
        for (int[] loc: seatLocs) {
        	result.append((char) (loc[0] + 65) + Integer.toString(loc[1] + 1) + ",");
            bufferSpace(loc[0], loc[1]);
        }
        result.deleteCharAt(result.length()-1);
        return result.toString();
    }

//	Create a buffer space of 3 seats and 1 row for public safety
    private List<int[]> bufferSpace(int row, int col) {
        List<int[]> buffered = new LinkedList<>();
        int[] vertical = {-1, 0, 1};
        int[] horizontal = {-3, -2, -1, 0, 1, 2, 3};
        for (int i: vertical) {
            for (int j: horizontal) {
                if (check(row + i, col + j) && (i != 0 || j != 0)) {
                    if (availableSeats[row + i][col + j] == Status.AVAILABLE) {
                    	availableSeats[row + i][col + j] = Status.BUFFER;
                        buffer++;
                        seatsLeftInRow[row + i]--;
                        buffered.add(new int[]{row + i, col + j});
                    }
                }
            }
        }
        return buffered;
    }

    private boolean check(int row, int col) {
        return row >= 0 && row < numRows && col >= 0 && col < availableSeats[row].length;
    }

    enum Status {
        AVAILABLE,
        RESERVED,
        BUFFER
    }

}

