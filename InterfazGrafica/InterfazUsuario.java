package InterfazGrafica;

import java.awt.Color;

import Herramientas.NLTK;
import Herramientas.OpenNLP;
import Herramientas.StCoreNLP;

public class InterfazUsuario extends javax.swing.JFrame {

   
    public InterfazUsuario() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setVisible(true);
    }
                       
    private void initComponents() {

    	 openNLPButton = new javax.swing.JButton();
         stanfordButton = new javax.swing.JButton();
         NLTKbutton = new javax.swing.JButton();
         jLabel1 = new javax.swing.JLabel();
         jLabel2 = new javax.swing.JLabel();

         setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
         setTitle("Herramienta de Procesamiento de Lenguaje Natural");
         setBackground(new java.awt.Color(255, 255, 255));
         setBounds(new java.awt.Rectangle(0, 0, 0, 0));
         setFocusCycleRoot(false);
         setForeground(java.awt.Color.white);
         setMaximumSize(new java.awt.Dimension(629, 386));
         setResizable(false);

         openNLPButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/onlplogo.png"))); // NOI18N
         openNLPButton.setMaximumSize(new java.awt.Dimension(300, 75));
         openNLPButton.setMinimumSize(new java.awt.Dimension(100, 50));
         openNLPButton.setPreferredSize(new java.awt.Dimension(200, 50));
         openNLPButton.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 openNLPButtonActionPerformed(evt);
             }
         });

         stanfordButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/logost.png"))); // NOI18N
         stanfordButton.setActionCommand("freeling");
         stanfordButton.setPreferredSize(new java.awt.Dimension(354, 50));
         stanfordButton.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 stanfordButtonActionPerformed(evt);
             }
         });

         NLTKbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/nltklogo.png"))); // NOI18N
         NLTKbutton.setText("NTLK");
         NLTKbutton.setMaximumSize(new java.awt.Dimension(300, 60));
         NLTKbutton.setPreferredSize(new java.awt.Dimension(220, 50));
         NLTKbutton.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 NLTKbuttonActionPerformed(evt);
             }
         });

         jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
         jLabel1.setText("Seleccione la herramienta...");
         jLabel1.setMaximumSize(new java.awt.Dimension(200, 50));
         jLabel1.setPreferredSize(new java.awt.Dimension(200, 50));

         jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/HPLN_500.png"))); // NOI18N

         javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
         getContentPane().setLayout(layout);
         layout.setHorizontalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addGroup(layout.createSequentialGroup()
                         .addGap(34, 34, 34)
                         .addComponent(jLabel2))
                     .addGroup(layout.createSequentialGroup()
                         .addContainerGap()
                         .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                     .addGroup(layout.createSequentialGroup()
                         .addGap(315, 315, 315)
                         .addComponent(openNLPButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                     .addGroup(layout.createSequentialGroup()
                         .addGap(237, 237, 237)
                         .addComponent(stanfordButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                     .addGroup(layout.createSequentialGroup()
                         .addGap(321, 321, 321)
                         .addComponent(NLTKbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                 .addContainerGap(213, Short.MAX_VALUE))
         );
         layout.setVerticalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addGap(42, 42, 42)
                 .addComponent(jLabel2)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                 .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addGap(12, 12, 12)
                 .addComponent(openNLPButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addGap(36, 36, 36)
                 .addComponent(stanfordButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addGap(42, 42, 42)
                 .addComponent(NLTKbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addGap(83, 83, 83))
         );

         pack();
    }                       

    private void openNLPButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
    			OpenNLP openNLP = new OpenNLP();
    			Procesamiento procesamiento = new Procesamiento (openNLP, "/data/onlplogo.png", "OpenNLP");
    			this.dispose();
        
    }                                             

    private void stanfordButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        	
    			StCoreNLP core = new StCoreNLP ();
    			Procesamiento procesamiento = new Procesamiento (core, "/data/logost.png", "Stanford CoreNLP");
    			this.dispose();
    	
    	
    }                                              

    private void NLTKbuttonActionPerformed(java.awt.event.ActionEvent evt) {                                           
       		NLTK nltk = new NLTK ();
       		Procesamiento procesamiento = new Procesamiento (nltk, "/data/nltklogo.png", "NLTK");
       		this.dispose();
    		
    }                                          
                  
    private javax.swing.JButton NLTKbutton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton openNLPButton;
    private javax.swing.JButton stanfordButton;
                   
}
