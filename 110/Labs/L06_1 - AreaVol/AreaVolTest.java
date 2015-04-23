
/**
 * Uses user input to calculate Area and Volume of a cylinder and sphere
 * 
 * @author Nick Alexander 
 * @version 2/22/13
 */

import java.util.Scanner;

public class AreaVolTest
{
    public static void main(String[] args)
    {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Enter the radius of the cylinder: ");
        String answer = myScanner.nextLine();
        double r = Double.parseDouble(answer);
        System.out.println("Enter the height of the cylinder: ");
        answer = myScanner.nextLine();
        double h = Double.parseDouble(answer);
        System.out.println("The surface area of the cylinder is: " + AreaVol.findCylinderSurfaceArea(r, h));
        System.out.println("The volume of the cylinder is " + AreaVol.findCylinderVolume(r, h));
        
        System.out.println("Enter the radius of the sphere: ");
        answer = myScanner.nextLine();
        double rS = Double.parseDouble(answer);
        System.out.printf("The surface area of the sphere is: %6.3f\n", AreaVol.findSphereSurfaceArea(rS));
        System.out.printf("The volume of the sphere is: %6.3f\n", AreaVol.findSphereVolume(rS));
        System.exit(0);
    }
}
