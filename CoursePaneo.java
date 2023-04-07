package application;

// Assignment #: Arizona State University Spring 2023 CSE205 #6
//         Name: Your Name
//    StudentID: Your ID
//      Lecture: Your lecture time (e.g., MWF 9:40am)
//  Description: //-- is where you should add your own code
//Note: when you submit on gradescope, you need to comment out the package line
//package yourPackageName;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.awt.Checkbox;
import java.util.ArrayList;
import java.util.Collection;
public class CoursePaneo extends HBox 
{
    //GUI components
    private ArrayList<Course> courseList;
    private VBox checkboxContainer;
    int countingInt;
    private Button add;
    private Button drop;
    private ComboBox<String> comboBox;
    private TextField textField;
    private TextField textField1;
    private Label exception;
    private Label duplicate;
    private Label botLeft;
    private ArrayList<CheckBox> checkboxList;
    private CheckBox cb;
    private Label botRight;
    
	
    //Step 1.1: declare any necessary instance variables here
    //----
    //constructor
 
	public CoursePaneo()
    {
        //step 1.2: initialize instance variables
        //----
		
    	courseList = new ArrayList<Course>();
    	checkboxList = new ArrayList<CheckBox>();
    	checkboxContainer = new VBox();
    
    	  
         
      
    	exception = new Label("At least one field is empty. Fill all fields");
    	exception.setTextFill(Color.BLUE);
    	duplicate = new Label("Duplicate course - Not added");
    	 
        Label labelLeft = new Label("  Add Course(s)");
        labelLeft.setTextFill(Color.BLUE);
        labelLeft.setFont(Font.font(null, 14));
        
        GridPane gridpane = new GridPane();
        BorderPane borderpane = new BorderPane();
       
        
        comboBox = new ComboBox<String>();
        
        comboBox.getItems().addAll("ACC","AME","BME","CHM","CSE","DAT","EEE");
        comboBox.setValue("CSE");
        textField = new TextField();
        textField1 = new TextField();
        
        gridpane.add(new Label("Subject"), 0, 0);
        gridpane.add(comboBox, 1, 0);
        gridpane.add(new Label("Course Num"), 0, 1);
        gridpane.add(textField, 1, 1);
        gridpane.add(new Label("Instructor"), 0, 2);
        gridpane.add(textField1, 1, 2);
        
        botLeft = new Label("  No Courses entered");
        
        
        Label labelRight = new Label("Course(s) Enrolled");
        labelRight.setTextFill(Color.BLUE);
        labelRight.setFont(Font.font(null, 14));
        //set up the layout. Note: CoursePane is a HBox and contains
        //leftPane, centerPane and rightPane. Pick proper layout class
        //and use nested sub-pane if necessary, then add each GUI components 
        //inside.
        //step 1.3: create and set up the layout of the leftPane, leftPane contains
        //a top label, the center sub-pane
        //and a label show at the bottom
        
        
        
        
        VBox leftPane = new VBox(90);
        gridpane.setPadding(new Insets(11, 12, 13, 14));
        gridpane.setVgap(20);
        gridpane.setHgap(20); 
       // leftPane.setAlignment(Pos.BASELINE_LEFT);
        leftPane.getChildren().addAll(labelLeft, gridpane, botLeft);
        
        labelLeft.setAlignment(Pos.TOP_LEFT); 
        gridpane.setAlignment(Pos.CENTER_LEFT);
        botLeft.setAlignment(Pos.BOTTOM_LEFT);
        
        leftPane.setAlignment(Pos.CENTER_LEFT);
        leftPane.setStyle("-fx-border-color: black");
        leftPane.setPrefWidth(300);
        
        //----
        //step 1.4: create and set up the layout of the centerPane which holds the 
        //two buttons
        add = new Button("Add =>");
        drop = new Button("Drop <=");
        add.setOnAction(new ButtonHandler());
        drop.setOnAction(new ButtonHandler());
      
        VBox centerPane = new VBox(20);
        
		centerPane.setAlignment(Pos.CENTER);
		centerPane.setPadding(new Insets(20));
		centerPane.getChildren().addAll(add, drop);

//----
        //step 1.5: create and set up the layout of the rightPane, rightPane 
        //contains a top label,
		VBox rightPane = new VBox();
        labelRight.setAlignment(Pos.TOP_RIGHT);
   //     rightPane.setAlignment(Pos.BASELINE_RIGHT);
        checkboxContainer.setAlignment(Pos.CENTER);
        
   //     checkboxContainer.setPrefWidth(160);
        
        botRight = new Label("Total course enrolled: " + courseList.size());
        borderpane.setPadding(new Insets(11, 12, 13, 14));
        
        
        borderpane.setTop(labelRight);
        
        borderpane.setCenter(checkboxContainer);
       
        borderpane.setBottom(botRight);
        
        botRight.setAlignment(Pos.BOTTOM_CENTER);
        labelRight.setAlignment(Pos.TOP_RIGHT);
    //    rightPane.getChildren().addAll(labelRight, checkboxContainer, botRight);
        borderpane.setStyle("-fx-border-color: black");
   //     rightPane.setPrefWidth(180);
        
        
        //checkboxContainer and a label show at the bottom
        //----
        //CoursePane is a HBox. Add leftPane, centerPane and rightPane inside
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(leftPane, centerPane, borderpane);
        //----
        //Step 3: Register the GUI component with its handler class
        //----
    } //end of constructor
    //step 2.1: Whenever a new Course is added or one or several courses are 
//dropped/removed, this method will
    //1) clear the original checkboxContainer;
    //2) create a CheckBox for each Course object inside the courseList, and also 
//add it inside the checkboxContainer;
    //3) register the CheckBox with the CheckBoxHandler.
    public void updateCheckBoxContainer() 
    {
    	checkboxContainer.getChildren().clear();
    	
    	for (int i = 0; i < courseList.size(); i++) {
    		cb = new CheckBox(courseList.get(i).toString());
    		
    		checkboxContainer.getChildren().add(cb);
    		cb.setIndeterminate(true);
    		cb.setOnAction(new CheckBoxHandler(courseList));
    		

    	}
    	
    	botRight.setText("Total course enrolled: " + courseList.size());
    	 		
        
    }
    //Step 2.2: Create a ButtonHandler class
    private class ButtonHandler implements EventHandler<ActionEvent>
    {
        @SuppressWarnings("unchecked")
		public void handle(ActionEvent e)
        {
        	
           
            try {	
//if (//everything is entered correctly and the "Add 
//=>" button is pressed
        	
               String subject = (String)comboBox.getValue();
               
         	   int number = Integer.parseInt(textField.getText());
         	   String instructor = textField1.getText();
         	   Button sub = (Button)e.getSource();
         	   Integer num = (int)number;
         	   Course course = new Course(subject, num, instructor);
         	   
         	  
            	
            	if(sub == add)	
            	{
            		
            		//else if ( //">=" button is pressed,  error handling using a loop to check if field is empty
            		 for (int i = 0; i < courseList.size(); i++) {
                	   if (subject.isEmpty() || num == null || instructor.isEmpty()) {
                		  courseList.remove(i);
                		  
                	   }
            		 }
                	   //checks for duplicates
            		 
                	   Course dup = new Course(subject, num, instructor);
                	   for (int i = 0; i < courseList.size(); i++) {
                		   
                		 dup = courseList.get(i);
                			   if (dup.getCourseNum() == num) {
                				   botLeft.setText("Duplicated course - Not added");
                				   botLeft.setTextFill(Color.RED);
                				   courseList.remove(i);
                				   
                				   
                			   
                		   }
                		   
                	   }
                	   
                	   courseList.add(course);
                	   
            	
          //   drop button 
                	   checkboxList.clear();
                	   
                	   int length = courseList.size();
                	//   Button bus = (Button)e.getSource();
                	   if (e.getSource() == drop) {
                	   ArrayList<Course> tempBox = new ArrayList<Course>();
                	   
                	   for (Course i : courseList) {

                           courseList.remove(i);
                           botRight.setText("Total course enrolled: " + courseList.size());
                          }
                	   
  
            			}
            				
            				
            				updateCheckBoxContainer();
            				//Clear all the text fields and prepare for the next entry;
            				textField.clear();
            				textField1.clear();
            				
            	            
            	                
            	           
            	}
            }
            
            			
          catch(NumberFormatException ex)
         {
          	botLeft.setText("  At least one field is empty. Fill all fields");
            botLeft.setTextFill(Color.RED);
                
          }
            		
            			
  
           
            catch(Exception exception) //
            {
                //----
            	
            	
            }
        } //end of handle() method
    } //end of ButtonHandler class
     ////Step 2.3: A ComboBoxHandler
    
    private class ComboBoxHandler implements EventHandler<ActionEvent>
    {

		
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
			
		}
       //----
    }//end of ComboBoxHandler
    //Step 2.4: A CheckBoxHandler
    private class CheckBoxHandler implements EventHandler<ActionEvent>
    {
        // Pass in a Course object so that we know which course is associated with 
//which CheckBox
        private Course oneCourse;
        //constructor
        public CheckBoxHandler(Course oneCourse)
        {
           this.oneCourse = oneCourse;
           
        }
      
 
		
		
		public CheckBoxHandler(ArrayList<Course> courseList) {
			// TODO Auto-generated constructor stub
		}


		public void handle(ActionEvent e)
        {
            CheckBox check = (CheckBox) e.getSource();
            if (cb.isSelected() == true) {
            	courseList.add(oneCourse);
            	courseList.size(); }
            else  { 
            	courseList.remove(oneCourse);

            }
            	
            	
            	
        }
    }//end of CheckBoxHandler
} //end of CoursePane class