package Can_Front;

import Can_Back.Can;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

@SuppressWarnings("unchecked")
public class Can_Fr {
    private JFrame canframe = new JFrame("�㵥С����v1.0.0");
    private JPanel leftarea = new JPanel();
    private JPanel rightarea = new JPanel();
    TextArea ta = new TextArea(5, 20);
    TextArea ga = new TextArea(5, 20);

    //����Ա
    JButton order_jb = new JButton("��ǰ�㵥");
    JButton history_jb = new JButton("��ʷ�˵�");
    JButton storage_jb = new JButton("�����Ϣ");

    //�˿�
    JButton call_jb = new JButton("�㵥");
    JButton pay_jb = new JButton("����");

    public Can canteen = new Can(2);//Ŀǰ1,2����û��,û�򵥵��˵���Order���ݿ�
    //���˵Ĵ��䵽history���ݿ�,ͬʱɾ��ԭ��������



    Can_Fr() {

        //һ�����

        canframe.pack();
        canframe.setSize(1500, 800);
        canframe.setLocationRelativeTo(null);
        canframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        canframe.setLayout(new FlowLayout(0));
        //�������1
        canframe.add(leftarea);
        canframe.add(rightarea);

        leftarea.setLayout(new GridLayout(6, 1));
        leftarea.setPreferredSize(new Dimension(400, 800));
        leftarea.setBackground(Color.LIGHT_GRAY);
        rightarea.setBackground(Color.white);
        rightarea.setPreferredSize(new Dimension(1000, 800));
        rightarea.setLayout(new GridLayout(2, 1));

        JButton ad = new JButton("����Ա���");
        ad.setCursor(new Cursor(Cursor.HAND_CURSOR));
        JButton co = new JButton("�˿����");
        co.setCursor(new Cursor(Cursor.HAND_CURSOR));

        leftarea.add(ad);
        leftarea.add(co);

        ta.setText("��ȡ������,\n��ȴ�...");
        ta.setFont(new Font("����", Font.PLAIN, 40));
        rightarea.add(ta);
        ga.setFont(new Font("����", Font.PLAIN, 30));
        rightarea.add(ga);

        canframe.setVisible(true);

        //! �¼�������
        ad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftarea.remove(ad);
                leftarea.remove(co);
                System.out.println("����Ա�Ѳ���");
                Ad();//����μ�
            }
        });

        co.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leftarea.remove(ad);
                leftarea.remove(co);
                System.out.println("�ͻ��Ѳ���");
                Co();//����μ�
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

        //! �¼�������
        order_jb.addActionListener(new ActionListener() { //��ѯ�㵥���
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ta.setText(canteen.Order());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        storage_jb.addActionListener(new ActionListener() { //��ѯ���
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ta.setText(canteen.Storage());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        history_jb.addActionListener(new ActionListener() { //��ѯ��ʷ�˵�
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
        ta.setText("���㵥,�����·�������λ,���,��Ʒ,�Կո�ָ�!\n������,��������,�Զ��ۿ�\n");

        //! �¼�������
        call_jb.addActionListener(new ActionListener() {//���ӵ㵥
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    canteen.Callorder(ga.getText());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        pay_jb.addActionListener(new ActionListener() {//ɾ���㵥
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
        return "�㵥����ϵͳv1.0.0������";
    }

    public static void main(String[] args) {
        Can_Fr cancanneed = new Can_Fr();
        System.out.println(cancanneed.toString());
    }
}
