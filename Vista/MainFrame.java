package Vista;

import Controlador.Control;
import Modelo.ParserException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter.HighlightPainter;

public class MainFrame extends JFrame implements ActionListener
{
    // Componentes y configuracion de graficos
    private final Object[] stateColumns = {"Lexema", "Tipo", "Valor(es)"};
    private final TextLineNumber txtLn;
    private final JFileChooser selectorDeArchivo;
    private final Lienzo lienzo;
    private final int WIDTH_MARGIN = 20;
    private final int HEIGHT_MARGIN = 35;
    private final int Y_MARGIN = 17;
    private final int X_MARGIN = 10;
    
    // Estructuras de datos
    private final LinkedList <Historial> histArreglos;
    private final ArrayList <String[]> listArr;
    private final ArrayList <String[]> listCons;
    private final ArrayList <String[]> listVar;
    
    // Controlador
    private final Control control;
    
    // Frames secundarios
    private AboutFrame frmAbout;
    private FileFrame frmAuto;
    private FileFrame frmPR;
    
    // Auxiliares
    private String fileTextAutomata;
    private String fileTextPR;
    private File dirPR;
    private File dirAuto;
    private File dirCodigo;
    private boolean progTitle;
    private boolean arraysLoaded;
    private int step;
    private final Timer timer;
    private int maxArrayN;
    private final ColorTheme colorsT;
    private Color color;
    private HighlightPainter HLPainter;

