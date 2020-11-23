
public class main {
    
    public static void main(String[] args) {
       
        arbol  arbolito = new arbol();
        
        System.out.println("A R B O L   B I N A R I O\n");
        System.out.println("Se van a agregar 20 elementos aleatorios al arbol\n");
        System.out.println("\nLos cuales son:\n");
        
        for(int i = 0; i < 20; ++i){
            int dato = (int) (Math.random() * 100 + 1);
            arbolito.insertar_arb(dato);
            System.out.print(dato+", ");
        }
    
        System.out.println("\n\n R E C O R R I D O S\n");
        System.out.println("---------------------------------------------------------------------------");
            
        System.out.println("\nEl recorrido In Orden sería:\n");
        arbolito.recorrer(0);
        
        System.out.println("\n\nEl recorrido Pre Orden sería:\n");
        arbolito.recorrer(1);     
        
        System.out.println("\n\nEl recorrido Post Orden sería:\n");
        arbolito.recorrer(2);
        
        System.out.println("\n\n\nB U S Q U E D A\n");
        System.out.println("---------------------------------------------------------------------------");
        
        boolean encontrado = false;
        do{
            int buscar = (int) (Math.random() * 100 + 1);
            System.out.println("\n\nSe buscará el número aleatorio : "+buscar+"\n");
            encontrado = arbolito.buscar(buscar);
        }while(encontrado == false);
        
        System.out.println("\n\n E L I M I N A C I O N\n");
        System.out.println("---------------------------------------------------------------------------");
        
        boolean eliminado = false;
        do{
            int eliminar = (int) (Math.random() * 100 + 1);
            System.out.println("\n\nSe eliminará el número aleatorio : "+eliminar+"\n");
            eliminado = arbolito.eliminar(eliminar);
        }while(eliminado == false);
        System.out.println("\nÁrbol actualizado\n");
        arbolito.recorrer(0);
        
        
    }
}

class arbol{
    nodo_arb raiz = null; //Se crea la raíz del árbol
    
    public void insertar_arb(int dato){ //método que inserta nodos al árbol
        nodo_arb nuevo = crear_nodo_arb(dato);
        if(raiz ==null){
            raiz = nuevo;
        }else{
            nodo_arb aux = raiz;
            nodo_arb ant = null;
            while(aux!=null){
                ant = aux;
                if(nuevo.dato > aux.dato){
                    aux = aux.der;
                }else{
                    aux = aux.izq;
                }    
            }
            if(nuevo.dato > ant.dato){
                ant.der = nuevo;
            }else{
                ant.izq = nuevo;
            }
        }
    }
    
    public void recorrer(int tipo){ //función que multiplexa los diferentes recorridos
        switch (tipo){
            case 0:
                in_orden(raiz);
                break;
            case 1:
                pre_orden(raiz); 
                break;
            case 2:
                post_orden(raiz);               
                break;
        }
    }
    
    public void in_orden(nodo_arb aux){ //recorrido in_orden
        if (aux != null) {
            in_orden(aux.izq);
            System.out.print(aux.dato + " ");
            in_orden(aux.der);
        } 
    }
    
    public void post_orden(nodo_arb aux){ //recorrido post_orden
        if (aux != null) {
            post_orden(aux.izq);
            post_orden(aux.der);
            System.out.print(aux.dato + " ");
        } 
    }
    
    public void pre_orden(nodo_arb aux){ //recorrido pre_orden
        if (aux != null) {
            System.out.print(aux.dato + " ");
            pre_orden(aux.izq);
            pre_orden(aux.der);
        } 
    }
    
    public boolean buscar(int dato){ //función que busca un elemento en el árbol
        nodo_arb buscar = encontrar(raiz, dato);
        boolean bandera = false;
        if(buscar == null){
            System.out.println("No se encontró el número");
            bandera = false;
        }else{
            System.out.println("Se ha encontrado el número: "+dato);
            bandera = true;
        }
        return bandera;
    }
    
    public nodo_arb encontrar(nodo_arb aux, int dato){ //función que busca encuentra un nodo del árbol
        if (aux == null || dato == aux.dato) return aux;
        if (dato < aux.dato) return encontrar(aux.izq, dato);
        return encontrar(aux.der, dato);
    }
    
    public boolean eliminar(int dato){ //función que elimina un nodo del árbol
        nodo_arb aux = encontrar(raiz, dato);
        boolean eliminado = false;
        if(aux != null){
            raiz = eliminar_m(raiz, dato);
            System.out.print("\nEl número: " + dato + " se ha eliminado");
            eliminado = true;
        }else{
            System.out.print("\nNo se eliminó el número porque no existe");
            eliminado = false;
        }
        return eliminado;
    }
    
    public nodo_arb eliminar_m(nodo_arb inicio, int dato){ //función que elimina un nodo del árbol
        if(inicio == null) return inicio;
        
        if(dato < inicio.dato){
            inicio.izq = eliminar_m(inicio.izq, dato);
        }else if(dato > inicio.dato){
            inicio.der = eliminar_m(inicio.der, dato);
        }else{
            if(inicio.izq == null){
                return inicio.der;
            }else if(inicio.der == null){
                return inicio.izq;
            }
            nodo_arb aux = minimo(inicio.der);
            inicio.dato = aux.dato;
            inicio.der = eliminar_m(inicio.der, inicio.dato);
        }
        return inicio;      
    }
    
    public nodo_arb minimo(nodo_arb aux){//función que encuentra el minimo de un árbol
        while(aux.izq != null){
            aux = aux.izq;
        }
        return aux;
    }
    
    public nodo_arb crear_nodo_arb(int dato){ //funcion que crea un nodo
        nodo_arb nuevo = new nodo_arb();
        nuevo.dato = dato;
        nuevo.izq = null;
        nuevo.der = null;
        
        return nuevo;
    }
}
class nodo_arb{ //la clase nodo
    int dato;
    nodo_arb izq;
    nodo_arb der;
}
