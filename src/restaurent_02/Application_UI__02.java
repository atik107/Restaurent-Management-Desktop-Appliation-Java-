/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package restaurent_02;

import java.awt.Color;
import java.awt.Image;
import java.awt.print.PrinterException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.ImageIcon;
import static restaurent_02.Application_UI.bills_id;
import static restaurent_02.log_in.name1;
import static restaurent_02.log_in.num1;
import static restaurent_02.log_in.rs_chk;
/**
 *
 * @author USER
 */

public class Application_UI__02 extends javax.swing.JFrame {

    //static Object jTextArea_receipt_2;

    /**
     * Creates new form Application_UI
     */
    File f=null;
    String path=null;
    private ImageIcon format=null;
    String fname=null;
    int s=0;
    byte [] pimage=null;
    PreparedStatement pst;
    ResultSet rs;
    
    int x,y;
    int f1=1;
    int cnt=0;
    double total=0.00;
    double tax=0.0;
    double tax_rate=0.0;
    Connection con;
    //private String rs_chk;
 
    
    public Application_UI__02() {
        initComponents();
        set_time();
        database_connect();
        read_info_from_database();
        page_01.setVisible(true);
        page_02.setVisible(false);
        //page_03.setVisible(false);
        page_04.setVisible(false);
        page_05.setVisible(false);
        
    }
        public final void read_info_from_database(){
        try{
                String sql = "Select *from food_info_01";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while(rs.next()){
                    String name,price,id;
                    name=rs.getString(1);
                    price=rs.getString(2);
                    byte[] image_data=rs.getBytes(3);
                    ImageIcon ii = new ImageIcon(new ImageIcon(image_data).getImage().getScaledInstance(two_pc_01.getWidth(),two_pc_01.getHeight(), Image.SCALE_SMOOTH));
                    id = rs.getString(4);
                    
                    if(id.equals("2113")){
                        two_pc_01.setIcon(ii);
                        two_price_01.setText(price);
                        two_food_name_01.setText(name);
                    }
                    else if(id.equals("2114")){
                        two_pc_02.setIcon(ii);
                        two_price_02.setText(price);
                        two_food_name_02.setText(name);
                    }
                    else if(id.equals("2115")){
                        two_pc_03.setIcon(ii);
                        two_price_03.setText(price);
                        two_food_name_03.setText(name);
                    }
                    else if(id.equals("2116")){
                        two_pc_04.setIcon(ii);
                        two_price_04.setText(price);
                        two_food_name_04.setText(name);
                    }
                    else if(id.equals("2117")){
                        two_pc_05.setIcon(ii);
                        two_price_05.setText(price);
                        two_food_name_05.setText(name);
                    }
                    else if(id.equals("2118")){
                        two_pc_06.setIcon(ii);
                        two_price_06.setText(price);
                        two_food_name_06.setText(name);
                    }
                } 
            }
            catch(SQLException ex){
                Logger.getLogger(ex.getMessage());
            }
    }
        
        
        
