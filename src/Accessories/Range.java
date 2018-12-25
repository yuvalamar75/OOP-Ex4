package Accessories;

/**
 * this class represents Range and it implements RangeN (see Javadoc there).
 * it calculates the distance, relation and value in relation 
 * @author YuvalAmar and DvirHacohen
 *
 */
public class Range implements RangeIN {
	double min;
	double max;
	double relation;

	/**
	 * 
	 * @param min get the min value
	 * @param max get the maximun value
	 */
	public Range(double min, double max) {
		this.min=min;
		this.max=max;
	}
	
	public Range(int min, int max) {
		this.min=min;
		this.max=max;
	}
	
	
	@Override
	public double distance() {
		return max-min;

	}
	
	@Override
	public double relation (double x) {
		relation = (x-min)/distance();
		return relation;
	}
	
	@Override
	public double  getval(double relation) {

		return (min + relation*distance());
	}
	public int  getvalI(double relation) {

		return (int) (min + relation*distance());
	}

	
	@Override
	public boolean isIn(double number) {

		return (number>min && number <max);
	}
	
	
	public double getMin() {
		return min;
	}


	public double getMax() {
		return max;
	}


	public double getRelation() {
		return relation;
	}


}
