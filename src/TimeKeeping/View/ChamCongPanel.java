/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package TimeKeeping.View;

import TimeKeeping.DBC.DBConnect;
import TimeKeeping.Model.DiemDanh;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.io.IOException;
import javax.imageio.ImageIO;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import static org.bytedeco.opencv.global.opencv_imgcodecs.imencode;
import org.bytedeco.opencv.global.opencv_imgproc;
import static org.bytedeco.opencv.global.opencv_imgproc.COLOR_BGRA2GRAY;
import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;
import static org.bytedeco.opencv.global.opencv_imgproc.rectangle;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_face.LBPHFaceRecognizer;
import org.bytedeco.opencv.opencv_objdetect.CascadeClassifier;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;

/**
 *
 * @author Ad
 */
public class ChamCongPanel extends javax.swing.JPanel {
    
    String source = "C:/javacv resource/opencv/sources/data/haarcascades/haarcascade_frontalface_alt.xml"; //paste here
    CascadeClassifier cascade = new CascadeClassifier(source);
    LBPHFaceRecognizer recognizer = LBPHFaceRecognizer.create();
    BytePointer mem = new BytePointer();
    
    private ChamCongPanel.DaemonThread myThread = null;
    int count = 0;
    //JavaCV
    VideoCapture webSource = null;
    Mat cameraImage = new Mat();
    int idnhanvien;
    DiemDanh dd = new DiemDanh();
    
    /**
     * Creates new form ChamCongPanel
     */
    public ChamCongPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Cam = new javax.swing.JPanel();
        turnOn = new javax.swing.JButton();
        jlbID = new javax.swing.JLabel();
        jlbHoTen = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        javax.swing.GroupLayout CamLayout = new javax.swing.GroupLayout(Cam);
        Cam.setLayout(CamLayout);
        CamLayout.setHorizontalGroup(
            CamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        CamLayout.setVerticalGroup(
            CamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 234, Short.MAX_VALUE)
        );

        turnOn.setText("Bật/Tắt Cam");
        turnOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                turnOnActionPerformed(evt);
            }
        });

        jlbID.setText("ID nhân viên");

        jlbHoTen.setText("Họ tên Nhân Viên");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(88, Short.MAX_VALUE)
                .addComponent(jlbID, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlbHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Cam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(turnOn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Cam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(turnOn)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbHoTen)
                    .addComponent(jlbID))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void turnOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_turnOnActionPerformed
        // TODO add your handling code here:
        startCamera();        
        recognizer.read("D:\\TimeKeepingProject\\fileTrain\\TimeclassifierLBPH.yml");
        recognizer.setThreshold(80);
    }//GEN-LAST:event_turnOnActionPerformed
    
    class DaemonThread implements Runnable {

        protected volatile boolean runnable = false;

        @Override
        public void run() {
            synchronized (this) {
                while (runnable) {
                    try {
                        if (webSource.grab()) {
                            webSource.retrieve(cameraImage);
                            Graphics g = Cam.getGraphics();
                            Mat imageColor = new Mat();
                            imageColor = cameraImage;

                            Mat imageGray = new Mat(); 
                            cvtColor(imageColor, imageGray, COLOR_BGRA2GRAY);

                            RectVector detectedFaces = new RectVector(); 
                            cascade.detectMultiScale(imageColor, detectedFaces, 1.1, 1, 1, new Size(150, 150), new Size(500, 500));

                            for (int i = 0; i < detectedFaces.size(); i++) { 
                                Rect dadosFace = detectedFaces.get(0);

                                rectangle(imageColor, dadosFace, new Scalar(255, 255, 0, 2), 3, 0, 0);
                                
                                //rectangle(frame, dadosFace, new Scalar(0, 255, 0, 3), 3, 0, 0);
                                Mat faceCapturada = new Mat(imageGray, dadosFace);
                                opencv_imgproc.resize(faceCapturada, faceCapturada, new Size(160, 160));

                                IntPointer rotulo = new IntPointer(1);
                                DoublePointer confidence = new DoublePointer(1);
                                
                                recognizer.predict(faceCapturada, rotulo, confidence);
                                int prediction = rotulo.get(0); 
                                idnhanvien = prediction;
                                
                                info();
                            }

                            imencode(".bmp", cameraImage, mem);
                            Image im = ImageIO.read(new ByteArrayInputStream(mem.getStringBytes()));
                            BufferedImage buff = (BufferedImage) im;
                            try {
                                if (g.drawImage(buff, 0, 0, Cam.getWidth(), Cam.getHeight(), 0, 0, buff.getWidth(), buff.getHeight(), null)) {
                                    if (runnable == false) {
                                        this.wait();
                                    }
                                }
                            } catch (Exception e) {
                            }
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    
    private void info() {
        new Thread() {
            @Override
            public void run() {
                Connection cons = DBConnect.GetConnection();
                String sql = "SELECT * FROM nhanvien WHERE idnhanvien = " + String.valueOf(idnhanvien);
                try {
                    
                    PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
                    PreparedStatement ps1 = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                    ResultSet rs = ps.executeQuery();
                    
                    while (rs.next()) {
                        jlbID.setText(String.valueOf(idnhanvien));
                        jlbHoTen.setText(rs.getString("hoten"));
                        long millis=System.currentTimeMillis(); 
                        java.sql.Date date=new java.sql.Date(millis);
                        java.sql.Time time=new java.sql.Time(millis);
                        if(idnhanvien > 0){
                            dd.setNgay(date);
                            dd.setId(idnhanvien);
                            dd.setGio(time);
                            diemDanh(dd);
                            stopCamera();
                        }
                    }

                    ps.close();
                    cons.close();

                } catch (Exception e) {
                    System.err.println(e);
                }
                
            }
        }.start();
    }
    
    public void diemDanh(DiemDanh dd){
        
        try{
            Connection cons = DBConnect.GetConnection();
            String sql = "INSERT INTO diemdanh(ngay, idnhanvien, gio) VALUES(?, ?, ?) ON DUPLICATE KEY UPDATE ngay = VALUES(ngay), idnhanvien = VALUES(idnhanvien), gio = VALUES(gio);";
            PreparedStatement ps = cons.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setDate(1, dd.getNgay());
            ps.setInt(2, dd.getId());
            ps.setTime(3, dd.getGio());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();            
            ps.close();
            cons.close();
        }catch(Exception e){System.err.println(e);}
        
    }
    
    public void stopCamera() {
        myThread.runnable = false;
        webSource.release();
    }

    public void startCamera() {
        new Thread() {
            @Override
            public void run() {
                webSource = new VideoCapture(0);

                myThread = new ChamCongPanel.DaemonThread();
                Thread t = new Thread(myThread);
                t.setDaemon(true);
                myThread.runnable = true;
                t.start();
            }
        }.start();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Cam;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlbHoTen;
    private javax.swing.JLabel jlbID;
    private javax.swing.JButton turnOn;
    // End of variables declaration//GEN-END:variables
}
