package Database;

import java.sql.*;

import static java.sql.Types.NULL;

public class Edu_Data {
    public static Connection con;
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver"; //! ����
    public static final String URL = "jdbc:mysql://localhost:3306/education"; //! ���ݿ������
    public static final String USER = "root";//! ���ݿ��û���
    public static final String PASS = "2333";//! ���ݿ�����

    public Edu_Data() {
        try {
            Class.forName(DRIVER);  //@ �������ݿ�����
            System.out.println("�������ݿ������ɹ�");
            con = DriverManager.getConnection(URL, USER, PASS); //@ ��������
            System.out.println("�������ݿ�ɹ�");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void test1() throws SQLException {

//@-------------------------��----------------------------
        //ѧ��������(������)
/*
        String insertSQL = "insert into testss(ID, Name, Account, Password, Score, Home, SelectClass) values (?,?,?,?,?,?,?)"; //��ѯ����
        PreparedStatement stat = con.prepareStatement(insertSQL); //Ԥ����
        stat.setInt(1, 100); //����Ԫ��(1����ڼ�������)
        stat.setString(2, "����K");
        stat.setInt(3, 1011);
        stat.setString(4, "����");
        stat.setInt(5, 2);
        stat.setString(6, "δ֪");
        stat.setString(7, "������������");
        stat.executeUpdate();
*/


//@-------------------------ɾ----------------------------
/*

        String deleteSQL = "delete from testss where id=114";
        PreparedStatement stmt = con.prepareStatement(deleteSQL); //Ԥ����
        stmt.executeUpdate(deleteSQL);
*/


//@-------------------------��----------------------------

/*

        String updateSQL = "update testss set password=? where SelectClass=? ";//����һ��mysql���
        PreparedStatement ps = con.prepareStatement(updateSQL);//����PreparedStatement����
        ps.setString(1, "nonono");//Ϊ��һ���ʺŸ�ֵ
        ps.setInt(2, NULL);//Ϊ�ڶ����ʺŸ�ֵ
        ps.executeUpdate();
*/

//@-------------------------��----------------------------
/*
        String readSQL = "select * from testss"; //��ѯȫ��
        Statement stamt = con.createStatement(); //����Statement��������ִ��SQL���
        ResultSet res = stamt.executeQuery(readSQL); //ִ��SQL���

        while (res.next()) {
            int id = res.getInt(1);
            String name = res.getString(2);
            int account = res.getInt(3);
            System.out.println(id + "  " + name + "  " + account);
        }*/
//        res��ʼָ���ѯ�������ݱ�ĵ�һ�е���һ�С�
//        res.next()��ʹָ���ƶ�����һ�У����ķ���ֵλ�������ͣ�boolean���������һ����������ô����ֵλtrue������Ϊfalse��
//
//        ��ȡ�������ݾ�ʹ��getInt()������
//        ��ȡ�ַ������ݾ�ʹ��getString()������
//        �����еĲ����ǲ�ѯ���Ľ����ĵڼ��У����ɴ�1��ʼ��.
//
//        ������whileѭ���жԻ�ȡ�����ݽ��в�����������ӵ�ArrayList�У��������ǵ�ʹ��
//@------------
    }

    
}

