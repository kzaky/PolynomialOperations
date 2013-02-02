
public class Polynomial {

	private double[] coeff;
	private int n;
	
	public Polynomial(double[] coeff){
		n= coeff.length;
		this.coeff=new double[n];
		System.arraycopy(coeff,0,this.coeff,0,n);	
	}
	
	//Calculate Epsilon
	private float epsilon(){
		float E = 1.0f;
		do{
			E /= 2.0f;
		}while ((float)(1.0 + E/2.0) != 1.0);
		return E;
	}
	
	public double[] getCoeff(){
		double[] coefficients = new double[n];
		System.arraycopy(coefficients, 0, coefficients, 0, n);
		return coefficients;
	}
	
	//Horner's Algorithm Implementation for Polynomial Evaluation
	public double evaluate(double x){
		double poly = coeff[n-1];
		for (int i=n-2; i>=0; i--)
			poly = coeff[i] + (x*poly);
		return poly;
	}
	
	//Polynomial Addition
	public Polynomial add(Polynomial P){
		double[] sol;
		double[] x = P.getCoeff();
		
		if (x.length >= n){
			sol = new double[x.length];
			for (int i = 0; i < n; i++)
				sol[i] = x[i] + coeff[i];
			for (int i = n; i < x.length; i++)
				sol[i] = x[i];
		}else{
			sol = new double[n];
			for (int i = 0; i < x.length; i++)
				sol[i] = x[i] + coeff[i];
			for (int i = x.length; i < n; i++)
				sol[i] = coeff[i];
		}
		
		return new Polynomial(sol);
	}
	
	//Newton-Raphson's Implementation for Polynomial Evaluation
	public double sqrt(double x){
		double poly = evaluate(x);
		
		if(poly==0)
			return 0.0;
		else if(poly<0)
			return -1.0;
		
		double E = (double)epsilon();
		double sol = poly;
		while ( sol*sol-poly > E)
			sol -= (sol*sol-poly)/(2*sol);
		
		return sol;	
	}
	
	//Polynomial Multiply
	public Polynomial multiply (Polynomial P){
		double[] sol;
		double[] x = P.getCoeff();
		if (x.length>=n){
			sol = new double[n];
			for (int i=0; i<n; i++)
				sol[i] = x[i]*coeff[i];
		}
		else{
			sol = new double[x.length];
			for (int i=0; i<x.length; i++)
				sol[i]= x[i]*coeff[i];
		}
		return new Polynomial(sol);
	}
	
	//Polynomial Differentiation
		public Polynomial diff(){
			double[] sol = new double[n-1];
			for (int i = 1; i < n; i++)
				sol[i-1] = coeff[i]*i;
			return new Polynomial(sol);
		}
		
	//Polynomial Integration
	public double integrate (double x, double y){
		double[] coefficients = new double[n+1];
		coefficients[0] = 0.0;
		for (int i = 1; i <= n; i++)
			coefficients[i] = coeff[i-1]/i;
		
		Polynomial sol = new Polynomial(coefficients);
		return sol.evaluate(y) - sol.evaluate(x);	
	}
}