     public void call_payment_func(){
        try {
            String sql = "insert into payment_info(name,number,bill_id,bill_amount,method,Transaction) values(?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);
            
            pst.setString(1,name1);
            pst.setString(2,num1);
            pst.setString(3,(int)bills_id+"");
            
            double t=(total+(tax_rate*total));
            pst.setString(4,t+"");
            pst.setString(5,"-1");
            pst.setString(6,"-1");
            pst.executeUpdate();
            //JOptionPane.showMessageDialog(this, "New Account Created");
        } catch (SQLException ex){
            Logger.getLogger(log_in.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public final void database_connect(){
        try{
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurent","root","root"); 
        } 
        catch (SQLException ex){
            Logger.getLogger(ex.getMessage());
        }
    }
    public final void save_bill(){
        try {
            f1=0;
            String sql = "insert into income(res1,res2) values(?,?)";
            pst = con.prepareStatement(sql);
            double t=(total+(tax_rate*total));
            pst.setDouble(1,0);
            pst.setDouble(2,t);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "billl saved");
        } catch (SQLException ex) {
            Logger.getLogger(Application_UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tax_calculate(){
        if(total<500) tax_rate=0.05;
        if(500<=total && total<1000) tax_rate=0.08;
        if(1000<=total && total<2000) tax_rate=0.12;
        if(total>=2000)  tax_rate=0.15;
    }
    public boolean qtyIszero(int qty){
        if(qty==0){
            JOptionPane.showMessageDialog(null,"Please input quantity !");
            return false;
        }
        return true;
    }
    public final void set_time(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Application_UI__02.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Date date=new Date();
                    SimpleDateFormat tf=new SimpleDateFormat("h:mm aa");
                    SimpleDateFormat df= new SimpleDateFormat("EEEE, dd-MM-yyyy");
                    
                    String time=tf.format(date);
                    jlabel_time1.setText(time.split(" ")[0]+" "+time.split(" ")[1]);
                    jlabel_date.setText(df.format(date));
                }
            }
        }).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        left_panel = new javax.swing.JPanel();
        tab_01 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tab_02 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tab_04 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tab_05 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        right_panel = new javax.swing.JPanel();
        upper_01 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        upper_02 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        page_01 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        page_02 = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        two_pc_03 = new javax.swing.JLabel();
        two_food_name_03 = new javax.swing.JLabel();
        two_spinner_03 = new javax.swing.JSpinner();
        two_price_03 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        two_ckbx_03 = new javax.swing.JCheckBox();
        kGradientPanel2 = new keeptoo.KGradientPanel();
        two_pc_01 = new javax.swing.JLabel();
        two_food_name_01 = new javax.swing.JLabel();
        two_spinner_01 = new javax.swing.JSpinner();
        two_price_01 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        two_ckbx_01 = new javax.swing.JCheckBox();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        two_pc_02 = new javax.swing.JLabel();
        two_food_name_02 = new javax.swing.JLabel();
        two_spinner_02 = new javax.swing.JSpinner();
        two_price_02 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        two_ckbx_02 = new javax.swing.JCheckBox();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        two_pc_04 = new javax.swing.JLabel();
        two_food_name_04 = new javax.swing.JLabel();
        two_spinner_04 = new javax.swing.JSpinner();
        two_price_04 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        two_ckbx_04 = new javax.swing.JCheckBox();
        kGradientPanel5 = new keeptoo.KGradientPanel();
        two_pc_05 = new javax.swing.JLabel();
        two_food_name_05 = new javax.swing.JLabel();
        two_spinner_05 = new javax.swing.JSpinner();
        two_price_05 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        two_ckbx_05 = new javax.swing.JCheckBox();
        kGradientPanel6 = new keeptoo.KGradientPanel();
        two_pc_06 = new javax.swing.JLabel();
        two_food_name_06 = new javax.swing.JLabel();
        two_spinner_06 = new javax.swing.JSpinner();
        two_price_06 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        two_ckbx_06 = new javax.swing.JCheckBox();
        page_04 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_receipt_2 = new javax.swing.JTextArea();
        reset_button = new javax.swing.JButton();
        total_button = new javax.swing.JButton();
        receipt_button = new javax.swing.JButton();
        jlabel_date = new javax.swing.JLabel();
        jlabel_time1 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        page_05 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1100, 650));
        setUndecorated(true);
        getContentPane().setLayout(null);

        left_panel.setBackground(new java.awt.Color(51, 51, 51));
        left_panel.setForeground(new java.awt.Color(102, 102, 102));
        left_panel.setLayout(null);

