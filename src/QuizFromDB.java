import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuizFromDB {
	private String[] question;
	
	int gameLevel = 2;// ���� ����: DB���� ��Ʈ - �������� �ٸ� ����, ���� �߰� ���ĺ� ���� ���� 
	int gameType = 2;// ����Ÿ��:  DB���� ��Ʈ - �ٸ����� ����

	
	public QuizFromDB(int level, int gameType) {
		// Connection ��ü�� �ڵ��ϼ����� import�� ���� com.mysql.connection�� �ƴ�
		// java ǥ���� java.sql.Connection Ŭ������ import�ؾ� �Ѵ�.
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		this.gameLevel = level;
		this.gameType = gameType;
		String strGL = String.valueOf(gameLevel);
		
		try {
			// 1. ����̹� �ε�
			// ����̹� �������̽��� ������ Ŭ������ �ε�
			// mysql, oracle �� �� ������ ���� Ŭ���� �̸��� �ٸ���.
			// mysql�� "com.mysql.jdbc.Driver"�̸�, �̴� �ܿ�� ���� �ƴ϶� ���۸��ϸ� �ȴ�.
			// ����� ������ �����ߴ� jar ������ ���� com.mysql.jdbc ��Ű���� Driver ��� Ŭ������ �ִ�.
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. �����ϱ�
			// ����̹� �Ŵ������� Connection ��ü�� �޶�� ��û�Ѵ�.
			// Connection�� ��� ���� �ʿ��� url ����, �����縶�� �ٸ���.
			// mysql�� "jdbc:mysql://localhost/�����db�̸�" �̴�.
			String url = "jdbc:mysql://localhost/quiznew?serverTimezone=UTC";

			// @param getConnection(url, userName, password);
			// @return Connection
			conn = DriverManager.getConnection(url, "root", "asdfuiop196");
			System.out.println("���� ����");
			
			String sql = "";
			stmt = conn.createStatement();
			
			int cnt = 0;
			String value;
			question = new String[50];
			if (gameType == 1) {
				
				sql = "SELECT * from animal where idAnimal = " + strGL;

				// 5. ���� ����
				// ���ڵ���� ResultSet ��ü�� �߰��ȴ�.
				rs = stmt.executeQuery(sql);

				// 6. ������ ����ϱ�
				cnt = 0;
				while (rs.next()) {
					// ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
					// �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.
					value = rs.getString(2);
					question[cnt] = value;
					cnt++;
				}

			} else if (gameType == 2) {
				sql = "SELECT * from fruit where idFruit = " + strGL;

				// 5. ���� ����
				// ���ڵ���� ResultSet ��ü�� �߰��ȴ�.
				rs = stmt.executeQuery(sql);

				// 6. ������ ����ϱ�
				cnt = 0;

				while (rs.next()) {
					// ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
					// �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.
					value = rs.getString(2);
					question[cnt] = value;
					cnt++;
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("����: " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public String[] getQestionFromDB() {
		return question;
	}
}