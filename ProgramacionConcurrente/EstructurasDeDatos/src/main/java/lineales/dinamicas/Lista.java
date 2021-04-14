package lineales.dinamicas;

/**
 *
 * @author Mauricio Sawicki
 */
public class Lista {

    private Nodo cabecera;
    private int longitud;

    public Lista() {
        this.cabecera = null;
        this.longitud = 0;
    }

    public int longitud() {
        return this.longitud;
    }

    public void vaciar() {
        this.cabecera = null;
        this.longitud = 0;
    }

    public boolean esVacia() {
        boolean res = false;

        if (this.cabecera == null) {
            res = true;
        }

        return res;
    }

    public boolean insertar(Object elem, int pos) {
        //inserta el elemento nuevo en la posicion pos
        //detecta y reporta error si es una pos invalida
        boolean exito = true;

        if (pos < 1 || pos > this.longitud + 1) {
            exito = false;
        } else {
            if (pos == 1) //crea un nuevo nodo y lo enlaza a la cabecera
            {
                this.cabecera = new Nodo(elem, this.cabecera);
            } else { //avanza hasta el elemento en pos-1
                Nodo aux = this.cabecera;
                int i = 1;
                while (i < pos - 1) {
                    aux = aux.getEnlace();
                    i++;
                }
                //crea el nodo nuevo y lo enlaza para no perderlo
                Nodo nuevo = new Nodo(elem, aux.getEnlace());
                //enlaza el nodo nuevo al de su izquierda
                aux.setEnlace(nuevo);
            }

            this.longitud++;
        }
        //nunca hay error de lista llena por lo tanto devuelve siempre true
        //si es que esta insertando en posicion valida
        return exito;
    }

    public boolean eliminar(int pos) {
        boolean exito;
        //controlo que no se trate de eliminar en una posición inválida
        if (pos < 1 || pos > this.longitud) {
            exito = false;
        } else {
            //caso especial si quiero eliminar en la posicion 1
            //enlazo la cabecera al siguiente nodo 
            //el primer nodo queda sin apuntar y se lo lleva el Garbage Collector
            if (pos == 1) {
                this.cabecera = this.cabecera.getEnlace();
            } else {
                //caso general eliminar 
                int i = 1;
                //creo un puntero aux que apunta al mismo nodo que apunta la cabecera
                Nodo aux = this.cabecera;
                while (i < pos - 1) {
                    //me ubico en la posicion del nodo anterior al que quiero eliminar
                    aux = aux.getEnlace();
                    i++;
                }
                //enlazo aux con el siguiente del siguiente 
                //el nodo a eliminar queda sin apuntar
                //por lo tanto se lo lleva el garbage collector
                aux.setEnlace(aux.getEnlace().getEnlace());
            }
            //la lista decrece en 1 unidad ya que elimine 1 elemento
            this.longitud--;
            exito = true;
        }
        return exito;
    }

    public Object recuperar(int pos) {
        Object elem = null;
        //rango posiciones validas para recuperar un elemento
        if (pos >= 1 && pos <= this.longitud) {
            //caso especial recuperar el elemento de la pos 1
            //simplemente le asigno a elem el elemento que hay en la cabecera
            if (pos == 1) {
                elem = this.cabecera.getElem();
            } else {
                //caso general
                int i = 1;
                Nodo aux = this.cabecera;
                //me situo en la posicion del elemento a recuperar
                while (i < pos) {
                    aux = aux.getEnlace();
                    i++;
                }
                //le asigno a elem el elemento que se encuentra en el nodo que apunta aux
                elem = aux.getElem();
            }
        }
        return elem;
    }

    public int localizar(Object elemento) {
        int pos = -1;
        int i = 1;
        Nodo aux = this.cabecera;
        while (aux != null) {
            //si el elemento extraido de aux es igual al elemento que estoy buscando
            if (aux.getElem().equals(elemento)) {
                aux = null;
                pos = i;
            } else {
                aux = aux.getEnlace();
                i++;
            }
        }
        return pos;
    }

    public void invertir() {
        Nodo anterior = this.cabecera;
        invertirAux(this.cabecera);
        if (anterior != null) {
            anterior.setEnlace(null);
        }
    }

