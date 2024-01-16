package Back;

import Database.Edu_Data;

import java.sql.*;


public class Admin {//����Ա��
    Edu_Data database = new Edu_Data();
    public Text tool;//�����ӿڴ�����Ϣ.

    public Admin(Text t) {//���캯��
        tool = t;
    }

    public int login_admin(int account, String password) {//��������:�˺�1,����1;
        if (account == 1 && password.equals("1"))
            return 1;
        else return 0;
    }

    public String ManageStudent_Show() throws SQLException { //����Ա��ȡѧ��Դ����.
        String readSQL = "select * from student_data"; //��ѯȫ��
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        man.append("��� ����  �˺�  ����   ѧ��           סַ             ��ѡ��");
        while (res.next()) {// ת��ѧ���б�ΪString���
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

    public void ManageStudent_Add(String newman) throws SQLException { //����Աͨ�����ո��ַ������Ӻ�̨����
        String[] a = newman.split(" ");//�洢���ܵķָ�����ַ���
        String insertSQL = "insert into student_data(ID, Name, Account, Password, Score, Home, SelectClass) values (?,?,?,?,?,?,?)"; //��ѯ����
        PreparedStatement stat = database.con.prepareStatement(insertSQL); //Ԥ����
        stat.setInt(1, Integer.parseInt(a[0])); //����Ԫ��(1����ڼ�������)
        stat.setString(2, a[1]);
        stat.setInt(3, Integer.parseInt(a[2]));
        stat.setString(4, a[3]);
        stat.setDouble(5, Double.parseDouble(a[4]));
        stat.setString(6, a[5]);
        stat.setString(7, a[6]);
        stat.executeUpdate();
    }


    public String ManageTeacher_Show() throws SQLException { //����Ա��ȡ��ʦԴ����.
        String readSQL = "select * from teacher_data"; //��ѯȫ��
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        man.append("��� ����  �˺�  ����  �Ա�  ��������  ��������  ��������  ѧ��  ְ��  ѧԺ");
        while (res.next()) {// ת��ѧ���б�ΪString���
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

    public void ManageTeacher_Add(String newman) throws SQLException { //����Աͨ�����ո��ַ������Ӻ�̨����
        String[] a = newman.split(" ");//�洢���ܵķָ�����ַ���
        String insertSQL = "insert into teacher_data(ID, Name, Account, Password, Sex, Birthdate, Workdate,LearnBackground,Occupation,College) values (?,?,?,?,?,?,?,?,?,?)"; //��ѯ����
        PreparedStatement stat = database.con.prepareStatement(insertSQL); //Ԥ����
        stat.setInt(1, Integer.parseInt(a[0])); //����Ԫ��(1����ڼ�������)
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

    public void ManageStudent_Update(String oldman) throws SQLException { //����Ա����ѧ������
        String[] a = oldman.split(" ");//�洢���ܵķָ�����ַ���,����ΪID(Ψһȷ��)

        String updateSQL1 = "update student_data set Name=?  where ID=? ";
        PreparedStatement ps = database.con.prepareStatement(updateSQL1);//����PreparedStatement����
        ps.setString(1, a[1]);//Ϊ��һ���ʺŸ�ֵ
        ps.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        ps.executeUpdate();

        String updateSQL2 = "update student_data set Account=?  where ID=? ";
        PreparedStatement pr = database.con.prepareStatement(updateSQL2);//����PreparedStatement����
        pr.setString(1, a[2]);//Ϊ��һ���ʺŸ�ֵ
        pr.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        pr.executeUpdate();

        String updateSQL3 = "update student_data set Password=?  where ID=? ";
        PreparedStatement pp = database.con.prepareStatement(updateSQL3);//����PreparedStatement����
        pp.setString(1, a[3]);//Ϊ��һ���ʺŸ�ֵ
        pp.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        pp.executeUpdate();

        String updateSQL4 = "update student_data set Score=?  where ID=? ";
        PreparedStatement aa = database.con.prepareStatement(updateSQL4);//����PreparedStatement����
        aa.setString(1, a[4]);//Ϊ��һ���ʺŸ�ֵ
        aa.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        aa.executeUpdate();

        String updateSQL5 = "update student_data set Home=?  where ID=? ";
        PreparedStatement bb = database.con.prepareStatement(updateSQL5);//����PreparedStatement����
        bb.setString(1, a[5]);//Ϊ��һ���ʺŸ�ֵ
        bb.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        bb.executeUpdate();

        String updateSQL6 = "update student_data set SelectClass=?  where ID=? ";
        PreparedStatement cc = database.con.prepareStatement(updateSQL6);//����PreparedStatement����
        cc.setString(1, a[6]);//Ϊ��һ���ʺŸ�ֵ
        cc.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        cc.executeUpdate();

    }

    public void ManageTeacher_Update(String oldman) throws SQLException { //����Ա���½�ʦ����
        String[] a = oldman.split(" ");//�洢���ܵķָ�����ַ���,����ΪID(Ψһȷ��)

        String updateSQL1 = "update teacher_data set Name=?  where ID=? ";
        PreparedStatement ps = database.con.prepareStatement(updateSQL1);//����PreparedStatement����
        ps.setString(1, a[1]);//Ϊ��һ���ʺŸ�ֵ
        ps.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        ps.executeUpdate();

        String updateSQL2 = "update teacher_data set Account=?  where ID=? ";
        PreparedStatement pr = database.con.prepareStatement(updateSQL2);//����PreparedStatement����
        pr.setString(1, a[2]);//Ϊ��һ���ʺŸ�ֵ
        pr.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        pr.executeUpdate();

        String updateSQL3 = "update teacher_data set Password=?  where ID=? ";
        PreparedStatement pp = database.con.prepareStatement(updateSQL3);//����PreparedStatement����
        pp.setString(1, a[3]);//Ϊ��һ���ʺŸ�ֵ
        pp.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        pp.executeUpdate();

        String updateSQL4 = "update teacher_data set Sex=?  where ID=? ";
        PreparedStatement aa = database.con.prepareStatement(updateSQL4);//����PreparedStatement����
        aa.setString(1, a[4]);//Ϊ��һ���ʺŸ�ֵ
        aa.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        aa.executeUpdate();

        String updateSQL5 = "update teacher_data set Birthdate=?  where ID=? ";
        PreparedStatement bb = database.con.prepareStatement(updateSQL5);//����PreparedStatement����
        bb.setString(1, a[5]);//Ϊ��һ���ʺŸ�ֵ
        bb.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        bb.executeUpdate();

        String updateSQL6 = "update teacher_data set Workdate=?  where ID=? ";
        PreparedStatement cc = database.con.prepareStatement(updateSQL6);//����PreparedStatement����
        cc.setString(1, a[6]);//Ϊ��һ���ʺŸ�ֵ
        cc.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        cc.executeUpdate();

        String updateSQL7 = "update teacher_data set LearnBackground=?  where ID=? ";
        PreparedStatement dd = database.con.prepareStatement(updateSQL7);//����PreparedStatement����
        dd.setString(1, a[7]);//Ϊ��һ���ʺŸ�ֵ
        dd.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        dd.executeUpdate();

        String updateSQL8 = "update teacher_data set Occupation=?  where ID=? ";
        PreparedStatement ee = database.con.prepareStatement(updateSQL8);//����PreparedStatement����
        ee.setString(1, a[8]);//Ϊ��һ���ʺŸ�ֵ
        ee.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        ee.executeUpdate();

        String updateSQL9 = "update teacher_data set College=?  where ID=? ";
        PreparedStatement ff = database.con.prepareStatement(updateSQL9);//����PreparedStatement����
        ff.setString(1, a[9]);//Ϊ��һ���ʺŸ�ֵ
        ff.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        ff.executeUpdate();
    }

    public void ManageStudent_Delete(String oldman) throws SQLException {  //����Աɾ��һ��ѧ������

        String updateSQL = "update student_data set ID=? where ID=?";//����һ��mysql���
        PreparedStatement ps = database.con.prepareStatement(updateSQL);//����PreparedStatement����
        ps.setString(1, "1013");//Ϊ��һ���ʺŸ�ֵ
        ps.setInt(2, Integer.parseInt(oldman));//Ϊ�ڶ����ʺŸ�ֵ
        ps.executeUpdate();


        String deleteSQL = "delete from student_data where `ID`=1013";
        PreparedStatement stmt = database.con.prepareStatement(deleteSQL); //Ԥ����
        stmt.executeUpdate(deleteSQL);
    }

    public void ManageTeacher_Delete(String oldman) throws SQLException {  //����Աɾ��һ����ʦ����

        String updateSQL = "update teacher_data set ID=? where ID=?";//����һ��mysql���
        PreparedStatement ps = database.con.prepareStatement(updateSQL);//����PreparedStatement����
        ps.setString(1, "1013");//Ϊ��һ���ʺŸ�ֵ
        ps.setInt(2, Integer.parseInt(oldman));//Ϊ�ڶ����ʺŸ�ֵ
        ps.executeUpdate();


        String deleteSQL = "delete from teacher_data where `ID`=?";
        PreparedStatement stmt = database.con.prepareStatement(deleteSQL); //Ԥ����
        stmt.executeUpdate(deleteSQL);
    }

    public void ManageStudent_password(String pas) throws SQLException { //�޸�ѧ������

        String A[] = pas.split(" ");
        String updateSQL = "update student_data set Password=? where ID=?";//����һ��mysql���
        PreparedStatement ps = database.con.prepareStatement(updateSQL);//����PreparedStatement����
        ps.setString(1, A[1]);//Ϊ��һ���ʺŸ�ֵ
        ps.setInt(2, Integer.parseInt(A[0]));//ID��ǰ
        ps.executeUpdate();
    }

    public void ManageTeacher_password(String pas) throws SQLException { //�޸���ʦ����

        String A[] = pas.split(" ");
        String updateSQL = "update teacher_data set Password=? where ID=?";//����һ��mysql���
        PreparedStatement ps = database.con.prepareStatement(updateSQL);//����PreparedStatement����
        ps.setString(1, A[1]);//Ϊ��һ���ʺŸ�ֵ
        ps.setInt(2, Integer.parseInt(A[0]));//ID��ǰ
        ps.executeUpdate();
    }

}