        tab_01.setBackground(new java.awt.Color(51, 51, 51));
        tab_01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_01MouseClicked(evt);
            }
        });
        tab_01.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Home");
        tab_01.add(jLabel4);
        jLabel4.setBounds(120, 30, 81, 43);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8_home_48px_2.png"))); // NOI18N
        tab_01.add(jLabel5);
        jLabel5.setBounds(50, 20, 50, 40);

        left_panel.add(tab_01);
        tab_01.setBounds(0, 150, 290, 80);

        tab_02.setBackground(new java.awt.Color(51, 51, 51));
        tab_02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_02MouseClicked(evt);
            }
        });
        tab_02.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Biriyani");
        tab_02.add(jLabel12);
        jLabel12.setBounds(120, 30, 110, 43);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8_restaurant_48px.png"))); // NOI18N
        tab_02.add(jLabel13);
        jLabel13.setBounds(50, 20, 50, 40);

        left_panel.add(tab_02);
        tab_02.setBounds(0, 230, 290, 80);

        tab_04.setBackground(new java.awt.Color(51, 51, 51));
        tab_04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_04MouseClicked(evt);
            }
        });
        tab_04.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("MyCart");
        tab_04.add(jLabel8);
        jLabel8.setBounds(120, 30, 110, 43);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8_delivery_handcart_48px_1.png"))); // NOI18N
        tab_04.add(jLabel9);
        jLabel9.setBounds(50, 20, 50, 40);

        left_panel.add(tab_04);
        tab_04.setBounds(0, 310, 290, 80);

        tab_05.setBackground(new java.awt.Color(51, 51, 51));
        tab_05.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab_05MouseClicked(evt);
            }
        });
        tab_05.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("About");
        tab_05.add(jLabel10);
        jLabel10.setBounds(120, 30, 81, 43);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8_user_male_52px.png"))); // NOI18N
        tab_05.add(jLabel11);
        jLabel11.setBounds(50, 10, 50, 60);

        left_panel.add(tab_05);
        tab_05.setBounds(0, 390, 290, 80);

        getContentPane().add(left_panel);
        left_panel.setBounds(0, 0, 290, 650);

        right_panel.setBackground(new java.awt.Color(255, 204, 204));
        right_panel.setMinimumSize(new java.awt.Dimension(800, 700));
        right_panel.setPreferredSize(new java.awt.Dimension(800, 700));
        right_panel.setLayout(null);

        upper_01.setBackground(new java.awt.Color(255, 0, 51));
        upper_01.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                upper_01MouseDragged(evt);
            }
        });
        upper_01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                upper_01MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                upper_01MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                upper_01MousePressed(evt);
            }
        });
        upper_01.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Mini");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        upper_01.add(jLabel1);
        jLabel1.setBounds(720, 0, 50, 30);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Exit");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });
        upper_01.add(jLabel6);
        jLabel6.setBounds(770, 0, 30, 30);

        right_panel.add(upper_01);
        upper_01.setBounds(0, 0, 810, 30);

        upper_02.setBackground(new java.awt.Color(255, 0, 51));
        upper_02.setLayout(null);

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8_chef_cooking_96px.png"))); // NOI18N
        upper_02.add(jLabel14);
        jLabel14.setBounds(10, 0, 100, 80);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("House");
        upper_02.add(jLabel15);
        jLabel15.setBounds(120, 100, 60, 20);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Biriyani");
        upper_02.add(jLabel20);
        jLabel20.setBounds(20, 80, 110, 40);

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8_rice_bowl_48px.png"))); // NOI18N
        upper_02.add(jLabel21);
        jLabel21.setBounds(120, 10, 50, 50);

        right_panel.add(upper_02);
        upper_02.setBounds(100, 30, 180, 130);

        page_01.setBackground(new java.awt.Color(255, 255, 255));
        page_01.setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/res_img_02.gif"))); // NOI18N
        page_01.add(jLabel2);
        jLabel2.setBounds(90, 160, 650, 460);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 153, 0));
        jLabel3.setText("Find best Biriyani !!");
        page_01.add(jLabel3);
        jLabel3.setBounds(390, 80, 300, 50);

        right_panel.add(page_01);
        page_01.setBounds(0, 0, 810, 650);

        page_02.setBackground(new java.awt.Color(255, 249, 249));
        page_02.setLayout(null);

        kGradientPanel1.setkEndColor(new java.awt.Color(255, 102, 153));
        kGradientPanel1.setkGradientFocus(2000);
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel1.setLayout(null);

        two_pc_03.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/rsz_mutton_biri.png"))); // NOI18N
        kGradientPanel1.add(two_pc_03);
        two_pc_03.setBounds(80, 10, 100, 90);

        two_food_name_03.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        two_food_name_03.setForeground(new java.awt.Color(51, 153, 255));
        two_food_name_03.setText("Mutton Biriyani");
        kGradientPanel1.add(two_food_name_03);
        two_food_name_03.setBounds(50, 100, 160, 27);

        two_spinner_03.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        kGradientPanel1.add(two_spinner_03);
        two_spinner_03.setBounds(90, 180, 50, 22);

        two_price_03.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        two_price_03.setForeground(new java.awt.Color(102, 153, 255));
        two_price_03.setText("150");
        kGradientPanel1.add(two_price_03);
        two_price_03.setBounds(60, 150, 50, 21);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 153, 255));
        jLabel16.setText("Purchase : ");
        kGradientPanel1.add(jLabel16);
        jLabel16.setBounds(130, 150, 80, 21);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 153, 255));
        jLabel17.setText("Price : ");
        kGradientPanel1.add(jLabel17);
        jLabel17.setBounds(10, 150, 60, 21);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 153, 255));
        jLabel18.setText("Quantity :");
        kGradientPanel1.add(jLabel18);
        jLabel18.setBounds(10, 180, 80, 21);

        two_ckbx_03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                two_ckbx_03ActionPerformed(evt);
            }
        });
        kGradientPanel1.add(two_ckbx_03);
        two_ckbx_03.setBounds(210, 150, 19, 20);

        page_02.add(kGradientPanel1);
        kGradientPanel1.setBounds(540, 170, 240, 220);

        kGradientPanel2.setkEndColor(new java.awt.Color(255, 102, 153));
        kGradientPanel2.setkGradientFocus(2000);
        kGradientPanel2.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel2.setLayout(null);

        two_pc_01.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/rsz_chicken_biriyani___01.png"))); // NOI18N
        kGradientPanel2.add(two_pc_01);
        two_pc_01.setBounds(70, 10, 110, 90);

        two_food_name_01.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        two_food_name_01.setForeground(new java.awt.Color(51, 153, 255));
        two_food_name_01.setText("Chicken Biriyani");
        kGradientPanel2.add(two_food_name_01);
        two_food_name_01.setBounds(50, 100, 170, 27);

        two_spinner_01.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        kGradientPanel2.add(two_spinner_01);
        two_spinner_01.setBounds(90, 180, 50, 22);

        two_price_01.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        two_price_01.setForeground(new java.awt.Color(102, 153, 255));
        two_price_01.setText("150");
        kGradientPanel2.add(two_price_01);
        two_price_01.setBounds(60, 150, 50, 21);

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 153, 255));
        jLabel22.setText("Purchase : ");
        kGradientPanel2.add(jLabel22);
        jLabel22.setBounds(130, 150, 80, 21);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 153, 255));
        jLabel23.setText("Price : ");
        kGradientPanel2.add(jLabel23);
        jLabel23.setBounds(10, 150, 60, 21);

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 153, 255));
        jLabel24.setText("Quantity :");
        kGradientPanel2.add(jLabel24);
        jLabel24.setBounds(10, 180, 80, 21);

        two_ckbx_01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                two_ckbx_01ActionPerformed(evt);
            }
        });
        kGradientPanel2.add(two_ckbx_01);
        two_ckbx_01.setBounds(210, 150, 19, 20);

        page_02.add(kGradientPanel2);
        kGradientPanel2.setBounds(20, 170, 240, 220);

        kGradientPanel3.setkEndColor(new java.awt.Color(255, 102, 153));
        kGradientPanel3.setkGradientFocus(2000);
        kGradientPanel3.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel3.setLayout(null);

        two_pc_02.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/rsz_kacci_001.png"))); // NOI18N
        kGradientPanel3.add(two_pc_02);
        two_pc_02.setBounds(80, 10, 100, 90);

        two_food_name_02.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        two_food_name_02.setForeground(new java.awt.Color(51, 153, 255));
        two_food_name_02.setText("Beef Biriyani");
        kGradientPanel3.add(two_food_name_02);
        two_food_name_02.setBounds(60, 100, 140, 27);

        two_spinner_02.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        kGradientPanel3.add(two_spinner_02);
        two_spinner_02.setBounds(90, 180, 50, 22);

        two_price_02.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        two_price_02.setForeground(new java.awt.Color(102, 153, 255));
        two_price_02.setText("150");
        kGradientPanel3.add(two_price_02);
        two_price_02.setBounds(60, 150, 50, 21);

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 153, 255));
        jLabel28.setText("Purchase : ");
        kGradientPanel3.add(jLabel28);
        jLabel28.setBounds(130, 150, 80, 21);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 153, 255));
        jLabel29.setText("Price : ");
        kGradientPanel3.add(jLabel29);
        jLabel29.setBounds(10, 150, 60, 21);

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(102, 153, 255));
        jLabel30.setText("Quantity :");
        kGradientPanel3.add(jLabel30);
        jLabel30.setBounds(10, 180, 80, 21);

        two_ckbx_02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                two_ckbx_02ActionPerformed(evt);
            }
        });
        kGradientPanel3.add(two_ckbx_02);
        two_ckbx_02.setBounds(210, 150, 19, 20);

        page_02.add(kGradientPanel3);
        kGradientPanel3.setBounds(280, 170, 240, 220);

        kGradientPanel4.setkEndColor(new java.awt.Color(255, 102, 153));
        kGradientPanel4.setkGradientFocus(2000);
        kGradientPanel4.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel4.setLayout(null);

        two_pc_04.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/special_meet biriyani_01.jpg"))); // NOI18N
        kGradientPanel4.add(two_pc_04);
        two_pc_04.setBounds(80, 10, 100, 90);

        two_food_name_04.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        two_food_name_04.setForeground(new java.awt.Color(51, 153, 255));
        two_food_name_04.setText("Special Meet Biriyani");
        kGradientPanel4.add(two_food_name_04);
        two_food_name_04.setBounds(20, 100, 210, 27);

        two_spinner_04.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        kGradientPanel4.add(two_spinner_04);
        two_spinner_04.setBounds(90, 180, 50, 22);

        two_price_04.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        two_price_04.setForeground(new java.awt.Color(102, 153, 255));
        two_price_04.setText("150");
        kGradientPanel4.add(two_price_04);
        two_price_04.setBounds(60, 150, 50, 21);

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 153, 255));
        jLabel34.setText("Purchase : ");
        kGradientPanel4.add(jLabel34);
        jLabel34.setBounds(130, 150, 80, 21);

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(102, 153, 255));
        jLabel35.setText("Price : ");
        kGradientPanel4.add(jLabel35);
        jLabel35.setBounds(10, 150, 60, 21);

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(102, 153, 255));
        jLabel36.setText("Quantity :");
        kGradientPanel4.add(jLabel36);
        jLabel36.setBounds(10, 180, 80, 21);

        two_ckbx_04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                two_ckbx_04ActionPerformed(evt);
            }
        });
        kGradientPanel4.add(two_ckbx_04);
        two_ckbx_04.setBounds(210, 150, 19, 20);

        page_02.add(kGradientPanel4);
        kGradientPanel4.setBounds(20, 400, 240, 220);

        kGradientPanel5.setkEndColor(new java.awt.Color(255, 102, 153));
        kGradientPanel5.setkGradientFocus(2000);
        kGradientPanel5.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel5.setLayout(null);

        two_pc_05.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/rsz_biirynai_sp_05.png"))); // NOI18N
        kGradientPanel5.add(two_pc_05);
        two_pc_05.setBounds(80, 10, 100, 90);

        two_food_name_05.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        two_food_name_05.setForeground(new java.awt.Color(51, 153, 255));
        two_food_name_05.setText("Egg Biriyani");
        kGradientPanel5.add(two_food_name_05);
        two_food_name_05.setBounds(60, 100, 130, 27);

        two_spinner_05.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        kGradientPanel5.add(two_spinner_05);
        two_spinner_05.setBounds(90, 180, 50, 22);

        two_price_05.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        two_price_05.setForeground(new java.awt.Color(102, 153, 255));
        two_price_05.setText("150");
        kGradientPanel5.add(two_price_05);
        two_price_05.setBounds(60, 150, 50, 21);

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(102, 153, 255));
        jLabel40.setText("Purchase : ");
        kGradientPanel5.add(jLabel40);
        jLabel40.setBounds(130, 150, 80, 21);

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(102, 153, 255));
        jLabel41.setText("Price : ");
        kGradientPanel5.add(jLabel41);
        jLabel41.setBounds(10, 150, 60, 21);

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(102, 153, 255));
        jLabel42.setText("Quantity :");
        kGradientPanel5.add(jLabel42);
        jLabel42.setBounds(10, 180, 80, 21);

        two_ckbx_05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                two_ckbx_05ActionPerformed(evt);
            }
        });
        kGradientPanel5.add(two_ckbx_05);
        two_ckbx_05.setBounds(210, 150, 19, 20);

        page_02.add(kGradientPanel5);
        kGradientPanel5.setBounds(280, 400, 240, 220);

        kGradientPanel6.setkEndColor(new java.awt.Color(255, 102, 153));
        kGradientPanel6.setkGradientFocus(2000);
        kGradientPanel6.setkStartColor(new java.awt.Color(255, 255, 255));
        kGradientPanel6.setLayout(null);

        two_pc_06.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/rsz_special_biriyani.png"))); // NOI18N
        kGradientPanel6.add(two_pc_06);
        two_pc_06.setBounds(70, 10, 100, 90);

        two_food_name_06.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        two_food_name_06.setForeground(new java.awt.Color(51, 153, 255));
        two_food_name_06.setText("Special Beef Biriyani");
        kGradientPanel6.add(two_food_name_06);
        two_food_name_06.setBounds(30, 100, 200, 27);

        two_spinner_06.setModel(new javax.swing.SpinnerNumberModel(0, 0, 10, 1));
        kGradientPanel6.add(two_spinner_06);
        two_spinner_06.setBounds(90, 180, 50, 22);

        two_price_06.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        two_price_06.setForeground(new java.awt.Color(102, 153, 255));
        two_price_06.setText("150");
        kGradientPanel6.add(two_price_06);
        two_price_06.setBounds(60, 150, 50, 21);

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(102, 153, 255));
        jLabel46.setText("Purchase : ");
        kGradientPanel6.add(jLabel46);
        jLabel46.setBounds(130, 150, 80, 21);

        jLabel47.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(102, 153, 255));
        jLabel47.setText("Price : ");
        kGradientPanel6.add(jLabel47);
        jLabel47.setBounds(10, 150, 60, 21);

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(102, 153, 255));
        jLabel48.setText("Quantity :");
        kGradientPanel6.add(jLabel48);
        jLabel48.setBounds(10, 180, 80, 21);

        two_ckbx_06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                two_ckbx_06ActionPerformed(evt);
            }
        });
        kGradientPanel6.add(two_ckbx_06);
        two_ckbx_06.setBounds(210, 150, 19, 20);

        page_02.add(kGradientPanel6);
        kGradientPanel6.setBounds(540, 400, 240, 220);

        right_panel.add(page_02);
        page_02.setBounds(0, 0, 810, 650);

        page_04.setBackground(new java.awt.Color(254, 248, 241));
        page_04.setLayout(null);

        jTextArea_receipt_2.setEditable(false);
        jTextArea_receipt_2.setColumns(20);
        jTextArea_receipt_2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextArea_receipt_2.setRows(5);
        jScrollPane1.setViewportView(jTextArea_receipt_2);

        page_04.add(jScrollPane1);
        jScrollPane1.setBounds(390, 100, 390, 520);

        reset_button.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        reset_button.setForeground(new java.awt.Color(255, 153, 0));
        reset_button.setText("Reset");
        reset_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_buttonActionPerformed(evt);
            }
        });
        page_04.add(reset_button);
        reset_button.setBounds(270, 580, 100, 40);

        total_button.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        total_button.setForeground(new java.awt.Color(255, 153, 0));
        total_button.setText("Total");
        total_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_buttonActionPerformed(evt);
            }
        });
        page_04.add(total_button);
        total_button.setBounds(30, 580, 100, 40);

        receipt_button.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        receipt_button.setForeground(new java.awt.Color(255, 153, 0));
        receipt_button.setText("Receipt");
        receipt_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receipt_buttonActionPerformed(evt);
            }
        });
        page_04.add(receipt_button);
        receipt_button.setBounds(150, 580, 100, 40);

        jlabel_date.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jlabel_date.setForeground(new java.awt.Color(255, 102, 0));
        page_04.add(jlabel_date);
        jlabel_date.setBounds(570, 60, 200, 30);

        jlabel_time1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jlabel_time1.setForeground(new java.awt.Color(255, 102, 0));
        page_04.add(jlabel_time1);
        jlabel_time1.setBounds(410, 60, 110, 30);

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/rsz_1electronic-invoice-5769320-4828207 (1).png"))); // NOI18N
        page_04.add(jLabel26);
        jLabel26.setBounds(40, 210, 300, 310);

        right_panel.add(page_04);
        page_04.setBounds(0, 0, 810, 650);

        page_05.setBackground(new java.awt.Color(255, 204, 153));
        page_05.setLayout(null);
        right_panel.add(page_05);
        page_05.setBounds(0, 0, 810, 650);

        getContentPane().add(right_panel);
        right_panel.setBounds(290, 0, 810, 650);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tab_01MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_01MouseClicked
        // TODO add your handling code here:
        page_01.setVisible(true);
        page_02.setVisible(false);
        //page_03.setVisible(false);
        page_04.setVisible(false);
        page_05.setVisible(false);
        
        tab_01.setBackground(new Color(102,102,102));
        tab_02.setBackground(new Color(51,51,51));
        //tab_03.setBackground(new Color(51,51,51));
        tab_04.setBackground(new Color(51,51,51));
        tab_05.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_tab_01MouseClicked

    private void tab_02MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_02MouseClicked
        // TODO add your handling code here:
        page_02.setVisible(true);
        page_01.setVisible(false);
        //page_03.setVisible(false);
        page_04.setVisible(false);
        page_05.setVisible(false);
        
        tab_02.setBackground(new Color(102,102,102));
        tab_01.setBackground(new Color(51,51,51));
        //tab_03.setBackground(new Color(51,51,51));
        tab_04.setBackground(new Color(51,51,51));
        tab_05.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_tab_02MouseClicked

    private void tab_04MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_04MouseClicked
        // TODO add your handling code here:
        page_04.setVisible(true);
        page_02.setVisible(false);
        //page_03.setVisible(false);
        page_01.setVisible(false);
        page_05.setVisible(false);
        
        tab_04.setBackground(new Color(102,102,102));
        tab_02.setBackground(new Color(51,51,51));
        //tab_03.setBackground(new Color(51,51,51));
        tab_01.setBackground(new Color(51,51,51));
        tab_05.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_tab_04MouseClicked

    private void tab_05MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_05MouseClicked
        // TODO add your handling code here:
        page_05.setVisible(true);
        page_02.setVisible(false);
        //page_03.setVisible(false);
        page_04.setVisible(false);
        page_01.setVisible(false);
        
        tab_05.setBackground(new Color(102,102,102));
        tab_02.setBackground(new Color(51,51,51));
        //tab_03.setBackground(new Color(51,51,51));
        tab_04.setBackground(new Color(51,51,51));
        tab_01.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_tab_05MouseClicked

    private void upper_01MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upper_01MouseEntered
        // TODO add your handling code here:
        upper_01.setBackground(new Color(204,0,0));
       
    }//GEN-LAST:event_upper_01MouseEntered

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        //this.setState(Application_UI.ICONIFIED);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        //System.exit(0);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void upper_01MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upper_01MousePressed
        // TODO add your handling code here:
        x=evt.getX();
        y=evt.getY();
    }//GEN-LAST:event_upper_01MousePressed

    private void upper_01MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upper_01MouseDragged
        // TODO add your handling code here:
        int xx=evt.getXOnScreen();
        int yy=evt.getYOnScreen();
        this.setLocation(xx-x,yy-y);
    }//GEN-LAST:event_upper_01MouseDragged

    private void upper_01MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_upper_01MouseExited
        // TODO add your handling code here:
         upper_01.setBackground(new Color(255,0,51));
    }//GEN-LAST:event_upper_01MouseExited

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        // TODO add your handling code here:
        multiple_restaurent ui=new multiple_restaurent();
        ui.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel6MousePressed

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        // TODO add your handling code here:
         this.setState(Application_UI__02.ICONIFIED);
    }//GEN-LAST:event_jLabel1MousePressed

   public void atik_cafe(){
        bills_id=2255+(int)(Math.random()*1070);
        jTextArea_receipt_2.setText("**********************   Atik's Restaurent  *******************\n\n"+
                "Bill Id : "+(int)bills_id+"\n"+
                "************************************************************\n"
                +"     Time :"+jlabel_time1.getText()+"                   "+"Date : "+jlabel_date.getText()+"\n"+
                "*************************************************************"+
                 "\n"+"Item name : \t\t\t"+"Prices (TK)"+"\n");
    }
    private void reset_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_buttonActionPerformed
        // TODO add your handling code here:
        rs_chk="0";
        two_spinner_01.setValue(0);
        two_spinner_02.setValue(0);
        two_spinner_03.setValue(0);
        two_spinner_04.setValue(0);
        two_spinner_05.setValue(0);
        two_spinner_06.setValue(0);
        
