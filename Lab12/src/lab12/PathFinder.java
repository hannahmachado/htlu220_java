package lab12;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

public class PathFinder {	
	private static int height;
	private static int width;
	private static Node[][] pacman;
	private static Node start;
	private static Queue<Node> mazeSolver= new LinkedList<Node>();
	private static Node up;
	private static Node down;
	private static Node east;
	private static Node west;

	public static void solveMaze(String inputFile, String outputFile){
		try{
		  File f = new File(inputFile);
		  FileReader text = new FileReader(f);
		  BufferedReader input= new BufferedReader(text);
		
		  String[] dimensions = input.readLine().split(" ");
		  height = Integer.parseInt(dimensions[0]);
		  width = Integer.parseInt(dimensions[1]);
		  System.out.println(height + " " + width);
		  pacman = new Node[height][width];
		
		  for(int i = 0; i < height; i++){
			String line = input.readLine();
			for(int x = 0; x < width; x++){
				char content = line.charAt(x);
				pacman[i][x] = new Node(false, content, null, i, x);
				if(content == 'S'){
					start= pacman[i][x];		
				}
		 	}
		  }
		  input.close();
		  }
		  catch(IOException ex){
				System.out.print("FileNotFoundException");
			}
		   BFS(start);
		   try{
			  
			  File solvedMaze = new File(outputFile);
			  FileWriter translate = new FileWriter(solvedMaze);
				
				translate.write(height + " " + width + "\n");
				for(int i = 0; i < height; i++){
					for(int x = 0; x < width; x++){
						translate.write(pacman[i][x].content);
					}
					translate.write("\n");
					}
				translate.close();
			} 
		   catch(IOException ex){
				System.out.print("FileNotFoundExceptioin");
			}
		  
	}
	
	public static Node[][] BFS(Node start){
		mazeSolver.add(start);
		start.visited=true;
		start.previous = null;
		while(!mazeSolver.isEmpty()){
			Node current= mazeSolver.remove();
		       if(current.content == 'G'){
		    	   while(current.previous != null){
						if(current.previous.content != 'S'){
						   current.previous.content = '.';
						}
						current = current.previous;
					}
		   }
		      neighbors(current);
	  }
		return pacman;
	}
	
	
	public static void neighbors(Node current){
		up = pacman[current.row - 1][current.col]; 
		if(up.content != 'X' && (up.visited == false)){
			mazeSolver.add(up);
			up.visited = true;
			up.previous = current;
		}
		down = pacman[current.row + 1][current.col]; 
		if(down.content != 'X' && (down.visited == false)){
			mazeSolver.add(down);
			down.visited = true;
			down.previous = current;
		}
		east = pacman[current.row][current.col - 1]; 
		if(east.content != 'X' && (east.visited == false)){
			mazeSolver.add(east);
			east.visited = true;
			east.previous = current;
		}
		west = pacman[current.row][current.col + 1]; 
		if(west.content != 'X' && (west.visited == false)){
			mazeSolver.add(west);
			west.visited = true;
			west.previous = current;
		}
	} 
	public static void main(String arg[]){
		solveMaze("classic.txt", "solved.txt");
		solveMaze("mediumMaze", "solved2.txt");
		solveMaze("bigMaze", "solved3.txt");
		solveMaze("demoMaze", "solved4.txt");
		solveMaze("randomMaze", "solved5.txt");
		solveMaze("tinyMaze", "solved6.txt");
		solveMaze("Straight.txt", "solved7.txt");
		solveMaze("tinyOpen", "solved8.txt");
		solveMaze("turn", "solved9.txt");
		solveMaze("unsolvable", "solved10.txt");
	}
}	
	