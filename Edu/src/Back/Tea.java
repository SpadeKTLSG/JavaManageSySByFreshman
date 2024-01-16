package Back;

import Database.Edu_Data;

import java.sql.*;
import java.util.Objects;

public class Tea {
    Edu_Data database = new Edu_Data();
    public Text tool;//�����ӿڴ�����Ϣ.

    public Tea(Text t) {//���캯��
        tool = t;
    }

    public int login_tea(int account, String password) throws SQLException {//��ʦ��¼:�����˺�������в�ѯ�˺�,���ȶ�����

        String readSQL = "select * from teacher_data"; //��ѯȫ��
        Statement stat = database.con.createStatement(); //����Statement��������ִ��SQL���
        ResultSet res = stat.executeQuery(readSQL); //ִ��SQL���
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

    public String Teacher_Show() throws SQLException {
        String readSQL = "select * from course_data"; //��ѯ�γ�ȫ��
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        man.append("��� ���� ��ʦ���� �Ͽ�ʱ�� �ص� ��ʱ ���� ѧ��");
        while (res.next()) {// ת���γ��б�ΪString���
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
        tool.tell(man.toString(), 0);
        return man.toString();
    }

    public String Teacher_self(int NowTeacherAccount) throws SQLException { //��ѯ�Լ���Ϣȫ��
        String readSQL = "select * from teacher_data"; //��ѯȫ��
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        man.append("��� ����  �˺�  ����  �Ա�  ��������  ��������  ��������  ѧ��  ְ��  ѧԺ");
        while (res.next()) {
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

            if (account == NowTeacherAccount) {
                man.append("\n").append(id).append("  ").append(name).append("  ").append(account).append("  ").append(password).append("  ").append(sex).append("  ").append(birthdate).append("  ").append(workdate).append("  ").append(lbg).append("  ").append(occupation).append("  ").append(college);
            }
        }
        tool.tell(man.toString(), 0);
        return man.toString();
    }

    public void Teacher_updateself(int NowTeacherAccount, String addd) throws SQLException { //�޸ĸ�����Ϣ
        //�����Լ���Ϣ
        String[] a = addd.split(" ");//�ո�ָ���ַ�������

        String updateSQL = "update teacher_data set Name=? where Account=? ";//������
        PreparedStatement ps = database.con.prepareStatement(updateSQL);
        ps.setString(1, a[1]);
        ps.setInt(2, NowTeacherAccount);
        ps.executeUpdate();

        String updateSQL1 = "update  teacher_data set Password=? where Account=? ";//������
        PreparedStatement ps1 = database.con.prepareStatement(updateSQL1);
        ps1.setString(1, a[3]);
        ps1.setInt(2, NowTeacherAccount);
        ps1.executeUpdate();

        String updateSQL2 = "update  teacher_data set Birthdate=? where Account=? ";//������
        PreparedStatement ps2 = database.con.prepareStatement(updateSQL2);
        ps2.setString(1, a[5]);
        ps2.setInt(2, NowTeacherAccount);
        ps2.executeUpdate();
        
    }
}
