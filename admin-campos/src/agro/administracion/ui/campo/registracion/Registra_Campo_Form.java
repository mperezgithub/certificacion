/*
 * Proyecto Administracion de Campos - Agro SRL
 * 
 * 2018 Certificacion de Instructores - Plan 111mil
 */
package agro.administracion.ui.campo.registracion;

import agro.administracion.Campo;
import agro.administracion.EstadoCampo;
import agro.administracion.Lote;
import agro.administracion.TipoSuelo;
import agro.administracion.controlador.GestorCampo;
import agro.administracion.controlador.HibernateUtil;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FocusTraversalPolicy;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AMP <Angel Mario Perez>
 */
public class Registra_Campo_Form extends javax.swing.JFrame {

    private GestorCampo gestorCampo;
    protected PoliticaNavegabilidadCampos focusTraversalPolicy;

    public Registra_Campo_Form() {
        initComponents();

    }

    /**
     * Validar nombre de campo duplicado
     *
     * @param nombreCampo
     * @param errormsg
     * @return
     */
    public boolean validarNombreCampoEnUso(String nombreCampo, String errormsg) {
        if (nombreCampo != null && nombreCampo.length() > 0 && this.gestorCampo.checkearCampoExistente(nombreCampo)) {
            mostrarMensajeErrorCampo(errormsg);
            return false;
        }
        return true;
    }

    /**
     * Alerta con mensaje simple al usuario de acuerdo al UI solicitado
     *
     * @param errormsg
     */
    public void mostrarMensajeErrorCampo(String errormsg) {
        jl_mensaje_error.setText(errormsg);
        jl_mensaje_error.setVisible(true);
    }

