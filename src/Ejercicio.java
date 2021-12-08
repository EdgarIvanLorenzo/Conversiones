import java.awt.List;
import java.util.*;

public class Ejercicio {
    public static void main(String[] args) {
        String expresion;
        Scanner entrada = new Scanner(System.in);

        System.out.println("Ingrese la expresion");
        expresion = entrada.nextLine();
        boolean comprobacion=Evaluacion(expresion);
        if(comprobacion){
            System.out.println(PostFija(expresion));
            System.out.println(Prefija(expresion));
        }else{
            System.out.println("Expresion incorrecta");
        }
    }

    public static boolean Evaluacion(String cadena){
        Stack<Character> pila=new Stack<Character>();

        //Recorrido de la pila
        for(int i=0;i<cadena.length();i++){
            if(cadena.charAt(i)=='{'){
                pila.push(cadena.charAt(i));
            }else if(cadena.charAt(i)=='['){
                pila.push(cadena.charAt(i));
            }else if(cadena.charAt(i)=='('){
                pila.push(cadena.charAt(i));
            }else if(cadena.charAt(i)=='}'){
                if(!pila.isEmpty()){
                    if(pila.get(pila.size()-1)=='{'){
                        pila.pop();
                    }
                }
            }else if(cadena.charAt(i)==']'){
                if(!pila.isEmpty()){
                    if(pila.get(pila.size()-1)=='['){
                        pila.pop();
                    }
                }
            }else if(cadena.charAt(i)==')'){
                if (!pila.isEmpty()){
                    if(pila.get(pila.size()-1)=='('){
                        pila.pop();
                    }
                }
            }
        }

        //Verifica si la pila esta vacia
        if(pila.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public static String PostFija(String expresion){
        Stack<String> pila1 = new Stack<>();
        ArrayList<String> resultado = new ArrayList<>();
        for(int i=0;i<expresion.length();i++){
            if(expresion.charAt(i)=='*'||expresion.charAt(i)=='+'||expresion.charAt(i)=='-'||expresion.charAt(i)=='/'||expresion.charAt(i)=='('||expresion.charAt(i)==')'){
                if(expresion.charAt(i)==')'){
                    String valor=pila1.pop();
                    pila1.pop();
                    resultado.add(valor);
                }else{
                    pila1.push(String.valueOf(expresion.charAt(i)));
                }
            }else{
                resultado.add(String.valueOf(expresion.charAt(i)));
            }
        }
        if(!pila1.isEmpty()){
            resultado.add(pila1.pop());
        }

        return "El resultado a PostFija es :"+resultado.toString();
    }

    public static String Prefija(String expresion){
        Stack<String> pila = new Stack<>();
        ArrayList<String> resultado = new ArrayList<>();
        for(int i=expresion.length()-1;i>=0;i--){
            if(expresion.charAt(i)=='*'||expresion.charAt(i)=='+'||expresion.charAt(i)=='-'||expresion.charAt(i)=='/'||expresion.charAt(i)=='('||expresion.charAt(i)==')'){
                if(expresion.charAt(i)=='('){
                    String valor=pila.pop();
                    pila.pop();
                    resultado.add(valor);
                }else{
                    pila.push(String.valueOf(expresion.charAt(i)));
                }
            }else{
                resultado.add(String.valueOf(expresion.charAt(i)));
            }
        }

        while(!pila.isEmpty()){
            resultado.add(pila.pop());
        }

        Collections.reverse(resultado);
        return "El resultado a Prefija es: "+resultado;
    }
}
