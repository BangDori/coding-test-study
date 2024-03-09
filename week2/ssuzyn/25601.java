import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 25601 {
	
	static HashMap<String, ArrayList<String>> tree;
	
	public static boolean casting(String A, String B) {
		if(A.equals(B)) return true;
		
		if(!tree.containsKey(A)) return false;
		
		for(String next: tree.get(A)) {
			if(casting(next, B)) return true;
		}
		
		return false;
	}
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		tree = new HashMap<>();
		StringTokenizer st;
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String parent = st.nextToken();
			
			if(!tree.containsKey(child)) {
				tree.put(child,  new ArrayList<>());
			}
			tree.get(child).add(parent);
		}
		
		st = new StringTokenizer(br.readLine());
		String A = st.nextToken();
		String B = st.nextToken();
		
		if(casting(A, B) || casting(B, A)) System.out.println("1");
		else System.out.println("0");

	}

}
