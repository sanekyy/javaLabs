import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MyTest {

    Array2DRowRealMatrix A, B, C;

    @Before
    public void prepareMatrix(){
        double[][] matrixA = {
                {1,2,3,4,5},
                {2,3,4,5,6},
                {3,4,5,6,7},
                {4,5,6,7,8},
                {5,6,7,8,9}
        };

        double[][] matrixB = {
                {4,5,6,7,8},
                {1,2,3,4,5},
                {2,3,4,5,6},
                {3,4,5,6,7},
                {5,6,7,8,9}
        };

        double[][] matrixC = {
                {49, 64, 79, 94, 109},
                {64, 84, 104, 124, 144},
                {79, 104, 129, 154, 179},
                {94, 124, 154, 184, 214},
                {109, 144, 179, 214, 249}
        };

        A = new Array2DRowRealMatrix(matrixA);
        B = new Array2DRowRealMatrix(matrixB);
        C = new Array2DRowRealMatrix(matrixC);
    }

    @Test
    public void testConcatenate() {
        assertTrue(C.equals(A.multiply(B)));
    }
}