    public void invertirAux(Nodo nodo) {
        if (nodo != null) {
            this.cabecera = nodo;
            invertirAux(nodo.getEnlace());
            if (nodo.getEnlace() != null) {
                nodo.getEnlace().setEnlace(nodo);
            }
        }
    }

    @Override
    public Lista clone() {
        Lista clon = new Lista();
        //Le asigno la longitud de la lista original a la lista clon
        clon.longitud = this.longitud;

        //Si la lista original tiene elementos HACER
        if (!esVacia()) {
            Nodo aux = this.cabecera;
            Nodo aux2 = new Nodo(aux.getElem(), null);

            clon.cabecera = aux2;

            //Declaro un tercer puntero
            Nodo aux3;
            aux = aux.getEnlace();

            //Mientras que sigan habiendo enlaces en mi Lista original
            while (aux != null) {
                //Creo el tercer puntero que va guardando los elementos de mi lista oroginal
                aux3 = new Nodo(aux.getElem(), null);

                aux2.setEnlace(aux3);
                aux2 = aux2.getEnlace();
                aux = aux.getEnlace();
            }

        }
        return clon;
    }

    @Override
    public String toString() {
        String s = "Lista vacia";
        if (this.cabecera != null) {
            Nodo aux = this.cabecera;
            s = "[";
            while (aux != null) {
                s += aux.getElem().toString();
                aux = aux.getEnlace();
                if (aux != null) {
                    s += ", ";
                }
            }
            s += "]";
        }
        return s;
    }


    public Lista obtenerMultiplos(int num) {
        Lista lista = new Lista();
        Object elemento;
        //hago que sig apunte al mismo nodo que esta apuntando la cabecera de la lista
        Nodo sig = this.cabecera;
        Nodo mult = null;
        int i = 1;
        while (sig != null) {
            elemento = sig.getElem();
            if (elemento.equals(num)) {
                // se ejecuta la primer vez esto
                mult = new Nodo(elemento, null);
                lista.cabecera = mult;
            } else if (i % num == 0) {
                mult.setEnlace(new Nodo(elemento, null));
                mult = mult.getEnlace();
            }
            sig = sig.getEnlace();
            i++;
        }
        return lista;
    }
    
        public Lista obtenerMultiplos2(int num) {
        Lista nueva = new Lista();
        int c = 1;
        Nodo aux = this.cabecera, aux2 = null;

        if (num != 0) {
            while (aux != null) {
                if ((c % (num)) == 0) {
                    if (nueva.cabecera == null) {
                        Nodo nuevo = new Nodo(aux.getElem(), null);
                        nueva.cabecera = nuevo;
                        nueva.longitud++;
                        aux2 = nueva.cabecera;
                    } else {
                        Nodo nuevo = new Nodo(aux.getElem(), null);
                        aux2.setEnlace(nuevo);
                        nueva.longitud++;
                        aux2 = aux2.getEnlace();
                    }
                }
                aux = aux.getEnlace();
                c++;
            }
        }

        return nueva;
    }
    
    //Recorre una unica vez la lista y elimina todas las apariciones de elementos iguales a x
    public void eliminarApariciones(Object x) {
        if (this.cabecera != null) {
            //Creo puntero sig que apunta al mismo nodo que esta apuntando la cabecera de la lista
            Nodo sig = this.cabecera;
            Nodo ant = null;
            this.cabecera = null;
            Object elemento;
            //Mientras sigan habiendo nodos en la lista
            while (sig != null) {
                //Voy guardando el elem del nodo en la variable elemento
                elemento = sig.getElem();
                //Si el elemento no es igual al enviado por parametro
                if (!elemento.equals(x)) {
                    //Caso de que la cabecera este vacia
                    if (this.cabecera == null) {
                        //Creo un nuevo nodo ant y le guardo el elemento
                        ant = new Nodo(elemento, null);
                        this.cabecera = ant;
                        //Caso de que la cabecera contenga un elemento
                    } else {
                        ant.setEnlace(new Nodo(elemento, null));
                        ant = ant.getEnlace();
                    }
                    //Si el elemento es igual al enviado por parametro decremento en 1 la longitud de la lista
                } else {
                    this.longitud--;
                }
                //Avance al siguiente nodo
                sig = sig.getEnlace();
            }	
        }
    }
    
