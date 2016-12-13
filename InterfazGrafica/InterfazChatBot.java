package InterfazGrafica;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Herramientas.ChatBot;
import Herramientas.HerramientaAbs;
import Herramientas.NLTK;
import Herramientas.StCoreNLP;

public class InterfazChatBot extends javax.swing.JFrame {

	static ChatBot chatBot;
	boolean inicializo = false;
	HerramientaAbs herramienta;
	String msj_usuario_id = null;
	String msj_usuario_txt = null;
	HashMap<String,ArrayList<String>> hash;
	
	public InterfazChatBot() throws IOException{

		this.chatBot = new ChatBot("key",this);
		this.herramienta = new StCoreNLP();
		hash = new HashMap<>();
		
		initComponents();
		
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazChatBot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazChatBot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazChatBot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazChatBot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
	}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        oAuth_TF = new javax.swing.JTextField();
        oAuth_reset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chat_TA = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        login_button = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        output_TA = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        comando_textfield = new javax.swing.JTextField();
        button_ejecutarComando = new javax.swing.JButton();
        mensaje_TF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        enviar_button = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        oAuth_TF.setText("oAuth");
        oAuth_TF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oAuth_TFActionPerformed(evt);
            }
        });

        oAuth_reset.setText("Reset oAuth");
        oAuth_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					oAuth_resetActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        chat_TA.setColumns(20);
        chat_TA.setRows(5);
        chat_TA.setEditable(false);
        jScrollPane1.setViewportView(chat_TA);

        jLabel1.setText("Chat");

        login_button.setText("Login");
        login_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					login_buttonActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        output_TA.setColumns(20);
        output_TA.setRows(5);
        output_TA.setEditable(false);
        jScrollPane2.setViewportView(output_TA);

        jLabel2.setText("Output");

        comando_textfield.setText("Comando");
        comando_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comando_textfieldActionPerformed(evt);
            }
        });
        

        button_ejecutarComando.setText("Ejecutar");
        button_ejecutarComando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	ArrayList<String> keys = new ArrayList<>(hash.keySet());

        		output_TA.append("////////////////////////\n\n\n\n");
        		output_TA.append("Resultados del analisis sentimental: ");
        		
            	for(String key : keys){
            		
            		float promedio = 0; 
            		ArrayList<String> values = new ArrayList<>(hash.get(key));
            		
            		for(String value : values){
            				if(value.contains("Positive")){
            					promedio++;
            				}else
            					if(value.contains("Negative")){
            						promedio--;
            					}
            		}
            		System.out.println("Resultado final: " +(promedio/values.size()));

            		output_TA.append(key+": "+(promedio/values.size())+"\n");
            	}
            	/*try {
            		//Abro el log que contiene toda la informacion	
					List<String> lista = Files.readAllLines(Paths.get(ChatBot.appData_Dir +"\\hangupsbot.log"));
					
					//En la hash se guarda cada uno de las lineas que se lee de cada usuario y luego se saca el promedio
					hash = new HashMap<>();
					
					for(String linea : lista){
						if(linea.contains("event: c/g/un")){
				    		obtenerUsuarioLog(linea);
				    	}
				    	else 
				    		if(linea.contains("event: len/tx")){
				    			msj_usuario_txt = linea.substring(42);
				    			System.out.println(msj_usuario_txt);
				    			int valor = Integer.parseInt(herramienta.sentiment_analysis(msj_usuario_txt));
				    			System.out.println("Usuario: " +msj_usuario_id +"\nMensaje: " +msj_usuario_txt +"\nValor" +valor);
				    			agregarYcalcular(valor);
				    		}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                */
            }
        });
        
        mensaje_TF.setText("Mensaje");

        enviar_button.setText("Enviar");
        enviar_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					chatBot.escribirConsola(mensaje_TF.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

//        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mensaje_TF, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(enviar_button)))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button_ejecutarComando))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(login_button)
                                .addGap(136, 136, 136)
                                .addComponent(oAuth_reset))
                            .addComponent(oAuth_TF, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(comando_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(oAuth_TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(comando_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(login_button)
                            .addComponent(oAuth_reset))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)))
                    .addComponent(button_ejecutarComando))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mensaje_TF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(enviar_button))))
                .addContainerGap())
        );

        pack();
        this.setVisible(true);
    }// </editor-fold>                        

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {                                             
        System.exit(0);
    }                                            

    private void oAuth_TFActionPerformed(java.awt.event.ActionEvent evt) {                                         
    }                                        

    private void oAuth_resetActionPerformed(java.awt.event.ActionEvent evt) throws IOException {   
    	if(chatBot != null){
    		//TODO: Frenar el bot.
    		chatBot.eliminarConfig();
    	}
    }                                           

    private void login_buttonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {                                             
//    	if(chatBot.cfgExistente()){
    		//TODO: Deslogear el 
    		chatBot.start();
//    		chatBot = new ChatBot(login_button.getText(),this);
//    	}
    }                                            

    private void comando_textfieldActionPerformed(java.awt.event.ActionEvent evt) {                                                  
    }                                                 

    public void addTexto_Output(String texto){
    	output_TA.append(texto+"\n");
    	
    	//Si se envia un msj., primero se obtiene el usuario
    	if(texto.contains("event: c/g/un")){
    		obtenerUsuario(texto);
    	}
    	else 
    		if(texto.contains("event: len/tx")){
    			msj_usuario_txt = texto.substring(31);
    			chat_TA.append(msj_usuario_id +": " +msj_usuario_txt + "\n");
    			
    			herramienta.tokenizar(texto);
    			String valor = herramienta.sentiment_analysis(msj_usuario_txt);
    			agregarYcalcular(valor);
    			chat_TA.append(String.valueOf(valor));
			}
    }
    
    public void obtenerUsuario(String texto){
    	msj_usuario_id = texto.substring(29);
    	int barras=0;
    	
    	for(int i = 0; i< msj_usuario_id.length(); i++){
    		if(msj_usuario_id.charAt(i)=='/'){
    			barras++;
    			if(barras == 2){	//El nombre de usuario se encuentra despues de la segunda barra del mensaje
    				msj_usuario_id = msj_usuario_id.substring(i+1);
    			}
    		}
    	}
    }
    
    public void agregarYcalcular(String valor){
    	System.out.println(valor);
    	if(hash.containsKey(msj_usuario_id)){
    		ArrayList<String> valores = (ArrayList<String>)hash.get(msj_usuario_id);
    		valores.add(valor);
    	}else{
    		ArrayList<String> valores = new ArrayList<String>();
    		valores.add(valor);
    		hash.put(msj_usuario_id, valores);
    	}
    }
    
    // Variables declaration - do not modify                     
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton button_ejecutarComando;
    private javax.swing.JTextArea chat_TA;
    private javax.swing.JTextField comando_textfield;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JButton enviar_button;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton login_button;
    private javax.swing.JTextField mensaje_TF;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JTextField oAuth_TF;
    private javax.swing.JButton oAuth_reset;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JTextArea output_TA;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration                   

}
