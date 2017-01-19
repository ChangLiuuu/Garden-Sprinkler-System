package garden.sprinkler.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;

import garden.common.Utils;
import garden.model.Sprinkler;
import garden.model.SprinklerBank;

public class DetailsView extends JPanel {
	private String[] tableHead = {"ID", "Group", "status"};
	private Object[][] data = new Object[10][3];

	private JTable table;
	public DetailsView(SprinklerBank spkBank) {
		//initiate data.
		int i = 0;
		
		List<Sprinkler> sprinklers = sortCollections(spkBank.getAllSprinklers());
		for(Sprinkler sprinkler : sprinklers){
			data[i][0] = sprinkler.getID();
			data[i][1] = sprinkler.getGroup();
			data[i][2] = changingTo(sprinkler.getStatus());
			i++;
		}
		
		table = new JTable(data, tableHead);
		setTableStyle();
		
		setLayout(new BorderLayout());
		add(table.getTableHeader(), BorderLayout.NORTH);
		add(table, BorderLayout.CENTER);
	}
	
	private List<Sprinkler> sortCollections(Collection<Sprinkler> sprinklers) {
		List<Sprinkler> ans = new ArrayList<Sprinkler>();
		for (Sprinkler spk : sprinklers) {
			ans.add(spk);
		}
		Collections.sort(ans);
		return ans;
	}
	
	private String changingTo(boolean status) {
		if (!status) return "off";
		else return "on";
	}
	
	private void setTableStyle(){
		table.setFont(Utils.getFormatFont(Font.BOLD, 15));
		table.getTableHeader().setFont(Utils.getFormatFont(Font.BOLD, 18));
	}
}
