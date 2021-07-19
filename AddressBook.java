//Mario Alejandro Aguirre A. | 2944585

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.*;
import java.util.*;
import java.io.*;

public class AddressBook {
    //Funciones
    public void Crear (String Numero, String Nombre, HashMap Contactos, PrintWriter pw)
    {
        Contactos.put(Numero, Nombre);
        pw.println(" "+Numero+","+Nombre);
        pw.close();
        System.out.println("\nContacto agregado");
    }

    public void Lista (HashMap Contactos)
    {
        //System.out.println(Contactos);
        /*Set<Map.Entry<String, String>> contacts = Contactos.entrySet();
        for (Map.Entry<String, String> info : contacts)
        {
            System.out.println("{"+info.getKey()+"}:{"+info.getValue()+"}");
        }*/

        Contactos.forEach((numero, nombre)->
        {
            System.out.println("{"+numero+"}:{"+nombre+"}");
        });
    }

    public void Borrar (HashMap Contactos, String Nombre_Borrar)
    {
        Contactos.remove(Nombre_Borrar);
        System.out.println("Contacto eliminado ");
    }

    public void ReadFile (HashMap Contactos) throws FileNotFoundException
    {
        File agendaN = new File("AddressBook.txt");
        Scanner fscan = new Scanner(agendaN);
        String line;
        boolean test;
            try
            {
                do {
                    //System.out.println(fscan.nextLine());
                    line = fscan.next();
                    //System.out.println(" "+line);
                    String[] values = line.split(",");
                    Contactos.put(values[0], values[1]);
                }while(line!=null);
            }catch (Exception e)
            {
            }
    }
    //
    public static void main(String[] args) throws IOException {
        //Variables
        File agenda = new File("AddressBook.txt");
        FileWriter fw = new FileWriter(agenda, true);
        PrintWriter pw = new PrintWriter(fw);
        //pw.println("Test");
        //pw.close();

        Scanner scan = new Scanner(System.in);
        HashMap<String, String> Contactos = new HashMap<String, String>();
        String Numero;
        String Nombre;
        String Numero_Borrar;
        int Opcion;
        AddressBook obj = new AddressBook();

        do {
            System.out.println("\n1. Mostrar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Borrar contacto");
            System.out.println("4. Cargar contactos de archivo");
            System.out.println("0. Salir");
            System.out.println("\n Selecciona opcion: ");
            Opcion = scan.nextInt();

            switch (Opcion) {
                case 1:
                    System.out.println("Lista de Contactos");
                    if(Contactos.isEmpty())
                    {
                        System.out.println("\n**Lista vacia, ingrese o cargue contactos**");
                    }else
                    obj.Lista(Contactos);
                    break;
                case 2:
                    System.out.println("\nNombre de contacto:");
                    Nombre = scan.next();
                    System.out.println("Numero de contacto:");
                    Numero = scan.next();

                    obj.Crear(Numero, Nombre, Contactos , pw);
                    break;
                case 3:
                    System.out.println("\nBorrar contacto");
                    System.out.println("Numeros disponibles:");
                    System.out.println(""+Contactos.keySet());
                    System.out.println("Ingresa numero a eliminar:");
                    Numero_Borrar = scan.next();
                    obj.Borrar(Contactos, Numero_Borrar);
                    break;
                case 4:
                    System.out.println("\nArchivos cargados");
                    obj.ReadFile(Contactos);
            }
        }while (Opcion !=0);
    }
}
