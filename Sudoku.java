import java.awt.*;        // Uses AWT's Layout Managers
import java.awt.event.*;  // Uses AWT's Event Handlers
import java.io.File;
import java.io.IOException;
import javax.swing.*;     // Uses Swing's Container/Components
import java.net.URL;
import javax.sound.sampled.*;
import java.util.concurrent.ThreadLocalRandom;


/**
 * The Sudoku game.
 * To solve the number puzzle, each row, each column, and each of the
 * nine 3×3 sub-grids shall contain all of the digits from 1 to 9
 */
@SuppressWarnings("serial") //@SuppressWarnings("serial") makes the compiler shut up about a missing serialVersionUID .

public class Sudoku extends JFrame implements ActionListener{
   // Name-constants for the game properties
   public static final int GRID_SIZE = 9;    // Size of the board
   public static final int SUBGRID_SIZE = 3; // Size of the sub-grid
 
   // Name-constants for UI control (sizes, colors and fonts)
   public static final int CELL_SIZE = 50;   // Cell width/height in pixels
   public static final int CANVAS_WIDTH  = CELL_SIZE * GRID_SIZE;
   public static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;
                                             // Board width/height in pixels
   public static final Color CLOSED_CELL_BGCOLOR = Color.DARK_GRAY; // RGB
   public static final Color OPEN_CELL_BGCOLOR = Color.white;
   public static final Color OPEN_CELL_TEXT_YES = Color.BLACK;  // RGB
   public static final Color OPEN_CELL_TEXT_NO = Color.RED;
   
   public static final Color CLOSED_CELL_TEXT =  Color.BLACK;
   public static final Font FONT_NUMBERS = new Font("DialogInput", Font.BOLD, 40);
   public static final Color Correct_Ans = Color.BLACK;
 
   // The game board composes of 9x9 JTextFields,
   // each containing String "1" to "9", or empty String
   private JTextField[][] tfCells = new JTextField[GRID_SIZE][GRID_SIZE];
   public static int [] row1 = new int [GRID_SIZE];
   public static int [] row2 = new int [GRID_SIZE];
   public static int [] row3 = new int [GRID_SIZE];
   public static int level = 1;
 
   // Puzzle to be solved and the mask (which can be used to control the
   //  difficulty level).
   // Hardcoded here. Extra credit for automatic puzzle generation
   //  with various difficulty levels.
   
   //EASY
   private static int[][] puzzle =
      {{5, 3, 4, 6, 7, 8, 9, 1, 2},
       {6, 7, 2, 1, 9, 5, 3, 4, 8},
       {1, 9, 8, 3, 4, 2, 5, 6, 7},
       {8, 5, 9, 7, 6, 1, 4, 2, 3},
       {4, 2, 6, 8, 5, 3, 7, 9, 1},
       {7, 1, 3, 9, 2, 4, 8, 5, 6},
       {9, 6, 1, 5, 3, 7, 2, 8, 4},
       {2, 8, 7, 4, 1, 9, 6, 3, 5},
       {3, 4, 5, 2, 8, 6, 1, 7, 9}};
   // For testing, open only 2 cells.
   
   private static boolean[][] masks = new boolean [GRID_SIZE][GRID_SIZE];
      /*{{false, false, false, false, false, true, false, false, false},
       {false, false, false, false, false, false, false, false, true},
       {false, false, false, false, false, false, false, false, false},
       {false, false, false, false, false, false, false, false, false},
       {false, false, false, false, false, false, false, false, false},
       {false, false, false, false, false, false, false, false, false},
       {false, false, false, false, false, false, false, false, false},
       {false, false, false, false, false, false, false, false, false},
       {false, false, false, false, false, false, false, false, false}};*/
   //--------------------------------------------------------------------------//
   
 
   private int count = 81;
   private int maskCount;
   private BG1 background = new BG1();  
   private NoSolutionBG nosolutionbg = new NoSolutionBG(); 
   private JMenuItem easy = new JMenuItem("Easy Level");
   private JMenuItem intermediate = new JMenuItem("Intermediate Level");
   private JMenuItem hard = new JMenuItem("Hard Level");
   private JMenuItem exit = new JMenuItem("Exit");
   private JMenuItem help = new JMenuItem("Solution");
   private JMenuItem disableSound = new JMenuItem("Disable Sound");
   private JMenuItem enableSound = new JMenuItem("Enable Sound");
   private JMenuItem about = new JMenuItem("The Team");
   private JLabel counter;
   private JMenuItem themeC = new JMenuItem("Yellow");
   private JMenuItem themeB = new JMenuItem("White");
      