    /**
     * Constructor con parametros
     *
     * @param gestor clase gestora de campos
     */
    public Registra_Campo_Form(GestorCampo gestor) {
        initComponents();
        this.gestorCampo = gestor;

        //inicializo tipo de suelos
        this.cb_tipo_suelo_lote.setModel(new DefaultComboBoxModel<>(this.gestorCampo.getTiposSuelo()));
        jt_lotes.setCellSelectionEnabled(false);
        jt_lotes.setRowSelectionAllowed(true);

        //adding check para superficie de campos
        this.tf_superficie_campo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tf_superficie_campo.setBackground(Color.white);
                if (tf_superficie_campo.getText().length() > 0) {
                    try {
                        Double.parseDouble(tf_superficie_campo.getText());
                    } catch (NumberFormatException z) {
                        tf_superficie_campo.setText("");
                        tf_superficie_campo.setBackground(new Color(220, 218, 227));
                    }
                }
            }
        ;
        });
        
        //agregando check para superficie de lotes
        this.tf_superficie_lote.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tf_superficie_lote.setBackground(Color.white);
                if (tf_superficie_lote.getText().length() > 0) {
                    try {
                        Double.parseDouble(tf_superficie_lote.getText());
                    } catch (NumberFormatException z) {
                        tf_superficie_lote.setText("");
                        tf_superficie_lote.setBackground(new Color(220, 218, 227));
                    }
                }
            }
        ;
        });
        
        //agregando check para numero de lote - tf_numero_lote
        this.tf_numero_lote.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                tf_numero_lote.setBackground(Color.white);
                if (tf_numero_lote.getText().length() > 0) {
                    try {
                        Double.parseDouble(tf_numero_lote.getText());
                    } catch (NumberFormatException z) {
                        tf_numero_lote.setText("");
                        tf_numero_lote.setBackground(new Color(220, 218, 227));
                    }
                }
            }
        ;
        }); 
        
        //Agregado de listener de foco para checkear tf_numero_lote
        this.tf_numero_lote.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {

                //Obtiene el model relacionado a la tabla
                DefaultTableModel model = (DefaultTableModel) jt_lotes.getModel();

                //Valida numero de lotes repetidos
                Vector vector = model.getDataVector();
                for (Object o : vector) {
                    Vector v = (Vector) o;
                    String numeroLoteEnTabla = (String) v.elementAt(0);

                    //String superficie = (String) v.elementAt(1);
                    if (numeroLoteEnTabla != null && numeroLoteEnTabla.equals(tf_numero_lote.getText())) {
                        mostrarMessageErrorLote("Este número de Lote ya está en uso");
                    }
                }
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (jl_error_message_lote.getText() != null) {
                    jl_error_message_lote.setText("");
                }
            }
        });

        //Agregado de listener para checkear el nombre de campo ingresado
        this.tf_nombre_campo.addFocusListener(new FocusListener() {
            public void focusLost(FocusEvent e) {
                validarNombreCampoEnUso(tf_nombre_campo.getText(), "Este nombre ya está en uso...");
            }

            @Override
            public void focusGained(FocusEvent e) {
                if (jl_mensaje_error.isVisible()) {
                    jl_mensaje_error.setVisible(false);
                }
            }
        });

        //politica de tabs
        focusTraversalPolicy = new PoliticaNavegabilidadCampos();
        setFocusTraversalPolicy(focusTraversalPolicy);

        //labels de errores
        jl_mensaje_error.setVisible(false);
        jl_error_message_lote.setVisible(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_nombre_campo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf_superficie_campo = new javax.swing.JTextField();
        jl_mensaje_error = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tf_numero_lote = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tf_superficie_lote = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cb_tipo_suelo_lote = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jb_agregar_lote = new javax.swing.JButton();
        jb_editar_lote = new javax.swing.JButton();
        jb_quitar_lote = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_lotes = new javax.swing.JTable();
        jl_error_message_lote = new javax.swing.JLabel();
        jb_cancelar = new javax.swing.JButton();
        jb_registrar_campo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrar Campo");
        setExtendedState(MAXIMIZED_BOTH);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Campo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(204, 204, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Superficie");

        tf_nombre_campo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("ha");

        tf_superficie_campo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_superficie_campo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_superficie_campoActionPerformed(evt);
            }
        });

        jl_mensaje_error.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_mensaje_error.setForeground(new java.awt.Color(255, 51, 51));
        jl_mensaje_error.setText(" ");
        jl_mensaje_error.setFocusable(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Usuario");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 0, 153));
        jLabel13.setText("amarioperez");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(55, 55, 55)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tf_nombre_campo, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jl_mensaje_error, javax.swing.GroupLayout.PREFERRED_SIZE, 608, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tf_superficie_campo, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addContainerGap(62, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tf_nombre_campo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jl_mensaje_error))
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(tf_superficie_campo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lotes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 18), new java.awt.Color(204, 204, 255))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel7.setText("Tenga en cuenta que debe ingresar al menos 1 Lote");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Nro");

        tf_numero_lote.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_numero_lote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_numero_loteActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Superficie");

        tf_superficie_lote.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tf_superficie_lote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_superficie_loteActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("ha");

        cb_tipo_suelo_lote.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Tipo de Suelo");

        jb_agregar_lote.setText("Agregar Lote   >");
        jb_agregar_lote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_agregar_loteActionPerformed(evt);
            }
        });

        jb_editar_lote.setText("Editar");
        jb_editar_lote.setEnabled(false);
        jb_editar_lote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_editar_loteActionPerformed(evt);
            }
        });

        jb_quitar_lote.setText("Quitar");
        jb_quitar_lote.setEnabled(false);
        jb_quitar_lote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_quitar_loteActionPerformed(evt);
            }
        });

        jt_lotes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jt_lotes.setForeground(new java.awt.Color(102, 102, 102));
        jt_lotes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nro", "Superficie", "TipoSuelo"
            }
        ));
        jt_lotes.setColumnSelectionAllowed(true);
        jt_lotes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jt_lotes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jt_lotesFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jt_lotesFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jt_lotes);
        jt_lotes.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jl_error_message_lote.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jl_error_message_lote.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(tf_superficie_lote, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4))
                                    .addComponent(tf_numero_lote, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cb_tipo_suelo_lote, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(jb_agregar_lote, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jl_error_message_lote, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 762, Short.MAX_VALUE)
                        .addComponent(jb_editar_lote, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jb_quitar_lote, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addComponent(jScrollPane1)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_numero_lote, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_superficie_lote, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cb_tipo_suelo_lote, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(32, 32, 32)
                        .addComponent(jl_error_message_lote, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jb_agregar_lote, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jb_quitar_lote, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jb_editar_lote, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(493, 493, 493)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jb_cancelar.setText("Cancelar");
        jb_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_cancelarActionPerformed(evt);
            }
        });

        jb_registrar_campo.setText("Registrar Campo");
        jb_registrar_campo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jb_registrar_campoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jb_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jb_registrar_campo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 556, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jb_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jb_registrar_campo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_superficie_campoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_superficie_campoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_superficie_campoActionPerformed

    private void tf_numero_loteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_numero_loteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_numero_loteActionPerformed

    private void tf_superficie_loteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_superficie_loteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_superficie_loteActionPerformed

    private void jb_agregar_loteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_agregar_loteActionPerformed

        //Obtiene el model relacionado a la tabla
        DefaultTableModel model = (DefaultTableModel) jt_lotes.getModel();

        //si no hay errores procede a checkear los datos para luego agregar la fila en la tabla
        if (!estaRepetidoLote(model)) {
            List<String> row = new ArrayList();

            String numeroLote = tf_numero_lote.getText();
            String superficie = tf_superficie_lote.getText();
            String tipoSuelo = cb_tipo_suelo_lote.getSelectedItem().toString();

            if (numeroLote.isEmpty()) {
                tf_numero_lote.requestFocus();
                return;
            }

            if (superficie.isEmpty()) {
                tf_superficie_lote.requestFocus();
                mostrarMessageErrorLote("Ingrese número de superficie");
                return;
            }

            if (!numeroLote.isEmpty() && !superficie.isEmpty() && !tipoSuelo.isEmpty()) {
                row.add(numeroLote);
                row.add(superficie);
                row.add(tipoSuelo);
                model.addRow(row.toArray());
                limpiarCamposLote();
                //limpiar mensaje de error de campo
                jl_mensaje_error.setText("");
            }
        }
    }//GEN-LAST:event_jb_agregar_loteActionPerformed
    /**
     * Muestra mensajes de error en la carga de lotes
     *
     * @param errormsg
     */
    public void mostrarMessageErrorLote(String errormsg) {
        jl_error_message_lote.setText(errormsg);
        jl_error_message_lote.setVisible(true);
    }

    /**
     * Valida si un lote esta repetido o ya fue ingresado en la tabla
     *
     * @return true si el lote ya se encuentra en la tabla o bien ha sido
     */
    private boolean estaRepetidoLote(DefaultTableModel model) {
        boolean estaRepetidoLote = false;

        //Valida numero de lotes repetidos
        Vector vector = model.getDataVector();
        for (Object o : vector) {
            Vector v = (Vector) o;
            String numeroLoteEnTabla = (String) v.elementAt(0);

            //String superficie = (String) v.elementAt(1);
            if (numeroLoteEnTabla != null && numeroLoteEnTabla.equals(tf_numero_lote.getText())) {
                mostrarMessageErrorLote("Este número de Lote ya está en uso");
                jb_agregar_lote.requestFocus();
                estaRepetidoLote = true;
                break;
            }
        }
        return estaRepetidoLote;
    }

    /**
     * Limpia los campos de ingreso de lotes
     */
    private void limpiarCamposLote() {
        tf_numero_lote.setText("");
        tf_superficie_lote.setText("");
        cb_tipo_suelo_lote.setSelectedIndex(0);
        //focus en numero de lote
        tf_numero_lote.requestFocus();
    }
    private void jb_editar_loteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_editar_loteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jb_editar_loteActionPerformed

    private void jb_quitar_loteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_quitar_loteActionPerformed
        DefaultTableModel model = (DefaultTableModel) jt_lotes.getModel();
        try {
            int selectedRowIndex = jt_lotes.getSelectedRow();
            model.removeRow(selectedRowIndex);
        } catch (Exception e) {
            System.out.println("fila no encontrada:" + e.getMessage());
        } finally {
            if (jt_lotes.getRowCount() == 0) {
                jb_quitar_lote.setEnabled(false);
                jb_editar_lote.setEnabled(false);
            }
        }
    }//GEN-LAST:event_jb_quitar_loteActionPerformed

    private void jb_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_cancelarActionPerformed
        dispose();
        HibernateUtil.shutdown();
        System.exit(0);
    }//GEN-LAST:event_jb_cancelarActionPerformed

    private void jb_registrar_campoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jb_registrar_campoActionPerformed
        if (validarDatosMinimosRequeridos()) {

            //presenta opciones de confirmacion
            Object[] choices = {"Cancelar", "Continuar"};
            Object defaultChoice = choices[0];
            int resp = JOptionPane.showOptionDialog(this,
                    "Confirma el registro del Campo",
                    "Registrar Campo",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    choices,
                    defaultChoice);

            //if la respuesta es continuar
            if (resp == 1) {
                //guarda la informacion ingresada en el formulario
                guardarInformacionCampo();

                //presenta datos en pantalla
                presentaInformacionCampoGuardado();
            }
        }
    }//GEN-LAST:event_jb_registrar_campoActionPerformed

    /**
     * Mediante un formulario se presenta datos en pantalla
     */
    private void presentaInformacionCampoGuardado() {
        //Presento datos en pantalla del regitro del campo realizado
        String nombreCampo = this.tf_nombre_campo.getText();
        Registra_Campo_Completado formCompletado = new Registra_Campo_Completado(nombreCampo, jt_lotes, this);
        formCompletado.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formCompletado.setVisible(true);
        formCompletado.setPreferredSize(new Dimension(751, 655));
        formCompletado.setResizable(false);
        formCompletado.pack();
    }

    /**
     * Guardar la informacion del Campo ingresado
     * @throws NumberFormatException 
     */
    private void guardarInformacionCampo() throws NumberFormatException {
        //Obtiene el model relacionado a la tabla para calcular la superficie de los lotes ingresados y guardo
        DefaultTableModel model = (DefaultTableModel) jt_lotes.getModel();
        Vector vector = model.getDataVector();
        
        Set lotes = new HashSet<Lote>();
        
        for (Object o : vector) {
            Vector v = (Vector) o;
            String numeroLote = (String) v.elementAt(0);
            String superficie = (String) v.elementAt(1);
            String tipoSueloDescripcion = (String) v.elementAt(2);
            
            //Obtengo datos de los lotes
            TipoSuelo tipoSuelo = gestorCampo.getTipoSueloPorDescripcion(tipoSueloDescripcion);
            System.out.print(tipoSuelo);
            
            Lote lote = new Lote();
            lote.setNumeroLote(Integer.parseInt(numeroLote));
            lote.setSuperficie(Double.parseDouble(superficie));
            lote.setTipoSuelo(tipoSuelo);
            gestorCampo.guardarLote(lote);
            
            lotes.add(lote);
        }
        
        //Obtengo datos del campo y guardo
        Campo campo = new Campo();
        campo.setNombre(this.tf_nombre_campo.getText());
        campo.setEstado(EstadoCampo.CREADO);
        campo.setLotes(lotes);
        gestorCampo.guardarCampo(campo);
    }

    /**
     * Limpiar el formulario completo
     */
    public void limpiarFormulario() {
        tf_nombre_campo.setText("");
        tf_superficie_campo.setText("");
        tf_numero_lote.setText("");
        tf_superficie_lote.setText("");
        cb_tipo_suelo_lote.setSelectedIndex(0);
        DefaultTableModel tMOdel = (DefaultTableModel) jt_lotes.getModel();
        tMOdel.setRowCount(0);
        //focus en nombre campo
        tf_nombre_campo.requestFocus();
    }

    /**
     * Validacion de datos ingresados en el formulario
     *
     * @return true si los datos minimos requeridos estan correctamente
     * ingresados.En caso negativo se advertira al usuario al respecto y se
     * devuelve false
     */
    private boolean validarDatosMinimosRequeridos() {

        //Validar nombre de campo en uso
        if (!validarNombreCampoEnUso(tf_nombre_campo.getText(), "Este nombre ya está en uso...")) {
            jb_registrar_campo.requestFocus();
            return false;
        }

        //Validar nombre del campo
        if (tf_nombre_campo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Ingrese nombre de Campo",
                    "Datos Mínimos Requeridos",
                    JOptionPane.INFORMATION_MESSAGE);

            tf_nombre_campo.requestFocus();
            return false;
        }

        //Validar superficie del campo
        if (tf_superficie_campo.getText().isEmpty()) {
            tf_superficie_campo.requestFocus();
            JOptionPane.showMessageDialog(null,
                    "Ingrese la superficie del Campo",
                    "Datos Mínimos Requeridos",
                    JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        //Obtiene superficie campo
        String superficieCampoValor = tf_superficie_campo.getText();
        Double superficieCampo = Double.parseDouble(superficieCampoValor);

        //Obtiene el model relacionado a la tabla para calcular la superficie de los lotes ingresados
        DefaultTableModel model = (DefaultTableModel) jt_lotes.getModel();
        Vector vector = model.getDataVector();
        Double superficieLotes = 0d;
        for (Object o : vector) {
            Vector v = (Vector) o;
            String superficie = (String) v.elementAt(1);
            if (superficie != null) {
                Double superficieLote = Double.parseDouble(superficie);
                superficieLotes = superficieLotes + superficieLote;
            }
        }
        if (!superficieLotes.equals(superficieCampo)) {
            mostrarMensajeErrorCampo("La suma de las superficie de los lotes no coincide con la superficie total del campo");
            return false;
        }
        return true;
    }

    private void jt_lotesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_lotesFocusGained
        int selectedRowIndex = jt_lotes.getSelectedRow();
        if (selectedRowIndex > -1) {
            jb_quitar_lote.setEnabled(true);
            jb_editar_lote.setEnabled(true);
        }
    }//GEN-LAST:event_jt_lotesFocusGained

    private void jt_lotesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jt_lotesFocusLost

    }//GEN-LAST:event_jt_lotesFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cb_tipo_suelo_lote;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton jb_agregar_lote;
    private javax.swing.JButton jb_cancelar;
    private javax.swing.JButton jb_editar_lote;
    private javax.swing.JButton jb_quitar_lote;
    private javax.swing.JButton jb_registrar_campo;
    private javax.swing.JLabel jl_error_message_lote;
    private javax.swing.JLabel jl_mensaje_error;
    private javax.swing.JTable jt_lotes;
    private javax.swing.JTextField tf_nombre_campo;
    private javax.swing.JTextField tf_numero_lote;
    private javax.swing.JTextField tf_superficie_campo;
    private javax.swing.JTextField tf_superficie_lote;
    // End of variables declaration//GEN-END:variables

    /**
     * Clase manejo seteo de navegabilidad de los campos de ingreso del
     * formulario
     */
    class PoliticaNavegabilidadCampos extends FocusTraversalPolicy {

        @Override
        public Component getComponentAfter(Container focusCycleRoot, Component aComponent) {
            if (aComponent.equals(tf_nombre_campo)) {
                return tf_superficie_campo;
            } else if (aComponent.equals(tf_superficie_campo)) {
                return tf_numero_lote;
            } else if (aComponent.equals(tf_numero_lote)) {
                return tf_superficie_lote;
            } else if (aComponent.equals(tf_superficie_lote)) {
                return cb_tipo_suelo_lote;
            } else if (aComponent.equals(cb_tipo_suelo_lote)) {
                return jb_agregar_lote;
            } else {
                return jt_lotes;
            }
        }

        @Override
        public Component getComponentBefore(Container focusCycleRoot, Component aComponent) {
            if (aComponent.equals(cb_tipo_suelo_lote)) {
                return tf_superficie_lote;
            } else if (aComponent.equals(tf_superficie_lote)) {
                return tf_numero_lote;
            } else if (aComponent.equals(tf_numero_lote)) {
                return tf_superficie_campo;
            } else if (aComponent.equals(jb_agregar_lote)) {
                return cb_tipo_suelo_lote;
            } else {
                return tf_nombre_campo;
            }
        }

        @Override
        public Component getDefaultComponent(Container focusCycleRoot) {
            return tf_nombre_campo;
        }

        @Override
        public Component getFirstComponent(Container focusCycleRoot) {
            return tf_nombre_campo;
        }

        @Override
        public Component getLastComponent(Container focusCycleRoot) {
            return cb_tipo_suelo_lote;
        }
    }
}
