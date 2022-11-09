package com.emergentes.controlador;

import com.emergentes.modelos.Productos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SessionServlet", urlPatterns = {"/SessionServlet"})
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession ses = request.getSession();
        if (ses.getAttribute("listapro")==null ) {
            ArrayList<Productos> listaux= new ArrayList <Productos>();
            ses.setAttribute("listapro", listaux);
        }
        
        ArrayList<Productos> lista = (ArrayList<Productos> )ses.getAttribute("listapro");
        
        String op = request.getParameter("op");
        String opcion = (op != null)? request.getParameter("op"):"view";
        
        Productos pro1 = new Productos();
        int id, pos;
        
        switch(opcion){
            case "nuevo"://Insertar Nuevo Producto
                request.setAttribute("miProducto", pro1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
            
            case "editar" ://Modificar Producto
                id = Integer.parseInt(request.getParameter("id"));
                pos= buscarIndice(request,id);
                pro1=lista.get(pos);
                request.setAttribute("miProducto", pro1);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                break;
                
            case "eliminar" ://Eliminar Producto
                pos=buscarIndice(request, Integer.parseInt(request.getParameter("id")));
                lista.remove(pos);
                ses.setAttribute("listapro", lista);
                response.sendRedirect("index.jsp");
                break;
            case "view" :
                response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession ses = request.getSession();
        ArrayList<Productos> lista = (ArrayList<Productos> ) ses.getAttribute("listapro");
        
        Productos pro1 = new Productos();
        
        pro1.setId(Integer.parseInt(request.getParameter("id")));
        pro1.setDescripcion(request.getParameter("descripcion"));
        pro1.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
        pro1.setPrecio(Double.parseDouble(request.getParameter("precio")));
        pro1.setCategoria(request.getParameter("categoria"));
        
        int idt = pro1.getId();
        
        if (idt == 0) {
            int ultID;
            ultID =ultimoId(request);
            pro1.setId(ultID);
            lista.add(pro1);
        }else{
            lista.set(buscarIndice(request, idt), pro1);
        }
        ses.setAttribute("listapro", lista);
        response.sendRedirect("index.jsp");

    }
    
    private int ultimoId(HttpServletRequest request){
        HttpSession ses = request.getSession();
        ArrayList<Productos> lista =(ArrayList<Productos>) ses.getAttribute("listapro");
        
        int idaux =0;
        
        for (Productos item: lista) {
            idaux = item.getId();
        }
        return idaux +1;
    }
    private int buscarIndice(HttpServletRequest request, int id){
        HttpSession ses = request.getSession();
        ArrayList<Productos> lista =(ArrayList<Productos>) ses.getAttribute("listapro");
        
        int i=0;
        if (lista.size()>0) {
            while  (i < lista.size()){
                if (lista.get(i).getId()== id) {
                    break;
                }else{
                    i++;
                }
            }
        }
        return i;
    }

}
