package tp.pr0;

public class FuncsMatematicas {

	public static int factorial(int n) {
		if(n==0 || n==1) {
			return 1;
		}
		else if(n < 0) {
			return 0;
		}
		else {
			return n*(factorial(n-1));
		}

	}
	
	public static int combinatorio(int n, int k) {
		if(k>=0&&k<=n) {
			return factorial(n)/(factorial(k)*factorial(n-k));
		}
		else {
			return 0;
		}

	}
	
	public static void main(java.lang.String[] args) {
		for (int i = 0; i < 6; ++i) {
			for (int j = 0; j <= i; ++j)
			System.out.print(FuncsMatematicas.combinatorio(i,j) + " ");
			System.out.println();
			}

	}

}
