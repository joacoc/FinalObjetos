package InterfazGrafica;

import java.awt.Color;
import java.io.IOException;

import Herramientas.Freeling;
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
         freelingButtonEs = new javax.swing.JButton();
         freelingButtonEn = new javax.swing.JButton();
         chatButton = new javax.swing.JButton();
         labelOP = new javax.swing.JLabel();
         labelSt = new javax.swing.JLabel();
         labelNLTK = new javax.swing.JLabel();
         labelFr1 = new javax.swing.JLabel();
         labelFr2 = new javax.swing.JLabel();


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
         
       
      
         freelingButtonEs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/freelinglogo.png"))); // NOI18N
         freelingButtonEs.setPreferredSize(new java.awt.Dimension(200, 50));
         freelingButtonEs.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 freelingButtonEsActionPerformed(evt);
             }
         });
         
         freelingButtonEn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/freelinglogo.png"))); // NOI18N
         freelingButtonEn.setPreferredSize(new java.awt.Dimension(200, 50));
         freelingButtonEn.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 freelingButtonEnActionPerformed(evt);
             }
         });

         chatButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
         chatButton.setText("Ir al chat");
         chatButton.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 try {
					chatButtonActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
             }
         });
         

         labelSt.setPreferredSize(new java.awt.Dimension(45, 30));
         labelSt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/ingles.png")));
         
         labelNLTK.setPreferredSize(new java.awt.Dimension(45, 30));
         labelNLTK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/ingles.png")));
         
         labelOP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/ingles.png")));
         
         labelFr1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/ingles.png")));
         labelFr2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/español.png")));

         
         
         javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
         getContentPane().setLayout(layout);
         layout.setHorizontalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                     .addGroup(layout.createSequentialGroup()
                         .addGap(34, 34, 34)
                         .addComponent(jLabel2))
                     .addGroup(layout.createSequentialGroup()
                         .addGap(317, 317, 317)
                         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                             .addComponent(NLTKbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addComponent(freelingButtonEn, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addComponent(freelingButtonEs, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                             .addComponent(labelNLTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addComponent(labelFr1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addComponent(labelFr2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                     .addGroup(layout.createSequentialGroup()
                         .addContainerGap()
                         .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(openNLPButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(labelOP, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGap(68, 68, 68)))
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                 .addComponent(chatButton)
                 .addGap(64, 64, 64))
             .addGroup(layout.createSequentialGroup()
                 .addGap(235, 235, 235)
                 .addComponent(stanfordButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                 .addComponent(labelSt, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
         );
         layout.setVerticalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addGap(42, 42, 42)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addGroup(layout.createSequentialGroup()
                         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                             .addGroup(layout.createSequentialGroup()
                                 .addComponent(jLabel2)
                                 .addGap(29, 29, 29)
                                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                     .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                     .addGroup(layout.createSequentialGroup()
                                         .addGap(43, 43, 43)
                                         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                             .addComponent(labelOP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                             .addComponent(openNLPButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                         .addGap(18, 18, 18)
                                         .addComponent(stanfordButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                     .addComponent(labelSt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                 .addGap(18, 18, 18)
                                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                     .addComponent(NLTKbutton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                     .addComponent(labelNLTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                 .addGap(18, 18, 18)
                                 .addComponent(freelingButtonEn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                             .addComponent(labelFr1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                         .addGap(18, 18, 18)
                         .addComponent(freelingButtonEs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                     .addComponent(labelFr2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                 .addGap(44, 44, 44)
                 .addComponent(chatButton)
                 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
         );

         pack();
     }// </editor-fold>                  

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
    
    private void freelingButtonEnActionPerformed(java.awt.event.ActionEvent evt) {                                         
        	Freeling freeling = new Freeling ("en");
        	Procesamiento procesamiento = new Procesamiento (freeling, "/data/freelinglogo.png", "Freeling (inglés)");
        	this.dispose();
    }                                        


    private void freelingButtonEsActionPerformed(java.awt.event.ActionEvent evt) {                                                 
    	Freeling freeling = new Freeling ("es");
    	Procesamiento procesamiento = new Procesamiento (freeling, "/data/freelinglogo.png", "Freeling (español)");
    	this.dispose();
    }
    
    private void chatButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {                                         
        // TODO add your handling code here:
		InterfazChatBot interfaz = new InterfazChatBot();
    }     
                  
    private javax.swing.JButton NLTKbutton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton openNLPButton;
    private javax.swing.JButton stanfordButton;
    private javax.swing.JButton freelingButtonEn;
    private javax.swing.JButton freelingButtonEs;
    private javax.swing.JButton chatButton;
    private javax.swing.JLabel labelFr1;
    private javax.swing.JLabel labelFr2;
    private javax.swing.JLabel labelNLTK;
    private javax.swing.JLabel labelOP;
    private javax.swing.JLabel labelSt;
                   
}
