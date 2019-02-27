/*    */ package Settings;
/*    */ 
/*    */ import java.awt.Image;
/*    */ import java.awt.Toolkit;
/*    */ import javax.swing.ImageIcon;
/*    */ 
/*    */ public class GenSettings
/*    */ {
/*    */   public static ImageIcon getImageIcon(String location)
/*    */   {
/* 24 */     return new ImageIcon(Toolkit.getDefaultToolkit().getImage(GenSettings.class.getResource(location)));
/*    */   }
/*    */ 
/*    */   public static Image getImage(String location)
/*    */   {
/* 33 */     return Toolkit.getDefaultToolkit().getImage(GenSettings.class.getResource(location));
/*    */   }
/*    */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     Settings.GenSettings
 * JD-Core Version:    0.6.2
 */