import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUclearbase {
    public static Connection con;
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver"; //! ����
    public static final String URL = "jdbc:mysql://localhost:3306/nuclear"; //! ���ݿ������
    public static final String USER = "root";//! ���ݿ��û���
    public static final String PASS = "2333";//! ���ݿ�����

    public NUclearbase() {
        try {
            Class.forName(DRIVER);  //@ �������ݿ�����
            System.out.println("�������ݿ������ɹ�");
            con = DriverManager.getConnection(URL, USER, PASS); //@ ��������
            System.out.println("�������ݿ�ɹ�");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}