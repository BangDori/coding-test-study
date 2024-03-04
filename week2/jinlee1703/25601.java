import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static HashMap<String, Cls> clsMap = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());    // 그래프 수

		// 그래프 초기 설정
		StringTokenizer st;

		// 그래프 에지 입력
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			String subCls = st.nextToken();
			String supCls = st.nextToken();

			if (!clsMap.containsKey(subCls)) clsMap.put(subCls, new Cls());
			if (!clsMap.containsKey(supCls)) clsMap.put(supCls, new Cls());

			// 부모, 자식 설정
			clsMap.get(subCls).sup = supCls;
			clsMap.get(supCls).sub.add(subCls);
		}

		st = new StringTokenizer(br.readLine());
		String start = st.nextToken();
		String target = st.nextToken();

		// 부모, 자식 검사
		int answer = checkSub(start, target) || checkSuper(start, target) ? 1 : 0;

		System.out.println(answer);
	}

	private static boolean checkSuper(String now, String target) {
		if (now == null) return false;
		if (now.equals(target)) return true;
		return checkSuper(clsMap.get(now).sup, target);
	}

	private static boolean checkSub(String start, String target) {
		Queue<String> q = new LinkedList<>();
		q.add(start);

		while (!q.isEmpty()) {
			String now = q.poll();
			for (String cls : clsMap.get(now).sub) {
				if (cls.equals(target)) return true;

				q.add(cls);
			}
		}

		return false;
	}
}

class Cls {
	String sup;
	List<String> sub;

	Cls() {
		this.sup = null;
		this.sub = new ArrayList<>();
	}

	public String toString() {
		return "{ sup: " + sup + ", sub: " + sub +" }";
	}
}