   /**
    * Constructor to setup the game and the UI Components
    */
   public Sudoku() {
	      
	   Container cp = getContentPane();
	      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

	      cp.setLayout(new BorderLayout());  // 9x9 GridLayout
	      JPanel tfPanel = new JPanel (new GridLayout(GRID_SIZE, GRID_SIZE));
	      
	      cp.add(tfPanel, BorderLayout.CENTER);     //create a new panel to store the timer
	      cp.add(new Screen2(), BorderLayout.NORTH);

	      JLabel image = new JLabel(new ImageIcon(((new ImageIcon("D:\\Java\\new\\Sudoku\\sudoku-title.jpg")).getImage()).getScaledInstance(335, 85, Image.SCALE_SMOOTH)));
	      JPanel imagePanel = new JPanel();
	      imagePanel.setLayout(new BorderLayout());
	      
	      counter = new JLabel();  //for the number of cells left after the user input. creating a new label to place it on top of the image(above).
	     // counter.setText(" "+ maskCount + " Cell(s) Left.");
   
	      imagePanel.add(counter, BorderLayout.WEST);
	      imagePanel.setBackground(Color.WHITE);
	      imagePanel.add(image, BorderLayout.CENTER); 
	      cp.add(imagePanel, BorderLayout.SOUTH);
	      
	 
	      // Allocate a common listener as the ActionEvent listener for all the
	      //  JTextFields
	      // ... [TODO 3] 
	      InputListener listener = new InputListener();
	      
	      initGame();
	      
	      // Construct 9x9 JTextFields and add to the content-pane
	      for (int row = 0; row < GRID_SIZE; ++row) {
	         for (int col = 0; col < GRID_SIZE; ++col) {
	            tfCells[row][col] = new JTextField(); // Allocate element of array
	            tfPanel.add(tfCells[row][col]);            // ContentPane adds JTextField
	            if (masks[row][col]) {
	               tfCells[row][col].setText("");     // set to empty string
	               tfCells[row][col].setEditable(true);
	               tfCells[row][col].setBackground(OPEN_CELL_BGCOLOR);
	 
	               // Add ActionEvent listener to process the input
	               // ... [TODO 4] 
	               tfCells[row][col].addActionListener(listener);
	            } else {
	               tfCells[row][col].setText(puzzle[row][col] + "");
	               tfCells[row][col].setEditable(false);
	               tfCells[row][col].setBackground(CLOSED_CELL_BGCOLOR);
	               tfCells[row][col].setForeground(CLOSED_CELL_TEXT);
	            }
	            // Beautify all the cells
	            tfCells[row][col].setHorizontalAlignment(JTextField.CENTER);
	            tfCells[row][col].setFont(FONT_NUMBERS);
	         }
	      }
//--------------------------------------------------------------------------------------------------------------------------------------------------------------//
	      //setting the row and col into white background.
	      
	      for(int row = 0; row <3; row ++) {
	    	  for(int col = 0; col <3; col++) {
	    		  tfCells[row][col].setBackground(Color.WHITE);
	    		 
	    	  }
	      }
	      for(int row = 0; row <3; row ++) {
	    	  for(int col = 3; col <6; col++) {
	    		  tfCells[row][col].setBackground(Color.GRAY);
	    		 
	    	  }
	      }
	      for(int row = 3; row <6; row ++) {
	    	  for(int col = 3; col <6; col++) {
	    		  tfCells[row][col].setBackground(Color.WHITE);
	    		  
	    	  }
	    	  
	      }
	      for(int row = 3; row <6; row ++) {
	    	  for(int col = 0; col <3; col++) {
	    		  tfCells[row][col].setBackground(Color.GRAY);
	    		  
	    	  }
	      }
	      for(int row = 6; row <9; row ++) {
	    	  for(int col = 6; col <9; col++) {
	    		  tfCells[row][col].setBackground(Color.WHITE);
	    		  
	    	  }
	      }
	      for(int row = 0; row <3; row ++) {
	    	  for(int col = 6; col <9; col++) {
	    		  tfCells[row][col].setBackground(Color.WHITE);
	    		  
	    	  }
	      }
	      for(int row = 3; row <6; row ++) {
	    	  for(int col = 6; col <9; col++) {
	    		  tfCells[row][col].setBackground(Color.GRAY);
	    		  
	    	  }
	      }
	      for(int row = 6; row <9; row ++) {
	    	  for(int col = 0; col <3; col++) {
	    		  tfCells[row][col].setBackground(Color.WHITE);
	    		  

	    	  }
	      }
	      for(int row = 6; row <9; row ++) {
	    	  for(int col = 3; col <6; col++) {
	    		  tfCells[row][col].setBackground(Color.GRAY);
	    		  
	    	  }
	      }
//---------------------------------------------------------------------------------------------------------------------------------------------------------------//	 
	      // Set the size of the content-pane and pack all the components
	      //  under this container.
	      cp.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
	      pack();
	      

	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Handle window closing
	      setTitle("Sudoku");
	      setVisible(true);
	      
	      for (int row = 0; row < GRID_SIZE; ++row) {
	          for (int col = 0; col < GRID_SIZE; ++col) {
	        	  if(masks[row][col] == true)
	        		  maskCount++;
	          }
	      }
	      count = count - maskCount;
      
   //--------------------------------------------------------------------------//   
      JMenuBar menubar = new JMenuBar();
      JMenu file = new JMenu("File");
      JMenu option = new JMenu("Options");
      JMenu helpBar = new JMenu("Help");
      JMenu New = new JMenu("New Game");  
      JMenu aboutUs = new JMenu("About");
      JMenu theme = new JMenu("Theme");
      			
      easy.addActionListener(this);					//Easy Level
      intermediate.addActionListener(this);			//Intermediate Level
      hard.addActionListener(this);					//Hard Level
      exit.addActionListener(this);					//Exit
      New.addActionListener(this);					//New Game
      help.addActionListener(this);      			//Help
      disableSound.addActionListener(this);			//Disable Sound
      enableSound.addActionListener(this);			//Enable Sound 
      about.addActionListener(this);
      themeC.addActionListener(this);
      themeB.addActionListener(this);
      
      
      
      menubar.add(file);
      menubar.add(option);
      menubar.add(theme);
      menubar.add(helpBar);
      menubar.add(aboutUs);
    
      
      
      file.add(New);
      file.addSeparator();
      file.add(exit);
      
      New.add(easy);
      New.addSeparator();
      New.add(intermediate);
      New.addSeparator();
      New.add(hard);
      
      option.add(disableSound);
      option.addSeparator();
      option.add(enableSound);    
      
      helpBar.add(help);
      
      aboutUs.add(about);
      theme.add(themeC);
      theme.addSeparator();
      theme.add(themeB);
      
      
      this.setJMenuBar(menubar);
      this.setSize(800,800);
      this.setVisible(true);
      
      
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
	   
	if(e.getSource() == easy) {
		level = 1;
		getPuzzle();
		getMasks(level);
		dispose();
		new Sudoku();
		background.disable();
		
	}else if(e.getSource() == intermediate) {
		level = 2;
		getPuzzle();
		getMasks(level);
		Sudoku.this.dispose();
		new Sudoku();
		background.disable();
		
	}else if(e.getSource() == hard) {
		level = 3;
		getPuzzle();
		getMasks(level);
		Sudoku.this.dispose();
		new Sudoku();
		background.disable();
		
	}else if(e.getSource()==exit) {
		System.exit(0);	
		
	}else if(e.getSource() == help) {
		
		ImageIcon icon = new ImageIcon("D:\\Java\\new\\Sudoku\\noob.jpg");
		Image temp = icon.getImage().getScaledInstance(58, 58, Image.SCALE_SMOOTH);
		icon = new ImageIcon(temp);
		
		background.disable();
		nosolutionbg.enable();
		JOptionPane.showMessageDialog(this, "NO SOLUTION HAHAHAHA!","NOOB",JOptionPane.WARNING_MESSAGE,icon);
		nosolutionbg.disable();
		background.enable();

	}else if(e.getSource() == enableSound) {
		
		background.enable();
	
	}
	else if(e.getSource() == disableSound) {
		background.disable();
	}
	else if(e.getSource()== about) {
		
		JOptionPane.showMessageDialog(this, "Done by:\r\n"+"Khuz (U1722592A)\r\n"+"Glen (U1721881L)");
	}
	else if (e.getSource() == themeC) {
		for(int row = 0; row <3; row ++) {
	    	  for(int col = 0; col <3; col++) {
	    		  tfCells[row][col].setBackground(Color.yellow);
	    		 
	    	  }
	      }
	      for(int row = 0; row <3; row ++) {
	    	  for(int col = 3; col <6; col++) {
	    		  tfCells[row][col].setBackground(Color.gray);
	    		 
	    	  }
	      }
	      for(int row = 3; row <6; row ++) {
	    	  for(int col = 3; col <6; col++) {
	    		  tfCells[row][col].setBackground(Color.yellow);
	    		  
	    	  }
	    	  
	      }
	      for(int row = 3; row <6; row ++) {
	    	  for(int col = 0; col <3; col++) {
	    		  tfCells[row][col].setBackground(Color.gray);
	    		  
	    	  }
	      }
	      for(int row = 6; row <9; row ++) {
	    	  for(int col = 6; col <9; col++) {
	    		  tfCells[row][col].setBackground(Color.yellow);
	    		  
	    	  }
	      }
	      for(int row = 0; row <3; row ++) {
	    	  for(int col = 6; col <9; col++) {
	    		  tfCells[row][col].setBackground(Color.yellow);
	    		  
	    	  }
	      }
	      for(int row = 3; row <6; row ++) {
	    	  for(int col = 6; col <9; col++) {
	    		  tfCells[row][col].setBackground(Color.gray);
	    		  
	    	  }
	      }
	      for(int row = 6; row <9; row ++) {
	    	  for(int col = 0; col <3; col++) {
	    		  tfCells[row][col].setBackground(Color.yellow);
	    		  

	    	  }
	      }
	      for(int row = 6; row <9; row ++) {
	    	  for(int col = 3; col <6; col++) {
	    		  tfCells[row][col].setBackground(Color.gray);
	    		  
	    	  }
	      }
	}
	else if(e.getSource() == themeB) {
		for(int row = 0; row <3; row ++) {
	    	  for(int col = 0; col <3; col++) {
	    		  tfCells[row][col].setBackground(Color.WHITE);
	    		 
	    	  }
	      }
	      for(int row = 0; row <3; row ++) {
	    	  for(int col = 3; col <6; col++) {
	    		  tfCells[row][col].setBackground(Color.gray);
	    		 
	    	  }
	      }
	      for(int row = 3; row <6; row ++) {
	    	  for(int col = 3; col <6; col++) {
	    		  tfCells[row][col].setBackground(Color.WHITE);
	    		  
	    	  }
	    	  
	      }
	      for(int row = 3; row <6; row ++) {
	    	  for(int col = 0; col <3; col++) {
	    		  tfCells[row][col].setBackground(Color.GRAY);
	    		  
	    	  }
	      }
	      for(int row = 6; row <9; row ++) {
	    	  for(int col = 6; col <9; col++) {
	    		  tfCells[row][col].setBackground(Color.WHITE);
	    		  
	    	  }
	      }
	      for(int row = 0; row <3; row ++) {
	    	  for(int col = 6; col <9; col++) {
	    		  tfCells[row][col].setBackground(Color.WHITE);
	    		  
	    	  }
	      }
	      for(int row = 3; row <6; row ++) {
	    	  for(int col = 6; col <9; col++) {
	    		  tfCells[row][col].setBackground(Color.GRAY);
	    		  
	    	  }
	      }
	      for(int row = 6; row <9; row ++) {
	    	  for(int col = 0; col <3; col++) {
	    		  tfCells[row][col].setBackground(Color.WHITE);
	    		  

	    	  }
	      }
	      for(int row = 6; row <9; row ++) {
	    	  for(int col = 3; col <6; col++) {
	    		  tfCells[row][col].setBackground(Color.GRAY);
	    		  
	    	  }
	      }
	}
}

