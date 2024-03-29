package Back;

import Database.Edu_Data;

import java.sql.*;
import java.util.Objects;

public class Stu {
    Edu_Data database = new Edu_Data();
    public Text tool;//包含接口传递信息.

    public Stu(Text t) {//构造函数
        tool = t;
    }

    public int login_stu(int account, String password) throws SQLException {//学员登录:输入账号密码进行查询账号,并比对密码

        String readSQL = "select * from student_data"; //查询全部
        Statement stat = database.con.createStatement(); //创建Statement对象用于执行SQL语句
        ResultSet res = stat.executeQuery(readSQL); //执行SQL语句
        int ok = 0;

        while (res.next()) {
            int acc = res.getInt(3);
            if (acc == account) {
                String pas = res.getString(4);
                if (Objects.equals(pas, password))
                    ok = 1;
            }

        }

        return ok;
    }

    public String Student_Show(int flag) throws SQLException { //第一次调用
        if (flag == 0) {
            //第一次调用,message为课程信息


            String readSQL = "select * from course_data"; //查询课程全部
            Statement stamt = database.con.createStatement();
            ResultSet res = stamt.executeQuery(readSQL);

            StringBuilder man = new StringBuilder();
            man.append("编号 课名 教师号码 上课时间 地点 课时 类型 学分");
            while (res.next()) {// 转化课程列表为String语句
                int id = res.getInt(1);
                String name = res.getString(2);
                int account = res.getInt(3);
                String day = res.getString(4);
                String loc = res.getString(5);
                int week = res.getInt(6);
                String king = res.getString(7);
                int sco = res.getInt(8);
                man.append("\n").append(id).append("  ").append(name).append("  ").append(account).append("  ").append(day).append("  ").append(loc).append("  ").append(week).append("  ").append(king).append("  ").append(sco);
            }
            tool.tell(man.toString(), flag);
            return man.toString();

        } else {
            //第二次调用,message为教师信息(放下面)
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
            tool.tell(man.toString(), flag);
            return man.toString();
        }

    }

    //缺陷: 只能选一次课,一次只能选一节
    public void Student_AddClass(int classcode, int NowStudentAccount) throws SQLException {//传入课程号,学生数据增加查询课程号里的课程名字到最后一栏,而后从个人信息中确认自己选的课
        String readSQL = "select * from course_data"; //查询课程
        Statement stamt = database.con.createStatement(); //创建Statement对象用于执行SQL语句
        ResultSet res = stamt.executeQuery(readSQL); //执行SQL语句

        while (res.next()) {
            int id = res.getInt(1);
            if (classcode == id) {
                String updateSQL = "update student_data set SelectClass=? where Account=? ";//生成一条mysql语句
                PreparedStatement ps = database.con.prepareStatement(updateSQL);//创建PreparedStatement对象
                ps.setString(1, String.valueOf(id));//为第一个问号赋值
                ps.setInt(2, NowStudentAccount);//为第二个问号赋值
                ps.executeUpdate();
            }
        }
    }

    public String Student_self(int NowStudentAccount) throws SQLException { //查询自己信息全部
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
            if (account == NowStudentAccount) {
                man.append("\n").append(id).append("  ").append(name).append("  ").append(account).append("  ").append(password).append("  ").append(score).append("  ").append(home).append("  ").append(sec);
            }
        }
        tool.tell(man.toString(), 0);
        return man.toString();
    }

    public void Student_updateself(int NowStudentAccount, String addd) throws SQLException { //修改个人信息
        //更新自己信息全部(改3处)
        String[] a = addd.split(" ");//空格分割的字符串传入

        String updateSQL = "update student_data set Name=? where Account=? ";//改姓名
        PreparedStatement ps = database.con.prepareStatement(updateSQL);
        ps.setString(1, a[1]);
        ps.setInt(2, NowStudentAccount);
        ps.executeUpdate();

        String updateSQL1 = "update student_data set Password=? where Account=? ";//改密码
        PreparedStatement ps1 = database.con.prepareStatement(updateSQL1);
        ps1.setString(1, a[3]);
        ps1.setInt(2, NowStudentAccount);
        ps1.executeUpdate();

        String updateSQL2 = "update student_data set Home=? where Account=? ";//改住址
        PreparedStatement ps2 = database.con.prepareStatement(updateSQL2);
        ps2.setString(1, a[5]);
        ps2.setInt(2, NowStudentAccount);
        ps2.executeUpdate();
    }
}
