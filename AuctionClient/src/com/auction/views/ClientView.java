/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.auction.views;

import com.auction.client.AuctionClientServant;
import com.auction.exceptions.AuctionException;
import com.auction.models.Auction;
import com.auction.models.User;
import java.awt.Point;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author abacate
 */
public class ClientView extends javax.swing.JFrame {

     /**
     * @param args the command line arguments
     */
    
    private ArrayList<Auction> auctions;

    public ArrayList<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(ArrayList<Auction> auctions) {
        this.auctions = auctions;
        this.atualizeTable(this.auctions);
    }

    public ArrayList<Auction> getMyAuctions() {
        return myAuctions;
    }

    public void setMyAuctions(ArrayList<Auction> myAuctions) {
        this.myAuctions = myAuctions;
    }
    private ArrayList<Auction> myAuctions;
    private AuctionClientServant father;

    public AuctionClientServant getFather() {
        return father;
    }

    public void setFather(AuctionClientServant father) {
        this.father = father;
    }
    
    /**
     * Creates new form ClientView
     * @param f
     * @throws java.rmi.RemoteException
     */
    
    public ClientView(AuctionClientServant f) throws RemoteException {
        
        this.father = f;
        initComponents();
        
        String name = "";
        while("".equals(name)){
            name = JOptionPane.showInputDialog("What's your name?");
            if(name == null)
                System.exit(0);
            if ("".equals(name)){
                JOptionPane.showMessageDialog(null, "Please, Insert a valid username.");
            }
        }
        
        this.father.setClientInfo(new User(-1, name));
        
	    try {
		    auctions = this.father.getServer().listAuctions();
	    } catch (AuctionException ex) {
		    Logger.getLogger(ClientView.class.getName()).log(Level.SEVERE, null, ex);
		    String dialog_msg =	"Error in auction: \n" + 
			    	    	ex.getAuction().toString() +
			    		ex.getMessage();
                    this.father.errorNotification(dialog_msg);
	    }
        this.atualizeTable(auctions);
        
        this.lblUsername.setText("Welcome " + name + " !");
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        tableAuctions.setDefaultRenderer(Object.class , centerRenderer);
 
    }
    
    private void atualizeTable(ArrayList<Auction> auctions){
        DefaultTableModel model = (DefaultTableModel) this.tableAuctions.getModel();
        model.setNumRows(0);
        for (Auction a: auctions){
            Object[] temp = new Object[5];
            temp[0] = a.getId();
            temp[1] = a.getAuctioneer().getName();
            temp[2] = a.getProduct().getDesc();
            temp[3] = a.getHighest_bid().getValue();
	    if(a.getHighest_bid().getUser() != null)
	    	temp[4] = a.getHighest_bid().getUser().getName();
	    else
		temp[4] = "No Bid";

            model.addRow(temp);
        }
        
        System.out.println(model);
        this.tableAuctions.setModel(model);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUsername = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableAuctions = new javax.swing.JTable();
        menuBar = new javax.swing.JMenuBar();
        menuAuction = new javax.swing.JMenu();
        itemNewAuction = new javax.swing.JMenuItem();
        itemNewBid = new javax.swing.JMenuItem();
        itemEndAuction = new javax.swing.JMenuItem();
        menuView = new javax.swing.JMenu();
        itemAuctions = new javax.swing.JMenuItem();
        itemAuctionsInterested = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        lblUsername.setText("Username");
        lblUsername.setName("lblUsername"); // NOI18N

        tableAuctions.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Auction ID", "Auctioneer", "Item", "Actual Value", "Actual Winner"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableAuctions.setAutoscrolls(false);
        tableAuctions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableAuctionsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableAuctions);

        menuAuction.setText("Auction");

        itemNewAuction.setText("New Auction");
        itemNewAuction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNewAuctionActionPerformed(evt);
            }
        });
        menuAuction.add(itemNewAuction);

        itemNewBid.setText("New Bid");
        itemNewBid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNewBidActionPerformed(evt);
            }
        });
        menuAuction.add(itemNewBid);

        itemEndAuction.setText("End Auction");
        itemEndAuction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemEndAuctionActionPerformed(evt);
            }
        });
        menuAuction.add(itemEndAuction);

        menuBar.add(menuAuction);

        menuView.setText("View");

        itemAuctions.setText("Auctions");
        itemAuctions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAuctionsActionPerformed(evt);
            }
        });
        menuView.add(itemAuctions);

        itemAuctionsInterested.setText("Auctions Interested");
        menuView.add(itemAuctionsInterested);

        menuBar.add(menuView);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(301, 301, 301)
                .addComponent(lblUsername)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblUsername)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemNewBidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNewBidActionPerformed
        // TODO add your handling code here:
        NewBidView v = new NewBidView(this);
    }//GEN-LAST:event_itemNewBidActionPerformed

    private void itemAuctionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAuctionsActionPerformed

        try {
            this.auctions = this.father.getServer().listAuctions();
            this.atualizeTable(this.auctions);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientView.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (AuctionException ex) {
		    String dialog_msg =	"Error in auction: \n" + 
			    	    	ex.getAuction().toString() +
			    		ex.getMessage();
                    this.father.errorNotification(dialog_msg);
	}
    }//GEN-LAST:event_itemAuctionsActionPerformed

    private void itemNewAuctionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNewAuctionActionPerformed
        // TODO add your handling code here:
        NewAuctionView v = new NewAuctionView(this);
    }//GEN-LAST:event_itemNewAuctionActionPerformed

    private void itemEndAuctionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemEndAuctionActionPerformed
        // TODO add your handling code here:
        String s = JOptionPane.showInputDialog("What's the auction id?");
        if (s == null)
            return;
        
        int id = Integer.parseInt(s);
        try {
            this.auctions = this.father.getServer().listAuctions();
        } catch (RemoteException ex) {
            Logger.getLogger(ClientView.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (AuctionException ex) {
		    String dialog_msg =	"Error in auction: \n" + 
			    	    	ex.getAuction().toString() +
			    		ex.getMessage();
                    this.father.errorNotification(dialog_msg);
    	}
        Auction aux = null;
        for (Auction a: auctions) {
            if (a.getId() == id){
                aux = a;
                break;
            }
        }
        
        if(aux == null){
            JOptionPane.showMessageDialog(null, "No matching auction found!");
            return;
        }
        
        try {
            this.father.getServer().finishAuction(aux);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientView.class.getName()).log(Level.SEVERE, null, ex);
        }   catch (AuctionException ex) {
		    String dialog_msg =	"Error in auction: \n" + 
			    	    	ex.getAuction().toString() +
			    		ex.getMessage();
                    this.father.errorNotification(dialog_msg);
        }
    }//GEN-LAST:event_itemEndAuctionActionPerformed

    private void tableAuctionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableAuctionsMouseClicked
        // TODO add your handling code here:
        JTable table =(JTable) evt.getSource();
        Point p = evt.getPoint();
        int row = table.rowAtPoint(p);
        if (evt.getClickCount() == 2) {
            int id = (int) tableAuctions.getValueAt(row, 0);
            NewBidView v = new NewBidView(this);
            v.setTextAuctionID(Integer.toString(id));
            
        }
    }//GEN-LAST:event_tableAuctionsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemAuctions;
    private javax.swing.JMenuItem itemAuctionsInterested;
    private javax.swing.JMenuItem itemEndAuction;
    private javax.swing.JMenuItem itemNewAuction;
    private javax.swing.JMenuItem itemNewBid;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JMenu menuAuction;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuView;
    private javax.swing.JTable tableAuctions;
    // End of variables declaration//GEN-END:variables
}
