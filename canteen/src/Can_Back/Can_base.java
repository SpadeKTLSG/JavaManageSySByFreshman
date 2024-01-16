package Can_Back;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Can_base {
    public static Connection con;
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver"; //! 驱动
    public static final String URL = "jdbc:mysql://localhost:3306/canteen"; //! 数据库的名字
    public static final String USER = "root";//! 数据库用户名
    public static final String PASS = "2333";//! 数据库密码

    public Can_base() {
        try {
            Class.forName(DRIVER);  //@ 加载数据库驱动
            System.out.println("加载数据库驱动成功");
            con = DriverManager.getConnection(URL, USER, PASS); //@ 建立连接
            System.out.println("连接数据库成功");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