    public MainFrame()
    {
        initComponents();
        
        URL iconURL = getClass().getResource("/Icons/config.png");
        
        if(iconURL != null)
        {
            ImageIcon icon = new ImageIcon(iconURL);
            setIconImage(icon.getImage());
        }
        
        setLocationRelativeTo(null);
        setTitle("Interprete");
        
        selectorDeArchivo = new JFileChooser();
        selectorDeArchivo.setFileFilter(new FileNameExtensionFilter("Archivos de Texto", "txt"));
        selectorDeArchivo.setMultiSelectionEnabled(false);
        
        dirPR = null;
        dirCodigo = null;
        dirAuto = null;
        fileTextAutomata = "";
        fileTextPR = "";
        step = 0;
        maxArrayN = 0;
        
        frmAbout = null;
        frmAuto = null;
        frmPR = null;
        
        control = new Control(this);
        
        histArreglos = new LinkedList <>();
        listArr = new ArrayList <>();
        listCons = new ArrayList <>();
        listVar = new ArrayList <>();
        
        lienzo = new Lienzo(histArreglos);
        txtLn = new TextLineNumber(txtEditor);
        panelEditor.setRowHeaderView(txtLn);
        progTitle = false;
        arraysLoaded = false;
        timer = new Timer(2000, this);
        color = Color.BLACK;
        HLPainter = DefaultHighlighter.DefaultPainter;
        colorsT = new ColorTheme();
        clearGraphics();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        tabPaneMain = new javax.swing.JTabbedPane();
        tabProgra = new javax.swing.JPanel();
        panelEditor = new javax.swing.JScrollPane();
        txtEditor = new javax.swing.JTextArea();
        panelConsola = new javax.swing.JScrollPane();
        txtConsola = new javax.swing.JTextArea();
        graficosLP = new javax.swing.JLayeredPane();
        panelSalida = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();
        tabEstruct = new javax.swing.JPanel();
        panelHistorial = new javax.swing.JScrollPane();
        txtHistorial = new javax.swing.JTextArea();
        panelArr = new javax.swing.JScrollPane();
        tablaArreglos = new javax.swing.JTable();
        panelVar = new javax.swing.JScrollPane();
        tablaVar = new javax.swing.JTable();
        panelCons = new javax.swing.JScrollPane();
        tablaCons = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mItCargarAuto = new javax.swing.JMenuItem();
        mItemVerAuto = new javax.swing.JMenuItem();
        mArchivo = new javax.swing.JMenu();
        mItemNuevo = new javax.swing.JMenuItem();
        mItemAbrir = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mItemEjecutar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mItemIniciarRapido = new javax.swing.JMenuItem();
        mItemIniciarMedio = new javax.swing.JMenuItem();
        mItemIniciarLento = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mItemIniciarPaso = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mItemGuardar = new javax.swing.JMenuItem();
        mItGuardarComo = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mItPR = new javax.swing.JMenuItem();
        mItemVerPR = new javax.swing.JMenuItem();
        mAyuda = new javax.swing.JMenu();
        mItemAcerca = new javax.swing.JMenuItem();
        mSalir = new javax.swing.JMenu();

        jMenuItem2.setText("jMenuItem2");

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Interprete");

        panelEditor.setBorder(javax.swing.BorderFactory.createTitledBorder("Editor"));

        txtEditor.setColumns(20);
        txtEditor.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        txtEditor.setRows(5);
        txtEditor.setTabSize(4);
        panelEditor.setViewportView(txtEditor);

        panelConsola.setBorder(javax.swing.BorderFactory.createTitledBorder("Consola"));

        txtConsola.setEditable(false);
        txtConsola.setColumns(20);
        txtConsola.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        txtConsola.setRows(5);
        panelConsola.setViewportView(txtConsola);

        graficosLP.setBorder(javax.swing.BorderFactory.createTitledBorder("Graficos"));

        javax.swing.GroupLayout graficosLPLayout = new javax.swing.GroupLayout(graficosLP);
        graficosLP.setLayout(graficosLPLayout);
        graficosLPLayout.setHorizontalGroup(
            graficosLPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        graficosLPLayout.setVerticalGroup(
            graficosLPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelSalida.setBorder(javax.swing.BorderFactory.createTitledBorder("Salida"));

        txtOutput.setEditable(false);
        txtOutput.setColumns(20);
        txtOutput.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        txtOutput.setRows(5);
        panelSalida.setViewportView(txtOutput);

        javax.swing.GroupLayout tabPrograLayout = new javax.swing.GroupLayout(tabProgra);
        tabProgra.setLayout(tabPrograLayout);
        tabPrograLayout.setHorizontalGroup(
            tabPrograLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPrograLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabPrograLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelEditor, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                    .addComponent(panelSalida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPrograLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelConsola, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                    .addComponent(graficosLP))
                .addContainerGap())
        );
        tabPrograLayout.setVerticalGroup(
            tabPrograLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabPrograLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabPrograLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelEditor, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                    .addComponent(graficosLP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPrograLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelConsola, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addComponent(panelSalida)))
        );

        tabPaneMain.addTab("Programa ", new javax.swing.ImageIcon(getClass().getResource("/Icons/code.png")), tabProgra); // NOI18N

        panelHistorial.setBorder(javax.swing.BorderFactory.createTitledBorder("Historial"));

        txtHistorial.setEditable(false);
        txtHistorial.setColumns(20);
        txtHistorial.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        txtHistorial.setRows(5);
        txtHistorial.setTabSize(4);
        panelHistorial.setViewportView(txtHistorial);

        panelArr.setBorder(javax.swing.BorderFactory.createTitledBorder("Arreglos"));

        tablaArreglos.setAutoCreateRowSorter(true);
        tablaArreglos.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        tablaArreglos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lexema", "Tipo", "Valor(es)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelArr.setViewportView(tablaArreglos);

        panelVar.setBorder(javax.swing.BorderFactory.createTitledBorder("Variables"));

        tablaVar.setAutoCreateRowSorter(true);
        tablaVar.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        tablaVar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lexema", "Tipo", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelVar.setViewportView(tablaVar);

        panelCons.setBorder(javax.swing.BorderFactory.createTitledBorder("Constantes"));

        tablaCons.setAutoCreateRowSorter(true);
        tablaCons.setFont(new java.awt.Font("Consolas", 0, 13)); // NOI18N
        tablaCons.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Lexema", "Tipo", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        panelCons.setViewportView(tablaCons);

        javax.swing.GroupLayout tabEstructLayout = new javax.swing.GroupLayout(tabEstruct);
        tabEstruct.setLayout(tabEstructLayout);
        tabEstructLayout.setHorizontalGroup(
            tabEstructLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabEstructLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabEstructLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelArr, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                    .addComponent(panelVar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelCons))
                .addContainerGap())
        );
        tabEstructLayout.setVerticalGroup(
            tabEstructLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabEstructLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabEstructLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabEstructLayout.createSequentialGroup()
                        .addComponent(panelHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(tabEstructLayout.createSequentialGroup()
                        .addComponent(panelArr, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelVar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCons, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
        );

        tabPaneMain.addTab("Estructuras de datos ", new javax.swing.ImageIcon(getClass().getResource("/Icons/list.png")), tabEstruct); // NOI18N

        jMenu1.setText("Autómata");

        mItCargarAuto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        mItCargarAuto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/input.png"))); // NOI18N
        mItCargarAuto.setText("Cargar | Reemplazar");
        mItCargarAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItCargarAutoActionPerformed(evt);
            }
        });
        jMenu1.add(mItCargarAuto);

        mItemVerAuto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/read.png"))); // NOI18N
        mItemVerAuto.setText("Ver");
        mItemVerAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemVerAutoActionPerformed(evt);
            }
        });
        jMenu1.add(mItemVerAuto);

        jMenuBar1.add(jMenu1);

        mArchivo.setText("Código");

        mItemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mItemNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/new.png"))); // NOI18N
        mItemNuevo.setText("Nuevo");
        mItemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemNuevoActionPerformed(evt);
            }
        });
        mArchivo.add(mItemNuevo);

        mItemAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mItemAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/open.png"))); // NOI18N
        mItemAbrir.setText("Abrir");
        mItemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemAbrirActionPerformed(evt);
            }
        });
        mArchivo.add(mItemAbrir);
        mArchivo.add(jSeparator2);

        mItemEjecutar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        mItemEjecutar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/run.png"))); // NOI18N
        mItemEjecutar.setText("Ejecutar");
        mItemEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemEjecutarActionPerformed(evt);
            }
        });
        mArchivo.add(mItemEjecutar);

        jMenu3.setText("Visualizar cambios en arreglos");

        mItemIniciarRapido.setText("Iniciar - Rapido");
        mItemIniciarRapido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemIniciarRapidoActionPerformed(evt);
            }
        });
        jMenu3.add(mItemIniciarRapido);

        mItemIniciarMedio.setText("Iniciar - Medio");
        mItemIniciarMedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemIniciarMedioActionPerformed(evt);
            }
        });
        jMenu3.add(mItemIniciarMedio);

        mItemIniciarLento.setText("Iniciar - Lento");
        mItemIniciarLento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemIniciarLentoActionPerformed(evt);
            }
        });
        jMenu3.add(mItemIniciarLento);
        jMenu3.add(jSeparator3);

        mItemIniciarPaso.setText("Avanzar un paso");
        mItemIniciarPaso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemIniciarPasoActionPerformed(evt);
            }
        });
        jMenu3.add(mItemIniciarPaso);

        mArchivo.add(jMenu3);

        jMenuItem1.setText("Cargar test");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mArchivo.add(jMenuItem1);
        mArchivo.add(jSeparator1);

        mItemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mItemGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/save.png"))); // NOI18N
        mItemGuardar.setText("Guardar");
        mItemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemGuardarActionPerformed(evt);
            }
        });
        mArchivo.add(mItemGuardar);

        mItGuardarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mItGuardarComo.setText("Guardar como...");
        mItGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItGuardarComoActionPerformed(evt);
            }
        });
        mArchivo.add(mItGuardarComo);

        jMenuBar1.add(mArchivo);

        jMenu2.setText("Palabras reservadas");

        mItPR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        mItPR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/input.png"))); // NOI18N
        mItPR.setText("Cargar | Reemplazar");
        mItPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItPRActionPerformed(evt);
            }
        });
        jMenu2.add(mItPR);

        mItemVerPR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/read.png"))); // NOI18N
        mItemVerPR.setText("Ver");
        mItemVerPR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemVerPRActionPerformed(evt);
            }
        });
        jMenu2.add(mItemVerPR);

        jMenuBar1.add(jMenu2);

        mAyuda.setText("Ayuda");

        mItemAcerca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/about.png"))); // NOI18N
        mItemAcerca.setText("Acerca de");
        mItemAcerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemAcercaActionPerformed(evt);
            }
        });
        mAyuda.add(mItemAcerca);

        jMenuBar1.add(mAyuda);

        mSalir.setText("Salir");
        mSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mSalirMouseClicked(evt);
            }
        });
        jMenuBar1.add(mSalir);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabPaneMain)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabPaneMain)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mSalirMouseClicked
        System.exit(0);
    }//GEN-LAST:event_mSalirMouseClicked

    private void mItemAcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemAcercaActionPerformed

        frmAbout = new AboutFrame();
        frmAbout.setResizable(false);
        frmAbout.setLocationRelativeTo(this);
        frmAbout.setVisible(true);
        
    }//GEN-LAST:event_mItemAcercaActionPerformed

    private void mItemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemGuardarActionPerformed

        if(dirCodigo == null)
        {
            saveAs();
        }
        
        else
        {
            save(dirCodigo);
        }
        
    }//GEN-LAST:event_mItemGuardarActionPerformed

    private void mItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemAbrirActionPerformed
        // Codigo 0 para abrir un archivo de codigo
        openFile(0);
        
    }//GEN-LAST:event_mItemAbrirActionPerformed

    private void mItGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItGuardarComoActionPerformed
        
        saveAs();
        
    }//GEN-LAST:event_mItGuardarComoActionPerformed

    private void mItCargarAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItCargarAutoActionPerformed
        // Codigo 1 para abrir un archivo de automata
        openFile(1);
        
    }//GEN-LAST:event_mItCargarAutoActionPerformed

    private void mItPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItPRActionPerformed
        // Codigo 2 para abrir un archivo de palabras reservadas
        openFile(2);
        
    }//GEN-LAST:event_mItPRActionPerformed

    private void mItemEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemEjecutarActionPerformed

        if(dirCodigo == null)
        {
            msgDial("No hay codigo para ejecutar", "Error de código", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(dirAuto == null)
        {
            msgDial("Automata no cargado", "Error de automata", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(dirPR == null)
        {
            msgDial("Palabras reservadas sin cargar", "Error de palabras reservadas", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if(timer.isRunning())
            timer.stop();
        
        try
        {
            control.cargarAuto(dirAuto.getAbsolutePath());
            control.cargarReserv(dirPR.getAbsolutePath());
            
            clearWorkspace();
            
            control.start(dirAuto.getAbsolutePath(), txtEditor.getText());
            
            step = 0;
            setLimits();
            loadArrays();
            
            arraysLoaded = true;
            maxArrayN = getMaxArrayCount();
            highLight(drawArraySet(0, color));
        }
        catch(IOException ie)
        {
            msgDial("No se cargo correctamente el automata o palabras reservadas\n Intente de nuevo",
            "Error de archivo", JOptionPane.ERROR_MESSAGE);
        }
        catch(ParserException pe)
        {
            msgDial(pe.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_mItemEjecutarActionPerformed

    private void mItemVerAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemVerAutoActionPerformed
        
        if(dirAuto != null)
        {
            frmAuto = new FileFrame("Automata", dirAuto.getAbsolutePath(), fileTextAutomata);
            frmAuto.setLocationRelativeTo(this);
            frmAuto.setVisible(true);
        }
        else
        {
            msgDial("No existe automata\nCarguelo primero", "Automata", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_mItemVerAutoActionPerformed

    private void mItemVerPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemVerPRActionPerformed
        
        if(dirPR != null)
        {
            frmPR = new FileFrame("Palabras Reservadas", dirPR.getAbsolutePath(), fileTextPR);
            frmPR.setLocationRelativeTo(this);
            frmPR.setVisible(true);
        }
        else
        {
            msgDial("No existen palabras reservadas\nCarguelas primero",
            "Palabras reservadas", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_mItemVerPRActionPerformed

    private void mItemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemNuevoActionPerformed
        
        txtEditor.setText("");
        clearWorkspace();
        panelEditor.setBorder(BorderFactory.createTitledBorder("Editor"));
        
    }//GEN-LAST:event_mItemNuevoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
        loadFile(1, new File("src/TestFiles/AutomataProyecto.txt"));
        loadFile(2, new File("src/TestFiles/reservadasProyecto.txt"));
        loadFile(0, new File("src/TestFiles/Prog8.txt"));
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void mItemIniciarRapidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemIniciarRapidoActionPerformed
        
        if(arraysLoaded && !timer.isRunning())
            runTimer(500);
        else
            msgDial("No se puede ejecutar la animación en este momento:\n1.- Verifique que ya se ejecuto el codigo\n"
            + "2.- Verifique que no se esta ejecutando una animación", "Imposible ejecutar animación", JOptionPane.WARNING_MESSAGE);
        
    }//GEN-LAST:event_mItemIniciarRapidoActionPerformed

    private void mItemIniciarPasoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemIniciarPasoActionPerformed
         
        if(arraysLoaded && !timer.isRunning())
            nextStep();
        else
            msgDial("No se puede ejecutar la animación en este momento:\n1.- Verifique que ya se ejecuto el codigo\n"
            + "2.- Verifique que no se esta ejecutando una animación", "Imposible ejecutar animación", JOptionPane.WARNING_MESSAGE);
        
    }//GEN-LAST:event_mItemIniciarPasoActionPerformed

    private void mItemIniciarMedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemIniciarMedioActionPerformed
        
        if(arraysLoaded && !timer.isRunning())
            runTimer(1000);
        else
            msgDial("No se puede ejecutar la animación en este momento:\n1.- Verifique que ya se ejecuto el codigo\n"
            + "2.- Verifique que no se esta ejecutando una animación", "Imposible ejecutar animación", JOptionPane.WARNING_MESSAGE);
        
    }//GEN-LAST:event_mItemIniciarMedioActionPerformed

    private void mItemIniciarLentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemIniciarLentoActionPerformed
        
        if(arraysLoaded && !timer.isRunning())
            runTimer(2000);
        else
            msgDial("No se puede ejecutar la animación en este momento:\n1.- Verifique que ya se ejecuto el codigo\n"
            + "2.- Verifique que no se esta ejecutando una animación", "Imposible ejecutar animación", JOptionPane.WARNING_MESSAGE);
        
    }//GEN-LAST:event_mItemIniciarLentoActionPerformed

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // FUNCIONES PARA ARCHIVOS: ABRIR, CARGAR, GUARDAR, GUARDAR-COMO
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private void openFile(int id)
    {
        selectorDeArchivo.setDialogType(JFileChooser.OPEN_DIALOG);
        int res = selectorDeArchivo.showOpenDialog(MainFrame.this);
        
        if(res == JFileChooser.APPROVE_OPTION)
        {
            File archivo = selectorDeArchivo.getSelectedFile().getAbsoluteFile();
            
            if(archivo.getName().endsWith(".txt"))
            {
                loadFile(id, archivo);
            }
            
            else
            {
                msgDial("Solo se admiten archivos .txt", "Error de compatibilidad", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(res == JFileChooser.ERROR_OPTION)
            errorArchivo(selectorDeArchivo.getSelectedFile().getName());        
    }

    private void loadFile(int id, File archivo)
    {
        try (BufferedReader reader = Files.newBufferedReader(archivo.toPath()))
        {
            String line;
            String texto = "";

            while ((line = reader.readLine()) != null)
                texto += line+"\n";
            
            clearWorkspace();
            
            // Guardamos referencia al archivo cargado en programa
            switch(id)
            {
                case 0: // Codigo

                dirCodigo = archivo;
                panelEditor.setBorder(BorderFactory.createTitledBorder("\""+archivo.getName()+"\""));
                txtEditor.setText(texto);

                break;

                case 1: // Automata

                dirAuto = archivo;
                fileTextAutomata = texto;
                
                break;
                // Palabras Reservadas
                default:

                    dirPR = archivo;
                    fileTextPR = texto;
            }
            
            msgDial("\""+archivo.getName()+"\""+" leído exitosamente", "Lectura de archivo", JOptionPane.INFORMATION_MESSAGE);
            
            reader.close();
            
        } catch (IOException x) { errorArchivo(archivo.getName()); }        
    }
    
    private void saveAs()
    {
        if(!isEditorEmpty())
        {
            selectorDeArchivo.setDialogType(JFileChooser.SAVE_DIALOG);
            int res = selectorDeArchivo.showSaveDialog(MainFrame.this);
            
            if(res == JFileChooser.APPROVE_OPTION)
            {
                save(selectorDeArchivo.getSelectedFile().getAbsoluteFile());
            }
            
            if(res == JFileChooser.ERROR_OPTION)
                errorArchivo(selectorDeArchivo.getSelectedFile().getName());
        }
        
        else
        {
            msgDial("El archivo a guardar está vacio", "Error de Archivo", JOptionPane.ERROR_MESSAGE);
        }
    }    
    
    private void save(File archivo)
    {
        if(!archivo.getName().endsWith(".txt"))
        {
            msgDial("Solo se admiten archivos .txt", "Error de compatibilidad", JOptionPane.ERROR_MESSAGE);
        }
        
        else
        {
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo)))
            {
                String[] result = txtEditor.getText().split("\\n");
                
                for (String result1 : result)
                    pw.println(result1);

                pw.close();
                
                dirCodigo = archivo;
                
                panelEditor.setBorder(BorderFactory.createTitledBorder("\""+archivo.getName()+"\""));
                progTitle = false;
                
                msgDial("\""+archivo.getName()+"\""+" guardado exitosamente", "Operación de Archivo", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (IOException x) { errorArchivo(archivo.getName()); }
        }
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // FUNCIONES DE CARGA Y DIBUJO DE ARREGLOS
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private void setLimits()
    {
        lienzo.setBounds(X_MARGIN, graficosLP.getY()+Y_MARGIN, graficosLP.getWidth()-WIDTH_MARGIN, graficosLP.getHeight()-HEIGHT_MARGIN);
    }
    
    private void loadArrays()
    {
        if(!listArr.isEmpty())
        {
            int y = -50, ajust = 5, yActivo = -1;
            int index;
            String auxName = "";
            String values[];
            boolean primero = true;
            boolean maxArraysReached = false;
            Color colorAux;
            Historial auxHist = new Historial("", new Rectangle(), 0, null);
            
            for(String[] arr : listArr)
            {
                try
                {
                    // Sirve para poder diferenciar un arreglo de otro
                    // y colocarlos en su historial correspondiente
                    if(!auxName.equals(arr[0]))
                    {
                        auxName = arr[0];
                        auxHist = getHistByName(auxName);

                        // Si no existe el historial significa que es de un nuevo arreglo
                        // por lo tanto creamos su historial
                        if(auxHist == null && !maxArraysReached)
                        {
                            y += 60;

                            if(y < lienzo.getBounds().height)
                            {
                                if(checkWidth(arr[2].split("\\|").length) < lienzo.getBounds().width)
                                //{
                                    histArreglos.add(new Historial(auxName, lienzo.getBounds(), y, colorsT.getColorTheme()));
                                    auxHist = histArreglos.peekLast();
                                    primero = true;
                                //}
                               // else
                               // {
                               //     msgDial("EL arreglo \""+auxName+"\" contiene demasiados elementos\nNo se mostrara en la ventana de Gráficos",
                               //     "Arreglo con demasiados elementos", JOptionPane.WARNING_MESSAGE);
                               // }
                            }

                            else
                            {
                                maxArraysReached = true;
                                msgDial("No caben más arreglos en la pantalla\n Se mostran solo "+histArreglos.size(),
                                "Demasiados arrelgos", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        else
                        {
                            yActivo = auxHist.getPosInicial();
                        }
                    }

                    colorAux = auxHist.getColor();
                    values = arr[2].split("\\|");
                    index = Integer.parseInt(arr[4]);
                    Arreglo a;
                    
                    // Si ya tenemos asignada una medida 'y' para el arreglo
                    // Es decir este arreglo pertenece a un historial existente
                    if(yActivo > 0)
                    {
                        a = new Arreglo(arr[1], values, arr[3], index, colorAux , yActivo+ajust);
                        yActivo = -1; // Reseteamos valor
                    }

                    else
                        a = new Arreglo(arr[1], values, arr[3], index, colorAux , y+ajust);

                    if(primero)
                    {
                        auxHist.getArrs().add(a);
                        primero = false;
                    }
                    else
                    {
                        // Si el arreglo no se repite (es unico) y no es primer elemento del historial
                        if(uniqueArray(auxHist, a))
                        {
                            auxHist.getArrs().add(a);
                        }
                    }
                }
                catch(Exception ex) {} // NullPointerException
            }
        }
    }
    
    private int checkWidth(int nDigits)
    {
        return Arreglo.SPACING+((nDigits-1)*Arreglo.DIGIT_FACTOR);
    }
    
    private Historial getHistByName(String name)
    {
        for(Historial h : histArreglos)
            if(h.getName().equals(name))
                return h;
        
        return null;
    }
    
    // Verificamos que el arreglo no se repita en el historial
    private boolean uniqueArray(Historial h, Arreglo nuevo)
    {       
        try
        {
            for(Arreglo arr : h.getArrs())
            {
                if(arr.printAll().equals(nuevo.printAll()))
                {
                    return false;
                }
            }
        }
        catch(IndexOutOfBoundsException ex){ return false; }
        
        return true;
    }
    
    private int getMaxArrayCount()
    {
        int max = -1;
        
        for(Historial h: histArreglos)
        {
            if(h.getArrs().size() > max)
                max = h.getArrs().size();
        }
        
        return max;
    }    
    
    private ArrayList<Object> drawArraySet(Color color)
    {
        return drawArraySet(step, color);
    }
    
    private ArrayList<Object> drawArraySet(int k, Color color)
    {
        ArrayList<Object> attributes = new ArrayList<>();
        Arreglo aux;
        
        lienzo.clear();
        lienzo.paintStep(k, color);
        
        for(Historial h : histArreglos)
        {
            try
            {
                aux = h.getArrs().get(k);
            }
            catch(IndexOutOfBoundsException ob){ aux = h.getArrs().get(h.getArrs().size()-1); }
            
            attributes.add(h.getColor());
            attributes.add(aux.getLine());
        }
        
        return attributes;
    }

    private void clearGraphics()
    {
        graficosLP.removeAll();
        lienzo.clear();
        graficosLP.add(lienzo, new Integer(0));
    }
    
    private String printAllHistoriales()
    {
        String out = "";
        
        out += "\n--------------------------------------------\n";
        out += "\nTotalArrays = "+maxArrayN+"\n";
        
        for(Historial h : histArreglos)
        {
            out += h.getName()+" Y = "+h.getPosInicial()+":\n";
            
            for(Arreglo a : h.getArrs())
                out +=a.printAll()+"\n";
        }
        
        out += "\n--------------------------------------------\n";
        
        return out;
    }
    
    private void printListArr()
    {
        for(String[] elem: listArr)
        {
            for(String s : elem)
                System.out.print(s+" ");
            
            System.out.println();
        }
        
        System.out.println();
    }    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // FUNCIONES DE ACTUALIZACIÓN DE TABLAS E HISTORIAL
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////// 
    
    public void updateArr(ArrayList<String[]> list)
    {
        if(list != null)
        {
            int i;
            listArr.addAll(list);
            String[][] model = new String[listArr.size()][3];
            
            for(i = 0; i < listArr.size(); i++)
            {
                    model[i][0] = listArr.get(i)[0]; // Nombre
                    model[i][1] = listArr.get(i)[1]; // Tipo
                    model[i][2] = listArr.get(i)[2]; // Valores
            }
            
            tablaArreglos.setModel(new DefaultTableModel(model, stateColumns));
            tablaArreglos.setFont(new Font("Consolas", Font.PLAIN, 14));
            tablaArreglos.setRowHeight(20);
            tablaArreglos.setShowGrid(true);

            addHistory(model, "Operación de arreglo");
        }
        else
        {
            tablaArreglos.setModel(new DefaultTableModel(null, stateColumns));
        }
    }
    
    public void updateVar(ArrayList<String[]> list)
    {
        if(list != null)
        {
            int i;
            listVar.addAll(list);
            String[][] model = new String[listVar.size()][3];

            for(i = 0; i < listVar.size(); i++)
            {
                    model[i][0] = listVar.get(i)[0];
                    model[i][1] = listVar.get(i)[1];
                    model[i][2] = listVar.get(i)[2];
            }

            tablaVar.setModel(new DefaultTableModel(model, stateColumns));
            tablaVar.setFont(new Font("Consolas", Font.PLAIN, 14));
            tablaVar.setRowHeight(20);
            tablaVar.setShowGrid(true);

            addHistory(model, "Operación de variable");
        }
        else
        {
            tablaVar.setModel(new DefaultTableModel(null, stateColumns));
        }        
    }
    
    public void updateConst(ArrayList<String[]> list)
    {
        if(list != null)
        {
            int i;
            listCons.addAll(list);
            String[][] model = new String[listCons.size()][3];

            for(i = 0; i < listCons.size(); i++)
            {
                    model[i][0] = listCons.get(i)[0];
                    model[i][1] = listCons.get(i)[1];
                    model[i][2] = listCons.get(i)[2];
            }

            tablaCons.setModel(new DefaultTableModel(model, stateColumns));
            tablaCons.setFont(new Font("Consolas", Font.PLAIN, 14));
            tablaCons.setRowHeight(20);
            tablaCons.setShowGrid(true);

            addHistory(model, "Constantes");
        }
        else
        {
            tablaCons.setModel(new DefaultTableModel(null, stateColumns));
        }
    }
    
    private void addHistory(String[][] data, String tipo)
    {
        txtHistorial.append("\n---------------"+tipo+"---------------\n");
        
        for(String[] c : data)
        {
            for(String r : c)
                txtHistorial.append(r+"\t\t");
            
            txtHistorial.append("\n");
        }
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // FUNCIONES AUXILIARES UI
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////     
    
    private void clearWorkspace()
    {
        listArr.clear();
        listVar.clear();
        listCons.clear();
        histArreglos.clear();
        txtEditor.getHighlighter().removeAllHighlights();
        updateArr(null);
        updateVar(null);
        updateConst(null);
        clearGraphics();
        txtHistorial.setText("");
        txtOutput.setText("");
        txtConsola.setText("");
        progTitle = false;
        arraysLoaded  = false;
        maxArrayN = 0;
    }
    
    private void errorArchivo(String name)
    {
        msgDial("Ocurrio un problema al acceder al archivo \""+name+"\"", "Error de Archivo", JOptionPane.ERROR_MESSAGE);
    }
    
    private boolean isEditorEmpty(){ return txtEditor.getText().trim().isEmpty(); }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // FUNCIONES DE TIMER
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private void runTimer(int milis)
    {
        timer.setRepeats(true);
        timer.setDelay(milis);
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() instanceof Timer)
        {
            
            nextStep();
        }
    }
    
    private void nextStep()
    {
        if(step++ >= maxArrayN-1)
        {
            timer.stop();
            step = 0;
            color = Color.BLACK;
            highLight(drawArraySet(maxArrayN, color));
            msgDial("Animación terminada con éxito","Animación finalizada", JOptionPane.INFORMATION_MESSAGE);
            
            return;
        }
        
        highLight(drawArraySet(null));
    }
    
    private void highLight(ArrayList<Object> attributes)
    {
        try
        {
            int i;
            txtEditor.getHighlighter().removeAllHighlights();
            
            for(i = 0; i < attributes.size()-1; i++)
            {
                if((i%2) == 0)
                {
                    HLPainter = new DefaultHighlightPainter((Color)attributes.get(i));
                    txtEditor.getHighlighter().addHighlight(txtEditor.getLineStartOffset(Integer.parseInt((String)attributes.get(i+1))-1),
                    txtEditor.getLineEndOffset(Integer.parseInt((String)attributes.get(i+1))-1), HLPainter);
                }
            }
        }
        catch(BadLocationException bl){}
    }
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //
    // FUNCIONES DE UTILIDADES PARA EL CONTROLADOR
    //
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public String inputDial(String msg)
    {
        return JOptionPane.showInputDialog(this, msg, "Ingrese valor", JOptionPane.QUESTION_MESSAGE);
    }
    
    public void msgDial(String msg, String title, int tipe)
    {
        JOptionPane.showMessageDialog(this, msg, title, tipe);
    }
    
    public JTextArea getTxtOutput(){ return txtOutput; }
    public JTextArea getTxtConsola(){ return txtConsola; }

    public boolean isProgTitleSet() { return progTitle; }

    public void setProgTitle(String progTitle)
    {
        panelEditor.setBorder(BorderFactory.createTitledBorder("\""+progTitle+"\""));
        this.progTitle = true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane graficosLP;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JMenu mArchivo;
    private javax.swing.JMenu mAyuda;
    private javax.swing.JMenuItem mItCargarAuto;
    private javax.swing.JMenuItem mItGuardarComo;
    private javax.swing.JMenuItem mItPR;
    private javax.swing.JMenuItem mItemAbrir;
    private javax.swing.JMenuItem mItemAcerca;
    private javax.swing.JMenuItem mItemEjecutar;
    private javax.swing.JMenuItem mItemGuardar;
    private javax.swing.JMenuItem mItemIniciarLento;
    private javax.swing.JMenuItem mItemIniciarMedio;
    private javax.swing.JMenuItem mItemIniciarPaso;
    private javax.swing.JMenuItem mItemIniciarRapido;
    private javax.swing.JMenuItem mItemNuevo;
    private javax.swing.JMenuItem mItemVerAuto;
    private javax.swing.JMenuItem mItemVerPR;
    private javax.swing.JMenu mSalir;
    private javax.swing.JScrollPane panelArr;
    private javax.swing.JScrollPane panelCons;
    private javax.swing.JScrollPane panelConsola;
    private javax.swing.JScrollPane panelEditor;
    private javax.swing.JScrollPane panelHistorial;
    private javax.swing.JScrollPane panelSalida;
    private javax.swing.JScrollPane panelVar;
    private javax.swing.JPanel tabEstruct;
    private javax.swing.JTabbedPane tabPaneMain;
    private javax.swing.JPanel tabProgra;
    private javax.swing.JTable tablaArreglos;
    private javax.swing.JTable tablaCons;
    private javax.swing.JTable tablaVar;
    private javax.swing.JTextArea txtConsola;
    private javax.swing.JTextArea txtEditor;
    private javax.swing.JTextArea txtHistorial;
    private javax.swing.JTextArea txtOutput;
    // End of variables declaration//GEN-END:variables
}