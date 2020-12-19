import java.util.*;

/*
 * Backend of Game LCS
 */
class AlgorithmLCS {
	// Function to find LCS of String X[0..m-1] and Y[0..n-1]
	public static String LCS(String X, String Y, int m, int n, int[][] T) {
		// return empty string if we have reached the end of
		// either sequence
		if (m == 0 || n == 0) {
			return new String();
		}

		// if last character of X and Y matches
		if (X.charAt(m - 1) == Y.charAt(n - 1)) {
			// append current character (X[m-1] or Y[n-1]) to LCS of
			// substring X[0..m-2] and Y[0..n-2]
			return LCS(X, Y, m - 1, n - 1, T) + X.charAt(m - 1);
		}

		// else when the last character of X and Y are different

		// if top cell of current cell has more value than the left
		// cell, then drop current character of String X and find LCS
		// of substring X[0..m-2], Y[0..n-1]

		if (T[m - 1][n] > T[m][n - 1]) {
			return LCS(X, Y, m - 1, n, T);
		} else {
			// if left cell of current cell has more value than the top
			// cell, then drop current character of String Y and find LCS
			// of substring X[0..m-1], Y[0..n-2]

			return LCS(X, Y, m, n - 1, T);
		}
	}

	// Function to fill lookup table by finding the length of LCS
	// of substring X[0..m-1] and Y[0..n-1]
	public static void LCSLength(String X, String Y, int m, int n, int[][] T) {
		// fill the lookup table in bottom-up manner
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				// if current character of X and Y matches
				if (X.charAt(i - 1) == Y.charAt(j - 1)) {
					T[i][j] = T[i - 1][j - 1] + 1;
				}
				// else if current character of X and Y don't match
				else {
					T[i][j] = Integer.max(T[i - 1][j], T[i][j - 1]);
				}
			}
		}
	}
}

class QuestionGenerator {
	private int level;
	private int gameType;

	private String[] questionOriginal;
	private String[] question;
	private String answer;//useranswer

	Set<Character> charSetFromQuestion = new HashSet<Character>();
	Set<Character> charSetAlphabet = new HashSet<Character>();
	Set<Character> charSetAlphabetExceptQuestion = new HashSet<Character>();

	public QuestionGenerator(int level, int gameType) {
		this.level = level;
		this.gameType = gameType;
		charSetAlphabet.addAll(Arrays.asList(new Character[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 'x', 't', 'u', 'v', 'w', 'x', 'y', 'z' }));
		setQuestion(level);
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setQuestion(int level) {
		int charLength = 0;// 레벨별 문제에 섞어줄 알페벳들 길이
		char[] charArray;

		if (level == 1)// easy
			charLength = 3;
		else if (level == 2)// hard
			charLength = 5;
		/*
		 * else if (level == 3) charLength = 7;
		 */
		// db에서 불러오기 question[] = 불러온 주제, 레벨
		QuizFromDB qdb = new QuizFromDB(level, gameType);
		String[] qarr1 = qdb.getQestionFromDB();
		String[] qarr2 = new String[50];
		String qRawVal = "";

		int len = 0;
		for (int i = 0; i < qarr1.length; i++) {
			if (qarr1[i] != null)
				len++;
		}
		question = new String[len];
		questionOriginal = new String[len];
		int[] noDupli = new int[len + 1];

		Random r = new Random();

		for (int i = 0; i < len; i++) // 숫자 len개를 뽑기위한 for문
		{
			noDupli[i] = r.nextInt(len); // 0~len숫자중 랜덤으로 하나를 뽑아 noDupli[0]~noDupli[len]에 저장
			for (int j = 0; j < i; j++) // 중복제거를 위한 for문

				if (noDupli[i] == noDupli[j]) {
					i--;
				}
		}

		int n;
		for (int i = 0; i < len; i++) {
			n = noDupli[i];
			qarr2[i] = qarr1[n];
		}
		
		char[] convert = null;
		
		for (int k = 0; k < len; k++) {

			qRawVal = qarr2[k];

			this.questionOriginal[k] = qRawVal;

			char[] qRawArr = qRawVal.toCharArray();

			for (int i = 0; i < qRawArr.length; i++) {
				charSetFromQuestion.add(qRawArr[i]);
			}

			// 불러온거 처리 - 알파벳 뭐뭐있는지 판별 - qusetion, charSetFromQuestion에는 제대로 문제 들어가있는상태
			charSetAlphabetExceptQuestion.addAll(charSetAlphabet);
			charSetAlphabetExceptQuestion.removeAll(charSetFromQuestion);
			// 랜덤처리. charSetAlphabetExceptQuestion list에는 랜덤추출된 알파벳만 남음

			// list = string + set 형태로 만들기

			List<Character> saveRandom = new ArrayList<>(charSetAlphabetExceptQuestion);
			List<Character> targetList = new ArrayList<>();
			// list 랜덤 처리후 다시 넣기

			Random rn = new Random();
			char c;
			int idx;

			for (int i = 0; i < charLength; i++) {
				idx = rn.nextInt(saveRandom.size());
				c = saveRandom.get(idx);
				targetList.add(c);
			}

			charArray = qRawVal.toCharArray();

			List<Character> qRawList = new ArrayList<>();

			for (int i = 0; i < charArray.length; i++) {
				qRawList.add(charArray[i]);
			}

			targetList.addAll(qRawList);

			// mix
			Collections.shuffle(targetList);

			convert = new char[targetList.size()];

			for (int i = 0; i < targetList.size(); i++) {
				convert[i] = targetList.get(i);
			}

			question[k] = String.valueOf(convert);
			
			charSetAlphabetExceptQuestion.clear();
			charSetFromQuestion.clear();
			charSetAlphabet.addAll(Arrays.asList(new Character[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
					'l', 'm', 'n', 'o', 'p', 'q', 'r', 'x', 't', 'u', 'v', 'w', 'x', 'y', 'z' }));
		}
		
		
	}

	public String getQuestion(int cnt) {
		return question[cnt];
	}

	public String getQuestionOriginal(int cnt) {
		return questionOriginal[cnt];
	}

	public String getAnswer() {
		return answer;
	}
}
