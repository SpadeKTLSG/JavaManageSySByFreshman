package Front;

import Back.Admin;
import Back.Text;
import Back.Stu;
import Back.Tea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

@SuppressWarnings("unchecked")
public class Edu_Front implements Text {
    private JFrame edu = new JFrame("教务系统 ");
    private JPanel leftarea = new JPanel();
    private JPanel rightarea = new JPanel();

    //!本地登录;创建本地对象实例,根据角色选择调用相应方法操作数据库
    public Admin Ad = new Admin(this);
    public Stu St = new Stu(this);
    public Tea Te = new Tea(this);
    //登录控件:
    public JTextField usernames = new JFormattedTextField();
    public JPasswordField passwords = new JPasswordField();
    String[] choose_identity = {"管理", "学生", "教师"};
    public JComboBox identity = new JComboBox(choose_identity);
    JLabel welcome = new JLabel("教务系统v0.0.1");
    JLabel note = new JLabel("选择登入角色后输入账号密码!");
    JButton login = new JButton("访问!");
    //全局控制变量
    public int character = 0;//1-学生,2-教师,3-管理员
    public int isLogin = 0;
    public int isExit = 0;
    public String message = "";

    public int ad_flag = 0;//页面控制变量

    //帮助储存当前学生和老师的账号
    public int NowStudentAccount = 0;
    public int NowTeacherAccount = 0;

    @Override
    public void tell(String sth, int flag) {
        message = sth;
        ad_flag = flag;
    }

