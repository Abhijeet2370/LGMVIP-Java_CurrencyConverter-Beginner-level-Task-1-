package currencyConverter;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.*;
import java.awt.event.*;
import java.text.DecimalFormat;

class MainWindow extends JFrame {
	private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("Files.translation");
	private JPanel contentPane;
	private JTextField fieldAmount;
	private ArrayList<Currency> currencies = Currency.init();

	public MainWindow() {
		setTitle(BUNDLE.getString("MainWindow.this.title"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		setLocationRelativeTo(null);
		setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu(BUNDLE.getString("MainWindow.mnFile.text"));
		mnFile.setMnemonic(KeyEvent.VK_F);
		menuBar.add(mnFile);

		JMenuItem mntmQuit = new JMenuItem(BUNDLE.getString("MainWindow.mntmQuit.text"));
		mntmQuit.setMnemonic(KeyEvent.VK_Q);
		mntmQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmQuit);

		JMenu mnHelp = new JMenu(BUNDLE.getString("MainWindow.mnHelp.text"));
		mnHelp.setMnemonic(KeyEvent.VK_H);
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem(BUNDLE.getString("MainWindow.mntmAbout.text"));
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AboutWindow.getInstance().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		mntmAbout.setMnemonic(KeyEvent.VK_A);
		mnHelp.add(mntmAbout);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblConvert = new JLabel(BUNDLE.getString("MainWindow.lblConvert.text")); //$NON-NLS-1$
		lblConvert.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConvert.setBounds(20, 14, 90, 20);
		lblConvert.setFont(new Font("Noto Sans", Font.BOLD, 12));
		contentPane.add(lblConvert);

		final JComboBox<String> comboBoxCountry1 = new JComboBox<String>();
		comboBoxCountry1.setBounds(147, 7, 240, 28);
		populate(comboBoxCountry1, currencies);
		contentPane.add(comboBoxCountry1);

		JLabel lblTo = new JLabel(BUNDLE.getString("MainWindow.lblTo.text")); 
		lblTo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTo.setBounds(84, 54, 26, 15);
		lblTo.setFont(new Font("Noto Sans", Font.BOLD, 12));
		contentPane.add(lblTo);

		final JComboBox<String> comboBoxCountry2 = new JComboBox<String>();
		comboBoxCountry2.setBounds(147, 47, 240, 28);
		populate(comboBoxCountry2, currencies);
		contentPane.add(comboBoxCountry2);

		final JLabel lblAmount = new JLabel(BUNDLE.getString("MainWindow.lblAmount.text")); 
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(40, 108, 69, 15);
		lblAmount.setFont(new Font("Noto Sans", Font.BOLD, 12));
		contentPane.add(lblAmount);

		fieldAmount = new JTextField();
		fieldAmount.setText("0.00");
		fieldAmount.setBounds(147, 101, 240, 29);
		contentPane.add(fieldAmount);
		fieldAmount.setColumns(10);
		fieldAmount.setDocument(new JTextFieldLimit(8));

		final JLabel lblResult = new JLabel("");
		lblResult.setHorizontalAlignment(SwingConstants.LEFT);
		lblResult.setBounds(147, 188, 428, 38);
		contentPane.add(lblResult);

		JButton btnConvert = new JButton(BUNDLE.getString("MainWindow.btnConvert.text"));
		btnConvert.setBounds(147, 142, 240, 38);
		btnConvert.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String nameCurrency1 = comboBoxCountry1.getSelectedItem().toString();
				String nameCurrency2 = comboBoxCountry2.getSelectedItem().toString();
				String result;
				String formattedPrice;
				String formattedAmount;
				Double price;
				Double amount = 0.0;
				DecimalFormat format = new DecimalFormat("#0.00");

				try {
					amount = Double.parseDouble(fieldAmount.getText());
				} catch (NumberFormatException e) {
					e.printStackTrace();
					amount = 0.0;
				}

				price = convert(nameCurrency1, nameCurrency2, currencies, amount);

				formattedPrice = format.format(price);
				formattedAmount = format.format(amount);

				result = formattedAmount + " " + nameCurrency1 + " = " + formattedPrice + " " + nameCurrency2;
				lblResult.setText(result);
			}
		});
		contentPane.add(btnConvert);
	}

	public static void populate(JComboBox<String> comboBox, ArrayList<Currency> currencies) {
		for (Integer i = 0; i < currencies.size(); i++) {
			comboBox.addItem(currencies.get(i).getName());
		}
	}

	public static Double convert(String currency1, String currency2, ArrayList<Currency> currencies, Double amount) {
		String shortNameCurrency2 = null;
		Double exchangeValue;
		Double price = 0.0;

		for (Integer i = 0; i < currencies.size(); i++) {
			if (currencies.get(i).getName() == currency2) {
				shortNameCurrency2 = currencies.get(i).getShortName();
				break;
			}
		}

		if (shortNameCurrency2 != null) {
			for (Integer i = 0; i < currencies.size(); i++) {
				if (currencies.get(i).getName() == currency1) {
					exchangeValue = currencies.get(i).getExchangeValues().get(shortNameCurrency2);
					price = Currency.convert(amount, exchangeValue);
					break;
				}
			}
		}

		return price;
	}
}

class JTextFieldLimit extends PlainDocument {
	private int limit;
	private boolean toUppercase = false;

	JTextFieldLimit(int limit) {
		super();
		this.limit = limit;
	}

	JTextFieldLimit(int limit, boolean upper) {
		super();
		this.limit = limit;
		toUppercase = upper;
	}

