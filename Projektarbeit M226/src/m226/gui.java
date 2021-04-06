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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class gui {

	protected Shell shell;
	private Text title;
	private Text firstname;
	private Text lastname;
	private String[] items = {"scheisse", "hund", "kachel"};

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
		shell.setSize(1000, 423);
		shell.setText("SWT Application");
		
		//BUCH HINZUFÜGEN
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setText("Neues Buch");
		lblNewLabel.setFont(SWTResourceManager.getFont("Corbel Light", 24, SWT.NORMAL));
		lblNewLabel.setBounds(21, 22, 450, 60);
		
		//TITEL
		
		Label lblTitel = new Label(shell, SWT.NONE);
		lblTitel.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		lblTitel.setBounds(21, 78, 70, 20);
		lblTitel.setText("Titel");
		
		title = new Text(shell, SWT.BORDER);
		title.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		title.setBounds(21, 104, 450, 28);
		
		//AUTOR
		
		Label lblNeuerAutor = new Label(shell, SWT.NONE);
		lblNeuerAutor.setText("Neuer Autor");
		lblNeuerAutor.setFont(SWTResourceManager.getFont("Corbel Light", 24, SWT.NORMAL));
		lblNeuerAutor.setBounds(494, 22, 462, 60);
		
		Combo author = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		author.setBounds(21, 176, 450, 28);
		author.setItems(items);
		author.select(0);
		
		//SEITEN
		
		Label lblAnzahlSeiten = new Label(shell, SWT.NONE);
		lblAnzahlSeiten.setText("Anzahl Seiten");
		lblAnzahlSeiten.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		lblAnzahlSeiten.setBounds(21, 221, 150, 20);
		
		Spinner pages = new Spinner(shell, SWT.BORDER);
		pages.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		pages.setBounds(21, 247, 450, 28);
		
		//BUCH KNOPF
		
		Button btnBuch = new Button(shell, SWT.NONE);
		btnBuch.setBounds(21, 312, 150, 41);
		btnBuch.setText("Buch hinzuf\u00FCgen");
		
		
		
		
		//AUTOR HINZUGÜGEN
		
		Label lblAutor = new Label(shell, SWT.NONE);
		lblAutor.setText("Autor");
		lblAutor.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		lblAutor.setBounds(21, 150, 70, 20);
		
		//VORNAME
		
		Label lblVorname = new Label(shell, SWT.NONE);
		lblVorname.setText("Vorname");
		lblVorname.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		lblVorname.setBounds(494, 78, 70, 20);
		
		firstname = new Text(shell, SWT.BORDER);
		firstname.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		firstname.setBounds(494, 104, 462, 28);
		
		//NACHNAME
		
		Label lblNachname = new Label(shell, SWT.NONE);
		lblNachname.setText("Nachname");
		lblNachname.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		lblNachname.setBounds(494, 150, 70, 20);
		
		lastname = new Text(shell, SWT.BORDER);
		lastname.setFont(SWTResourceManager.getFont("Corbel Light", 9, SWT.NORMAL));
		lastname.setBounds(494, 176, 462, 28);
		
		//AUTOR KNOPF
		
		Button btnAutor = new Button(shell, SWT.NONE);
		btnAutor.setBounds(494, 312, 150, 41);
		btnAutor.setText("Autor hinzuf\u00FCgen");
		

	}
}
