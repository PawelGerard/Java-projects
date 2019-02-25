package Graphics;

import java.awt.Dimension;

import javax.swing.BorderFactory;

import Main.SQL;

public class ViewP extends ActionP{
	
	SQL sql = new SQL();

	public ViewP() {
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(lNameTitle);
		tSearchBR .setPreferredSize(new Dimension(100,25));
		add(tSearchBR );
		add(bSearchBR);
		tableBR.setPreferredSize(new Dimension(620, 500));
		scrollBR.setPreferredSize(new Dimension(620, 350));
		add(scrollBR);
		setValuesInTableBR(dmBR, sql.selectBorrows(""));
		sql.closeConnection();
	}
}
