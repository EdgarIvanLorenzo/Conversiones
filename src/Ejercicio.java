import java.awt.List;
import java.util.*;

public class Ejercicio {
    public static Hashtable<String,Integer> Hash(){
        Hashtable<String,Integer> hash=new Hashtable<>();
        hash.put("^",3);
        hash.put("*",2);
        hash.put("/",2);
        hash.put("+",1);
        hash.put("-",1);
        return hash;
    }

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
        Hashtable<String,Integer> hash=new Hashtable<>();
        hash=Ejercicio.Hash();
        ArrayList<String> resultado = new ArrayList<>();
        for(int i=0;i<expresion.length();i++){
            if(expresion.charAt(i)=='*'||expresion.charAt(i)=='+'||expresion.charAt(i)=='-'||expresion.charAt(i)=='/'||expresion.charAt(i)=='('||expresion.charAt(i)==')'||expresion.charAt(i)=='^'){
                if(expresion.charAt(i)==')'){
                    String valor;
                    valor=pila1.get(pila1.size()-1);
                    while(!valor.equals("(")){
                        valor=pila1.pop();
                        resultado.add(valor);
                        if(!pila1.isEmpty()){
                            valor=pila1.get(pila1.size()-1);
                        }else{
                            break;
                        }
                    }
                    if(!pila1.isEmpty()){
                        pila1.pop();
                    }
                }else if(expresion.charAt(i)=='('){
                    pila1.add(String.valueOf(expresion.charAt(i)));
                }else{
                    boolean salir=true;
                    if(!pila1.isEmpty()){
                        String valor=pila1.get(pila1.size()-1).toString();
                        if(!valor.equals("(")){
                            if(!valor.equals(")")){
                                while(salir && !pila1.isEmpty()){
                                    if(!valor.equals("(")){
                                        if(!valor.equals(")")){
                                            int valor1=hash.get(String.valueOf(expresion.charAt(i)));
                                            int valor2=hash.get(valor);
                                            System.out.println("Valor: "+expresion.charAt(i)+" = "+valor1);
                                            System.out.println("Pila: "+hash.get(String.valueOf(expresion.charAt(i))));
                                            if(valor1<=valor2){
                                                resultado.add(valor);
                                                pila1.pop();
                                                if(!pila1.isEmpty()){
                                                    valor=pila1.get(pila1.size()-1);
                                                }else{
                                                    break;
                                                }
                                            }else{
                                                pila1.add(String.valueOf(expresion.charAt(i)));
                                                break;
                                            }
                                        }else{
                                            pila1.push(String.valueOf(expresion.charAt(i)));
                                            break;
                                        }
                                    }else{
                                        pila1.push(String.valueOf(expresion.charAt(i)));
                                        break;
                                    }
                                }
                            }else{
                                pila1.push(String.valueOf(expresion.charAt(i)));
                            }
                        }else{
                            pila1.push(String.valueOf(expresion.charAt(i)));
                        }
                    }else{
                        System.out.println(expresion.charAt(i));
                        pila1.push(String.valueOf(expresion.charAt(i)));
                    }

                }
            }else{
                resultado.add(String.valueOf(expresion.charAt(i)));
            }
        }
        if(!pila1.isEmpty()){
            for(String p:pila1){
                if(!p.equals("(")){
                    if(!p.equals(")")){
                        resultado.add(p);
                    }
                }

            }

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
