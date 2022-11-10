package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.Usuario;
import dao.douUsuario;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class vUsuario extends JFrame {
	douUsuario dou=new douUsuario();
	int fila= -1;
	DefaultTableModel modelo=new DefaultTableModel();
	ArrayList<Usuario>lista=new ArrayList<Usuario>();
	private JTable tblUsuario;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vUsuario frame = new vUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public vUsuario() {
		setTitle("CRUD USUARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 499);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(10, 32, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblID = new JLabel("1");
		lblID.setBounds(127, 32, 46, 14);
		contentPane.add(lblID);
		
		JLabel lblNewLabel_1 = new JLabel("USUARIO");
		lblNewLabel_1.setBounds(10, 60, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		JTextField txtUser = new JTextField();
		txtUser.setBounds(120, 57, 86, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10); 
		
		JLabel lblNewLabel_1_1 = new JLabel("PASSWORD");
		lblNewLabel_1_1.setBounds(10, 99, 79, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JTextField txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(120, 96, 86, 20);
		contentPane.add(txtPassword);
		
		JTextField txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(120, 139, 86, 20);
		contentPane.add(txtNombre);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("NOMBRE");
		lblNewLabel_1_1_1.setBounds(10, 142, 67, 14);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Usuario user=new Usuario();
					user.setUser(txtUser.getText());
					user.setPassword(txtPassword.getText());
					user.setNombre(txtNombre.getText());
					if(dou.insertarUsuario(user)) {
						actualizarTabla();
						JOptionPane.showMessageDialog(null, "SE AGREGO CORRERTAMENTE");
					}else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				}catch(Exception ex ) {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}
		});
		btnAgregar.setBounds(0, 188, 89, 23);
		contentPane.add(btnAgregar);
		
		JButton btnEditar = new JButton("EDITAR");
		btnEditar.setBounds(110, 188, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion = JOptionPane.showConfirmDialog(null, "ESTAS SEGURO DE ELIMINAR ESTE USUARIO?");
					if(opcion ==0) {
						
					}
				if(dou.eliminarUsuario(lista.get(fila).getId())) {
					actualizarTabla();
					JOptionPane.showMessageDialog(null, "ELIMNAR CORRECTAMENTE!!");
				}else {
					JOptionPane.showMessageDialog(null, "ERROR");
				}
			}catch(Exception ex ) {
				JOptionPane.showMessageDialog(null, "ERROR");
			}
			}
		});
		btnEliminar.setBounds(213, 188, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.setBounds(335, 188, 89, 23);
		contentPane.add(btnBorrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 222, 383, 227);
		contentPane.add(scrollPane);
		
		tblUsuario = new JTable();
		tblUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblUsuario.getSelectedRow();
				lblID.setText(""+lista.get(fila).getId());
			}
		});
		tblUsuario.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblUsuario);

	modelo.addColumn("ID");
	modelo.addColumn("USER");
	modelo.addColumn("PASSWORD");
	modelo.addColumn("NOMBRE");
	tblUsuario.setModel(modelo);
	actualizarTabla();
	}
	public void actualizarTabla () {
		while(modelo.getRowCount()>0) {
			modelo.removeRow(0);
		}
		lista=dou.fetchUsusarios();
		for(Usuario u: lista) {
			Object o[]=new Object[4];
			o[0]=u.getId();
			o[1]=u.getUser();
			o[2]=u.getPassword();
			o[3]=u.getNombre();
			modelo.addRow(o);
		}
		tblUsuario.setModel(modelo);
	}
}
