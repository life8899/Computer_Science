
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Simulates a Virtual Memory Manager with FIFO, LRU, and Random implementations with an Array Implementation of the
 * Page Table
 *
 * @version 11-18-2014
 *
 * @author Nick Alexander
 * @author Daniel Davis
 */
public class MemSim
{
    private static File traceFile;
    private static Page[] pageTable;
    private static String algorithm;
    private static long traceEvents;
    private static long pageFaults;
    private static long writesToDisk;

    /**
     * memsim <path-to-trace-file> <number-of-pageTable> <fifo/lru/random>
     *
     * @param args Runtime parameters
     */
    public static void main(String[] args)
    {
        try {
            traceFile = new File(args[0]);
            pageTable = new Page[Integer.parseInt(args[1])];
            algorithm = validateAlgorithm(args[2]);
            switch (algorithm) {
                case "fifo": fifo(); break;
                case "lru": lru(); break;
                case "random": random(); break;
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("Trace File Not Found");
            System.exit(-1);
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid Number of pageTable Argument - Must be a Positive Integer");
            System.exit(-2);
        } catch (IllegalArgumentException illArg) {
            System.out.println("Invalid Algorithm Argument - Must be fifo, lru, or random");
            System.exit(-3);
        }
        printStats();
        reset();
    }

    /**
     * Checks to ensure that the input algorithm is a valid algorithm (FIFO, LRU, or Random).
     * If valid, returns the algorithm name. Throws IllegalArgumentException if invalid.
     *
     * @param algorithmName Algorithm name supplied as input
     * @return Algorithm name if valid
     * @throws IllegalArgumentException if algorithm name is invalid
     *
     */
    private static String validateAlgorithm(String algorithmName) throws IllegalArgumentException
    {
        algorithmName = algorithmName.toLowerCase();
        if (algorithmName.equals("fifo") || algorithmName.equals("lru") || algorithmName.equals("random")) {
            return algorithmName;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Simulates the Fist-In, First-Out Page Replacement Policy
     *
     * @throws FileNotFoundException if trace file is not found
     *
     */
    private static void fifo() throws FileNotFoundException
    {
        PageQueue queue = new PageQueue();
        Scanner fileScanner = new Scanner(traceFile);
        while (fileScanner.hasNextLine()) {
            String[] reference = fileScanner.nextLine().split(" ");
            Page newPage = new Page(reference[0], reference[1]);
            traceEvents++;
            if(findFrame(newPage.getAddress()) == -1) {
                pageFaults++;
                int freeFrame = checkFreeFrame();
                if (freeFrame == -1) {
                    Page firstPage = queue.dequeue();
                    int firstPageIndex = findFrame(firstPage.getAddress());
                    if (pageTable[firstPageIndex].hasBeenWritten()) {
                        writesToDisk++;
                    }
                    pageTable[firstPageIndex] = newPage;
                    queue.enqueue(newPage);
                } else {
                    if (newPage.isWrite())
                        newPage.hasBeenWritten(true);
                    pageTable[freeFrame] = newPage;
                    queue.enqueue(newPage);
                }
            } else {
                if (newPage.isWrite()) {
                    pageTable[findFrame(newPage.getAddress())].hasBeenWritten(true);
                }
            }
        }
    }

    /**
     *
     * Simulates the Least Recently Used Page Replacement Policy
     */
    private static void lru() throws FileNotFoundException
    {
        Scanner fileScanner = new Scanner(traceFile);
        while (fileScanner.hasNextLine()) {
            String[] reference = fileScanner.nextLine().split(" ");
            Page newPage = new Page(reference[0], reference[1]);
            traceEvents++;
            if (findFrame(newPage.getAddress()) == -1) {
                pageFaults++;
                int freeFrame = checkFreeFrame();
                if (freeFrame == -1) {
                    int lruPageIndex = getLRUPageIndex();
                    if (pageTable[lruPageIndex].hasBeenWritten()) {
                        writesToDisk++;
                    }
                    pageTable[lruPageIndex] = newPage;
                } else {
                    if (newPage.isWrite()) {
                        newPage.hasBeenWritten(true);
                    }
                    pageTable[freeFrame] = newPage;
                }
            } else {
                int pageIndex = findFrame(newPage.getAddress());
                if (newPage.isWrite()) {
                    pageTable[pageIndex].hasBeenWritten(true);
                }
                pageTable[pageIndex].resetEventsSinceLastUsed();
            }
            for (Page page : pageTable) {
                if (page != null) {
                    page.incrementEventsSinceUsed();
                }
            }
        }
    }

    /**
     * Simulates the Random Page Replacement Policy
     */
    private static void random() throws FileNotFoundException
    {
        Scanner fileScanner = new Scanner(traceFile);
        while (fileScanner.hasNextLine()) {
            String[] refernce = fileScanner.nextLine().split(" ");
            Page newPage = new Page(refernce[0], refernce[1]);
            traceEvents++;
            if (findFrame(newPage.getAddress()) == -1) {
                pageFaults++;
                int freeFrame = checkFreeFrame();
                if (freeFrame == -1) {
                    int victimPageIndex = getRandomPageIndex();
                    if (pageTable[victimPageIndex].hasBeenWritten()) {
                        writesToDisk++;
                    }
                    pageTable[victimPageIndex] = newPage;
                } else {
                    if (newPage.isWrite()) {
                        newPage.hasBeenWritten(true);
                    }
                    pageTable[freeFrame] = newPage;
                }
            } else {
                if (newPage.isWrite()) {
                    pageTable[findFrame(newPage.getAddress())].hasBeenWritten(true);
                }
            }
        }
    }

    /**
     * Checks if any frame currently contains the target String. This allows for checking for a full
     * trace (address paired with operation) or checking for just an address
     *
     * Returns the index of the frame containing the target String or -1 if target String is not found
     *
     * @param address Address to be found
     * @return Index of frame containing target String if found, -1 if not found
     */
    private static int findFrame(long address) {
        for (int i = 0; i < pageTable.length; i++) {
            if (pageTable[i] != null) {
                if (pageTable[i].getAddress() == address) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Checks if there are any free pageTable inside the pageTable array. If so, returns the index of the first free frame
     *  or -1 if all pageTable are filled
     *
     * @return Index of the first free frame, or -1 if all pageTable are filled
     */
    private static int checkFreeFrame()
    {
        for (int i = 0; i < pageTable.length; i++) {
            if (pageTable[i] == null) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Finds the Page that has the Highest "Events Since Last Used" Count (Least Recently Used) and returns the Page
     * Table Index for that Page
     * @return Page Table Index of the Least Recently Used Page in the Page Table
     */
    private static int getLRUPageIndex()
    {
        int maxEventsSinceUsed = 0;
        int lruPageIndex = 0;
        for (int i = 0; i < pageTable.length; i++) {
            if (pageTable[i] != null && pageTable[i].getEventsSinceUsed() > maxEventsSinceUsed) {
                maxEventsSinceUsed = pageTable[i].getEventsSinceUsed();
                lruPageIndex = i;
            }
        }
        return lruPageIndex;
    }

    /**
     * Retruns a Random Page Table Index for the Random Policy
     * @return Random Page Table Index
     */
    private static int getRandomPageIndex()
    {
        Random random = new Random();
        int randomIndex;
        do {
            randomIndex = random.nextInt(pageTable.length);
        } while (pageTable[randomIndex] == null);
        return randomIndex;
    }

    /**
     * Prints the pageTable, Trace Events, Page Faults, and Writes to Disk to the console after simulation
     */
    private static void printStats()
    {
        System.out.println("Frames: " + String.valueOf(pageTable.length));
        System.out.println("Trace Events: " + String.valueOf(traceEvents));
        System.out.println("Page Faults: " + String.valueOf(pageFaults));
        System.out.println("Writes to Disk: " + String.valueOf(writesToDisk));
    }

    /**
     * Resets all the variables for consecutive executions
     */
    private static void reset()
    {
        traceFile = null;
        pageTable = null;
        algorithm = null;
        traceEvents = 0;
        pageFaults = 0;
        writesToDisk = 0;
    }
}
