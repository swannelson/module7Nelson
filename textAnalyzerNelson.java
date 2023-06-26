package textAnalyzerNelson;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



public class textAnalyzerNelson implements ActionListener {

	public static void constructGUI()
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Word Occurrences");
		
		frame.setLayout(new GridLayout(1, 4));
		
		
		JLabel fn = new JLabel("Word Occurences");
		
		JLabel sn = new JLabel("Text: The Raven");
		JTextArea res = new JTextArea();
		
		JButton next = new JButton("Next");
		frame.setBounds(10,40,700,500);
		frame.add(fn);
		
		frame.add(sn);
		
		frame.add(new JLabel(" "));
		
		frame.add(new JLabel(" "));
		frame.add(res);
		res.setLineWrap(true);
		res.setWrapStyleWord(true);
	
		frame.add(next);
		
		next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fn.setBounds(0, 10, 659, 51);
				fn.setText("Results:");
				
            try {
				res.setText(wordSearch());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            next.setVisible(false);
            sn.setVisible(false);
            }
        });
		
		
		int frameWidth = 700;
		int frameHeight = 500;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		frame.setBounds(
				(int) (screenSize.getWidth()/2) - frameWidth,
				(int) (screenSize.getHeight()/2) - frameHeight,
				frameWidth,
				frameHeight);
		frame.setVisible(true);
	}
	public static void main(String[] args) throws IOException
	{
		wordSearch();
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run() {
				constructGUI();
			}
				});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public String wordSearch(File input) throws IOException{
		String str = "";
		
		Map map1 = new HashMap();
		try (BufferedReader bufferr = new BufferedReader(new FileReader(input))){
			StringBuilder stbuilder = new StringBuilder();
			StringBuilder sg = new StringBuilder();
			String line = bufferr.readLine();
			
			 while (line != null) {
	                String[] w = line.split(" ");
	                for (int i = 0; i < w.length; i++) {
	                    if (map1.get(w[i]) == null) {
	                        map1.put(w[i], 1);
	                    } else {
	                        int newValue = Integer.valueOf(String.valueOf(map1.get(w[i])));
	                        newValue++;
	                        map1.put(w[i], newValue);
	                    }
	                }
	                stbuilder.append(System.lineSeparator());
	                line = bufferr.readLine();
	            }
			 Set<Map.Entry<String, Integer>> list = map1.entrySet();
				ArrayList<Entry<String, Integer>> sort = new ArrayList<Map.Entry<String, Integer>>(list); 
				Collections.sort( sort, new Comparator<Map.Entry<String, Integer>>() 
		            {
		                public int compare( Map.Entry<String, Integer> a, Map.Entry<String, Integer> b ) 
		                {
		                    return (b.getValue()).compareTo( a.getValue() ); 
		                   
		                   

		                }
		            } );
				 
				 for( int i=0;i< 5;i++) {
					 sg.append((i+1) + ". " + sort.get(i) + "\n");
				 }
				
				 str = sg.toString();
				 System.out.println(str);
				return str;
	        }
	      
			
	

}

	
	
}
