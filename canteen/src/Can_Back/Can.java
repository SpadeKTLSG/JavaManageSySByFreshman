package Can_Back;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Can {
    public int order_key=1; //订单号开始序列
    public Can(int i) {
        order_key=i;
    }

    Can_base database = new Can_base();

    public String Storage() throws SQLException { //查库存

        String readSQL = "select * from storage"; //查询全部
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        man.append("编号 名称 数量");
        while (res.next()) {
            int id = res.getInt(1);
            String name = res.getString(2);
            int quantity = res.getInt(3);
            man.append("\n").append(id).append("  ").append(name).append("  ").append(quantity);

        }
        return man.toString();
    }

    public String History() throws SQLException { //查历史账单

        String readSQL = "select * from history"; //查询全部
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        man.append("编号 价格    商品           时间   ");
        while (res.next()) {
            int id = res.getInt(1);
            Double price=res.getDouble(2);
            String name = res.getString(3);
            String time = res.getString(4);
            man.append("\n").append(id).append("  ").append(price).append("  ").append(name).append("  ").append(time);

        }
        return man.toString();
    }

    public String Order() throws SQLException{ //查询当前点单情况
        String readSQL = "select * from `order`"; //查询全部
        Statement stamt = database.con.createStatement();
        ResultSet res = stamt.executeQuery(readSQL);

        StringBuilder man = new StringBuilder();
        man.append("当前的点单序列:\n\n桌位    价格     商品\n");
        while (res.next()) {
            int id = res.getInt(2);
            Double price=res.getDouble(3);
            String name = res.getString(4);
            man.append("\n").append("第"+id+"号桌").append("  ").append(price).append("$  ").append(name);

        }
        return man.toString();
    }

    public void Callorder(String input) throws SQLException{ //点单(新增
        System.out.println("有一笔新的点单");
        String a[]=input.split(" ");//sit,price,item
        String insertSQL = "insert into `order`(ID,sit,price,item) values (?,?,?,?)";
        PreparedStatement stat = database.con.prepareStatement(insertSQL);
        stat.setInt(1,++order_key);
        stat.setInt(2, Integer.parseInt(a[0]));
        stat.setDouble(3, Double.parseDouble(a[1]));
        stat.setString(4, a[2]);
        stat.executeUpdate();
    }

    public void Payorder(int siter) throws SQLException{ //结账-删除sit索引订单
        System.out.println("新的结账");
        String updateSQL = "update `order` set ID=? where sit=?";//生成一条mysql语句
        PreparedStatement ps = database.con.prepareStatement(updateSQL);//创建PreparedStatement对象
        ps.setString(1, "1013");//为第一个问号赋值
        ps.setInt(2, siter);//为第二个问号赋值
        ps.executeUpdate();

        String deleteSQL = "delete from `order` where `ID`=1013";
        PreparedStatement stmt = database.con.prepareStatement(deleteSQL); //预编译
        stmt.executeUpdate(deleteSQL);
    }

}
