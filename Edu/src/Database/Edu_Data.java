package Database;

import java.sql.*;

import static java.sql.Types.NULL;

public class Edu_Data {
    public static Connection con;
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver"; //! 驱动
    public static final String URL = "jdbc:mysql://localhost:3306/education"; //! 数据库的名字
    public static final String USER = "root";//! 数据库用户名
    public static final String PASS = "2333";//! 数据库密码

    public Edu_Data() {
        try {
            Class.forName(DRIVER);  //@ 加载数据库驱动
            System.out.println("加载数据库驱动成功");
            con = DriverManager.getConnection(URL, USER, PASS); //@ 建立连接
            System.out.println("连接数据库成功");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void test1() throws SQLException {

//@-------------------------增----------------------------
        //学生数据增(不采用)
/*
        String insertSQL = "insert into testss(ID, Name, Account, Password, Score, Home, SelectClass) values (?,?,?,?,?,?,?)"; //查询操作
        PreparedStatement stat = con.prepareStatement(insertSQL); //预编译
        stat.setInt(1, 100); //设置元素(1代表第几个属性)
        stat.setString(2, "玄桃K");
        stat.setInt(3, 1011);
        stat.setString(4, "密码");
        stat.setInt(5, 2);
        stat.setString(6, "未知");
        stat.setString(7, "哈哈哈哈哈哈");
        stat.executeUpdate();
*/


//@-------------------------删----------------------------
/*

        String deleteSQL = "delete from testss where id=114";
        PreparedStatement stmt = con.prepareStatement(deleteSQL); //预编译
        stmt.executeUpdate(deleteSQL);
*/


//@-------------------------改----------------------------

/*

        String updateSQL = "update testss set password=? where SelectClass=? ";//生成一条mysql语句
        PreparedStatement ps = con.prepareStatement(updateSQL);//创建PreparedStatement对象
        ps.setString(1, "nonono");//为第一个问号赋值
        ps.setInt(2, NULL);//为第二个问号赋值
        ps.executeUpdate();
*/

//@-------------------------查----------------------------
/*
        String readSQL = "select * from testss"; //查询全部
        Statement stamt = con.createStatement(); //创建Statement对象用于执行SQL语句
        ResultSet res = stamt.executeQuery(readSQL); //执行SQL语句

        while (res.next()) {
            int id = res.getInt(1);
            String name = res.getString(2);
            int account = res.getInt(3);
            System.out.println(id + "  " + name + "  " + account);
        }*/
//        res初始指向查询到的数据表的第一行的上一行。
//        res.next()会使指针移动至下一行，它的返回值位布尔类型（boolean）。如果下一行有数据那么返回值位true，否则为false。
//
//        获取整形数据就使用getInt()方法。
//        获取字符串数据就使用getString()方法。
//        方法中的参数是查询到的结果表的第几列，依旧从1开始。.
//
//        可以在while循环中对获取的数据进行操作，例如添加到ArrayList中，方便我们的使用
//@------------
    }

    
}