    //Agrega el elemento "nuevo" a la Lista en la primera posición y luego
    //lo repite cada "x" posiciones
    public void agregarElem(Object nuevo, int x) {
        if (this.longitud > 0) {
            Nodo aux = new Nodo(nuevo, this.cabecera);
            this.cabecera = aux;
            this.longitud += this.longitud % x;
            int valor = 0;
            while (aux != null) {
                if (valor == x ) {
                    Nodo aux2 = new Nodo(nuevo, aux.getEnlace());
                    aux.setEnlace(aux2);
                    this.longitud++;
                    valor = 0;
                } else {
                    valor++;
                }
                aux = aux.getEnlace();
            }
        }
    }
    
        public Lista generarSecuencia(Cola q, int t) {
        Cola cola = q.clone();
        Lista lista = new Lista();
        Object elemento;
        int x = 1, i = 0, r = 1;
        while (!cola.esVacia()) {
            i += 2;
            elemento = cola.obtenerFrente();
            lista.insertar(elemento, r);
            lista.insertar(elemento, i);   
            
            cola.sacar();
            
            if (x % t == 0 && !cola.esVacia()) {
                i++;
                lista.insertar('#', i);
                lista.insertar('#', i++);
                
                r = i + 1;
            }
            x++;
        }
        return lista;
    }
        
    
    public static Lista generarSecuenciaExpress(Cola q, int t) {
        Cola cola = q.clone();
        Lista lista = new Lista();
        Pila pila = new Pila();
        Pila rever = new Pila();
        Object elemento;
        int i = 1;
        while (!cola.esVacia()) {
            pila.apilar(cola.obtenerFrente());
            cola.sacar();
            if (i % t == 0 && !cola.esVacia())
                pila.apilar('#');
            i++;
        }
        while (!pila.esVacia()) {
            elemento = pila.obtenerTope();
            if (elemento.equals('#')) {
                while (!rever.esVacia()) {
                    lista.insertar(rever.obtenerTope(), 1);
                    rever.desapilar();
                }
                lista.insertar('#', 1);
                lista.insertar('#', 2);
            } else {
                lista.insertar(elemento, 1);
                rever.apilar(elemento);
            }
            pila.desapilar();
        }
        while (!rever.esVacia()) {
            lista.insertar(rever.obtenerTope(), 1);
            rever.desapilar();
        }
        return lista;
    }


    public void insertarPromedio() {
        int res = 0;
        Nodo aux = this.cabecera, nuevo;

        if (this.cabecera != null) {
            while (aux.getEnlace() != null) {
                res = res + (int) aux.getElem();
                aux = aux.getEnlace();
            }
            res = (res + (int) aux.getElem()) / this.longitud;
            nuevo = new Nodo(res, null);
            aux.setEnlace(nuevo);
            this.longitud++;
        }
    }

    public void promedioPublic() {
        promedioRecursivo(this.cabecera, 0);
    }

    private void promedioRecursivo(Nodo referencia, int res) {

        if (referencia.getEnlace() == null) {
            res = (res + (int) referencia.getElem()) / this.longitud;
            Nodo nuevo = new Nodo(res, null);
            referencia.setEnlace(nuevo);
            this.longitud++;
        } else {
            res = res + (int) referencia.getElem();
            referencia = referencia.getEnlace();
            promedioRecursivo(referencia, res);
        }
    }

    public void agregarProducto(int x) {
        Nodo aux = this.cabecera, nuevo;
        int i, c = 1, longitudLista = this.longitud, res;

        while (c <= (longitudLista / x)) {
            i = 1;
            res = 1;
            while (i < x) { //si no anda ver esto
                res = res * (int) aux.getElem();
                aux = aux.getEnlace();
                i++;
            }
            res = res * (int) aux.getElem();
            nuevo = new Nodo(res, aux.getEnlace());
            aux.setEnlace(nuevo);
            aux = aux.getEnlace().getEnlace();
            this.longitud++;
            c++;
        }

        res = 1;

        while (aux.getEnlace() != null) {
            res = res * (int) aux.getElem();
            aux = aux.getEnlace();
        }
        res = res * (int) aux.getElem();
        nuevo = new Nodo(res, null);
        aux.setEnlace(nuevo);
        this.longitud++;
    }    
    
    
}

