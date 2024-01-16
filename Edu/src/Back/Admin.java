package Back;

import Database.Edu_Data;

import java.sql.*;


public class Admin {//管理员类
    Edu_Data database = new Edu_Data();
    public Text tool;//包含接口传递信息.

    public Admin(Text t) {//构造函数
        tool = t;
    }

    public int login_admin(int account, String password) {//本地密码:账号1,密码1;
        if (account == 1 && password.equals("1"))
            return 1;
        else return 0;
    }

    public String ManageStudent_Show() throws SQLException { //管理员获取学生源数据.
        String readSQL = "select * from student_data"; //查询全部
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        man.append("编号 姓名  账号  密码   学分           住址             已选课");
        while (res.next()) {// 转化学生列表为String语句
            int id = res.getInt(1);
            String name = res.getString(2);
            int account = res.getInt(3);
            String password = res.getString(4);
            Double score = res.getDouble(5);
            String home = res.getString(6);
            String sec = res.getString(7);
            man.append("\n").append(id).append("  ").append(name).append("  ").append(account).append("  ").append(password).append("  ").append(score).append("  ").append(home).append("  ").append(sec);
        }
        tool.tell(man.toString(), 1);
        return man.toString();
    }

    public void ManageStudent_Add(String newman) throws SQLException { //管理员通过带空格字符串增加后台数据
        String[] a = newman.split(" ");//存储接受的分割开来的字符串
        String insertSQL = "insert into student_data(ID, Name, Account, Password, Score, Home, SelectClass) values (?,?,?,?,?,?,?)"; //查询操作
        PreparedStatement stat = database.con.prepareStatement(insertSQL); //预编译
        stat.setInt(1, Integer.parseInt(a[0])); //设置元素(1代表第几个属性)
        stat.setString(2, a[1]);
        stat.setInt(3, Integer.parseInt(a[2]));
        stat.setString(4, a[3]);
        stat.setDouble(5, Double.parseDouble(a[4]));
        stat.setString(6, a[5]);
        stat.setString(7, a[6]);
        stat.executeUpdate();
    }


    public String ManageTeacher_Show() throws SQLException { //管理员获取教师源数据.
        String readSQL = "select * from teacher_data"; //查询全部
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        man.append("编号 姓名  账号  密码  性别  出生日期  工作日期  教育经历  学历  职称  学院");
        while (res.next()) {// 转化学生列表为String语句
            int id = res.getInt(1);
            String name = res.getString(2);
            int account = res.getInt(3);
            String password = res.getString(4);
            String sex = res.getString(5);
            String birthdate = res.getString(6);
            String workdate = res.getString(7);
            String lbg = res.getString(8);
            String occupation = res.getString(9);
            String college = res.getString(10);

            man.append("\n").append(id).append("  ").append(name).append("  ").append(account).append("  ").append(password).append("  ").append(sex).append("  ").append(birthdate).append("  ").append(workdate).append("  ").append(lbg).append("  ").append(occupation).append("  ").append(college);
        }
        tool.tell(man.toString(), 2);
        return man.toString();
    }

    public void ManageTeacher_Add(String newman) throws SQLException { //管理员通过带空格字符串增加后台数据
        String[] a = newman.split(" ");//存储接受的分割开来的字符串
        String insertSQL = "insert into teacher_data(ID, Name, Account, Password, Sex, Birthdate, Workdate,LearnBackground,Occupation,College) values (?,?,?,?,?,?,?,?,?,?)"; //查询操作
        PreparedStatement stat = database.con.prepareStatement(insertSQL); //预编译
        stat.setInt(1, Integer.parseInt(a[0])); //设置元素(1代表第几个属性)
        stat.setString(2, a[1]);
        stat.setInt(3, Integer.parseInt(a[2]));
        stat.setString(4, a[3]);
        stat.setString(5, a[4]);
        stat.setString(6, a[5]);
        stat.setString(7, a[6]);
        stat.setString(8, a[7]);
        stat.setString(9, a[8]);
        stat.setString(10, a[9]);
        stat.executeUpdate();
    }

    public void ManageStudent_Update(String oldman) throws SQLException { //管理员更新学生数据
        String[] a = oldman.split(" ");//存储接受的分割开来的字符串,索引为ID(唯一确认)

        String updateSQL1 = "update student_data set Name=?  where ID=? ";
        PreparedStatement ps = database.con.prepareStatement(updateSQL1);//创建PreparedStatement对象
        ps.setString(1, a[1]);//为第一个问号赋值
        ps.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        ps.executeUpdate();

        String updateSQL2 = "update student_data set Account=?  where ID=? ";
        PreparedStatement pr = database.con.prepareStatement(updateSQL2);//创建PreparedStatement对象
        pr.setString(1, a[2]);//为第一个问号赋值
        pr.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        pr.executeUpdate();

        String updateSQL3 = "update student_data set Password=?  where ID=? ";
        PreparedStatement pp = database.con.prepareStatement(updateSQL3);//创建PreparedStatement对象
        pp.setString(1, a[3]);//为第一个问号赋值
        pp.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        pp.executeUpdate();

        String updateSQL4 = "update student_data set Score=?  where ID=? ";
        PreparedStatement aa = database.con.prepareStatement(updateSQL4);//创建PreparedStatement对象
        aa.setString(1, a[4]);//为第一个问号赋值
        aa.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        aa.executeUpdate();

        String updateSQL5 = "update student_data set Home=?  where ID=? ";
        PreparedStatement bb = database.con.prepareStatement(updateSQL5);//创建PreparedStatement对象
        bb.setString(1, a[5]);//为第一个问号赋值
        bb.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        bb.executeUpdate();

        String updateSQL6 = "update student_data set SelectClass=?  where ID=? ";
        PreparedStatement cc = database.con.prepareStatement(updateSQL6);//创建PreparedStatement对象
        cc.setString(1, a[6]);//为第一个问号赋值
        cc.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        cc.executeUpdate();

    }