   /** The entry main() entry method */
   public static void main(String[] args) {
      // [TODO 1] (Now)
      // Check Swing program template on how to run the constructor
      SwingUtilities.invokeLater(new Runnable(){
    	  @Override 
    	  public void run() {
    		JOptionPane.showMessageDialog(null, "RULES\r\n\n" 
    				
    				+"-Sudoku are easy to learn yet highly addictive language-independent logic puzzles which have recently taken the whole world by storm.\r\n\n"
    				
    				+"-Using pure logic and requiring no math to solve, these fascinating puzzles offer endless fun and intellectual entertainment to puzzle\r\n"
    				+" fans of all skills and ages.\r\n\n"  
    				
    				+"-The Classic Sudoku is a number placing puzzle based on a 9x9 grid with several given numbers.\r\n\n"
    				
    				+"-The object is to place the numbers 1 to 9 in the empty squares so that each row, each column and each 3x3 box contains the same number only once.\r\n\n" + 
    				
    				"-Sudoku puzzles come in endless number combinations and range from very easy to extremely difficult taking anything from five minutes to several hours to solve.\r\n\n"
    				
    				+"-Sudoku puzzles also come in many variants, each variant looking differently and each variant offering a unique twist of brain challenging logic.\r\n\n" + 
    				
    				"-However, make one mistake and you’ll find yourself stuck later on as you get closer to the solution… Try these puzzles, and see if you can solve them too!\r\n\n"
    				
    				+"Are you ready...?");
    		
    		 new Sudoku();
    	  }
      });
   }
  
