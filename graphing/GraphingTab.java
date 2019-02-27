/*     */ package graphing;
/*     */ 
/*     */ import Settings.GenSettings;
/*     */ import components.SmartTextField;
/*     */ import equations.Equation;
/*     */ import equations.EquationInput;
/*     */ import exceptions.InvalidBoundsException;
/*     */ import expressions.Expression;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import java.util.Vector;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class GraphingTab extends JPanel
/*     */   implements ActionListener, MouseWheelListener, MouseMotionListener, MouseListener, FocusListener, KeyListener
/*     */ {
/*     */   private static final long serialVersionUID = -2431506973009907432L;
/*  60 */   private int equationCount = 3;
/*  61 */   private int xPrev = 0; private int yPrev = 0;
/*  62 */   private double xClicked = 0.0D; private double yClicked = 0.0D;
/*     */   private GraphPanel graphPanel;
/*     */   private JPanel eastPanel;
/*     */   private JPanel southPanel;
/*     */   private JPanel directionPanel;
/*     */   private JPanel boundsPanel;
/*     */   private JPanel equationPanel;
/*     */   private JPanel buttonPanel;
/*     */   private JPanel cursorCordPanel;
/*     */   private JLabel lblMinX;
/*     */   private JLabel lblMaxX;
/*     */   private JLabel lblMinY;
/*     */   private JLabel lblMaxY;
/*     */   private JLabel lblCursorX;
/*     */   private JLabel lblCursorY;
/*     */   private JTextField txtMinX;
/*     */   private JTextField txtMaxX;
/*     */   private JTextField txtMinY;
/*     */   private JTextField txtMaxY;
/*     */   private JButton btnGraph;
/*     */   private JButton btnLeft;
/*     */   private JButton btnRight;
/*     */   private JButton btnUp;
/*     */   private JButton btnDown;
/*     */   private JButton btnCenter;
/*     */   private JButton btnAddEquation;
/*     */   private JButton btnRemoveEquation;
/*     */   private JButton btnZoomIn;
/*     */   private JButton btnZoomOut;
/*     */   private JScrollPane equationScrollPane;
/*     */   private JPopupMenu mnuGraphRightClick;
/*     */   private JMenu mnuDrawLine;
/*     */   private JMenu mnuPoints;
/*     */   private JMenuItem miZoomIn;
/*     */   private JMenuItem miZoomOut;
/*     */   private JMenuItem miPlotPoint;
/*     */   private JMenuItem miRemovePoint;
/*     */   private JMenuItem miDrawLineBetweenPoints;
/*     */   private JMenuItem miDrawTangentLine;
/*     */   private JMenuItem miPlotMinPoint;
/*     */   private JMenuItem miPlotMaxPoint;
/*     */   private JMenuItem miRemoveAllPoints;
/*     */   private JMenuItem miClearGraph;
/*     */   private JMenuItem miViewTableOfPoints;
/*     */   private JMenuItem miViewTableOfValues;
/*     */   private JMenuItem miPlotAtXValue;
/*     */ 
/*     */   public GraphingTab()
/*     */   {
/*  80 */     setLayout(new BorderLayout());
/*     */ 
/*  82 */     createLayout();
/*  83 */     createRightClickMenu();
/*     */ 
/*  85 */     this.graphPanel.drawGrid();
/*  86 */     add(this.graphPanel, "Center");
/*  87 */     add(this.eastPanel, "East");
/*  88 */     add(this.southPanel, "South");
/*     */ 
/*  90 */     this.graphPanel.repaint();
/*  91 */     resetStats();
/*     */   }
/*     */ 
/*     */   private void createLayout()
/*     */   {
/*  99 */     this.graphPanel = new GraphPanel();
/* 100 */     this.graphPanel.setBorder(BorderFactory.createEtchedBorder(0));
/* 101 */     this.eastPanel = new JPanel();
/* 102 */     this.eastPanel.setLayout(new BoxLayout(this.eastPanel, 1));
/* 103 */     this.eastPanel.setPreferredSize(new Dimension(140, 100));
/* 104 */     this.southPanel = new JPanel();
/* 105 */     this.southPanel.setLayout(new FlowLayout(0));
/* 106 */     this.directionPanel = new JPanel(new GridLayout(3, 3));
/* 107 */     this.directionPanel.setMaximumSize(new Dimension(140, 100));
/* 108 */     this.boundsPanel = new JPanel(new GridLayout(0, 2));
/* 109 */     this.boundsPanel.setBorder(BorderFactory.createEtchedBorder());
/* 110 */     this.boundsPanel.setMaximumSize(new Dimension(140, 100));
/* 111 */     this.equationPanel = new JPanel();
/* 112 */     this.equationPanel.setLayout(new GridLayout(0, 1));
/* 113 */     this.equationScrollPane = new JScrollPane(this.equationPanel);
/* 114 */     this.equationScrollPane.setPreferredSize(new Dimension(360, 125));
/* 115 */     this.buttonPanel = new JPanel();
/* 116 */     this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, 1));
/* 117 */     this.cursorCordPanel = new JPanel(new GridLayout(0, 1));
/* 118 */     this.cursorCordPanel.setMaximumSize(new Dimension(120, 40));
/*     */ 
/* 121 */     this.btnZoomIn = new JButton(GenSettings.getImageIcon("/images/zoomIn.png"));
/* 122 */     this.btnZoomOut = new JButton(GenSettings.getImageIcon("/images/zoomOut.png"));
/*     */ 
/* 124 */     this.btnLeft = new JButton(GenSettings.getImageIcon("/images/arrowLeft.png"));
/* 125 */     this.btnRight = new JButton(GenSettings.getImageIcon("/images/arrowRight.png"));
/* 126 */     this.btnUp = new JButton(GenSettings.getImageIcon("/images/arrowUp.png"));
/* 127 */     this.btnDown = new JButton(GenSettings.getImageIcon("/images/arrowDown.png"));
/* 128 */     this.btnCenter = new JButton(GenSettings.getImageIcon("/images/center.png"));
/*     */ 
/* 131 */     this.btnAddEquation = new JButton("Equation", GenSettings.getImageIcon("/images/addSmall.png"));
/* 132 */     this.btnRemoveEquation = new JButton("Equation", GenSettings.getImageIcon("/images/removeSmall.png"));
/* 133 */     this.btnGraph = new JButton("Graph");
/*     */ 
/* 136 */     this.lblMaxX = new JLabel("Max X:");
/* 137 */     this.txtMaxX = new JTextField(5);
/* 138 */     this.lblMinX = new JLabel("Min X:");
/* 139 */     this.txtMinX = new JTextField(5);
/* 140 */     this.lblMaxY = new JLabel("Max Y:");
/* 141 */     this.txtMaxY = new JTextField(5);
/* 142 */     this.lblMinY = new JLabel("Min Y:");
/* 143 */     this.txtMinY = new JTextField(5);
/*     */ 
/* 146 */     this.directionPanel.add(this.btnZoomOut);
/* 147 */     this.directionPanel.add(this.btnUp);
/* 148 */     this.directionPanel.add(this.btnZoomIn);
/* 149 */     this.directionPanel.add(this.btnLeft);
/* 150 */     this.directionPanel.add(this.btnCenter);
/* 151 */     this.directionPanel.add(this.btnRight);
/* 152 */     this.directionPanel.add(new JPanel());
/* 153 */     this.directionPanel.add(this.btnDown);
/* 154 */     this.directionPanel.add(new JPanel());
/*     */ 
/* 157 */     this.equationPanel.add(new EquationInput("y1=", Color.RED));
/* 158 */     this.equationPanel.add(new EquationInput("y2=", Color.BLUE));
/* 159 */     this.equationPanel.add(new EquationInput("y3=", Color.YELLOW));
/* 160 */     for (int i = 0; i < this.equationPanel.getComponentCount(); i++) {
/* 161 */       ((EquationInput)this.equationPanel.getComponent(i)).getInput().addKeyListener(this);
/*     */     }
/*     */ 
/* 165 */     this.buttonPanel.add(this.btnAddEquation);
/* 166 */     this.buttonPanel.add(this.btnRemoveEquation);
/* 167 */     this.buttonPanel.add(this.btnGraph);
/*     */ 
/* 170 */     this.lblCursorX = new JLabel("X: N/A");
/* 171 */     this.lblCursorY = new JLabel("Y: N/A");
/* 172 */     this.cursorCordPanel.add(this.lblCursorX);
/* 173 */     this.cursorCordPanel.add(this.lblCursorY);
/*     */ 
/* 176 */     this.southPanel.add(this.equationScrollPane);
/* 177 */     this.southPanel.add(this.buttonPanel);
/*     */ 
/* 180 */     this.eastPanel.add(this.directionPanel);
/* 181 */     this.eastPanel.add(this.boundsPanel);
/* 182 */     this.eastPanel.add(this.cursorCordPanel);
/*     */ 
/* 185 */     this.boundsPanel.add(this.lblMaxX);
/* 186 */     this.boundsPanel.add(this.txtMaxX);
/* 187 */     this.boundsPanel.add(this.lblMinX);
/* 188 */     this.boundsPanel.add(this.txtMinX);
/* 189 */     this.boundsPanel.add(this.lblMaxY);
/* 190 */     this.boundsPanel.add(this.txtMaxY);
/* 191 */     this.boundsPanel.add(this.lblMinY);
/* 192 */     this.boundsPanel.add(this.txtMinY);
/*     */ 
/* 195 */     this.btnGraph.addActionListener(this);
/* 196 */     this.btnLeft.addActionListener(this);
/* 197 */     this.btnRight.addActionListener(this);
/* 198 */     this.btnUp.addActionListener(this);
/* 199 */     this.btnDown.addActionListener(this);
/* 200 */     this.btnCenter.addActionListener(this);
/* 201 */     this.btnZoomIn.addActionListener(this);
/* 202 */     this.btnZoomOut.addActionListener(this);
/* 203 */     this.btnAddEquation.addActionListener(this);
/* 204 */     this.btnRemoveEquation.addActionListener(this);
/* 205 */     this.graphPanel.addMouseMotionListener(this);
/* 206 */     this.graphPanel.addMouseWheelListener(this);
/* 207 */     this.graphPanel.addMouseListener(this);
/* 208 */     this.txtMaxX.addFocusListener(this);
/* 209 */     this.txtMinX.addFocusListener(this);
/* 210 */     this.txtMaxY.addFocusListener(this);
/* 211 */     this.txtMinY.addFocusListener(this);
/*     */   }
/*     */ 
/*     */   private void createRightClickMenu()
/*     */   {
/* 219 */     this.mnuGraphRightClick = new JPopupMenu();
/* 220 */     this.miPlotPoint = new JMenuItem("Plot Free Point");
/* 221 */     this.miPlotAtXValue = new JMenuItem("Plot On Equation");
/* 222 */     this.miRemovePoint = new JMenuItem("Remove Point");
/* 223 */     this.miZoomIn = new JMenuItem("Zoom In");
/* 224 */     this.miZoomOut = new JMenuItem("Zoom Out");
/* 225 */     this.miDrawLineBetweenPoints = new JMenuItem("Between Points");
/* 226 */     this.miDrawTangentLine = new JMenuItem("Tangent to Equation");
/* 227 */     this.miPlotMinPoint = new JMenuItem("Plot Min Point");
/* 228 */     this.miPlotMaxPoint = new JMenuItem("Plot Max Point");
/* 229 */     this.miRemoveAllPoints = new JMenuItem("Remove All Points");
/* 230 */     this.miClearGraph = new JMenuItem("Clear");
/* 231 */     this.miViewTableOfPoints = new JMenuItem("Table of Points");
/* 232 */     this.miViewTableOfValues = new JMenuItem("Table of Values");
/*     */ 
/* 234 */     this.mnuDrawLine = new JMenu("Draw Line");
/* 235 */     this.mnuPoints = new JMenu("Plot Points");
/*     */ 
/* 237 */     this.mnuGraphRightClick.add(this.miZoomIn);
/* 238 */     this.mnuGraphRightClick.add(this.miZoomOut);
/* 239 */     this.mnuGraphRightClick.addSeparator();
/* 240 */     this.mnuGraphRightClick.add(this.mnuPoints);
/* 241 */     this.mnuGraphRightClick.add(this.mnuDrawLine);
/* 242 */     this.mnuGraphRightClick.addSeparator();
/* 243 */     this.mnuGraphRightClick.add(this.miViewTableOfPoints);
/* 244 */     this.mnuGraphRightClick.add(this.miViewTableOfValues);
/* 245 */     this.mnuGraphRightClick.addSeparator();
/* 246 */     this.mnuGraphRightClick.add(this.miClearGraph);
/*     */ 
/* 248 */     this.mnuDrawLine.add(this.miDrawLineBetweenPoints);
/* 249 */     this.mnuDrawLine.add(this.miDrawTangentLine);
/*     */ 
/* 251 */     this.mnuPoints.add(this.miPlotPoint);
/* 252 */     this.mnuPoints.add(this.miPlotAtXValue);
/* 253 */     this.mnuPoints.addSeparator();
/* 254 */     this.mnuPoints.add(this.miPlotMaxPoint);
/* 255 */     this.mnuPoints.add(this.miPlotMinPoint);
/* 256 */     this.mnuPoints.addSeparator();
/* 257 */     this.mnuPoints.add(this.miRemovePoint);
/* 258 */     this.mnuPoints.add(this.miRemoveAllPoints);
/*     */ 
/* 261 */     this.miPlotPoint.addActionListener(this);
/* 262 */     this.miPlotAtXValue.addActionListener(this);
/* 263 */     this.miRemovePoint.addActionListener(this);
/* 264 */     this.miRemoveAllPoints.addActionListener(this);
/* 265 */     this.miZoomIn.addActionListener(this);
/* 266 */     this.miZoomOut.addActionListener(this);
/* 267 */     this.miDrawLineBetweenPoints.addActionListener(this);
/* 268 */     this.miDrawTangentLine.addActionListener(this);
/* 269 */     this.miPlotMinPoint.addActionListener(this);
/* 270 */     this.miPlotMaxPoint.addActionListener(this);
/* 271 */     this.miClearGraph.addActionListener(this);
/* 272 */     this.miViewTableOfPoints.addActionListener(this);
/* 273 */     this.miViewTableOfValues.addActionListener(this);
/*     */   }
/*     */ 
/*     */   public JButton getBtnAddEquation()
/*     */   {
/* 281 */     return this.btnAddEquation;
/*     */   }
/*     */ 
/*     */   public JButton getBtnGraph()
/*     */   {
/* 289 */     return this.btnGraph;
/*     */   }
/*     */ 
/*     */   public int getEquationCount()
/*     */   {
/* 296 */     return this.equationCount;
/*     */   }
/*     */ 
/*     */   public JPanel getEquationPanel()
/*     */   {
/* 303 */     return this.equationPanel;
/*     */   }
/*     */ 
/*     */   public GraphPanel getGraphPanel()
/*     */   {
/* 311 */     return this.graphPanel;
/*     */   }
/*     */ 
/*     */   private void resetStats()
/*     */   {
/* 318 */     DecimalFormat df = new DecimalFormat("#.#####");
/* 319 */     this.txtMaxX.setText(df.format(this.graphPanel.getMaxX()));
/* 320 */     this.txtMinX.setText(df.format(this.graphPanel.getMinX()));
/* 321 */     this.txtMaxY.setText(df.format(this.graphPanel.getMaxY()));
/* 322 */     this.txtMinY.setText(df.format(this.graphPanel.getMinY()));
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent e)
/*     */   {
/* 330 */     if (e.getSource() == this.btnGraph) {
/*     */       try {
/* 332 */         Vector eq = new Vector();
/* 333 */         for (Component cmp : this.equationPanel.getComponents()) {
/* 334 */           if (cmp.getClass() == EquationInput.class) {
/* 335 */             EquationInput ei = (EquationInput)cmp;
/* 336 */             if (!ei.getInput().getText().isEmpty()) {
/* 337 */               eq.add(new Equation(ei.getInput().getText(), ei.getColor()));
/*     */             }
/*     */           }
/*     */         }
/* 341 */         this.graphPanel.drawGraph(eq);
/*     */       } catch (Exception exc) {
/* 343 */         JOptionPane.showMessageDialog(this, exc.getMessage(), "Error", 0);
/*     */       }
/*     */     }
/* 346 */     if (e.getSource() == this.btnLeft) {
/* 347 */       this.graphPanel.moveHorizontal(-10.0D);
/*     */     }
/* 349 */     if (e.getSource() == this.btnRight) {
/* 350 */       this.graphPanel.moveHorizontal(10.0D);
/*     */     }
/* 352 */     if (e.getSource() == this.btnUp) {
/* 353 */       this.graphPanel.moveVertical(10.0D);
/*     */     }
/* 355 */     if (e.getSource() == this.btnDown) {
/* 356 */       this.graphPanel.moveVertical(-10.0D);
/*     */     }
/* 358 */     if (e.getSource() == this.btnCenter) {
/* 359 */       this.graphPanel.center();
/*     */     }
/* 361 */     if (e.getSource() == this.btnZoomIn) {
/* 362 */       this.graphPanel.zoom(-20.0D);
/*     */     }
/* 364 */     if (e.getSource() == this.btnZoomOut) {
/* 365 */       this.graphPanel.zoom(25.0D);
/*     */     }
/* 367 */     if (e.getSource() == this.btnAddEquation) {
/* 368 */       Random r = new Random();
/* 369 */       this.equationCount += 1;
/* 370 */       this.equationPanel.add(new EquationInput("y" + this.equationCount + "=", 
/* 371 */         new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256))));
/* 372 */       this.equationScrollPane.validate();
/* 373 */       ((EquationInput)this.equationPanel.getComponent(this.equationCount - 1)).getInput().addKeyListener(this);
/*     */     }
/* 375 */     if (e.getSource() == this.btnRemoveEquation) {
/* 376 */       if (this.equationCount > 1) {
/* 377 */         this.equationPanel.remove(this.equationPanel.getComponentCount() - 1);
/* 378 */         this.equationCount -= 1;
/* 379 */         this.equationScrollPane.validate();
/*     */       } else {
/* 381 */         JOptionPane.showMessageDialog(this, "Can't remove last equation.", "Error", 0);
/*     */       }
/*     */     }
/*     */ 
/* 385 */     if (e.getSource() == this.miPlotPoint) {
/* 386 */       AddPointDialog addPoint = new AddPointDialog(this, this.xClicked, this.yClicked);
/* 387 */       addPoint.setLocationRelativeTo(this);
/* 388 */       addPoint.setVisible(true);
/*     */     }
/* 390 */     if (e.getSource() == this.miRemovePoint) {
/* 391 */       String remove = JOptionPane.showInputDialog(this, "Name of point to remove:");
/* 392 */       GraphPanel.removePoint(remove);
/*     */       try {
/* 394 */         wait(10L);
/*     */       } catch (InterruptedException ex) {
/* 396 */         Logger.getLogger(GraphingTab.class.getName()).log(Level.SEVERE, null, ex);
/*     */       }
/*     */       finally {
/* 399 */         repaint();
/*     */       }
/*     */     }
/* 402 */     if (e.getSource() == this.miRemoveAllPoints) {
/* 403 */       GraphPanel.getPoints().clear();
/*     */     }
/* 405 */     if (e.getSource() == this.miZoomIn) {
/* 406 */       this.graphPanel.zoom(-50.0D);
/*     */     }
/* 408 */     if (e.getSource() == this.miZoomOut) {
/* 409 */       this.graphPanel.zoom(100.0D);
/*     */     }
/* 411 */     if (e.getSource() == this.miDrawLineBetweenPoints) {
/* 412 */       if (GraphPanel.getPoints().size() < 2) {
/* 413 */         JOptionPane.showMessageDialog(this, "Less then 2 points are ploted on graph.", "Error", 0);
/* 414 */         return;
/*     */       }
/* 416 */       DrawLineBetweenPointsDialog dld = new DrawLineBetweenPointsDialog(this);
/* 417 */       dld.setLocationRelativeTo(this);
/* 418 */       dld.setVisible(true);
/*     */     }
/*     */     AddPointAtXValueDialog localAddPointAtXValueDialog1;
/*     */     AddPointAtXValueDialog addPoint;
/* 422 */     if ((e.getSource() == this.miDrawTangentLine) || (e.getSource() == this.miPlotMinPoint) || (e.getSource() == this.miPlotMaxPoint) || 
/* 423 */       (e.getSource() == this.miViewTableOfValues) || (e.getSource() == this.miPlotAtXValue))
/*     */     {
/* 425 */       boolean allEmpty = true;
/* 426 */       ??? = ( = this.equationPanel.getComponents()).length; for (localAddPointAtXValueDialog1 = 0; localAddPointAtXValueDialog1 < ???; localAddPointAtXValueDialog1++) { Component eq = ???[localAddPointAtXValueDialog1];
/* 427 */         if (!((EquationInput)eq).getInput().getText().isEmpty()) {
/* 428 */           allEmpty = false;
/* 429 */           break;
/*     */         }
/*     */       }
/* 432 */       if (!allEmpty)
/*     */       {
/* 434 */         if (e.getSource() == this.miDrawTangentLine) {
/* 435 */           DrawTangentLineDialog dtld = new DrawTangentLineDialog(this);
/* 436 */           dtld.setLocationRelativeTo(this);
/* 437 */           dtld.setVisible(true);
/*     */         }
/* 439 */         else if ((e.getSource() == this.miPlotMinPoint) || (e.getSource() == this.miPlotMaxPoint))
/*     */         {
/*     */           AddMinMaxPointDialog addPoint;
/*     */           AddMinMaxPointDialog addPoint;
/* 442 */           if (e.getSource() == this.miPlotMinPoint) {
/* 443 */             addPoint = new AddMinMaxPointDialog(this, this.xClicked, this.graphPanel.getMaxX() - this.graphPanel.getMinX(), 1);
/*     */           }
/*     */           else {
/* 446 */             addPoint = new AddMinMaxPointDialog(this, this.xClicked, this.graphPanel.getMaxX() - this.graphPanel.getMinX(), 2);
/*     */           }
/* 448 */           addPoint.setLocationRelativeTo(this);
/* 449 */           addPoint.setVisible(true);
/*     */         }
/* 451 */         if (e.getSource() == this.miViewTableOfValues) {
/* 452 */           EquationValueTableWindow evtb = new EquationValueTableWindow(this.equationPanel);
/* 453 */           evtb.setLocationRelativeTo(this);
/* 454 */           evtb.setVisible(true);
/*     */         }
/* 456 */         if (e.getSource() == this.miPlotAtXValue) {
/* 457 */           addPoint = new AddPointAtXValueDialog(this);
/* 458 */           addPoint.setLocationRelativeTo(this);
/* 459 */           addPoint.setVisible(true);
/*     */         }
/*     */       } else {
/* 462 */         JOptionPane.showMessageDialog(this, "All equation inputs are empty.", "Error", 0);
/*     */       }
/*     */     }
/* 465 */     if (e.getSource() == this.miClearGraph) {
/* 466 */       GraphPanel.getPoints().clear();
/*     */       Component[] arrayOfComponent1;
/* 467 */       localAddPointAtXValueDialog1 = (arrayOfComponent1 = this.equationPanel.getComponents()).length; for (addPoint = 0; addPoint < localAddPointAtXValueDialog1; addPoint++) { Component eq = arrayOfComponent1[addPoint];
/* 468 */         ((EquationInput)eq).getInput().setText("");
/* 469 */         this.btnGraph.doClick();
/*     */       }
/*     */ 
/* 472 */       repaint();
/*     */     }
/* 474 */     if (e.getSource() == this.miViewTableOfPoints) {
/* 475 */       new PointValuesTableWindow().setVisible(true);
/*     */     }
/*     */ 
/* 479 */     resetStats();
/*     */   }
/*     */ 
/*     */   public void mouseMoved(MouseEvent e)
/*     */   {
/* 487 */     if (e.getSource() == this.graphPanel) {
/* 488 */       DecimalFormat df = new DecimalFormat("#.#####");
/* 489 */       double x = this.graphPanel.PixelToUnitX(e.getX());
/* 490 */       double y = this.graphPanel.PixelToUnitY(e.getY());
/*     */ 
/* 492 */       this.lblCursorX.setText("X: " + df.format(x));
/* 493 */       this.lblCursorY.setText("Y: " + df.format(y));
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseWheelMoved(MouseWheelEvent e)
/*     */   {
/* 500 */     if (e.getSource() == this.graphPanel)
/*     */     {
/* 502 */       if (e.getWheelRotation() < 0) {
/* 503 */         this.graphPanel.zoom(-20.0D);
/*     */       }
/*     */       else {
/* 506 */         this.graphPanel.zoom(25.0D);
/*     */       }
/*     */ 
/* 509 */       resetStats();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseDragged(MouseEvent e)
/*     */   {
/* 517 */     if (e.getSource() == this.graphPanel) {
/* 518 */       double xMoved = e.getX();
/* 519 */       double yMoved = e.getY();
/* 520 */       double percentX = (xMoved - this.xPrev) / this.graphPanel.getWidth();
/* 521 */       double percentY = (yMoved - this.yPrev) / this.graphPanel.getHeight();
/* 522 */       this.graphPanel.moveHorizontal(-percentX * 100.0D);
/* 523 */       this.graphPanel.moveVertical(percentY * 100.0D);
/* 524 */       this.xPrev = ((int)xMoved);
/* 525 */       this.yPrev = ((int)yMoved);
/* 526 */       resetStats();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void focusGained(FocusEvent e)
/*     */   {
/* 534 */     if (e.getSource() == this.txtMaxX) {
/* 535 */       this.txtMaxX.selectAll();
/*     */     }
/* 537 */     if (e.getSource() == this.txtMinX) {
/* 538 */       this.txtMinX.selectAll();
/*     */     }
/* 540 */     if (e.getSource() == this.txtMaxY) {
/* 541 */       this.txtMaxY.selectAll();
/*     */     }
/* 543 */     if (e.getSource() == this.txtMinY)
/* 544 */       this.txtMinY.selectAll();
/*     */   }
/*     */ 
/*     */   public void focusLost(FocusEvent e)
/*     */   {
/*     */     try
/*     */     {
/* 553 */       if (e.getSource() == this.txtMaxX) {
/* 554 */         Expression expr = new Expression(this.txtMaxX.getText());
/*     */         try {
/* 556 */           this.graphPanel.setMaxX(expr.evaluate());
/*     */         }
/*     */         catch (Exception x)
/*     */         {
/* 560 */           this.graphPanel.setMaxX((0.0D / 0.0D));
/*     */         }
/*     */       }
/* 563 */       if (e.getSource() == this.txtMinX) {
/* 564 */         Expression expr = new Expression(this.txtMinX.getText());
/*     */         try {
/* 566 */           this.graphPanel.setMinX(expr.evaluate());
/*     */         }
/*     */         catch (Exception x)
/*     */         {
/* 570 */           this.graphPanel.setMinX((0.0D / 0.0D));
/*     */         }
/*     */       }
/*     */ 
/* 574 */       if (e.getSource() == this.txtMaxY) {
/* 575 */         Expression expr = new Expression(this.txtMaxY.getText());
/*     */         try {
/* 577 */           this.graphPanel.setMaxY(expr.evaluate());
/*     */         }
/*     */         catch (Exception x)
/*     */         {
/* 581 */           this.graphPanel.setMaxY((0.0D / 0.0D));
/*     */         }
/*     */       }
/*     */ 
/* 585 */       if (e.getSource() == this.txtMinY) {
/* 586 */         Expression expr = new Expression(this.txtMinY.getText());
/*     */         try {
/* 588 */           this.graphPanel.setMinY(expr.evaluate());
/*     */         }
/*     */         catch (Exception x)
/*     */         {
/* 592 */           this.graphPanel.setMaxX((0.0D / 0.0D));
/*     */         }
/*     */       }
/*     */     } catch (NumberFormatException nfe) {
/* 596 */       JOptionPane.showMessageDialog(this, nfe.getMessage(), "Error", 0);
/*     */     } catch (InvalidBoundsException ibe) {
/* 598 */       JOptionPane.showMessageDialog(this, ibe.getMessage(), "Error", 0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseClicked(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/* 611 */     if ((e.getSource() == this.graphPanel) && ((e.isPopupTrigger()) || (e.getModifiers() == 4))) {
/* 612 */       this.xClicked = this.graphPanel.PixelToUnitX(e.getX());
/* 613 */       this.yClicked = this.graphPanel.PixelToUnitY(e.getY());
/* 614 */       this.mnuGraphRightClick.show(this.graphPanel, e.getX() + 5, e.getY() + 3);
/*     */     }
/*     */ 
/* 617 */     if (e.getSource() == this.graphPanel) {
/* 618 */       this.xPrev = e.getX();
/* 619 */       this.yPrev = e.getY();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseReleased(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mouseEntered(MouseEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void mouseExited(MouseEvent e)
/*     */   {
/* 635 */     if (e.getSource() == this.graphPanel) {
/* 636 */       this.lblCursorX.setText("X: N/A");
/* 637 */       this.lblCursorY.setText("Y: N/A");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent e)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent e)
/*     */   {
/* 648 */     if ((e.getSource().getClass() == SmartTextField.class) && 
/* 649 */       (e.getKeyCode() == 10))
/* 650 */       this.btnGraph.doClick();
/*     */   }
/*     */ 
/*     */   public void keyReleased(KeyEvent e)
/*     */   {
/*     */   }
/*     */ }

/* Location:           /Users/Ryan/Downloads/Calculator_3.0_Alpha/
 * Qualified Name:     graphing.GraphingTab
 * JD-Core Version:    0.6.2
 */