    public void ManageTeacher_Update(String oldman) throws SQLException { //管理员更新教师数据
        String[] a = oldman.split(" ");//存储接受的分割开来的字符串,索引为ID(唯一确认)

        String updateSQL1 = "update teacher_data set Name=?  where ID=? ";
        PreparedStatement ps = database.con.prepareStatement(updateSQL1);//创建PreparedStatement对象
        ps.setString(1, a[1]);//为第一个问号赋值
        ps.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        ps.executeUpdate();

        String updateSQL2 = "update teacher_data set Account=?  where ID=? ";
        PreparedStatement pr = database.con.prepareStatement(updateSQL2);//创建PreparedStatement对象
        pr.setString(1, a[2]);//为第一个问号赋值
        pr.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        pr.executeUpdate();

        String updateSQL3 = "update teacher_data set Password=?  where ID=? ";
        PreparedStatement pp = database.con.prepareStatement(updateSQL3);//创建PreparedStatement对象
        pp.setString(1, a[3]);//为第一个问号赋值
        pp.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        pp.executeUpdate();

        String updateSQL4 = "update teacher_data set Sex=?  where ID=? ";
        PreparedStatement aa = database.con.prepareStatement(updateSQL4);//创建PreparedStatement对象
        aa.setString(1, a[4]);//为第一个问号赋值
        aa.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        aa.executeUpdate();

        String updateSQL5 = "update teacher_data set Birthdate=?  where ID=? ";
        PreparedStatement bb = database.con.prepareStatement(updateSQL5);//创建PreparedStatement对象
        bb.setString(1, a[5]);//为第一个问号赋值
        bb.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        bb.executeUpdate();

        String updateSQL6 = "update teacher_data set Workdate=?  where ID=? ";
        PreparedStatement cc = database.con.prepareStatement(updateSQL6);//创建PreparedStatement对象
        cc.setString(1, a[6]);//为第一个问号赋值
        cc.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        cc.executeUpdate();

        String updateSQL7 = "update teacher_data set LearnBackground=?  where ID=? ";
        PreparedStatement dd = database.con.prepareStatement(updateSQL7);//创建PreparedStatement对象
        dd.setString(1, a[7]);//为第一个问号赋值
        dd.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        dd.executeUpdate();

        String updateSQL8 = "update teacher_data set Occupation=?  where ID=? ";
        PreparedStatement ee = database.con.prepareStatement(updateSQL8);//创建PreparedStatement对象
        ee.setString(1, a[8]);//为第一个问号赋值
        ee.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        ee.executeUpdate();

        String updateSQL9 = "update teacher_data set College=?  where ID=? ";
        PreparedStatement ff = database.con.prepareStatement(updateSQL9);//创建PreparedStatement对象
        ff.setString(1, a[9]);//为第一个问号赋值
        ff.setInt(2, Integer.parseInt(a[0]));//为第二个问号赋值
        ff.executeUpdate();
    }

    public void ManageStudent_Delete(String oldman) throws SQLException {  //管理员删除一条学生数据

        String updateSQL = "update student_data set ID=? where ID=?";//生成一条mysql语句
        PreparedStatement ps = database.con.prepareStatement(updateSQL);//创建PreparedStatement对象
        ps.setString(1, "1013");//为第一个问号赋值
        ps.setInt(2, Integer.parseInt(oldman));//为第二个问号赋值
        ps.executeUpdate();


        String deleteSQL = "delete from student_data where `ID`=1013";
        PreparedStatement stmt = database.con.prepareStatement(deleteSQL); //预编译
        stmt.executeUpdate(deleteSQL);
    }

    public void ManageTeacher_Delete(String oldman) throws SQLException {  //管理员删除一条老师数据

        String updateSQL = "update teacher_data set ID=? where ID=?";//生成一条mysql语句
        PreparedStatement ps = database.con.prepareStatement(updateSQL);//创建PreparedStatement对象
        ps.setString(1, "1013");//为第一个问号赋值
        ps.setInt(2, Integer.parseInt(oldman));//为第二个问号赋值
        ps.executeUpdate();


        String deleteSQL = "delete from teacher_data where `ID`=?";
        PreparedStatement stmt = database.con.prepareStatement(deleteSQL); //预编译
        stmt.executeUpdate(deleteSQL);
    }

    public void ManageStudent_password(String pas) throws SQLException { //修改学生密码

        String A[] = pas.split(" ");
        String updateSQL = "update student_data set Password=? where ID=?";//生成一条mysql语句
        PreparedStatement ps = database.con.prepareStatement(updateSQL);//创建PreparedStatement对象
        ps.setString(1, A[1]);//为第一个问号赋值
        ps.setInt(2, Integer.parseInt(A[0]));//ID在前
        ps.executeUpdate();
    }

    public void ManageTeacher_password(String pas) throws SQLException { //修改老师密码

        String A[] = pas.split(" ");
        String updateSQL = "update teacher_data set Password=? where ID=?";//生成一条mysql语句
        PreparedStatement ps = database.con.prepareStatement(updateSQL);//创建PreparedStatement对象
        ps.setString(1, A[1]);//为第一个问号赋值
        ps.setInt(2, Integer.parseInt(A[0]));//ID在前
        ps.executeUpdate();
    }

}
