/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author weera
 */

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Print {
    
    Print(int ID, double amount, String name, int st_num){
        
        int passID  = ID;
        double passAmount = amount;
        String passName = name;
        int passSt_num = st_num;
        
    }
    public PageFormat getPageFormat(PrinterJob pj)
    {
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double bodyHeight = 3;  
    double headerHeight = 5.0;                  
    double footerHeight = 5.0;        
    double width = cm_to_pp(8); 
    double height = cm_to_pp(headerHeight+bodyHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(0,10,width,height - cm_to_pp(1));  
            
    pf.setOrientation(PageFormat.PORTRAIT);  
    pf.setPaper(paper);    

    return pf;
    }
    protected static double cm_to_pp(double cm)
    {            
        return toPPI(cm * 0.393600787);            
    }
 
    protected static double toPPI(double inch)
    {            
        return inch * 72d;            
    }
    


    public class BillPrintable implements Printable {
        public int print(Graphics graphics, PageFormat pageFormat,int pageIndex)
                throws PrinterException
        {
            ImageIcon icon=new ImageIcon("C:UsersccsDocumentsNetBeansProjectsvideo TestPOSInvoicesrcposinvoicemylogo.jpg"); 
            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {
                Graphics2D g2d = (Graphics2D) graphics;                    
                double width = pageFormat.getImageableWidth();                               
                g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY());
                
                try{
                int y=20;
                int yShift = 10;
                int headerRectHeight=15;
                
                g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
                g2d.drawString("-------------------------------------",12,y);y+=yShift;
                g2d.drawString("         CodeGuid.com        ",12,y);y+=yShift;
                g2d.drawString("   No 00000 Address Line One ",12,y);y+=yShift;
                g2d.drawString("   Address Line 02 SRI LANKA ",12,y);y+=yShift;
                g2d.drawString("   www.facebook.com/CodeGuid ",12,y);y+=yShift;
                g2d.drawString("        +94700000000      ",12,y);y+=yShift;
                g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;

                g2d.drawString(" Item Name                  Price   ",10,y);y+=yShift;
                g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
                
                g2d.drawString("-------------------------------------",10,y);y+=yShift;
                g2d.drawString(" Total amount:               "+10+"   ",10,y);y+=yShift;
                g2d.drawString("-------------------------------------",10,y);y+=yShift;
                g2d.drawString(" Cash      :                 "+01+"   ",10,y);y+=yShift;
                g2d.drawString("-------------------------------------",10,y);y+=yShift;
                g2d.drawString(" Balance   :                 "+20+"   ",10,y);y+=yShift;

                g2d.drawString("*************************************",10,y);y+=yShift;
                g2d.drawString("       THANK YOU COME AGAIN            ",10,y);y+=yShift;
                g2d.drawString("*************************************",10,y);y+=yShift;
                g2d.drawString("       SOFTWARE BY:CODEGUID          ",10,y);y+=yShift;
                g2d.drawString("   CONTACT: contact@codeguid.com       ",10,y);y+=yShift;
                
                }
                
                catch(Exception e){
                    e.printStackTrace();
                }
                result = PAGE_EXISTS;
            }
            return result;
        }
    }
}


