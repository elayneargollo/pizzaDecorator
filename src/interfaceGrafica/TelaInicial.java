package interfaceGrafica;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import main.Principal;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String MSG_ERRO_REMOVE = "There are no items to remove";
	private static final String MSG_ERRO = "There is no way to move this item";

	private Principal leading;
	private JPanel panel, contentPane;
	private JButton btnAddList, btnRemoveList, btnAddListAll, btnRemoveListAll, btnUp, btnDrop, btnSend;

	public TelaInicial() {
		this.leading = new Principal();

		setElements();
		initComponent();
	}

	private void setElements() {
		
		URL path = null;
		
		 try {
			path = new URL("https://image.freepik.com/fotos-gratis/cortar-pizza-no-preto_23-2147749510.jpg");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		this.panel = new CustomizacaoPainel(path);
		this.btnAddList = new JButton(">");
		this.btnRemoveList = new JButton("<");
		this.btnAddListAll = new JButton(">>");
		this.btnRemoveListAll = new JButton("<<");
		this.btnUp = new JButton("Up");
		this.btnDrop = new JButton("Drop");
		this.btnSend = new JButton("Prepare");

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initComponent() {

		DefaultListModel<String> model = new DefaultListModel<String>();

		JList<String> listChosenFlavors = new JList<String>();
		listChosenFlavors.setBounds(238, 11, 132, 298);
		panel.add(listChosenFlavors);

		String filling[] = leading.getSabores();
		ArrayList<String> selectedItens = new ArrayList<String>();

		JList AvailableDlavorsList = new JList(filling);
		AvailableDlavorsList.setBounds(28, 11, 132, 298);
		panel.add(AvailableDlavorsList);

		listChosenFlavors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		AvailableDlavorsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		AvailableDlavorsList.setSelectedIndex(0);

		settingWindow();
		settingButton();
		actionButton(AvailableDlavorsList, model, listChosenFlavors, selectedItens, filling);
		submit(selectedItens);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void settingWindow() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Pizzaria Decorator");
		setBounds(100, 100, 513, 380);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.setOpaque(false);
		
		this.setIconImage(new ImageIcon(getClass().getResource("/imagens/pizza.jpg")).getImage());
	
	}
	
	private void settingButton() {
		btnAddList.setBounds(179, 11, 49, 23);
		panel.add(btnAddList);

		btnRemoveList.setBounds(179, 45, 49, 23);
		panel.add(btnRemoveList);

		btnAddListAll.setBounds(179, 105, 49, 23);
		panel.add(btnAddListAll);

		btnRemoveListAll.setBounds(179, 139, 49, 23);
		panel.add(btnRemoveListAll);

		btnUp.setBounds(388, 11, 89, 23);
		panel.add(btnUp);

		btnDrop.setBounds(388, 45, 89, 23);
		panel.add(btnDrop);

		btnSend.setBounds(388, 297, 89, 23);
		panel.add(btnSend);
	}

	private void actionButton(JList<?> AvailableDlavorsList, DefaultListModel<String> model,
			JList<String> listChosenFlavors, ArrayList<String> chosenFilling, String stuffingPizza[]) {
		btnAddList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String content = (String) (AvailableDlavorsList.getSelectedValue());

				chosenFilling.add(content);
				model.addElement(content);
				listChosenFlavors.setModel(model);
				listChosenFlavors.setSelectedIndex(0);

			}
		});

		btnRemoveList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int listSize = model.getSize();

				if (listSize > 0) {
					int index = listChosenFlavors.getSelectedIndex();

					model.remove(index);
					chosenFilling.remove(index);
					listChosenFlavors.setSelectedIndex(0);

				} else {
					JOptionPane.showMessageDialog(null, MSG_ERRO_REMOVE);
				}

			}
		});

		btnAddListAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < stuffingPizza.length; i++) {

					model.addElement(stuffingPizza[i]);
					listChosenFlavors.setModel(model);
					chosenFilling.add(stuffingPizza[i]);
				}

			}
		});

		btnRemoveListAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				model.clear();
				listChosenFlavors.setModel(model);
				chosenFilling.clear();
			}
		});

		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int listSize = model.getSize();

				if (listSize > 0) {

					int selection = listChosenFlavors.getSelectedIndex();

					if (selection != 0) {

						model.add(selection - 1, model.get(selection));
						model.remove(selection + 1);
						listChosenFlavors.setSelectedIndex(selection - 1);

					} else {
						JOptionPane.showMessageDialog(null, MSG_ERRO);
					}

				} else {
					JOptionPane.showMessageDialog(null, MSG_ERRO_REMOVE);
				}

			}
		});

		btnDrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int listSize = model.getSize();

				if (listSize > 0) {
					int selection = listChosenFlavors.getSelectedIndex();

					if (selection != listSize - 1) {

						model.add(selection + 2, model.get(selection));
						model.remove(selection);
						listChosenFlavors.setSelectedIndex(selection + 1);

					} else {
						JOptionPane.showMessageDialog(null, MSG_ERRO);
					}

				} else {
					JOptionPane.showMessageDialog(null, MSG_ERRO_REMOVE);
				}

			}
		});
	}

	private void submit(ArrayList<String> selectedItens) {

		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leading.main(selectedItens);
			}
		});

	}
}
