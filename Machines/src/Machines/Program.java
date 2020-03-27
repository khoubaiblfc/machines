package Machines;

import java.io.Console;
import java.sql.SQLException;

public class Program {

	public static void main(String[] args) throws Exception
    {
        Program p = new Program();

        p.entete(args);
        String command;
            
           
        do
            {
                
                command = Input.getInput("\nCommande:");
                if (command.toUpperCase()!="EXIT") p.SearchCommand(command, args);
                

           

          }
            while (command.toUpperCase() != "EXIT");











        }

    
	
	  private void entete(String[] args) {
          System.out.println("");
          System.out.println("********************************************** BIENVENU****************************************************************");
          System.out.println("********************************************Khoubaib Jlassi************************************************************");
          System.out.println("");
          System.out.println("                    Bienvenu sur la petite application qui permet de surveiller l'état  ");
          System.out.println("                          d'une installation de fabrication en suivant tous  ");
          System.out.println("                                 les événements système émis par "); 
          System.out.println("                                        toutes les machines \n \n");

          System.out.println("----------------**Voici la liste des commandes que vous pouvez utiliser lors de votre manipulation:**------------------- ");
          System.out.println("");
          for (String parametre : args)
          {
              System.out.print("          *[" + parametre.toUpperCase() + " ");
              switch (parametre.toUpperCase())
              {
                  case "CREATE":
                      System.out.print("]  [Nom_machine] [Id_machine] :        Créer une machine\n");
                      break;
                  case "TRACK":
                      System.out.print("]  [ID_machine] [Nombre_unité] :        Ajouter des unités pour une machine  \n");
                      break;
                  case "TEMPERATURE":
                      System.out.print("]  [ID_machine degré]    :        Ajouter une température à une  machine  \n");
                      System.out.print("          *[" + parametre.toUpperCase() + "] [ID_machine ]           :        Afficher  la  température d'une  machine  \n");
                      break;
                  case "TOTAL":
                      System.out.print("]  [ID_machine]                :        Afficher le nombre total d'unités produites par une machine  \n");
                      break;
                  case "AVERAGE":
                      System.out.print("]  [ID_machine]              :        Afficher la moyenne des unités produites par  machine  \n ");
                      break;
                  default:
                      System.out.println("");
                      break;
              }
          }


      }
	  
	  private Boolean FormatInt(String nbrForm) {

          
          try
          {
              int a = Integer.parseInt(nbrForm);
          }
          catch (Exception ex)
          {
              return false;
             
          }

          return true;
      }


	  
	  
	  
	  private void SearchCommand(String cmd, String[] args) throws Exception
      {
          int i = 0;
          int espace = 0;
          espace = cmd.indexOf(" ");
          String commande;
          if (espace != -1)
          {
               commande = cmd.substring(0, espace);
          }
          else commande =cmd;
          commande.trim();
          //System.out.println(commande);
          Boolean trouve = false;
          while (i < args.length && !trouve)
          {
              if (commande.toUpperCase().equals(args[i].toUpperCase()))
              {
                  trouve = true;
              }
              i++;

          }
          //System.out.println(espace);
          String p1, p2, Restcmd;
          
          
          if (trouve )
          {

        	 
              if (espace != -1)
              { 
            	  
                  switch (commande.toUpperCase())
                  {
                      case "CREATE":                        
                          Restcmd = cmd.substring(espace + 1).trim();
                          if (Restcmd.indexOf(" ") != -1 && Restcmd.substring(Restcmd.indexOf(" ") + 1).indexOf(" ")==-1 )
                          {
                              
                              p1 = Restcmd.substring(0, Restcmd.indexOf(" "));
                              p2=  Restcmd.substring( Restcmd.indexOf(" ")+1);
                              Machine m = new Machine();
                              if (m.Ajout(p2, p1) == 1) {
                                  System.out.println("Création réussite");

                                  }
                        
                          }
                          else  System.out.println("La commande CREATE doit avoir 2 paramétres: Nom_machine , Id_machine...");                           
                          break;

                      case "TRACK":
                          Restcmd = cmd.substring(espace + 1).trim();
                          if (Restcmd.indexOf(" ") != -1 && Restcmd.substring(Restcmd.indexOf(" ") + 1).indexOf(" ") == -1)
                          {
                              p1 = Restcmd.substring(0, Restcmd.indexOf(" "));
                              p2 = Restcmd.substring(Restcmd.indexOf(" ") + 1);
                              UnitProd up = new UnitProd();
                             
                              if (FormatInt(p2)){
                                  int a = up.Ajout(p1, Integer.parseInt(p2));

                                  if (a == -1)   System.out.println("Machine n'existe pas");
                                  else if (a==1) System.out.println("Ajout réussit");
                                  else System.out.println("problème d'ajout");

                              }

                              else System.out.println("Le nombre d'unité est invalide");

                          }
                          else System.out.println("La commande TRACK doit avoir 2 paramétres: ID_machine , Nombre_unité...");
                          break;
                      case "TEMPERATURE":
                          Restcmd = cmd.substring(espace + 1).trim();                            
                        
                          if (Restcmd.indexOf(" ") != -1 && Restcmd.substring(Restcmd.indexOf(" ") + 1).indexOf(" ") == -1)
                          {

                              
                              p1 = Restcmd.substring(0, Restcmd.indexOf(" "));
                              p2 = Restcmd.substring(Restcmd.indexOf(" ") + 1);
                              Temperature t = new Temperature();
                              if (FormatInt(p2))
                              {
                                  t.Ajout(p1, Integer.parseInt(p2));
                              }else System.out.println("La degré est invalide ");
                          }
                          else if (Restcmd.indexOf(" ") == -1) {
                              p1 = Restcmd.trim();
                              Temperature t = new Temperature();
                              int deg = t.AfficheTemp(p1);

                              if ((deg != -1) && (deg != 0)) System.out.println(deg);
                              else if (deg == 0) System.out.println("cette machine n'existe pas ");
                              else System.out.println("cette machine n'a pas de température ");
                          }

                          else
                          {
                              System.out.println("La commande TEMPERATURE doit avoir soit un  paramétre: ID_machine , pour afficher la température de  ");
                              System.out.println(" la machine ,ou bien 2 paramétres: ID_machine, degré pour affecter une température a une machine...");
                          }
                          break;
                      case "TOTAL":
                          Restcmd = cmd.substring(espace + 1).trim();
                          if (Restcmd.indexOf(" ") == -1 )
                          {
                              p1 = Restcmd.trim();
                              UnitProd up = new UnitProd();
                              if (up.totalUnite(p1) == -1)    System.out.println("Cette machine n'existe pas...");
                              else System.out.println(up.totalUnite(p1));

                          }
                          else System.out.println("La commande TOTAL doit avoir un seul  paramétre: Id_machine...");
                          break;
                      case "AVERAGE":
                          Restcmd = cmd.substring(espace + 1).trim();
                          if (Restcmd.indexOf(" ") == -1)
                          {
                              p1 = Restcmd.trim();
                              UnitProd up = new UnitProd();
                              if (up.Average(p1) == -1) System.out.println("Cette machine n'existe pas...");
                              else System.out.println(up.Average(p1));

                          }
                          else System.out.println("La commande AVERAGE doit avoir un seul  paramétre: Id_machine...");
                          break;
                      default:
                          System.out.println("");
                          break;
                  }



              }
              else {
                  
                  System.out.println("Commande sans paramétres");
              }
          }


          else {
                  System.out.println("Commande n'existe pas");
          }

         

      }
	  
}
