import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Simulates a Virtual Memory Manager with FIFO, LRU, and Random implementations with a HashMap Implementation of the
 * Page Table
 *
 * @version 11-18-2014
 *
 * @author Nick Alexander
 * @author Daniel Davis
 */
public class HashedMemSim
{
    private static File traceFile;
    private static BoundedHashMap<Long, Page> pageTable;
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
            pageTable = new BoundedHashMap<>(Integer.parseInt(args[1]));
            algorithm = validateAlgorithm(args[2]);
            switch (algorithm) {
                case "fifo": fifo(); break;
                case "lru": lru(); break;
                case "random": random(); break;
            }
        } catch (FileNotFoundException fnf) { // Trace File Not Found
            System.out.println("Trace File Not Found");
            System.exit(-1);
        } catch (NumberFormatException nfe) { // Invalid Number of pageTable
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
     * Simultes the First In, First Out Page Replacement Policy
     * @throws FileNotFoundException If the trace file cannot be found
     */
    private static void fifo() throws FileNotFoundException
    {
        PageQueue queue = new PageQueue();
        Scanner fileScanner = new Scanner(traceFile);
        while (fileScanner.hasNextLine()) {
            String[] reference = fileScanner.nextLine().split(" ");
            Page newPage = new Page(reference[0], reference[1]);
            traceEvents++;
            if (pageTable.containsKey(newPage.getAddress())) { // Page in a Frame
                if (newPage.isWrite()) {
                    pageTable.get(newPage.getAddress()).hasBeenWritten(true);
                }
            } else { // Page Not in a Frame
                pageFaults++;
                if (pageTable.isFull()) { // No Empty Frames
                    Page victimPage = queue.dequeue();
                    if (victimPage.hasBeenWritten()) {
                        writesToDisk++;
                    }
                    pageTable.remove(victimPage.getAddress());
                    pageTable.add(newPage.getAddress(), newPage);
                    queue.enqueue(newPage);
                } else { // Empty Frames
                    if (newPage.isWrite()) {
                        newPage.hasBeenWritten(true);
                    }
                    pageTable.add(newPage.getAddress(), newPage);
                    queue.enqueue(newPage);
                }
            }
        }
    }

    /**
     * Simultes the Least Recently Used Page Replacement Policy
     * @throws FileNotFoundException If the trace file cannot be found
     */
    private static void lru() throws FileNotFoundException
    {
        Scanner fileScanner = new Scanner(traceFile);
        while (fileScanner.hasNextLine()) {
            String[] reference = fileScanner.nextLine().split(" ");
            Page newPage = new Page(reference[0], reference[1]);
            traceEvents++;
            if (pageTable.containsKey(newPage.getAddress())) { // Page in a Frame
                if (newPage.isWrite()) {
                    pageTable.get(newPage.getAddress()).hasBeenWritten(true);
                }
                pageTable.get(newPage.getAddress()).resetEventsSinceLastUsed();
            } else { // Page not in a Frame
                pageFaults++;
                if (pageTable.isFull()) { // No Empty Frame
                    Page victimPage = findLRU();
                    if (victimPage.hasBeenWritten()) {
                        writesToDisk++;
                    }
                    pageTable.remove(victimPage.getAddress());
                    pageTable.add(newPage.getAddress(), newPage);
                } else { //Empty Frame
                    if (newPage.isWrite()) {
                        newPage.hasBeenWritten(true);
                    }
                    pageTable.add(newPage.getAddress(), newPage);
                }
            }
            for (Page page : pageTable.values()) {
                if (page != null) {
                    page.incrementEventsSinceUsed();
                }
            }
        }
    }

    /**
     * Calculates which Page has the Most Events since it was Last Used in the Page Table. This is equivalent to the
     * least recently used Page.
     *
     * @return Reference to the Least Recently Used Page in the Page Table
     */
    private static Page findLRU()
    {
        Page victimPage = new Page("0", "R");
        for (Page page : pageTable.values())
        {
            if (page.getEventsSinceUsed() > victimPage.getEventsSinceUsed()) {
                victimPage = page;
            }
        }
        return victimPage;
    }


    /**
     * Simultes the Random Page Replacement Policy
     *
     * @throws FileNotFoundException If the trace file cannot be found
     */
    private static void random() throws FileNotFoundException
    {
        Random random = new Random();
        Scanner fileScanner = new Scanner(traceFile);
        while (fileScanner.hasNextLine()) {
            String[] reference = fileScanner.nextLine().split(" ");
            Page newPage = new Page(reference[0], reference[1]);
            traceEvents++;
            if (pageTable.containsKey(newPage.getAddress())) {
                if (newPage.isWrite()) {
                    pageTable.get(newPage.getAddress()).hasBeenWritten(true);
                }
            } else {
                pageFaults++;
                if (pageTable.isFull()) {
                    int victimPageIndex = random.nextInt(pageTable.size());
                    Page victimPage = (Page)pageTable.values().toArray()[victimPageIndex];
                    if (victimPage.hasBeenWritten()) {
                        writesToDisk++;
                    }
                    pageTable.remove(victimPage.getAddress());
                } else {
                    if (newPage.isWrite()) {
                        newPage.hasBeenWritten(true);
                    }
                    pageTable.add(newPage.getAddress(), newPage);
                }
            }
        }
    }

    /**
     * Prints the pageTable, Trace Events, Page Faults, and Writes to Disk to the console after simulation
     */
    private static void printStats()
    {
        System.out.println("Frames: " + String.valueOf(pageTable.getCapacity()));
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