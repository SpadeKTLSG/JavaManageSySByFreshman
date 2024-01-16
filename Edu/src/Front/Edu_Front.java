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
    private JFrame edu = new JFrame("����ϵͳ ");
    private JPanel leftarea = new JPanel();
    private JPanel rightarea = new JPanel();

    //!���ص�¼;�������ض���ʵ��,���ݽ�ɫѡ�������Ӧ�����������ݿ�
    public Admin Ad = new Admin(this);
    public Stu St = new Stu(this);
    public Tea Te = new Tea(this);
    //��¼�ؼ�:
    public JTextField usernames = new JFormattedTextField();
    public JPasswordField passwords = new JPasswordField();
    String[] choose_identity = {"����", "ѧ��", "��ʦ"};
    public JComboBox identity = new JComboBox(choose_identity);
    JLabel welcome = new JLabel("����ϵͳv0.0.1");
    JLabel note = new JLabel("ѡ������ɫ�������˺�����!");
    JButton login = new JButton("����!");
    //ȫ�ֿ��Ʊ���
    public int character = 0;//1-ѧ��,2-��ʦ,3-����Ա
    public int isLogin = 0;
    public int isExit = 0;
    public String message = "";

    public int ad_flag = 0;//ҳ����Ʊ���

    //�������浱ǰѧ������ʦ���˺�
    public int NowStudentAccount = 0;
    public int NowTeacherAccount = 0;

    @Override
    public void tell(String sth, int flag) {
        message = sth;
        ad_flag = flag;
    }

    Edu_Front() {//! ����ʱ�����������,Ȼ����ݽ����ϵͳ���벻ͬ���Ӻ���
        //һ�����
        edu.setVisible(true);
        edu.pack();
        edu.setSize(1500, 800);
        edu.setLocationRelativeTo(null);
        edu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        edu.setLayout(new FlowLayout(0));
        //�������1
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

        //? ��¼��ťԪ��

        welcome.setFont(new Font("����", Font.BOLD, 20));
        welcome.setForeground(Color.red);

        note.setFont(new Font("����", Font.BOLD, 20));
        note.setForeground(Color.red);

        login.setFont(new Font("����", Font.BOLD, 20));
        login.setForeground(Color.red);
        login.setBorder(BorderFactory.createLineBorder(Color.blue));//���ñ߿�
        login.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.addActionListener(new ActionListener() {//��������Ĳ���:�ȶ����ݿ���������,ȷ���Ƿ����
            @Override
            public void actionPerformed(ActionEvent e) {
                int find_ca = identity.getSelectedIndex();//ȷ��ѡ���¼��ɫ
                System.out.println(find_ca + "���ͽ�ɫ��¼��");
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
                        NowStudentAccount = Integer.parseInt(usernames.getText());//��ȡѧ���˺Ŵ�����̨
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
                        NowTeacherAccount = Integer.parseInt(usernames.getText());//��ȡ��ʦ�˺Ŵ�����̨
                        Edu_teacher();
                    }
                }
            }
        });

        //�������2

        rightarea.setBackground(Color.white);
        rightarea.setPreferredSize(new Dimension(1000, 800));
        rightarea.setLayout(new GridLayout(2, 1));
    }

    void Edu_student() {//ѧ��ǰ��ҳ��ؼ����
        System.out.println("ѧԱ�Ѳ���");
        leftarea.remove(welcome);
        leftarea.remove(identity);
        leftarea.remove(usernames);
        leftarea.remove(passwords);
        leftarea.remove(login);
        leftarea.remove(note);


        //ѧ���ؼ�����:
        JButton st_stu = new JButton("�鿴�γ�,��ʦ��Ϣ");
        st_stu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton st_add = new JButton("ѡ��");  //��ť:�ύѡ�ογ̱��
        st_add.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton st_pas = new JButton("�޸ĸ�����Ϣ");  //��ť:�ύ������Ϣ
        st_pas.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel divide = new JLabel("-------�ָ���--------");
        divide.setHorizontalAlignment(SwingConstants.CENTER);
        divide.setFont(new Font("����", Font.PLAIN, 25));

        JButton st_addclass = new JButton("�ύѡ�ογ̱��");
        st_pas.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton st_updateself = new JButton("�ύ������Ϣ");
        st_updateself.setCursor(new Cursor(Cursor.HAND_CURSOR));

        leftarea.add(st_stu);
        leftarea.add(st_add);
        leftarea.add(st_pas);
        leftarea.add(divide);
        leftarea.add(st_addclass);
        leftarea.add(st_updateself);

        TextArea ta = new TextArea(5, 20);
        ta.setText("��ȡ������,\n��ȴ�...");
        ta.setFont(new Font("����", Font.PLAIN, 40));
        rightarea.add(ta);

        TextArea ga = new TextArea(5, 20);
        ga.setFont(new Font("����", Font.PLAIN, 30));
        rightarea.add(ga);
        leftarea.setVisible(false);
        leftarea.setVisible(true);

        //! �¼�����

        st_stu.addActionListener(new ActionListener() {//! �鿴ѡ��|�����ʦ����Ϣ
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    message = St.Student_Show(ad_flag); //�����ӿ�message�������(flag����)
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                ta.setText(message);
                ad_flag = 1;
                try {
                    message = St.Student_Show(ad_flag); //�����ӿ�message�������(flag����)
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                ga.setText(message);
                ad_flag = 0;
            }
        });

        st_add.addActionListener(new ActionListener() {//! �鿴ѡ��,�·�����Ҫѡ�Ŀ�,�����ť�ύ
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    message = St.Student_Show(ad_flag); //�����ӿ�message�������(flag����)
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                ta.setText(message);
                ga.setText(""); //����·�����򷽱��û�����
            }
        });

        st_addclass.addActionListener(new ActionListener() {//���ú�̨��������ѡ��
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

        st_pas.addActionListener(new ActionListener() {//��ʾ������Ϣ
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ta.setText(St.Student_self(NowStudentAccount));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //�޸�ʾ��: 11 cwx 1 1 1 www 0
        st_updateself.addActionListener(new ActionListener() { //���Լ���Ϣ
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

    void Edu_teacher() {//��ʦǰ��ҳ��ؼ����
        System.out.println("��ʦ�Ѳ���");
        //TODO 2
        //�鿴�Լ��Ŀγ���Ϣ
        //�޸ĸ�����Ϣ

        leftarea.remove(welcome);
        leftarea.remove(identity);
        leftarea.remove(usernames);
        leftarea.remove(passwords);
        leftarea.remove(login);
        leftarea.remove(note);
        leftarea.setVisible(false);
        leftarea.setVisible(true);

        //��ʦ�ؼ�����:
        JButton te_stu = new JButton("�鿴�γ���Ϣ");
        te_stu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton te_pas = new JButton("�޸ĸ�����Ϣ");  //��ť:�ύ������Ϣ
        te_pas.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel divide = new JLabel("-------�ָ���--------");
        divide.setHorizontalAlignment(SwingConstants.CENTER);
        divide.setFont(new Font("����", Font.PLAIN, 25));

        JButton te_updateself = new JButton("�ύ������Ϣ");
        te_updateself.setCursor(new Cursor(Cursor.HAND_CURSOR));

        leftarea.add(te_stu);
        leftarea.add(te_pas);
        leftarea.add(divide);
        leftarea.add(te_updateself);

        TextArea ta = new TextArea(5, 20);
        ta.setText("��ȡ������,\n��ȴ�...");
        ta.setFont(new Font("����", Font.PLAIN, 40));
        rightarea.add(ta);

        TextArea ga = new TextArea(5, 20);
        ga.setFont(new Font("����", Font.PLAIN, 30));
        rightarea.add(ga);
        leftarea.setVisible(false);
        leftarea.setVisible(true);

        //! �¼�����
        te_stu.addActionListener(new ActionListener() {//�鿴ѡ��
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

        te_pas.addActionListener(new ActionListener() {//��ʾ������Ϣ
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ta.setText(Te.Teacher_self(NowTeacherAccount));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //! �޸�ʾ��: 114514 ������ 115 414 Ů 1 1 ��ר ���� ����
        te_updateself.addActionListener(new ActionListener() { //���Լ���Ϣ
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

    void Edu_administer() {//����Աǰ��ҳ��ؼ����
        System.out.println("����Ա�Ѳ���");
        leftarea.remove(welcome);
        leftarea.remove(identity);
        leftarea.remove(usernames);
        leftarea.remove(passwords);
        leftarea.remove(login);
        leftarea.remove(note);
        leftarea.setVisible(false);
        leftarea.setVisible(true);

        //ҳ���Ƿ�
        int wherelook = 0;//����ѧ��1,�����ʦ2,���������ѽ��ð�ť
        //����Ա�ؼ�����:
        JButton ad_stu = new JButton("ѧ������");
        ad_stu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton ad_tea = new JButton("��ʦ����");
        ad_tea.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton ad_pas = new JButton("��������");
        ad_pas.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel divide = new JLabel("-------�ָ���--------");
        divide.setHorizontalAlignment(SwingConstants.CENTER);
        divide.setFont(new Font("����", Font.PLAIN, 25));

        JButton ad_adddata = new JButton("����");
        ad_adddata.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton ad_deldata = new JButton("ɾ��");
        ad_deldata.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton ad_updatedata = new JButton("����");
        ad_updatedata.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton ad_ok = new JButton("ȷ���޸�");
        ad_ok.setCursor(new Cursor(Cursor.HAND_CURSOR));

        leftarea.add(ad_stu);
        leftarea.add(ad_tea);
        leftarea.add(ad_pas);
        leftarea.add(divide);
        leftarea.add(ad_adddata);
        leftarea.add(ad_deldata);
        leftarea.add(ad_updatedata);
        leftarea.add(ad_ok);
        ad_ok.setVisible(false); //��ʱ����ȷ�ϰ�ť

       /* //�������jap
        JButton jap = new JButton("xues");
        jap.setFont(new Font("����", Font.PLAIN, 40));
        jap.setBackground(Color.YELLOW);
        jap.setForeground(Color.red);
        jap.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jap.setBorder(BorderFactory.createLineBorder(Color.blue));//���ñ߿�*/

        final int[] input_ok = {0};//ȷ����ɺ�һ��,֮��ǵù�λ
        ad_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                input_ok[0] = 1;
                System.out.println("����ȷ��");
            }
        });

        //! �����ı���
        TextArea ta = new TextArea(5, 20);
        ta.setText("��ȡ������,\n��ȴ�...");
        ta.setFont(new Font("����", Font.PLAIN, 40));
        rightarea.add(ta);

        TextArea ga = new TextArea(5, 20);
        ga.setFont(new Font("����", Font.PLAIN, 30));
        rightarea.add(ga);

        ad_stu.addActionListener(new ActionListener() { //�鿴ѧ������

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    message = Ad.ManageStudent_Show(); //�����ӿ�message�������(flag����)
                    ta.setText(message);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        ad_tea.addActionListener(new ActionListener() { //�鿴��ʦ����
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    message = Ad.ManageTeacher_Show(); //�����ӿ�message�������(flag����)
                    ta.setText(message);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        ad_pas.addActionListener(new ActionListener() { //��������
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ad_flag == 1) { //ѧ��
                    ad_ok.setVisible(true);
                    try {
                        Ad.ManageStudent_password(ga.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    System.out.println("ѧ�����������");
                } else if (ad_flag == 2) {//��ʦ
                    ad_ok.setVisible(true);
                    try {
                        Ad.ManageTeacher_password(ga.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println("��ʦ���������");
                } else {
                    ;//ʲô������.
                }
            }
        });

        //����
        //����ѧԱ��������:"114514 �����ƶ� 114 514 1.14 �±���Ұ��լ Null"
        //���Խ�ʦ��������:"114514 �����ƶ� 114 514 �� 2003-03-16 2003-03-16 ��ר ���� ����"
        ad_adddata.addActionListener(new ActionListener() { //�������
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ad_flag == 1) { //ѧ����������Ϣ
                    ad_ok.setVisible(true);

                    //�򵥴ֱ��ı�������,�ո�ָ�
                    try {
                        Ad.ManageStudent_Add(ga.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    System.out.println("ѧ������");
                } else if (ad_flag == 2) {//��ʦ��������Ϣ
                    ad_ok.setVisible(true);

                    //�򵥴ֱ��ı�������,�ո�ָ�
                    try {
                        Ad.ManageTeacher_Add(ga.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    System.out.println("��ʦ����");
                } else {
                    ;//ʲô������.
                }
            }
        });

        //����
        //����ѧԱ��������:"114514 ���� 154 555 9.94 Ұ���� Null"
        //���Խ�ʦ��������:"114514 ���� 114 514 �� 1103-03-16 1103-03-16 Сר ��ʦ ����"
        ad_updatedata.addActionListener(new ActionListener() {//��������
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ad_flag == 1) { //ѧ����������Ϣ
                    ad_ok.setVisible(true);

                    //�򵥴ֱ��ı�������,�ո�ָ�
                    try {
                        Ad.ManageStudent_Update(ga.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    System.out.println("ѧ�������ݸ���");
                } else if (ad_flag == 2) {//��ʦ��������Ϣ
                    ad_ok.setVisible(true);

                    //�򵥴ֱ��ı�������,�ո�ָ�
                    try {
                        Ad.ManageTeacher_Update(ga.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.out.println("��ʦ�����ݸ���");
                } else {
                    ;//ʲô������.
                }
            }
        });


        //ɾ��:����IDֱ��ɾ��->�޸Ķ�ӦԪ��IDΪ1013��ɾ��
        ad_deldata.addActionListener(new ActionListener() {//ɾ��ָ������
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ad_flag == 1) { //ѧ����ɾ����Ϣ
                    ad_ok.setVisible(true);

                    try {
                        Ad.ManageStudent_Delete(ga.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }


                    System.out.println("ѧ��������ɾ��");
                } else if (ad_flag == 2) {//��ʦ��ɾ����Ϣ
                    ad_ok.setVisible(true);

                    try {
                        Ad.ManageTeacher_Delete(ga.getText());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    System.out.println("��ʦ������ɾ��");
                } else {
                    ;//ʲô������.
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
