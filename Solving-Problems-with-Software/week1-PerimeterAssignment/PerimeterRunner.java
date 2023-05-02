import edu.duke.*;
import java.io.File;

public class PerimeterRunner {
    public double getPerimeter(Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints(Shape s) {
        int numPoints = 0;
        for (Point currPt : s.getPoints()) {
            numPoints = numPoints + 1;
            System.out.println(currPt);
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        double averLen = getPerimeter(s) / getNumPoints(s);
        return averLen;
    }

    public double getLargestSide(Shape s) {
        double currLargest = 0.0;
        Point prevPt = s.getLastPoint();

        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > currLargest) {
                currLargest = currDist;
            }
            prevPt = currPt;
        }
        return currLargest;
    }

    public double getLargestX(Shape s) {
        double currLargX = 0.0;
        for (Point currPt : s.getPoints()) {
            double currX = currPt.getX();
            if (currX > currLargX) {
                currLargX = currX;
            }
        }
        return currLargX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        double largestPeri = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPeri = getPerimeter(s);
            if (currPeri > largestPeri) {
                largestPeri = currPeri;
            }
        }
        return largestPeri;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;
        double largestPeri = 0.0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPeri = getPerimeter(s);
            if (currPeri > largestPeri) {
                largestPeri = currPeri;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter() {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double averLen = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of points = " + numPoints);
        System.out.println("Average Length = " + averLen);
        System.out.println("Longest side = " + largestSide);
        System.out.println("Largest X = " + largestX);
    }

    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPeri = getLargestPerimeterMultipleFiles();
        System.out.println("Largest Peri = " + largestPeri);
    }

    public void testFileWithLargestPerimeter() {
        String fileName = getFileWithLargestPerimeter();
        System.out.println("File with largest Peri is " + fileName);
    }

    public void triangle() {
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0, 0));
        triangle.addPoint(new Point(3, 0));
        triangle.addPoint(new Point(0, 4));
        for (Point p : triangle.getPoints()) {
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }

    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main(String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        // pr.testPerimeter();
        // pr.testPerimeterMultipleFiles();
        // pr.testFileWithLargestPerimeter();
        // pr.printFileNames();
        pr.triangle();
    }
}
