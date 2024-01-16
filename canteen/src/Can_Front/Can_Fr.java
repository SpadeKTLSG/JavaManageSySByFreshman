package Can_Front;

import Can_Back.Can;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

@SuppressWarnings("unchecked")
public class Can_Fr {
    private JFrame canframe = new JFrame("点单小助手v1.0.0");
    private JPanel leftarea = new JPanel();
    private JPanel rightarea = new JPanel();
    TextArea ta = new TextArea(5, 20);
    TextArea ga = new TextArea(5, 20);

    //服务员
    JButton order_jb = new JButton("当前点单");
    JButton history_jb = new JButton("历史账单");
    JButton storage_jb = new JButton("库存信息");

    //顾客
    JButton call_jb = new JButton("点单");
    JButton pay_jb = new JButton("结账");

    public Can canteen = new Can(2);//目前1,2桌还没买单,没买单的账单在Order数据库
    //买单了的传输到history数据库,同时删除原来的数据



    Can_Fr() {

        //一级面板

        canframe.pack();
        canframe.setSize(1500, 800);
        canframe.setLocationRelativeTo(null);
        canframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        canframe.setLayout(new FlowLayout(0));
        //二级面板1
        canframe.add(leftarea);
        canframe.add(rightarea);

        leftarea.setLayout(new GridLayout(6, 1));
        leftarea.setPreferredSize(new Dimension(400, 800));
        leftarea.setBackground(Color.LIGHT_GRAY);
        rightarea.setBackground(Color.white);
        rightarea.setPreferredSize(new Dimension(1000, 800));
        rightarea.setLayout(new GridLayout(2, 1));

        JButton ad = new JButton("服务员入口");
        ad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton co = new JButton("顾客入口");
        co.setCursor(new Cursor(Cursor.HAND_CURSOR));

        leftarea.add(ad);
        leftarea.add(co);

        ta.setText("获取数据中,\n请等待...");
        ta.setFont(new Font("宋体", Font.PLAIN, 40));
        rightarea.add(ta);
        ga.setFont(new Font("宋体", Font.PLAIN, 30));
        rightarea.add(ga);

        canframe.setVisible(true);

        //! 事件触发器
        ad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftarea.remove(ad);
                leftarea.remove(co);
                System.out.println("服务员已部署");
                Ad();//进入次级
            }
        });

        co.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftarea.remove(ad);
                leftarea.remove(co);
                System.out.println("客户已部署");
                Co();//进入次级
            }
        });

    }

    void Ad() {
        order_jb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        history_jb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        storage_jb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(order_jb);
        leftarea.add(history_jb);
        leftarea.add(storage_jb);
        canframe.setVisible(false);
        canframe.setVisible(true);

        //! 事件触发器
        order_jb.addActionListener(new ActionListener() { //查询点单情况
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ta.setText(canteen.Order());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        storage_jb.addActionListener(new ActionListener() { //查询库存
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ta.setText(canteen.Storage());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        history_jb.addActionListener(new ActionListener() { //查询历史账单
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ta.setText(canteen.History());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    void Co() {
        call_jb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pay_jb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(call_jb);
        leftarea.add(pay_jb);
        canframe.setVisible(false);
        canframe.setVisible(true);
        ta.setText("若点单,请在下方输入座位,金额,商品,以空格分隔!\n若结账,请点击结账,自动扣款\n");

        //! 事件触发器
        call_jb.addActionListener(new ActionListener() {//增加点单
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    canteen.Callorder(ga.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        pay_jb.addActionListener(new ActionListener() {//删除点单
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    canteen.Payorder(Integer.parseInt(ga.getText()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    @Override
    public String toString() {
        return "点单管理系统v1.0.0运行中";
    }

    public static void main(String[] args) {
        Can_Fr cancanneed = new Can_Fr();
        System.out.println(cancanneed.toString());
    }
}