	public void insertString(int offset, String str, AttributeSet attr)
			throws BadLocationException {
		if (str == null)
			return;

		if ((getLength() + str.length()) <= limit) {
			if (toUppercase)
				str = str.toUpperCase();
			super.insertString(offset, str, attr);
		}
	}
}

class Currency {
	private String name;
	private String shortName;
	private HashMap<String, Double> exchangeValues = new HashMap<String, Double>();

	public Currency(String nameValue, String shortNameValue) {
		this.name = nameValue;
		this.shortName = shortNameValue;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public HashMap<String, Double> getExchangeValues() {
		return this.exchangeValues;
	}

	public void setExchangeValues(String key, Double value) {
		this.exchangeValues.put(key, value);
	}

	public void defaultValues() {
		String currency = this.name;

		switch (currency) {
			case "Indian Rupee":
				this.exchangeValues.put("INR", 1.00);
				this.exchangeValues.put("USD", 0.012);
				this.exchangeValues.put("KWD", 0.0038);
				this.exchangeValues.put("EGP", 0.36);
				this.exchangeValues.put("CNY", 0.083);
				this.exchangeValues.put("EUR", 0.011);
				break;

			case "US Dollar":
				this.exchangeValues.put("INR", 81.29);
				this.exchangeValues.put("USD", 1.00);
				this.exchangeValues.put("KWD", 0.31);
				this.exchangeValues.put("EGP", 29.50);
				this.exchangeValues.put("CNY", 6.70);
				this.exchangeValues.put("EUR", 0.92);
				break;

			case "Kuwaiti Dinar":
				this.exchangeValues.put("INR", 267.35);
				this.exchangeValues.put("USD", 3.29);
				this.exchangeValues.put("KWD", 1.00);
				this.exchangeValues.put("EGP", 97.03);
				this.exchangeValues.put("CNY", 22.05);
				this.exchangeValues.put("EUR", 3.03);
				break;

			case "Egyptian Pound":
				this.exchangeValues.put("INR", 2.76);
				this.exchangeValues.put("USD", 0.034);
				this.exchangeValues.put("KWD", 0.010);
				this.exchangeValues.put("EGP", 1.00);
				this.exchangeValues.put("CNY", 0.23);
				this.exchangeValues.put("EUR", 0.031);
				break;

			case "Chinese Yuan Renminbi":
				this.exchangeValues.put("INR", 12.09);
				this.exchangeValues.put("USD", 0.15);
				this.exchangeValues.put("KWD", 0.046);
				this.exchangeValues.put("EGP", 4.40);
				this.exchangeValues.put("CNY", 1.00);
				this.exchangeValues.put("EUR", 0.14);
				break;

			case "EURO":
				this.exchangeValues.put("INR", 88.14);
				this.exchangeValues.put("USD", 1.08);
				this.exchangeValues.put("KWD", 0.33);
				this.exchangeValues.put("EGP", 31.99);
				this.exchangeValues.put("CNY", 7.27);
				this.exchangeValues.put("EUR", 1.00);
				break;
		}
	}

	public static ArrayList<Currency> init() {
		ArrayList<Currency> currencies = new ArrayList<Currency>();

		currencies.add(new Currency("Indian Rupee", "INR"));
		currencies.add(new Currency("US Dollar", "USD"));
		currencies.add(new Currency("Kuwaiti Dinar", "KWD"));
		currencies.add(new Currency("Egyptian Pound", "EGP"));
		currencies.add(new Currency("Chinese Yuan Renminbi", "CNY"));
		currencies.add(new Currency("Euro", "EUR"));

		for (Integer i = 0; i < currencies.size(); i++) {
			currencies.get(i).defaultValues();
		}

		return currencies;
	}

	public static Double convert(Double amount, Double exchangeValue) {
		Double price;
		price = amount * exchangeValue;
		price = Math.round(price * 100d) / 100d;

		return price;
	}
}

class AboutWindow extends JFrame {
	private JPanel contentPane;
	private static AboutWindow windowInstance = null;

	private AboutWindow() {
		setBounds(100, 100, 347, 260);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("Currency Converter");
		lblTitle.setBounds(65, 50, 219, 33);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Noto Sans", Font.BOLD, 20));
		contentPane.add(lblTitle);

		JLabel lblcreater = new JLabel("By Abhijeet Rathore");
		lblcreater.setBounds(65, 77, 219, 33);
		lblcreater.setHorizontalAlignment(SwingConstants.CENTER);
		lblcreater.setFont(new Font(" ", Font.BOLD, 20));
		contentPane.add(lblcreater);

		JLabel lblcomp = new JLabel("Let's Grow More");
		lblcomp.setHorizontalAlignment(SwingConstants.CENTER);
		lblcomp.setBounds(1, 1, 150, 33);
		lblcomp.setFont(new Font(" ", Font.BOLD, 15));
		contentPane.add(lblcomp);

		JLabel lblgreet = new JLabel("--- Thank You ---");
		lblgreet.setHorizontalAlignment(SwingConstants.CENTER);
		lblgreet.setBounds(100, 125, 150, 33);
		lblgreet.setFont(new Font(" ", Font.BOLD, 15));
		contentPane.add(lblgreet);

	}

	public static AboutWindow getInstance() {
		if (windowInstance == null) {
			windowInstance = new AboutWindow();
		}
		return windowInstance;
	}
}

public class CurrencyConverter {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow mainWindow = new MainWindow();
					mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