//        three_spinner_01.setValue(0);
//        three_spinner_02.setValue(0);
//        three_spinner_03.setValue(0);
//        three_spinner_04.setValue(0);
//        three_spinner_05.setValue(0);
//        three_spinner_06.setValue(0);
        
        jTextArea_receipt_2.setText("");
        cnt=0;
        total=0;
        tax=0;
        tax_rate=0;
        
        two_ckbx_01.setSelected(false);
        two_ckbx_02.setSelected(false);
        two_ckbx_03.setSelected(false);
        two_ckbx_04.setSelected(false);
        two_ckbx_05.setSelected(false);
        two_ckbx_06.setSelected(false);
        
//        three_ckbx_01.setSelected(false);
//        three_ckbx_02.setSelected(false);
//        three_ckbx_03.setSelected(false);
//        three_ckbx_04.setSelected(false);
//        three_ckbx_05.setSelected(false);
//        three_ckbx_06.setSelected(false);  
        
        total_button.setEnabled(true);
    }//GEN-LAST:event_reset_buttonActionPerformed

    private void total_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_buttonActionPerformed
        // TODO add your handling code here:
        if(total==0.00){
            JOptionPane.showMessageDialog(null,"You haven't selected any item !!");
        }
        else{
            rs_chk="2";
            tax_calculate();
            call_payment_func();
            jTextArea_receipt_2.setText(jTextArea_receipt_2.getText()+
                    "\n*************************************************************\n"+
                    "Item cost :\t\t\t"+total+"\n"+
                    "Tax : \t\t("+tax_rate*100+" %)"+"\t"+tax_rate*total+"\n"+
                    "*************************************************************\n"+
                    "\tPayable ammount :\t"+(total+(tax_rate*total)));
            total_button.setEnabled(false);
        } 
    }//GEN-LAST:event_total_buttonActionPerformed

    private void receipt_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receipt_buttonActionPerformed
        if(total!=0 && !total_button.isEnabled()){
            try {
                if(f1==1) save_bill();
                //jTextArea_receipt_2.print();
                payment_system ps=new payment_system();
                ps.setVisible(true);
            } 
            catch (Exception ex) {
                Logger.getLogger(Application_UI__02.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"No receipt available.....");
        }
    }//GEN-LAST:event_receipt_buttonActionPerformed

    private void two_ckbx_06ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_two_ckbx_06ActionPerformed
        // TODO add your handling code here:
        int qty=Integer.parseInt(two_spinner_06.getValue().toString());
        if(qtyIszero(qty) && two_ckbx_06.isSelected()){
            cnt++;
            if(cnt==1){
                atik_cafe();
            }
            int p=Integer.parseInt(two_price_06.getText().toString());
            total+=p*qty;
            jTextArea_receipt_2.setText(jTextArea_receipt_2.getText()+cnt+". "+two_food_name_06.getText()+"\t\t"+p*qty+"\n");
        }
        else{
            two_ckbx_06.setSelected(false);
        }
    }//GEN-LAST:event_two_ckbx_06ActionPerformed

    private void two_ckbx_05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_two_ckbx_05ActionPerformed
        // TODO add your handling code here:
        int qty=Integer.parseInt(two_spinner_05.getValue().toString());
        if(qtyIszero(qty) && two_ckbx_05.isSelected()){
            cnt++;
            if(cnt==1){
                atik_cafe();
            }
            int p=Integer.parseInt(two_price_05.getText().toString());
            total+=p*qty;
            jTextArea_receipt_2.setText(jTextArea_receipt_2.getText()+cnt+". "+two_food_name_05.getText()+"\t\t\t"+p*qty+"\n");
        }
        else{
            two_ckbx_05.setSelected(false);
        }
    }//GEN-LAST:event_two_ckbx_05ActionPerformed

    private void two_ckbx_04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_two_ckbx_04ActionPerformed
        // TODO add your handling code here:
        int qty=Integer.parseInt(two_spinner_04.getValue().toString());
        if(qtyIszero(qty) && two_ckbx_04.isSelected()){
            cnt++;
            if(cnt==1){
                atik_cafe();
            }
            int p=Integer.parseInt(two_price_04.getText().toString());
            total+=p*qty;
            jTextArea_receipt_2.setText(jTextArea_receipt_2.getText()+cnt+". "+two_food_name_04.getText()+"\t\t"+p*qty+"\n");
        }
        else{
            two_ckbx_04.setSelected(false);
        }
    }//GEN-LAST:event_two_ckbx_04ActionPerformed

    private void two_ckbx_02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_two_ckbx_02ActionPerformed
        // TODO add your handling code here:
        int qty=Integer.parseInt(two_spinner_02.getValue().toString());
        if(qtyIszero(qty) && two_ckbx_02.isSelected()){
            cnt++;
            if(cnt==1){
                atik_cafe();
            }
            int p=Integer.parseInt(two_price_02.getText().toString());
            total+=p*qty;
            jTextArea_receipt_2.setText(jTextArea_receipt_2.getText()+cnt+". "+two_food_name_02.getText()+"\t\t\t"+p*qty+"\n");
        }
        else{
            two_ckbx_02.setSelected(false);
        }
    }//GEN-LAST:event_two_ckbx_02ActionPerformed

    private void two_ckbx_01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_two_ckbx_01ActionPerformed
        // TODO add your handling code here:
        int qty=Integer.parseInt(two_spinner_01.getValue().toString());
        if(qtyIszero(qty) && two_ckbx_01.isSelected()){
            cnt++;
            if(cnt==1){
                atik_cafe();
            }
            int p=Integer.parseInt(two_price_01.getText().toString());
            total+=p*qty;
            jTextArea_receipt_2.setText(jTextArea_receipt_2.getText()+cnt+". "+two_food_name_01.getText()+"\t\t"+p*qty+"\n");
        }
        else{
            two_ckbx_01.setSelected(false);
        }
    }//GEN-LAST:event_two_ckbx_01ActionPerformed

    private void two_ckbx_03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_two_ckbx_03ActionPerformed
        // TODO add your handling code here:
        int qty=Integer.parseInt(two_spinner_03.getValue().toString());
        if(qtyIszero(qty) && two_ckbx_03.isSelected()){
            cnt++;
            if(cnt==1){
                atik_cafe();
            }
            int p=Integer.parseInt(two_price_03.getText().toString());
            total+=p*qty;
            jTextArea_receipt_2.setText(jTextArea_receipt_2.getText()+cnt+". "+two_food_name_03.getText()+"\t\t"+p*qty+"\n");
        }
        else{
            two_ckbx_03.setSelected(false);
        }
    }//GEN-LAST:event_two_ckbx_03ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Application_UI__02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Application_UI__02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Application_UI__02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Application_UI__02.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Application_UI__02().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea jTextArea_receipt_2;
    private javax.swing.JLabel jlabel_date;
    private javax.swing.JLabel jlabel_time1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel3;
    private keeptoo.KGradientPanel kGradientPanel4;
    private keeptoo.KGradientPanel kGradientPanel5;
    private keeptoo.KGradientPanel kGradientPanel6;
    private javax.swing.JPanel left_panel;
    private javax.swing.JPanel page_01;
    private javax.swing.JPanel page_02;
    private javax.swing.JPanel page_04;
    private javax.swing.JPanel page_05;
    private javax.swing.JButton receipt_button;
    private javax.swing.JButton reset_button;
    private javax.swing.JPanel right_panel;
    private javax.swing.JPanel tab_01;
    private javax.swing.JPanel tab_02;
    private javax.swing.JPanel tab_04;
    private javax.swing.JPanel tab_05;
    private javax.swing.JButton total_button;
    private javax.swing.JCheckBox two_ckbx_01;
    private javax.swing.JCheckBox two_ckbx_02;
    private javax.swing.JCheckBox two_ckbx_03;
    private javax.swing.JCheckBox two_ckbx_04;
    private javax.swing.JCheckBox two_ckbx_05;
    private javax.swing.JCheckBox two_ckbx_06;
    private javax.swing.JLabel two_food_name_01;
    private javax.swing.JLabel two_food_name_02;
    private javax.swing.JLabel two_food_name_03;
    private javax.swing.JLabel two_food_name_04;
    private javax.swing.JLabel two_food_name_05;
    private javax.swing.JLabel two_food_name_06;
    private javax.swing.JLabel two_pc_01;
    private javax.swing.JLabel two_pc_02;
    private javax.swing.JLabel two_pc_03;
    private javax.swing.JLabel two_pc_04;
    private javax.swing.JLabel two_pc_05;
    private javax.swing.JLabel two_pc_06;
    private javax.swing.JLabel two_price_01;
    private javax.swing.JLabel two_price_02;
    private javax.swing.JLabel two_price_03;
    private javax.swing.JLabel two_price_04;
    private javax.swing.JLabel two_price_05;
    private javax.swing.JLabel two_price_06;
    private javax.swing.JSpinner two_spinner_01;
    private javax.swing.JSpinner two_spinner_02;
    private javax.swing.JSpinner two_spinner_03;
    private javax.swing.JSpinner two_spinner_04;
    private javax.swing.JSpinner two_spinner_05;
    private javax.swing.JSpinner two_spinner_06;
    private javax.swing.JPanel upper_01;
    private javax.swing.JPanel upper_02;
    // End of variables declaration//GEN-END:variables
}
