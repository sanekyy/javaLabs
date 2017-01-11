import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMatrixMultiply {

    Array2DRowRealMatrix A, B, C, D, E;

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

        double[][] matrixE = {
                {49, 79, 94, 109},
                {64, 104, 124, 144},
                {79, 129, 154, 179},
                {94, 154, 184, 214}
        };

        A = new Array2DRowRealMatrix(matrixA);
        B = new Array2DRowRealMatrix(matrixB);
        C = new Array2DRowRealMatrix(matrixC);
        D = new Array2DRowRealMatrix();
        E = new Array2DRowRealMatrix(matrixE);
    }

    @Test
    public void testMultiply() {
        assertTrue(C.equals(A.multiply(B)));
    }

    @Test
    public void testMultiplyEmpty(){
        try{
            C = A.multiply(D);
        } catch (DimensionMismatchException e){
            assertTrue(e.getMessage().equals("5 != 0"));
        }
    }

    @Test
    public void testMultiplyDifferentSize(){
        try{
            C = A.multiply(E);
        } catch (DimensionMismatchException e){
            assertTrue(e.getMessage().equals("5 != 4"));
        }
    }


}