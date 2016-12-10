package InterfazGrafica;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Herramientas.HerramientaAbs;

public class Procesamiento extends javax.swing.JFrame {

    static HerramientaAbs herramienta;
    static String path;
    static String title;
	
    public Procesamiento(HerramientaAbs herramienta, String path, String title) {
    	Procesamiento.herramienta=herramienta;
        Procesamiento.path = path;
        Procesamiento.title = title;
    	initComponents();
    	this.getContentPane().setBackground(Color.white);
        this.setVisible(true);
    }
                         
    private void initComponents() {

    	 optMetodos = new javax.swing.JComboBox<>();
         aplicar = new javax.swing.JButton();
         jScrollPane1 = new javax.swing.JScrollPane();
         TextIn = new javax.swing.JTextArea();
         jScrollPane2 = new javax.swing.JScrollPane();
         TextOut = new javax.swing.JTextArea();
         jLabel1 = new javax.swing.JLabel();
         jLabel2 = new javax.swing.JLabel();
         jLabel3 = new javax.swing.JLabel();
         jLabel4 = new javax.swing.JLabel();
         backButton = new javax.swing.JButton();
         jLabel5 = new javax.swing.JLabel();
         jScrollPane3 = new javax.swing.JScrollPane();
         jScrollPane4 = new javax.swing.JScrollPane();
         Referencias = new javax.swing.JTextArea();

         setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
         setTitle(title);
         setBackground(new java.awt.Color(255, 255, 255));
         setResizable(false);

         optMetodos.setEditable(true);
         optMetodos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
         optMetodos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar metodo", "Tokenize", "Chunk", "Analisis gramatical", "Identificar lenguaje", "Reconocer nombre entidades", "Etiquetado gramatical", "Detectar sentencias", "Analisis sentimental", "Coreferencia" }));
         optMetodos.addItemListener(new java.awt.event.ItemListener() {
             public void itemStateChanged(java.awt.event.ItemEvent evt) {
                 optMetodosItemStateChanged(evt);
             }
         });

         aplicar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
         aplicar.setText("Aplicar");
         aplicar.setEnabled(false);
         aplicar.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 
					try {
						aplicarActionPerformed(evt);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
             }
         });

         TextIn.setColumns(20);
         TextIn.setRows(5);
         TextIn.addKeyListener(new java.awt.event.KeyAdapter() {
             public void keyReleased(java.awt.event.KeyEvent evt) {
                 TextInKeyReleased(evt);
             }
         });
         jScrollPane1.setViewportView(TextIn);
         
         Referencias.setEditable(false);
         Referencias.setColumns(20);
         Referencias.setRows(5);
         jScrollPane4.setViewportView(Referencias);

         jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
         jLabel5.setText("Referencias");
         
         TextOut.setEditable(false);
         TextOut.setColumns(20);
         TextOut.setRows(5);
         jScrollPane3.setViewportView(TextOut);

         jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
         jLabel1.setText("Ingresar texto...");

         jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
         jLabel2.setText("Resultado");
         
         jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource(Procesamiento.path)));

         jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data/HPLN_300.png"))); // NOI18N
         
         
         backButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
         backButton.setText("Volver a inicio");
         backButton.addActionListener(new java.awt.event.ActionListener() {
             public void actionPerformed(java.awt.event.ActionEvent evt) {
                 backButtonActionPerformed(evt);
             }
         });

         javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
         getContentPane().setLayout(layout);
         layout.setHorizontalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addGroup(layout.createSequentialGroup()
                         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                             .addGroup(layout.createSequentialGroup()
                                 .addGap(62, 62, 62)
                                 .addComponent(jLabel3))
                             .addGroup(layout.createSequentialGroup()
                                 .addContainerGap()
                                 .addComponent(optMetodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(aplicar))
                     .addGroup(layout.createSequentialGroup()
                         .addContainerGap()
                         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                             .addComponent(jLabel5)
                             .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addComponent(backButton))))
                 .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                     .addComponent(jLabel2)
                     .addComponent(jLabel1)
                     .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
                     .addComponent(jScrollPane3))
                 .addContainerGap())
             .addGroup(layout.createSequentialGroup()
                 .addContainerGap()
                 .addComponent(jLabel4)
                 .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
         );
         layout.setVerticalGroup(
             layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
             .addGroup(layout.createSequentialGroup()
                 .addContainerGap()
                 .addComponent(jLabel4)
                 .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addGroup(layout.createSequentialGroup()
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(jLabel1)
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                         .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGap(30, 30, 30)
                         .addComponent(jLabel2)
                         .addGap(18, 18, 18)
                         .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addGap(25, 25, 25))
                     .addGroup(layout.createSequentialGroup()
                         .addGap(39, 39, 39)
                         .addComponent(jLabel3)
                         .addGap(28, 28, 28)
                         .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                             .addComponent(optMetodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                             .addComponent(aplicar))
                         .addGap(18, 18, 18)
                         .addComponent(jLabel5)
                         .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                         .addComponent(jScrollPane4)
                         .addGap(23, 23, 23)
                         .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addContainerGap())))
         );

         pack();
    }                       
                                       

    
    private void optMetodosItemStateChanged(java.awt.event.ItemEvent evt) {                                            
        if (optMetodos.getSelectedItem().toString() == "Seleccionar metodo" || TextIn.getText().isEmpty())
        	aplicar.setEnabled(false);
        else
        	aplicar.setEnabled(true);
    }                                           

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
		       
    			InterfazUsuario ui = new InterfazUsuario();
		        this.dispose();
    }  
    
    
    private void addReferencias (String path) throws IOException{
		    	Referencias.setText("");
    			String cadena;
		        FileReader f = new FileReader(path);
		        BufferedReader b = new BufferedReader(f);
		        while((cadena = b.readLine())!=null) {
		            Referencias.append(cadena + "\n");
		        }
		        b.close();
    }
    
    
    private void aplicarActionPerformed(java.awt.event.ActionEvent evt) throws IOException{                                        
				    	
    	if (optMetodos.getSelectedItem().toString() != "Seleccionar metodo" || !TextIn.getText().isEmpty()){
    	
    			switch (optMetodos.getSelectedItem().toString()) {
				    	 
				        case "Tokenize":
				        	TextOut.setText(herramienta.tokenizar(TextIn.getText()));
				        break;
				        case "Chunk":
				        	String s =herramienta.chunk(TextIn.getText());
				        	if (s!="LA HERRAMIENTA NO SOPORTA LA ACCION")
				        		this.addReferencias("ReferenciasChunk.txt");
				        	TextOut.setText(s);
				        	
				        break;
				 
				        case "Analisis gramatical":
				        	TextOut.setText(herramienta.parse(TextIn.getText()));
				        break;
				        case "Identificar lenguaje":
				        	TextOut.setText(herramienta.lang_ident(TextIn.getText()));
				        break;
				        
				        case "Reconocer nombre entidades":
				        	TextOut.setText(herramienta.name_entity_recognizer(TextIn.getText()));
				        break;
				       
				        case "Etiquetado gramatical":
				        		TextOut.setText(herramienta.etiquetado_gramatical(TextIn.getText()));
				        		if (title == "Freeling" && herramienta.lang_ident(TextIn.getText())=="Español"){
				        				this.addReferencias("Referencias-pos-freeling-es.txt");
				        		}
				        		else {
				        			this.addReferencias("Referencias-pos-en.txt");
				        		}
				        break;
				        
				        case "Detectar sentencias":
				        	TextOut.setText(herramienta.sentence_detect(TextIn.getText()));
				        break;
				        
				        case "Analisis sentimental":
				        	TextOut.setText(herramienta.sentiment_analysis(TextIn.getText()));
				        break;
				        
				        case "Coreferencia":
				        	TextOut.setText(herramienta.coreference(TextIn.getText()));
				        break;
			
				 
				 }
    			
    	}
    }                                       


    private void TextInKeyReleased(java.awt.event.KeyEvent evt) {                                   
        if(TextIn.getText().isEmpty() || optMetodos.getSelectedItem().toString() == "Seleccionar metodo") 
            aplicar.setEnabled(false); 
        else 
            aplicar.setEnabled(true);
    }                                  

                    
    private javax.swing.JTextArea Referencias;
    private javax.swing.JTextArea TextIn;
    private javax.swing.JButton aplicar;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea TextOut;
    private javax.swing.JComboBox<String> optMetodos;
                      
}
