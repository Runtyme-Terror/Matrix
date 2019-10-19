package matrix;

import static matrix.Matrix.create;

/**
 * This abstract class partially implements the Matrix API.
 * Only the number of rows and columns are stored. A subclass
 * should create the data structure used to store matrix elements.
 *
 * @author tcolburn
 * @author David Gagliardi
 */
public abstract class AbstractMatrix implements Matrix {

    /**
     * Returns the number of rows in this matrix.
     *
     * @return the number of rows in this matrix.
     */
    @Override
    public int getNumRows() {
        return numRows;
    }

    /**
     * Returns the number of columns in this matrix.
     *
     * @return the number of columns in this matrix.
     */
    @Override
    public int getNumColumns() {
        return numColumns;
    }

    /**
     * Sets the number of rows in this matrix.
     * @param numRows the number of rows in this matrix
     * @throws MatrixException if numRows is not positive
     */
    @Override
    public void setNumRows(int numRows) {
        if (numRows <= 0) {
            throw new MatrixException(String.format("numRows (%s) must be positive", numRows));
        }
        this.numRows = numRows;
    }

    /**
     * Sets the number of columns in this matrix.
     * @param numColumns the number of columns in this matrix
     * @throws MatrixException if numColumns is not positive
     */
    @Override
    public void setNumColumns(int numColumns) {
        if (numColumns <= 0) {
            throw new MatrixException(String.format("numColumns (%s) must be positive", numColumns));
        }
        this.numColumns = numColumns;
    }

    /**
     * Creates a visual representation of this matrix as a string. This method
     * can be used for debugging. This is a template method; it uses a method
     * (get) that must be implemented by a subclass. This method overrides a
     * method in the Object class.
     *
     * @return the string representation of this matrix.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int r = 0; r < getNumRows(); r++) {
            for (int c = 0; c < getNumColumns(); c++) {
                builder.append(String.format("%6s", get(r, c)));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * Tests for equality of this matrix with another. Matrices are equal if
     * they have the same dimensions and all elements are equal by ==. This is a
     * template method; it uses a method (get) that must be implemented by a
     * subclass. This method overrides a method in the Object class, so it must
     * type check and cast its argument to the correct type.
     *
     * @param obj the other matrix to be tested for equality with this matrix
     * @return <b>true</b> if the other matrix is equal to this matrix,
     * <b>false</b> otherwise
     */
    @Override
    public boolean equals(Object obj) {
        boolean sameMatrix = true;
        int item1, item2;
    Matrix otherMatrix = (Matrix) obj;
    
    if (this.getNumRows() != otherMatrix.getNumRows() || this.getNumColumns() != otherMatrix.getNumColumns()) {
            return false;
        }
      for (int r = 0; r < getNumRows(); ++r) {
        for (int c = 0; c < getNumColumns(); ++c) {
            item1 = get(r, c);
            item2 = otherMatrix.get(r, c);
            
            if (item1 != item2) {
                sameMatrix = false;
            }
        }
      }
        return sameMatrix;
    }

    /**
     * Adds this matrix to another.
     * @param other the other matrix to add
     * @return a new matrix that is the sum of this matrix and other
     * @throws MatrixException if this matrix and the other matrix do not have
     * the same dimensions
     */
    @Override
    public Matrix add(Matrix other) {
              if (this.getNumRows() != other.getNumRows() || this.getNumColumns() != other.getNumColumns()) {
            throw new MatrixException(String.format("Not valid matrix dimensions!"));
              }
              
              int item1, item2, item3;
        
          
           for (int r = 0; r < getNumRows(); ++r) {
            for (int c = 0; c < getNumColumns(); ++c) {
                item1 = get(r, c);
                item2 = other.get(r,c);
                item3 = item1 + item2;
                other.set(r, c, item3);
            }
        }
        return other;
    }

    /**
     * Multiplies this matrix by another.
     * @param other the other matrix to multiply
     * @return a new matrix that is the product of this matrix and other
     * @throws MatrixException if the number of columns in this matrix does not
     * match the number of rows in the other
     */
    @Override
    public Matrix multiply(Matrix other) {
        if (this.getNumColumns() != other.getNumRows()) {
            throw new MatrixException(String.format("Not valid matrix dimensions!"));
              }
        int item = 0;
        
        Matrix multiMatrix = create(this.getNumRows(), other.getNumColumns() );
        
         for (int i=0; i < this.getNumRows(); ++i)
       {
           for (int j=0; j < other.getNumColumns(); ++j)
           {
               item = 0;
               for (int k=0; k < this.getNumColumns(); ++k)
                   item += (this.get(i,k))*(other.get(k,j));
               multiMatrix.set(i,j, item);
           }
           
       }
        return multiMatrix;
    }
    
    /**
     * Private instance fields follow
     */

    private int numRows;
    private int numColumns;

}