package util;

import java.util.Iterator;

public interface IMatrix {

	/* Getters & setters */
	public int getNumRows();

	public int getNumColumns();

	public Double get(int i, int j);

	public void set(int i, int j, double value);
	
	/* Iterators */
	public Iterator<Double> getRowIterator(int row);

	public Iterator<Double> getColumnIterator(int col);
	
	/* Util */
	public String toString();
}
