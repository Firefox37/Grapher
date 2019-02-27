/*     */ package main;
/*     */ 
/*     */ import Settings.GenSettings;
/*     */ import Settings.GraphSettings;
/*     */ import calculator.CalculatorTab;
/*     */ import components.ExpressionTablePane;
/*     */ import components.VariableTablePane;
/*     */ import exceptions.InvalidVariableNameException;
/*     */ import expressions.EquationEvaluator;
/*     */ import expressions.ExpressionList;
/*     */ import expressions.IEvaluator;
/*     */ import expressions.VariableList;
/*     */ import graphing.GraphingTab;
/*     */ import java.awt.Color;
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.JCheckBoxMenuItem;
/*     */ import javax.swing.JColorChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JRadioButtonMenuItem;
/*     */ import javax.swing.JTabbedPane;
/*     */ 
/*     */ public class MainWindow extends JFrame
/*     */   implements ActionListener
/*     */ {
/*     */   private static final long serialVersionUID = -8481436709321974592L;
/*     */   JTabbedPane tabbedPane;
/*     */   CalculatorTab calculatorTab;
/*     */   GraphingTab graphingTab;
/*     */   JMenuBar menuBar;
/*     */   JMenu mnuFile;
/*     */   JMenu mnuSettings;
/*     */   JMenu mnuInfo;
/*     */   JMenu mnuLineWidth;
/*     */   JMenu mnuGraphColor;
/*     */   JMenu mnuPrecision;
/*     */   JMenu mnuAngleUnits;
/*     */   JMenuItem miExit;
/*     */   JMenuItem miSave;
/*     */   JMenuItem miAbout;
/*     */   JMenuItem miHelp;
/*     */   JMenuItem miLoad;
/*     */   JRadioButtonMenuItem rbDegrees;
/*     */   JRadioButtonMenuItem rbRadians;
/*     */   JRadioButtonMenuItem rbGradians;
/*     */   JRadioButtonMenuItem rbThin;
/*     */   JRadioButtonMenuItem rbMedium;
/*     */   JRadioButtonMenuItem rbThick;
/*     */   JRadioButtonMenuItem rbCustThickness;
/*     */   JRadioButtonMenuItem rbWhite;
/*     */   JRadioButtonMenuItem rbLightGray;
/*     */   JRadioButtonMenuItem rbGray;
/*     */   JRadioButtonMenuItem rbCustColor;
/*     */   JRadioButtonMenuItem rbNoAcc;
/*     */   JRadioButtonMenuItem rbSmallAcc;
/*     */   JRadioButtonMenuItem rbMedAcc;
/*     */   JRadioButtonMenuItem rbHighAcc;
/*     */   JCheckBoxMenuItem ckAntiAlias;
/*     */   JCheckBoxMenuItem ckDrawGrid;
/*     */   ButtonGroup bgAngle;
/*     */   ButtonGroup bgLineWidth;
/*     */   ButtonGroup bgGraphColor;
/*     */   ButtonGroup bgPrecision;
/*     */ 
/*     */   public MainWindow()
/*     */   {
/*  67 */     setDefaultCloseOperation(3);
/*  68 */     setTitle("Graphing Calculator");
/*  69 */     setIconImage(GenSettings.getImage("/images/calculator.png"));
/*     */ 
/*  71 */     createTabbedPane();
/*  72 */     createMenuBar();
/*     */ 
/*  74 */     setJMenuBar(this.menuBar);
/*  75 */     add(this.tabbedPane, "Center");
/*  76 */     setSize(520, 550);
/*  77 */     setMinimumSize(getSize());
/*     */   }
/*     */ 
/*     */   private void createMenuBar()
/*     */   {
/*  85 */     this.bgAngle = new ButtonGroup();
/*  86 */     this.bgLineWidth = new ButtonGroup();
/*  87 */     this.bgGraphColor = new ButtonGroup();
/*  88 */     this.bgPrecision = new ButtonGroup();
/*     */ 
/*  91 */     this.menuBar = new JMenuBar();
/*  92 */     this.mnuFile = new JMenu("File");
/*  93 */     this.mnuSettings = new JMenu("Settings");
/*  94 */     this.mnuInfo = new JMenu("Info");
/*  95 */     this.mnuAngleUnits = new JMenu("Angle Units");
/*  96 */     this.mnuLineWidth = new JMenu("Line Width");
/*  97 */     this.mnuGraphColor = new JMenu("Graph Background");
/*  98 */     this.mnuPrecision = new JMenu("Precision Modifier");
/*     */ 
/* 101 */     this.miSave = new JMenuItem("Save State", GenSettings.getImageIcon("/images/saveSmall.png"));
/* 102 */     this.miLoad = new JMenuItem("Load State", GenSettings.getImageIcon("/images/loadSmall.png"));
/* 103 */     this.miExit = new JMenuItem("Exit", GenSettings.getImageIcon("/images/exitSmall.png"));
/* 104 */     this.miAbout = new JMenuItem("About");
/* 105 */     this.miHelp = new JMenuItem("Help", GenSettings.getImageIcon("/images/helpSmall.png"));
/*     */ 
/* 108 */     this.rbDegrees = new JRadioButtonMenuItem("Degrees");
/* 109 */     this.rbRadians = new JRadioButtonMenuItem("Radians");
/* 110 */     this.rbGradians = new JRadioButtonMenuItem("Gradians");
/* 111 */     this.rbThin = new JRadioButtonMenuItem("Thin");
/* 112 */     this.rbMedium = new JRadioButtonMenuItem("Medium");
/* 113 */     this.rbThick = new JRadioButtonMenuItem("Thick");
/* 114 */     this.rbCustThickness = new JRadioButtonMenuItem("Custom");
/* 115 */     this.rbWhite = new JRadioButtonMenuItem("White");
/* 116 */     this.rbLightGray = new JRadioButtonMenuItem("Light Gray");
/* 117 */     this.rbGray = new JRadioButtonMenuItem("Gray");
/* 118 */     this.rbCustColor = new JRadioButtonMenuItem("Custom");
/* 119 */     this.rbNoAcc = new JRadioButtonMenuItem("None");
/* 120 */     this.rbSmallAcc = new JRadioButtonMenuItem("Small");
/* 121 */     this.rbMedAcc = new JRadioButtonMenuItem("Medium");
/* 122 */     this.rbHighAcc = new JRadioButtonMenuItem("High");
/*     */ 
/* 125 */     this.ckAntiAlias = new JCheckBoxMenuItem("Use Antialiasing");
/* 126 */     this.ckDrawGrid = new JCheckBoxMenuItem("Draw Grid (Still Buggy)");
/*     */ 
/* 129 */     this.mnuFile.add(this.miLoad);
/* 130 */     this.mnuFile.add(this.miSave);
/* 131 */     this.mnuFile.add(this.miExit);
/*     */ 
/* 134 */     this.bgAngle.add(this.rbDegrees);
/* 135 */     this.bgAngle.add(this.rbRadians);
/* 136 */     this.bgAngle.add(this.rbGradians);
/*     */ 
/* 139 */     this.bgLineWidth.add(this.rbThin);
/* 140 */     this.bgLineWidth.add(this.rbMedium);
/* 141 */     this.bgLineWidth.add(this.rbThick);
/* 142 */     this.bgLineWidth.add(this.rbCustThickness);
/*     */ 
/* 145 */     this.bgGraphColor.add(this.rbWhite);
/* 146 */     this.bgGraphColor.add(this.rbLightGray);
/* 147 */     this.bgGraphColor.add(this.rbGray);
/* 148 */     this.bgGraphColor.add(this.rbCustColor);
/*     */ 
/* 151 */     this.bgPrecision.add(this.rbNoAcc);
/* 152 */     this.bgPrecision.add(this.rbSmallAcc);
/* 153 */     this.bgPrecision.add(this.rbMedAcc);
/* 154 */     this.bgPrecision.add(this.rbHighAcc);
/*     */ 
/* 157 */     this.mnuGraphColor.add(this.rbWhite);
/* 158 */     this.mnuGraphColor.add(this.rbLightGray);
/* 159 */     this.mnuGraphColor.add(this.rbGray);
/* 160 */     this.mnuGraphColor.add(this.rbCustColor);
/*     */ 
/* 163 */     this.mnuLineWidth.add(this.rbThin);
/* 164 */     this.mnuLineWidth.add(this.rbMedium);
/* 165 */     this.mnuLineWidth.add(this.rbThick);
/* 166 */     this.mnuLineWidth.add(this.rbCustThickness);
/*     */ 
/* 169 */     this.mnuPrecision.add(this.rbNoAcc);
/* 170 */     this.mnuPrecision.add(this.rbSmallAcc);
/* 171 */     this.mnuPrecision.add(this.rbMedAcc);
/* 172 */     this.mnuPrecision.add(this.rbHighAcc);
/*     */ 
/* 175 */     this.mnuAngleUnits.add(this.rbDegrees);
/* 176 */     this.mnuAngleUnits.add(this.rbRadians);
/* 177 */     this.mnuAngleUnits.add(this.rbGradians);
/*     */ 
/* 180 */     this.mnuSettings.add(this.mnuAngleUnits);
/* 181 */     this.mnuSettings.add(this.mnuPrecision);
/* 182 */     this.mnuSettings.add(this.mnuLineWidth);
/* 183 */     this.mnuSettings.add(this.mnuGraphColor);
/* 184 */     this.mnuSettings.addSeparator();
/* 185 */     this.mnuSettings.add(this.ckAntiAlias);
/* 186 */     this.mnuSettings.add(this.ckDrawGrid);
/*     */ 
/* 189 */     this.mnuInfo.add(this.miHelp);
/* 190 */     this.mnuInfo.add(this.miAbout);
/*     */ 
/* 193 */     this.menuBar.add(this.mnuFile);
/* 194 */     this.menuBar.add(this.mnuSettings);
/* 195 */     this.menuBar.add(this.mnuInfo);
/*     */ 
/* 198 */     this.miLoad.addActionListener(this);
/* 199 */     this.miSave.addActionListener(this);
/* 200 */     this.miExit.addActionListener(this);
/* 201 */     this.miHelp.addActionListener(this);
/* 202 */     this.miAbout.addActionListener(this);
/* 203 */     this.rbDegrees.addActionListener(this);
/* 204 */     this.rbRadians.addActionListener(this);
/* 205 */     this.rbGradians.addActionListener(this);
/* 206 */     this.ckAntiAlias.addActionListener(this);
/* 207 */     this.ckDrawGrid.addActionListener(this);
/* 208 */     this.rbThin.addActionListener(this);
/* 209 */     this.rbMedium.addActionListener(this);
/* 210 */     this.rbThick.addActionListener(this);
/* 211 */     this.rbCustThickness.addActionListener(this);
/* 212 */     this.rbWhite.addActionListener(this);
/* 213 */     this.rbLightGray.addActionListener(this);
/* 214 */     this.rbGray.addActionListener(this);
/* 215 */     this.rbCustColor.addActionListener(this);
/* 216 */     this.rbNoAcc.addActionListener(this);
/* 217 */     this.rbSmallAcc.addActionListener(this);
/* 218 */     this.rbMedAcc.addActionListener(this);
/* 219 */     this.rbHighAcc.addActionListener(this);
/*     */ 
/* 222 */     this.rbRadians.doClick();
/* 223 */     this.rbThin.doClick();
/* 224 */     this.ckAntiAlias.doClick();
/* 225 */     this.rbLightGray.doClick();
/* 226 */     this.rbSmallAcc.doClick();
/*     */   }
/*     */ 
/*     */   private void createTabbedPane()
/*     */   {
/* 233 */     this.tabbedPane = new JTabbedPane();
/*     */ 
/* 235 */     this.calculatorTab = new CalculatorTab();
/* 236 */     this.graphingTab = new GraphingTab();
/* 237 */     this.tabbedPane.addTab("Calculator", GenSettings.getImageIcon("/images/calcSmall.png"), this.calculatorTab);
/* 238 */     this.tabbedPane.addTab("Graphing", GenSettings.getImageIcon("/images/graphSmall.png"), this.graphingTab);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 245 */     if (e.getSource() == this.miLoad) {
/* 246 */       FileDialog fd = new FileDialog(this, "Load State", 0);
/* 247 */       fd.setVisible(true);
/*     */ 
/* 249 */       if (fd.getFile() != null) {
/* 250 */         ObjectInputStream in = null;
/*     */         try {
/* 252 */           String filePath = fd.getDirectory() + fd.getFile();
/* 253 */           in = new ObjectInputStream(new FileInputStream(filePath));
/* 254 */           Storage store = (Storage)in.readObject();
/*     */ 
/* 256 */           ExpressionList.setExpressions(store.getExpressions());
/* 257 */           VariableList.setVariables(store.getVariables());
/* 258 */           ExpressionTablePane.refreshTable();
/* 259 */           VariableTablePane.refreshTable();
/*     */ 
/* 262 */           this.tabbedPane.remove(this.graphingTab);
/* 263 */           this.graphingTab = store.getGraphingTab();
/* 264 */           this.tabbedPane.addTab("Graphing", GenSettings.getImageIcon("/images/graphSmall.png"), this.graphingTab);
/* 265 */           setJMenuBar(this.menuBar);
/* 266 */           repaint();
/* 267 */           in.close();
/*     */         }
/*     */         catch (InvalidVariableNameException ex) {
/* 270 */           JOptionPane.showMessageDialog(this, ex.getMessage());
/*     */         } catch (ClassNotFoundException ex) {
/* 272 */           JOptionPane.showMessageDialog(this, ex.getMessage());
/*     */         } catch (IOException ex) {
/* 274 */           JOptionPane.showMessageDialog(this, ex.getMessage());
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 280 */     if (e.getSource() == this.miSave) {
/* 281 */       FileDialog fd = new FileDialog(this, "Save State", 1);
/* 282 */       fd.setVisible(true);
/*     */ 
/* 284 */       if (fd.getFile() != null) {
/* 285 */         Storage store = new Storage(ExpressionList.getExpressionList(), VariableList.getVariables(), this.graphingTab, this.menuBar);
/* 286 */         ObjectOutputStream objstream = null;
/*     */         try {
/* 288 */           String filePath = fd.getDirectory() + fd.getFile();
/* 289 */           objstream = new ObjectOutputStream(new FileOutputStream(filePath));
/* 290 */           objstream.writeObject(store);
/* 291 */           objstream.close();
/*     */         } catch (IOException ex) {
/* 293 */           Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
/*     */           try
/*     */           {
/* 296 */             objstream.close();
/*     */           } catch (IOException ex) {
/* 298 */             Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
/*     */           }
/*     */         }
/*     */         finally
/*     */         {
/*     */           try
/*     */           {
/* 296 */             objstream.close();
/*     */           } catch (IOException ex) {
/* 298 */             Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 304 */     if (e.getSource() == this.miExit) {
/* 305 */       dispose();
/*     */     }
/* 307 */     if (e.getSource() == this.miAbout) {
/* 308 */       JOptionPane.showMessageDialog(this, "Egor's Graphing Calculator is a desktop calculator \nthat implements the functionality of a TI-83.", "About", 1);
/*     */     }
/* 310 */     if (e.getSource() == this.miHelp) {
/* 311 */       JOptionPane.showMessageDialog(this, "Supports the following functions: \n\n*, +, -, *, /, ^, %, cos, sin, tan, acos, asin, atan,\nsqrt, sqr, log, min, max, ceil, floor, abs, neg, rnd.\n\nWhen in graph mode, click and drag the mouse to move the graph plane.", "Help", 1);
/*     */     }
/*     */ 
/* 315 */     if ((e.getSource() == this.rbDegrees) || (e.getSource() == this.rbRadians) || (e.getSource() == this.rbGradians)) {
/* 316 */       IEvaluator m = new EquationEvaluator();
/* 317 */       if (this.rbRadians.isSelected())
/* 318 */         m.setAngleUnits(1);
/* 319 */       else if (this.rbDegrees.isSelected())
/* 320 */         m.setAngleUnits(2);
/* 321 */       else if (this.rbGradians.isSelected()) {
/* 322 */         m.setAngleUnits(3);
/*     */       }
/*     */     }
/*     */ 
/* 326 */     if (e.getSource() == this.ckAntiAlias) {
/* 327 */       GraphSettings.setAntialiased(this.ckAntiAlias.isSelected());
/*     */     }
/* 329 */     if (e.getSource() == this.ckDrawGrid) {
/* 330 */       GraphSettings.setDrawGrid(this.ckDrawGrid.isSelected());
/*     */     }
/*     */ 
/* 334 */     if ((e.getSource() == this.rbThin) || (e.getSource() == this.rbMedium) || (e.getSource() == this.rbThick) || (e.getSource() == this.rbCustThickness)) {
/* 335 */       if (this.rbThin.isSelected())
/* 336 */         GraphSettings.setLineWidth(1.0F);
/* 337 */       else if (this.rbMedium.isSelected())
/* 338 */         GraphSettings.setLineWidth(1.5F);
/* 339 */       else if (this.rbThick.isSelected())
/* 340 */         GraphSettings.setLineWidth(2.0F);
/* 341 */       else if (this.rbCustThickness.isSelected()) {
/*     */         try {
/* 343 */           float thickness = Float.parseFloat(JOptionPane.showInputDialog(this.rbCustThickness, "Enter the custom thickness:"));
/* 344 */           GraphSettings.setLineWidth(thickness);
/*     */         }
/*     */         catch (Exception nfe) {
/* 347 */           JOptionPane.showMessageDialog(this.rbCustThickness, nfe.getMessage(), "Error", 0);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 353 */     if ((e.getSource() == this.rbWhite) || (e.getSource() == this.rbLightGray) || (e.getSource() == this.rbGray) || (e.getSource() == this.rbCustColor)) {
/* 354 */       if (this.rbWhite.isSelected()) {
/* 355 */         GraphSettings.setBgColor(Color.WHITE);
/* 356 */       } else if (this.rbLightGray.isSelected()) {
/* 357 */         GraphSettings.setBgColor(Color.LIGHT_GRAY);
/* 358 */       } else if (this.rbGray.isSelected()) {
/* 359 */         GraphSettings.setBgColor(Color.GRAY);
/* 360 */       } else if (this.rbCustColor.isSelected()) {
/* 361 */         Color clr = JColorChooser.showDialog(this.rbCustColor, "Color Chooser", GraphSettings.getBgColor());
/* 362 */         if (clr != null) {
/* 363 */           GraphSettings.setBgColor(clr);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 369 */     if ((e.getSource() == this.rbNoAcc) || (e.getSource() == this.rbSmallAcc) || (e.getSource() == this.rbMedAcc) || (e.getSource() == this.rbHighAcc)) {
/* 370 */       if (this.rbNoAcc.isSelected()) {
/* 371 */         GraphSettings.setMinCalcPerPixel(1);
/* 372 */         GraphSettings.setMaxCalcPerPixel(1);
/* 373 */       } else if (this.rbSmallAcc.isSelected()) {
/* 374 */         GraphSettings.setMinCalcPerPixel(1);
/* 375 */         GraphSettings.setMaxCalcPerPixel(10);
/* 376 */       } else if (this.rbMedAcc.isSelected()) {
/* 377 */         GraphSettings.setMinCalcPerPixel(1);
/* 378 */         GraphSettings.setMaxCalcPerPixel(20);
/* 379 */       } else if (this.rbHighAcc.isSelected()) {
/* 380 */         GraphSettings.setMinCalcPerPixel(1);
/* 381 */         GraphSettings.setMaxCalcPerPixel(30);
/*     */       }
/*     */     }
/* 384 */     repaint();
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     main.MainWindow
 * JD-Core Version:    0.6.2
 */