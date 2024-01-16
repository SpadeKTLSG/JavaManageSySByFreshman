import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Nuclear {
    int autoincre = 1;//自动生成的编号
    NUclearbase database = new NUclearbase();

    //合并了用户和管理员的类
    public Nuclear() {
    }

    public int LoginUser(String ac, String pas) throws SQLException {//用户登录
        String readSQL = "select * from person"; //查询全部
        Statement stat = database.con.createStatement(); //创建Statement对象用于执行SQL语句
        ResultSet res = stat.executeQuery(readSQL); //执行SQL语句
        int ok = 0;

        while (res.next()) {
            String a = res.getString(3);
            if (Objects.equals(ac, a)) {
                String pp = res.getString(4);
                if (Objects.equals(pas, pp))
                    ok = 1;
            }
        }
        return ok;
    }

    public int LoginAdmin(String ac, String pas) {//管理员登录
        if (ac.equals("1") && pas.equals("1"))
            return 1;
        else return 0;
    }

    public String getnews() throws SQLException { //获取消息
        String readSQL = "select * from news"; //查询全部
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        while (res.next()) {
            int id = res.getInt(1);
            String name = res.getString(2);
            man.append("\n").append(id).append("  ").append(name);
        }
        return man.toString();
    }

    public void pushfeedback(String or, String NowUserAccount) throws SQLException { //返回核酸报告,修改person标签,修改nuclearing标签
        System.out.println("有新的用户报告生成");
        String[] a = or.split(" ");
        //先登记已上传
        String updateSQL2 = "update person set isUpload=?  where account=? ";
        PreparedStatement pr = database.con.prepareStatement(updateSQL2);//创建PreparedStatement对象
        pr.setInt(1, 1);//为第一个问号赋值
        pr.setString(2, NowUserAccount);//为第二个问号赋值
        pr.executeUpdate();

        //判断有没有1->增加报告
        if (a[0].equals("1") || a[1].equals("1")) {
            System.out.println("@管理员,检测到异常数据于账户" + NowUserAccount + ",请留意");
            String insertSQL = "insert into nuclearlog(logID, toaccount, isSafe) values (?,?,?)";
            PreparedStatement stat = database.con.prepareStatement(insertSQL);
            stat.setInt(1, ++autoincre);
            stat.setString(2,NowUserAccount);
            stat.setInt(3, 1); //1代表出问题
            stat.executeUpdate();

        } else {
            String insertSQL = "insert into nuclearlog(logID, toaccount, isSafe) values (?,?,?)";
            PreparedStatement stat = database.con.prepareStatement(insertSQL);
            stat.setInt(1, ++autoincre);
            stat.setString(2,NowUserAccount);
            stat.setInt(3, 0); //1代表出问题
            stat.executeUpdate();

        }
    }

    public void Addnews(String news) throws SQLException { //管理员增加数据
        System.out.println("有新的新闻|通知");
        String insertSQL = "insert into news(no, text) values (?,?)";
        PreparedStatement stat = database.con.prepareStatement(insertSQL);
        stat.setInt(1, ++autoincre);
        stat.setString(2,news);
        stat.executeUpdate();
    }

    public String Showperson() throws SQLException{ //查看成员列表
        String readSQL = "select * from person"; //查询全部
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        man.append("编号 姓名 账号 密码 已登记");
        while (res.next()) {// 转化学生列表为String语句
            int id = res.getInt(1);
            String name = res.getString(2);
            String account = res.getString(3);
            String password = res.getString(4);
           int isupload=res.getInt(5);
            man.append("\n").append(id).append("  ").append(name).append("  ").append(account).append("  ").append(password).append("  ").append(isupload);
        }
        return man.toString();
    }

    public String Showbugperson() throws SQLException{ //查看没有填核酸成员列表
        String readSQL = "select * from person"; //查询全部
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        man.append("编号 姓名 账号 密码 已登记");
        while (res.next()) {// 转化学生列表为String语句
            int id = res.getInt(1);
            String name = res.getString(2);
            String account = res.getString(3);
            String password = res.getString(4);
            int isupload=res.getInt(5);
            if(isupload==0) {
                man.append("\n").append(id).append("  ").append(name).append("  ").append(account).append("  ").append(password).append("  ").append(isupload);
            }
        }
        return man.toString();
    }

    public void personAdd(String newman) throws SQLException { //管理员通过带空格字符串增加后台数据
        System.out.println("成员增加");
        String[] a = newman.split(" ");//存储接受的分割开来的字符串
        String insertSQL = "insert into person(name, account, pin, isUpload) values (?,?,?,?)";
        PreparedStatement stat = database.con.prepareStatement(insertSQL); //预编译
        stat.setString(1, a[0]);
        stat.setString(2, a[1]);
        stat.setString(3, a[2]);
        stat.setInt(4, Integer.parseInt(a[3]));
        stat.executeUpdate();
    }

    public void personDelete(String oldman) throws SQLException {  //管理员删除一条学生数据
        System.out.println("删除了数据");
        String updateSQL = "update person set personID=? where personID=?";//生成一条mysql语句
        PreparedStatement ps = database.con.prepareStatement(updateSQL);//创建PreparedStatement对象
        ps.setString(1, "1013");//为第一个问号赋值
        ps.setInt(2, Integer.parseInt(oldman));//为第二个问号赋值
        ps.executeUpdate();

        String deleteSQL = "delete from person where personID=1013";
        PreparedStatement stmt = database.con.prepareStatement(deleteSQL); //预编译
        stmt.executeUpdate(deleteSQL);
    }

    public void personChange(String oldman) throws SQLException{ //更新数据
        System.out.println("更新了一条数据");
        String[] a = oldman.split(" ");//存储接受的分割开来的字符串,索引为ID(唯一确认)

        String updateSQL1 = "update person set name=?  where personID=? ";
        PreparedStatement ps = database.con.prepareStatement(updateSQL1);//创建PreparedStatement对象
        ps.setString(1, a[1]);//为第一个问号赋值
        ps.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        ps.executeUpdate();

        String updateSQL2 = "update person set account=?  where personID=? ";
        PreparedStatement pr = database.con.prepareStatement(updateSQL2);//创建PreparedStatement对象
        pr.setString(1, a[2]);//为第一个问号赋值
        pr.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        pr.executeUpdate();

        String updateSQL3 = "update person set pin=?  where personID=? ";
        PreparedStatement pp = database.con.prepareStatement(updateSQL3);//创建PreparedStatement对象
        pp.setString(1, a[3]);//为第一个问号赋值
        pp.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        pp.executeUpdate();

        String updateSQL4 = "update person set isUpload=?  where personID=? ";
        PreparedStatement pp1 = database.con.prepareStatement(updateSQL4);//创建PreparedStatement对象
        pp1.setInt(1, Integer.parseInt(a[3]));//为第一个问号赋值
        pp1.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        pp1.executeUpdate();
    }
}
