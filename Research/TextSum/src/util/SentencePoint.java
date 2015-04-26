package util;

import java.util.Comparator;

public class SentencePoint implements Comparator<SentencePoint>, Comparable<SentencePoint>
{
    private int x;
    private int y;

    public SentencePoint(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public String toString()
    {
        return "(" + this.x + ", " + this.y + ")";
    }

    @Override
    public int compareTo(SentencePoint p2)
    {
        int compare = 0;
        int xCompVal = Integer.compare(this.x, p2.getX());
        int yCompVal = Integer.compare(this.y, p2.getY());
        if (xCompVal == 0 && yCompVal == 0) {
            compare = 0;
        } else if (xCompVal == 0) {
            compare = yCompVal;
        } else {
            compare = xCompVal;
        }
        return compare;
    }

    @Override
    public int compare(SentencePoint p1, SentencePoint p2)
    {
        int compare = 0;
        int xCompVal = Integer.compare(p1.getX(), p2.getX());
        int yCompVal = Integer.compare(p1.getY(), p2.getY());
        if (xCompVal == 0 && yCompVal == 0) {
            compare = 0;
        } else if (xCompVal == 0) {
            compare = yCompVal;
        } else {
            compare = xCompVal;
        }
        return compare;
    }

    @Override
    public boolean equals(Object obj) {
        SentencePoint p2 = (SentencePoint)obj;
        if (this.x == p2.getX() && this.y == p2.getY())
            return true;
        else
            return false;
    }
}