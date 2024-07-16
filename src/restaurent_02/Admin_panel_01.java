/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package restaurent_02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author USER
 */
public final class Admin_panel_01 extends javax.swing.JFrame {

    /**
     * Creates new form log_in
     */
    File f=null;
    String path=null;
    private ImageIcon format=null;
    String fname=null;
    int s=0;
    byte [] pic=null;
    
    int x,y,flag1,custom_num=0,revenue_num=0,food_num=18;
    double total_1=0,total_2=0;
    private DefaultTableModel model;
    int flag2=0;

    public Admin_panel_01(){
        initComponents();
        database_connect();
        food_info_database();
        //admin_pic.setIcon(null);
        load_profile_image();
        read_admin_show_data();
        table_change();
        
        Thread t=new Thread(new Runnable(){
            @Override
            public void run(){
                 try{
                    int ii=1,jj=1,kk=1;
                    int mx=1000000;
                    for(int i=1;i<=mx;i=i++){
                        Thread.sleep(150);
                        
                        if(ii<=custom_num){
                            num_3.setText(ii+"+ ");
                            ii++;
                        }
                        
                        if(jj<=revenue_num){
                            if(jj<1000){
                                num_2.setText(jj+"+");

                            }
                            else{
                                int tmp=jj/1000;
                                num_2.setText(tmp+"k+");
                                jj+=1000;
                            }
                            if(jj>=0 && jj<200) jj+=2;
                            if(jj>=200 && jj<1000) jj+=50;
                            if(jj>=1000 && jj<5000) jj+=200;
                            if(jj>=10000 && jj<50000) jj+=1000;
                            else{
                                jj+=2000;
                            }
                        }
                        
                        if(kk<=food_num){
                            num_1.setText(kk+"+ ");
                            kk+=2;
                        }
                    }
                }
                catch(InterruptedException e){
                    JOptionPane.showMessageDialog(null,e);
                }
            }
       });
       t.start();
    }
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    
    public void read_food_info_from_database(){
        try{
            String sql1 = "Select *from food_info_01";
            pst = con.prepareStatement(sql1);
            rs = pst.executeQuery();
            
            while(rs.next()){
                custom_num++;
                String name,price,id;
                byte[] pic;
                
                name=rs.getString(1);
                price=rs.getString(2);
                pic=rs.getBytes(3);
                id=rs.getString(4);
                
                String tb[]={id,name,price};
                DefaultTableModel tb1=(DefaultTableModel)food_tb_01.getModel();
                tb1.addRow(tb);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(Admin_panel_01.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void food_info_database(){
       
    try {
            String[]title = {"ID","Name","Price","Images"};
            String sql="select * from food_info_01";
          
            DefaultTableModel model1 = new DefaultTableModel(null,title){
                @Override
                public Class<?> getColumnClass(int column) {
                    if (column==3) return ImageIcon.class;
                    return Object.class;
                }
                @Override
                public boolean isCellEditable(int row, int column) {                
                    return false;               
                };
            };
            
            
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
        
            Object[]fila = new Object[4];
            while(rs.next()){
                
                fila[1] = rs.getString(1);
                fila[2] = rs.getString(2);
                pic=rs.getBytes(3);
                //byte[] image_data=rs.getBytes(1);
                format =new ImageIcon(pic);
                Image mm=format.getImage();
                Image img2=mm.getScaledInstance(50,50, Image.SCALE_SMOOTH);
                //ImageIcon image=new ImageIcon(img2);
                
                fila[3] = new ImageIcon(img2); 
//                 ImageIcon imageicon = new ImageIcon(new ImageIcon(rs.getBytes(3)).getImage()
//                .getScaledInstance(admin_pic.getWidth(), admin_pic.getHeight(), Image.SCALE_SMOOTH));
                fila[0] = rs.getString(4);
                model1.addRow(fila);
            }
            food_tb_01.setModel(model1);
        }
        catch (SQLException e) {
             JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public final void table_change(){
        table_02.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,16)) ;
        table_02.getTableHeader().setOpaque(false);
        table_02.getTableHeader().setBackground(new Color(0,153,255));
        table_02.getTableHeader().setForeground(new Color(255,255,255));
        table_02.setRowHeight(25);
        
                  //**********  //another table.............//*******
                  
        table_01.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,16)) ;
        table_01.getTableHeader().setOpaque(false);
        table_01.getTableHeader().setBackground(new Color(0,153,255));
        table_01.getTableHeader().setForeground(new Color(255,255,255));
        table_01.setRowHeight(25);
        
        
        table_03.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,16)) ;
        table_03.getTableHeader().setOpaque(false);
        table_03.getTableHeader().setBackground(new Color(0,153,255));
        table_03.getTableHeader().setForeground(new Color(255,255,255));
        table_03.setRowHeight(25);
        
        food_tb_01.getTableHeader().setFont(new Font("Segoe UI",Font.BOLD,16)) ;
        food_tb_01.getTableHeader().setOpaque(false);
        food_tb_01.getTableHeader().setBackground(new Color(0,153,255));
        food_tb_01.getTableHeader().setForeground(new Color(255,255,255));
       // food_tb_01.setRowHeight(25);
    }
    public final void read_admin_show_data(){
                     //read customer data and show in jtable.........
        try{
            String sql1 = "Select *from login";
            pst = con.prepareStatement(sql1);
            rs = pst.executeQuery();
            
            while(rs.next()){
                custom_num++;
                String name,pas,num,id;
                name=rs.getString(1);
                num=rs.getString(2);
                pas=rs.getString(3);
                id=custom_num+"   ";
                String tb[]={id,name,num,pas};
                DefaultTableModel tb1=(DefaultTableModel)table_01.getModel();
                tb1.addRow(tb);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(Admin_panel_01.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        //*************************************Revenue info .........**************************************
        /////////////////////////////////////////////////////////////////////////////////
        
        try{
            String sql1 = "Select *from income";
            pst = con.prepareStatement(sql1);
            rs = pst.executeQuery();
            
            while(rs.next()){
                revenue_num++;
                double r1,r2;
                r1=rs.getDouble(1);
                r2=rs.getDouble(2);
                total_1+=r1;
                total_2+=r2;
                
                String rs1=r1+"",rs2=r2+"",id2;
                
                id2=revenue_num+"";
                String tb[]={id2,rs1,rs2};
                DefaultTableModel tb1=(DefaultTableModel)table_02.getModel();
                tb1.addRow(tb);
            }
            
            revenue_num=0;
            revenue_num=(int)(total_1+total_2);
            String tt1=total_1+"",tt2=total_2+"";
            profit_1.setText(tt1);
            profit_2.setText(tt2);
        }
        catch(SQLException ex){
            Logger.getLogger(Admin_panel_01.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        //***********************************payment  info ****************************************
        ///////////////////////////////////////////////////////////////////////////////////////////
        
        
        try{
            String sql1 = "Select *from payment_info";
            pst = con.prepareStatement(sql1);
            rs = pst.executeQuery();
            
            while(rs.next()){
                String name,num,bill_id,bill_amount,serial,m,tra;
                name=rs.getString(1);
                num=rs.getString(2);
                bill_id=rs.getString(3);
                bill_amount=rs.getString(4);
                serial=rs.getString(7);
                m=rs.getString(5);
                tra=rs.getString(6);
                
                String tb[]={serial,name,num,bill_id,bill_amount,m,tra};
                DefaultTableModel tb1=(DefaultTableModel)table_03.getModel();
                tb1.addRow(tb);
            }
        }
        catch(SQLException ex){
            Logger.getLogger(Admin_panel_01.class.getName()).log(Level.SEVERE, null, ex);
        }
        food_info.setVisible(true);
        revenue_info.setVisible(false);
        customer_info1.setVisible(false);
    }
    
    
    
    
    public final void database_connect(){
        try{
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurent","root","root"); 
        } 
        catch (SQLException ex){
            Logger.getLogger(ex.getMessage());
        }
    }
    
    
    public final void load_profile_image(){
        try {
            String name;
            String sql3="select * from admin_info where id= '"+15+"'";
            //"Select *from login where number = '"+num+"'";
            pst = con.prepareStatement(sql3);
            rs = pst.executeQuery();
            
            if(rs.next()){
                byte[] image_data=rs.getBytes(1);
                format =new ImageIcon(image_data);
                
                Image mm=format.getImage();
                name=rs.getString(2);
                Image img2=mm.getScaledInstance(120,120, Image.SCALE_SMOOTH);
                ImageIcon image=new ImageIcon(img2);
                
                admin_pic.setIcon(image);
                admin_name_input.setText(name);
                
                 //ImageIcon imageicon = new ImageIcon(new ImageIcon(image_data).getImage().getScaledInstance(admin_pic.getWidth(), admin_pic.getHeight(), Image.SCALE_SMOOTH));
                 //admin_pic.setIcon(imageicon);
                 
                 //JOptionPane.showMessageDialog(null,"Image uploading...");
            }else{
                JOptionPane.showMessageDialog(null,"NO image found...");
            }
        } 
        catch (SQLException e) {
                Logger.getLogger(e.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        log_in_bar = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        base_panel = new javax.swing.JPanel();
        admin_left = new keeptoo.KGradientPanel();
        jLabel1 = new javax.swing.JLabel();
        admin_name_input = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        image_upload_button = new javax.swing.JButton();
        admin_pic = new rojerusan.RSLabelImage();
        save_profile_button1 = new javax.swing.JButton();
        admin_logout_button = new javax.swing.JButton();
        food_info = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        food_tb_01 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        food_id = new javax.swing.JTextField();
        food_name = new javax.swing.JTextField();
        food_price = new javax.swing.JTextField();
        food_pic = new rojerusan.RSLabelImage();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        admin_food_update_button = new javax.swing.JButton();
        input_food_pic_edit = new javax.swing.JButton();
        revenue_info = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_02 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        profit_1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        profit_2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        pie_chart_button_01 = new javax.swing.JButton();
        customer_info1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_01 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_03 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        food_button = new javax.swing.JPanel();
        admin_pic2 = new rojerusan.RSLabelImage();
        jLabel7 = new javax.swing.JLabel();
        num_1 = new javax.swing.JLabel();
        customer_button = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        num_3 = new javax.swing.JLabel();
        revenue_button = new javax.swing.JPanel();
        admin_pic3 = new rojerusan.RSLabelImage();
        jLabel5 = new javax.swing.JLabel();
        num_2 = new javax.swing.JLabel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        kGradientPanel2 = new keeptoo.KGradientPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1100, 700));
        setUndecorated(true);
        getContentPane().setLayout(null);

        log_in_bar.setBackground(new java.awt.Color(255, 0, 51));
        log_in_bar.setMinimumSize(new java.awt.Dimension(900, 30));
        log_in_bar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                log_in_barMouseDragged(evt);
            }
        });
        log_in_bar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                log_in_barMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                log_in_barMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                log_in_barMousePressed(evt);
            }
        });
        log_in_bar.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("X");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel6MousePressed(evt);
            }
        });
        log_in_bar.add(jLabel6);
        jLabel6.setBounds(1060, 0, 20, 30);

        getContentPane().add(log_in_bar);
        log_in_bar.setBounds(0, 0, 1100, 30);

        base_panel.setBackground(new java.awt.Color(255, 255, 255));
        base_panel.setLayout(null);

        admin_left.setForeground(new java.awt.Color(51, 51, 51));
        admin_left.setkEndColor(new java.awt.Color(0, 204, 255));
        admin_left.setkGradientFocus(1000);
        admin_left.setkStartColor(new java.awt.Color(0, 0, 0));
        admin_left.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Welcome to");
        admin_left.add(jLabel1);
        jLabel1.setBounds(80, 470, 130, 30);

        admin_name_input.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        admin_name_input.setForeground(new java.awt.Color(255, 255, 255));
        admin_name_input.setText("Atiqul Islam Atik");
        admin_left.add(admin_name_input);
        admin_name_input.setBounds(40, 240, 210, 50);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Admin Panel");
        admin_left.add(jLabel4);
        jLabel4.setBounds(40, 480, 210, 80);

        image_upload_button.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        image_upload_button.setForeground(new java.awt.Color(0, 0, 255));
        image_upload_button.setText("+");
        image_upload_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                image_upload_buttonActionPerformed(evt);
            }
        });
        admin_left.add(image_upload_button);
        image_upload_button.setBounds(110, 190, 50, 40);

        admin_pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/User-Profile-PNG-High-Quality-Image.png"))); // NOI18N
        admin_left.add(admin_pic);
        admin_pic.setBounds(60, 20, 150, 150);

        save_profile_button1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        save_profile_button1.setText("Update");
        save_profile_button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_profile_button1ActionPerformed(evt);
            }
        });
        admin_left.add(save_profile_button1);
        save_profile_button1.setBounds(100, 320, 90, 30);

        admin_logout_button.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        admin_logout_button.setText("Log Out");
        admin_logout_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_logout_buttonActionPerformed(evt);
            }
        });
        admin_left.add(admin_logout_button);
        admin_logout_button.setBounds(90, 620, 90, 30);

        base_panel.add(admin_left);
        admin_left.setBounds(0, 0, 280, 670);

        food_info.setBackground(new java.awt.Color(255, 255, 255));
        food_info.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                food_infoAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        food_info.setLayout(null);

        food_tb_01.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        food_tb_01.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Price", "Images"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Byte.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        food_tb_01.setRowHeight(60);
        food_tb_01.setSelectionBackground(new java.awt.Color(255, 51, 102));
        food_tb_01.setSelectionForeground(new java.awt.Color(255, 255, 255));
        food_tb_01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                food_tb_01MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(food_tb_01);

        food_info.add(jScrollPane4);
        jScrollPane4.setBounds(70, 80, 680, 240);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 153, 0));
        jLabel15.setText("Item Details");
        food_info.add(jLabel15);
        jLabel15.setBounds(290, 20, 170, 30);

        food_id.setEditable(false);
        food_id.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        food_id.setForeground(new java.awt.Color(0, 153, 255));
        food_id.setText("Id");
        food_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                food_idActionPerformed(evt);
            }
        });
        food_info.add(food_id);
        food_id.setBounds(290, 380, 240, 30);

        food_name.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        food_name.setForeground(new java.awt.Color(0, 153, 255));
        food_name.setText("Name");
        food_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                food_nameActionPerformed(evt);
            }
        });
        food_info.add(food_name);
        food_name.setBounds(290, 420, 240, 30);

        food_price.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        food_price.setForeground(new java.awt.Color(0, 153, 255));
        food_price.setText("Price");
        food_price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                food_priceActionPerformed(evt);
            }
        });
        food_info.add(food_price);
        food_price.setBounds(290, 460, 240, 30);

        food_pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/1_dood_cvr pic.png"))); // NOI18N
        food_info.add(food_pic);
        food_pic.setBounds(70, 380, 100, 90);

        jSeparator1.setBackground(new java.awt.Color(51, 102, 255));
        jSeparator1.setForeground(new java.awt.Color(51, 102, 255));
        food_info.add(jSeparator1);
        jSeparator1.setBounds(40, 350, 740, 10);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel16.setText("Food Picture");
        food_info.add(jLabel16);
        jLabel16.setBounds(70, 470, 100, 30);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setText("Id : ");
        food_info.add(jLabel17);
        jLabel17.setBounds(210, 390, 60, 20);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel18.setText("Name :");
        food_info.add(jLabel18);
        jLabel18.setBounds(210, 430, 60, 20);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel19.setText("Price :");
        food_info.add(jLabel19);
        jLabel19.setBounds(210, 470, 60, 20);

        admin_food_update_button.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        admin_food_update_button.setForeground(new java.awt.Color(153, 51, 255));
        admin_food_update_button.setText("Update");
        admin_food_update_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                admin_food_update_buttonActionPerformed(evt);
            }
        });
        food_info.add(admin_food_update_button);
        admin_food_update_button.setBounds(670, 430, 90, 30);

        input_food_pic_edit.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        input_food_pic_edit.setForeground(new java.awt.Color(255, 102, 0));
        input_food_pic_edit.setText("+");
        input_food_pic_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_food_pic_editActionPerformed(evt);
            }
        });
        food_info.add(input_food_pic_edit);
        input_food_pic_edit.setBounds(10, 380, 50, 30);

        base_panel.add(food_info);
        food_info.setBounds(280, 140, 810, 520);

        revenue_info.setBackground(new java.awt.Color(252, 249, 249));
        revenue_info.setLayout(null);

        table_02.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table_02.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Serial No.", "Fast Food Restaurent", "Biriyani House"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_02.setRowHeight(25);
        table_02.setSelectionBackground(new java.awt.Color(255, 51, 102));
        table_02.setSelectionForeground(new java.awt.Color(255, 255, 255));
        table_02.setShowHorizontalLines(true);
        table_02.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_02);

        revenue_info.add(jScrollPane1);
        jScrollPane1.setBounds(100, 100, 590, 200);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 153, 0));
        jLabel3.setText("Revenue Analyses");
        revenue_info.add(jLabel3);
        jLabel3.setBounds(240, 20, 260, 50);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 0, 204));
        jLabel8.setText("Biriyani House : ");
        revenue_info.add(jLabel8);
        jLabel8.setBounds(40, 450, 150, 30);

        profit_1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        profit_1.setForeground(new java.awt.Color(204, 0, 204));
        profit_1.setText("0");
        revenue_info.add(profit_1);
        profit_1.setBounds(210, 420, 150, 30);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 0, 204));
        jLabel12.setText("Fast Food Corner : ");
        revenue_info.add(jLabel12);
        jLabel12.setBounds(40, 420, 170, 30);

        profit_2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        profit_2.setForeground(new java.awt.Color(204, 0, 204));
        profit_2.setText("0");
        revenue_info.add(profit_2);
        profit_2.setBounds(210, 450, 150, 30);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 0, 204));
        jLabel13.setText("Details in piechart");
        revenue_info.add(jLabel13);
        jLabel13.setBounds(450, 380, 230, 30);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 0, 204));
        jLabel14.setText("Profit (Tk)");
        revenue_info.add(jLabel14);
        jLabel14.setBounds(120, 370, 140, 30);

        pie_chart_button_01.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        pie_chart_button_01.setForeground(new java.awt.Color(0, 153, 255));
        pie_chart_button_01.setText("Pie Chart");
        pie_chart_button_01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pie_chart_button_01ActionPerformed(evt);
            }
        });
        revenue_info.add(pie_chart_button_01);
        pie_chart_button_01.setBounds(500, 430, 120, 40);

        base_panel.add(revenue_info);
        revenue_info.setBounds(280, 140, 810, 520);

        customer_info1.setBackground(new java.awt.Color(254, 249, 249));
        customer_info1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 153, 0));
        jLabel2.setText("Payment Information");
        customer_info1.add(jLabel2);
        jLabel2.setBounds(280, 260, 320, 40);

        table_01.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table_01.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Number", "Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_01.setSelectionBackground(new java.awt.Color(255, 51, 102));
        table_01.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setViewportView(table_01);

        customer_info1.add(jScrollPane2);
        jScrollPane2.setBounds(110, 70, 590, 150);

        table_03.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table_03.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Serial", "Name", "Number", "Bill Id", "Bill amount", "payment", "Transaction"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_03.setRowHeight(25);
        table_03.setSelectionBackground(new java.awt.Color(255, 51, 102));
        table_03.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(table_03);

        customer_info1.add(jScrollPane3);
        jScrollPane3.setBounds(10, 320, 800, 180);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 153, 0));
        jLabel9.setText("User's Information");
        customer_info1.add(jLabel9);
        jLabel9.setBounds(300, 10, 260, 40);

        base_panel.add(customer_info1);
        customer_info1.setBounds(280, 140, 810, 520);

        food_button.setBackground(new java.awt.Color(102, 0, 204));
        food_button.setForeground(new java.awt.Color(153, 51, 255));
        food_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                food_buttonMouseClicked(evt);
            }
        });
        food_button.setLayout(null);

        admin_pic2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8_edit_property_100px.png"))); // NOI18N
        food_button.add(admin_pic2);
        admin_pic2.setBounds(30, 20, 70, 70);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Food Item");
        food_button.add(jLabel7);
        jLabel7.setBounds(20, 90, 100, 30);

        num_1.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        num_1.setForeground(new java.awt.Color(255, 153, 0));
        num_1.setText("0");
        food_button.add(num_1);
        num_1.setBounds(110, 30, 100, 50);

        base_panel.add(food_button);
        food_button.setBounds(320, 10, 220, 130);

        customer_button.setBackground(new java.awt.Color(102, 0, 204));
        customer_button.setForeground(new java.awt.Color(255, 255, 255));
        customer_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customer_buttonMouseClicked(evt);
            }
        });
        customer_button.setLayout(null);

        jLabel10.setForeground(new java.awt.Color(255, 153, 153));
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8_user_male_52px.png"))); // NOI18N
        customer_button.add(jLabel10);
        jLabel10.setBounds(30, 30, 70, 60);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Customer");
        customer_button.add(jLabel11);
        jLabel11.setBounds(10, 90, 100, 30);

        num_3.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        num_3.setForeground(new java.awt.Color(255, 153, 0));
        num_3.setText("0");
        customer_button.add(num_3);
        num_3.setBounds(100, 30, 100, 50);

        base_panel.add(customer_button);
        customer_button.setBounds(850, 10, 220, 130);

        revenue_button.setBackground(new java.awt.Color(102, 0, 204));
        revenue_button.setForeground(new java.awt.Color(153, 51, 255));
        revenue_button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                revenue_buttonMouseClicked(evt);
            }
        });
        revenue_button.setLayout(null);

        admin_pic3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/icons8_profit_80px.png"))); // NOI18N
        revenue_button.add(admin_pic3);
        admin_pic3.setBounds(20, 20, 70, 60);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Revenue");
        revenue_button.add(jLabel5);
        jLabel5.setBounds(20, 90, 100, 30);

        num_2.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        num_2.setForeground(new java.awt.Color(255, 153, 0));
        num_2.setText("0");
        revenue_button.add(num_2);
        num_2.setBounds(120, 30, 100, 50);

        base_panel.add(revenue_button);
        revenue_button.setBounds(580, 10, 230, 130);

        kGradientPanel3.setkEndColor(new java.awt.Color(102, 0, 204));
        kGradientPanel3.setkStartColor(new java.awt.Color(102, 0, 204));
        base_panel.add(kGradientPanel3);
        kGradientPanel3.setBounds(280, 10, 820, 130);

        kGradientPanel1.setkEndColor(new java.awt.Color(255, 153, 102));
        kGradientPanel1.setkStartColor(new java.awt.Color(204, 204, 204));
        base_panel.add(kGradientPanel1);
        kGradientPanel1.setBounds(280, 660, 820, 10);

        kGradientPanel2.setkEndColor(new java.awt.Color(255, 153, 102));
        kGradientPanel2.setkStartColor(new java.awt.Color(255, 153, 102));
        base_panel.add(kGradientPanel2);
        kGradientPanel2.setBounds(1090, 140, 10, 520);

        getContentPane().add(base_panel);
        base_panel.setBounds(0, 30, 1100, 670);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void log_in_barMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_log_in_barMouseEntered
        // TODO add your handling code here:
        log_in_bar.setBackground(new Color(204,0,0));
    }//GEN-LAST:event_log_in_barMouseEntered

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        //System.exit(0);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void log_in_barMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_log_in_barMouseExited
        // TODO add your handling code here:
        log_in_bar.setBackground(new Color(255,0,51));
    }//GEN-LAST:event_log_in_barMouseExited

    private void log_in_barMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_log_in_barMouseDragged
        // TODO add your handling code here:
        int xx=evt.getXOnScreen();
        int yy=evt.getYOnScreen();
        this.setLocation(xx-x,yy-y);
    }//GEN-LAST:event_log_in_barMouseDragged

    private void log_in_barMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_log_in_barMousePressed
        // TODO add your handling code here:
        x=evt.getX();
        y=evt.getY();
    }//GEN-LAST:event_log_in_barMousePressed

    private void jLabel6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MousePressed
        // TODO add your handling code here:
        log_in l=new log_in();
        l.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel6MousePressed

    private void image_upload_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_image_upload_buttonActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser=new JFileChooser();
        FileNameExtensionFilter fnwf=new FileNameExtensionFilter("PNG JPG AND JPEG","png","jpeg","jpg");
        fileChooser.addChoosableFileFilter(fnwf);
        int load=fileChooser.showOpenDialog(null);
        
        if(load==fileChooser.APPROVE_OPTION){
            f=fileChooser.getSelectedFile();
            path =f.getAbsolutePath();
            
            ImageIcon ii=new ImageIcon(path);
            Image img=ii.getImage().getScaledInstance(120,120,Image.SCALE_SMOOTH);
            admin_pic.setIcon(ii);
            //flag1=1;
            
            try{
             File f=new File(path);
                InputStream is=new FileInputStream(f);
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                
                byte[] buf = new byte[1024];
                for(int readNum;(readNum=is.read(buf))!=-1;){
                    bos.write(buf, 0, readNum);
                }
                pic = bos.toByteArray();
                flag1=1;
            }
            catch(IOException e){
                JOptionPane.showMessageDialog(null, e);
        }
            //save image to database................................................................ 
        }
    }//GEN-LAST:event_image_upload_buttonActionPerformed

    private void admin_logout_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_logout_buttonActionPerformed
        dispose();
        log_in  lg = new log_in();
        lg.setVisible(true);
    }//GEN-LAST:event_admin_logout_buttonActionPerformed

    private void customer_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customer_buttonMouseClicked
        // TODO add your handling code here: 
        customer_info1.setVisible(true);
        revenue_info.setVisible(false);
        food_info.setVisible(false);   
        
        customer_button.setBackground(new Color(136,86,187));
        revenue_button.setBackground(new Color(102,0,204));
        food_button.setBackground(new Color(102,0,204));
    }//GEN-LAST:event_customer_buttonMouseClicked

    private void revenue_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenue_buttonMouseClicked
        // TODO add your handling code here:
        customer_info1.setVisible(false);
        revenue_info.setVisible(true);
        food_info.setVisible(false);
        
        revenue_button.setBackground(new Color(136,86,187));
        customer_button.setBackground(new Color(102,0,204));
        food_button.setBackground(new Color(102,0,204));
    }//GEN-LAST:event_revenue_buttonMouseClicked

    private void food_buttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_food_buttonMouseClicked
        // TODO add your handling code here:
        customer_info1.setVisible(false);
        revenue_info.setVisible(false);
        food_info.setVisible(true);
        
        food_button.setBackground(new Color(136,86,187));
        customer_button.setBackground(new Color(102,0,204));
        revenue_button.setBackground(new Color(102,0,204));
    }//GEN-LAST:event_food_buttonMouseClicked

    private void food_infoAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_food_infoAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_food_infoAncestorAdded

    private void pie_chart_button_01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pie_chart_button_01ActionPerformed
        DefaultPieDataset pie=new DefaultPieDataset();
        pie.setValue("Fast Food Corner", total_1);
        pie.setValue("Biriyani House",total_2);
        
        JFreeChart chart=ChartFactory.createPieChart("Pie Chart",pie,true,true,true);
        PiePlot p=(PiePlot)chart.getPlot();
        
        ChartFrame frame=new ChartFrame("pie Chart",chart);
        frame.setVisible(true);
        frame.setSize(500,450);
        //double x,y;
      
        frame.setLocation(500,500);
    }//GEN-LAST:event_pie_chart_button_01ActionPerformed

    private void save_profile_button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_profile_button1ActionPerformed
        // TODO add your handling code here:
        if(flag1==1){
            try {
            //JOptionPane.showMessageDialog(null,"Image Successfully ......");
                String name=admin_name_input.getText();
                File f=new File(path);
                try {
                    InputStream is=new FileInputStream(f);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Admin_panel_01.class.getName()).log(Level.SEVERE, null, ex);
                }
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                String sql2="update admin_info set admin_pic=? , admin_name=? where id='"+15+"'";
                //String sql2= "insert into admin_info(admin_pic,admin_name) values(?,?)";
                //update  "update admin_info set admin_pic='"+is+"',admin_name='"+name+"'"
                pst=con.prepareStatement(sql2);
                pst.setBytes(1,pic);
                pst.setString(2,name);
               
                
                int inserted=pst.executeUpdate();
                if(inserted>0){
                    JOptionPane.showMessageDialog(null,"Admin info update successfully......");
                }
            } 
            catch (SQLException e) {
                  Logger.getLogger(e.getMessage());
            }   
        }else{
            JOptionPane.showMessageDialog(null,"Blank Input........");
        }
    }//GEN-LAST:event_save_profile_button1ActionPerformed

    private void food_tb_01MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_food_tb_01MouseClicked
        // TODO add your handling code here:
        int row = food_tb_01.getSelectedRow();
        String selection = food_tb_01.getModel().getValueAt(row, 0).toString();
        
        String sql = "select * from food_info_01 where food_id = " + selection;
        try {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if (rs.next()){
                food_name.setText(rs.getString(1));
                food_id.setText(rs.getString(4));
                food_price.setText(rs.getString(2));
                
                byte[] image_data=rs.getBytes(3);
                format =new ImageIcon(image_data);
                Image mm=format.getImage();
                Image img2=mm.getScaledInstance(100,100, Image.SCALE_SMOOTH);
                ImageIcon image=new ImageIcon(img2);
                food_pic.setIcon(image);
               
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        } 
            
    }//GEN-LAST:event_food_tb_01MouseClicked

    private void food_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_food_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_food_priceActionPerformed

    private void food_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_food_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_food_idActionPerformed

    private void food_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_food_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_food_nameActionPerformed

    private void admin_food_update_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_admin_food_update_buttonActionPerformed
        // TODO add your handling code here:
        if(flag2==1){
            try {
                flag2=0;
                String sql2="update food_info_01 set f_name=?,f_price=?,f_pic=? where food_id=? ";
                
                String n,p,id;
                n=food_name.getText();
                p=food_price.getText();
                id=food_id.getText();
                
                pst=con.prepareStatement(sql2);
                pst.setString(1,n);
                pst.setString(2,p);
                pst.setString(4,id);
                pst.setBytes(3,pic);
                
                int inserted=pst.executeUpdate();
                if(inserted>0){
                    food_info_database();
                    JOptionPane.showMessageDialog(null,"Edit successfully !!!");
                }
            } 
            catch (SQLException e) {
                  Logger.getLogger(e.getMessage());
            }   
        }else if(flag2==0){
            String n,p,id;
            id=food_id.getText();
            String name,price;
            //byte[] pic;
            try{
                String sql1 = "Select *from food_info_01 where food_id="+id;
                pst = con.prepareStatement(sql1);
                rs = pst.executeQuery();
            
                while(rs.next()){
                    //custom_num++;
                    //name=rs.getString(1);
                    //price=rs.getString(2);
                    pic=rs.getBytes(3);
                    //id=rs.getString(4);
                }
            }
            catch(SQLException ex){
                Logger.getLogger(Admin_panel_01.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                flag2=0;
                String sql2="update food_info_01 set f_name=?,f_price=?,f_pic=? where food_id=? ";
                
                
                n=food_name.getText();
                p=food_price.getText();
                
                //JOptionPane.showMessageDialog(null,"TRY........");
                pst=con.prepareStatement(sql2);
                pst.setString(1,n);
                pst.setString(2,p);
                pst.setString(4,id);
                pst.setBytes(3,pic);
                
                int inserted=pst.executeUpdate();
                if(inserted>0){
                    JOptionPane.showMessageDialog(null,"Information Update successfully !!!");
                    food_info_database();
                }
            } 
            catch (SQLException e) {
                  Logger.getLogger(e.getMessage());
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Blank Input");
        }
        
    }//GEN-LAST:event_admin_food_update_buttonActionPerformed

    private void input_food_pic_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_food_pic_editActionPerformed
        // TODO add your handling code here:
         JFileChooser fileChooser=new JFileChooser();
        FileNameExtensionFilter fnwf=new FileNameExtensionFilter("PNG JPG AND JPEG","png","jpeg","jpg");
        fileChooser.addChoosableFileFilter(fnwf);
        int load=fileChooser.showOpenDialog(null);
        
        if(load==fileChooser.APPROVE_OPTION){
            f=fileChooser.getSelectedFile();
            path =f.getAbsolutePath();
            
            ImageIcon ii=new ImageIcon(path);
            Image img=ii.getImage().getScaledInstance(120,120,Image.SCALE_SMOOTH);
            food_pic.setIcon(ii);
            //flag1=1;
            
            try{
             File f=new File(path);
                InputStream is=new FileInputStream(f);
                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                
                byte[] buf = new byte[1024];
                for(int readNum;(readNum=is.read(buf))!=-1;){
                    bos.write(buf, 0, readNum);
                }
                pic = bos.toByteArray();
                flag2=1;
            }
            catch(IOException e){
                JOptionPane.showMessageDialog(null, e);
        }
            //save image to database................................................................ 
        }
    }//GEN-LAST:event_input_food_pic_editActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_panel_01().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton admin_food_update_button;
    private keeptoo.KGradientPanel admin_left;
    private javax.swing.JButton admin_logout_button;
    private javax.swing.JLabel admin_name_input;
    private rojerusan.RSLabelImage admin_pic;
    private rojerusan.RSLabelImage admin_pic2;
    private rojerusan.RSLabelImage admin_pic3;
    private javax.swing.JPanel base_panel;
    private javax.swing.JPanel customer_button;
    private javax.swing.JPanel customer_info1;
    private javax.swing.JPanel food_button;
    private javax.swing.JTextField food_id;
    private javax.swing.JPanel food_info;
    private javax.swing.JTextField food_name;
    private rojerusan.RSLabelImage food_pic;
    private javax.swing.JTextField food_price;
    private javax.swing.JTable food_tb_01;
    private javax.swing.JButton image_upload_button;
    private javax.swing.JButton input_food_pic_edit;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private keeptoo.KGradientPanel kGradientPanel1;
    private keeptoo.KGradientPanel kGradientPanel2;
    private keeptoo.KGradientPanel kGradientPanel3;
    private javax.swing.JPanel log_in_bar;
    private javax.swing.JLabel num_1;
    private javax.swing.JLabel num_2;
    private javax.swing.JLabel num_3;
    private javax.swing.JButton pie_chart_button_01;
    private javax.swing.JLabel profit_1;
    private javax.swing.JLabel profit_2;
    private javax.swing.JPanel revenue_button;
    private javax.swing.JPanel revenue_info;
    private javax.swing.JButton save_profile_button1;
    private javax.swing.JTable table_01;
    private javax.swing.JTable table_02;
    private javax.swing.JTable table_03;
    // End of variables declaration//GEN-END:variables
}
