package m226;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;

public class gui {

	protected Shell shell;
	private Text txtTitel;
	private Text text_2;
	private Text text_3;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			gui window = new gui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window. 
	 */
	
	
	protected void createContents() {
		shell = new Shell();
		shell.setSize(1000, 700);
		shell.setText("SWT Application");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setText("Neues Buch");
		lblNewLabel.setFont(SWTResourceManager.getFont("Corbel Light", 24, SWT.NORMAL));
		lblNewLabel.setBounds(21, 22, 450, 60);
		
		Label lblNeuerAutor = new Label(shell, SWT.NONE);
		lblNeuerAutor.setText("Neuer Autor");
		lblNeuerAutor.setFont(SWTResourceManager.getFont("Corbel Light", 24, SWT.NORMAL));
		lblNeuerAutor.setBounds(494, 22, 462, 60);
		
		txtTitel = new Text(shell, SWT.BORDER);
		txtTitel.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		txtTitel.setBounds(21, 104, 450, 28);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		text_2.setBounds(494, 104, 462, 28);
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		text_3.setBounds(494, 176, 462, 28);
		
		Label lblTitel = new Label(shell, SWT.NONE);
		lblTitel.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		lblTitel.setBounds(21, 78, 70, 20);
		lblTitel.setText("Titel");
		
		Label lblAutor = new Label(shell, SWT.NONE);
		lblAutor.setText("Autor");
		lblAutor.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		lblAutor.setBounds(21, 150, 70, 20);
		
		Label lblTitel_1_1 = new Label(shell, SWT.NONE);
		lblTitel_1_1.setText("Nachname");
		lblTitel_1_1.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		lblTitel_1_1.setBounds(494, 150, 70, 20);
		
		Label lblTitel_1_1_1 = new Label(shell, SWT.NONE);
		lblTitel_1_1_1.setText("Vorname");
		lblTitel_1_1_1.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		lblTitel_1_1_1.setBounds(494, 78, 70, 20);
		
		Label lblAnzahlSeiten = new Label(shell, SWT.NONE);
		lblAnzahlSeiten.setText("Anzahl Seiten");
		lblAnzahlSeiten.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		lblAnzahlSeiten.setBounds(21, 221, 150, 20);
		
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		spinner.setBounds(21, 247, 450, 28);
		
		Combo combo = new Combo(shell, SWT.READ_ONLY);
		combo.setBounds(21, 176, 450, 28);

	}
}
