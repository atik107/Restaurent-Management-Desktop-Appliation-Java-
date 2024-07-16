package restaurent_02;

import com.sun.tools.javac.Main;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public final class progress_page extends javax.swing.JFrame {
 
    int x,y;
    public progress_page() {
        initComponents();
        
       Thread t=new Thread(new Runnable(){
            @Override
            public void run() {
                 try{
                    for(int i=0;i<=100;i++){
                        if(i<45) Thread.sleep(10);
                        if(i>=45 && i<=55) Thread.sleep(120);
                        if(i>55 && i<70) Thread.sleep(10);
                        if(i>70 && i<95) Thread.sleep(10);
                        if(i>95) Thread.sleep(100);
                        
                        prog_bar.setValue(i);
                        prgs_int.setText(i+" %");
                        if(i==0){
                            jPanel_pic_01.setVisible(true);
                            jPanel_pic_2.setVisible(false);
                        }
                        if(i==50){
                            jPanel_pic_01.setVisible(false);
                            jPanel_pic_2.setVisible(true);
                        }
                        if(i==20){
                            msg1.setText("Loading Modules....");
                        }
                        if(i==40){
                            msg1.setText("Connecting to Database....");
                        }
                        if(i==60){
                            msg1.setText("Connection Successful !");
                        }
                        if(i==80){
                            msg1.setText("Launching Application....");
                        }
                    }
                }
                catch(Exception e){
                    JOptionPane.showMessageDialog(null,e);
                }
                dispose();
                multiple_restaurent mr=new multiple_restaurent();
                mr.setVisible(true);
            }
       });
       t.start();
    }
     
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login_left = new keeptoo.KGradientPanel();
        prgs_int = new javax.swing.JLabel();
        msg1 = new javax.swing.JLabel();
        jPanel_pic_01 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel_pic_2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        prog_bar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 555));
        setUndecorated(true);
        getContentPane().setLayout(null);

        login_left.setkEndColor(new java.awt.Color(153, 0, 153));
        login_left.setkGradientFocus(10);
        login_left.setkStartColor(new java.awt.Color(153, 0, 255));
        login_left.setLayout(null);

        prgs_int.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        prgs_int.setForeground(new java.awt.Color(255, 255, 255));
        prgs_int.setText("0 %");
        login_left.add(prgs_int);
        prgs_int.setBounds(820, 484, 60, 30);

        msg1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        msg1.setForeground(new java.awt.Color(255, 255, 255));
        msg1.setText("Loading .....");
        login_left.add(msg1);
        msg1.setBounds(40, 484, 200, 30);

        jPanel_pic_01.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_pic_01.setLayout(null);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/progress_pic_01.png"))); // NOI18N
        jPanel_pic_01.add(jLabel2);
        jLabel2.setBounds(80, 70, 730, 340);

        login_left.add(jPanel_pic_01);
        jPanel_pic_01.setBounds(10, 10, 860, 460);

        jPanel_pic_2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_pic_2.setLayout(null);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pictures/progress_pic_02.png"))); // NOI18N
        jPanel_pic_2.add(jLabel4);
        jLabel4.setBounds(80, 70, 730, 340);

        login_left.add(jPanel_pic_2);
        jPanel_pic_2.setBounds(10, 10, 860, 460);

        getContentPane().add(login_left);
        login_left.setBounds(0, 0, 900, 520);

        prog_bar.setBackground(new java.awt.Color(204, 204, 204));
        prog_bar.setForeground(new java.awt.Color(255, 153, 51));
        getContentPane().add(prog_bar);
        prog_bar.setBounds(0, 520, 900, 30);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        
        //progress_page pg=new progress_page();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new progress_page().setVisible(true);
            }
        });   
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel_pic_01;
    private javax.swing.JPanel jPanel_pic_2;
    private keeptoo.KGradientPanel login_left;
    private javax.swing.JLabel msg1;
    private javax.swing.JLabel prgs_int;
    private javax.swing.JProgressBar prog_bar;
    // End of variables declaration//GEN-END:variables
}
