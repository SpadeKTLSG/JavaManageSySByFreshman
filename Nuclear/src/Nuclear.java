import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Nuclear {
    int autoincre = 1;//�Զ����ɵı��
    NUclearbase database = new NUclearbase();

    //�ϲ����û��͹���Ա����
    public Nuclear() {
    }

    public int LoginUser(String ac, String pas) throws SQLException {//�û���¼
        String readSQL = "select * from person"; //��ѯȫ��
        Statement stat = database.con.createStatement(); //����Statement��������ִ��SQL���
        ResultSet res = stat.executeQuery(readSQL); //ִ��SQL���
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

    public int LoginAdmin(String ac, String pas) {//����Ա��¼
        if (ac.equals("1") && pas.equals("1"))
            return 1;
        else return 0;
    }

    public String getnews() throws SQLException { //��ȡ��Ϣ
        String readSQL = "select * from news"; //��ѯȫ��
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

    public void pushfeedback(String or, String NowUserAccount) throws SQLException { //���غ��ᱨ��,�޸�person��ǩ,�޸�nuclearing��ǩ
        System.out.println("���µ��û���������");
        String[] a = or.split(" ");
        //�ȵǼ����ϴ�
        String updateSQL2 = "update person set isUpload=?  where account=? ";
        PreparedStatement pr = database.con.prepareStatement(updateSQL2);//����PreparedStatement����
        pr.setInt(1, 1);//Ϊ��һ���ʺŸ�ֵ
        pr.setString(2, NowUserAccount);//Ϊ�ڶ����ʺŸ�ֵ
        pr.executeUpdate();

        //�ж���û��1->���ӱ���
        if (a[0].equals("1") || a[1].equals("1")) {
            System.out.println("@����Ա,��⵽�쳣�������˻�" + NowUserAccount + ",������");
            String insertSQL = "insert into nuclearlog(logID, toaccount, isSafe) values (?,?,?)";
            PreparedStatement stat = database.con.prepareStatement(insertSQL);
            stat.setInt(1, ++autoincre);
            stat.setString(2,NowUserAccount);
            stat.setInt(3, 1); //1���������
            stat.executeUpdate();

        } else {
            String insertSQL = "insert into nuclearlog(logID, toaccount, isSafe) values (?,?,?)";
            PreparedStatement stat = database.con.prepareStatement(insertSQL);
            stat.setInt(1, ++autoincre);
            stat.setString(2,NowUserAccount);
            stat.setInt(3, 0); //1���������
            stat.executeUpdate();

        }
    }

    public void Addnews(String news) throws SQLException { //����Ա��������
        System.out.println("���µ�����|֪ͨ");
        String insertSQL = "insert into news(no, text) values (?,?)";
        PreparedStatement stat = database.con.prepareStatement(insertSQL);
        stat.setInt(1, ++autoincre);
        stat.setString(2,news);
        stat.executeUpdate();
    }

    public String Showperson() throws SQLException{ //�鿴��Ա�б�
        String readSQL = "select * from person"; //��ѯȫ��
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        man.append("��� ���� �˺� ���� �ѵǼ�");
        while (res.next()) {// ת��ѧ���б�ΪString���
            int id = res.getInt(1);
            String name = res.getString(2);
            String account = res.getString(3);
            String password = res.getString(4);
           int isupload=res.getInt(5);
            man.append("\n").append(id).append("  ").append(name).append("  ").append(account).append("  ").append(password).append("  ").append(isupload);
        }
        return man.toString();
    }

    public String Showbugperson() throws SQLException{ //�鿴û��������Ա�б�
        String readSQL = "select * from person"; //��ѯȫ��
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        man.append("��� ���� �˺� ���� �ѵǼ�");
        while (res.next()) {// ת��ѧ���б�ΪString���
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

    public void personAdd(String newman) throws SQLException { //����Աͨ�����ո��ַ������Ӻ�̨����
        System.out.println("��Ա����");
        String[] a = newman.split(" ");//�洢���ܵķָ�����ַ���
        String insertSQL = "insert into person(name, account, pin, isUpload) values (?,?,?,?)";
        PreparedStatement stat = database.con.prepareStatement(insertSQL); //Ԥ����
        stat.setString(1, a[0]);
        stat.setString(2, a[1]);
        stat.setString(3, a[2]);
        stat.setInt(4, Integer.parseInt(a[3]));
        stat.executeUpdate();
    }

    public void personDelete(String oldman) throws SQLException {  //����Աɾ��һ��ѧ������
        System.out.println("ɾ��������");
        String updateSQL = "update person set personID=? where personID=?";//����һ��mysql���
        PreparedStatement ps = database.con.prepareStatement(updateSQL);//����PreparedStatement����
        ps.setString(1, "1013");//Ϊ��һ���ʺŸ�ֵ
        ps.setInt(2, Integer.parseInt(oldman));//Ϊ�ڶ����ʺŸ�ֵ
        ps.executeUpdate();

        String deleteSQL = "delete from person where personID=1013";
        PreparedStatement stmt = database.con.prepareStatement(deleteSQL); //Ԥ����
        stmt.executeUpdate(deleteSQL);
    }

    public void personChange(String oldman) throws SQLException{ //��������
        System.out.println("������һ������");
        String[] a = oldman.split(" ");//�洢���ܵķָ�����ַ���,����ΪID(Ψһȷ��)

        String updateSQL1 = "update person set name=?  where personID=? ";
        PreparedStatement ps = database.con.prepareStatement(updateSQL1);//����PreparedStatement����
        ps.setString(1, a[1]);//Ϊ��һ���ʺŸ�ֵ
        ps.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        ps.executeUpdate();

        String updateSQL2 = "update person set account=?  where personID=? ";
        PreparedStatement pr = database.con.prepareStatement(updateSQL2);//����PreparedStatement����
        pr.setString(1, a[2]);//Ϊ��һ���ʺŸ�ֵ
        pr.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        pr.executeUpdate();

        String updateSQL3 = "update person set pin=?  where personID=? ";
        PreparedStatement pp = database.con.prepareStatement(updateSQL3);//����PreparedStatement����
        pp.setString(1, a[3]);//Ϊ��һ���ʺŸ�ֵ
        pp.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        pp.executeUpdate();

        String updateSQL4 = "update person set isUpload=?  where personID=? ";
        PreparedStatement pp1 = database.con.prepareStatement(updateSQL4);//����PreparedStatement����
        pp1.setInt(1, Integer.parseInt(a[3]));//Ϊ��һ���ʺŸ�ֵ
        pp1.setInt(2, Integer.parseInt(a[0]));//Ϊ�ڶ����ʺŸ�ֵ
        pp1.executeUpdate();
    }
}
