package com.JAVA.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/moduleServlet")
public class ModuleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ModuleServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = null;
        Module module = null;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            conn = daoFactory.getConnection();
            ModuleDaoIMPL moduleDao = new ModuleDaoIMPL(conn);
            String action = request.getParameter("action");

            if (action == null || action.equals("list")) {
                List<Module> modules = moduleDao.getAll();
                request.setAttribute("modules", modules);
                request.getRequestDispatcher("module.jsp").forward(request, response);
            } else if (action.equals("ajout")) {
                request.getRequestDispatcher("ajoutermodule.jsp").forward(request, response);
            } else if (action.equals("edit")) {
                int moduleId = Integer.parseInt(request.getParameter("id"));
                module = moduleDao.getById(moduleId);
                request.setAttribute("module", module);
                request.getRequestDispatcher("editermodule.jsp").forward(request, response);
            } else if (action.equals("supprimer")) {
                int moduleId = Integer.parseInt(request.getParameter("id"));
                moduleDao.delete(moduleId);
                response.sendRedirect("moduleServlet?action=list");
            } else if (action.equals("consulter")) {
                int moduleId = Integer.parseInt(request.getParameter("id"));
                module = moduleDao.getById(moduleId);
                request.setAttribute("module", module);
                request.getRequestDispatcher("consultermodule.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        Connection conn = null;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            conn = daoFactory.getConnection();
            ModuleDaoIMPL moduleDao = new ModuleDaoIMPL(conn);

            if (action.equals("ajouter")) {
                request.getParameter("nom_module");
                request.getParameter("abrev_module");
                Module newModule = new Module();
                moduleDao.insert(newModule);
                response.sendRedirect("moduleServlet?action=list");
            } else if (action.equals("editer")) {
                Integer.parseInt(request.getParameter("id_module"));
                request.getParameter("nom_module");
                request.getParameter("abrev_module");
                Module updatedModule = new Module();
                moduleDao.update(updatedModule);
                response.sendRedirect("moduleServlet?action=list");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