   public void initGame() {
	   getPuzzle();
	   getMasks(level);
   }
   
   public static void getPuzzle() {
	   
	  int khuz = ThreadLocalRandom.current().nextInt(1,3); //check from min = 1 to max = (2), but syntax requires to max + 1
	   
	   if(khuz == 1) {
		   for(int col = 0; col<GRID_SIZE;col++) {
			   row1[col] = puzzle[0][col];
			   row2[col] = puzzle[1][col];
			   row3[col] = puzzle[2][col];
			   puzzle[1][col] = row1[col];
			   puzzle[2][col] = row2[col];
			   puzzle[0][col] = row3[col];
			   
			   row1[col] = puzzle[3][col];
			   row2[col] = puzzle[4][col];
			   row3[col] = puzzle[5][col];
			   puzzle[4][col] = row1[col];
			   puzzle[5][col] = row2[col];
			   puzzle[3][col] = row3[col];
			   
			   row1[col] = puzzle[6][col];
			   row2[col] = puzzle[7][col];
			   row3[col] = puzzle[8][col];
			   puzzle[7][col] = row1[col];
			   puzzle[8][col] = row2[col];
			   puzzle[6][col] = row3[col];
		   }   
		   }
	   else if(khuz == 2) {
		   for(int row = 0; row <GRID_SIZE; row++) {
			   row1[row] = puzzle[row][0];
			   row2[row] = puzzle[row][1];
			   row3[row] = puzzle[row][2];
			   puzzle[row][1] = row1[row];
			   puzzle[row][2] = row2[row];
			   puzzle[row][0] = row3[row];
			   
			   row1[row] = puzzle[row][3];
			   row2[row] = puzzle[row][4];
			   row3[row] = puzzle[row][5];
			   puzzle[row][4] = row1[row];
			   puzzle[row][5] = row2[row];
			   puzzle[row][3] = row3[row];
			   
			   row1[row] = puzzle[row][6];
			   row2[row] = puzzle[row][7];
			   row3[row] = puzzle[row][8];
			   puzzle[row][7] = row1[row];
			   puzzle[row][8] = row2[row];
			   puzzle[row][6] = row3[row];
		   }
	   }
	   
   }
   public static void getMasks(int level) {
	   int glen;
	   
	   if(level == 1) { //level = 1 means easy.
		   for(int row = 0; row < GRID_SIZE; row++) {
			   for(int col = 0; col < GRID_SIZE; col++) {
				  glen = ThreadLocalRandom.current().nextInt(1,101);  // 1 - 100%, 101 because threadlocalrandom is the max + 1. 
				   if(glen <=25) {  //25% of the cells are OPEN. (means need user to input)
					   {
						   masks[row][col] = true;
					   }
				   }
				   else if (glen >25)
					   {
					   masks[row][col]= false; //75% of the cells are CLOSE.
					   }
			   }
		   }
		   
	   }
	   else if(level == 2) { // level = 2 means intermediate.
		   for(int row = 0; row < GRID_SIZE; row++) {
			   for(int col = 0; col < GRID_SIZE; col++) {
				  glen = ThreadLocalRandom.current().nextInt(1,101);
				   if(glen <=45) { 
					   {
						   masks[row][col] = true;
					   }
				   }
				   else if (glen >45)
					   {
					   masks[row][col]= false;
					   }
			   }
		   }
		   
	   }
	   else if(level == 3) {  //level = 3 means hard.
		   for(int row = 0; row < GRID_SIZE; row++) {
			   for(int col = 0; col < GRID_SIZE; col++) {
				  glen = ThreadLocalRandom.current().nextInt(1,101);
				   if(glen <=60) {
					   {
						   masks[row][col] = true;
					   }
				   }
				   else if (glen >60)
					   {
					   masks[row][col]= false;
					   }
			   }
		   }
		   
	   }
	   
   }
  

