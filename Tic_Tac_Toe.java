import java.util.*;
import java.lang.*;

public class NimisTic
{
    Scanner sc = new Scanner(System.in);
    String tic[][]=new String[3][3];
    int val[][]=new int[3][3];
    String n1;
    String n2;
    int c=0;
    void intro()
    {
        System.out.println("This is completely unnecessary, but i guess i'll write this because you want it so bad. Welcome, peasants.");
        System.out.println("Wanna play?");
    }
    
    void board()
    {
        int i,j;
        for(i=0;i<=2;i++)
        {
           for(j=0;j<=2;j++)
           {
               if(tic[i][j]==null)
                 System.out.print("    ");
               else
                 System.out.print("   " + tic[i][j]);
               if(j!=2)
                 System.out.print("   |");
               else
                 System.out.println();
           }
           if(i!=2)
             System.out.print("-----------------------");
           System.out.println();
        }
    }
    
    void tac(String str1, String str2)
    {
        int i;
        n1=str1;
        n2=str2;
        board();
        for(i=0;i<4;i++)
        {
          if(c==0)
          {
            System.out.println(str1 + "'s turn");
            valid(0);
            board();
            c=check();
          }
          if (c==0)
          {
            System.out.println(str2 + "'s turn");
            valid(1);
            board();
            c=check();
          } 
        }
        
        if(c==0)
        {
          board();
          System.out.println(str1 + "'s turn");
          valid(0);
          board();
          c=check();
        }
        
        if (c==0)
          draw();
    }
    
    void draw()
    {
        System.out.println("Draw.");
        again();
    }
    
    int check()
    {
        int i,j,count,flag=0;
        for(i=0;i<=2;i++)
        {
            count=0;
            for(j=1;j<=2;j++)
            {
                if((val[i][j])==(val[i][j-1]))
                count++;
            }
            if (count==2)
            flag=val[i][j-1];
        }
        
        for(i=0;i<3;i++)
        {
            count=0;
            for(j=1;j<3;j++)
            {
                if((val[j][i])==(val[j-1][i]))
                count++;
            }
            if (count==2)
            flag=val[j-1][i];
        }
        
        if((val[0][0]==val[1][1])&&(val[1][1]==val[2][2]))
          flag=val[1][1];
        
        if((val[0][2]==val[1][1])&&(val[1][1]==val[2][0]))
          flag=val[1][1];
        
        if(flag!=0)
        {
          win(flag);
          return 1;
        }
        else
          return 0;
    }
    
    void toe(int x, int y, int a)
    {
        if(val[x][y]==0)
        {
          if(a==0)
          {
              tic[x][y]="X";
              val[x][y]=1;
          }
          else
          {
              tic[x][y]="O";
              val[x][y]=2;
          }
        }
    }
    
    void valid(int a)
    {
        int x=sc.nextInt();
        int y=sc.nextInt();
        if((x>2)||(x<0)||(y>2)||(y<0))
        {
          System.out.println("Invalid Input");
          valid(a);
        }
        else if(val[x][y]!=0)
        {
          System.out.println("Position occupied");
          valid(a);
        }
        else
          toe(x,y,a);
    }
    
    void win(int a)
    {
        if(a==1)
        {
          System.out.println(n1 + " has won.");
          again();
          c=1;
        }
        else if(a==2)
        {
          System.out.println(n2 + " has won.");
          again();
          c=1;
        }
    }
    
    void players()
    {
        System.out.print("Enter name of player 1: ");
        String name1=sc.nextLine();
        System.out.print("Enter name of player 2: ");
        String name2=sc.nextLine();
        double rand=Math.random();
        if (rand<0.5)
        {
            System.out.println(name1 + " plays first.");
            tac(name1,name2);
        }
        else
        {
            System.out.println(name2 + " plays first.");
            tac(name2,name1);
        }
    }
    
    void again()
    {
        System.out.println("Would you like to play again?");
        String resp1=sc.next();
        if (resp1.equalsIgnoreCase("Yes"))
        {
          System.out.println("Good.");
          players();
        }
        else if(resp1.equalsIgnoreCase("No"))
        { 
          System.out.println("K."); 
        }
    }
    
    void game()
    {
        intro();
        String resp=sc.nextLine();
        if (resp.equalsIgnoreCase("Yes"))
        {
          System.out.println("Good.");
          players();
        }
        else
          System.out.println("K.");                
    }
}
