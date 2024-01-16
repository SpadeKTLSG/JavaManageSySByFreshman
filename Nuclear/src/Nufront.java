import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

@SuppressWarnings("unchecked")
public class Nufront {
    private JFrame nuframe = new JFrame("防疫助手v1.0.0");
    private JPanel leftarea = new JPanel();
    private JPanel rightarea = new JPanel();
    TextArea ta = new TextArea(5, 20);
    TextArea ga = new TextArea(5, 20);
    public JTextField usernames = new JFormattedTextField();
    public JPasswordField passwords = new JPasswordField();
    String[] choose_identity = {"管理员", "用户"};
    public JComboBox identity = new JComboBox(choose_identity);
    JButton login = new JButton("访问!");


    //用户账号
    String NowUserAccount;
    int isLogin = 0; //判断登录
    public Nuclear N = new Nuclear();//后台


    public Nufront() {

        //登录:管理员-直接, 用户-直接

        //一级面板

        nuframe.pack();
        nuframe.setSize(1500, 800);
        nuframe.setLocationRelativeTo(null);
        nuframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        nuframe.setLayout(new FlowLayout(0));
        //二级面板1
        nuframe.add(leftarea);
        nuframe.add(rightarea);

        leftarea.setLayout(new GridLayout(6, 1));
        leftarea.setPreferredSize(new Dimension(400, 800));
        leftarea.setBackground(Color.LIGHT_GRAY);
        rightarea.setBackground(Color.white);
        rightarea.setPreferredSize(new Dimension(1000, 800));
        rightarea.setLayout(new GridLayout(2, 1));

        leftarea.add(identity);
        leftarea.add(usernames);
        leftarea.add(passwords);
        leftarea.add(login);

        ta.setText("获取数据中,\n请等待...");
        ta.setFont(new Font("宋体", Font.PLAIN, 40));
        rightarea.add(ta);
        ga.setFont(new Font("宋体", Font.PLAIN, 30));
        rightarea.add(ga);

        nuframe.setVisible(true);

        //! 事件触发器
        login.addActionListener(new ActionListener() {//点击进入后的操作:比对数据库现有数据,确定是否进入
            @Override
            public void actionPerformed(ActionEvent e) {
                int find_ca = identity.getSelectedIndex();//确认选择登录角色
                System.out.println(find_ca + "类型角色登录中");
                if (find_ca == 0) {
                    isLogin = N.LoginAdmin(usernames.getText(), passwords.getText());
                    if (isLogin == 1) Ad();
                }
                if (find_ca == 1) {
                    try {
                        isLogin = N.LoginUser(usernames.getText(), passwords.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (isLogin == 1) {
                        NowUserAccount = usernames.getText();//获取学生账号传到后台
                        Us();
                    }
                }
            }
        });


    }

    public void Ad() { //管理员菜单
        System.out.println("管理员已部署");
        leftarea.remove(identity);
        leftarea.remove(usernames);
        leftarea.remove(passwords);
        leftarea.remove(login);
        leftarea.setLayout(new GridLayout(8, 1));

        JButton getpersons = new JButton("获取成员列表");
        getpersons.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(getpersons);

        JButton getbugs = new JButton("未登记名单");
        getbugs.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(getbugs);

        JLabel postnu = new JLabel("    ---分割线---");
        postnu.setBackground(Color.YELLOW);
        postnu.setFont(new Font("宋体", Font.BOLD, 40));
        leftarea.add(postnu);

        JButton addnews = new JButton("增加新闻");
        addnews.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(addnews);
//----------------------------选中成员列表的时候出现----------------------------------
        JButton addman = new JButton("增加成员");
        addman.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(addman);
        addman.setVisible(false);

        JButton delman = new JButton("删除成员");
        delman.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(delman);
        delman.setVisible(false);

        JButton changeman = new JButton("修改成员");
        changeman.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(changeman);
        changeman.setVisible(false);

        leftarea.setVisible(false);
        leftarea.setVisible(true);


        //! 事件:
        addnews.addActionListener(new ActionListener() { // 增加通知
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    N.Addnews(ga.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        getbugs.addActionListener(new ActionListener() { //显示没有交报告的人
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ta.setText((N.Showbugperson()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        getpersons.addActionListener(new ActionListener() { //显示所有人
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ta.setText(N.Showperson());
                    addman.setVisible(true);
                    delman.setVisible(true);
                    changeman.setVisible(true);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        addman.addActionListener(new ActionListener() { //增加数据
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    N.personAdd(ga.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        delman.addActionListener(new ActionListener() { //删除数据
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   N.personDelete(ga.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        changeman.addActionListener(new ActionListener() { //更新数据
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    N.personChange(ga.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    public void Us() { //用户菜单
        System.out.println("用户已部署");
        leftarea.remove(identity);
        leftarea.remove(usernames);
        leftarea.remove(passwords);
        leftarea.remove(login);
        leftarea.setVisible(false);
        leftarea.setVisible(true);
        ta.setText("在下方按空格分割提交核酸与体温情况,0代表没问题,两个零代表两个都没问题,有一个1就会报警");

        JButton getnews = new JButton("获取通知");
        getnews.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(getnews);
        JButton postnu = new JButton("提交报告");
        postnu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(postnu);

        getnews.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ta.setText(N.getnews());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        postnu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    N.pushfeedback(ga.getText(), NowUserAccount);

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    @Override
    public String toString() {
        return "防疫助手v1.0.0运行中";
    }

    public static void main(String[] args) {
        Nufront nuclear = new Nufront();
        System.out.println(nuclear.toString());
    }
}