    Edu_Front() {//! 运行时候先运行这个,然后根据进入的系统进入不同的子函数
        //一级面板
        edu.setVisible(true);
        edu.pack();
        edu.setSize(1500, 800);
        edu.setLocationRelativeTo(null);
        edu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        edu.setLayout(new FlowLayout(0));
        //二级面板1
        edu.add(leftarea);
        edu.add(rightarea);

        leftarea.setLayout(new GridLayout(9, 1));
        leftarea.setPreferredSize(new Dimension(400, 800));
        leftarea.setBackground(Color.LIGHT_GRAY);

        leftarea.add(welcome);
        leftarea.add(identity);
        leftarea.add(usernames);
        leftarea.add(passwords);
        leftarea.add(login);
        leftarea.add(note);

        //? 登录按钮元素

        welcome.setFont(new Font("宋体", Font.BOLD, 20));
        welcome.setForeground(Color.red);

        note.setFont(new Font("宋体", Font.BOLD, 20));
        note.setForeground(Color.red);

        login.setFont(new Font("宋体", Font.BOLD, 20));
        login.setForeground(Color.red);
        login.setBorder(BorderFactory.createLineBorder(Color.blue));//设置边框
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.addActionListener(new ActionListener() {//点击进入后的操作:比对数据库现有数据,确定是否进入
            @Override
            public void actionPerformed(ActionEvent e) {
                int find_ca = identity.getSelectedIndex();//确认选择登录角色
                System.out.println(find_ca + "类型角色登录中");
                if (find_ca == 0) {
                    isLogin = Ad.login_admin(Integer.parseInt(usernames.getText()), passwords.getText());
                    if (isLogin == 1) Edu_administer();
                }
                if (find_ca == 1) {
                    try {
                        isLogin = St.login_stu(Integer.parseInt(usernames.getText()), passwords.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (isLogin == 1) {
                        NowStudentAccount = Integer.parseInt(usernames.getText());//获取学生账号传到后台
                        Edu_student();
                    }
                }
                if (find_ca == 2) {
                    try {
                        isLogin = Te.login_tea(Integer.parseInt(usernames.getText()), passwords.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    if (isLogin == 1) {
                        NowTeacherAccount = Integer.parseInt(usernames.getText());//获取教师账号传到后台
                        Edu_teacher();
                    }
                }
            }
        });

        //二级面板2

        rightarea.setBackground(Color.white);
        rightarea.setPreferredSize(new Dimension(1000, 800));
        rightarea.setLayout(new GridLayout(2, 1));
    }

    void Edu_student() {//学生前端页面控件设计
        System.out.println("学员已部署");
        leftarea.remove(welcome);
        leftarea.remove(identity);
        leftarea.remove(usernames);
        leftarea.remove(passwords);
        leftarea.remove(login);
        leftarea.remove(note);


        //学生控件生成:
        JButton st_stu = new JButton("查看课程,教师信息");
        st_stu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton st_add = new JButton("选课");  //按钮:提交选课课程编号
        st_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton st_pas = new JButton("修改个人信息");  //按钮:提交个人信息
        st_pas.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel divide = new JLabel("-------分割线--------");
        divide.setHorizontalAlignment(SwingConstants.CENTER);
        divide.setFont(new Font("宋体", Font.PLAIN, 25));

        JButton st_addclass = new JButton("提交选课课程编号");
        st_pas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton st_updateself = new JButton("提交个人信息");
        st_updateself.setCursor(new Cursor(Cursor.HAND_CURSOR));

        leftarea.add(st_stu);
        leftarea.add(st_add);
        leftarea.add(st_pas);
        leftarea.add(divide);
        leftarea.add(st_addclass);
        leftarea.add(st_updateself);

        TextArea ta = new TextArea(5, 20);
        ta.setText("获取数据中,\n请等待...");
        ta.setFont(new Font("宋体", Font.PLAIN, 40));
        rightarea.add(ta);

        TextArea ga = new TextArea(5, 20);
        ga.setFont(new Font("宋体", Font.PLAIN, 30));
        rightarea.add(ga);
        leftarea.setVisible(false);
        leftarea.setVisible(true);

        //! 事件处理

        st_stu.addActionListener(new ActionListener() {//! 查看选课|下面教师的信息
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    message = St.Student_Show(ad_flag); //借助接口message获得数据(flag忽略)
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                ta.setText(message);
                ad_flag = 1;
                try {
                    message = St.Student_Show(ad_flag); //借助接口message获得数据(flag忽略)
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                ga.setText(message);
                ad_flag = 0;
            }
        });

        st_add.addActionListener(new ActionListener() {//! 查看选课,下方输入要选的课,点击按钮提交
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    message = St.Student_Show(ad_flag); //借助接口message获得数据(flag忽略)
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                ta.setText(message);
                ga.setText(""); //清空下方输入框方便用户输入
            }
        });

        st_addclass.addActionListener(new ActionListener() {//调用后台方法增加选课
            @Override
            public void actionPerformed(ActionEvent e) {

                String A = ga.getText();
                try {
                    St.Student_AddClass(Integer.parseInt(A), NowStudentAccount);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        st_pas.addActionListener(new ActionListener() {//显示个人信息
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ta.setText(St.Student_self(NowStudentAccount));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //修改示例: 11 cwx 1 1 1 www 0
        st_updateself.addActionListener(new ActionListener() { //改自己信息
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    St.Student_updateself(NowStudentAccount, ga.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    void Edu_teacher() {//教师前端页面控件设计
        System.out.println("教师已部署");
        //TODO 2
        //查看自己的课程信息
        //修改个人信息

        leftarea.remove(welcome);
        leftarea.remove(identity);
        leftarea.remove(usernames);
        leftarea.remove(passwords);
        leftarea.remove(login);
        leftarea.remove(note);
        leftarea.setVisible(false);
        leftarea.setVisible(true);

        //教师控件生成:
        JButton te_stu = new JButton("查看课程信息");
        te_stu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton te_pas = new JButton("修改个人信息");  //按钮:提交个人信息
        te_pas.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel divide = new JLabel("-------分割线--------");
        divide.setHorizontalAlignment(SwingConstants.CENTER);
        divide.setFont(new Font("宋体", Font.PLAIN, 25));

        JButton te_updateself = new JButton("提交个人信息");
        te_updateself.setCursor(new Cursor(Cursor.HAND_CURSOR));

        leftarea.add(te_stu);
        leftarea.add(te_pas);
        leftarea.add(divide);
        leftarea.add(te_updateself);

        TextArea ta = new TextArea(5, 20);
        ta.setText("获取数据中,\n请等待...");
        ta.setFont(new Font("宋体", Font.PLAIN, 40));
        rightarea.add(ta);

        TextArea ga = new TextArea(5, 20);
        ga.setFont(new Font("宋体", Font.PLAIN, 30));
        rightarea.add(ga);
        leftarea.setVisible(false);
        leftarea.setVisible(true);

        //! 事件处理
        te_stu.addActionListener(new ActionListener() {//查看选课
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    message = Te.Teacher_Show();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                ta.setText(message);
                ga.setText("");
            }
        });

        te_pas.addActionListener(new ActionListener() {//显示个人信息
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ta.setText(Te.Teacher_self(NowTeacherAccount));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //! 修改示例: 114514 李天锁 115 414 女 1 1 大专 教授 数信
        te_updateself.addActionListener(new ActionListener() { //改自己信息
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Te.Teacher_updateself(NowTeacherAccount, ga.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    void Edu_administer() {//管理员前端页面控件设计
        System.out.println("管理员已部署");
        leftarea.remove(welcome);
        leftarea.remove(identity);
        leftarea.remove(usernames);
        leftarea.remove(passwords);
        leftarea.remove(login);
        leftarea.remove(note);
        leftarea.setVisible(false);
        leftarea.setVisible(true);

        //页面标记符
        int wherelook = 0;//管理学生1,管理教师2,其他忽略已禁用按钮
        //管理员控件生成:
        JButton ad_stu = new JButton("学生管理");
        ad_stu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton ad_tea = new JButton("教师管理");
        ad_tea.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton ad_pas = new JButton("重置密码");
        ad_pas.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel divide = new JLabel("-------分割线--------");
        divide.setHorizontalAlignment(SwingConstants.CENTER);
        divide.setFont(new Font("宋体", Font.PLAIN, 25));

        JButton ad_adddata = new JButton("增加");
        ad_adddata.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton ad_deldata = new JButton("删除");
        ad_deldata.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton ad_updatedata = new JButton("更新");
        ad_updatedata.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton ad_ok = new JButton("确认修改");
        ad_ok.setCursor(new Cursor(Cursor.HAND_CURSOR));

        leftarea.add(ad_stu);
        leftarea.add(ad_tea);
        leftarea.add(ad_pas);
        leftarea.add(divide);
        leftarea.add(ad_adddata);
        leftarea.add(ad_deldata);
        leftarea.add(ad_updatedata);
        leftarea.add(ad_ok);
        ad_ok.setVisible(false); //暂时隐藏确认按钮

       /* //字体设计jap
        JButton jap = new JButton("xues");
        jap.setFont(new Font("宋体", Font.PLAIN, 40));
        jap.setBackground(Color.YELLOW);
        jap.setForeground(Color.red);
        jap.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jap.setBorder(BorderFactory.createLineBorder(Color.blue));//设置边框*/

        final int[] input_ok = {0};//确认完成后按一下,之后记得归位
        ad_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input_ok[0] = 1;
                System.out.println("输入确认");
            }
        });

        //! 上下文本域
        TextArea ta = new TextArea(5, 20);
        ta.setText("获取数据中,\n请等待...");
        ta.setFont(new Font("宋体", Font.PLAIN, 40));
        rightarea.add(ta);

        TextArea ga = new TextArea(5, 20);
        ga.setFont(new Font("宋体", Font.PLAIN, 30));
        rightarea.add(ga);

        ad_stu.addActionListener(new ActionListener() { //查看学生数据

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    message = Ad.ManageStudent_Show(); //借助接口message获得数据(flag忽略)
                    ta.setText(message);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        ad_tea.addActionListener(new ActionListener() { //查看老师数据
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    message = Ad.ManageTeacher_Show(); //借助接口message获得数据(flag忽略)
                    ta.setText(message);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        ad_pas.addActionListener(new ActionListener() { //重置密码
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ad_flag == 1) { //学生
                    ad_ok.setVisible(true);
                    try {
                        Ad.ManageStudent_password(ga.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    System.out.println("学生有密码更新");
                } else if (ad_flag == 2) {//教师
                    ad_ok.setVisible(true);
                    try {
                        Ad.ManageTeacher_password(ga.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println("教师有密码更新");
                } else {
                    ;//什么都不干.
                }
            }
        });

        //增加
        //测试学员增加数据:"114514 田所浩二 114 514 1.14 下北泽野兽宅 Null"
        //测试教师增加数据:"114514 田所浩二 114 514 男 2003-03-16 2003-03-16 大专 教授 数信"
        ad_adddata.addActionListener(new ActionListener() { //添加数据
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ad_flag == 1) { //学生端增加信息
                    ad_ok.setVisible(true);

                    //简单粗暴文本框输入,空格分隔
                    try {
                        Ad.ManageStudent_Add(ga.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    System.out.println("学生增加");
                } else if (ad_flag == 2) {//教师端增加信息
                    ad_ok.setVisible(true);

                    //简单粗暴文本框输入,空格分隔
                    try {
                        Ad.ManageTeacher_Add(ga.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    System.out.println("教师增加");
                } else {
                    ;//什么都不干.
                }
            }
        });

        //更新
        //测试学员更新数据:"114514 田所 154 555 9.94 野兽屋 Null"
        //测试教师更新数据:"114514 田所 114 514 男 1103-03-16 1103-03-16 小专 讲师 数信"
        ad_updatedata.addActionListener(new ActionListener() {//更新数据
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ad_flag == 1) { //学生端增加信息
                    ad_ok.setVisible(true);

                    //简单粗暴文本框输入,空格分隔
                    try {
                        Ad.ManageStudent_Update(ga.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    System.out.println("学生有数据更新");
                } else if (ad_flag == 2) {//教师端增加信息
                    ad_ok.setVisible(true);

                    //简单粗暴文本框输入,空格分隔
                    try {
                        Ad.ManageTeacher_Update(ga.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println("教师有数据更新");
                } else {
                    ;//什么都不干.
                }
            }
        });


        //删除:输入ID直接删除->修改对应元素ID为1013后删除
        ad_deldata.addActionListener(new ActionListener() {//删除指定数据
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ad_flag == 1) { //学生端删除信息
                    ad_ok.setVisible(true);

                    try {
                        Ad.ManageStudent_Delete(ga.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }


                    System.out.println("学生有数据删除");
                } else if (ad_flag == 2) {//教师端删除信息
                    ad_ok.setVisible(true);

                    try {
                        Ad.ManageTeacher_Delete(ga.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    System.out.println("教师有数据删除");
                } else {
                    ;//什么都不干.
                }
            }
        });
    }


    @Override
    public String toString() {
        return "Edu_Front is Working";
    }

    public static void main(String[] args) {
        Edu_Front edufront = new Edu_Front();
        System.out.println(edufront.toString());
    }


}
