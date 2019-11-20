package Vista;

import Controlador.Control;
import Modelo.ParserException;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame
{
    private final Object[] stateColumns = {"Lexema", "Tipo", "Valor(es)"};
    private final Lienzo lienzo;
    private final ArrayList <Arreglo> arrs;
    private final ArrayList <String[]> listArr;
    private final ArrayList <String[]> listCons;
    private final ArrayList <String[]> listVar;
    private final JFileChooser selectorDeArchivo;
    private final Control control;
    private AboutFrame frmAbout;
    private FileFrame frmAuto;
    private FileFrame frmPR;
    private TextLineNumber txtLn;
    private String txtAutomata;
    private String txtPR;
    private File dirPR;
    private File dirAuto;
    private File dirCodigo;
    private long loggerEventID;

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
        
        txtAutomata = "";
        txtPR = "";
        
        loggerEventID = 0;
        
        frmAbout = null;
        frmAuto = null;
        frmPR = null;
        control = new Control(this);
        
        arrs = new ArrayList <>();
        listArr = new ArrayList <>();
        listCons = new ArrayList <>();
        listVar = new ArrayList <>();
        lienzo = new Lienzo(arrs);
        
        Arreglo arr1 = new Arreglo("Nums 1");
        Arreglo arr2 = new Arreglo("Nums 2");
        
        arr1.add(20, 20, 1, "First");
        arr1.add(30, 30, 2, "Second");
        arr1.add(40, 40, 3, "Third");
        
        arr2.add(50, 50, 4, "F2");
        arr2.add(60, 60, 5, "S2");
        arr2.add(70, 70, 6, "T2");
        
        arrs.add(arr1);
        arrs.add(arr2);
        //lienzo.setBounds(12, graficosLP.getY()+20, graficosLP.getWidth()-25, graficosLP.getHeight()-40);
        //arrs.setBounds(lienzo.getBounds());
        
        //graficosLP.add(lienzo, new Integer(0));
        
        txtLn = new TextLineNumber(txtEditor);
        panelEditor.setRowHeaderView(txtLn);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem2 = new javax.swing.JMenuItem();
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
        tabConfig = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mItCargarAuto = new javax.swing.JMenuItem();
        mItemVerAuto = new javax.swing.JMenuItem();
        mArchivo = new javax.swing.JMenu();
        mItemNuevo = new javax.swing.JMenuItem();
        mItemAbrir = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mItemEjecutar = new javax.swing.JMenuItem();
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
                    .addComponent(panelEditor, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                    .addComponent(graficosLP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabPrograLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelConsola, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
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
                    .addComponent(panelHistorial)
                    .addGroup(tabEstructLayout.createSequentialGroup()
                        .addComponent(panelArr, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelVar, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelCons, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)))
                .addContainerGap())
        );

        tabPaneMain.addTab("Estructuras de datos ", new javax.swing.ImageIcon(getClass().getResource("/Icons/list.png")), tabEstruct); // NOI18N

        javax.swing.GroupLayout tabConfigLayout = new javax.swing.GroupLayout(tabConfig);
        tabConfig.setLayout(tabConfigLayout);
        tabConfigLayout.setHorizontalGroup(
            tabConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1013, Short.MAX_VALUE)
        );
        tabConfigLayout.setVerticalGroup(
            tabConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 595, Short.MAX_VALUE)
        );

        tabPaneMain.addTab("Configuraciones ", new javax.swing.ImageIcon(getClass().getResource("/Icons/settings.png")), tabConfig); // NOI18N

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
            JOptionPane.showMessageDialog(this,
                "No hay codigo para ejecutar",
                "Error de código", JOptionPane.WARNING_MESSAGE);
            
            return;
        }
        
        if(dirAuto == null)
        {
            JOptionPane.showMessageDialog(this,
                "Automata no cargado",
                "Error de automata", JOptionPane.WARNING_MESSAGE);
            
            return;
        }
        
        if(dirPR == null)
        {
            JOptionPane.showMessageDialog(this,
                "Palabras reservadas sin cargar",
                "Error de palabras reservadas", JOptionPane.WARNING_MESSAGE);
            
            return;
        }
        try
        {
            control.cargarAuto(dirAuto.getAbsolutePath());
            control.cargarReserv(dirPR.getAbsolutePath());
            clearWorkspace();
            control.start(dirAuto.getAbsolutePath(), txtEditor.getText());
            setLimits();
        }
        catch(IOException ie){ System.err.println(ie.getStackTrace()[0].getClassName());
        JOptionPane.showMessageDialog(this,
                               "No se cargo correctamente el automata o palabras reservadas\n Intente de nuevo",
                               "Error de archivo", JOptionPane.ERROR_MESSAGE); }
        catch(ParserException pe){ JOptionPane.showMessageDialog(this, pe.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE); }
        
    }//GEN-LAST:event_mItemEjecutarActionPerformed

    private void mItemVerAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemVerAutoActionPerformed
        if(dirAuto != null)
        {
            frmAuto = new FileFrame("Automata", dirAuto.getAbsolutePath(), txtAutomata);
            frmAuto.setLocationRelativeTo(this);
            frmAuto.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(this,
            "No existe automata\nCarguelo primero",
            "Automata", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_mItemVerAutoActionPerformed

    private void mItemVerPRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemVerPRActionPerformed
        if(dirPR != null)
        {
            frmPR = new FileFrame("Palabras Reservadas", dirPR.getAbsolutePath(), txtPR);
            frmPR.setLocationRelativeTo(this);
            frmPR.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(this,
            "No existen palabras reservadas\nCarguelas primero",
            "Palabras reservadas", JOptionPane.INFORMATION_MESSAGE);
        }        
    }//GEN-LAST:event_mItemVerPRActionPerformed

    private void mItemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemNuevoActionPerformed
        
        txtEditor.setText("");
        clearWorkspace();
        panelEditor.setBorder(BorderFactory.createTitledBorder("Editor"));
        
    }//GEN-LAST:event_mItemNuevoActionPerformed

    
    private void openFile(int id)
    {
        selectorDeArchivo.setDialogType(JFileChooser.OPEN_DIALOG);
        int res = selectorDeArchivo.showOpenDialog(MainFrame.this);
        
        if(res == JFileChooser.APPROVE_OPTION)
        {
            File archivo = selectorDeArchivo.getSelectedFile().getAbsoluteFile();
            
            if(archivo.getName().endsWith("txt"))
            {
                try (BufferedReader reader = Files.newBufferedReader(archivo.toPath()))
                {
                    String line = null;
                    String texto = "";
                    
                    while ((line = reader.readLine()) != null)
                        texto += line+"\n";
                    
                    // Limpiamos editor consola y salida
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
                        txtAutomata = texto;
                        
                        break;
                        // Palabras Reservadas
                        default:
                            
                            dirPR = archivo;
                            txtPR = texto;
                    }
                    
                    //txtLogger.append((++loggerEventID)+".- Lectura de archivo \""+archivo.getName()+"\"\n");
                    
                    JOptionPane.showMessageDialog(this,
                                          "Archivo leído exitosamente",
                                          "Lectura de archivo", JOptionPane.INFORMATION_MESSAGE);
                    
                    reader.close();
                } catch (IOException x) { errorArchivo(); }
            }
            
            else
            {
                JOptionPane.showMessageDialog(this,
                                          "Solo se admiten archivos .txt",
                                          "Error de compatibilidad", JOptionPane.ERROR_MESSAGE);
                
                //txtLogger.append((++loggerEventID)+".- Error de formato de archivo\n");
            }
        }
        
        if(res == JFileChooser.ERROR_OPTION)
            errorArchivo();        
    }
    
    private void clearWorkspace()
    {
        listArr.clear();
        listVar.clear();
        listCons.clear();
        arrs.clear();
        updateArr(null);
        updateVar(null);
        updateConst(null);
        refresh();
        txtHistorial.setText("");
        txtOutput.setText("");
        txtConsola.setText("");
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
                errorArchivo();
        }
        
        else
        {
            JOptionPane.showMessageDialog(this,
                                          "El archivo a guardar está vacio",
                                          "Error de Archivo", JOptionPane.ERROR_MESSAGE);
            //txtLogger.append((++loggerEventID)+".- Archivo no guardado\n");
        }
    }
    
    private void errorArchivo()
    {
        //txtLogger.append((++loggerEventID)+".- Error de lectura de archivo\n");
        
        JOptionPane.showMessageDialog(this,
                    "Ocurrio un problema al acceder al archivo",
                    "Error de Archivo", JOptionPane.ERROR_MESSAGE);
    }
    
    private boolean isEditorEmpty(){ return txtEditor.getText().trim().isEmpty(); }
    
    private void save(File archivo)
    {
        if(!archivo.getName().endsWith(".txt"))
        {
            //txtLogger.append((++loggerEventID)+".- Error al guardar archivo\n");
                    
            JOptionPane.showMessageDialog(this,
                                          "Solo se admiten archivos .txt",
                                          "Error de compatibilidad", JOptionPane.ERROR_MESSAGE);
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
                //txtLogger.append((++loggerEventID)+".- Archivo \""+archivo.getName()+"\" guardado\n");
                
                JOptionPane.showMessageDialog(this,
                                              "Archivo guardado exitosamente",
                                              "Operación de Archivo", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (IOException x) { errorArchivo(); }
        }
    }
    
    private void setLimits()
    {
        System.out.println("GraficosLP X: "+graficosLP.getX()+ " GraficosLP Y:"+graficosLP.getY());
        System.out.println("GraficosLP W: "+graficosLP.getWidth()+ " GraficosLP H:"+graficosLP.getHeight());
        
        lienzo.setBounds(0, graficosLP.getY(), graficosLP.getWidth(), graficosLP.getHeight());
        Rectangle bounds = lienzo.getBounds();
        
        for(Arreglo a : arrs)
            a.setBounds(bounds);
        
        graficosLP.removeAll();
        lienzo.repaint();
        graficosLP.add(lienzo, new Integer(0));
    }
    
    public void updateArr(ArrayList<String[]> list)
    {
        if(list != null)
        {
            int i;
            listArr.addAll(list);
            String[][] model = new String[listArr.size()][3];
            
            for(i = 0; i < listArr.size(); i++)
            {
                    model[i][0] = listArr.get(i)[0];
                    model[i][1] = listArr.get(i)[1];
                    model[i][2] = listArr.get(i)[2];
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
    
    public String inputDial(String msg)
    {
        return JOptionPane.showInputDialog(this, msg, "Ingrese valor", JOptionPane.QUESTION_MESSAGE);
    }
    
    public void msgDial(String msg, String title, int tipe)
    {
        switch(tipe)
        {
            // Info
            case 0: JOptionPane.showMessageDialog(this, msg, title, JOptionPane.INFORMATION_MESSAGE);
            break;
            
            // Warning
            case 1: JOptionPane.showMessageDialog(this, msg, title, JOptionPane.WARNING_MESSAGE);
            break;
            
            // Error
            default: JOptionPane.showMessageDialog(this, msg, title, JOptionPane.ERROR_MESSAGE);
                                           
        }
    }
    
    public void refresh(){ lienzo.repaint(); }
    
    public JTextArea getTxtOutput(){ return txtOutput; }
    public JTextArea getTxtConsola(){ return txtConsola; }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane graficosLP;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenu mArchivo;
    private javax.swing.JMenu mAyuda;
    private javax.swing.JMenuItem mItCargarAuto;
    private javax.swing.JMenuItem mItGuardarComo;
    private javax.swing.JMenuItem mItPR;
    private javax.swing.JMenuItem mItemAbrir;
    private javax.swing.JMenuItem mItemAcerca;
    private javax.swing.JMenuItem mItemEjecutar;
    private javax.swing.JMenuItem mItemGuardar;
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
    private javax.swing.JPanel tabConfig;
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