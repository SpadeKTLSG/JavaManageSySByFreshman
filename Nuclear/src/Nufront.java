import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

@SuppressWarnings("unchecked")
public class Nufront {
    private JFrame nuframe = new JFrame("��������v1.0.0");
    private JPanel leftarea = new JPanel();
    private JPanel rightarea = new JPanel();
    TextArea ta = new TextArea(5, 20);
    TextArea ga = new TextArea(5, 20);
    public JTextField usernames = new JFormattedTextField();
    public JPasswordField passwords = new JPasswordField();
    String[] choose_identity = {"����Ա", "�û�"};
    public JComboBox identity = new JComboBox(choose_identity);
    JButton login = new JButton("����!");


    //�û��˺�
    String NowUserAccount;
    int isLogin = 0; //�жϵ�¼
    public Nuclear N = new Nuclear();//��̨


    public Nufront() {

        //��¼:����Ա-ֱ��, �û�-ֱ��

        //һ�����

        nuframe.pack();
        nuframe.setSize(1500, 800);
        nuframe.setLocationRelativeTo(null);
        nuframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        nuframe.setLayout(new FlowLayout(0));
        //�������1
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

        ta.setText("��ȡ������,\n��ȴ�...");
        ta.setFont(new Font("����", Font.PLAIN, 40));
        rightarea.add(ta);
        ga.setFont(new Font("����", Font.PLAIN, 30));
        rightarea.add(ga);

        nuframe.setVisible(true);

        //! �¼�������
        login.addActionListener(new ActionListener() {//��������Ĳ���:�ȶ����ݿ���������,ȷ���Ƿ����
            @Override
            public void actionPerformed(ActionEvent e) {
                int find_ca = identity.getSelectedIndex();//ȷ��ѡ���¼��ɫ
                System.out.println(find_ca + "���ͽ�ɫ��¼��");
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
                        NowUserAccount = usernames.getText();//��ȡѧ���˺Ŵ�����̨
                        Us();
                    }
                }
            }
        });


    }

    public void Ad() { //����Ա�˵�
        System.out.println("����Ա�Ѳ���");
        leftarea.remove(identity);
        leftarea.remove(usernames);
        leftarea.remove(passwords);
        leftarea.remove(login);
        leftarea.setLayout(new GridLayout(8, 1));

        JButton getpersons = new JButton("��ȡ��Ա�б�");
        getpersons.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(getpersons);

        JButton getbugs = new JButton("δ�Ǽ�����");
        getbugs.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(getbugs);

        JLabel postnu = new JLabel("    ---�ָ���---");
        postnu.setBackground(Color.YELLOW);
        postnu.setFont(new Font("����", Font.BOLD, 40));
        leftarea.add(postnu);

        JButton addnews = new JButton("��������");
        addnews.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(addnews);
//----------------------------ѡ�г�Ա�б��ʱ�����----------------------------------
        JButton addman = new JButton("���ӳ�Ա");
        addman.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(addman);
        addman.setVisible(false);

        JButton delman = new JButton("ɾ����Ա");
        delman.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(delman);
        delman.setVisible(false);

        JButton changeman = new JButton("�޸ĳ�Ա");
        changeman.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(changeman);
        changeman.setVisible(false);

        leftarea.setVisible(false);
        leftarea.setVisible(true);


        //! �¼�:
        addnews.addActionListener(new ActionListener() { // ����֪ͨ
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    N.Addnews(ga.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        getbugs.addActionListener(new ActionListener() { //��ʾû�н��������
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ta.setText((N.Showbugperson()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        getpersons.addActionListener(new ActionListener() { //��ʾ������
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

        addman.addActionListener(new ActionListener() { //��������
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    N.personAdd(ga.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        delman.addActionListener(new ActionListener() { //ɾ������
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                   N.personDelete(ga.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        changeman.addActionListener(new ActionListener() { //��������
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

    public void Us() { //�û��˵�
        System.out.println("�û��Ѳ���");
        leftarea.remove(identity);
        leftarea.remove(usernames);
        leftarea.remove(passwords);
        leftarea.remove(login);
        leftarea.setVisible(false);
        leftarea.setVisible(true);
        ta.setText("���·����ո�ָ��ύ�������������,0����û����,���������������û����,��һ��1�ͻᱨ��");

        JButton getnews = new JButton("��ȡ֪ͨ");
        getnews.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leftarea.add(getnews);
        JButton postnu = new JButton("�ύ����");
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
        return "��������v1.0.0������";
    }

    public static void main(String[] args) {
        Nufront nuclear = new Nufront();
        System.out.println(nuclear.toString());
    }
}
