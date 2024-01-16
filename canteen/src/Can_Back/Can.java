package Can_Back;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Can {
    public int order_key=1; //�����ſ�ʼ����
    public Can(int i) {
        order_key=i;
    }

    Can_base database = new Can_base();

    public String Storage() throws SQLException { //����

        String readSQL = "select * from storage"; //��ѯȫ��
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        man.append("��� ���� ����");
        while (res.next()) {
            int id = res.getInt(1);
            String name = res.getString(2);
            int quantity = res.getInt(3);
            man.append("\n").append(id).append("  ").append(name).append("  ").append(quantity);

        }
        return man.toString();
    }

    public String History() throws SQLException { //����ʷ�˵�

        String readSQL = "select * from history"; //��ѯȫ��
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        man.append("��� �۸�    ��Ʒ           ʱ��   ");
        while (res.next()) {
            int id = res.getInt(1);
            Double price=res.getDouble(2);
            String name = res.getString(3);
            String time = res.getString(4);
            man.append("\n").append(id).append("  ").append(price).append("  ").append(name).append("  ").append(time);

        }
        return man.toString();
    }

    public String Order() throws SQLException{ //��ѯ��ǰ�㵥���
        String readSQL = "select * from `order`"; //��ѯȫ��
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        man.append("��ǰ�ĵ㵥����:\n\n��λ    �۸�     ��Ʒ\n");
        while (res.next()) {
            int id = res.getInt(2);
            Double price=res.getDouble(3);
            String name = res.getString(4);
            man.append("\n").append("��"+id+"����").append("  ").append(price).append("$  ").append(name);

        }
        return man.toString();
    }

    public void Callorder(String input) throws SQLException{ //�㵥(����
        System.out.println("��һ���µĵ㵥");
        String a[]=input.split(" ");//sit,price,item
        String insertSQL = "insert into `order`(ID,sit,price,item) values (?,?,?,?)";
        PreparedStatement stat = database.con.prepareStatement(insertSQL);
        stat.setInt(1,++order_key);
        stat.setInt(2, Integer.parseInt(a[0]));
        stat.setDouble(3, Double.parseDouble(a[1]));
        stat.setString(4, a[2]);
        stat.executeUpdate();
    }

    public void Payorder(int siter) throws SQLException{ //����-ɾ��sit��������
        System.out.println("�µĽ���");
        String updateSQL = "update `order` set ID=? where sit=?";//����һ��mysql���
        PreparedStatement ps = database.con.prepareStatement(updateSQL);//����PreparedStatement����
        ps.setString(1, "1013");//Ϊ��һ���ʺŸ�ֵ
        ps.setInt(2, siter);//Ϊ�ڶ����ʺŸ�ֵ
        ps.executeUpdate();

        String deleteSQL = "delete from `order` where `ID`=1013";
        PreparedStatement stmt = database.con.prepareStatement(deleteSQL); //Ԥ����
        stmt.executeUpdate(deleteSQL);
    }

}
