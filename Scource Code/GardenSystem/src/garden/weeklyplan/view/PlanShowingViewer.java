package garden.weeklyplan.view;


import java.awt.BorderLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;

import garden.common.Utils;
import garden.model.WeeklyPlan;

public class PlanShowingViewer extends JPanel {
	private String[] head = {"Group", "Monday", "Tuesday", "Wednesday", "Thursday", 
			"Friday", "Saturday", "Sunday"};
	private JTable table;
	
	public PlanShowingViewer() {
		setLayout(new BorderLayout());
		
	}
	
	public void display(WeeklyPlan plan) {
		//draw a new JTabel
		String[][] datas = {getString(plan, "north"), getString(plan, "west"), 
				getString(plan, "east"), getString(plan, "south")};
		
		table = new JTable(datas, head);
		setTableStyle();
		
		this.removeAll();
		this.add(table.getTableHeader(), BorderLayout.NORTH);
		this.add(table, BorderLayout.CENTER);
	}
	
	private String[] getString(WeeklyPlan plan, String group) {
		String[] ans = new String[8];
		
		ans[0] = group;
		
		for (int i = 1; i < 8; i++) {
			String str = "";
			List<String> dayOfListString = plan.getWeeklyPlan(group).get(head[i]);
			if (dayOfListString == null) {
				ans[i] = str;
				continue;
			} else{
				for (int j = 0; j < dayOfListString.size(); j++) {
					if (j > 0) str += "\n";
					str += dayOfListString.get(j);
				}
				ans[i] = str;
			}
		}
		
		return ans;
	}
	
	private void setTableStyle(){
		table.setFont(Utils.getFormatFont(Font.BOLD | Font.ITALIC, 14));
		table.getTableHeader().setFont(Utils.getFormatFont(Font.BOLD, 16));
	}
}
