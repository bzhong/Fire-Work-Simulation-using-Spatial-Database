package mainProgram;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author qianqianzhong
 */
public class HW3GUI extends javax.swing.JFrame {

    /**
     * Creates new form HW3GUI
     */
    public HW3GUI(String[] args) throws SQLException, IOException {
        initComponents();
        prevQueries.setText("Populating data...please wait a moment.\n");
        populate = new Populate(args);
        populate.execPopulate();
        prevQueries.setText("Populating data completed.\n");
        hw3 = new HW3();
        counter = 1;
        isAllowedToDraw = false;
        userPolygon = new ArrayList<>();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        mymap = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        activeBldg = new javax.swing.JCheckBox();
        activeBldgOnFire = new javax.swing.JCheckBox();
        activeHydrants = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        wholeRegion = new javax.swing.JRadioButton();
        rangeQuery = new javax.swing.JRadioButton();
        findNeighborBldg = new javax.swing.JRadioButton();
        findClosestHydrant = new javax.swing.JRadioButton();
        submitQuery = new javax.swing.JButton();
        mouseLocation = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        prevQueries = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Qianqian Zhong W1105442");
        setSize(new java.awt.Dimension(1280, 768));

        mymap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mainProgram/map.jpg"))); // NOI18N
        mymap.setSize(new java.awt.Dimension(820, 580));
        mymap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mymapMouseClicked(evt);
            }
        });
        mymap.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                mymapMouseMoved(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Active Feature Type"));

        activeBldg.setText("Buildings");

        activeBldgOnFire.setText("Buildings on Fire");

        activeHydrants.setText("Hydrants");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(activeBldg)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(activeBldgOnFire)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(activeHydrants))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(activeBldg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(activeBldgOnFire)
                    .addComponent(activeHydrants))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Query"));

        buttonGroup1.add(wholeRegion);
        wholeRegion.setText("Whole Region");
        wholeRegion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wholeRegionActionPerformed(evt);
            }
        });

        buttonGroup1.add(rangeQuery);
        rangeQuery.setText("Range Query");
        rangeQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rangeQueryActionPerformed(evt);
            }
        });

        buttonGroup1.add(findNeighborBldg);
        findNeighborBldg.setText("Find Neighbor Buildings");
        findNeighborBldg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findNeighborBldgActionPerformed(evt);
            }
        });

        buttonGroup1.add(findClosestHydrant);
        findClosestHydrant.setText("Find Closest Fire Hydrants");
        findClosestHydrant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findClosestHydrantActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wholeRegion)
                    .addComponent(rangeQuery)
                    .addComponent(findNeighborBldg)
                    .addComponent(findClosestHydrant))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wholeRegion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rangeQuery)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(findNeighborBldg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(findClosestHydrant)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        submitQuery.setText("Submit Query");
        submitQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitQueryActionPerformed(evt);
            }
        });

        mouseLocation.setText("Current Mouse Location: ");

        prevQueries.setColumns(20);
        prevQueries.setLineWrap(true);
        prevQueries.setRows(5);
        jScrollPane1.setViewportView(prevQueries);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mouseLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1)
                            .addComponent(mymap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(submitQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(mymap)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(mouseLocation))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(submitQuery)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitQueryActionPerformed
        // TODO add your handling code here:
        boolean actBldg = activeBldg.isSelected();
        boolean actBldgOnFire = activeBldgOnFire.isSelected();
        boolean actHydrant = activeHydrants.isSelected();
        try {
            if (wholeRegion.isSelected()) {
                //whole region
                ResultSet bldgResultSet = hw3.execBldgQueryForWholeRegion(actBldg, actBldgOnFire, this);
                ResultSet hydrantResultSet = null;
                BufferedImage surface = new BufferedImage(820, 580, BufferedImage.TYPE_INT_RGB);
                Graphics g = surface.getGraphics();
                g.drawImage(ImageIO.read(new File("map.jpg")), 0, 0, null);
                if (actHydrant == true) {
                    hydrantResultSet = hw3.execHydrantQueryForWholeRegion(actHydrant, this);
                }
                if (bldgResultSet != null) {
                    hw3.displayBldg(bldgResultSet, g);
                }
                if (hydrantResultSet != null) {
                    hw3.displayHydrant(hydrantResultSet, g);
                }
                mymap.setIcon(new javax.swing.ImageIcon(surface));
            } else if (rangeQuery.isSelected()) {
                ResultSet bldgResultSet = hw3.execBldgQueryForRangeQuery(actBldg, actBldgOnFire, userPolygon, this);
                ResultSet hydrantResultSet = null;
                BufferedImage surface = new BufferedImage(820, 580, BufferedImage.TYPE_INT_RGB);
                Graphics g = surface.getGraphics();
                g.drawImage(imageForRangeQuery, 0, 0, null);
                if (actHydrant == true) {
                    hydrantResultSet = hw3.execHydrantQueryForRangeQuery(actHydrant, userPolygon, this);
                }
                if (bldgResultSet != null) {
                    hw3.displayBldg(bldgResultSet, g);
                }
                if (hydrantResultSet != null) {
                    hw3.displayHydrant(hydrantResultSet, g);
                }
                mymap.setIcon(new javax.swing.ImageIcon(surface));
                isAllowedToDraw = false;
            } else if (findNeighborBldg.isSelected()) {
                //find neighbor buildings
                ResultSet bldgResultSet = hw3.findBldgNearby(this);
                Image surface = ((ImageIcon)mymap.getIcon()).getImage();
                Graphics g = surface.getGraphics();
                if (bldgResultSet != null) {
                    hw3.displayBldg(bldgResultSet, g);
                }
                mymap.setIcon(new javax.swing.ImageIcon(surface));
            } else if (findClosestHydrant.isSelected()) {
                //find closest fire hydrants
                ResultSet bldgOnFireResultSet = hw3.findBldgOnFire(this);
                ResultSet hydrantResultSet = hw3.findNearestHydrant(this);
                BufferedImage surface = new BufferedImage(820, 580, BufferedImage.TYPE_INT_RGB);
                Graphics g = surface.getGraphics();
                g.drawImage(ImageIO.read(new File("map.jpg")), 0, 0, null);
                if (bldgOnFireResultSet != null) {
                    hw3.displayBldg(bldgOnFireResultSet, g);
                }
                if (hydrantResultSet != null) {
                    hw3.displayHydrant(hydrantResultSet, g);
                }
                mymap.setIcon(new javax.swing.ImageIcon(surface));
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(HW3GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(HW3GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(HW3GUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(HW3GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_submitQueryActionPerformed

    private void mymapMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mymapMouseMoved
        // TODO add your handling code here:
        mouseLocation.setText("Current Mouse Location: (" + evt.getX() + ", " + evt.getY() + ")");
    }//GEN-LAST:event_mymapMouseMoved

    private void rangeQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rangeQueryActionPerformed
        try {
            // TODO add your handling code here:
            isAllowedToDraw = true;
            userPolygon.clear();
            BufferedImage surface = new BufferedImage(820, 580, BufferedImage.TYPE_INT_RGB);
            Graphics g = surface.getGraphics();
            g.drawImage(ImageIO.read(new File("map.jpg")), 0, 0, null);
            mymap.setIcon(new javax.swing.ImageIcon(surface));
        } catch (IOException ex) {
            Logger.getLogger(HW3GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rangeQueryActionPerformed

    private void mymapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mymapMouseClicked
        // TODO add your handling code here:
        if (!isAllowedToDraw)
            return;
        if (rangeQuery.isSelected()) {
            if (SwingUtilities.isLeftMouseButton(evt)) {
                Image image = ((ImageIcon)mymap.getIcon()).getImage();
                Graphics g = image.getGraphics();
                g.setColor(Color.RED);
                if (!userPolygon.isEmpty()) {
                    int point1x = userPolygon.get(userPolygon.size() - 2);
                    int point1y = userPolygon.get(userPolygon.size() - 1);
                    g.drawLine(point1x, point1y, evt.getX(), evt.getY());
                    imageForRangeQuery = image;
                    mymap.setIcon(new ImageIcon(image));
                }
                userPolygon.add(evt.getX());
                userPolygon.add(evt.getY());
            }
            else if (SwingUtilities.isRightMouseButton(evt)) {
                isAllowedToDraw = false;
            }
        }
    }//GEN-LAST:event_mymapMouseClicked

    private void wholeRegionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wholeRegionActionPerformed
        try {
            // TODO add your handling code here:
            BufferedImage surface = new BufferedImage(820, 580, BufferedImage.TYPE_INT_RGB);
            Graphics g = surface.getGraphics();
            g.drawImage(ImageIO.read(new File("map.jpg")), 0, 0, null);
            mymap.setIcon(new javax.swing.ImageIcon(surface));
        } catch (IOException ex) {
            Logger.getLogger(HW3GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_wholeRegionActionPerformed

    private void findNeighborBldgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findNeighborBldgActionPerformed
        try {
            // TODO add your handling code here:
            BufferedImage surface = new BufferedImage(820, 580, BufferedImage.TYPE_INT_RGB);
            Graphics g = surface.getGraphics();
            g.drawImage(ImageIO.read(new File("map.jpg")), 0, 0, null);
            ResultSet bldgOnFireResultSet = hw3.findBldgOnFire(this);
            if (bldgOnFireResultSet != null) {
                hw3.displayBldg(bldgOnFireResultSet, g);
            }
            mymap.setIcon(new javax.swing.ImageIcon(surface));
        } catch (IOException | SQLException ex) {
            Logger.getLogger(HW3GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_findNeighborBldgActionPerformed

    private void findClosestHydrantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findClosestHydrantActionPerformed
        try {
            // TODO add your handling code here:
            BufferedImage surface = new BufferedImage(820, 580, BufferedImage.TYPE_INT_RGB);
            Graphics g = surface.getGraphics();
            g.drawImage(ImageIO.read(new File("map.jpg")), 0, 0, null);
            mymap.setIcon(new javax.swing.ImageIcon(surface));
        } catch (IOException ex) {
            Logger.getLogger(HW3GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_findClosestHydrantActionPerformed

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
            java.util.logging.Logger.getLogger(HW3GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HW3GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HW3GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HW3GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        final String[] datafile = args;
        /* Create and display the form */
        (new Thread(new Runnable() {
            public void run() {
                try {
                    new HW3GUI(datafile).setVisible(true);
                } catch (SQLException | IOException ex) {
                    Logger.getLogger(HW3GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        })).start();
    }

    public javax.swing.JCheckBox getActiveBldg() {
        return activeBldg;
    }

    public javax.swing.JCheckBox getActiveBldgOnFire() {
        return activeBldgOnFire;
    }

    public javax.swing.JCheckBox getActiveHydrants() {
        return activeHydrants;
    }
    
    public javax.swing.JTextArea getTextArea() {
        return prevQueries;
    }

    private Populate populate;
    private HW3 hw3;
    int counter;
    ArrayList<Integer> userPolygon;
    boolean isAllowedToDraw;
    Image imageForRangeQuery;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activeBldg;
    private javax.swing.JCheckBox activeBldgOnFire;
    private javax.swing.JCheckBox activeHydrants;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton findClosestHydrant;
    private javax.swing.JRadioButton findNeighborBldg;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel mouseLocation;
    private javax.swing.JLabel mymap;
    private javax.swing.JTextArea prevQueries;
    private javax.swing.JRadioButton rangeQuery;
    private javax.swing.JButton submitQuery;
    private javax.swing.JRadioButton wholeRegion;
    // End of variables declaration//GEN-END:variables
}