   // [TODO 2]
   // Inner class to be used as ActionEvent listener for ALL JTextFields
   private class InputListener implements ActionListener {  
 
      @Override
      public void actionPerformed(ActionEvent e) {
         // All the 9*9 JTextFileds invoke this handler. We need to determine
         // which JTextField (which row and column) is the source for this invocation.
         int rowSelected = -1;
         int colSelected = -1;
 
         // Get the source object that fired the event
         JTextField source = (JTextField)e.getSource();
         // Scan JTextFileds for all rows and columns, and match with the source object
         boolean found = false;
         for (int row = 0; row < GRID_SIZE && !found; ++row) {
            for (int col = 0; col < GRID_SIZE && !found; ++col) {
               if (tfCells[row][col] == source) {
                  rowSelected = row;
                  colSelected = col;
                  found = true;  // break the inner/outer loops
               }
            }
         }
 
         /*
          * [TODO 5]
          * 1. Get the input String via tfCells[rowSelected][colSelected].getText()
          * 2. Convert the String to int via Integer.parseInt().
          * 3. Assume that the solution is unique. Compare the input number with
          *    the number in the puzzle[rowSelected][colSelected].  If they are the same,
          *    set the background to green (Color.GREEN); otherwise, set to red (Color.RED).
          */
       //---------------------------------------------------------------------------------------------------------------------------//
         int inStr = Integer.parseInt(tfCells[rowSelected][colSelected].getText());
        //to check which puzzle it is.
         if(inStr ==  puzzle[rowSelected][colSelected]){
	        	 tfCells[rowSelected][colSelected].setBackground(Color.GREEN);
	        	 masks[rowSelected][colSelected] = true; 
        		 count++;
        		tfCells[rowSelected][colSelected].setEditable(false);
        		tfCells[rowSelected][colSelected].setEnabled(false);
        		tfCells[rowSelected][colSelected].setDisabledTextColor(CLOSED_CELL_TEXT);
        		new SFX1(true); 
       	 	}
	        else
       	 		{
	        	tfCells[rowSelected][colSelected].setBackground(Color.RED);
       	 		new SFX1(false);
       	 		}
        if(inStr == puzzle[rowSelected][colSelected]) {
        	 masks[rowSelected][colSelected] = true ;
        	 maskCount--;
        	 System.out.println(maskCount);
        	 counter.setText("   "+ maskCount + " Cell(s) Left.");
        	 
         }

         /* 
          * [TODO 6] Check if the player has solved the puzzle after this move.
          * You could update the masks[][] on correct guess, and check the masks[][] if
          * any input cell pending.
          */
         
         
         ImageIcon icon = new ImageIcon("C:\\Users\\Khuz\\Desktop\\Gandalf.gif");
         if(count == 81) {
        	 new Congrats1();
        	 //JOptionPane.showMessageDialog(null, "Congratulation!");
        	 
        	 int result = JOptionPane.showConfirmDialog(null,
       		        "Congratulation! Do you want to try again?",
       		        "Confirm Quit", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, icon);
        	 
       		if (result == JOptionPane.NO_OPTION) System.exit(0);
       		if(result == JOptionPane.YES_OPTION) {
       			Sudoku.this.dispose();
       			new Sudoku();
       			background.disable();
       			

       		}
         }
      }
   }    
}